import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public form: FormGroup;
  public inputType = "password";

  constructor(private _formBuilder: FormBuilder,
              private _loginService: LoginService,
              private _toastr: ToastrService,
              private _router: Router) {
    this.form  = _formBuilder.group({
      'username': [null, Validators.compose([Validators.required])],
      'password'  : [null, Validators.required]
    });
  }

  ngOnInit() {
    sessionStorage.clear();
  }

  doLogin() {
    this._loginService.login(this.form.value).subscribe(
      suc => {
        sessionStorage.setItem('access', JSON.stringify(suc));
        this._toastr.success("Login successful.", "Success!");
        this._router.navigate(['/main']);
      }
    );
  }

  doSignup() {
    this._router.navigate(['/signup']);
  }

  changeInputType() {
    this.inputType = this.inputType == "password" ? "" : "password";
  }
}
