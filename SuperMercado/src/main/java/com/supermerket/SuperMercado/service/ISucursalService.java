/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.SucursalDTO;
import java.util.List;

/**
 *
 * @author César
 */
public interface ISucursalService {
    // Metodos CRUD
    public void saveSucursal(SucursalDTO sucursalDTO);
    public List<SucursalDTO> getSucursales();
    public void editSucursal(SucursalDTO sucursalDTO, Long id);
    public void deleteSucursal(Long id);
}
