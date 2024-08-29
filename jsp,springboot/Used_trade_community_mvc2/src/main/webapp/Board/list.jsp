<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="board.dao.boardDAO"%>
<%@ page import="board.dto.boardDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%!
    int pageSize = 10;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%
    String pageNum = request.getParameter("pageNum");

    if (pageNum == null) {
        pageNum = "1";
    }
    String field_ = request.getParameter("type");
    String query_ = request.getParameter("keyword");

    String field = "subject";
    if(field_ != null)
        field = field_;
    String query = "";
    if(query_ != null)
        query = query_;

    int currentPage = Integer.parseInt(pageNum);
    int startRow = (currentPage - 1) * pageSize + 1;
    int endRow = currentPage * pageSize;
    int count = 0;
    int number = 0;
    List<boardDTO> articleList = null; 
    
    boardDAO dbPro = boardDAO.getInstance();
    count = dbPro.getArticleCount();
    
    if (count > 0) {
        articleList = dbPro.getArticles(startRow, pageSize, field, query);
    }
    number = count-(currentPage-1)*pageSize;
    
%>

<%
    HttpSession sessions = request.getSession();
    String loginState = (String) sessions.getAttribute("loginState");
    String userEmail = (String) sessions.getAttribute("userEmail");
    String userNickname = (String) sessions.getAttribute("userNickname");
    Integer userMemNumObj = (Integer) sessions.getAttribute("userMemNum");
    int userMemNum = (userMemNumObj != null) ? userMemNumObj.intValue() : 0;
    String userId = (String) sessions.getAttribute("userId");
%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet" type="text/css">
    <title>게시판</title>
</head>
<body>
    <h1 align="center">
        글목록(전체 글:<%=count%>)
    </h1>
	<table>
		<tr>
			<td align="center">로그인상태 : <%=loginState %></td>
			<td align="center">회원 아이디 : <%=userId %></td>
			<td align="center">닉네임 : <%=userNickname %></td>
			<td align="center">회원 번호 : <%=userMemNum %></td>
			<td align="center">회원 이메일 : <%=userEmail %></td>
			
		</tr>
	</table>
	
	
    <table>
        <tr>
            <td align="right"><a href="writePage.bo">글쓰기</a></td>
            <td align="center"><a href="Logout.mo">로그아웃</a></td>
        </tr>
    </table>

    <% if (articleList == null || articleList.isEmpty()) { %>

    <table>
        <tr>
            <td align="center">검색 결과가 없습니다.</td>
        </tr>
    </table>

    <% } else {%>
    <table>
        <tr height="30">
            <td align="center" width="50">번 호</td>
            <td align="center" width="250">제 목</td>
            <td align="center" width="100">작성자</td>
            <td align="center" width="150">작성일</td>
            <td align="center" width="50">조 회</td>
            <td align="center" width="100">IP</td>
        </tr>
        <%  
        for (int i = 0 ; i < articleList.size() ; i++) {
            boardDTO article = articleList.get(i);
        %>
        <tr height="30">
            <td width="50"><%= (currentPage - 1) * pageSize + i + 1 %></td>
            <td width="250" align="left">
                <%
                if(article.getHeadline() != null){
                %> [<%=article.getHeadline()%>] <%
                }%> <%
                int wid=0; 
                if(article.getRe_level()>0){
                    wid=5*(article.getRe_level());
                %> <img src="${pageContext.request.contextPath}/images/level.png" width="<%=wid%>" height="16"> <img
                    src="${pageContext.request.contextPath}/images/re.png"> <%  }else{%> <img src="images/level.png"
                    width="<%=wid%>" height="16"> <%  }%> <a
                    href="${pageContext.request.contextPath}/content.bo?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
                        <%=article.getSubject()%></a> <% if(article.getReadcount()>=20){%> <img
                    src="${pageContext.request.contextPath}/images/hot.png" border="0" height="16">
                    <%}%>
                </td>
                <td width="100" align="left"><a
                    href="mailto:<%=article.getEmail()%>"> <%=article.getWriter()%></a>
                </td>
                <td width="150"><%= sdf.format(article.getReg_date())%></td>
                <td width="50"><%=article.getReadcount()%></td>
                <td width="100"><%=article.getIp()%></td>
            </tr>
            <%}%>
        </table>
        <%}%>

        <%
        if (count > 0) {
            int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
            int startPage =1;

            if(currentPage % 10 != 0)
                startPage = (int)(currentPage/10)*10 + 1;
            else
                startPage = ((int)(currentPage/10)-1)*10 + 1;

            int pageBlock = 10;
            int endPage = startPage + pageBlock - 1;
            if (endPage > pageCount) endPage = pageCount;

            if (startPage > 10) { %>
            <a href="list.bo?pageNum=<%= startPage - 10 %>&type=<%=field%>&keyword=<%=query%>">[이전]</a>
            <%      }

            for (int i = startPage ; i <= endPage ; i++) {  %>
            <a href="list.bo?pageNum=<%= i %>&type=<%=field%>&keyword=<%=query%>">[<%= i %>]
            </a>
            <%      }

            if (endPage < pageCount) {  %>
            <a href="list.bo?pageNum=<%= startPage + 10 %>&type=<%=field%>&keyword=<%=query%>">[다음]</a>
            <%
            }
        }
        %>
        <br>
        <br>
        <form action="list.bo" method="get" name="searchform">
            <select name="type" class="type-box">
                <option value="">검색 유형 선택</option>
                <option value="subject">제목</option>
                <option value="writer">작성자</option>
                <option value="content">내용</option>
            </select>
            <td colspan="2"><input class="inputId" type="text" name="keyword"
                placeholder="검색어 입력"></td>
            <td><input class="submitBtn" type="submit" value="검색하기">
                <a href="list.bo" class="button">초기화</a>
            </td>
        </form>
    </body>
</html>
