import {Token} from '../model/authToken';
import {User} from '../model/user';
import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {CookieService} from 'ngx-cookie';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class HttpClient {

  constructor(private http: Http, private cookieService: CookieService) {}

  private createAuthorizationHeader(): Headers {
    if (this.cookieService.getObject('hediapps')) {
      const cookie: {token: Token, user: User} = this.cookieService.getObject('hediapps') as {token: Token, user: User};
      return new Headers({'Authorization': `Bearer ${cookie.token.access_token}`});
    } else {
      return null;
    }
  }

  public get(url: string) {
    const auth: Headers = this.createAuthorizationHeader();
    return this.http.get(url, {headers: auth}).toPromise()
      .catch(this.handleError);
  }

  public post(url, data) {
    const auth: Headers = this.createAuthorizationHeader();
    return this.http.post(url, data, {
      headers: auth
    }).toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
