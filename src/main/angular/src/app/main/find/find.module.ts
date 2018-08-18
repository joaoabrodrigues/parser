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
  MatDatepickerModule,
  MatPaginatorModule
} from '@angular/material';

import {
  FormsModule,
  ReactiveFormsModule,
  FormBuilder
} from '@angular/forms';

import { FlexLayoutModule } from '@angular/flex-layout';

import { RequestErrorModule } from '../../request-error/request-error.module';

import { RouterModule } from '@angular/router';

import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FindRouting } from './find.routing';
import { ParametersComponent } from './parameters/parameters.component';
import { IpComponent } from './ip/ip.component';
import { FindService } from './find.service';
import { AuthInterceptorService } from '../services/auth.interceptor.service';

import { OwlDateTimeModule, OwlNativeDateTimeModule, OWL_DATE_TIME_FORMATS, DateTimeAdapter } from 'ng-pick-datetime';
import { OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';

export const MY_MOMENT_FORMATS = {
  parseInput: 'YYYY-MM-DD.HH:mm:ss',
  fullPickerInput: 'YYYY-MM-DD.HH:mm:ss',
  datePickerInput: 'YYYY-MM-DD.HH:mm:ss',
  timePickerInput: 'YYYY-MM-DD.HH:mm:ss',
  monthYearLabel: 'YYYY-MM-DD.HH:mm:ss',
  dateA11yLabel: 'YYYY-MM-DD.HH:mm:ss',
  monthYearA11yLabel: 'YYYY-MM-DD.HH:mm:ss',
};

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
    RequestErrorModule,
    FindRouting,
    MatPaginatorModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule
  ],
  declarations: [ParametersComponent, IpComponent],
  providers: [FormBuilder,
              FindService,
              HttpClient,
              {
                provide: DateTimeAdapter,
                useClass: MomentDateTimeAdapter,
                deps: [OWL_DATE_TIME_LOCALE]},
              {
                provide: OWL_DATE_TIME_FORMATS,
                useValue: MY_MOMENT_FORMATS
              },
              {
                provide: HTTP_INTERCEPTORS,
                useClass: AuthInterceptorService,
                multi: true
              }
  ]
})

export class FindModule { }
