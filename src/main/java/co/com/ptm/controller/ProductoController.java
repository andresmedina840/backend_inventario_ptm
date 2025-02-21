package co.com.ptm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.ptm.dto.ProductoDTO;
import co.com.ptm.entities.Producto;
import co.com.ptm.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/productos")
@RequiredArgsConstructor
public class ProductoController {

	private final ProductoService productoService;

	@PostMapping
	public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
		try {
			Producto producto = convertirDtoAEntidad(productoDTO);
			Producto productoGuardado = productoService.guardarProducto(producto);
			return ResponseEntity
					.ok(Map.of("code", 0, "message", "Producto creado exitosamente", "data", productoGuardado));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("code", 1, "message", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(Map.of("code", 99, "message", "Error interno del servidor"));
		}
	}

	@GetMapping
	public ResponseEntity<?> listarProductos() {
		try {
			List<ProductoDTO> productos = productoService.listarProductos().stream().map(this::convertirEntidadADto)
					.collect(Collectors.toList());

			return ResponseEntity
					.ok(Map.of("code", 0, "message", "Lista de productos obtenida correctamente", "data", productos));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(Map.of("code", 99, "message", "Error interno del servidor"));
		}
	}
	
	@GetMapping("/combinaciones/{valor}")
	public ResponseEntity<?> obtenerCombinaciones(@PathVariable double valor) {
		try {
			List<List<Object>> combinaciones = productoService.obtenerCombinacionesProductos(valor);
			return ResponseEntity.ok(Map.of("code", 0, "message", "Combinaciones obtenidas correctamente", "data", combinaciones));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
				.body(Map.of("code", 99, "message", "Error interno del servidor"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
		try {
			Producto productoActualizado = productoService.actualizarProducto(id, convertirDtoAEntidad(productoDTO));
			return ResponseEntity.ok(
					Map.of("code", 0, "message", "Producto actualizado correctamente", "data", productoActualizado));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("code", 1, "message", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(Map.of("code", 99, "message", "Error interno del servidor"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
		try {
			productoService.eliminarProducto(id);
			return ResponseEntity.ok(Map.of("code", 0, "message", "Producto eliminado correctamente"));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("code", 1, "message", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(Map.of("code", 99, "message", "Error interno del servidor"));
		}
	}

	private Producto convertirDtoAEntidad(ProductoDTO dto) {
		return Producto.builder().nombre(dto.getNombre()).codigoBarras(dto.getCodigoBarras())
				.descripcion(dto.getDescripcion()).precio(dto.getPrecio()).cantidadStock(dto.getCantidadStock())
				.build();
	}

	private ProductoDTO convertirEntidadADto(Producto producto) {
		return ProductoDTO.builder().id(producto.getId()).nombre(producto.getNombre())
				.codigoBarras(producto.getCodigoBarras()).descripcion(producto.getDescripcion())
				.precio(producto.getPrecio()).cantidadStock(producto.getCantidadStock()).build();
	}

}