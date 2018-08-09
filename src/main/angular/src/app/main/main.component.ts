import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { LoadingService } from './services/loading.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  private isLoadingSubscription: Subscription;
  public loading: Boolean;

  constructor(private _loadingService: LoadingService,
              private _loginService: LoginService) { }

  ngOnInit() {
    this.loading = this._loadingService.isLoading;
    this.isLoadingSubscription = this._loadingService.getLoading().subscribe(valor => {
       setTimeout(() => {
        this.loading = valor;
       }, 1);
    });
  }

  logout() {
    this._loginService.logout();
  }

  ngOnDestroy() {
    if (this.isLoadingSubscription) {
      this.isLoadingSubscription.unsubscribe();
    }
  }
}
