<%@include file="include.jsp" %>

<html>
<head>
	<title>Registrarse</title>
	<script>
		function validarContrasenia(){
			var contrasenia = document.getElementById("contrasenia").value;
			var contrasenia2 = document.getElementById("contrasenia2").value;
			if (contrasenia != contrasenia2){
				document.getElementById("errorContrasenia2").innerHTML="Las contraseñas deben coincidir";
				return false;
			}
			document.getElementById("errorContrasenia2").innerHTML="";
			return true;
		}
	</script>
</head>
<body>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<h1>
			Registrarse 
		</h1>
		
		<P>  Usuario </P>
		<form:form commandName="usuario" method="POST">
		      <table>
		        <tr>
		            <td><form:hidden path="id" /></td>
		        </tr>
		        <tr>
		            <td>Nombre:</td>
		            <td><form:input path="nombre" /></td>
		            <td><form:errors path="nombre" style="color:#FF0000"/></td>
		        </tr>
		        <tr>
		            <td>Apellido:</td>
		            <td><form:input path="apellido" /></td>
		            <td><form:errors path="apellido" style="color:#FF0000"/></td>
		        </tr>
		        <tr>
		            <td>Email:</td>
		            <td><form:input path="email" /></td>
		            <td><form:errors path="email" style="color:#FF0000"/></td>
		        </tr>
		        <tr>
		        	<td><form:hidden path="nivel.id" /></td>
		        </tr>
		        <tr>
		        	<td><form:hidden path="activado" /></td>
		        </tr>
		        <tr>
		            <td>Contraseña:</td>
		            <td><form:input type="password" path="contrasenia" /></td>
		            <td><form:errors path="contrasenia" style="color:#FF0000"/></td>
		        </tr>
		        <tr>
		            <td>Repita contraseña:</td>
		            <td><input type="password" id="contrasenia2" /></td>
		            <td id="errorContrasenia2" style="color:#FF0000"></td>
		        </tr>
		        <tr>
		        	<c:if test="${usuario.id == null}">
			            <td colspan="1">
		            		<input type="submit" name="accion" class="btn btn-success" value="Registrarse"  onClick="return validarContrasenia()" />
			            </td>
		            </c:if>
		            <td colspan="1">
		              	<input type="submit" name="accion" class="btn btn-danger" value="Volver" />
		            </td>
		        </tr>
		      </table>
		  </form:form>
		  <span style="color:#00FF00"><p>${accionEjecutada}</p></span>
	</div>
	<div class="col-md-2"></div>
</div>
</body>
</html>
