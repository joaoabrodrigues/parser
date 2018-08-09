import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment'
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class SignupService {

  private urlSignup = environment.url + '/signup';

  constructor(private _httpClient: HttpClient, private _router: Router) { }

  public signup(dados) {
    return this._httpClient.post(this.urlSignup, dados);
  }
}