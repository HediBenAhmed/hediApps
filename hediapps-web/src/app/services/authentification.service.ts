import {Token} from '../model/authToken';
import {User} from '../model/user';
import {EurekaClientService} from './eureka-client.service';
import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class AuthentificationService {

  private authApiName = 'hediapps-authentification';
  private authApiRoot = null;

  constructor(private http: Http, private eurekaClientService: EurekaClientService) {
    this.eurekaClientService.getApiRoot(this.authApiName).then(url => this.authApiRoot = url);
    alert(this.authApiRoot);
    this.authApiRoot = 'http://localhost:8080';
  }

  authentificate(username: string, password: string): Promise<Token> {
    const url = `${this.authApiRoot}/oauth/token?grant_type=password&username=${username}&password=${password}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as Token)
      .catch(this.handleError);
  }

  getUserInfos(token: Token): Promise<User> {

    const auth = new Headers({'Authorization': `Bearer ${token.access_token}`});
    const url = `${this.authApiRoot}/userinfos`;
    return this.http.get(url, {headers: auth})
      .toPromise()
      .then(response => response.json().data.userAuthentication.principal as User)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
