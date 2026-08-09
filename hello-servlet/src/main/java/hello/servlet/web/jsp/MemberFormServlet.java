package hello.servlet.web.jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "jspMemberFormServlet", urlPatterns = "/jsp/members/new-form")
public class MemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("formAction", "/jsp/members/save");
        request.setAttribute("listUrl", "/jsp/members");
        request.getRequestDispatcher("/WEB-INF/views/new-form.jsp").forward(request, response);
    }
}
