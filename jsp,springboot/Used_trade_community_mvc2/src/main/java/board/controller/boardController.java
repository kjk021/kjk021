package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.command.boardCommand;
import board.command.boardContentCommand;
import board.command.boardDeleteCommand;
import board.command.boardInputCommand;
import board.command.boardListCommand;
import board.command.boardUpdateCommand;



/**
 * Servlet implementation class boardController
 */
@WebServlet("*.bo")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String viewPage = null; //어떤 페이지로 갈 건지 지정
		boardCommand command = null; //다형성 이용, view 값에 따라서 목록 조회 등 수행 예정
		//ctrl shift o = 자동 임포트 및 안 쓰는 임포트 삭제 기능
		
		//주소 가져 오는 것
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		//com : /list.do, /modify.do 등... 커멘드만 남음
		
		//ctrl shift o = 자동 import 및 불필요한 import 삭제
		//ctrl shift s 누르고 r = getter setter 자동 생성
		//ctrl t = f3이랑 비슷하지만 어떤 선언부로 이동할 지 선택이 가능함
		
		//Link With Editor -> 현재 작업하고 있는 걸 자동 선택해 줌
		//점 세개 -> Package Presentation
		//ctrl shift r = 해당 위치로 이동 
		
		
		if(com.equals("/list.bo")) {
			viewPage = "/Board/list.jsp";
			command = new boardListCommand(); 
			command.execute(request, response);
		} else if(com.equals("/content.bo")) {
			viewPage = "/Board/content.jsp";
			command = new boardContentCommand();
			command.execute(request, response);
		
		} else if(com.equals("/modifyPage.bo")) {
			viewPage = "/Board/updateform.jsp";
		} else if(com.equals("/modify.bo")) {
			viewPage = "/list.bo";
			command = new boardUpdateCommand();
			command.execute(request, response);
		
		} else if(com.equals("/writePage.bo")) { 
			viewPage = "/Board/writeForm.jsp";
		} else if(com.equals("/write.bo")) {
			viewPage = "/list.bo";
			command = new boardInputCommand();
			command.execute(request, response);
		
		} else if(com.equals("/deletePage.bo")) {
			viewPage = "/Board/deleteform.jsp"; //답글 화면
		} else if(com.equals("/delete.bo")) {
			viewPage = "/list.bo";
			command = new boardDeleteCommand();
			command.execute(request, response);
		} 
		
		
		
		if(viewPage.equals("/list.bo")) {
			response.sendRedirect(conPath+viewPage);
		} else {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}

}




