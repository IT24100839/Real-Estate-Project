<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%
  session.invalidate();
  response.sendRedirect("login.jsp");
%>
