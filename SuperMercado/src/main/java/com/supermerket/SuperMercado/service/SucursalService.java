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
    public void editSucursal(SucursalDTO sucursalDTO) {
        if(sucurRepo.existsById(sucursalDTO.getId())){
            saveSucursal(sucursalDTO);
        } else{
            throw new NotFoundException("No existe ninguna sucursal con ese Id");
        }
    }

    @Override
    public void deleteSucursal(Long id) {
        sucurRepo.deleteById(id);
    }
}
