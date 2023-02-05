# Ejercicio final de manejo de jdbc

Para la tarea se requiere crear un proyecto llamado ProyectoMantenedorUsuariosJDBC para administrar a los usuarios de la Base de Datos, con las operaciones: actualizar, eliminar, crear, listar y salir. <br>
<br>
La tabla usuarios la pueden crear a partir del siguiente esquema DDL: <br>

CREATE TABLE `usuarios` ( <br>
  `id` int NOT NULL AUTO_INCREMENT, <br>
  `username` varchar(12) DEFAULT NULL, <br>
  `password` varchar(60) DEFAULT NULL, <br>
  `email` varchar(45) DEFAULT NULL, <br>
  PRIMARY KEY (`id`) <br>
) ENGINE=InnoDB <br>
 <br>

Se pide crear e implementar las clases: modelo Usuario, de conexión a la BBDD ConexionBaseDatos y la clase UsuarioRepositorioImpl implementada a partir de la interface Repositorio (con generic). <br>

Para la clase de aplicación con el método main vamos a contar con un menú para poder seleccionar el tipo de operación, puede ser con la clase Scanner indicando una lista con las opciones, cada opción con un numero. <br>

Según el numero ingresado usar un if o switch para implementar cada una de las 4 operaciones y 5 para salir. <br>

Todos los datos se deben ingresar mediante el teclado como parámetros. <br>

Usar un do while para iterar hasta que la operación sea salir, cada vez que se selecciona una operación distinta a salir, al finalizar con dicha operación debe volver al menú para continuar con otra, al finalizar con la opción salir (5) se debe cerrar la conexión a la base de datos y finalizar el programa. <br>
