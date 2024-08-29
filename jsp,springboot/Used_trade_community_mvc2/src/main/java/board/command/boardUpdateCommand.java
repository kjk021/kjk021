package board.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.boardDAO;
import board.dto.boardDTO;

public class boardUpdateCommand implements boardCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		String passwd = request.getParameter("passwd");

		// 수정할 게시글 정보를 담기 위해 BoardDTO 객체를 생성합니다.
		boardDTO article = new boardDTO();
		article.setNum(num);
		article.setWriter(writer);
		article.setSubject(subject);
		article.setEmail(email);
		article.setContent(content);
		article.setPasswd(passwd);

		// BoardDAO 객체를 생성하여 게시글을 수정합니다.
		boardDAO dbPro = boardDAO.getInstance();
		try {
			int check = dbPro.updateArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
