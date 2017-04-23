var app = angular.module('myApp', [ 'ngRoute', 'ngCookies', 'luegg.directives',
		'myApp.services', 'chartwdg', 'tablewdg', 'chatwdg' ]);
app
		.config(
				[
						'$routeProvider',
						'$locationProvider',
						'$httpProvider',
						function($routeProvider, $locationProvider,
								$httpProvider) {

							$routeProvider.when('/dashboard', {
								templateUrl : 'pages/index.html'
							});

							$routeProvider.when('/login', {
								templateUrl : 'pages/login.html'
							});

							$routeProvider.otherwise({
								templateUrl : 'pages/index.html'
							});

							// $locationProvider.hashPrefix('!');

							/*
							 * Register error provider that shows message on
							 * failed requests or redirects to login page on
							 * unauthenticated requests
							 */
							$httpProvider.interceptors.push(function($q,
									$rootScope, $location) {
								return {
									'responseError' : function(rejection) {
										var status = rejection.status;
										var config = rejection.config;
										var method = config.method;
										var url = config.url;

										if (status == 401) {
											$location.path("/login");
										} else {
											console.error(method + " on " + url
													+ " failed with status "
													+ status);
										}

										return $q.reject(rejection);
									}
								};
							});

							/*
							 * Registers auth token interceptor, auth token is
							 * either passed by header or by query parameter as
							 * soon as there is an authenticated user
							 */
							$httpProvider.interceptors
									.push(function($q, $rootScope, $location) {
										return {
											'request' : function(config) {
												var isRestCall = config.url
														.indexOf('rest') == 0;
												if (isRestCall
														&& angular
																.isDefined($rootScope.authToken)) {
													var authToken = $rootScope.authToken;
													// if
													// (exampleAppConfig.useAuthTokenHeader)
													// {
													config.headers['X-Auth-Token'] = authToken;
													// } else {
													// config.url = config.url
													// + "?token="
													// + authToken;
													// }
												}
												return config
														|| $q.when(config);
											}
										};
									});

						} ]

		).run(function($rootScope, $location, $cookies, loginService) {

			/* Reset error when a new view is loaded */
			$rootScope.$on('$viewContentLoaded', function() {
				delete $rootScope.error;
			});

			$rootScope.hasRole = function(role) {

				if ($rootScope.user === undefined) {
					return false;
				}

				if ($rootScope.user.roles[role] === undefined) {
					return false;
				}

				return $rootScope.user.roles[role];
			};

			/* Try getting valid user from cookie or go to login page */
			var originalPath = $location.path();

			$location.path("/login");
			var authToken = $cookies.get('authToken');
			if (authToken !== undefined) {
				$rootScope.authToken = authToken;
				loginService.getCurrent(function(user) {
					$rootScope.user = user;

					$location.path(originalPath);
				});
			}

			$rootScope.initialized = true;
		});