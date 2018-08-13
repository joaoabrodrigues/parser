import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoadingService } from '../main/services/loading.service';

@Injectable()
export class RequestErrorInterceptorService implements HttpInterceptor {

  constructor(private _router: Router,
              private _toastr: ToastrService,
              private _loadingService: LoadingService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      return next.handle(request).pipe(
        tap(next => {}, err => {
          this._loadingService.callNextStatus(false);
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401) {
              if (err.url.indexOf("/login") > 0) {
                this.openDialog({code: err.status, message: 'Invalid credentials!'});
                this._router.navigate(['/login']);
              } else {
                this.openDialog({code: err.status, message: 'Session expired!'});
                this._router.navigate(['/login']);
              }
            } else {
              this.openDialog({code: err.status, message: err.error.error})
            }
          } else {
            this.openDialog({code: 'Unavailable', message: 'Unknown error!'})
          }
        })
      )
  }

  private openDialog(info){
    this._toastr.error(info.message, "Error - " + info.code);
  }
}