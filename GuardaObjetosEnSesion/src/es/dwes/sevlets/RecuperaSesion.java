package es.dwes.sevlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RecuperaSesion", urlPatterns = {"/RecuperaSesion"})
public class RecuperaSesion extends HttpServlet {
    
 
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // se recuperan las varibles de sesión previamente creadas
            HttpSession laSesion = request.getSession(true);
                        
            //Se imprime el contenido de la sesión
            Enumeration<?> enumeracionAtributos;
            String nombreAtributo, valorAtributo;
            enumeracionAtributos = laSesion.getAttributeNames();
            out.println("<h1>PasoVariablesSesion</h1>");
            out.println("<h3>Servlet RecuperaSesion</h3>");
            out.println("<p>Variables de sesión:</p>");
            while (enumeracionAtributos.hasMoreElements()) {
                nombreAtributo = enumeracionAtributos.nextElement().toString();
                valorAtributo = laSesion.getAttribute(nombreAtributo).toString();
                out.println(nombreAtributo + " = " + valorAtributo + "<br />");
            }
            out.println("<hr />");
        }    
        catch (Exception e){ 
            out.println("Se produce una excepción <br />");
            out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }  
}
