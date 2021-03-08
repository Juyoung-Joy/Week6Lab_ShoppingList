package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 824664
 */
public class ShoppingListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();      
        String action = request.getParameter("action");
        
        if(action != null && action.equals("logout")){
           session.invalidate();
           session = request.getSession();
           request.setAttribute("message", "Successfully logged out");
           getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                   .forward(request, response); 
           return;
        }
        
        String username = (String) session.getAttribute("username");
        
        if (username != null && action == null) {
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                        .forward(request, response);  
        }        
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);         
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
 
        ArrayList<String> items = new ArrayList<>();      
        
        switch (action) {
            case "register":     
                String username = request.getParameter("username");
                if (username == null || username.isEmpty()) 
                {
                    request.setAttribute("username", username);
                    session.setAttribute("message", "Username can't be empty");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                            .forward(request, response);
                    return;
                }                
                session.setAttribute("username", username);
                break;
            
            case "add": 
                String item = request.getParameter("item");
                items.add(item);
                session.setAttribute("items", items);     
                session.setAttribute("item", item);
                break;
                
            case "delete":
                String delItem = request.getParameter("item");
                items.remove(delItem);
                session.setAttribute("items", items);
                break;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                        .forward(request, response);           
    }

}
