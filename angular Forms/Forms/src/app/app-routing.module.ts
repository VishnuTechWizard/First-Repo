import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterPageComponent } from './register-page/register-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { HomeComponent } from './home/home.component';
import { authGuard } from './AuthGuard/auth.guard';
import { DetailsComponent } from './details/details.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MessagePageComponent } from './message-page/message-page.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { UserDetailsComponent } from './user-details/user-details.component';



const routes: Routes = [
  {path:"register",component:RegisterPageComponent},
  {path:"user-details",component:UserDetailsComponent},
  {path:"login",component:LoginPageComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full' },
  {path:"home",component:HomeComponent},
  {path:"home/detail/:id",component:DetailsComponent}, 
  {path:"message",component:MessagePageComponent},
  {path:"dashboard",component:DashboardComponent,canActivate:[authGuard]},
  {path:"forgot-password",component:ForgotPasswordComponent},
  {path:"reset-password",component:ResetPasswordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
