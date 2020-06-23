<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <title>GuestBook</title>
  </head>
  <body>
<h1>Comments</h1>
<img src=\"https://www.motherjones.com/wp-content/uploads/2019/05/20190529_Comments_2002.png?resize=630,354\">
<br><br>


<p>
<c:forEach items="${messages}" var ="message">
    <p> style="font-family: fantasy" ${messages.date}</p>
    <p> style="font-family: Bahnschrift"> name: ${messages.name}</p>
    <p> style="font-family: Consolas">message: ${message.message}</p>
  </c:forEach>
</p>
<hr>
    <div>
   <form method='post' action=''>
   <p> please leave us your comments...</p><br>
   name: <input type="text" name="name" required><br>
   e-mail:<input type="email" name="e-mail" required><br><br>
   <textarea name="message"  cols="30" rows="10"></textarea>
   <button type="submit">Send!</button>
    </form>
    </div>
    </body>
   </html>
