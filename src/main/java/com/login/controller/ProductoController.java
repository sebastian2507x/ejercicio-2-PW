package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.login.model.Producto;
import com.login.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("productos", productoService.getAllProductos());
		return "lista-productos";
	}

	@GetMapping("/new")
	public String formularioNuevo(Model model) {
		model.addAttribute("producto", new Producto());
		return "nuevo-producto";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Producto producto) {
		productoService.createProducto(producto);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String formularioEditar(@PathVariable Long id, Model model) {
		model.addAttribute("producto", productoService.getProductoById(id).orElseThrow());
		return "editar-producto";
	}

	@PostMapping("/update/{id}")
	public String actualizar(@PathVariable Long id, @ModelAttribute Producto producto) {
		productoService.updateProducto(id, producto);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable Long id) {
		productoService.deleteProducto(id);
		return "redirect:/";
	}

	/** Página personalizada de acceso denegado. */
	@GetMapping("/403")
	public String accesoDenegado() {
		return "403";
	}
}
