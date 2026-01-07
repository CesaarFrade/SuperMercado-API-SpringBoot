/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.supermerket.SuperMercado.service;

import com.supermerket.SuperMercado.dto.ProductoDTO;
import com.supermerket.SuperMercado.model.Producto;
import java.util.List;

/**
 *
 * @author César
 */
public interface IProductoService {
    // Metodos CRUD
    public void saveProducto(ProductoDTO productoDTO);
    public List<ProductoDTO> getProductos();
    public ProductoDTO getProductoById(Long id);
    public void editProducto(ProductoDTO productoDTO);
    public void deleteProducto(Long id);
}
