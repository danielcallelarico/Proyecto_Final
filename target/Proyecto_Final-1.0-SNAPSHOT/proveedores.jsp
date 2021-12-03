<%
    if(session.getAttribute("login") != "OK"){
    response.sendRedirect("login.jsp");
}
    %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" rel="stylesheet">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>Almacen</title>
    </head>
    <body>
        <div class="container">
            <h1>Lista De Proveedores</h1>
            <br/>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="proveedores" />
            </jsp:include>
            <div style="text-align: right">
            <a href="Logout" class="btn btn-danger" > Cerrar Sesion</a> 
            </div>
            <a href="ProveedorControlador?action=add" class="btn btn-primary btn-sm mt-10">+ Adicionar Proveedor</a>
            <br/>
            <br/>
            <table class="table table-hover">
                <tr>
                    <th>Id</th>
                    <th>Empresa</th>
                    <th>Nombre Proveedor</th>
                    <th>Celular</th>
                    <th>Direccion</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${proveedores}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.empresa}</td>
                        <td>${item.nombre_prov}</td>
                        <td>${item.celular}</td>
                        <td>${item.direccion}</td>
                        <td><a href="ProveedorControlador?action=edit&id=${item.id}"><i class="far fa-edit"></i> Editar</a></td>
                        <td><a href="ProveedorControlador?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro'))"><i class="far fa-trash-alt"></i> Eliminar</a></td>
                    </tr>
                </c:forEach>
            </table>

        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.js"></script>
    </body>
</html>
