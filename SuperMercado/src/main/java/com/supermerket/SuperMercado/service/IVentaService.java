/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.VentaDTO;
import java.util.List;

/**
 *
 * @author César
 */
public interface IVentaService {
    // Metodos CRUD
    public void saveVenta(VentaDTO ventaDTO);
    public List<VentaDTO> getVentas();
    public void editVenta(VentaDTO ventaDTO, Long id);
    public void deleteVenta(Long id);
}
