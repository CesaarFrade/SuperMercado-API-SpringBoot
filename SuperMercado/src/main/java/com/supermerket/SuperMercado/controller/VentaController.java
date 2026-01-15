/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.controller;

import com.supermerket.SuperMercado.dto.VentaDTO;
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
public class VentaController {
    @Autowired
    private VentaService ventServ;
    @GetMapping("/venta/get")
    public ResponseEntity<List<VentaDTO>> getVentas(){
        return ResponseEntity.ok(ventServ.getVentas());
    }
    
    @PostMapping("/venta/save")
    public void saveVenta(@RequestParam VentaDTO venta){
        ventServ.saveVenta(venta);
    }
    
    @PutMapping("/venta/edit")
    public void editVenta(@RequestParam VentaDTO venta){
        ventServ.editVenta(venta);
    }
    
    @DeleteMapping("/venta/delete/{id}")
    public void deleteVenta(@PathVariable Long id){
        ventServ.deleteVenta(id);
    }
}
