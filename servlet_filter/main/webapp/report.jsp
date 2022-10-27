<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Report Information</title>
</head>
<body>
	<jsp:useBean id="report" scope="request"
		class="webcert.ch07.examp0701.ReportBean" />
	
	Nome:<jsp:getProperty name="report" property="user" />
	Password:<jsp:getProperty name="report" property="password" />
	
	<c:forEach var="elem" items="${requestScope.report.weatherType}">
		 <c:out value="${elem}" />
	</c:forEach>
	
	
	<a href="http://localhost:8080/Esercizio/secondoreport.jsp">link</a>
    
</body>
</html>