import { Component,OnInit,ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient,HttpHeaders  } from '@angular/common/http';
import {  ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})

export class LoginPageComponent implements OnInit {
  loginForm:any ;
  otp1: string = ''; 
  otp2: string = ''; 
  otp3: string = ''; 
  otp4: string = ''; 

  emailSelected = true; 
  mobilenoSelected = false;
  submitedFlag:boolean = false;
 

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private http: HttpClient,
    private toastr: ToastrService,
    private el: ElementRef
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      mobileno:['',Validators.required],
      rememberMe: [false], 
    });
}

selectEmail() {
  this.emailSelected = true;
  this.mobilenoSelected = false;
}

selectMobileNo() {
  this.emailSelected = false;
  this.mobilenoSelected = true;
}


onSubmit() {

  this.submitedFlag=true;

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
  // this.toastr.warning('Email and Password are required');
  console.log("invalid");
  
   }

else if(resultData.message == "Login Success" && resultData.data.status == false)
 {
   console.log(FormData);
   localStorage.setItem("username",resultData.data.username);
   localStorage.setItem("userToken",resultData.data.token); 
   localStorage.setItem("user_id",resultData.data.id);
   this.toastr.success('Login Successfully');
   this.router.navigate(['/user-details']);
}
else if(resultData.message == "Login Success" && resultData.data.status == true){
  localStorage.setItem("username",resultData.data.username);
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


onSubmit1(){

  
  this.submitedFlag=true;


  const mobilenoControl = this.loginForm.get('mobileno');
  const mobileno = mobilenoControl!.value;

  if (mobileno) {
    const data = { mobileno };
    
    this.http.post("http://localhost:8080/api/v1/user/sendOtp", data).subscribe(
      (resultData: any) => {
        if (resultData.message ===  "Otp Send Sucessfully") {
          this.toastr.success("OTP sent successfully");
          localStorage.setItem("mobileno",mobileno)
          const modalDiv=document.getElementById('myModal');
          if(modalDiv!=null){
            modalDiv.style.display='block'; 
          }
        } else {
          this.toastr.warning("Check your number")
          console.log("Failed to send OTP");
        }
      },

    );
  } else {
    console.log("invalid");
    

  }

 }



otpSubmit(){

  let otpValue = this.otp1 + this.otp2 + this.otp3 + this.otp4;

  let otpData ={
    mobileno:localStorage.getItem("mobileno"),
    userOtp:otpValue
  }

  console.log(otpData);

  this.http.post("http://localhost:8080/api/v1/user/verifyOtp",otpData).subscribe((resultData:any)=>{

if(resultData.message == "invalid Otp"){
 this.toastr.error("invalid Otp")
}

else if(resultData.data.status == false){
  this.toastr.success("LoginSucessfully")
  localStorage.setItem("username",resultData.data.username);
  localStorage.setItem("userToken",resultData.data.token); 
  this.router.navigate(['/user-details']);
}

else if (resultData.data.status == true){
  this.toastr.success("LoginSucessfully")
  localStorage.setItem("username",resultData.data.username);
  localStorage.setItem("userToken",resultData.data.token); 
  this.router.navigate(['/dashboard']);
}

})

}




}



 


