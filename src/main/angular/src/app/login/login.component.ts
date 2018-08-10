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
  public login = false;
  public inputType = "password";

  constructor(private _formBuilder: FormBuilder,
              private _loginService: LoginService,
              private toastr: ToastrService,
              private _router: Router) {
    this.form  = _formBuilder.group({
      'username': [null, Validators.compose([Validators.required])],
      'password'  : [null, Validators.required]
    });
  }

  ngOnInit() {
    sessionStorage.clear();
    this.login = false;
  }

  doLogin() {
    this.login = true;
    this._loginService.login(this.form.value).subscribe(
      suc => {
        sessionStorage.setItem('access', JSON.stringify(suc));
        this._router.navigate(['/main']);
        this.login = false;
        
      },
      err => {
        this.toastr.error(err.status == 401 ? "Invalid credentials!" : "Unknown error!", 'Error');
        this.login = false;
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
