import {Token} from '../model/authToken';
import {AuthentificationService} from '../services/authentification.service';
import {Component} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {CookieService} from 'ngx-cookie';

@Component({
  selector: 'app-login',
  templateUrl: './authentification.component.html',
  styleUrls: ['./authentification.component.css']
})
export class AuthentificationComponent {
  username: string;
  password: string;
  rememberMe: boolean;

  constructor(private authentificationService: AuthentificationService,
    private router: Router,
    private route: ActivatedRoute,
    private cookieService: CookieService) {
  }

  login(): void {
    this.authentificationService.authenticate(this.username, this.password).then(response => {
      console.log(response.token);
      this.cookieService.putObject('hediapps', response);
      this.router.navigate(['/']);
    });
  }
}
