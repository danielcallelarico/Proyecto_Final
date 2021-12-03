<%
    String opcion = request.getParameter("opcion");
%>    

<ul class="nav nav-tabs">
      <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("empleados") ? "active" : "")%>" href="EmpleadoControlador">Empleados</a>
    </li
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("proveedores") ? "active" : "")%>" href="ProveedorControlador">Proveedores</a>
    </li> 
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("clientes") ? "active" : "")%>" href="ClienteControlador">Clientes</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("productos") ? "active" : "")%>" href="ProductoControlador">Productos</a>
    </li>
    
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("ventas") ? "active" : "")%>" href="VentaControlador">Ventas</a>
    </li>
  
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("pedidos") ? "active" : "")%>" href="PedidoControlador">Pedidos</a>
    </li> 
    
</ul>