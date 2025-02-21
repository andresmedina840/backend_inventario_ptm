package co.com.ptm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import co.com.ptm.entities.Producto;
import co.com.ptm.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

	private final ProductoRepository productoRepository;

	public Producto guardarProducto(Producto producto) {
		validarProducto(producto);
		return productoRepository.save(producto);
	}

	public List<Producto> listarProductos() {
		return productoRepository.findAll();
	}

	public Producto actualizarProducto(Long id, Producto producto) {
		validarProducto(producto);

		Optional<Producto> productoExistente = productoRepository.findById(id);
		if (productoExistente.isEmpty()) {
			throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
		}

		Producto productoActualizado = productoExistente.get();
		productoActualizado.setNombre(producto.getNombre());
		productoActualizado.setCodigoBarras(producto.getCodigoBarras());
		productoActualizado.setDescripcion(producto.getDescripcion());
		productoActualizado.setPrecio(producto.getPrecio());
		productoActualizado.setCantidadStock(producto.getCantidadStock());

		return productoRepository.save(productoActualizado);
	}

	public void eliminarProducto(Long id) {
		if (!productoRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
		}
		productoRepository.deleteById(id);
	}

	public List<List<Object>> obtenerCombinacionesProductos(double valorMaximo) {
		List<Producto> productos = productoRepository.findAll();
		List<List<Object>> combinaciones = new ArrayList<>();

		for (int i = 0; i < productos.size(); i++) {
			for (int j = i + 1; j < productos.size(); j++) {
				double suma = productos.get(i).getPrecio() + productos.get(j).getPrecio();
				if (suma <= valorMaximo) {
					combinaciones
							.add(List.of(List.of(productos.get(i).getNombre(), productos.get(j).getNombre()), suma));
				}
				for (int k = j + 1; k < productos.size(); k++) {
					double sumaTres = suma + productos.get(k).getPrecio();
					if (sumaTres <= valorMaximo) {
						combinaciones.add(List.of(List.of(productos.get(i).getNombre(), productos.get(j).getNombre(),
								productos.get(k).getNombre()), sumaTres));
					}
				}
			}
		}

		// Ordenar descendentemente por suma y limitar a 5 resultados
		return combinaciones.stream().sorted((a, b) -> Double.compare((double) b.get(1), (double) a.get(1))).limit(5)
				.collect(Collectors.toList());
	}

	private void validarProducto(Producto producto) {
		if (producto.getPrecio() < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo");
		}
		if (producto.getCantidadStock() < 0) {
			throw new IllegalArgumentException("El stock no puede ser negativo");
		}
	}
}
