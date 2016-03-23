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
        </tr>
        <tr>
            <td colspan="1">
            	<c:if test="${gasto.id == null}">
            		<input type="submit" name="accion" value="Guardar" />
            	</c:if>
            	<c:if test="${gasto.id != null}">
            		<input type="submit" name="accion" value=Modificar />
            	</c:if>
            </td>
            <td colspan="1">
              <input type="submit" name="accion" value="Volver" />
            </td>
        </tr>
      </table>
  </form:form>
  <p>${accionEjecutada}</p>
</body>
</html>
