<%@include file="include.jsp" %>
<%@ page import= "com.jetchart.demo.model.CNivel, com.jetchart.demo.model.CPeriodo" %>
<%@ page import="com.jetchart.demo.model.CEstadoPeriodo" %> 
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ABM Periodo</title>
</head>
<body>
	<div class="row">
	  <div class="col-md-2"></div>
	  <div class="col-md-8">
	  		<h2>ABM Periodo</h2>
	  		<br>
				<form:form method="POST" class="form-horizontal" modelAttribute="periodoBuscado">
					<div class="form-group">
						<label class="col-sm-6 bg-primary">Filtro de búsqueda</label>
					</div>
					<div class="form-group">
						<label for="mes" class="col-sm-2 control-label">Mes</label>
						<div class="col-sm-4">
				    		<form:select class="form-control input-sm" path="mes" id="mes" items="${mesDropDown}" onchange="$('#btnBuscar').click()"/>
				    	</div>
				    </div>
					<div class="form-group">
						<label for="anio" class="col-sm-2 control-label">Año</label>
						<div class="col-sm-4">
				    		<form:select class="form-control input-sm" path="anio" id="anio" items="${anioDropDown}" onchange="$('#btnBuscar').click()"/>
				    	</div>
				    </div>
				    <div class="form-group">
    					<div class="col-sm-offset-2 col-sm-2">
							<input type="submit" class="btn btn-primary btn-xs" name="accion" id="btnBuscar" value="Buscar" />
						</div>
					</div>
				</form:form>
				<br>
				<p class="bg-success">&nbsp;</p>
				<br>
				<c:if test="${periodo != null}">
				<form method="POST">
					<div class="table-responsive">
					      <table>
					      	    <tr>
									<td><b>Datos</b></td>
								</tr>
						    	<tr>
									<td>Periodo: ${periodo.mes}/${periodo.anio}</td>
								</tr>
								<tr>
									<c:if test="${periodo.estadoPeriodo.id != ID_VIGENTE}">
										<td>Estado Periodo: <span style="color:#FF0000">${periodo.estadoPeriodo.descripcion}</span></td>
									</c:if>
									<c:if test="${periodo.estadoPeriodo.id == ID_VIGENTE}">
										<td>Estado Periodo: <span style="color:#00FF00">${periodo.estadoPeriodo.descripcion}</span></td>
									</c:if>
								</tr>
						</table>
					</div>	
				<br>
				<div class="table-responsive">
					<table class="table table-hover">
						<tr class="success">
						<td colspan="5"><b>Usuarios que participan del periodo</b></td>
						</tr>
				     	<tr>
					  		<th>Nombre</th>
					   		<th>Apellido</th>
				     	</tr>
					   	<c:forEach var="usuario" items="${usuariosPeriodo}">
					    	<tr>
					    		<td>${usuario.nombre}</td>
					    		<td>${usuario.apellido}</td>
					    	</tr>
					    </c:forEach>
					</table>
				</div>	
				<br>
				<input type="submit" name="accion" class="btn btn-danger" value="Volver" />
			  </c:if>
			  <c:if test="${periodo == null}">
			  	<p><span style="color:#FF0000">No existe periodo</span></p>
			  	<br>
			  </c:if>
	  </div>
	  <div class="col-md-2"></div>
</div>
</body>
</html>