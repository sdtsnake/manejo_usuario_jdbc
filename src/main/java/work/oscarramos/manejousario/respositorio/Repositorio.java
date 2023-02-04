package work.oscarramos.manejousario.respositorio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    List<T> findAll();
    T findByEmail(String email);
    void save(T t);
    void delete(String email);
    void update(T t) throws SQLException;
}
