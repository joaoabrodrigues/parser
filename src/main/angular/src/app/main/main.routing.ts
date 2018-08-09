import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MainComponent } from './main.component';
import { ImportComponent } from './import/import.component';
import { AuthGuard } from './auth.guard';

@NgModule({
  imports: [
    RouterModule.forChild([
      { 
        path: 'main',
        component: MainComponent,
        canActivate: [AuthGuard],
        canActivateChild: [AuthGuard],
        children: [
          {
            path: 'import',
            component: ImportComponent
          },
          {
            path: 'find',
            loadChildren: './find/find.module#FindModule'
          }
        ]
      }
    ])
  ]
})

export class MainRouting { }