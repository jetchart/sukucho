<%@include file="include.jsp" %>

<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>SuKucho</title>
</head>
<body>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
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
		            <td>Contraseņa:</td>
		            <td><input type="password" name="contrasenia" value=""/></td>
		        </tr>
		        <tr>
		        	<td></td>
		        	<td id="errorContrasenia2" style="color:#FF0000">${errorLogin}</td>
		        </tr>
		        <tr>
		            <td colspan="1">
		              <input type="submit" name="accion" class="btn btn-success" value="Ingresar" />
		            </td>
		        </tr>
		      </table>
		  </form:form>
<!-- 		  <a href="./registrarse">Registrarse</a> -->
		</c:if>
	</div>
	<div class="col-md-2"></div>
</div>
</body>
</html>
