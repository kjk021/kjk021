<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/css/Login.css" rel="stylesheet" type="text/css">
 
</head>
<body>
    <div class="login-container">
        <h1>로그인</h1>
        <form action="Login.mo" method="post">
            <table>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="id"></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="passwd"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="로그인"></td>
                </tr>
            </table>
        </form>
        <div class="signup-btn">
            <form action="SingupPage.mo" method="post">
                <button type="submit">회원가입</button>
            </form>
        </div>
    </div>
</body>
</html>