
package com.emergentes.controlador;

import com.emergentes.dao.EmpleadoDAO;
import com.emergentes.dao.EmpleadoDAOimpl;
import com.emergentes.modelo.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmpleadoControlador", urlPatterns = {"/EmpleadoControlador"})
public class EmpleadoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             try {
            EmpleadoDAO dao = new EmpleadoDAOimpl();
            int id;
            Empleado prod = new Empleado();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("empleado", prod);
                    request.getRequestDispatcher("frmempleado.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    prod = dao.getById(id);
                    //System.out.println(cli);
                    request.setAttribute("empleado", prod);
                    request.getRequestDispatcher("frmempleado.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/EmpleadoControlador");
                    break;
                case "view":
                    List<Empleado> lista = dao.getAll();
                    request.setAttribute("empleados", lista);
                    request.getRequestDispatcher("empleados.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error empleados CONTROLADOR " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String celular = request.getParameter("celular");
        String cargo = request.getParameter("cargo");
        int sueldo = Integer.parseInt(request.getParameter("sueldo"));

        Empleado cli = new Empleado();

        cli.setId(id);
        cli.setNombre(nombre);
        cli.setCelular(celular);
        cli.setCargo(cargo);
        cli.setSueldo(sueldo);

        if (id == 0) {
            try {
                EmpleadoDAO dao = new EmpleadoDAOimpl();
                dao.insert(cli);
                response.sendRedirect(request.getContextPath() + "/EmpleadoControlador");
            } catch (Exception ex) {
                System.out.println("Error Nuevo Empleado" + ex.getMessage());
            }
        } else {
            try {
                EmpleadoDAO dao = new EmpleadoDAOimpl();
                System.out.println("DAtos" + cli.toString());
                dao.update(cli);
                response.sendRedirect(request.getContextPath() + "/EmpleadoControlador");
            } catch (Exception ex) {
                System.out.println("Error Editar Empleado " + ex.getMessage());
            }
        }
    }


}
