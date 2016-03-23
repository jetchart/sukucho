<%@include file="include.jsp" %>

<html>
<body>
	<h2>ABM Gasto</h2>
	
	<form method="POST">
		<table border="1">
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
	<input type="submit" name="accion" value="Buscar" />
	</form>

	<form method="POST">
      <c:if test="${not empty gastos}">
      <table>
      	    <tr>
				<td>Datos:</td>
			</tr>
	    	<tr>
				<td>Periodo: ${periodo.mes}/${periodo.anio}</td>
			</tr>
			<tr>
				<c:if test="${estadoPeriodo == 'Cerrado'}">
					<td>Estado Periodo: <span style="color:#FF0000">${estadoPeriodo}</span></td>
				</c:if>
				<c:if test="${estadoPeriodo != 'Cerrado'}">
					<td>Estado Periodo: <span style="color:#00FF00">${estadoPeriodo}</span></td>
				</c:if>
			</tr>
		</table>
      	<br>
      	<table border="1">
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
	            <c:if test="${estadoPeriodo == 'Cerrado' || gasto.usuario.id != sessionScope.usuario.id}">
	            	<td>Sin acción</td>
	            </c:if>
	            <c:if test="${estadoPeriodo != 'Cerrado' && gasto.usuario.id == sessionScope.usuario.id}">
	            	<td><a href="./abmGasto?accion=Modificar&id=${gasto.id}" >Modificar</a>&nbsp;/&nbsp;<a href="./abmGasto?accion=Eliminar&id=${gasto.id}" >Eliminar</a></td>
	            </c:if>
	        </tr>
	        </c:forEach>
		</table>
		<br>
		 <c:if test="${estadoPeriodo == 'Cerrado'}">
			<table>
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
		</c:if>
		<c:if test="${estadoPeriodo != 'Cerrado'}">
			<table>
				<tr>
					<td>Total parcial: <span style="color:#00FF00">$${totalPeriodo}</span></td>
				</tr>
			</table>
		</c:if>
      </c:if>
    <br>  
    <c:if test="${estadoPeriodo == 'Vigente'}">
		<input type="submit" name="accion" value="Crear" />
	</c:if>
	<input type="submit" name="accion" value="Volver" />
  </form>
</body>
</html>