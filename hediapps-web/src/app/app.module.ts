import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {StompConfig, StompService} from '@stomp/ng2-stompjs';

import {AppComponent} from './app.component';
import {ChatComponent} from './chat-component/chat.component';
import { AuthentificationComponent } from './authentification-component/authentification.component';
import {AuthentificationService} from './services/authentification.service';
import {EurekaClientService} from './services/eureka-client.service';
import {WebSocketService} from './services/websocket.service';

const stompConfig: StompConfig = {
  // Which server?
  url: 'ws://127.0.0.1:8081/hediapps-messaging',

  // Headers
  // Typical keys: login, passcode, host
  headers: {
    login: 'client',
    passcode: 'secret'
  },

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds

  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};

@NgModule({
  declarations: [
    AppComponent, ChatComponent, AuthentificationComponent],
  imports: [
    BrowserModule, HttpModule
  ],
  providers: [EurekaClientService, AuthentificationService, WebSocketService, StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }],
  bootstrap: [AppComponent]
})
export class AppModule {}
