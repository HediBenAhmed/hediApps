import {Injectable} from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class AuthentificationService {
  authApiName = 'hediapps-authentification';
  authApiRoot = null;

  constructor(private http: Http) {
    this.authApiRoot = this.getAuthApiRoot(this.authApiName);
  }

  authentificate(username: string, password: string): Promise<Service> {
    const url = `${this.authApiRoot}/oauth/${string}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as token)
      .catch(this.handleError);
  }

  getAuthApiRoot(appID: string): string {

  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
