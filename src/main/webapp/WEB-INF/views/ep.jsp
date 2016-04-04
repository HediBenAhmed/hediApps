<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
<script src="js/app.js"></script>
<script src="js/service.js"></script>
<script src="js/controller.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<body>

	<div data-ng-app="myApp" data-ng-controller="myCtrl">

		User Name: <input type="text" data-ng-model="userName"><br>
		password: <input type="text" data-ng-model="pwd"><br> <br>

		<button data-ng-click="login()">Login</button>
		<br> <br> Process ID: <input type="text"
			data-ng-model="idArchivedProcess"><br> Data Name: <input
			type="text" data-ng-model="dataName"><br> <br>

		<button data-ng-click="getArchivedDataValue()">Get data</button>
		<br> <br>

		<button data-ng-click="logout()">Logout</button>
		<br> <br> <br> <br>Data id: <input type="text"
			data-ng-model="idData" class="form-control"><br> <br>Data value: <input
			type="text" data-ng-model="dataValue" class="form-control"><br> <br>

		<button data-ng-click="findData()" class="btn btn-default">Get data</button>
		<br> <br>
	</div>


</body>
</html>