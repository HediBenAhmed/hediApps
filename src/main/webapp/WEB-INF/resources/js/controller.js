'use strict';

app.controller('taskCtrl', [
		'$scope',
		'taskService',
		function($scope, taskService) {

			var self = this;
			self.tasks = [];

			self.findAllTasks = function() {
				taskService.findAllTasks().then(
						function(tasks) {

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
				taskService.findData($scope.idData).then(function(d) {
					$scope.dataValue = JSON.stringify(d);
				}, function(errResponse) {
					console.error('Error');
				});
			};
		} ]);

app.controller('dataCtrl', [
		'$scope',
		'dataService',
		function($scope, dataService) {

			var self = this;
			self.datas = [];

			self.findAllDatas = function() {
				return dataService.findAllDatas().then(function(datas) {
					self.datas = datas;
				}, function(errResponse) {
					console.error('Error');
				});

			};

			self.findAllDatas();

			$scope.findData = function() {
				dataService.findData($scope.idData).then(function(d) {
					$scope.dataValue = JSON.stringify(d);
				}, function(errResponse) {
					console.error('Error');
				});
			};
		} ]);

app.controller('loginCtrl', [
		'$scope',
		'$window',
		'loginService',
		function($scope, $window, loginService) {

			$scope.login = function() {
				loginService.login($scope.userName, $scope.pwd).then(
						function(d) {
							$window.location.href = hostApp + '/index.html';
						}, function(errResponse) {
							console.error('Error');
						});
			};

			$scope.logout = function() {
				loginService.logout().then(function(d) {
					$window.location.href = hostApp + '/login.html';
				}, function(errResponse) {
					console.error('Error');
				});
			};
		} ]);