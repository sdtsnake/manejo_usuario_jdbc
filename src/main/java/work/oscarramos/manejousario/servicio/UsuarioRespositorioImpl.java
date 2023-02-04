package work.oscarramos.manejousario.servicio;

import work.oscarramos.manejousario.conexion.ConexionBaseDatos;
import work.oscarramos.manejousario.modelo.Usuario;
import work.oscarramos.manejousario.respositorio.Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRespositorioImpl implements Repositorio<Usuario> {
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {
            while (rs.next()) {
                Usuario usuario = getUsuario(rs);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario findByEmail(String email) {
        Usuario usuario = null;
        Integer id = buscaId(email);
        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM usuarios AS u WHERE u.id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuarios(username, password, email) VALUES(?,?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, usuario.getUserName());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String email) {
        Integer id = buscaId(email);
        if (id > 0) {
            try (PreparedStatement stmt = getConnection()
                    .prepareStatement("DELETE FROM usuarios WHERE id=?")) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
            String sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE id=?";
            try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
                stmt.setString(1, usuario.getUserName());
                stmt.setString(2, usuario.getPassword());
                stmt.setString(3, usuario.getEmail());
                stmt.setLong(4, usuario.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    private Integer buscaId(String email) {
        Usuario usuario = new Usuario(0);
        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT u.id FROM usuarios AS u WHERE u.email =?")) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario.setId((rs.getInt("id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario.getId();
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId((rs.getInt("id")));
        usuario.setUserName(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }
}
