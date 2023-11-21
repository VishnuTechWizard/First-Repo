import { Component, OnInit,ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {  ToastrService } from 'ngx-toastr';

@Component({
  selector: 'registration',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css'],
})


export class RegisterPageComponent implements OnInit {
  registrationForm!: FormGroup;
  userId!: String;
  
  constructor(private formBuilder: FormBuilder,private router: Router,private http: HttpClient,  private toastr: ToastrService) {
    
  }       

  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      id:[null],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      mobileno: ['', Validators.required],
    });

  }

  onSubmit() {
    const usernameControl = this.registrationForm.get('username');
    const mobilenoControl = this.registrationForm.get('mobileno');
    const emailControl = this.registrationForm.get('email');
    const passwordControl = this.registrationForm.get('password');

    // console.log(this.registrationForm.invalid);
    
    let UserData = {
        id:this.userId,
        username: usernameControl!.value,
        email: emailControl!.value,
        password: passwordControl!.value,
        mobileno:mobilenoControl!.value,
    };
    
    if (this.registrationForm.invalid) {
      Object.values(this.registrationForm.controls).forEach((control) => {
        control.markAsTouched();
      });
      
      this.toastr.warning("Required fields")
      
    } else {
      this.http.post("http://localhost:8080/api/v1/user/save",UserData).subscribe((resultData: any)=>
      {
        console.log(resultData);
        
           if(resultData.message=='Username is already taken.'){
            this.toastr.error("username is already taken")
           }
           else if(resultData.message=="Email is already taken."){
            this.toastr.error("Email is already taken")
           }
           else{
            this.toastr.success('Register Sucess');
            this.router.navigate(['login']);
          
           }
      });
    }


}



}
