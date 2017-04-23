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

services.factory("messageService", function($q, $timeout, $rootScope) {
	var service = {};
	var socket = {
		client : null,
		stomp : null
	};

	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = "/hediapps/hediapps-websocket";

	service.CHAT_BROKER = "/app/sendPublicMessage";
	service.CHAT_TOPIC = "/topic/messages";

	service.USER_BROKER = "/app/sendPrivateMessage";
	service.USER_QUEUE = "/user/" + $rootScope.user.email + "/messages";

	service.SERVER_TOPIC = "/topic/systemMessages";

	service.send = function(uriBroker, message) {
		message.fromUser = $rootScope.user;
		socket.stomp.send(uriBroker, {}, JSON.stringify(message));
	};

	var reconnect = function() {
		$timeout(function() {
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};

	var initialize = function() {
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);

		socket.stomp.connect({
			"user" : $rootScope.user.email
		}, function(frame) {

			socket.stomp.subscribe(service.SERVER_TOPIC,
					function(systemMessage) {
						console.log(JSON.stringify(systemMessage));
					});

			socket.stomp.subscribe(service.CHAT_TOPIC, function(message) {
				// console.log(JSON.parse(message.body));
			});

			socket.stomp.subscribe(service.USER_QUEUE, function(message) {
				console.log(JSON.stringify(message));
			});
		});
		socket.stomp.onclose = reconnect;
	};

	initialize();

	return service;
});
