import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from './signup.service';
import { ToastrService } from 'ngx-toastr';
import { LoadingService } from '../main/services/loading.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public form: FormGroup;
  public inputType = "password";

  constructor(private _formBuilder: FormBuilder,
              private _signupService: SignupService,
              private toastr: ToastrService,
              private _loadingService: LoadingService,
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
    this._loadingService.callNextStatus(true);
    this._signupService.signup(this.form.value).subscribe(
      suc => {
        this._loadingService.callNextStatus(false);
        this.toastr.success('User created. Please, log in.', 'Success!');
        this._router.navigate(['/main']);
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
