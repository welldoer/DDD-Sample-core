<%@ page contentType="text/html; carset=ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html>
<head>
  <title>Start</title>
</head>
<body>
  <mvc:form modelAttribute="trackCommand" action="result">
    <table border="0" cellspacing="0" cellpadding="4">
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
</body>
</html>