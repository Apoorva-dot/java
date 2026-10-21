package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // =========================
        // COOKIE
        // =========================

        Cookie cookie = new Cookie("username", "Ashish");
        response.addCookie(cookie);

        // Read cookies
        Cookie[] cookies = request.getCookies();

        out.println("<h1>Cookies</h1>");

        if (cookies != null) {

            for (Cookie c : cookies) {

                if ("username".equals(c.getName())) {

                    out.println("<p>Username cookie: "
                            + c.getValue()
                            + "</p>");
                }
            }
        }

        // =========================
        // HTTP SESSION
        // =========================

        HttpSession session = request.getSession();

        session.setAttribute("username", "Ashish");

        String username =
                (String) session.getAttribute("username");

        out.println("<h1>HttpSession</h1>");
        out.println("<p>Session username: "
                + username
                + "</p>");

        // =========================
        // URL REWRITING
        // =========================

        String url = response.encodeURL("session");

        out.println("<h1>URL Rewriting</h1>");
        out.println("<a href='" + url + "'>Session Link</a>");

        // Uncomment this when you want to destroy the session:
        // session.invalidate();
    }
}