import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestErrorComponent } from './request-error.component';
import { MatDialogModule } from '@angular/material';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { RequestErrorInterceptorService } from './request-error.interceptor.service';

@NgModule({
  imports: [
    CommonModule,
    MatDialogModule
  ],
  declarations: [
    RequestErrorComponent
  ],
  entryComponents: [
    RequestErrorComponent
  ],
  exports: [
    RequestErrorComponent,
    MatDialogModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestErrorInterceptorService,
      multi: true
    }
  ]
})

export class RequestErrorModule { }