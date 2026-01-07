/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.supermerket.SuperMercado.repository;

import com.supermerket.SuperMercado.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author César
 */

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
    
}
