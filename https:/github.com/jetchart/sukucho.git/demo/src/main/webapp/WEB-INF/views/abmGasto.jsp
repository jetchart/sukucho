<%@include file="include.jsp" %>
<%@ page import= "com.jetchart.demo.model.CNivel, com.jetchart.demo.model.CPeriodo" %>
<%@ page import="com.jetchart.demo.model.CEstadoPeriodo" %> 
<html>
<body>
	<div class="row">
	  <div class="col-md-2"></div>
	  <div class="col-md-8">
	  		<h2>ABM Gasto</h2>
				<form:form method="POST" class="form-horizontal" modelAttribute="periodoBuscado">
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
			      <c:if test="${not empty gastos}">
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
							<tr>
								<td><a href="./abmPeriodo?mes=${periodo.mes}&anio=${periodo.anio}">Ver usuarios</a></td>
							</tr>
					</table>
				</div>
			      	<br>
			      	<div class="table-responsive"> 
				      	<table class="table table-hover">
							<tr class="success">
							<td colspan="5"><b>Gastos del periodo seleccionado</b></td>
							</tr>
				      		<tr>
					      		<th>Descripcion</th>
					      		<th>Fecha</th>
					      		<th>Usuario</th>
					      		<th>Precio</th>
					      		<th>Accion</th>
				      		</tr>
					      	<c:forEach var="gasto" items="${gastos}">
					        <tr>
					            <td>${gasto.descripcion}</td>
					            <td>${gasto.fecha}</td>
					            <td>${gasto.usuario.nombre} ${gasto.usuario.apellido}</td>
					            <td>$${gasto.precio}</td>
					            <c:if test="${periodo.estadoPeriodo.id != ID_VIGENTE || (gasto.usuario.id != sessionScope.usuario.id && sessionScope.usuario.nivel.id != 1)}">
					            	<td>Sin acción</td>
					            </c:if>
					            <c:if test="${periodo.estadoPeriodo.id == ID_VIGENTE && (gasto.usuario.id == sessionScope.usuario.id || sessionScope.usuario.nivel.id == 1)}">
					            	<td><a href="./abmGasto?accion=Modificar&id=${gasto.id}" >Modificar</a>&nbsp;/&nbsp;<a href="./abmGasto?accion=Eliminar&id=${gasto.id}" >Eliminar</a></td>
					            </c:if>
					        </tr>
					        </c:forEach>
						</table>
					</div>
					<br>
					 <c:if test="${periodo.estadoPeriodo.id != ID_VIGENTE}">
					 	<div class="table-responsive">
							<table class="table">
								<tr>
									<td>Total gastado: <span style="color:#FF0000">$${totalPeriodo}</span></td>
								</tr>
								<tr>
									<td>Personas: <span style="color:#FF0000">${cantidadPersonas}</span></td>
								</tr>
								<tr>
									<td>Monto por Persona: <span style="color:#FF0000">$${montoPorPersona}</span></td>
								</tr>
							</table>
						</div>
						<br>
						<c:if test="${not empty gastos}">
							<div class="table-responsive">
								<table class="table table-hover">
									<tr class="success">
										<td colspan="3">
											<b>Gastos por usuario en el periodo seleccionado</b>
										</td>
									</tr>
									<tr>
										<th>
											Usuario
										</th>
										<th>
											Gastó
										</th>
										<th>
											Debe
										</th>
									</tr>
									<c:forEach var="personaGasto" items="${personaGastos}">
									<tr>
										<td>
											${personaGasto[0]}
										</td>
										<td>
											$${personaGasto[1]}
										</td>
										<td>
											<c:if test="${(montoPorPersona - personaGasto[1]) > 0}">
												$${montoPorPersona - personaGasto[1]}
											</c:if>
											<c:if test="${(montoPorPersona - personaGasto[1]) <= 0}">
												No debe
											</c:if>
											
										</td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</c:if>
					</c:if>
					<c:if test="${periodo.estadoPeriodo.id == ID_VIGENTE}">
						<div class="table-responsive">
							<table class="table">
								<tr>
									<td>Total parcial: <span style="color:#00FF00">$${totalPeriodo}</span></td>
								</tr>
							</table>
						</div>
					</c:if>
			      </c:if>
			      <c:if test="${empty gastos}">
			      	<p><span style="color:#FF0000">No se registran gastos para el periodo seleccionado</span></p>
			      </c:if>
			    <br>  
			    <c:if test="${periodo.estadoPeriodo.id == ID_VIGENTE}">
					<input type="submit" name="accion" class="btn btn-success" value="Registrar gasto" />
				</c:if>
				<input type="submit" name="accion" class="btn btn-danger" value="Volver" />
			  </form>
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