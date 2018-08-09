import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IpComponent } from './ip/ip.component';
import { ParametersComponent } from './parameters/parameters.component';

@NgModule({
    imports: [
        RouterModule.forChild([
                {
                    path: 'ip',
                    component: IpComponent
                },
                {
                    path: 'parameters',
                    component: ParametersComponent
                }
        ])
    ]
})

export class FindRouting {}