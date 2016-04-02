<%@include file="include.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Usuario</title>
	<script>
		function validarContrasenia(){
			if ($("#contrasenia2").val() != $("#contrasenia").val()){
				$("#errorContrasenia2").html("Las contraseñas deben coincidir");
				return false;
			}
			$("#errorContrasenia2").html("");
			return true;
		}
		
		$( document ).ready(function() {
			$("#contrasenia2").val($("#contrasenia").val());
		});
	</script>
</head>
<body>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<h1>
			Usuario 
		</h1>

		<form:form commandName="usuario" method="POST" class="form-horizontal">
		    <form:hidden path="id" />
			<div class="form-group">
		    	<label for="nombre" class="col-sm-2 control-label">Nombre *</label>
				<div class="col-sm-10">
		            <form:input class="form-control" path="nombre" id="nombre" />
		            <form:errors path="nombre" style="color:#FF0000"/>
				</div>
			</div>
			<div class="form-group">
				<label for="apellido" class="col-sm-2 control-label">Apellido *</label>
				<div class="col-sm-10">
		        	<form:input class="form-control" path="apellido" id="apellido" />
		            <form:errors path="apellido" style="color:#FF0000"/>
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">Email *</label>
				<div class="col-sm-10">
		        	<form:input class="form-control" path="email" id="email" />
		            <form:errors path="email" style="color:#FF0000"/>
				</div>
			</div>
			<c:if test="${sessionScope.usuario.nivel.id == 1}">
				<div class="form-group">
			    	<label for="nivel" class="col-sm-2 control-label">Estado cuenta *</label>
			        <div class="col-sm-4">
						<form:select class="form-control" path="activado" id="nivel">
					    	<form:options items="${estadoCuentaDropDown}" />
					    </form:select>
				    </div>
			    </div>
			</c:if>
			<c:if test="${sessionScope.usuario.nivel.id != 1}">
        		<form:hidden path="activado" />
        	</c:if>
			<c:if test="${sessionScope.usuario.nivel.id == 1}">
					<div class="form-group">
			        	<label for="nivel" class="col-sm-2 control-label">Nivel *</label>
			        	<div class="col-sm-4">
					        <form:select class="form-control" path="nivel.id" id="nivel">
					        	<form:options items="${nivelDropDown}" />
					        </form:select>
				        </div>
			        </div>
		        </c:if>
		        <c:if test="${sessionScope.usuario.nivel.id != 1}">
		        	<form:hidden path="nivel.id" />
		   	</c:if>
			<div class="form-group">
				<label for="contrasenia" class="col-sm-2 control-label">Contraseña *</label>
				<div class="col-sm-10">
		        	<form:input type="password" class="form-control" path="contrasenia" id="contrasenia" />
		            <form:errors path="contrasenia" style="color:#FF0000"/>
				</div>
			</div>
			<div class="form-group">
				<label for="contrasenia2" class="col-sm-2 control-label">Repita contraseña *</label>
				<div class="col-sm-10">
		        	<input type="password" class="form-control" id="contrasenia2" />
		            <span id="errorContrasenia2" style="color:#FF0000"></span>
				</div>
			</div>
			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
	            	<c:if test="${usuario.id == null}">
		            	<input type="submit" name="accion" class="btn btn-success" value="Guardar" onClick="return validarContrasenia()"/>
		            </c:if>
		            <c:if test="${usuario.id != null}">
						<input type="submit" name="accion" class="btn btn-success" value=Modificar onClick="return validarContrasenia()"/>
		            </c:if>
		            <c:if test="${sessionScope.usuario.nivel.id == 1}">
		            	<input type="submit" name="accion" class="btn btn-danger" value="Volver" />
		            </c:if>
				</div>
		    </div>
		  </form:form>
		  <div class="col-sm-offset-2 col-md-8">
		  	<span class="text-success">${accionEjecutada}</span>
		  </div>
	</div>
	<div class="col-md-2"></div>
</div>
</body>
</html>
