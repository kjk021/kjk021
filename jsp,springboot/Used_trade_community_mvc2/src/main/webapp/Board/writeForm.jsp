<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/css/write.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../script/write.js"></script>
</head>
<body>
<% 
  int num = 0, ref = 1, re_step = 0, re_level = 0;
  String strV = "";
  try{
    if(request.getParameter("num")!=null){
       num=Integer.parseInt(request.getParameter("num"));
       ref=Integer.parseInt(request.getParameter("ref"));
       re_step=Integer.parseInt(request.getParameter("re_step"));
       re_level=Integer.parseInt(request.getParameter("re_level"));
    }
    
    // 세션에서 이름과 이메일 값을 가져옵니다.
    String userNickname = (String) session.getAttribute("userNickname");
    String userEmail = (String) session.getAttribute("userEmail");
%>
<h1 align="center">글쓰기</h1>
<form method="post" name="writeform" 
      action="write.bo" onsubmit="return writeSave()">
<input type="hidden" name="num" value="<%=num%>">
<input type="hidden" name="ref" value="<%=ref%>">
<input type="hidden" name="re_step" value="<%=re_step%>">
<input type="hidden" name="re_level" value="<%=re_level%>">
<table>
   <tr>
    <td  width="70" align="center">작성자</td>
    <td  width="330" align="left">
       <!-- 세션에서 가져온 이름 값을 여기에 넣습니다. -->
       <input type="text" size="10" maxlength="10" 
          name="writer" style="ime-mode:active;" value="<%= userNickname %>" readonly></td>
  </tr>
  <tr>
    <td  width="70" align="center" >제 목</td>
    <td  width="330" align="left">
        <form method="get" action="SelectServlet">
        <select id="headline" name="headline" size="1">
            <option value="">선택하세요.</option>
            <option value="사고팝니다">[사고팝니다]</option>
            <option value="핫딜특가">[핫딜특가]</option>
            <option value="구인구직">[구인구직]</option>
            <option value="동네정보">[동네정보]</option>        
        </select> 
    <%
      if(request.getParameter("num")==null) 
         strV = "";
      else
         strV = "[답변]";
    %>
    <input type="text" size="40" maxlength="50" name="subject"
         value="<%=strV%>" style="ime-mode:active;"></td>    
  </tr>
<tr>
    <td  width="70"  align="center">Email</td>
    <td  width="330" align="left">
       <!-- 세션에서 가져온 이메일 값을 여기에 넣습니다. -->
       <input type="text" size="40" maxlength="30" name="email"
           style="ime-mode:inactive;" value="<%= userEmail %>" readonly></td>
  </tr>
  <tr>
    <td  width="70"  align="center" >내 용</td>
    <td  width="330" align="left">
     <textarea name="content" rows="13" cols="40" 
              style="ime-mode:active;"></textarea> </td>
  </tr>
  <tr>
    <td  width="70"   align="center" >비밀번호</td>
    <td  width="330" align="left">
     <input type="password" size="8" maxlength="12" 
             name="passwd" style="ime-mode:inactive;"> 
     </td>
  </tr>
  <tr>      
    <td colspan=2  align="center"> 
      <input type="submit" value="글쓰기" >  
      <input type="reset" value="다시작성">
      <input type="button" value="목록보기" OnClick="window.location='list.bo'">
    </td>
  </tr>
</table>    
 <%
  }catch(Exception e){}
%>    
</form>  

</body>
</html>
