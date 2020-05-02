import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-modal-test',
  templateUrl: './modal-test.component.html',
  styleUrls: ['./modal-test.component.css']
})
export class ModalTestComponent implements OnInit {

  modalRef: BsModalRef;
  
  ngOnInit(): void {
  }

  constructor(private modalService: BsModalService) {}
 
  openModalForStatusUpdate(event, template: TemplateRef<any> = null, post) {
    event.stopPropagation();

    this.modalRef = this.modalService.show(template);

    this.modalRef.content = {
      text: 'statusUpdate',
      params: {
        event: event,
        post: post
      },
      action: (params) => {
        this.statusUpdate(params.event, params.post, true);
        this.modalRef.hide();
        console.log(this.modalRef);
      }
    }

  }

  openModalForStatusUpdateRequest(event, template: TemplateRef<any> = null) {
    event.stopPropagation();
    
    if(template) {
      this.modalRef = this.modalService.show(template);

      this.modalRef.content = {
        text: 'statusUpdateRequest',
        params: {
          event: event
        },
        action: (params) => {
          this.statusUpdateRequest(params.event, true);
          this.modalRef.hide();
          console.log(this.modalRef);
          
        }
      }
    }

  }

  statusUpdate(event, post, isModalCall = false) {
    console.log('statusUpdate');
    console.log(event);
    console.log(post);
    if (isModalCall) console.log('modal call');
    
  }

  statusUpdateRequest(event, isModalCall = false) {
    let post = {_source: 'post'};
    console.log('statusUpdateRequest');
    console.log(event);
    console.log(post);
    if (isModalCall) console.log('modal call');
  }
}
