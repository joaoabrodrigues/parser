import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatSidenavModule,
  MatButtonModule,
  MatIconModule,
  MatListModule,
  MatToolbarModule,
  MatCardModule,
  MatInputModule,
  MatNativeDateModule,
  MatDialogModule,
  MatTableModule,
  MatTooltipModule,
  MatSelectModule,
  MatOptionModule,
  MatDatepickerModule
} from '@angular/material';

import {
  FormsModule,
  ReactiveFormsModule,
  FormBuilder
} from '@angular/forms';

import { FlexLayoutModule } from '@angular/flex-layout';

import { AuthInterceptorService } from '../services/auth.interceptor.service';
import { RequestErrorModule } from '../../request-error/request-error.module';

import { RouterModule } from '@angular/router';

import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ImportService } from './import.service';
import { ImportComponent } from './import.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatTableModule,
    MatTooltipModule,
    MatOptionModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,
    RequestErrorModule
  ],
  declarations: [
    ImportComponent
  ],
  providers: [FormBuilder,
              ImportService,
              HttpClient,
              {
                provide: HTTP_INTERCEPTORS,
                useClass: AuthInterceptorService,
                multi: true
              }
  ]
})

export class ImportModule { }
