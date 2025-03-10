CREATE TABLE IF NOT EXISTS producto (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  codigo_barras VARCHAR(255) NOT NULL UNIQUE,
  descripcion VARCHAR(255) NOT NULL,
  cantidad_stock INT NOT NULL DEFAULT 0,
  precio INT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
