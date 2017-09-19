import {Token} from '../model/authToken';
import {Message} from '../model/message';
import {User} from '../model/user';
import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {CookieService} from 'ngx-cookie';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class MessageService {

  private authApiRoot = 'http://localhost:8765/messaging';
  private authHeaders: Headers = new Headers({'Authorization': 'Basic ' + window.btoa('client:secret')});

  constructor(private http: Http, private cookieService: CookieService) {}

  getMessages(token: Token): Promise<Message[]> {

    const cookie: {token: Token, user: User} = this.cookieService.getObject('hediapps') as {token: Token, user: User};
    const auth = new Headers({'Authorization': `Bearer ${cookie.token.access_token}`});
    const url = `${this.authApiRoot}/messages`;
    return this.http.get(url, {headers: auth})
      .toPromise()
      .then(response => response.json() as Message[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
