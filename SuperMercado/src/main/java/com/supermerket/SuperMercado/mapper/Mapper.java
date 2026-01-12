/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.mapper;

import com.supermerket.SuperMercado.dto.DetalleVentaDTO;
import com.supermerket.SuperMercado.dto.ProductoDTO;
import com.supermerket.SuperMercado.dto.SucursalDTO;
import com.supermerket.SuperMercado.dto.VentaDTO;
import com.supermerket.SuperMercado.model.Producto;
import com.supermerket.SuperMercado.model.Sucursal;
import com.supermerket.SuperMercado.model.Venta;
import java.util.stream.Collectors;

/**
 *
 * @author César
 */
public class Mapper {
    // Mapeo de Producto a ProductoDTO
    // Convertir de Entidad a DTO (Para enviar datos al cliente)
    // Método estático
    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;
        
        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecioActual())
                .build();
    }
    
    // Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta v){
        if(v == null) return null;
        
        var detalle = v.getDetalles().stream().map(det ->
            DetalleVentaDTO.builder()
                .id(det.getProducto().getId())
                .nombreProducto(det.getProducto().getNombre())
                .cantProducto(det.getCantidad())
                .precio(det.getPrecioUnitario())
                .subtotal(det.getPrecioUnitario() * det.getCantidad())
                .build()
        
        
        
        ).collect(Collectors.toList());
        
        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);
        
        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .idSucursal(v.getSucursal().getId())
                .estado(v.getEstado())
                .detalles(detalle)
                .total(v.getTotal())
                .build();
    } 
    
    // Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal s){
        if(s == null) return null;
        
        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    } 
}
