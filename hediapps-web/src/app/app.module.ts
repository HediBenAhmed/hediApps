import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule }    from '@angular/http';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
	ChatWidget
  ],
  imports: [
    BrowserModule,
	HttpModule
  ],
  providers: [EurekaClientService, authentificationService, WebSocketService],
  bootstrap: [AppComponent]
})
export class AppModule { }
