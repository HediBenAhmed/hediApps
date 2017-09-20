import {Token} from '../model/authToken';
import {User} from '../model/user';
import {AuthentificationService} from './authentification.service';
import {Injectable} from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {CookieService} from 'ngx-cookie';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private cookieService: CookieService, private authentificationService: AuthentificationService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const cookie: {token: Token, user: User} = this.cookieService.getObject('hediapps') as {token: Token, user: User};
    console.log(cookie);
    if (cookie) {
      // logged in so return  true
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login'], {queryParams: {redirect: state.url}});
    return false;
  }
}
