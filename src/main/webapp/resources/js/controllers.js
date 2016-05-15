'use strict';

app.controller('taskCtrl', [
		'$scope',
		'taskService',
		function($scope, taskService) {

			var self = this;
			self.tasks = [];

			self.findAllTasks = function() {
				taskService.query(function(tasks) {

					var currentDate = new Date();

					for (var i = 0; i < tasks.length; i++) {
						var taskDate = new Date(tasks[i].taskDate);
						tasks[i].dayRemain = Math
								.floor((taskDate - currentDate)
										/ (1000 * 60 * 60 * 24));
					}

					self.tasks = tasks;

				}, function(errResponse) {
					console.error('Error');
				});
			};

			self.findAllTasks();

			$scope.findTask = function() {
				taskService.get({
					id : $scope.idData
				}, function(d) {
					$scope.dataValue = JSON.stringify(d);
				}, function(errResponse) {
					console.error('Error');
				});
			};
		} ]);

app.controller('dataCtrl', [ '$scope', 'dataService',
		function($scope, dataService) {

			var self = this;
			self.datas = [];

			self.findAllDatas = function() {
				dataService.query(function(datas) {

					self.datas = datas;
				}, function(errResponse) {
					console.error('Error');
				});

			};

			self.findAllDatas();

			$scope.findData = function() {
				dataService.get({
					id : $scope.idData
				}, function(d) {
					$scope.dataValue = JSON.stringify(d);
				}, function(errResponse) {
					console.error('Error');
				});
			};
		} ]);

app.controller('loginCtrl', [
		'$scope',
		'$rootScope',
		'$location',
		'loginService',
		'$cookies',
		function($scope, $rootScope, $location, loginService, $cookies) {

			$scope.login = function() {
				loginService.authenticate($.param({
					username : $scope.username,
					password : $scope.password
				}), function(authenticationResult) {

					var authToken = authenticationResult.token;
					$rootScope.authToken = authToken;

					if ($scope.rememberMe) {
						var now = new Date();
						// this will set the expiration to 12 months
						var exp = new Date(now.getFullYear() + 1, now
								.getMonth(), now.getDate());

						$cookies.put('authToken', authToken, {
							expires : exp
						});
					} else {
						$cookies.put('authToken', authToken);
					}
					
					loginService.getCurrent(function(user) {
						$rootScope.user = user;
						$scope.name = user.name;
						$location.path("/");
					});
				});

			};
			
			$scope.logout = function() {
				delete $rootScope.user;
				delete $rootScope.authToken;
				$cookies.remove('authToken');
				$location.path("/login");
			};
			
			$scope.currentUser = function() {
				return $rootScope.user;
			};
		} ]);