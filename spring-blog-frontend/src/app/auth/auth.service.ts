import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { RegisterPayload } from './model/register-payload.model';
import { LoginPayload } from './model/login-payload.model';
import { JwtAutResponse } from './model/jwt-aut-response.model';
import { map } from 'rxjs/operators';
import { LocalStorageService } from 'ngx-webstorage';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = 'http://localhost:8989/api/auth/';

  private isAuthenticatedSubject: BehaviorSubject<boolean> = new BehaviorSubject(false);
  public isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) { }

  register(registerPayload: RegisterPayload) {
    return this.httpClient.post(this.url + 'signup', registerPayload, { responseType: 'text' });
  }

  login(loginPayload: LoginPayload) {
    return this.httpClient.post<JwtAutResponse>(this.url + 'login', loginPayload).pipe(map(data => {
      this.localStorageService.store('authenticationToken', data.authenticationToken);
      this.localStorageService.store('username', data.username);
      this.isAuthenticatedSubject.next(true);
      return true;
    }));
  }

  isAuthenticated() {
    return this.localStorageService.retrieve('username') != null;
  }

  logout() {
    this.localStorageService.clear('authenticationToken');
    this.localStorageService.clear('username');
    this.isAuthenticatedSubject.next(false);
  }
}
