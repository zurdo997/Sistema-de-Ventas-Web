/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.Venta;
import modelo.VentaDAO;

/**
 *
 * @author Alan97
 */
public class Controlador extends HttpServlet {
    Empleado em = new Empleado();
    EmpleadoDAO emdao = new EmpleadoDAO();
    Cliente c = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto p = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int ide;
    int idc;
    int idp;
    
    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    
    String numeroserie = "";
    VentaDAO vdao = new VentaDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleados")) {
            switch(accion) {
                case "Listar":
                    List lista = emdao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombre");
                    String tel = request.getParameter("txtTel");
                    String estado = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(estado);
                    em.setUser(user);
                    emdao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = emdao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni2 = request.getParameter("txtDni");
                    String nom2 = request.getParameter("txtNombre");
                    String tel2 = request.getParameter("txtTel");
                    String estado2 = request.getParameter("txtEstado");
                    String user2 = request.getParameter("txtUser");
                    em.setDni(dni2);
                    em.setNom(nom2);
                    em.setTel(tel2);
                    em.setEstado(estado2);
                    em.setUser(user2);
                    em.setId(ide);
                    emdao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    emdao.eliminar(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleados.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List lista = pdao.listar();
                    request.setAttribute("productos", lista);
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtDni");
                    double pre = Double.parseDouble(request.getParameter("txtNombres"));
                    int st = Integer.parseInt(request.getParameter("txtTel"));
                    String est = request.getParameter("txtEstado");
                    p.setNom(nom);
                    p.setPre(pre);
                    p.setStock(st);
                    p.setEstado(est);
                    pdao.agregar(p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    Producto pr = pdao.listarId(idp);
                    request.setAttribute("producto", pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    double pre1 = Double.parseDouble(request.getParameter("txtNombres"));
                    int st1 = Integer.parseInt(request.getParameter("txtTel"));
                    String est1 = request.getParameter("txtEstado");
                    p.setNom(dni1);
                    p.setPre(pre1);
                    p.setStock(st1);
                    p.setEstado(est1);
                    p.setId(idp);
                    pdao.actualizar(p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.eliminar(idp);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                default:                    
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    c.setDni(dni);
                    c.setNom(nom);
                    c.setDir(tel);
                    c.setEs(est);
                    cdao.agregar(c);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    Cliente cl = cdao.listarId(idc);
                    request.setAttribute("cliente", cl);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    c.setDni(dni1);
                    c.setNom(nom1);
                    c.setDir(tel1);
                    c.setEs(est1);
                    c.setId(idc);
                    cdao.actualizar(c);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    cdao.eliminar(idc);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("NuevaVenta")) {
            switch(accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    c.setDni(dni);
                    c = cdao.buscar(dni);
                    request.setAttribute("c", c);
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    p = pdao.listarId(id);
                    request.setAttribute("c", c);
                    request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    break;
                case "Agregar":
                    request.setAttribute("c", c);
                    totalPagar = 0.0;
                    item = item + 1;
                    cod = p.getId();
                    descripcion = request.getParameter("nomproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;
                    v = new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);
                    break;
                case "GenerarVenta":
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);
                        int skac = pr.getStock() - cantidad;
                        aO.actualizarstock(idproducto, skac);
                    }
                    //Guardar Venta
                    v.setIdcliente(c.getId());
                    v.setIdempleado(2);
                    v.setNumserie(numeroserie);
                    v.setFecha("2019-08-20");
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);
                    //Detalle Venta
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);
                    }
                    break;
                default:
                    v = new Venta();
                    lista = new ArrayList<>();
                    item = 0;
                    totalPagar = 0.0;
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "0000001";
                        request.setAttribute("nserie", numeroserie);
                    }else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
