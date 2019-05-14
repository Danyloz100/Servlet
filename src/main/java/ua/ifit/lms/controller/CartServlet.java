package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.view.CartView;
import ua.ifit.lms.view.IndexSingletonView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();

        User user = (User) session.getAttribute("user");

        if (user != null) {
            out.println(indexSingletonView.getMenu()
                    .replace("<a class=\"nav-link\" href=\"/login\"> Login <span class=\"sr-only\">", "<a class=\"nav-link\" href=\"/logout\"> Log out <span class=\"sr-only\">")
                    .replace("<a class=\"nav-link\" href=\"/reg\"> SingUp </a>", "<a class=\"nav-link\" href=\"/cart\"> " + user.getName() + " </a>")
            );
        }
        else
            out.println(indexSingletonView.getMenu());
        CartView cartView = new CartView();
        out.println(cartView.getCartPage());
    }

    }
