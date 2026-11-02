package com.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.model.Producto;
import com.login.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}

	public Optional<Producto> getProductoById(Long id) {
		return productoRepository.findById(id);
	}

	public Producto createProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	public Producto updateProducto(Long id, Producto productoDetails) {
		Producto producto = productoRepository.findById(id).orElseThrow();
		producto.setNombre(productoDetails.getNombre());
		producto.setPrecio(productoDetails.getPrecio());
		return productoRepository.save(producto);
	}

	public void deleteProducto(Long id) {
		Producto producto = productoRepository.findById(id).orElseThrow();
		productoRepository.delete(producto);
	}

}
