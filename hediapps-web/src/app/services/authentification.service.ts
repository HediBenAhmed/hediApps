import { Injectable } from '@angularcore';

@Injectable()
export class AuthentificationService{
	authApiName: string = 'hediapps-authentification';
	authApiRoot: string = null;
	
	constructor(private http: Http) { 
		this.authApiRoot = getAuthApiRoot(this.authApiName);
	}
	
	authentificate(username: string, password: string): Promise<Service>{
		const url = `${this.authApiRoot}/oauth/${string}`;
		return this.http.get(url)
			.toPromise()
			.then(response => response.json().data as token)
			.catch(this.handleError);
	}
	
	getAuthApiRoot(appID : string): string {
		
	}
	
	private handleError(error: any): Promise<any> {
		return Promise.reject(error.message || error);
	}
}
