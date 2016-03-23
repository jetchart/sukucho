<%@include file="include.jsp" %>
<%@ page import= "com.jetchart.demo.model.CNivel, com.jetchart.demo.model.CPeriodo" %> 
<html>
<body>
	<h2>ABM Gasto</h2>
	
	<form method="POST">
		<table>
			<tr>
			<td colspan="2">Filtro de búsqueda</td>
			</tr>
	        <tr>
            	<td>Mes:</td>
            	<td><input type="text" name="mes" value="${periodo.mes}" size="1"/></td>
        	</tr>
	        <tr>
            	<td>Anio:</td>
            	<td><input type="text" name="anio" value="${periodo.anio}" size="1"/></td>
        	</tr>
        </table>
	<input type="submit" class="btn btn-primary btn-xs" name="accion" value="Buscar" />
	</form>
	<br>
	<form method="POST">
      <c:if test="${not empty gastos}">
      <table>
      	    <tr>
				<td><b>Datos</b></td>
			</tr>
	    	<tr>
				<td>Periodo: ${periodo.mes}/${periodo.anio}</td>
			</tr>
			<tr>
				<c:if test="${estadoPeriodo == CPeriodo.ESTADO_CERRADO}">
					<td>Estado Periodo: <span style="color:#FF0000">${estadoPeriodo}</span></td>
				</c:if>
				<c:if test="${estadoPeriodo != CPeriodo.ESTADO_CERRADO}">
					<td>Estado Periodo: <span style="color:#00FF00">${estadoPeriodo}</span></td>
				</c:if>
			</tr>
	</table>
      	<br>
      	<table class="table">
			<tr>
			<td colspan="5">Gastos del periodo seleccionado</td>
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
	            <c:if test="${estadoPeriodo == 'Cerrado' || (gasto.usuario.id != sessionScope.usuario.id && sessionScope.usuario.nivel.id != 1)}">
	            	<td>Sin acción</td>
	            </c:if>
	            <c:if test="${estadoPeriodo != 'Cerrado' && (gasto.usuario.id == sessionScope.usuario.id || sessionScope.usuario.nivel.id == 1)}">
	            	<td><a href="./abmGasto?accion=Modificar&id=${gasto.id}" >Modificar</a>&nbsp;/&nbsp;<a href="./abmGasto?accion=Eliminar&id=${gasto.id}" >Eliminar</a></td>
	            </c:if>
	        </tr>
	        </c:forEach>
		</table>
		<br>
		 <c:if test="${estadoPeriodo == 'Cerrado'}">
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
			<br>
			<c:if test="${not empty gastos}">
				<table class="table">
					<tr>
						<td colspan="3">
							Gastos por usuario en el periodo seleccionado
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
			</c:if>
		</c:if>
		<c:if test="${estadoPeriodo != 'Cerrado'}">
			<table class="table">
				<tr>
					<td>Total parcial: <span style="color:#00FF00">$${totalPeriodo}</span></td>
				</tr>
			</table>
		</c:if>
      </c:if>
    <br>  
    <c:if test="${estadoPeriodo == 'Vigente'}">
		<input type="submit" name="accion" class="btn btn-success" value="Registrar gasto" />
	</c:if>
	<input type="submit" name="accion" class="btn btn-danger" value="Volver" />
  </form>
</body>
</html>