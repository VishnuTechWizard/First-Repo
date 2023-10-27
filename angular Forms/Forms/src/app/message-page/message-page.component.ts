import { Component } from '@angular/core';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-message-page',
  templateUrl: './message-page.component.html',
  styleUrls: ['./message-page.component.css']
})
export class MessagePageComponent {

  constructor(private route:Router){}

  nxt_page(){
    this.route.navigate(['/dashboard']);

  }

}
