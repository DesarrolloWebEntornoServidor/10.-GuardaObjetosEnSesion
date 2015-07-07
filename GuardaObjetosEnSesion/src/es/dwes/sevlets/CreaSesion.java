package es.dwes.sevlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.dwes.auxiliar.Punto;

@WebServlet("/CreaSesion")
public class CreaSesion extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // se vacía la sesión si existe
            if (request.getSession(false) != null) {  // si existe una sesión
                request.getSession().invalidate();  // ... la elimina
            }
            
            // se crea una sesión asociada a la petición
            HttpSession laSesion = request.getSession(true);
                        
            // se imprime el contenido de la sesión vacía
            Enumeration<?> enumeracionAtributos;
            String nombreAtributo, valorAtributo;
            enumeracionAtributos = laSesion.getAttributeNames();
            out.println("<h1>PasoVariablesSesion</h1>");
            out.println("<h3>Servlet CreaSesion</h3>");
            out.println("<p>Variables de sesión antes:</p>");
            while (enumeracionAtributos.hasMoreElements()) {
                nombreAtributo = enumeracionAtributos.nextElement().toString();
                valorAtributo = laSesion.getAttribute(nombreAtributo).toString();
                out.println(nombreAtributo + " = " + valorAtributo + " <br />");
            }
            out.println("<hr /> <br />");
            
            // se registran variables de sesión (objetos de diferentes clases)
            int entero = 12;
            double real = 3.1416;
            String cadena = "Hola sesión";
            Date fecha = new Date();
            Map<String,String> semaforo = new LinkedHashMap<String, String>();
            semaforo.put("R", "Rojo");semaforo.put("A", "Amarillo");semaforo.put("V", "Verde");
            Punto unPunto = new Punto(2,3);
            
            
            laSesion.setAttribute("entero", entero);
            laSesion.setAttribute("real", real);
            laSesion.setAttribute("texto", cadena);
            laSesion.setAttribute("fecha", fecha);
            laSesion.setAttribute("semaforo", semaforo);
            laSesion.setAttribute("unPunto", unPunto);
            
            // Se imprime el contenido de la sesión rellena
            enumeracionAtributos = laSesion.getAttributeNames();
            out.println("<p>Variables de sesión después;</p>");
            while (enumeracionAtributos.hasMoreElements()) {
                nombreAtributo = enumeracionAtributos.nextElement().toString();
                valorAtributo = laSesion.getAttribute(nombreAtributo).toString();
                out.println(nombreAtributo + " = " + valorAtributo + "<br />");
            }
            out.println("<hr /> <br />");
            
            out.println("<a href=\"RecuperaSesion\">RecuperaSesion</a>");    
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