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

services.factory("ChatService", function($q, $timeout) {
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [];
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/hediapps/chat";
    service.CHAT_TOPIC = "/topic/message";
    service.CHAT_BROKER = "/app/chat";
    
    service.receive = function() {
      return listener.promise;
    };
    
    service.send = function(message) {
      var id = Math.floor(Math.random() * 1000000);
      socket.stomp.send(service.CHAT_BROKER, {
        priority: 9
      }, JSON.stringify({
        message: message,
        id: id
      }));
      messageIds.push(id);
    };
    
    var reconnect = function() {
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var getMessage = function(data) {
      var message = JSON.parse(data), out = {};
      out.message = message.message;
      out.time = new Date(message.time);
      if (_.contains(messageIds, message.id)) {
        out.self = true;
        messageIds = _.remove(messageIds, message.id);
      }
      return out;
    };
    
    var startListener = function() {
      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
        listener.notify(getMessage(data.body));
      });
    };
    
    var initialize = function() {
      socket.client = new SockJS(service.SOCKET_URL);
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect({}, startListener);
      socket.stomp.onclose = reconnect;
    };
    
    initialize();
    service.send("hello");
    return service;
  });
