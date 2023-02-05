# Ejercicio final de manejo de jdbc

Para la tarea se requiere crear un proyecto llamado ProyectoMantenedorUsuariosJDBC para administrar a los usuarios de la Base de Datos, con las operaciones: actualizar, eliminar, crear, listar y salir.

La tabla usuarios la pueden crear a partir del siguiente esquema DDL:

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(12) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB


Se pide crear e implementar las clases: modelo Usuario, de conexión a la BBDD ConexionBaseDatos y la clase UsuarioRepositorioImpl implementada a partir de la interface Repositorio (con generic).

Para la clase de aplicación con el método main vamos a contar con un menú para poder seleccionar el tipo de operación, puede ser con la clase Scanner indicando una lista con las opciones, cada opción con un numero.

O bien! otra forma seria usando la clase JOptionPane para un menú con interfaces graficas de la siguiente forma:

int opcionIndice = 0;
...
 
Map<String, Integer> operaciones = new HashMap();
operaciones.put("Actualizar", 1);
operaciones.put("Eliminar", 2);
operaciones.put("Agregar", 3);
operaciones.put("Listar", 4);
operaciones.put("Salir", 5);
 
Object[] opArreglo = operaciones.keySet().toArray();
 
Object opcion = JOptionPane.showInputDialog(null,
                    "Seleccione un Operación", 
                     "Mantenedor de Usuarios", 
                     JOptionPane.INFORMATION_MESSAGE, null, opArreglo, opArreglo[0]);
 
if (opcion == null) {
    JOptionPane.showMessageDialog(null, "Debe seleccionar una operación");
} else {
    opcionIndice = operaciones.get(opcion.toString());
	
   // aca un if o switch para las distintas operaciones.
}


Según el numero ingresado usar un if o switch para implementar cada una de las 4 operaciones y 5 para salir.

Todos los datos se deben ingresar mediante el teclado como parámetros.

Usar un do while para iterar hasta que la operación sea salir, cada vez que se selecciona una operación distinta a salir, al finalizar con dicha operación debe volver al menú para continuar con otra, al finalizar con la opción salir (5) se debe cerrar la conexión a la base de datos y finalizar el programa.
