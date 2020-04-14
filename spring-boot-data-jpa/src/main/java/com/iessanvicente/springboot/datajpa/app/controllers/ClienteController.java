package com.iessanvicente.springboot.datajpa.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;
import com.iessanvicente.springboot.datajpa.app.models.services.IClienteService;
import com.iessanvicente.springboot.datajpa.app.util.paginator.PageRender;

@Controller
@RequestMapping("/app")
public class ClienteController {
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("cliente/{id}")
	public String detalle(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = clienteService.findById(id);
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no se ha encontrado");
			return "redirect:/app/clientes";
		}
		model.addAttribute("titulo", "Detalle del cliente: " + cliente.getNombre());
		model.addAttribute("cliente", cliente);
		return "detalle";
	}
	
	@GetMapping({"/clientes/{page}", "/clientes"})
	public String listar(Model model, @PathVariable Optional<Integer> page) {
		Page<Cliente> clientes = clienteService.findAll(
				PageRequest.of(page.orElse(0), 4));
		
		PageRender<Cliente> pageRender = new PageRender<>("/app/clientes", clientes);
		
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo", "Nuevo Cliente");
		model.addAttribute("cliente", new Cliente());
		return "form";
	}
	
	@PostMapping("/form")
	public String save(@Valid Cliente cliente, BindingResult result, Model model, 
			RedirectAttributes flash, @RequestParam("file") MultipartFile file) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Nuevo Cliente");
			return "form";
		} else {
			if(!file.isEmpty()) {
				Path directorioRecursos = Paths.get("src//main//resources//static//upload");
				String rootPath = directorioRecursos.toFile().getAbsolutePath();
				try {
					byte[] bytes = file.getBytes();
					String[] filenameSplited = file.getOriginalFilename().split("\\.");
					if(filenameSplited.length > 1) {
						String extension = filenameSplited[filenameSplited.length-1];
						String nombreFile = UUID.randomUUID()+"."+extension;
						Path rutaCompleta = Paths.get(rootPath+"//"+nombreFile);
						Files.write(rutaCompleta, bytes);
						flash.addFlashAttribute("info", "El avatar se ha subido correctamente");
						cliente.setAvatar(nombreFile);
					} else {
						flash.addFlashAttribute("warning", "El avatar no se ha podido subir");
					}
				} catch (IOException e) {
					e.printStackTrace();
					flash.addFlashAttribute("warning", "El avatar no se ha podido subir");
				}
			}
			
			clienteService.save(cliente);
			if(cliente.getId() != null) {
				flash.addFlashAttribute("success", "Cliente editado con exito");
			} else {
				flash.addFlashAttribute("success", "Cliente creado con exito");
			}
			return "redirect:clientes";
		}
	}
	
	@GetMapping("/form/{id}")
	public String editar(Model model, @PathVariable Long id,  RedirectAttributes flash) {
		Cliente clienteEditar = null;
		clienteEditar = clienteService.findById(id);
		
		if(clienteEditar == null) {
			flash.addFlashAttribute("error", "El cliente no se ha encontrado");
			return "redirect:/app/clientes";
		} else {
			model.addAttribute("titulo", "Editar Cliente");
			model.addAttribute("cliente", clienteEditar);
			return "form";
		}
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model, @PathVariable Long id,  RedirectAttributes flash) {
		if(id > 0) {
			flash.addFlashAttribute("success", "Cliente eliminado con exito");
			clienteService.deleteById(id);
		}
		return "redirect:/app/clientes";
	}
}
