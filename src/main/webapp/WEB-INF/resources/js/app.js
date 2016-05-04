var hostApp = "http://localhost:8080/hediapps";
var app = angular.module('myApp', [ 'ngResource', 'ngRoute', 'ngCookies',
		'graph' ]);

app
		.config([
				'$routeProvider',
				function($routeProvider) {
					$routeProvider
							.when(
									"/",
									{
										templateUrl : "dashboard.html",
										resolve : {
											auth : [
													"$window",
													"loginService",
													"$cookies",
													function($window,
															loginService,
															$cookies) {

														if ($window.location.href
																.indexOf("login.html") == -1) {

															/*
															 * var userToken =
															 * $cookies.get("user");
															 * if(!userToken){
															 * $window.location.href =
															 * hostApp +
															 * '/login.html'; }
															 */

															loginService
																	.checkToken()
																	.then(
																			function(
																					d) {

																				var checkToken = d.valid;

																				if (checkToken) {
																					// return
																					// $q.when(checkToken);
																				} else {
																					// return
																					// $q.reject({
																					// authenticated
																					// :
																					// false
																					// });
																					$window.location.href = hostApp
																							+ '/login.html';
																				}
																			});
														}
													} ]
										}
									});
				} ]);