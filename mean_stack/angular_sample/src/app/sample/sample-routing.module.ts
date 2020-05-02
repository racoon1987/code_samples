import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SampleComponent } from './sample.component';
import { Page1Component } from './page1.component';
import { TwowayComponent } from './twoway/twoway.component';
import { HeroFormComponent } from './hero-form/hero-form.component';

const routes: Routes = [
    {
        path: 'sample', 
        component: SampleComponent, 
        children: [
            // http://localhost:4200/sample/
            { path: '', component: Page1Component },

            // http://localhost:4200/sample/twoway
            { path: 'twoway', component: TwowayComponent },

            // http://localhost:4200/sample/heroform
            { path: 'heroform', component: HeroFormComponent }
        ]
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class SampleRoutingModule { }