package es.ieslavereda;

import es.ieslavereda.connection.MyDataSource;
import es.ieslavereda.model.AlmacenDB;
import es.ieslavereda.model.Cliente;
import es.ieslavereda.model.ClienteDB;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MyDataSource.conectarMYSQL();

        AlmacenDB datos = new ClienteDB();
        List<Cliente> clientes = datos.getAllClientes();
        System.out.println(clientes);

    }
}