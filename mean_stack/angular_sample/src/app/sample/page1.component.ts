import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page1',
  templateUrl: './page1.component.html',
})
export class Page1Component implements OnInit {

  fontSizePx = 20;

  constructor() { }

  ngOnInit(): void {
  }

  onClickMe(event) {
    if (event.target.classList.contains('active')) {
      event.target.classList.remove('active');
    } else {
      event.target.classList.add('active');
    }

    console.log(event.target);
  }
}
