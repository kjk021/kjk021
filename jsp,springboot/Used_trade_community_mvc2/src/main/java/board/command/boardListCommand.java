package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.boardDAO;
import board.dto.boardDTO;

import java.util.List;
import java.text.SimpleDateFormat;

public class boardListCommand implements boardCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // DAO를 사용하여 데이터베이스에서 게시판 목록을 가져옵니다.
    	 boardDAO dbPro = boardDAO.getInstance();
    	    List<boardDTO> articleList = null; // 게시판 목록을 담을 리스트

    	    try {
    	        // 현재 페이지와 검색어를 가져옵니다.
    	        String pageNumParam = request.getParameter("pageNum");
    	        int currentPage = 1; // 기본값으로 1을 설정합니다.
    	        if (pageNumParam != null) {
    	            currentPage = Integer.parseInt(pageNumParam); // 현재 페이지 번호
    	        }
    	        String type = request.getParameter("type"); // 검색 유형
    	        String keyword = request.getParameter("keyword"); // 검색어

    	        int pageSize = 10; // 한 페이지당 보여줄 게시물 수
    	        int startRow = (currentPage - 1) * pageSize + 1; // 시작 row 번호
    	        int endRow = currentPage * pageSize; // 끝 row 번호

    	        int totalCount; // 전체 게시물 수

    	        // DAO를 사용하여 전체 게시물 수를 가져옵니다.
    	        if (type != null && keyword != null && !type.isEmpty() && !keyword.isEmpty()) {
    	            // 검색 기능이 적용된 경우
    	            articleList = dbPro.getArticles(startRow, pageSize, type, keyword);
    	        } else {
    	            // 일반적인 목록 조회인 경우
    	            totalCount = dbPro.getArticleCount();
    	            articleList = dbPro.getArticles(startRow, pageSize);
    	        }

    	 

    	        // 게시글 목록과 페이징 정보를 request 객체에 저장하여 JSP 파일에서 사용할 수 있도록 합니다.
    	        request.setAttribute("articleList", articleList);
    	        request.setAttribute("currentPage", currentPage); // 현재 페이지 번호
    	        request.setAttribute("pageSize", pageSize); // 한 페이지당 보여줄 게시물 수
    	        request.setAttribute("type", type); // 검색 유형
    	        request.setAttribute("keyword", keyword); // 검색어
  
    	    } catch (NumberFormatException e) {
    	        // pageNum 파라미터를 정수로 변환할 수 없는 경우 기본값으로 설정한 페이지를 사용합니다.
    	        e.printStackTrace();
    	        // 예외 처리 로직 추가
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    }
}