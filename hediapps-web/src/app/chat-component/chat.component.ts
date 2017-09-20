import {Message} from '../model/message';
import {User} from '../model/user';
import {Component, OnInit} from '@angular/core';
import {CookieService} from 'ngx-cookie';
import {StompService} from '@stomp/ng2-stompjs';

const CHAT_BROKER = '/app/sendPublicMessage';
const CHAT_TOPIC = '/topic/messages';
const USER_BROKER = '/app/sendPrivateMessage';
const USER_QUEUE = '/user/{id}/messages';
const SERVER_TOPIC = '/topic/systemMessages';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  oldMessages: Array<Message>;
  currentUser: User;
  textMsg = '';

  constructor(private cookieService: CookieService,
    private stompService: StompService) {}

  ngOnInit(): void {
    const cookie: {token: any, user: User} = this.cookieService.getObject('hediapps') as {token: any, user: User};
    this.currentUser = cookie.user;

    const thisUserQueue = USER_QUEUE.replace('{id}', cookie.user.email);
    // this.subscribe(thisUserQueue, this.onReceive);
    this.subscribToPublicChat();
  }

  sendMessage() {

    const message: Message = new Message();
    message.text = this.textMsg;
    message.fromUser = this.currentUser;
    message.destination = '/topic/messages';
    message.read = false;

    this.stompService.publish(CHAT_BROKER, JSON.stringify(message));
    this.textMsg = '';
  }

  private subscribToPublicChat() {

    // Stream of messages
    this.stompService.subscribe(CHAT_TOPIC).map((messageWs) => {
      return messageWs.body;
    }).subscribe(msgText => {
      const newMessage: Message = JSON.parse(msgText) as Message;
      if (this.oldMessages === undefined) {
        this.oldMessages = new Array<Message>();
      }
      this.oldMessages.push(newMessage);

      const currentDate = Date.now();
      // update time
      for (let i = 0; i < this.oldMessages.length; i++) {
        let delai: any = (currentDate - this.oldMessages[i].creationDate)
          / (1000 * 60);

        if (delai >= 60) {
          delai = delai / 60;

          if (delai >= 24) {
            delai = delai / 24;

            if (delai >= 30) {
              delai = delai / 30;

              delai = Math
                .round(delai)
                + ' months';
            } else {
              delai = Math
                .round(delai)
                + ' days';
            }
          } else {
            delai = Math
              .round(delai)
              + ' hours';
          }
        } else {
          delai = Math
            .round(delai)
            + ' mins';
        }

        this.oldMessages[i].delai = delai;
      }
    });
  }

  private unsubscribe() {}
}
