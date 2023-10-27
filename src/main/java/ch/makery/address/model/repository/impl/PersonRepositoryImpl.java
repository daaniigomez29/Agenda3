package ch.makery.address.model.repository.impl;

import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.util.DateUtil;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRepositoryImpl implements PersonRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonVO> personas;
    private PersonVO persona;

    public PersonRepositoryImpl() {
    }

    public ArrayList<PersonVO> obtenerListaPersonas() throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM Personas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String street = rs.getString("street");
                Integer postalCode = rs.getInt("postalCode");
                String city = rs.getString("city");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                Integer codigo = rs.getInt("idPerson");
                this.persona = new PersonVO(codigo,firstName, lastName, street, postalCode, city, birthday);
                this.persona.setCod(codigo);
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);

            return this.personas;
        } catch (SQLException var6) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }
    public void addPerson(PersonVO m) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO Personas (firstName, lastName, street, postalCode, city, birthday) VALUES ('" + m.getFirstName() + "','" + m.getLastName() + "','" + m.getStreet() + "','" + m.getPostalCode() + "','" + m.getCity() + "','" + m.getBirthday() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }
    public void deletePerson(Integer idPersona) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Personas WHERE idPerson = %d", idPersona);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido relaizar la eliminación");
        }
    }
    public void editPerson(PersonVO personVO, Integer cod) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Personas SET firstName = '%s', lastName = '%s', street = '%s', postalCode = '%s', city = '%s', birthday = '%s' WHERE idPerson = %d", personVO.getFirstName(), personVO.getLastName(), personVO.getStreet(), personVO.getPostalCode(), personVO.getCity(), personVO.getBirthday(), cod);
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionPerson("No se ha podido realizar la edición");
        }
    }
    public int lastId() throws ExcepcionPerson {
        int lastPersonId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            ResultSet resultSet =comando.executeQuery("SELECT AUTO_INCREMENT "+
                    "FROM information_schema.TABLES "+
                    "WHERE TABLE_SCHEMA = 'Prueba' "+
                    "AND TABLE_NAME = 'Personas'");

            {
                if(resultSet.next()){
                    lastPersonId=resultSet.getInt("AUTO_INCREMENT")-1;
                }
                System.out.println(lastPersonId);
                return lastPersonId;
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new ExcepcionPerson("No se ha podido realizar la búsqueda del ID");
        }
    }
}
