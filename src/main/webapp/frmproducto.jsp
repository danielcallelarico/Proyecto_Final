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
            <h1>Formulario de Productos</h1>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="productos"/>
            </jsp:include>
            <br>
            
            <form action="ProductoControlador" method="post">
                <input type="hidden" name="id" value="${producto.id}" />
                <div class="form-group">
                    <label for="nombre">Marca</label>
                    <input type="text" name="nombre" value="${producto.marca}" class="form-control" placeholder="Escriba la amrca del producto">
                </div>
                <div class="form-group">
                    <label for="nombre">Cantidad</label>
                    <input type="email" name="correo" value="${producto.cantidad}" class="form-control" placeholder="Inserte cantidad">
                </div>
                <div class="form-group">
                    <label for="nombre">Precio</label>
                    <input type="text" name="celular" value="${producto.precio}" class="form-control" placeholder="Inserte precio">
                </div>
                <div class="form-group">
                    <label for="nombre">Descripcion</label>
                    <input type="text" name="celular" value="${producto.descripcion}" class="form-control" placeholder="Escriba descripcion del producto">
                </div>
                <br>
                <br>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>


        </div>
 <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
