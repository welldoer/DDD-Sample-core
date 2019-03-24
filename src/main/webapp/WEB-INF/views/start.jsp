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
  <div id="container">
    <div id="search">
      <h1>Search for Your Cargo</h1>
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
    </div>
  </div>
  
  <c:choose>
    <c:when test="${cargo ne null}">
      <div id="result">
        <h2>Your cargo is currently at: ${cargo.currentLocation}</h2>
        <h3>Tracking History</h3>
        <table cellspacing="4">
          <thead>
	        <tr>
	          <td>Event</td>
	          <td>Location</td>
	          <td>Time</td>
	        </tr>
	      </thead>
	      </tbody>
            <c:forEach var="event" items="${cargo.deliveryHistory.events()}">
            <tr class="event-type-${event.type}">
              <td>${event.type}</td>
              <td>${event.location}</td>
              <td>${event.time}</td>
            </tr>
          </c:forEach>
        </table>
        
      </div>
    </c:when>
    <c:when test="${trackingId ne null}">
      <p>Unknown cargo id: ${trackingId} !</p>
    </c:when>
    <c:otherwise>
      <p>Welcome, pls input cargo id!</p>
    </c:otherwise>
  </c:choose>
</body>
</html>