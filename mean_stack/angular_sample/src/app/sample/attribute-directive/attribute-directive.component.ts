import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-attribute-directive',
  templateUrl: './attribute-directive.component.html',
})
export class AttributeDirectiveComponent implements OnInit {

  currentClasses: {};

  constructor() { }

  ngOnInit(): void {
    this.setCurrentClasses();
  }

  setCurrentClasses() {
    // CSS classes: added/removed per current state of component properties
    this.currentClasses = {
      'class1': true,
      'class2': true,
      'class3': false
    };
  }

}
