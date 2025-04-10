package es.ieslavereda.model;

import java.util.List;

public interface AlmacenDB {
    List<Cliente> getAllClientes();
    void updateCliente(Cliente cliente);
    void addCliente(Cliente cliente);
    void deleteCliente(Cliente cliente);
    Cliente getCliente(String dni);
}
