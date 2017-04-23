'use strict';

var services = angular.module('myApp.services', [ 'ngResource' ]);

services.factory('taskService', function($resource) {

	return $resource('rest/tasks/:id', {
		id : '@id'
	});
});

services.factory('dataService', function($resource) {

	return $resource('rest/datas/:id', {
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

services.factory('userService', function($resource) {

	return $resource('rest/users/:id/:action', {}, {
		getMessages : {
			method : 'GET',
			isArray : true,
			params : {
				'id' : '@id',
				'action' : 'messages'
			}
		}
	});
});

var webSocketService = {
	RECONNECT_TIMEOUT : 30000,
	SOCKET_URL : "/hediapps/hediapps-websocket",
	CHAT_BROKER : "/app/sendPublicMessage",
	CHAT_TOPIC : "/topic/messages",
	USER_BROKER : "/app/sendPrivateMessage",
	USER_QUEUE : "/user/{id}/messages",
	SERVER_TOPIC : "/topic/systemMessages",

	socket : {
		client : null,
		stomp : null
	}
};