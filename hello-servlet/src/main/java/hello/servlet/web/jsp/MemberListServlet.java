package hello.servlet.web.jsp;

import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "jspMemberListServlet", urlPatterns = "/jsp/members")
public class MemberListServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("members", memberRepository.findAll());
        request.setAttribute("newFormUrl", "/jsp/members/new-form");
        request.getRequestDispatcher("/WEB-INF/views/members.jsp").forward(request, response);
    }
}
