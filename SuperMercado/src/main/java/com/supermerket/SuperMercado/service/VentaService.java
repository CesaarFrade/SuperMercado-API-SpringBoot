/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.DetalleVentaDTO;
import com.supermerket.SuperMercado.dto.VentaDTO;
import com.supermerket.SuperMercado.mapper.Mapper;
import com.supermerket.SuperMercado.model.DetalleVenta;
import com.supermerket.SuperMercado.model.Producto;
import com.supermerket.SuperMercado.model.Sucursal;
import com.supermerket.SuperMercado.model.Venta;
import com.supermerket.SuperMercado.repository.ProductoRepository;
import com.supermerket.SuperMercado.repository.SucursalRepository;
import com.supermerket.SuperMercado.repository.VentaRepository;
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
public class VentaService implements IVentaService{
    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private SucursalRepository sucurRepo;
    @Autowired
    private ProductoRepository prodRepo;

    @Override
    public void saveVenta(VentaDTO ventaDTO) {
        if(ventaDTO == null) throw new RuntimeException("Venta dto es null");
        if(ventaDTO.getDetalles() == null || ventaDTO.getDetalles().isEmpty()) 
            throw new RuntimeException("Debe incluir al menos un detalle de venta");
        if(sucurRepo.existsById(ventaDTO.getIdSucursal())){
            Sucursal sucursal = sucurRepo.findById(ventaDTO.getId()).orElse(null);
            List<DetalleVentaDTO> detallesDTO = ventaDTO.getDetalles();
            ArrayList<DetalleVenta> detalles = new ArrayList<>();
            DetalleVenta detalle = new DetalleVenta();
            Producto producto;
            Venta venta = new Venta();
            venta.setFecha(ventaDTO.getFecha());
            venta.setEstado(ventaDTO.getEstado());
            venta.setSucursal(sucursal);
            venta.setTotal(ventaDTO.getTotal());
            for(DetalleVentaDTO detalleDTO : detallesDTO){
                producto = prodRepo.findByNombre(detalleDTO.getNombreProducto()).orElse(null);
                if(producto == null){
                    throw new NotFoundException("Producto no encontrado con id: " + 
                            producto.getId());
                } else{
                    detalle.builder()
                   .id(detalleDTO.getId())
                   .cantidad(detalleDTO.getCantProducto())
                   .precioUnitario(detalleDTO.getPrecio())
                   .producto(producto)
                   .venta(venta)
                   .build();
                }
                detalles.add(detalle);
            }
            venta.setDetalles(detalles);
            ventaRepo.save(venta);
        } else{
            throw new NotFoundException("No existe sucursal"
                    + " o no existe ninguna sucursal con ese id");
        }
        
    }

    @Override
    public List<VentaDTO> getVentas() {
        return ventaRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public void editVenta(VentaDTO ventaDTO) {
        if(ventaRepo.existsById(ventaDTO.getId())){
            saveVenta(ventaDTO);
        } else{
            throw new NotFoundException("No existe ninguna venta con ese Id");
        }
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepo.deleteById(id);
    }
}
