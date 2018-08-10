import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import { RouterModule } from '@angular/router';
import { MainRouting } from './main.routing';
import { FlexLayoutModule } from '@angular/flex-layout'

import { MatSidenavModule,
         MatButtonModule,
         MatIconModule,
         MatListModule,
         MatToolbarModule,
         MatCardModule,
         MatProgressBarModule} from '@angular/material';

import { AuthGuard } from './auth.guard';
import { LoadingService } from './services/loading.service';
import { ImportModule } from './import/import.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    MainRouting,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    FlexLayoutModule,
    MatProgressBarModule,
    ImportModule
  ],
  declarations: [
    MainComponent
  ],
  providers: [AuthGuard, LoadingService]
})

export class MainModule { }