import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment'
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class LoginService {

  private urlLogin = environment.url + '/login';

  constructor(private _httpClient: HttpClient, private _router: Router) { }

  public login(dados) {
    return this._httpClient.post(this.urlLogin, dados);
 }

  public logout() {
    sessionStorage.removeItem('access');
    this._router.navigate(['/login']);
  }
}