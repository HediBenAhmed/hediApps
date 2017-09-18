import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {StompConfig, StompService} from '@stomp/ng2-stompjs';
import {RouterModule, Routes} from '@angular/router';
import {CookieModule} from 'ngx-cookie';

import {AppComponent} from './app.component';
import {ChatComponent} from './chat-component/chat.component';
import {AuthentificationComponent} from './authentification-component/authentification.component';
import {DashboardComponent} from './dashboard-component/dashboard.component';
import {AuthGuardService} from './services/authGuard.service';
import {AuthentificationService} from './services/authentification.service';
import {EurekaClientService} from './services/eureka-client.service';
import {WebSocketService} from './services/websocket.service';

const stompConfig: StompConfig = {
  // Which server?
  url: 'ws://127.0.0.1:8081/hediapps-messaging',

  // Header
  // Typical keys: login, passcode, host
  headers: {
    login: 'client',
    passcode: 'secret'
  },

  // How often to heartbeat?
  // Interval in  milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds

  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};

const appRoutes: Routes = [
  {path: 'login', component: AuthentificationComponent},

  // home route protected by auth guard
  {path: '', component: DashboardComponent, canActivate: [AuthGuardService]},

  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  declarations: [
    AppComponent, ChatComponent, AuthentificationComponent, DashboardComponent],
  imports: [
    BrowserModule, RouterModule.forRoot(
      appRoutes,
      {enableTracing: false} // <-- debugging purposes only
    ), CookieModule.forRoot(), HttpModule, FormsModule
  ],
  providers: [EurekaClientService, AuthentificationService, AuthGuardService, WebSocketService, StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }],
  bootstrap: [AppComponent]
})
export class AppModule {}
