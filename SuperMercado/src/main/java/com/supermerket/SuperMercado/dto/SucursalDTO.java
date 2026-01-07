/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermerket.SuperMercado.dto;

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
public class SucursalDTO {
    private Long id;
    private String nombre;
    private String direccion;
}
