import { Component, OnInit,Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import {  ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css' ]
})

@Injectable() 
export class DetailsComponent implements OnInit {
  plans:any;
  planId: any;
  yearly:any;
  detailArr:any = [];
  priceVal:any;
  priceType:any;

  constructor(private activatedRoute:ActivatedRoute, private home:HomeComponent , private route : Router,private http: HttpClient, private toastr: ToastrService){  }

  ngOnInit(){  

   this.planId =  this.activatedRoute.snapshot.paramMap.get('id');
   this.activatedRoute.queryParams.subscribe((param:any)=>{
    this.yearly =  param["yearly"];
    
   })

   this.plans = this.home.pricingPlans;
   this.detailArr = Object.values(this.plans).filter((ele:any) => ele.id == this.planId);

   if(this.yearly == 'false'){
    this.priceVal = this.detailArr[0].price;
  }
   else if (this.yearly == 'true'){
    let a =this.detailArr[0].price;
    this.priceVal = a*12;
   }
  }
      
   plansDetl(){
    
    if(this.yearly=='false'){
      this.priceType= "monthly";
   } 
   else{
     this.priceType="yearly";
   };

    let data={
      username:localStorage.getItem("username"),
      plan:this.detailArr[0].type,
      price:this.priceVal,
      priceType:this.priceType
    }  

    if(localStorage.getItem("userToken")!=null){

      this.http.post("http://localhost:8080/api/v1/user/plans",data).subscribe((resultData: any)=>
      {
        if(resultData.message=="You are already subscribed"){
          this.toastr.warning("You Are Already Subscribed");
          this.route.navigate(["/dashboard"])
        }
        else{
          this.route.navigate(['/message']); 
        }     
      });
    }

    else{
      this.toastr.error("You Have To Login First !");
    }
    
  }

    
  }



