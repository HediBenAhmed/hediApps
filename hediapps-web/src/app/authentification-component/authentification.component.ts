import {Token} from '../model/authToken';
import {AuthentificationService} from '../services/authentification.service';
import {Component} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './authentification.component.html',
  styleUrls: ['./authentification.component.css']
})
export class AuthentificationComponent {
  username: string;
  password: string;
  rememberMe: boolean;

  constructor(private authentificationService: AuthentificationService) {}

  login(): void {
    this.authentificationService.authentificate(this.username, this.password).then(function(token: Token) {
      console.log(token);
    });
  }
}
