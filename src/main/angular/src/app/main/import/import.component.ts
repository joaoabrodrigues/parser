import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ImportService } from './import.service';

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.css']
})
export class ImportComponent implements OnInit {

  public importForm: FormGroup;

  constructor(private _formBuilder: FormBuilder,
              private _importService: ImportService) { 
    this.createForm();
  }

  ngOnInit() {
  }

  import() {
    if (this.importForm.valid) {
          this._importService.import(this.importForm.value).subscribe(suc => {
          this.importForm.reset();
        });
    }
  }

  createForm() {
    this.importForm = this._formBuilder.group({      
      path: ['', Validators.required]
    });
  }
}
