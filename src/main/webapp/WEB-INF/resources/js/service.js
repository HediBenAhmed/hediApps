'use strict';

var taskServiceURL = hostApp + '/taskService';

app.factory('taskService', [
		'$http',
		'$q',
		function($http, $q) {
			return {

				findTask : function(id) {
					return $http.get(taskServiceURL + '/' + id)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching tasks');
								return $q.reject(errResponse);
							});
				},
				findAllTasks : function(id) {
					return $http.get(taskServiceURL + '/all').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching tasks');
								return $q.reject(errResponse);
							});
				}
			};

		} ]);

var dataServiceURL = hostApp + '/dataService';

app.factory('dataService', [
		'$http',
		'$q',
		function($http, $q) {
			return {

				findData : function(id) {
					return $http.get(dataServiceURL + '/' + id)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching tasks');
								return $q.reject(errResponse);
							});
				},
				findAllDatas : function(id) {
					return $http.get(dataServiceURL + '/all').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching tasks');
								return $q.reject(errResponse);
							});
				}
			};

		} ]);

var loginServiceURL = hostApp + '/loginService';

app.factory('loginService', [
		'$http',
		'$q',
		function($http, $q) {
			
			return {

				login : function(userName, pwd) {
					return $http.get(loginServiceURL + '/login?userName='+ userName + '&pwd=' + pwd)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while login');
								return $q.reject(errResponse);
							});
				},
				logout : function() {
					return $http.get(loginServiceURL + '/logout').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while logout');
								return $q.reject(errResponse);
							});
				},
				checkToken : function() {
					return $http.get(loginServiceURL + '/checkToken').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while checkToken');
								return $q.reject(errResponse);
							});
				}
			};

		} ]);