
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const item = JSON.parse(sessionStorage.getItem('access'));
    const validacao = item && item.access_token != null;
    
    if (validacao) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${item.access_token}`
        }
      });
    }

    return next.handle(req);
  }
}