<%@include file="include.jsp" %>

<html>
<head>
	<title>SuKucho</title>
</head>
<body>
<h1>
<!-- 	SuKucho  -->
</h1>
<c:if test="${sessionScope.usuario != null}">
</c:if>
<c:if test="${sessionScope.usuario == null}">
<form:form method="POST">
      <table>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value=""/></td>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><input type="password" name="contrasenia" value=""/></td>
        </tr>
        <tr>
            <td colspan="1">
              <input type="submit" name="accion" value="Ingresar" />
            </td>
        </tr>
      </table>
  </form:form>
</c:if>

</body>
</html>
