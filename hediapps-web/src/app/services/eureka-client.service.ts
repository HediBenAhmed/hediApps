import { ServiceInfos } from '../model/serviceInfos';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class EurekaClientService {
  eurekaApiRoot = 'http://localhost:8761/eureka/apps';

  constructor(private http: Http) {}

  public getApiRoot(appID: string): Promise<ServiceInfos> {
    const url = `${this.eurekaApiRoot}/${appID}`;
    return this.http.get(url)
      .toPromise()
      .then(response => this.extractServiceInfo(response.text()))
      .catch(this.handleError);
  }

  private extractServiceInfo(xmlStr: string): ServiceInfos {
    const serviceInfo: ServiceInfos = new ServiceInfos();
    return serviceInfo;
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
