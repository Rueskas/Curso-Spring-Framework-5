package com.iessanvicente.springboot.datajpa.app.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iessanvicente.springboot.datajpa.app.models.entities.Cliente;
import com.iessanvicente.springboot.datajpa.app.models.entities.Factura;
import com.iessanvicente.springboot.datajpa.app.models.entities.ItemFactura;
import com.iessanvicente.springboot.datajpa.app.models.entities.Producto;
import com.iessanvicente.springboot.datajpa.app.models.services.IClienteService;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/app/factura")
@SessionAttributes("factura")
public class FacturaController {
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/{id}")
	public String detalle(@PathVariable Long id, Model model, RedirectAttributes flash) {
		//Factura factura = clienteService.findFacturaById(id);
		Factura factura = clienteService.fetchFacturaById(id);
		if(factura == null) {
			flash.addFlashAttribute("error", "No se ha encontrado la factura");
			return "redirect:/app/clientes";
		}
		model.addAttribute("titulo", "Factura: ".concat(factura.getDescripcion()));
		model.addAttribute("factura", factura);
		return "factura/detalle";
	}
	
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable Long clienteId, Model model, RedirectAttributes flash) {
		Cliente cliente = clienteService.findById(clienteId);
		if(cliente == null) {
			flash.addFlashAttribute("error", "No se ha encontrado el cliente");
			return "redirect:/app/clientes";
		}
		Factura factura = new Factura();
		factura.setCliente(cliente);
		model.addAttribute("titulo", "Crear Factura");
		model.addAttribute("factura", factura);
		return "factura/form";
	}
	
	@PostMapping("/form")
	public String crear(@Valid Factura factura, 
			BindingResult result,
			Model model,
			@RequestParam(name="item_id[]",required=false) Long[] itemIds, 
			@RequestParam(name="cantidad[]", required=false) Integer[] cantidades,
			RedirectAttributes flash,
			SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "factura/form";
		}
		if(itemIds == null || itemIds.length == 0) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "Añade productos a la factura antes de crearla.");
			return "factura/form";
		}
		List<Long> ids = Arrays.asList(itemIds);
		ids.stream().map(id -> {
			ItemFactura item = new ItemFactura();
			Producto p = clienteService.findProductoById(id);
			item.setProducto(p);
			item.setCantidad(cantidades[ids.indexOf(id)]);
			return item;
		}).forEach(item -> factura.addItem(item));
		System.out.println(factura);
		clienteService.saveFactura(factura);
		status.setComplete();
		flash.addFlashAttribute("success", "La factura se ha creado correctamente");
		return  "redirect:/app/cliente/"+factura.getCliente().getId();
	}
	@GetMapping(value="/productos/{filter}", produces= {"application/json"})
	public @ResponseBody List<Producto> getProductos(@PathVariable String filter){
		return clienteService.findProducto(filter);
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
		Factura f = clienteService.findFacturaById(id);
		if(f != null) {
			clienteService.deleteFacturaById(id);
			flash.addFlashAttribute("success", "La factura se ha eliminado con exito.");
			return "redirect:/app/cliente/" + f.getCliente().getId();
		} else {
			flash.addFlashAttribute("error", "No se ha encontrado la factura");
			return "redirect:/app/clientes";
		}
	}
}
