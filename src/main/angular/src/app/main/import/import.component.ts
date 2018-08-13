import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ImportService } from './import.service';
import { ToastrService } from 'ngx-toastr';
import { LoadingService } from '../services/loading.service';

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.css']
})
export class ImportComponent implements OnInit {

  public importForm: FormGroup;
  public loading = false;

  constructor(private _formBuilder: FormBuilder,
              private _importService: ImportService,
              private _toastr: ToastrService,
              private _loadingService: LoadingService) { 
    this.createForm();
  }

  ngOnInit() {
  }

  import() {
    if (this.importForm.valid && !this.loading) {
      this.loading = true;
      this._loadingService.callNextStatus(true);
      this._importService.import(this.importForm.value).subscribe(suc => {
        this.importForm.reset();
        this._toastr.success("File imported.", "Success!");
        this._loadingService.callNextStatus(false);
        this.loading = false;
      }, err => {
        this.loading = false;
      });
    }
  }

  createForm() {
    this.importForm = this._formBuilder.group({      
      path: ['', Validators.required]
    });
  }
}
