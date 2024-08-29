<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link href="${pageContext.request.contextPath}/css/Sing_up.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="register-container">
        <h1>회원가입</h1>
        <form action="Singup.mo" method="post">
            <table>
                <tr>
                    <td>이름</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="id"></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="passwd"></td>
                </tr>
                <tr>
                    <td>닉네임</td>
                    <td><input type="text" name="nickname"></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td>전화번호</td>
                    <td><input type="text" name="phone"></td>
                </tr>
                <tr>
                    <td>주민등록번호</td>
                    <td><input type="text" name="RRN"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="가입"></td>
                </tr>
            </table>
        </form>
        <div class="login-link">
            이미 회원이신가요? <a href="LoginPage.mo">로그인 페이지로 이동</a>
        </div>
    </div>
</body>
</html>