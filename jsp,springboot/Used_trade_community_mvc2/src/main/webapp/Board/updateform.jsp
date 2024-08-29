<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "board.dao.boardDAO" %>
<%@ page import = "board.dto.boardDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/css/updatelist.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");
  try{
      boardDAO dbPro = boardDAO.getInstance(); 
      boardDTO article =  dbPro.updateGetArticle(num);
%>

<h1 align="center">글수정</h1>
<br>
</head>
<body>
<form method="post" name="writeform" 
action="modify.bo?pageNum=<%=pageNum%>" onsubmit="return writeSave()">
<table>
  <tr>
    <td  width="70" align="center">작성자</td>
    <td align="left" width="330">
       <input type="text" size="10" maxlength="10" name="writer" 
         value="<%=article.getWriter()%>" style="ime-mode:active;">
	   <input type="hidden" name="num" value="<%=article.getNum()%>" readonly></td>
  </tr>
  <tr>
    <td  width="70"  align="center" >제 목</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="50" name="subject"
        value="<%=article.getSubject()%>" style="ime-mode:active;" ></td>
  </tr>
  <tr>
    <td  width="70"  align="center">Email</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="30" name="email" 
        value="<%=article.getEmail()%>" style="ime-mode:inactive;" readonly></td>
  </tr>
  <tr>
    <td  width="70"  align="center" >내 용</td>
    <td align="left" width="330">
     <textarea name="content" rows="13" cols="40" 
       style="ime-mode:active;"><%=article.getContent()%></textarea></td>
  </tr>
  <tr>
    <td  width="70"   align="center" >비밀번호</td>
    <td align="left" width="330" >
     <input type="password" size="8" maxlength="12" 
               name="passwd" style="ime-mode:inactive;">
     
	 </td>
  </tr>
  <tr>      
   <td colspan=2  align="center"> 
     <input type="submit" value="글수정" >  
     <input type="reset" value="다시작성">
     <input type="button" value="목록보기" 
       onclick="document.location.href='list.bo?pageNum=<%=pageNum%>'">
   </td>
 </tr>
 </table>
</form>
<%
}catch(Exception e){}%>      
      
</body>
</html>