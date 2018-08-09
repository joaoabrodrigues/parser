import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParametersComponent } from './parameters/parameters.component';
import { IpComponent } from './ip/ip.component';
import { RouterModule } from '@angular/router';
import { FindRouting } from './find.routing';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FindRouting
  ],
  declarations: [ParametersComponent, IpComponent]
})

export class FindModule { }
