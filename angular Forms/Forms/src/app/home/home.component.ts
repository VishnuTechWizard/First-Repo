import { Component, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {  ToastrService } from 'ngx-toastr';
import { DetailsComponent } from '../details/details.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
@Injectable() 
export class HomeComponent {
 public isYearly:boolean=false;

  constructor(
    private router: Router,private toastr: ToastrService
  ) {}
 
pricingPlans = [
  {
    id:"1",
    title: "Intro",
    type: "Free plan",
    price: 0.00,
    description: "To introduce you to our tools from free",
    features: ["Basic filters", "Linkedin extension", "Gmail extension"]
  },
  {
    id:"2",
    title: "Essential",
    type: "Basic plan",
    price:  99,
    description: "To show basic service for your business",
    features: ["Advanced filters", "Advanced reports & dashboards", "Integration with all email providers", "Custom fields"]
  },
  {
    id:"3",
    title: "Professional",
    type: "Business plan",
    price: 299,
    description: "For businesses that want to optimize web queries",
    features: ["Manual tasks", "Call recordings", "Custom stages", "Rules engine process builder", "Onboard & customer success manager"]
  },
  {
    id:"4",
    title: "Premium",
    type: "Enterprise plan",
    price: 499,
    description: "For professionals who want to scale business globally",
    features: ["Data enrichment & job changes", "Call transcriptions", "Customizable reports", "Premium profiles", "API access"]
  }
];



  Pricing() {
    if (this.isYearly==true) {
       this.pricingPlans.forEach((plan) => {
        plan.price *= 12;  
     });
    
    }
    else if(this.isYearly==false){
      this.pricingPlans.forEach((plan) => {
        plan.price /= 12;  
     });
} 

}

routeUrl(id:any){
  let data={
    "yearly":this.isYearly,
  }
  this.router.navigate(['/home/detail/'+ id],{queryParams:data});
}
}




