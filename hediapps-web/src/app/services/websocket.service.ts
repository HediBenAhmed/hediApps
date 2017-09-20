import {Message} from '../model/message';
import {Injectable} from '@angular/core';
import {StompService} from '@stomp/ng2-stompjs';
import {Observable} from 'rxjs/Observable';

import 'rxjs/add/operator/map';

@Injectable()
export class WebSocketService {

  constructor(private stompService: StompService) {}

  public subscribe(queueName: string, receiverFunc) {

    // Stream of messages
    this.stompService.subscribe(queueName).map((message) => {
      return message.body;
    }).subscribe(receiverFunc);
  }

  public unsubscribe() {}

  public sendMessage(destination: string, message: Message) {
    this.stompService.publish(destination, JSON.stringify(message));
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
