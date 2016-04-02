<%@include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Errors</title>
</head>
<body>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<h1>
			Error 
		</h1>
		
		<P>  <b>Causa:</b> </P>
		${cause}
		<P>  <b>Stacktrace:</b> </P>
		${stackTrace}
	</div>
	<div class="col-md-2"></div>
</div>
</body>
</html>
