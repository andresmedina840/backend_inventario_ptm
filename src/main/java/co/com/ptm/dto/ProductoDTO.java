package co.com.ptm.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {

	private Long id;

	@NotBlank
	private String nombre;

	@NotBlank
	private String codigoBarras;

	@NotBlank
	private String descripcion;

	@NotNull
	@PositiveOrZero
	private Integer precio;

	@NotNull
	@PositiveOrZero
	private Integer cantidadStock;
}