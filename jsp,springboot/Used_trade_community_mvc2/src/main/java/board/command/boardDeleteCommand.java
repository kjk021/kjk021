package board.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.boardDAO;

public class boardDeleteCommand implements boardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String passwd = request.getParameter("passwd");

		boardDAO dbPro = boardDAO.getInstance();
		int check = 0;
		try {
			check = dbPro.deleteArticle(num, passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (check == 1) {
			// 삭제 성공 시 리스트 페이지로 이동
			String redirectURL = "list.bo?pageNum=" + pageNum;
			
		} else {
			// 삭제 실패 시 이전 페이지로 이동하고 경고창 띄우기
			request.setAttribute("message", "비밀번호가 맞지 않습니다");
		}
	}
}
