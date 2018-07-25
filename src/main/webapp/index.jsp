<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!！!!</h2>

<form action="blog/registe" method="post">
    <input type="text" name="username">
    <input type="text" name="password">
    <input type="text" name="confirmPassword">
    <input type="text" name="email">
    <input type="submit">
</form>
<form action="blog/validate" method="post">
    <input type="text" name="checkcode">
    <input type="submit">
</form>
</body>
</html>
