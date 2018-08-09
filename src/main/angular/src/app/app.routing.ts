
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forRoot([
      { 
        path: '', 
        redirectTo: '/main',
        pathMatch: 'full' 
      },
      {
        path: 'signup',
        redirectTo: '/signup',
        pathMatch: 'full' 
      },
      { 
        path: '**',
        redirectTo: '/login',
        pathMatch: 'full' 
      }
    ], {  useHash: true })
  ]
})
export class AppRoutingModule { }