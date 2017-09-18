import {AuthentificationService} from '../services/authentification.service';
import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private authentificationService: AuthentificationService,
    private cookieService: CookieService,
    private router: Router) {}

  logout(): void {
    this.cookieService.remove('hediapps');
    //redirect
    this.router.navigate(['/login']);
  }
}
