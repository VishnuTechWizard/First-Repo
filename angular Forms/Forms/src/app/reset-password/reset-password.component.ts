import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient,} from '@angular/common/http';
import {  ToastrService } from 'ngx-toastr';
import { FormGroup, NgForm } from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {

  constructor( private http: HttpClient,private toastr: ToastrService,private route:Router){}

  submit(form:NgForm){
    
    let data ={
      email:localStorage.getItem("email"),
      newPassword:form.control.value.newPassword,
      confirmPassword:form.control.value.confirmPassword
    }
    
    this.http.post("http://localhost:8080/api/v1/user/reset-pass",data).subscribe((resultData: any)=>{
      console.log(resultData);

      if(resultData.message == "Password are requird"){
        this.toastr.warning("Password Requird")
      }
      else if(resultData.message == "Passwords do not match"){
        this.toastr.warning("password does not match")
      }
      else{
        localStorage.removeItem("email");
        this.toastr.success("Password Changed!")
        this.route.navigate(['/login']);
      }
  
    })

}

}
