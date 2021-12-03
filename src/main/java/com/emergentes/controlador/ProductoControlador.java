
package com.emergentes.controlador;

import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            ProductoDAO dao = new ProductoDAOimpl();
            int id;
            Producto prod = new Producto();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("producto", prod);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    prod = dao.getById(id);
                    //System.out.println(cli);
                    request.setAttribute("producto", prod);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/ClienteControlador");
                    break;
                case "view":
                    List<Producto> lista = dao.getAll();
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error producto CONTROLADOR " + ex.getMessage());
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
         int id = Integer.parseInt(request.getParameter("id"));
        String marca = request.getParameter("nombre");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Float precio = Float.parseFloat(request.getParameter("precio")) ;
        String descripcion = request.getParameter("descripcion");

        Producto prod = new Producto();

        prod.setId(id);
        prod.setMarca(marca);
        prod.setCantidad(cantidad);
        prod.setPrecio(precio);
        prod.setDescripcion(descripcion);

        if (id == 0) {
            try {
                ProductoDAO dao = new ProductoDAOimpl();
                dao.insert(prod);
                response.sendRedirect(request.getContextPath() + "/ProductoControlador");
            } catch (Exception ex) {
                System.out.println("Error Nuevo Producto" + ex.getMessage());
            }
        } else {
            try {
                ProductoDAO dao = new ProductoDAOimpl();
                System.out.println("DAtos" + prod.toString());
                dao.update(prod);
                response.sendRedirect(request.getContextPath() + "/ProductoControlador");
            } catch (Exception ex) {
                System.out.println("Error Editar Producto " + ex.getMessage());
            }
        }
    }

}
