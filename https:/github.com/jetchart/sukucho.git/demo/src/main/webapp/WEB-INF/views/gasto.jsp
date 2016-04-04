<%@include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Gasto</title>
<script>
function resaltarErrores(){
    $('.has-feedback').each(function(indice, elemento) {
    	if ($(elemento).children('div').children('.error').text() != ""){
    		$(elemento).addClass("has-error");
    		$(elemento).children('div').children('span').show();
    	}else{
    		$(elemento).removeClass("has-error");
    		$(elemento).children('div').children('span').hide();
    	}
    });
} 
		
		$( document ).ready(function() {
			resaltarErrores();
		});
	</script>
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
			<div class="form-group  has-feedback">
				<label for="descripcion" class="col-sm-2 control-label">Descripcion *</label>
				<div class="col-sm-10">
					<form:input class="form-control" id="descripcion" path="descripcion" />
					<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
					<form:errors class="error" path="descripcion" style="color:#FF0000"/>
				</div>
			</div>
				<form:hidden path="fecha" />
				<form:hidden path="usuario.id" />
				<form:hidden path="periodo.id" />
			<div class="form-group  has-feedback">
				<label for="precio" class="col-sm-2 control-label">Precio *</label>
				<div class="col-sm-10">
					<div class="input-group">
						<div class="input-group-addon">$</div>
						<form:input class="form-control" path="precio" id="precio"/>
		        	</div>
		        	<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
		        	<form:errors class="error" path="precio" style="color:#FF0000"/>
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
		  <div class="col-sm-offset-2 col-md-8">
		  	<span class="text-success">${accionEjecutada}</span>
		  </div>
	  </div>
	  <div class="col-md-2"></div>
</div>
</body>
</html>
