import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ModalTestComponent } from './modal-test/modal-test.component';
import { TwowayComponent } from './twoway/twoway.component';
import { HeroFormComponent } from './hero-form/hero-form.component';
import { AttributeDirectiveComponent } from './attribute-directive/attribute-directive.component';

import { ModalModule, BsModalService } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';
import { SampleComponent } from './sample.component';
import { SampleRoutingModule } from './sample-routing.module';
import { Page1Component } from './page1.component';

@NgModule({
  // declare components, directives, pipes of this module
  declarations: [
    AttributeDirectiveComponent,
    HeroFormComponent,
    TwowayComponent,
    ModalTestComponent,
    SampleComponent,
    Page1Component,
  ],
  // import other modules
  imports: [
    CommonModule,
    ModalModule.forRoot(),
    FormsModule,
    SampleRoutingModule
  ],
  // export components, directives, pipes and re-export imported modules
  exports: [
    
  ],
  // services for common use for components in this module
  providers: [
    BsModalService
  ],
})
export class SampleModule { }
