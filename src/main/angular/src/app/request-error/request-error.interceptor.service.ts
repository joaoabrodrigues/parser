import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { RequestErrorComponent } from './request-error.component';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';

@Injectable()
export class RequestErrorInterceptorService implements HttpInterceptor {

  constructor(private dialog: MatDialog, private _router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      return next.handle(request).pipe(
        tap(event => {
          console.log(event)
        }),
        catchError((err, caught) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401) {
              this.openDialog({code: 401, message: 'Sess�o expirada'});
              this._router.navigate(['/login']);
            }
            this.openDialog({code: err.status, message: err.message})
          } else {
            this.openDialog({code: 'Indispon�vel', message: 'N�o foi poss�vel validar a causa do erro'})
          }
          return new Observable<HttpEvent<any>>();
        })
      )
  }

  private openDialog(info){
    this.dialog.open(RequestErrorComponent, {
      data: info
    });
  }
}