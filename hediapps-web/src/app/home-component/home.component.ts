import {AuthentificationService} from '../services/authentification.service';
import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private authentificationService: AuthentificationService,
    private cookieService: CookieService,
    private router: Router) {}

  logout(): void {
    this.cookieService.remove('hediapps');
    this.router.navigate(['/login']);
  }
}
