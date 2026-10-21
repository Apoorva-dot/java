package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    value = "/config",
    initParams = {
        @jakarta.servlet.annotation.WebInitParam(
            name = "name",
            value = "Ashish"
        )
    }
)
public class ConfigContextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // ServletConfig
        ServletConfig config = getServletConfig();
        String name = config.getInitParameter("name");

        // ServletContext
        ServletContext context = getServletContext();

        context.setAttribute("message", "Hello from ServletContext!");

        String message = (String) context.getAttribute("message");

        out.println("<h1>ServletConfig</h1>");
        out.println("<p>Name: " + name + "</p>");

        out.println("<h1>ServletContext</h1>");
        out.println("<p>" + message + "</p>");
    }
}