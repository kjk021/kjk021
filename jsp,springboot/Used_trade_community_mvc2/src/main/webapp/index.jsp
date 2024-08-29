<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 세션에서 로그인 상태를 확인합니다.
    String loginState = (String) session.getAttribute("loginState");
    
    // 로그인 상태가 없거나, 로그인 상태가 없는 경우 로그인 페이지로 이동합니다.
    if (loginState == null || loginState.isEmpty()) {
        response.sendRedirect("LoginPage.mo");
        return;
    }
    
    // 로그인 상태가 있으면 상태에 따라 적절한 페이지로 리다이렉트합니다.
    if (loginState.equals("admin")) {
        response.sendRedirect("list.bo");
    } else if (loginState.equals("common")) {
        response.sendRedirect("list.bo");
    } else {
        // 그 외의 상황에 대한 처리(예를 들어, 세션이 손상된 경우)
        response.sendRedirect("LoginPage.mo");
    }
%>