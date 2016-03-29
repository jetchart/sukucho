<%@include file="include.jsp" %>

<html>
<head>
	<title>Errors</title>
</head>
<body>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<h1>
			Error 
		</h1>
		
		<P>  Causa: </P>
		${cause}
		<P>  Stacktrace: </P>
		${stackTrace}
	</div>
	<div class="col-md-2"></div>
</div>
</body>
</html>
