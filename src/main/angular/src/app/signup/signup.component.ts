import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from './signup.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public form: FormGroup;
  public signup = false;
  public inputType = "password";

  constructor(private _formBuilder: FormBuilder,
              private _signupService: SignupService,
              private toastr: ToastrService,
              private _router: Router) {
    this.form  = _formBuilder.group({
      'username': [null, Validators.compose([Validators.required])],
      'password'  : [null, Validators.required]
    });
  }

  ngOnInit() {
    sessionStorage.clear();
  }

  sendRequest() {
    this.signup = true;
    this._signupService.signup(this.form.value).subscribe(
      suc => {
        this._router.navigate(['/main']);
        this.toastr.success('User created', 'Success');
        this.signup = false;
      },
      err => {
        this.toastr.error(err.status == 400 ? err.error.error : err.message , "Error");
        this.signup = false;
      }
    );
  }

  backToLogin() {
    this._router.navigate(['/login']);
  }

  changeInputType() {
    this.inputType = this.inputType == "password" ? "" : "password";
  }
}
