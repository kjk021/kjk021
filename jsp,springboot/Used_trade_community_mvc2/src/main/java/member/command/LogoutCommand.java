package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements memberCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 세션 삭제 또는 세션 속성 제거 등의 작업 수행
        HttpSession session = request.getSession();
        session.invalidate(); // 세션 무효화
    }
}
