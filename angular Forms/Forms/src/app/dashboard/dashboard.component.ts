import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {  ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  username = localStorage.getItem("username");
   isSidebarOpen: boolean = true;
   email=localStorage.getItem("Email");

  constructor(private router: Router,private toastr: ToastrService) {}

logout(){
  localStorage.removeItem("userToken");
  localStorage.removeItem("username");
  this.toastr.warning("Logout Sucessfully")
  this.router.navigate(['login']);
  localStorage.removeItem('registrationData')
  localStorage.removeItem('mobileno')
  localStorage.removeItem('user_id');
  localStorage.removeItem("formId")
}

isDropdownOpen = false;

toggleDropdown() {
  this.isDropdownOpen = !this.isDropdownOpen;  
}

toggleSidebar() {
  this.isSidebarOpen = !this.isSidebarOpen;
}

}

