/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.DetalleVentaDTO;
import com.supermerket.SuperMercado.dto.VentaDTO;
import com.supermerket.SuperMercado.model.DetalleVenta;
import com.supermerket.SuperMercado.model.Venta;
import com.supermerket.SuperMercado.repository.SucursalRepository;
import com.supermerket.SuperMercado.repository.VentaRepository;
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
    SucursalRepository sucurRepo;

    @Override
    public void saveVenta(VentaDTO ventaDTO) {
        ventaRepo.save(dtoAVenta(ventaDTO));
    }

    @Override
    public List<VentaDTO> getVentas() {
        List<VentaDTO> ventasDTO = new ArrayList<>();
        List<Venta> ventas = ventaRepo.findAll();
        for(Venta venta : ventas){
            ventasDTO.add(ventaADTO(venta));
        }
        return ventasDTO;
    }

    @Override
    public VentaDTO getVentaById(Long id) {
        return  ventaADTO(ventaRepo.findById(id).orElse(null));
    }

    @Override
    public void editVenta(VentaDTO ventaDTO) {
        saveVenta(ventaDTO);
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepo.deleteById(id);
    }
    
    public VentaDTO ventaADTO(Venta venta){
        List<DetalleVentaDTO> detallesDTO = new ArrayList<>();
        List<DetalleVenta> detalles = venta.getDetalles();
        for(DetalleVenta detalle : detalles){
            detallesDTO.add(detalleVentaADTO(detalle));
        }
        VentaDTO ventaDTO = new VentaDTO(venta.getId(), venta.getFecha(), venta.getEstado(),venta.getSucursal().getId(), detallesDTO, venta.getTotal());
        return ventaDTO;
    }
    
    public Venta dtoAVenta(VentaDTO ventaDTO) {
        // Buscamos si ya existe para editar, o creamos una nueva
        Venta venta = ventaRepo.findById(ventaDTO.getId()).orElse(new Venta());
        
        venta.setId(ventaDTO.getId());
        venta.setFecha(ventaDTO.getFecha());
        venta.setEstado(ventaDTO.getEstado());
        venta.setTotal(ventaDTO.getTotal());
        
        // Es importante recuperar el objeto Sucursal real de la DB
        if (ventaDTO.getIdSucursal() != null) {
            sucurRepo.findById(ventaDTO.getIdSucursal()).ifPresent(venta::setSucursal);
        }
        
        return venta;
    }
    
    public DetalleVentaDTO detalleVentaADTO(DetalleVenta detalleVenta){
        DetalleVentaDTO detalleVentaDTO = new DetalleVentaDTO();
        detalleVentaDTO.setId(detalleVenta.getId());
        detalleVentaDTO.setNombreProducto(detalleVenta.getProducto().getNombre());
        detalleVentaDTO.setCantProducto(detalleVenta.getCantidad());
        detalleVentaDTO.setPrecio(detalleVenta.getPrecioUnitario());
        detalleVentaDTO.setSubtotal(detalleVenta.getPrecioUnitario() * detalleVenta.getCantidad());
        return detalleVentaDTO;
    }
}
