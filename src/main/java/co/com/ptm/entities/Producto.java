package co.com.ptm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255)
    private String nombre;

    @NotBlank(message = "El código de barras es obligatorio")
    @Size(max = 255)
    private String codigoBarras;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a cero")
    private Integer precio;

    @NotNull(message = "El stock es obligatorio")
    @Builder.Default
    private Integer cantidadStock = 0;
}