/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.controller;

import com.supermerket.SuperMercado.dto.SucursalDTO;
import com.supermerket.SuperMercado.service.SucursalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author César
 */

@RestController
public class SucursalController {
    @Autowired
    private SucursalService sucServ;
    @GetMapping("/sucursal/get")
    public ResponseEntity<List<SucursalDTO>> getSucursales(){
        return ResponseEntity.ok(sucServ.getSucursales());
    }
    
    @PostMapping("/sucursal/save")
    public void saveSucursal(@RequestBody SucursalDTO sucursal){
        sucServ.saveSucursal(sucursal);
    }
    
    @PutMapping("/sucursal/edit/{id}")
    public void editSucursal(@RequestBody SucursalDTO sucursal, @PathVariable Long id){
        sucServ.editSucursal(sucursal, id);
    }
    
    @DeleteMapping("/sucursal/delete/{id}")
    public void deleteSucursal(@PathVariable Long id){
        sucServ.deleteSucursal(id);
    }
}
