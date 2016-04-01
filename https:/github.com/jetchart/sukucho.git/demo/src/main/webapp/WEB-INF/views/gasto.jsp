<%@include file="include.jsp" %>

<html>
<head>
	<title>Gasto</title>
</head>
<body>
	<div class="row">
	  <div class="col-md-2"></div>
	  <div class="col-md-8">
		<h1>
			Gasto 
		</h1>

		<form:form modelAttribute="gasto" method="POST" class="form-horizontal">
			<form:hidden path="id" />
			<div class="form-group">
				<label for="descripcion" class="col-sm-2 control-label">Descripcion *</label>
				<div class="col-sm-10">
					<form:input class="form-control" id="descripcion" path="descripcion" />
					<form:errors path="descripcion" style="color:#FF0000"/>
				</div>
			</div>
				<form:hidden path="fecha" />
				<form:hidden path="usuario.id" />
				<form:hidden path="periodo.id" />
			<div class="form-group">
				<label for="precio" class="col-sm-2 control-label">Precio *</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="precio" id="precio"/>
		        	<form:errors path="precio" style="color:#FF0000"/>
				</div>
			</div>
			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
		            	<c:if test="${gasto.id == null}">
		            		<input type="submit" name="accion" class="btn btn-success" value="Guardar" />
		            	</c:if>
		            	<c:if test="${gasto.id != null}">
		            		<input type="submit" name="accion" class="btn btn-success" value=Modificar />
		            	</c:if>
		              <input type="submit" name="accion" class="btn btn-danger" value="Volver" />
				</div>
			</div>
		  </form:form>
		  <span style="color:#00FF00"><p>${accionEjecutada}</p></span>
	  </div>
	  <div class="col-md-2"></div>
</div>
</body>
</html>
