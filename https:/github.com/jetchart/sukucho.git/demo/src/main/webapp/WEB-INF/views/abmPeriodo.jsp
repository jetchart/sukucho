<%@include file="include.jsp" %>
<%@ page import= "com.jetchart.demo.model.CNivel, com.jetchart.demo.model.CPeriodo" %>
<%@ page import="com.jetchart.demo.model.CEstadoPeriodo" %> 
<html>
<body>
	<div class="row">
	  <div class="col-md-2"></div>
	  <div class="col-md-8">
	  		<h2>ABM Gasto</h2>
			<form:form method="POST" modelAttribute="periodoBuscado">
					<div class="table-responsive">
						<table>
							<tr>
							<td colspan="2"><b>Filtro de búsqueda</b></td>
							</tr>
					        <tr>
				            	<td>Mes:</td>
				            	<td><form:select path="mes" items="${mesDropDown}" /></td>
				        	</tr>
					        <tr>
				            	<td>Año:</td>
				            	<td><form:select path="anio" items="${anioDropDown}" /></td>
				        	</tr>
				        </table>
			        </div>
				<input type="submit" class="btn btn-primary btn-xs" name="accion" value="Buscar" />
				</form:form>
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