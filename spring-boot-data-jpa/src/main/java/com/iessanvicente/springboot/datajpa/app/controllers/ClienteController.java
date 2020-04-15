package com.iessanvicente.springboot.datajpa.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;
import com.iessanvicente.springboot.datajpa.app.models.services.IClienteService;
import com.iessanvicente.springboot.datajpa.app.models.services.IUploadFileService;
import com.iessanvicente.springboot.datajpa.app.util.paginator.PageRender;

@Controller
@RequestMapping("/app")
public class ClienteController {
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("cliente/{id}")
	public String detalle(@PathVariable Long id, Model model, RedirectAttributes flash) {
		//Cliente cliente = clienteService.findById(id);
		Cliente cliente = clienteService.fetchById(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no se ha encontrado");
			return "redirect:/app/clientes";
		}
		model.addAttribute("titulo", "Detalle del cliente: " + cliente.getNombre());
		model.addAttribute("cliente", cliente);
		return "detalle";
	}
	
	@GetMapping("/img/{filename:.+}")
	public ResponseEntity<?> getImage(@PathVariable String filename){
		Resource resource;
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido cargar el avatar");
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\""+resource.getFilename() +"\"")
				.body(resource);
	}

	@GetMapping({ "/clientes/{page}", "/clientes", "/"})
	public String listar(Model model, @PathVariable Optional<Integer> page) {
		Page<Cliente> clientes = clienteService.findAll(PageRequest.of(page.orElse(0), 4));

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
	public String save(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
			@RequestParam("file") MultipartFile file) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Nuevo Cliente");
			return "form";
		} else {
			if (!file.isEmpty()) {
				try {
					String nombreFile = uploadFileService.copy(file);
					if(cliente.getId() != null) {
						Cliente clienteSaved = clienteService.findById(cliente.getId());
						if(clienteSaved.getAvatar() != null) {
							uploadFileService.delete(clienteSaved.getAvatar());
							flash.addFlashAttribute("info", "El avatar se ha modificado correctamente");
						} else {
							flash.addFlashAttribute("info", "El avatar se ha subido correctamente");
						}
					} else if(cliente.getId() == null) {
						flash.addFlashAttribute("info", "El avatar se ha subido correctamente");
					}
					cliente.setAvatar(nombreFile);
				} catch (IOException e) {
					e.printStackTrace();
					flash.addFlashAttribute("warning", "El avatar no se ha podido subir");
				}
			}

			clienteService.save(cliente);
			if (cliente.getId() != null) {
				flash.addFlashAttribute("success", "Cliente editado con exito");
			} else {
				flash.addFlashAttribute("success", "Cliente creado con exito");
			}
			return "redirect:clientes";
		}
	}

	@GetMapping("/form/{id}")
	public String editar(Model model, @PathVariable Long id, RedirectAttributes flash) {
		Cliente clienteEditar = null;
		clienteEditar = clienteService.findById(id);

		if (clienteEditar == null) {
			flash.addFlashAttribute("error", "El cliente no se ha encontrado");
			return "redirect:/app/clientes";
		} else {
			model.addAttribute("titulo", "Editar Cliente");
			model.addAttribute("cliente", clienteEditar);
			return "form";
		}
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model, @PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = clienteService.findById(id);
			if(cliente != null) {
				String image = cliente.getAvatar();
				if(image != null && uploadFileService.delete(image)) {
					flash.addAttribute("info", "Avatar modificado con exito");
				}
				flash.addFlashAttribute("success", "Cliente eliminado con exito");
				clienteService.deleteById(id);
			}
		}
		return "redirect:/app/clientes";
	}
}
