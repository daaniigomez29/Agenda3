package ch.makery.address.model.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    /**
     * Contructor predeterminado
     */
    public ConexionJDBC() {
    }

    /**
     * Conexión a la base de datos.
     * @return Conexión realizada.
     * @throws SQLException Lanza excepción si ocurre un fallo al conectar a la base de datos.
     * @author Daniel Gómez
     */
    public Connection conectarBD() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Prueba?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return conn;
        } catch (SQLException var2) {
            SQLException ex = var2;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }

            throw new SQLException();
        } catch (Exception var3) {
            throw new SQLException();
        }
    }

    /**
     * Desconecta la conexión a la base de datos.
     * @param conn conexión
     * @author Daniel Gómez
     */
    public void desconectarBD(Connection conn) {
        try {
            conn.close();
        } catch (SQLException var3) {
            SQLException ex = var3;
            System.out.println("\n--- SQLException capturada ---\n");

            while (ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }

    }
}
