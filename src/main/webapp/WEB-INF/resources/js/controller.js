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

app
		.controller(
				'dataCtrl',
				[
						'$scope',
						'dataService',
						function($scope, dataService) {

							var self = this;
							self.datas = [];
							self.colors = [ "rgba(60, 118, 61, 1)",
									"rgba(169, 68, 66, 1)",
									"rgba(49, 112, 143, 1)",
									"rgba(120,120,120,1)" ];

							self.findAllDatas = function() {
								dataService
										.findAllDatas()
										.then(
												function(datas) {

													self.datas = datas;
													var data = {
														labels : [ "January",
																"February",
																"March",
																"April", "May",
																"June", "July" ],
														datasets : []
													};
													for (var i = 0; i < datas.length; i++) {

														var idColor = i
																% self.colors.length;

														var dataset = {
															label : datas[i].name,
															fillColor : "rgba(0,0,0,0)",
															strokeColor : self.colors[idColor],
															pointColor : self.colors[idColor],
															pointStrokeColor : "#fff",
															pointHighlightFill : "#fff",
															pointHighlightStroke : self.colors[idColor],
															data : datas[i].values
														};

														data.datasets[i] = dataset;
													}	new Chart(document.getElementById("linechart").getContext("2d")).Line(data,
															{
														responsive : true,
														maintainAspectRatio : false,
													});
												}, function(errResponse) {
													console.error('Error');
												});
							};

							self.findAllDatas();

							$scope.findData = function() {
								dataService.findData($scope.idData).then(
										function(d) {
											$scope.dataValue = JSON
													.stringify(d);
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