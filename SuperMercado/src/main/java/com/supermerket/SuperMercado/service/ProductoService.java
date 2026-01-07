/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.ProductoDTO;
import com.supermerket.SuperMercado.model.Producto;
import com.supermerket.SuperMercado.repository.ProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author César
 */

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public void saveProducto(ProductoDTO productoDTO) {
        productoRepo.save(dtoAProducto(productoDTO));
    }

    @Override
    public List<ProductoDTO> getProductos() {
        List<ProductoDTO> productosDTO = new ArrayList<>();
        List<Producto> productos = productoRepo.findAll();
        for(Producto producto : productos){
            productosDTO.add(productoADTO(producto));
        }
        return productosDTO;
    }

    @Override
    public ProductoDTO getProductoById(Long id) {
        return productoADTO(productoRepo.findById(id).orElse(null));
    }

    @Override
    public void editProducto(ProductoDTO productoDTO) {
        saveProducto(productoDTO);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepo.deleteById(id);
    }
    
    // Convertir de Entidad a DTO (Para enviar datos al cliente)
    public ProductoDTO productoADTO(Producto producto) {
        if (producto == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setCategoria(producto.getCategoria());
        // Mapeo de nombres distintos
        dto.setPrecio(producto.getPrecioActual());
        dto.setCantidad(producto.getStock());

        return dto;
    }

    // Convertir de DTO a Entidad (Para guardar o editar en DB)
    public Producto dtoAProducto(ProductoDTO dto) {
        if (dto == null) return null;

        Producto producto = new Producto();
        // Si el ID viene en el DTO, se lo asignamos (importante para editar)
        producto.setId(dto.getId()); 
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        // Mapeo inverso de nombres distintos
        producto.setPrecioActual(dto.getPrecio());
        producto.setStock(dto.getCantidad());

        return producto;
    }
}
