package Presentation;

import Logic.Order;
import Logic.User;
import Data.DAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DAO d = new DAO();
    HttpSession session;
    ArrayList<User> userList = d.getUsers();
    ArrayList<Order> orderList = d.getOrderList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        session = request.getSession();

        String origin = request.getParameter("origin");
        switch (origin) {
            case "index":
                index(request, response);
                break;
            case "createUser":
                createUser(request, response);
                break;
            case "checkLogin":
                checkLogin(request, response);
                break;
            case "shop":
                shop(request, response);
                break;
            case "makeUser":
                makeUser(request, response);
                break;
            case "admin":
                admin(request, response);
                break;
            case "orderList":
                orderList(request, response);
                break;
            case "removeUser":
                removeUser(request, response);
                break;
            case "calcBricks":
                calcBricks(request, response);
                break;
            case "placeOrder":
                placeOrder(request, response);
                break;
            case "myOrders":
                myOrders(request, response);
                break;
            case "shipOrder":
                shipOrder(request, response);
                break;
            case "viewBricks":
                viewBricks(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("createUser.jsp").forward(request, response);
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean valid = d.checkLogin(email, password);
        User u = d.getUser(email);
        userList = d.getUsers();
        session.setAttribute("user", u);
        session.setAttribute("userList", userList);

        orderList = d.getOrderList();
        session.setAttribute("orderList", orderList);

        if (valid && email != null && password != null && !("".equals(email))
                && !("".equals(password)) && u.isAdmin() == true) {
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else if (valid && email != null && password != null && !("".equals(email))
                && !("".equals(password))) {
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void makeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (!("".equals(email)) && !("".equals(password))) {
            d.createUser(email, password);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("createUser.jsp").forward(request, response);
        }

    }

    private void shop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    private void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("orderList.jsp").forward(request, response);
    }

    private void removeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        d.removeUser(email);
        userList.clear();
        userList = d.getUsers();
        session.setAttribute("userList", userList);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void calcBricks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        int height = Integer.parseInt(request.getParameter("height"));
        int bandType = Integer.parseInt(request.getParameter("band"));

        String band = "";
        switch (bandType) {
            case 1:
                band = "½-stensforbandt";
                break;
            case 2:
                band = "1/4-stensforbandt";
                break;
            case 3:
                band = "Blok forbandt";
                break;
            case 4:
                band = "Kryds forbandt";
                break;
            default:
                throw new AssertionError();
        }

        int[] brickList = d.calcBricks(width, length, height, bandType);
        session.setAttribute("brickList", brickList);
        session.setAttribute("width", width);
        session.setAttribute("length", length);
        session.setAttribute("height", height);
        session.setAttribute("band", band);

        request.getRequestDispatcher("calcBricks.jsp").forward(request, response);
    }

    private void placeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User) session.getAttribute("user");
        int[] brickList = (int[]) session.getAttribute("brickList");

        int width = (int) session.getAttribute("width");
        int length = (int) session.getAttribute("length");
        int height = (int) session.getAttribute("height");
        String bandType = (String) session.getAttribute("band");

        d.makeOrder(width, length, height, bandType, u.getEmail());

        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    private void myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User) session.getAttribute("user");
        ArrayList<Order> myOrders = d.getMyOrderList(u.getEmail());
        myOrders.clear();
        myOrders = d.getMyOrderList(u.getEmail());
        session.setAttribute("myOrders", myOrders);
        request.getRequestDispatcher("myOrders.jsp").forward(request, response);
    }

    private void shipOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("shipOrder"));
        d.shipOrder(orderID);
        orderList.clear();
        orderList = d.getOrderList();
        session.setAttribute("orderList", orderList);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void viewBricks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int viewBricksID = Integer.parseInt(request.getParameter("viewBricksID"));
        Order order = d.getOrder(viewBricksID);
        
        int width = order.getWidth();
        int length = order.getLength();
        int height = order.getHeight();
        int bandType = 0;
        String band = order.getBandType();
        
        switch (band) {
            case "½-stensforbandt":
                bandType = 1;
                break;
            case "1/4-stensforbandt":
                bandType = 2;
                break;
            case "Blok forbandt":
                bandType = 3;
                break;
            case "Kryds forbandt":
                bandType = 4;
                break;
            default:
                throw new AssertionError();
        }
        int[] brickList = d.calcBricks(width, length, height, bandType);
        
        int x4 = brickList[0] + brickList[3];
        int x2 = brickList[1] + brickList[4];
        int x1 = brickList[2] + brickList[5];
        
        session.setAttribute("viewBricksList", brickList);
        /*
        session.setAttribute("x4", x4);
        session.setAttribute("x2", x2);
        session.setAttribute("x1", x1);
        */
        request.getRequestDispatcher("viewBricks.jsp").forward(request, response);
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
