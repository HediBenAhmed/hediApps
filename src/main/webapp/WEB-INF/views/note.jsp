<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Note Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<h1>
    Add a Note
</h1>
 
<c:url var="addAction" value="/note/create" ></c:url>
 
<form:form action="${addAction}" commandName="note">
<table>

    <tr>
        <td>
            <form:label path="id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="id" size="8"/>
        </td> 
    </tr>
    <tr>
        <td>
            <form:label path="comment">
                <spring:message text="comment"/>
            </form:label>
        </td>
        <td>
            <form:input path="comment" />
        </td> 
    </tr>
    <tr>
        <td>
            <form:label path="createdDate">
                <spring:message text="createdDate"/>
            </form:label>
        </td>
        <td>
            <form:input path="createdDate" />
        </td>
    </tr>
    
    <tr>
        <td colspan="2">
            <c:if test="${!empty note.comment}">
                <input type="submit"
                    value="<spring:message text="Edit Note"/>" />
            </c:if>
            <c:if test="${empty note.comment}">
                <input type="submit"
                    value="<spring:message text="Add note"/>" />
            </c:if>
        </td>
    </tr>
</table>  
</form:form>
<br>
<h3>Notes List</h3>
<c:if test="${!empty listNotes}">
    <table class="tg">
    <tr>
        <th width="80">note ID</th>
        <th width="120">note comment</th>
        <th width="120">note date</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listNotes}" var="note">
        <tr>
            <td>${note.id}</td>
            <td>${note.comment}</td>
            <td>${note.createdDate}</td>
            <td><a href="<c:url value='/edit/${note.id}' />" >Edit</a></td>
            <td><a href="<c:url value='/remove/${note.id}' />" >Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</c:if>
</body>
</html>