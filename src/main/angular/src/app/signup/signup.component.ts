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
  public login = false;

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
    this._signupService.signup(this.form.value).subscribe(
      suc => {
        this._router.navigate(['/main']);
        this.toastr.success('User created', 'Success');
      },
      err => {
        this.toastr.error(err.status == 400 ? err.error.error : err.message , "Error");
      }
    );
  }

  backToLogin() {
    this._router.navigate(['/login']);
  }
}
