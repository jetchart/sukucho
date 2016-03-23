<%@include file="include.jsp" %>

<html>
<head>
	<title>Gasto</title>
</head>
<body>
<h1>
	Gasto 
</h1>

<P>  Gasto </P>
<form:form modelAttribute="gasto" method="POST">
      <table>
        <tr>
            <td><form:hidden path="id" /></td>
        </tr>
        <tr>
            <td>Descripcion:</td>
            <td><form:input path="descripcion" /></td>
            <td><form:errors path="descripcion" style="color:#FF0000"/></td>
        </tr>
        <tr>
            <td><form:hidden path="fecha" /></td>
        </tr>
        <tr>
            <td><form:hidden path="usuario.id" /></td>
        </tr>
        <tr>
            <td>Precio:</td>
            <td><form:input path="precio" /></td>
            <td><form:errors path="precio" style="color:#FF0000"/></td>
        </tr>
        <tr>
            <td colspan="1">
            	<c:if test="${gasto.id == null}">
            		<input type="submit" name="accion" class="btn btn-success" value="Guardar" />
            	</c:if>
            	<c:if test="${gasto.id != null}">
            		<input type="submit" name="accion" class="btn btn-success" value=Modificar />
            	</c:if>
            </td>
            <td colspan="1">
              <input type="submit" name="accion" class="btn btn-danger" value="Volver" />
            </td>
        </tr>
      </table>
  </form:form>
  <span style="color:#00FF00"><p>${accionEjecutada}</p></span>
</body>
</html>
