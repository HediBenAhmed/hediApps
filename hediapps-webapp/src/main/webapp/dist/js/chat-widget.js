angular
		.module('chatwdg', [ 'ngRoute' ])
		.directive(
				'chatwdg',
				function($rootScope) {

					var controllers = [
							'$scope',
							function($scope) {

								$scope.textMsg = "";
								$scope.oldMessages = [];

								var reconnect = function() {
									$timeout(function() {
										initialize();
									}, webSocketService.RECONNECT_TIMEOUT);
								};

								var responseHandler = function(message) {
									$scope.oldMessages.push(JSON
											.parse(message.body));
								}

								var initialize = function() {
									webSocketService.socket.client = new SockJS(
											webSocketService.SOCKET_URL);
									webSocketService.socket.stomp = Stomp
											.over(webSocketService.socket.client);

									webSocketService.socket.stomp
											.connect(
													{
														"user" : $rootScope.user.email
													},
													function(frame) {

														webSocketService.socket.stomp
																.subscribe(
																		webSocketService.SERVER_TOPIC,
																		function(
																				systemMessage) {
																			console
																					.log(JSON
																							.stringify(systemMessage));
																		});

														webSocketService.socket.stomp
																.subscribe(
																		webSocketService.CHAT_TOPIC,
																		responseHandler);

														webSocketService.USER_QUEUE = webSocketService.USER_QUEUE
																.replace(
																		"{id}",
																		$rootScope.user.email);
														webSocketService.socket.stomp
																.subscribe(
																		webSocketService.USER_QUEUE,
																		function(
																				message) {
																			console
																					.log(JSON
																							.stringify(message));
																		});
													});
									webSocketService.socket.stomp.onclose = reconnect;
								};

								initialize();

								$scope.sendMessage = function() {
									var message = {
										"destination" : webSocketService.CHAT_TOPIC,
										"text" : $scope.textMsg,
										"fromUser" : $rootScope.user
									};

									webSocketService.socket.stomp.send(
											webSocketService.CHAT_BROKER, {},
											JSON.stringify(message));

									$scope.textMsg = "";
								};
							} ];

					return {
						restrict : 'E',
						templateUrl : 'partials/chat-widget.html',
						controller : controllers,

						link : function link(scope, element, attrs) {
						}
					};
				});
