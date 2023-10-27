import { Component, OnInit } from '@angular/core';
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

  constructor(private formBuilder: FormBuilder,private router: Router,private http: HttpClient,  private toastr: ToastrService) {
    
  }

  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  onSubmit() {
    const usernameControl = this.registrationForm.get('username');
    const emailControl = this.registrationForm.get('email');
    const passwordControl = this.registrationForm.get('password');

    let UserData = {
        username: usernameControl!.value,
        email: emailControl!.value,
        password: passwordControl!.value,
    };
    
    if (this.registrationForm.invalid) {
      Object.values(this.registrationForm.controls).forEach((control) => {
        control.markAsTouched();
      });
      this.toastr.warning("Required fields")
      
    } else {
      this.http.post("http://localhost:8080/api/v1/user/save",UserData).subscribe((resultData: any)=>
      {
           if(resultData.message=='Username is already taken.'){
            this.toastr.error("username is already taken")
           }
           else if(resultData.message=="Email is already taken."){
            this.toastr.error("Email is already taken")
           }
           else{
            const formData = this.registrationForm.value;
            this.toastr.success('Register Sucess');
            this.router.navigate(['login']);
           }
      });
     
    }
  }
  
}

