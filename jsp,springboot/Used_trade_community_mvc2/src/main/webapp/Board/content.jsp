<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.dao.boardDAO"%>
<%@ page import="board.dto.boardDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/css/content.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%
    int num = Integer.parseInt(request.getParameter("num"));
    String pageNum = request.getParameter("pageNum");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    try{
    	boardDTO article = (boardDTO) request.getAttribute("article");
    
        int ref=article.getRef();
        int re_step=article.getRe_step();
        int re_level=article.getRe_level();
        
        String userNickname = (String) session.getAttribute("userNickname");
        String userEmail = (String) session.getAttribute("userEmail");
        String loginState = (String) session.getAttribute("loginState");
    %>

    <h1 align="center">글내용 보기</h1>

    <table>
        <tr height="30">
            <td align="center" width="125">글번호</td>
            <td align="center" width="125" align="center"><%=article.getNum()%></td>
            <td align="center" width="125">조회수</td>
            <td align="center" width="125" align="center"><%=article.getReadcount()%></td>
        </tr>
        <tr height="30">
            <td align="center" width="125">작성자</td>
            <td align="center" width="125" align="center"><%=article.getWriter()%></td>
            <td align="center" width="125">작성일</td>
            <td align="center" width="125" align="center"><%= sdf.format(article.getReg_date())%></td>
        </tr>
        <tr height="30">
            <td align="center" width="125">글제목</td>
            <td align="center" width="375" align="center" colspan="3"><%=article.getSubject()%></td>
        </tr>
        <tr>
            <td align="center" width="125">글내용</td>
            <td align="left" width="375" colspan="3"><pre><%=article.getContent()%></pre></td>
        </tr>
        <tr height="30">
            <td colspan="4" align="right">
                <%
    if ("common".equals(loginState) && userNickname.equals(article.getWriter()) || "admin".equals(loginState)){  
%> 
                <input type="button" value="글수정" onclick="document.location.href='modifyPage.bo?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
                &nbsp;&nbsp;&nbsp;&nbsp; 
                <input type="button" value="글삭제" onclick="document.location.href='deletePage.bo?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
                &nbsp;&nbsp;&nbsp;&nbsp; 
                <%
    } 
%> 
                <input type="button" value="답글쓰기" onclick="document.location.href='writePage.bo?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">
                &nbsp;&nbsp;&nbsp;&nbsp; 
                <input type="button" value="글목록" onclick="document.location.href='list.bo?pageNum=<%=request.getParameter("pageNum")%>'">
            </td>
        </tr>
    </table>
    <%
}catch(Exception e){} 
%>
</body>
</html>