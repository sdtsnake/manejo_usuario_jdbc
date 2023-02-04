import work.oscarramos.manejousario.conexion.ConexionBaseDatos;
import work.oscarramos.manejousario.modelo.Usuario;
import work.oscarramos.manejousario.servicio.UsuarioRespositorioImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static UsuarioRespositorioImpl usuarioRespositorio = new UsuarioRespositorioImpl();

    public static void main(String[] args) throws SQLException {

        try (Connection conn = ConexionBaseDatos.getConnection()) {
            System.out.println("========================================");
            System.out.println("            Manejo de usuarios          ");
            System.out.println("========================================");
            System.out.println("1. Crear usario");
            System.out.println("2. Actualiza usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Borrar usurio");
            System.out.println("5. Salir");
            System.out.print("Seleccione : ");
            Scanner sc = new Scanner(System.in);
            char opciones = sc.next().charAt(0);

            switch (opciones) {
                case '1':
                    crearUsuario();
                    limpiarPantalla();
                    main(args);
                    break;
                case '2':
                    actualizaUsuario();
                    limpiarPantalla();
                    main(args);
                    break;
                case '3':
                    listarUsuarios();
                    limpiarPantalla();
                    main(args);
                    break;
                case '4':
                    boirrarUsuario();
                    limpiarPantalla();
                    main(args);
                    break;
                case '5':
                    limpiarPantalla();
                    System.out.println("Ejecucion terminada....");
                    System.exit(0);
                    break;
            }
        }
    }

    private static void boirrarUsuario() {
        Scanner sc = new Scanner(System.in);
        limpiarPantalla();
        System.out.println("========================================");
        System.out.println("            Borrar usuario              ");
        System.out.println("========================================");
        System.out.print("Correo electronico del usuario : ");
        String correo = sc.nextLine();
        System.out.println();
        usuarioRespositorio.delete(correo);
    }

    private static void listarUsuarios() {
        limpiarPantalla();
        System.out.println("========================================");
        System.out.println("           Listado de usuarios          ");
        System.out.println("========================================");
        List<Usuario> usuarioList = usuarioRespositorio.findAll();
        usuarioList.stream().forEach(System.out::println);

    }

    private static void crearUsuario() {
        Scanner sc = new Scanner(System.in);
        limpiarPantalla();
        System.out.println("========================================");
        System.out.println("            Crear de usuario            ");
        System.out.println("========================================");
        System.out.print("Nombre de usuario              : ");
        String nombre = sc.nextLine();
        System.out.print("Contraseña de usuario          : ");
        String password = sc.nextLine();
        System.out.print("Correo electronico del usuario : ");
        String correo = sc.nextLine();
        System.out.println();
        Usuario usuario = new Usuario(nombre, password, correo);
        usuarioRespositorio.save(usuario);
    }

    private static void actualizaUsuario() throws SQLException {
        Scanner sc = new Scanner(System.in);
        limpiarPantalla();
        System.out.println("========================================");
        System.out.println("         Correo del usuario            ");
        System.out.println("========================================");
        System.out.print("Correo electronico del usuario : ");
        String correo = sc.nextLine();
        Usuario usuario = usuarioRespositorio.findByEmail(correo);
        limpiarPantalla();
        if (usuario.getId() == 0 || usuario.getId() == null) {
            System.out.printf("No existe correo electronico");
            String acept = sc.nextLine();
        } else {
            System.out.println("========================================");
            System.out.println("     Datos actrualiza del usuario       ");
            System.out.println("========================================");
            System.out.print("Nombre de usuario              : ");
            String nombre = sc.nextLine();
            System.out.print("Contraseña de usuario          : ");
            String password = sc.nextLine();
            System.out.print("Correo electronico del usuario : ");
            String correoNuevo = sc.nextLine();
            System.out.println();
            Integer id = usuario.getId();
            Usuario usuarioUpdate = new Usuario(id, nombre, password, correoNuevo);
            usuarioRespositorio.update(usuarioUpdate);
        }
    }

    public static <ArrayList> void limpiarPantalla() {
        try {
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}