<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<meta charset="UTF-8">
<title>Notes</title>
</head>
<body>
    
  <jsp:useBean id="dao" class="mynote.DAO"/>
    <select>
    <c:forEach var="note" items="${dao.getLista()}" varStatus="id">
      <option value="${note.getIdNote()}"> ${note.getIdNote()}</option>

    </c:forEach>
    </select>
<input type="submit" value="Submit">
</body>
</html> 