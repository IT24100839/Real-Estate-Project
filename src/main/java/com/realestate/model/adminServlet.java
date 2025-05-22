package com.realestate.model;

import javax.servlet.*;
        import javax.servlet.http.*;
        import java.io.IOException;

public class adminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
