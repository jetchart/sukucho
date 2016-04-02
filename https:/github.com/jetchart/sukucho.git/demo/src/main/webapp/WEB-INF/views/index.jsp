<%@include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>SuKucho</title>
</head>
<body>
<div class="row">
	<div class="col-md-4"></div>
	<div class="col-md-4">
		<h1>
		<!-- 	SuKucho  -->
		</h1>
		<c:if test="${sessionScope.usuario != null}">
		</c:if>
		<c:if test="${sessionScope.usuario == null}">
		<form:form method="POST" class="form-horizontal">
			<div class="form-group">
				<label for="email" class="col-sm-4 control-label">Email:</label>
				<div class="col-sm-8">
			      <input type="text" name="email" value=""/>
			    </div>
			</div>
			<div class="form-group">
				<label for="contrasenia" class="col-sm-4 control-label">Contraseña:</label>
				<div class="col-sm-8">
			      <input type="password" name="contrasenia" value=""/>
			      <p><span id="errorContrasenia2" style="color:#FF0000">${errorLogin}</span></p>
			    </div>
			</div>
			<div class="form-group">
    			<div class="col-sm-offset-4 col-sm-6">
    				<input type="submit" name="accion" class="btn btn-success" value="Ingresar" />
    			</div>
    		</div>
		  </form:form>
		  <div class="col-sm-offset-4 col-sm-4">
		  	<a href="./registrarse">Registrarse</a>
		  </div>
		</c:if>
	</div>
	<div class="col-md-4"></div>
</div>
</body>
</html>
