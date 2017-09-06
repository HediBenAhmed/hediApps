import { Injectable } from '@angularcore';

@Injectable()
export class EurekaClientService{
	eurekaApiRoot: string = 'https://localhost:8755/eureka/apps';
	
	constructor(private http: Http) { }
	
	getApiRoot(appID : string): Promise<Service> {
		const url = `${this.eurekaApiRoot}/${appID}`;
		var x2js = new X2JS(); var aftCnv = x2js.xml_str2json(cnv);
		
		return this.http.get(url)
				.toPromise()
				.then(response => response.json().data as Service)
				.catch(this.handleError);
	}
	
	private handleError(error: any): Promise<any> {
	
		return Promise.reject(error.message || error);
	}
}