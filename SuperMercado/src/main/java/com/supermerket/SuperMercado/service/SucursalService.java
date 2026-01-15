/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.SucursalDTO;
import com.supermerket.SuperMercado.mapper.Mapper;
import com.supermerket.SuperMercado.model.Sucursal;
import com.supermerket.SuperMercado.repository.SucursalRepository;
import exception.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author César
 */

@Service
public class SucursalService implements ISucursalService{
    @Autowired
    private SucursalRepository sucurRepo;

    @Override
    public void saveSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = Sucursal.builder()
                .id(sucursalDTO.getId())
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();
        sucurRepo.save(sucursal);
    }

    @Override
    public List<SucursalDTO> getSucursales() {
        return sucurRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public void editSucursal(SucursalDTO sucursalDTO, Long id) {
        // 1. Buscamos la entidad original (si no existe, lanzamos excepción de una vez)
        Sucursal sucursal = sucurRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("No existe la sucursal con Id: " + id));

        // 2. Actualizamos los campos manualmente (o con un mapper)
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setDireccion(sucursalDTO.getDireccion());

        // 3. Guardamos la entidad actualizada
        sucurRepo.save(sucursal);
    }

    @Override
    public void deleteSucursal(Long id) {
        sucurRepo.deleteById(id);
    }
}
