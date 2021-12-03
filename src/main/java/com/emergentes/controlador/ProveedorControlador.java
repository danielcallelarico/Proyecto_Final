
package com.emergentes.controlador;

import com.emergentes.dao.ProveedorDAO;
import com.emergentes.dao.ProveedorDAOimpl;
import com.emergentes.modelo.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProveedorControlador", urlPatterns = {"/ProveedorControlador"})
public class ProveedorControlador extends HttpServlet {   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            ProveedorDAO dao = new ProveedorDAOimpl();
            int id;
            Proveedor prod = new Proveedor();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("proveedor", prod);
                    request.getRequestDispatcher("frmproveedor.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    prod = dao.getById(id);
                    //System.out.println(cli);
                    request.setAttribute("proveedor", prod);
                    request.getRequestDispatcher("frmproveedor.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/ProveedorControlador");
                    break;
                case "view":
                    List<Proveedor> lista = dao.getAll();
                    request.setAttribute("proveedores", lista);
                    request.getRequestDispatcher("proveedores.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error proveedor CONTROLADOR " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
        String empresa = request.getParameter("empresa");
        String nombre_prov = request.getParameter("nombre_prov");
        String celular = request.getParameter("celular");
        String direccion = request.getParameter("celular");

        Proveedor cli = new Proveedor();

        cli.setId(id);
        cli.setEmpresa(empresa);
        cli.setNombre_prov(nombre_prov);
        cli.setCelular(celular);
        cli.setDireccion(direccion);

        if (id == 0) {
            try {
                ProveedorDAO dao = new ProveedorDAOimpl();
                dao.insert(cli);
                response.sendRedirect(request.getContextPath() + "/ProveedorControlador");
            } catch (Exception ex) {
                System.out.println("Error Nuevo Proveedor " + ex.getMessage());
            }
        } else {
            try {
                ProveedorDAO dao = new ProveedorDAOimpl();
                System.out.println("DAtos" + cli.toString());
                dao.update(cli);
                response.sendRedirect(request.getContextPath() + "/ProveedorControlador");
            } catch (Exception ex) {
                System.out.println("Error Editar Proveedor " + ex.getMessage());
            }
        }
    }

}
