package com.emergentes.controlador;

import com.emergentes.dao.RolDAO;
import com.emergentes.dao.RolDAOimpl;
import com.emergentes.modelo.Rol;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RolController", urlPatterns = {"/RolController"})
public class RolController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RolDAO dao = new RolDAOimpl();
            int id;
            Rol r = new Rol();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("rol", r);
                    request.getRequestDispatcher("frmrol.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    r = dao.getById(id);
                    request.setAttribute("rol", r);
                    request.getRequestDispatcher("frmrol.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/RolController");
                    break;
                case "view":
                    List<Rol> lista = dao.getAll();
                    request.setAttribute("roles", lista);
                    request.getRequestDispatcher("roles.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");

        Rol r = new Rol();

        r.setId(id);
        r.setDescripcion(descripcion);

        if (id == 0) {
            try {
                RolDAO dao = new RolDAOimpl();
                dao.insert(r);
                response.sendRedirect(request.getContextPath() + "/RolController");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                RolDAO dao = new RolDAOimpl();
                dao.update(r);
                response.sendRedirect(request.getContextPath() + "/RolController");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
