/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.ProductoDTO;
import com.supermerket.SuperMercado.mapper.Mapper;
import com.supermerket.SuperMercado.model.Producto;
import com.supermerket.SuperMercado.model.Sucursal;
import com.supermerket.SuperMercado.repository.ProductoRepository;
import exception.NotFoundException;
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
        Producto prod = Producto.builder()
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .precioActual(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();
        productoRepo.save(prod);
    }

    @Override
    public List<ProductoDTO> getProductos() {
        return productoRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public void editProducto(ProductoDTO productoDTO, Long id) {
        // 1. Buscamos la entidad original (si no existe, lanzamos excepción de una vez)
        Producto producto = productoRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("No existe producto con Id: " + id));

        // 2. Actualizamos los campos manualmente (o con un mapper)
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecioActual(productoDTO.getPrecio());
        producto.setCategoria(productoDTO.getCategoria());
        productoDTO.setCantidad(productoDTO.getCantidad());

        // 3. Guardamos la entidad actualizada
        productoRepo.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        if(productoRepo.existsById(id)){
            productoRepo.deleteById(id);
        } else{
            throw new NotFoundException("Producto no encontrado para eliminar");
        }  
    }
        
}
