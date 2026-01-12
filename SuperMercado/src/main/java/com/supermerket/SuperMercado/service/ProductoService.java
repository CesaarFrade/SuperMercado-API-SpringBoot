/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.ProductoDTO;
import com.supermerket.SuperMercado.mapper.Mapper;
import com.supermerket.SuperMercado.model.Producto;
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
                .stock(productoDTO.getCantidad())
                .build();
        productoRepo.save(prod);
    }

    @Override
    public List<ProductoDTO> getProductos() {
        return productoRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public void editProducto(ProductoDTO productoDTO) {
        // Vamos a buscar si existe ese producto
        Producto prod = productoRepo.findById(productoDTO.getId()).orElseThrow(() -> new NotFoundException("Prodcuto no encontrado"));
        if(prod != null){
            saveProducto(productoDTO);
        } else{
            System.out.println("No existe ningun producto con ese id");
        }
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepo.deleteById(id);
    }
}
