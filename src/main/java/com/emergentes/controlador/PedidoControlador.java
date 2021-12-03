
package com.emergentes.controlador;

import com.emergentes.dao.PedidoDAO;
import com.emergentes.dao.PedidoDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.dao.ProveedorDAO;
import com.emergentes.dao.ProveedorDAOimpl;
import com.emergentes.modelo.Pedido;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PedidoControlador", urlPatterns = {"/PedidoControlador"})
public class PedidoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PedidoDAO dao = new PedidoDAOimpl();
            ProductoDAO daoProducto = new ProductoDAOimpl();
            ProveedorDAO daoProveedor = new ProveedorDAOimpl();
            int id;
            List<Producto> lista_productos = null;
            List<Proveedor> lista_proveedores = null;            
            
            Pedido ped = new Pedido();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    lista_productos = daoProducto.getAll();
                    lista_proveedores = daoProveedor.getAll();
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("lista_proveedores", lista_proveedores);
                    request.setAttribute("pedido", ped);
                    request.getRequestDispatcher("frmpedido.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    ped = dao.getById(id);
                    //System.out.println(venta);
                    lista_productos = daoProducto.getAll();
                    lista_proveedores = daoProveedor.getAll();
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("lista_proveedores", lista_proveedores);
                    
                    request.setAttribute("pedido", ped);
                    request.getRequestDispatcher("frmpedido.jsp").forward(request, response);
                    break;     
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/PedidoControlador");
                    break;
                case "view":
                    List<Pedido> lista = dao.getAll();
                    request.setAttribute("pedidos", lista);
                    request.getRequestDispatcher("pedidos.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error Pedido CONTROLADOR " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int producto_id = Integer.parseInt(request.getParameter("producto_id"));
        int proveedor_id = Integer.parseInt(request.getParameter("proveedor_id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String fecha = request.getParameter("fecha");
        System.out.println("Fecha: ====> " + fecha);
        
        Pedido pedido = new Pedido();
        
        pedido.setId(id);
        pedido.setProducto_id(producto_id);
        pedido.setProveedor_id(proveedor_id);
        pedido.setCantidad(cantidad);
        pedido.setFecha(fecha);
        
        System.out.println(pedido.toString());
        
        if (id == 0){
            try {
                PedidoDAO dao = new PedidoDAOimpl();
                dao.insert(pedido);
                response.sendRedirect(request.getContextPath()+"/PedidoControlador");
            } catch (Exception ex) {
                System.out.println("Error Nuevo Pedido" + ex.getMessage());
            }
        }
        else{
            try {
                PedidoDAO dao = new PedidoDAOimpl();
                System.out.println("Datos" + pedido.toString());
                dao.update(pedido);
                response.sendRedirect(request.getContextPath()+"/PedidoControlador");
            } catch (Exception ex) {
                System.out.println("Error Editar Venta " + ex.getMessage());
            }
        }
    }

}
