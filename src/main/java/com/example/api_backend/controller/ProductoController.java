package com.example.api_backend.controller;

import com.example.api_backend.exception.ResourceNotFoundException;
import com.example.api_backend.entity.Producto;
import com.example.api_backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // GET: Listar todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
    
 // GET Paginado: /api/productos/paged?page=0&size=10&sortBy=precio&direction=asc
    @GetMapping("/paged")
    public ResponseEntity<Page<Producto>> listarProductosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        // 1. Configuramos el sentido del ordenamiento (ASC o DESC)
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") 
                ? Sort.Direction.DESC 
                : Sort.Direction.ASC;

        // 2. Construimos el objeto Pageable de Spring Data
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        // 3. Ejecutamos la consulta paginada
        Page<Producto> paginaProductos = productoRepository.findAll(pageable);

        return ResponseEntity.ok(paginaProductos);
    }

    // GET por ID
    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PRODUCT_NOT_FOUND"));
    }
    
    // POST: Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // PUT: Editar
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PRODUCT_NOT_FOUND"));

        productoExistente.setNombre(productoDetalles.getNombre());
        productoExistente.setPrecio(productoDetalles.getPrecio());
        productoExistente.setStock(productoDetalles.getStock());
        productoExistente.setContentType(productoDetalles.getContentType());
        productoExistente.setImagen(productoDetalles.getImagen()); // <--- Actualizamos la imagen

        return productoRepository.save(productoExistente);
    }

    // DELETE: Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("PRODUCT_NOT_FOUND");
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}