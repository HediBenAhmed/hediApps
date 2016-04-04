var hostApp = "http://localhost:8080/hediapps";
var app = angular.module('myApp', [ 'ngResource', 'ngRoute' ]);

app.directive('graph', function() {
	return {
		restrict : 'E',
		scope : {
			datasGraph : '=datas'
		},
		templateUrl : 'graph'
	};
});

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "dashboard.html",
		resolve : {
			auth : [ "$window", "loginService", function($window, loginService) {
				
				if(!$window.location.href.contains("login.html")){
				
				loginService.checkToken().then(function(d) {

					var checkToken = d.valid;

					if (checkToken) {
						//return $q.when(checkToken);
					} else {
						//return $q.reject({
						//	authenticated : false
						//});
						$window.location.href = hostApp + '/login.html';
					}
				});
			}
			} ]
		}
	});
} ]);