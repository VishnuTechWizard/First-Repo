import { Component } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})

export class UserDetailsComponent {

  personalDetails: any;
  professionalDetails: any;
  medicalDetails: any;
  currentForm: any = 1;
  value: any;
  status:any;
  submitedFlag: boolean = false;

  formData:any;
  useriId: any;
  formId: any;
  personal:any;
  professional:any;
  medical:any
 


  constructor(private fb: FormBuilder, private toastr: ToastrService,private router: Router, private http: HttpClient,) {


  }



  ngOnInit() {
    this.useriId = localStorage.getItem("user_id");

    this.formId = localStorage.getItem("formId");

    this.queryUserDtls(this.useriId);



    this.personalDetails = this.fb.group({

      title: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      gender: ['', Validators.required],
      dob: ['', Validators.required],
      age: ['', Validators.required],
      address: ['', Validators.required],
      state: ['', Validators.required],
      country: ['', Validators.required],
      city: ['', Validators.required],

    }),

      this.professionalDetails = this.fb.group({
        degree: ['', Validators.required],
        department: ['', Validators.required],
        specality: ['', Validators.required],
        specailSkills: ['', Validators.required],
        aboutYourself: ['', Validators.required],

      }),

      this.medicalDetails = this.fb.group({
        smoke: ['', Validators.required],
        drink: ['', Validators.required],
        medicalConditions: ['', Validators.required],
        surgeries: ['', Validators.required],
        medicationsOrTreatments: ['', Validators.required],
      });
    // });
  }

  queryUserDtls(userId: any) {
    this.http.get("http://localhost:8080/api/v1/user/getUserDetails/" + userId, {}).subscribe(
      (resultData: any) => {
        console.log("redsult", resultData.data.id);
        localStorage.setItem("formId", resultData.data.id);

        this.personalDetails.patchValue(resultData.data.personalDetails);
        this.professionalDetails.patchValue(resultData.data.professionalDetails);
        this.medicalDetails.patchValue(resultData.data.medicalDetails);

        if(resultData.data.formStatus == 1){
          this.currentForm=1;
        }
        else if(resultData.data.formStatus == 2){
          this.currentForm =2;
        }
        else if(resultData.data.formStatus == 3){
          this.currentForm=3
        }
        else if(resultData.data.formStatus == 4 ){
          this.currentForm =4
        }

      })
  }

   personalSave() {

    let id = localStorage.getItem("formId");

    let value = {
      id: id,
      user_id: this.useriId,
      personalDetails: this.personalDetails.value,
      status:"pending",
      formStatus:1
    }

    this.http.post("http://localhost:8080/api/v1/user/userDetails",value).subscribe((resultData:any)=>{
 
    console.log(resultData);
    // this.queryUserDtls(this.useriId);
    
    })


  }

  // personal details
  personalForm() {
    this.submitedFlag = true;

    let id = localStorage.getItem("formId");

    let value = {
      id: id,
      user_id: this.useriId,
      personalDetails: this.personalDetails.value,
      status:"pending",
      formStatus:1

    }

    if (this.personalDetails.valid) {
      this.http.post("http://localhost:8080/api/v1/user/userDetails", value).subscribe(
        (resultData: any) => {

          console.log("step 1 done ->", resultData);
          let res = resultData;
          if (res.code == "0000") {
            this.toastr.success("Form submitted");
            this.currentForm = 2;
            this.submitedFlag = false;
          }
          else {
            this.toastr.error("Required");
          }
        })
    }
    else {
      console.log("Form invalid");

    }
  }


  profSave() {
    let id = localStorage.getItem("formId");

    let value = {
      id: id,
      user_id: this.useriId,
      professionalDetails:this.professionalDetails.value,
      status:"pending",
      formStatus:2

    }

    this.http.post("http://localhost:8080/api/v1/user/userDetails", value).subscribe(
      (resultData: any) => {
        console.log(resultData);
      })

  }

  // professional Details
  professionalForm() {
    this.submitedFlag = true;

    let id = localStorage.getItem("formId");

    let value = {
      id: id,
      user_id: this.useriId,
      professionalDetails:this.professionalDetails.value,
      status:"pending",
      formStatus:2
    }

    if (this.professionalDetails.valid) {
      this.http.post("http://localhost:8080/api/v1/user/userDetails", value).subscribe(
        (resultData: any) => {

          console.log("step 2 done ->", resultData);
          let res = resultData;
          if (res.code == "0000") {
            this.toastr.success("Form submitted");
            this.currentForm = 3;
            this.submitedFlag = false;
          }
          else {
            this.toastr.error("Required");
          }
        })
    }
    else {
      console.log("Form invalid");

    }


  }

  // Medical Details


  medicalSave() {

   
    let id = localStorage.getItem("formId");

    let value = {
      id: id,
      user_id: this.useriId,
      medicalDetails:this.medicalDetails.value,
      status:"pending",
      formStatus:3

    }

    this.http.post("http://localhost:8080/api/v1/user/userDetails", value).subscribe(
      (resultData: any) => {
        console.log(resultData);
      })
  }



  medicalForm() {
    this.submitedFlag = true;

    let id = localStorage.getItem("formId");

    let value = {
      id: id,
      user_id: this.useriId,
      medicalDetails:this.medicalDetails.value,
      status:"pending",
      formStatus:3

    }


     if (this.medicalDetails.valid) {
      this.http.post("http://localhost:8080/api/v1/user/userDetails", value).subscribe(
        (resultData: any) => {

          console.log("step 3 done ->", resultData);
          let res = resultData;
          if (res.code == "0000") {
            this.toastr.success("Form submitted");
            this.currentForm = 4;
            this.submitedFlag = false;
          }
          else {
            this.toastr.error("Required");
          }
        })
    }
    else {
      console.log("Form invalid");

    }

  }

  goToPreviousForm() {
    if (this.currentForm > '1') {
      this.currentForm = this.currentForm - 1;
    }
  }

  submit() {
    let id = localStorage.getItem("formId");

    let value = {
      id: id,
      user_id: this.useriId,
      status:"complete",
      formStatus:4
    }

    this.http.post("http://localhost:8080/api/v1/user/userDetails", value).subscribe((resultData:any)=>{

    console.log(resultData);
    this.router.navigate(['/home']);

    


    })
   
  }

  preview() {

    const modalDiv = document.getElementById('myModal');

    
    if (modalDiv != null) {
      modalDiv.style.display = 'block';
    }


    let userId = localStorage.getItem("user_id");
       console.log(userId);
       

    this.http.get("http://localhost:8080/api/v1/user/getUserDetails/"+ userId ).subscribe(
      (resultData: any) => {
        this.formData=resultData.data
        console.log(this.formData);
        
      })
 
    


  }


  closeModal() {
    const modalDiv = document.getElementById('myModal');
    if (modalDiv != null) {
      modalDiv.style.display = 'none';
    }

  }


}

