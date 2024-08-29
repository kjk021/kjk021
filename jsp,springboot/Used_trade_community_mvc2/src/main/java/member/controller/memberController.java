package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.command.LoginCommand;
import member.command.LogoutCommand;
import member.command.SingUpCommand;
import member.command.memberCommand;


/**
 * Servlet implementation class memberController
 */
@WebServlet("*.mo")
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberController() {
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
		memberCommand command = null; //다형성 이용, view 값에 따라서 목록 조회 등 수행 예정
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
		
		
		if(com.equals("/LoginPage.mo")) {
            viewPage = "/Login/Login.jsp"; // 로그인 페이지로 이동
        } else if(com.equals("/Login.mo")) {
        	command = new LoginCommand();
            command.execute(request, response);
            return; // 로그인 후 리다이렉트는 LoginCommand에서 처리
        } else if(com.equals("/Logout.mo")) {
            viewPage = "/LoginPage.mo"; // 로그아웃 후 로그인 페이지로 이동
            command = new LogoutCommand();
            command.execute(request, response);
        } else if(com.equals("/SingupPage.mo")) {
            viewPage = "/Sing_up/Sing_up.jsp"; // 회원가입 페이지로 이동
        } else if(com.equals("/Singup.mo")) {
        	command = new SingUpCommand();
            command.execute(request, response);
            return; // SingUpCommand에서 리다이렉트를 처리
        } 
        
		
		
        if(viewPage.equals("/list.bo")) {
            response.sendRedirect(request.getContextPath() + viewPage); // 페이지 리다이렉트
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response); // 페이지 포워딩
        }
		
	}

}




