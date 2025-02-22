INSERT INTO producto (nombre, codigo_barras, descripcion, cantidad_stock, precio) VALUES
('Laptop HP', '100000000001', 'Laptop HP 15.6" con procesador Intel Core i5', 10, 3200000),
('Mouse Logitech', '100000000002', 'Mouse inalámbrico Logitech con sensor óptico', 50, 80000),
('Teclado Mecánico', '100000000003', 'Teclado mecánico con retroiluminación RGB', 30, 250000),
('Monitor Samsung', '100000000004', 'Monitor LED Samsung de 24 pulgadas Full HD', 15, 750000),
('Disco Duro Externo', '100000000005', 'Disco duro externo de 1TB USB 3.0', 25, 350000),
('Silla Ergonómica', '100000000006', 'Silla de oficina ergonómica con soporte lumbar', 5, 1200000),
('Memoria RAM 16GB', '100000000007', 'Módulo de memoria RAM DDR4 de 16GB 3200MHz', 20, 450000),
('Impresora Epson', '100000000008', 'Impresora multifuncional Epson con WiFi', 10, 680000)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre), descripcion = VALUES(descripcion), cantidad_stock = VALUES(cantidad_stock), precio = VALUES(precio);
