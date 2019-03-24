<%@ page contentType="text/html; carset=ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
  <script type="text/javascript"></script>
  <style type="text/css" title="style" media="screen">
    @import "static/css/style.css";
  </style>
  <title>Start</title>
</head>
<body>
  <mvc:form modelAttribute="trackCommand" action="result">
    <table cellspacing="0" cellpadding="4">
      <tr>
        <td><mvc:label path="trackingId">Tracking Id</mvc:label></td>
        <td><mvc:input path="trackingId" /></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="Track!" />
        </td>
      </tr>
    </table>
  </mvc:form>
  
  <div id="result">
    <c:choose>
      <c:when test="${cargo ne null}">
        <p>Your cargo is currently at: <span id="currentLocation">${cargo.currentLocation}</span></p>
        
        <table>
          <c:forEach var="event" items="${cargo.deliveryHistory.events()}">
            <tr><td><c:out value="${event.type}"/> &nbsp; on &nbsp;</td><td><c:out value="${event.location}"/>&nbsp; at &nbsp;</td><td><c:out value="${event.time}"/></td></tr>
          </c:forEach>
        </table>
        
      </c:when>
      <c:when test="${trackingId ne null}">
        <p>Unknown cargo id: ${trackingId} !</p>
	  </c:when>
      <c:otherwise>
        <p>Welcome, pls input cargo id!</p>
	  </c:otherwise>
    </c:choose>
  </div>
</body>
</html>