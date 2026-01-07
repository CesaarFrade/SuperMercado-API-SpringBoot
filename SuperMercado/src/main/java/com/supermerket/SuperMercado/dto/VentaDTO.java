/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author César
 */

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    // Datos de la venta
   private Long id;
   private LocalDate fecha;
   private String estado;
   // Datos de la sucursal
   private Long idSucursal;
   // Lista de detalles
   private List<DetalleVentaDTO> detalles;
   // Detalles de la venta
   private Double total;
}
