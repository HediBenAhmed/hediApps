import { Injectable } from '@angularcore';

@Injectable()
export class WebSocketService{

	const RECONNECT_TIMEOUT = 30000;
	const SOCKET_URL = "/hediapps-messaging",
	const CHAT_BROKER = "/app/sendPublicMessage",
	const CHAT_TOPIC = "/topic/messages",
	const USER_BROKER = "/app/sendPrivateMessage",
	const USER_QUEUE = "/user/{id}/messages",
	const SERVER_TOPIC = "/topic/systemMessages",

	var socket : {
		client : null,
		stomp : null
	}

	oldMessages: Message[] = [];
	
	constructor(private http: Http) { 
		this.authApiRoot = getAuthApiRoot(this.authApiName);
	}
	
	
	initialize(user: User){

		this.socket.client = new SockJS(this.SOCKET_URL);
		this.socket.stomp = Stomp.over(this.socket.client);
		this.socket.stomp.connect({"user" : user.email}, function(frame) {
			this.socket.stomp.subscribe(this.SERVER_TOPIC,function(systemMessage) {
				console.log(JSON.stringify(systemMessage));
			});

			this.socket.stomp.subscribe(this.CHAT_TOPIC, responseHandler);

			var thisUserQueue = this.USER_QUEUE.replace("{id}", user.email);
			this.socket.stomp.subscribe(this.thisUserQueue,function(message) {
				console.log(JSON.stringify(message));
			});
		});
		
		this.socket.stomp.onclose = reconnect(user);
	};
	
	reconnect(user: User) {
		$timeout(function() {
			initialize(user);
		}, webSocketService.RECONNECT_TIMEOUT);
	}
	
	responseHandler(response) {
		var newMessage = JSON.parse(response.body)
		this.oldMessages.push(newMessage);

		var currentDate = Date.now();
		// update time
		for (var i = 0; i < this.oldMessages.length; i++) {
			var delai = (currentDate - this.oldMessages[i].creationDate)/ (1000 * 60);
			if (delai >= 60) {
				delai = delai / 60;

				if (delai >= 24) {
					delai = delai / 24;

					if (delai >= 30) {
						delai = delai / 30;

						delai = Math.round(delai) + " months";
					} else {
						delai = Math.round(delai) + " days";
					}
				} else {
					delai = Math.round(delai) + " hours";
				}
			} else {
				delai = Math.round(delai) + " mins";
			}

			this.oldMessages[i].delai = delai;
		}
	}
}