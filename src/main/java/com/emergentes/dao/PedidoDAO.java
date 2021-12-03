
package com.emergentes.dao;

import com.emergentes.modelo.Pedido;
import java.util.List;

public interface PedidoDAO {
    public void insert(Pedido pedido) throws Exception;

    public void update(Pedido pedido) throws Exception;

    public void delete(int id) throws Exception;

    public Pedido getById(int id) throws Exception;

    public List<Pedido> getAll() throws Exception;
}
