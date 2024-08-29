package member.command;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.dao.memberDAO;
import member.dto.memberDTO;

public class SingUpCommand implements memberCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");

            memberDTO member = new memberDTO();
            member.setName(request.getParameter("name"));
            member.setId(request.getParameter("id"));
            member.setPasswd(request.getParameter("passwd"));
            member.setNickname(request.getParameter("nickname"));
            member.setEmail(request.getParameter("email"));
            member.setPhone(request.getParameter("phone"));
            member.setRRN(request.getParameter("RRN"));
            member.setRanks(1); // 기본값 설정

            memberDAO db = memberDAO.getInstance();
            db.insertMember(member);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('회원가입이 완료되었습니다.');");
            out.println("window.location.href = 'LoginPage.mo';");
            out.println("</script>");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}