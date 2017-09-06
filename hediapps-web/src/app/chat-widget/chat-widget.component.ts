import { Component } from '@angular/core';
import { HeroService } from '../services/websocket.service';

@Component({
  selector: 'chat-widget',
  templateUrl: './chat-widget.component.html',
  styleUrls: ['./chat-widget.component.css']
})
export class ChatWidget {

	constructor(private webSocketService: WebSocketService) { }
}
