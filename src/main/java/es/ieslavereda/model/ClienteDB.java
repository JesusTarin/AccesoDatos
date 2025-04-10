package es.ieslavereda.model;

import es.ieslavereda.connection.MyDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDB implements AlmacenDB{
    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMySQLDataSource();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from Cliente")) {
            while (resultSet.next()) {
                clientes.add(new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("dni"),
                        resultSet.getDate("fecha_nacimiento").toLocalDate()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void updateCliente(Cliente cliente) {
        DataSource dataSource = MyDataSource.getMySQLDataSource();
        String query = "update Cliente set nombre = ?, apellidos = ?, fecha_nacimiento = ? where dni = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pt = connection.prepareStatement(query)) {

            pt.setString(1, cliente.getNombre());
            pt.setString(2, cliente.getApellidos());
            pt.setDate(3, Date.valueOf(cliente.getFecha_nacimiento()));
            pt.setString(4, cliente.getDni());

            pt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCliente(Cliente cliente) {
        DataSource dataSource = MyDataSource.getMySQLDataSource();
        String query = "insert into Cliente(nombre,apellidos,dni,fecha_nacimiento) values (?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement pt = connection.prepareStatement(query)) {

            pt.setString(1, cliente.getNombre());
            pt.setString(2, cliente.getApellidos());
            pt.setString(3, cliente.getDni());
            pt.setDate(4, Date.valueOf(cliente.getFecha_nacimiento()));

            pt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCliente(Cliente cliente) {
        DataSource dataSource = MyDataSource.getMySQLDataSource();
        String query = "delete from cliente where dni = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement pt = connection.prepareStatement(query)) {

            pt.setString(1, cliente.getDni());

            pt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Cliente getCliente(String dni) {
        DataSource dataSource = MyDataSource.getMySQLDataSource();
        Cliente cliente = null;
        String query = "select * from Cliente where dni = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement pt = connection.prepareStatement(query)) {

            pt.setString(1,dni);
            ResultSet resultSet = pt.executeQuery();
            resultSet.next();
            cliente = new Cliente(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getDate(5).toLocalDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public void eliminarFactura(int numeroFactura) {
        DataSource dataSource = MyDataSource.getMySQLDataSource();
        String query = "call eliminar_factura(?)";

        try (Connection connection = dataSource.getConnection();
            CallableStatement cs = connection.prepareCall(query)) {

            cs.setInt(1,numeroFactura);
            cs.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getArticulosMayorPrecioQue(double precio) {
        DataSource dataSource = MyDataSource.getMySQLDataSource();
        String query = "drop function if exists getArticulosMayorPrecioQue(); call eliminar_factura(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement cs = connection.prepareCall(query)) {

            cs.setDouble(1,precio);
            cs.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
