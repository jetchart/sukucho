<%@include file="include.jsp" %>

<html>
<head>
	<title>Usuario</title>
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
        	<form:hidden path="activado" />
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
		        	<form:input class="form-control" path="contrasenia" id="contrasenia" />
		            <form:errors path="contrasenia" style="color:#FF0000"/>
				</div>
			</div>
			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
	            	<c:if test="${usuario.id == null}">
		            	<input type="submit" name="accion" class="btn btn-success" value="Guardar" />
		            </c:if>
		            <c:if test="${usuario.id != null}">
						<input type="submit" name="accion" class="btn btn-success" value=Modificar />
		            </c:if>
		            <c:if test="${sessionScope.usuario.nivel.id == 1}">
		            	<input type="submit" name="accion" class="btn btn-danger" value="Volver" />
		            </c:if>
				</div>
		    </div>
		  </form:form>
		  <span style="color:#00FF00"><p>${accionEjecutada}</p></span>
	</div>
	<div class="col-md-2"></div>
</div>
</body>
</html>
