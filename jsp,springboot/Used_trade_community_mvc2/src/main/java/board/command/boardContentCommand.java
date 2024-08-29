package board.command;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dao.boardDAO;
import board.dto.boardDTO;

public class boardContentCommand implements boardCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int num = Integer.parseInt(request.getParameter("num"));
            String pageNum = request.getParameter("pageNum");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            boardDAO dbPro = boardDAO.getInstance();
            boardDTO article = dbPro.getArticle(num);

            int ref = article.getRef();
            int re_step = article.getRe_step();
            int re_level = article.getRe_level();

            HttpSession session = request.getSession();
            String userNickname = (String) session.getAttribute("userNickname");
            String loginState = (String) session.getAttribute("loginState");

            request.setAttribute("article", article);
            request.setAttribute("userNickname", userNickname);
            request.setAttribute("loginState", loginState);
            request.setAttribute("pageNum", pageNum);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // 게시물 번호 파싱 에러 처리
        } catch (Exception e) {
            e.printStackTrace();
            // 기타 예외 처리
        }
    }
}