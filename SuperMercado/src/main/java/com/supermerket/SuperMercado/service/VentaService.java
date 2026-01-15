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
import jakarta.transaction.Transactional;
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
    @Transactional // Muy importante para asegurar que se guarde todo o nada
    public void saveVenta(VentaDTO ventaDTO) {
        if(ventaDTO == null) throw new RuntimeException("El DTO de venta no puede ser nulo");

        // 1. Buscar la sucursal correctamente usando idSucursal
        Sucursal sucursal = sucurRepo.findById(ventaDTO.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("No existe sucursal con id: " + ventaDTO.getIdSucursal()));

        if(ventaDTO.getDetalles() == null || ventaDTO.getDetalles().isEmpty()) 
            throw new RuntimeException("Debe incluir al menos un detalle de venta");

        // 2. Crear la instancia de Venta
        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());
        venta.setEstado(ventaDTO.getEstado());
        venta.setSucursal(sucursal);
        venta.setTotal(ventaDTO.getTotal());

        // 3. Crear la lista de detalles
        List<DetalleVenta> listaDetalles = new ArrayList<>();
        Double totalCalculado = 0.0;
        
        for(DetalleVentaDTO detalleDTO : ventaDTO.getDetalles()){
            // Buscar producto por nombre
            Producto producto = prodRepo.findByNombre(detalleDTO.getNombreProducto())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + detalleDTO.getNombreProducto()));

            // 4. USAR EL BUILDER CORRECTAMENTE E INSTANCIAR DENTRO DEL BUCLE
            DetalleVenta detalle = new DetalleVenta();
            detalle.setCantidad(detalleDTO.getCantProducto());
            detalle.setProducto(producto);
            detalle.setPrecioUnitario(detalleDTO.getPrecio());
            detalle.setVenta(venta);

            listaDetalles.add(detalle);
            totalCalculado += detalleDTO.getPrecio()*detalleDTO.getCantProducto();
        }

        // 5. Asignar la lista a la venta y guardar
        venta.setDetalles(listaDetalles);
        ventaRepo.save(venta); // Al tener CascadeType.ALL, esto guardará también los detalles
    }

    @Override
    public List<VentaDTO> getVentas() {
        return ventaRepo.findAll().stream().map(Mapper::toDTO).toList();
    }


    @Transactional
    public void editVenta(VentaDTO ventaDTO, Long id) {
        // 1. Verificar si existe la venta original
        Venta ventaExistente = ventaRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("No existe la venta con Id: " + id));

        // 2. Verificar si la sucursal nueva existe (por si cambió en el DTO)
        Sucursal sucursal = sucurRepo.findById(ventaDTO.getIdSucursal())
            .orElseThrow(() -> new NotFoundException("No existe sucursal con id: " + ventaDTO.getIdSucursal()));

        // 3. Actualizar datos básicos de la cabecera
        ventaExistente.setFecha(ventaDTO.getFecha());
        ventaExistente.setEstado(ventaDTO.getEstado());
        ventaExistente.setSucursal(sucursal);
        ventaExistente.setTotal(ventaDTO.getTotal());

        // 4. Actualizar Detalles (Limpiar los anteriores y agregar los nuevos)
        // Esto es necesario para evitar detalles huérfanos en la DB
        ventaExistente.getDetalles().clear(); 

        for(DetalleVentaDTO detalleDTO : ventaDTO.getDetalles()){
            Producto producto = prodRepo.findByNombre(detalleDTO.getNombreProducto())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + detalleDTO.getNombreProducto()));

            DetalleVenta nuevoDetalle = new DetalleVenta();
            nuevoDetalle.setCantidad(detalleDTO.getCantProducto());
            nuevoDetalle.setPrecioUnitario(detalleDTO.getPrecio());
            nuevoDetalle.setProducto(producto);
            nuevoDetalle.setVenta(ventaExistente); // Vincular a la venta que estamos editando

            ventaExistente.getDetalles().add(nuevoDetalle);
        }

        // 5. Guardar la venta existente (JPA hará el UPDATE)
        ventaRepo.save(ventaExistente);
    }


    @Override
    public void deleteVenta(Long id) {
        ventaRepo.deleteById(id);
    }
}
