<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Greeting Page</title>
<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  h1 {
    color: #4CAF50;
    font-size: 48px;
    text-align: center;
  }
</style>
</head>
<body>
  <h1>Hello <% out.println(session.getAttribute("name")); %></h1>
</body>
</html>
