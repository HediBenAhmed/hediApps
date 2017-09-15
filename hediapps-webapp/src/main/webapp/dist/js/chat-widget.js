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

								var responseHandler = function(response) {
																		
									$scope
											.$apply(function() {
												
												var newMessage = JSON
														.parse(response.body)
												$scope.oldMessages
														.push(newMessage);

												var currentDate = Date.now();
												// update time
												for (var i = 0; i < $scope.oldMessages.length; i++) {
													var delai = (currentDate - $scope.oldMessages[i].creationDate)
															/ (1000 * 60);

													if (delai >= 60) {
														delai = delai / 60;

														if (delai >= 24) {
															delai = delai / 24;

															if (delai >= 30) {
																delai = delai / 30;

																delai = Math
																		.round(delai)
																		+ " months";
															} else {
																delai = Math
																		.round(delai)
																		+ " days";
															}
														} else {
															delai = Math
																	.round(delai)
																	+ " hours";
														}
													} else {
														delai = Math
																.round(delai)
																+ " mins";
													}

													$scope.oldMessages[i].delai = delai;
												}
											});
								}

								var initialize = function() {

									if (webSocketService.socket.client == null
											|| webSocketService.socket.stomp == null) {
										webSocketService.socket.client = new SockJS(
												webSocketService.SOCKET_URL);
										webSocketService.socket.stomp = Stomp
												.over(webSocketService.socket.client);
									}

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
