package co.com.ptm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.com.ptm.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
