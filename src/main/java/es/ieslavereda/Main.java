package es.ieslavereda;

import es.ieslavereda.connection.MyDataSource;
import es.ieslavereda.model.AlmacenDB;
import es.ieslavereda.model.Cliente;
import es.ieslavereda.model.ClienteDB;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MyDataSource.conectarMYSQL();

        getAllClientesTest();
        updateClienteTest();


    }

    static AlmacenDB datos = new ClienteDB();

    public static void getAllClientesTest() {
        List<Cliente> clientes = datos.getAllClientes();
        System.out.println(clientes);
    }

    public static void updateClienteTest() {
        Cliente cliente = new Cliente("UPDATE", "TEST", "53052298S", LocalDate.now());
        datos.updateCliente(cliente);
        getAllClientesTest();
    }

    public static void addClienteTest() {
        Cliente cliente2 = new Cliente("NUEVO", "CLIENTETEST", "12345678A", LocalDate.now());
        datos.addCliente(cliente2);
        getAllClientesTest();
    }

    public static void deleteClienteTest() {
        Cliente cliente3 = new Cliente("NUEVO", "CLIENTETEST", "12345678A", LocalDate.now());
        datos.deleteCliente(cliente3);
        getAllClientesTest();
    }

    public static void getClienteTest() {
        System.out.println(datos.getCliente("53052298S"));
    }

}