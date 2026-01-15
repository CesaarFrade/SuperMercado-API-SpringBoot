/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.supermerket.SuperMercado.repository;

import com.supermerket.SuperMercado.model.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author César
 */

@Repository

public interface ProductoRepository extends JpaRepository <Producto, Long>{
    // Buscar producto por su nombre
    Optional<Producto> findByNombre(String nombre); // Jpa hace automaticamente la consulta
}
