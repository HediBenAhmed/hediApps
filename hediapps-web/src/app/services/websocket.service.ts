import {User} from '../model/user';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {StompService} from '@stomp/ng2-stompjs';
import {Observable} from 'rxjs/Observable';
import {Message} from '@stomp/stompjs';

import {Subscription} from 'rxjs/Subscription';

const CHAT_BROKER = '/app/sendPublicMessage';
const CHAT_TOPIC = '/topic/messages';
const USER_BROKER = '/app/sendPrivateMessage';
const USER_QUEUE = '/user/{id}/messages';
const SERVER_TOPIC = '/topic/systemMessages';

@Injectable()
export class WebSocketService {

  private authApiName = 'hediapps-messaging';
  private authApiRoot = null;

  // Stream of messages
  private subscription: Subscription;
  public messages: Observable<Message>;

  // Subscription status
  public subscribed: boolean;

  // Array of historic message (bodies)
  public mq: Array<string> = [];

  // A count of messages received
  public count = 0;
  private _counter = 1;

  constructor(private http: Http, private stompService: StompService) {
    this.authApiRoot = this.getAuthApiRoot(this.authApiName);

    this.subscribed = false;
  }

  getAuthApiRoot(appID: string): string {
    return 'http://client:secret@localhost:8081';
  }

  public subscribe(user: User) {
    if (this.subscribed) {
      return;
    }

    const thisUserQueue = USER_QUEUE.replace('{id}', user.email);

    // Stream of messages
    this.messages = this.stompService.subscribe(thisUserQueue);

    // Subscribe a function to be run on_next message
    this.subscription = this.messages.subscribe(this.on_next);

    this.subscribed = true;
  }

  public unsubscribe() {
    if (!this.subscribed) {
      return;
    }

    // This will internally unsubscribe from Stomp Broker
    // There are two subscriptions - one created explicitly, the other created in the template by use of 'async'
    this.subscription.unsubscribe();
    this.subscription = null;
    this.messages = null;

    this.subscribed = false;
  }

  public onSendMessage() {
    const _getRandomInt = (min, max) => {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    };
    this.stompService.publish('/topic/ng-demo-sub',
      `{ type: "Test Message", data: [ ${this._counter}, ${_getRandomInt(1, 100)}, ${_getRandomInt(1, 100)}] }`);

    this._counter++;
  }

  /** Consume a message from the _stompService */
  public on_next = (message: Message) => {

    // Store message in "historic messages" queue
    this.mq.push(message.body + '\n');

    // Count it
    this.count++;

    // Log it to the console
    console.log(message);
  }

//  responseHandler(response) {
//    var newMessage = JSON.parse(response.body)
//    this.oldMessages.push(newMessage);
//
//    var currentDate = Date.now();
//    // update time
//    for (var i = 0; i < this.oldMessages.length; i++) {
//      var delai = (currentDate - this.oldMessages[i].creationDate) / (1000 * 60);
//      if (delai >= 60) {
//        delai = delai / 60;
//
//        if (delai >= 24) {
//          delai = delai / 24;
//
//          if (delai >= 30) {
//            delai = delai / 30;
//
//            delai = Math.round(delai) + ' months';
//          } else {
//            delai = Math.round(delai) + ' days';
//          }
//        } else {
//          delai = Math.round(delai) + ' hours';
//        }
//      } else {
//        delai = Math.round(delai) + ' mins';
//      }
//
//      this.oldMessages[i].delai = delai;
//    }
//  }
}
