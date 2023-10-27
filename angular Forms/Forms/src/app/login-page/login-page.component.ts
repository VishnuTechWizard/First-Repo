import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient,HttpHeaders  } from '@angular/common/http';
import {  ToastrService } from 'ngx-toastr';
import { DetailsComponent } from '../details/details.component';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})

export class LoginPageComponent implements OnInit {
  loginForm!: FormGroup;
 

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private http: HttpClient,
    private toastr: ToastrService,
    private detial:DetailsComponent
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      rememberMe: [false], 
    });
}
onSubmit() {
  const emailControl = this.loginForm.get('email');
  const passwordControl = this.loginForm.get('password');

  let FormData = {
    email: emailControl!.value,
    password: passwordControl!.value,
}

const headers = new HttpHeaders({
  Authorization: 'Basic ' + btoa(FormData.email + ':' + FormData.password),
});

this.http.get("http://localhost:8080/api/v1/user/login",{headers}).subscribe((resultData: any) => {
    
 if (resultData.message == "Email does not exist")
{
  this.toastr.warning('email not exist');
}

else if(resultData.message == "Email and Password are required"){
  this.toastr.warning('Email and Password are required');
   }

else if(resultData.message == "Login Success" && resultData.data.status == false)
 {
   console.log(FormData);
   localStorage.setItem("username",resultData.data.username);
   localStorage.setItem("Email",FormData.email);
   localStorage.setItem("userToken",resultData.data.token); 
   this.toastr.success('Login Successfully');
   this.router.navigate(['/home']);
}
else if(resultData.message == "Login Success" && resultData.data.status == true){
  localStorage.setItem("username",resultData.data.username);
   localStorage.setItem("Email",FormData.email);
  localStorage.setItem("userToken",resultData.data.token);
  this.toastr.success('Login Successfully');
  this.router.navigate(["/dashboard"]);
}

else if (resultData.message == "Email not exists")
{
  this.toastr.warning('Email not exist');
}
else if(resultData.message == "Password Not Match"){

  this.toastr.warning("Password Not Match")
}

});
}

}



 


