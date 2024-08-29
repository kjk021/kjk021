<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");

%>
<html>
<head>
<title>게시판</title>
<link href="${pageContext.request.contextPath}/css/deletelist.css" rel="stylesheet" type="text/css">
<script type="text/javascript">  
</script>
</head>
<body>
<h1 align="center">글삭제</h1>
<br>
<form method="POST" name="delForm" 
   action="delete.bo?pageNum=<%=pageNum%>" 
   onsubmit="return deleteSave()"> 
 <table>
  <tr height="30">
     <td align=center>
       <b>비밀번호를 입력해 주세요.</b></td>
  </tr>
  <tr height="30">
     <td align=center >비밀번호 :   
       <input type="password" name="passwd" size="8" maxlength="12">
	   <input type="hidden" name="num" value="<%=num%>"></td>
 </tr>
 <tr height="30">
    <td align=center>
      <input type="submit" value="글삭제" >
      <input type="button" value="글목록" 
       onclick="document.location.href='list.bo?pageNum=<%=pageNum%>'">     
   </td>
 </tr>  
</table> 
</form>
</body>
</html>