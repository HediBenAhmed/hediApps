'use strict';

var services = angular.module('myApp.loginService', [ 'ngResource' ]);

services.factory('taskService', function($resource) {

	return $resource('rest/task/:id', {
		id : '@id'
	});
});

services.factory('dataService', function($resource) {

	return $resource('rest/data/:id', {
		id : '@id'
	});
});

services.factory('loginService', function($resource) {

	return $resource('rest/loginService/:action', {}, {
		authenticate : {
			method : 'POST',
			params : {
				'action' : 'authenticate'
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		},
		getCurrent : {
			method : 'GET',
			params : {
				'action' : 'current'
			}
		}
	});
});
