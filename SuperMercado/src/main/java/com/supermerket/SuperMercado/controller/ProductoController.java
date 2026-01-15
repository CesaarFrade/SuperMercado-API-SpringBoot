/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.controller;

import com.supermerket.SuperMercado.dto.ProductoDTO;
import com.supermerket.SuperMercado.dto.VentaDTO;
import com.supermerket.SuperMercado.service.ProductoService;
import com.supermerket.SuperMercado.service.VentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author César
 */

@RestController
public class ProductoController {
    @Autowired
    private ProductoService prodServ;
    @GetMapping("/producto/get")
    public ResponseEntity<List<ProductoDTO>> getProductos(){
        return ResponseEntity.ok(prodServ.getProductos());
    }
    
    @PostMapping("/producto/save")
    public void saveProducto(@RequestParam ProductoDTO producto){
        prodServ.saveProducto(producto);
    }
    
    @PutMapping("/producto/edit")
    public void editProducto(@RequestParam ProductoDTO producto){
        prodServ.editProducto(producto);
    }
    
    @DeleteMapping("/producto/delete/{id}")
    public void deleteProducto(@PathVariable Long id){
        prodServ.deleteProducto(id);
    }
}
