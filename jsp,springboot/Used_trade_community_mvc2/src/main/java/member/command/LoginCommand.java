package member.command;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.dao.memberDAO;
import member.dto.memberDTO;

public class LoginCommand implements memberCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");

            String id = request.getParameter("id");
            String passwd = request.getParameter("passwd");

            memberDAO db = memberDAO.getInstance();
            int loginResult = db.login(id, passwd);
            memberDTO member = db.getMemberInfo(id);

            HttpSession session = request.getSession();

            if (loginResult == 2) {
                // Common user login successful
                session.setAttribute("loginState", "common");
                session.setAttribute("userId", member.getId());
                session.setAttribute("userNickname", member.getNickname());
                session.setAttribute("userPassword", member.getPasswd());
                session.setAttribute("userMemNum", member.getMem_num());
                session.setAttribute("userEmail", member.getEmail());
                response.sendRedirect("list.bo");
            } else if (loginResult == 1) {
                // Admin login successful
                session.setAttribute("loginState", "admin");
                session.setAttribute("userId", member.getId());
                session.setAttribute("userNickname", member.getNickname());
                session.setAttribute("userPassword", member.getPasswd());
                session.setAttribute("userMemNum", member.getMem_num());
                session.setAttribute("userEmail", member.getEmail());
                response.sendRedirect("list.bo");
            } else {
                // Handle login failure cases
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                if (loginResult == 3) {
                    // ID does not exist
                    out.println("alert('존재하지 않는 아이디입니다.');");
                } else if (loginResult == 4) {
                    // Incorrect password
                    out.println("alert('비밀번호가 잘못되었습니다.');");
                } else {
                    // Other login failures
                    out.println("alert('로그인에 실패했습니다. 다시 시도해주세요.');");
                }
                out.println("window.location.href = 'LoginPage.mo';");
                out.println("</script>");
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}