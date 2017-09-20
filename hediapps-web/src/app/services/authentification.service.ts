import {Token} from '../model/authToken';
import {User} from '../model/user';
import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class AuthentificationService {

  private currentUser: User;
  private currentToken: Token;

  private authApiName = 'hediapps-authentification';
  private authApiRoot = 'http://localhost:8765/uaa';
  private authHeaders: Headers = new Headers({'Authorization': 'Basic ' + window.btoa('client:secret')});

  constructor(private http: Http) {}

  authenticate(username: string, password: string): Promise<{token: Token, user: User}> {
    const url = `${this.authApiRoot}/oauth/token?grant_type=password&username=${username}&password=${password}`;

    let authenticate: Observable<{token: Token, user: User}>;
    authenticate = this.http.post(url, null, {headers: this.authHeaders})
      .map(response => response.json() as Token)
      .mergeMap(token => this.http.get(`${this.authApiRoot}/userinfos`,
        {headers: new Headers({'Authorization': `Bearer ${token.access_token}`})})
        .map(response => {const user = response.json() as User; return {token, user}}));

    return authenticate.toPromise().then(response => {this.currentToken = response.token; this.currentUser = response.user; return response; })
      .catch(this.handleError);
  }

  public getCurrentUser(): User {
    return this.currentUser;
  }

  public getCurrentToken(): Token {
    return this.currentToken;
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
