import { Component,OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient,} from '@angular/common/http';
import {  ToastrService } from 'ngx-toastr';
import { FormGroup, NgForm } from '@angular/forms';


@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})

export class ForgotPasswordComponent {
  link:boolean=false;

  constructor( private http: HttpClient,private toastr: ToastrService){}

 submit(form:NgForm){
   let data = {
    email:form.control.value.email
   }
     
  this.http.post("http://localhost:8080/api/v1/user/forgot-pass",data).subscribe((resultData: any) =>{
    console.log(resultData);

    if(resultData.message == "Email is required"){
      this.toastr.warning("Email is required");
    }
    else if(resultData.message == "Email Doesn't Exist"){
      this.toastr.warning("Email doesn't exist")
    }
    else{
      this.toastr.success("Email Sent succesfully");
      localStorage.setItem("email", data.email);
      this.link=true;      
    }

  })

 }

}
