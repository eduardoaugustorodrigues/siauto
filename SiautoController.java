
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bo.helper.SiautoHelper;
import model.bo.cmd.InterfaceCommand;

public class SiautoController extends HttpServlet {
    private SiautoHelper helper;

    public SiautoController() {
        helper = new SiautoHelper();
    }
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // O processamento da requisição é delegado ao SiautoHelper
        helper.setRequest(request);
        InterfaceCommand command = helper.getCommand(); // retorna um comando a executar
        String pagina = command.execute(request, response); // retorna página a exibir na view
        // retorna a página para o browser exibir
        request.getRequestDispatcher(pagina).include(request, response);
        //request.getRequestDispatcher(pagina).forward(request, response);
        
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SiautoController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SiautoController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    }

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
