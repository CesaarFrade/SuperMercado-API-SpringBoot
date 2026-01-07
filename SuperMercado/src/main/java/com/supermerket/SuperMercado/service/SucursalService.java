/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.SucursalDTO;
import com.supermerket.SuperMercado.model.Sucursal;
import com.supermerket.SuperMercado.repository.SucursalRepository;
import java.util.ArrayList;
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
        sucurRepo.save(dtoASucursal(sucursalDTO));
    }

    @Override
    public List<SucursalDTO> getSucursal() {
        List<Sucursal> sucursales = sucurRepo.findAll();
        List<SucursalDTO> sucursalesDTO = new ArrayList<>();
        for(Sucursal sucursal : sucursales){
            sucursalesDTO.add(sucursalADTO(sucursal));
        }
        return sucursalesDTO;
    }

    @Override
    public SucursalDTO getSucursalById(Long id) {
        Sucursal sucursal =  sucurRepo.findById(id).orElse(null);
        return sucursalADTO(sucursal);
    }

    @Override
    public void editSucursal(SucursalDTO sucursalDTO) {
        saveSucursal(sucursalDTO);
    }

    @Override
    public void deleteSucursal(Long id) {
        sucurRepo.deleteById(id);
    }
    
    public SucursalDTO sucursalADTO(Sucursal sucursal){
        SucursalDTO sucursalDTO = new SucursalDTO(sucursal.getId(), sucursal.getNombre(), sucursal.getDireccion());
        return sucursalDTO;
    }
    
    public Sucursal dtoASucursal(SucursalDTO sucursalDTO){
        Sucursal sucursal = new Sucursal();
        sucursal.setId(sucursalDTO.getId());
        sucursal.setDireccion(sucursalDTO.getDireccion());
        sucursal.setNombre(sucursalDTO.getNombre());
        return sucursal;
    }
}
