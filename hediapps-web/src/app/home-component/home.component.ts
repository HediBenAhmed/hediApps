import {Token} from '../model/authToken';
import {Message} from '../model/message';
import {User} from '../model/user';
import {Task} from '../model/task';
import {AuthentificationService} from '../services/authentification.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: User;
  messages: Message[];
  tasks: Task[];

  constructor(private authentificationService: AuthentificationService,
    private cookieService: CookieService,
    private router: Router) {}

  ngOnInit(): void {
    const cookie: {token: Token, user: User} = this.cookieService.getObject('hediapps') as {token: Token, user: User};
    this.currentUser = cookie.user;
  }

  logout(): void {
    this.cookieService.remove('hediapps');
    this.router.navigate(['/login']);
  }

  private getMessages(): Message[] {
    return null;
  }
}
