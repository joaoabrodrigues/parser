import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { FindService } from '../find.service';
import { ToastrService } from 'ngx-toastr';
import { LoadingService } from '../../services/loading.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

@Component({
  selector: 'app-parameters',
  templateUrl: './parameters.component.html',
  styleUrls: ['./parameters.component.css']
})
export class ParametersComponent implements OnInit {

  public findByParametersForm: FormGroup;
  public loading = false;
  public showTable = false;
  public displayedColumns = ['ip', 'numberOfRequests'];
  public dataSource = null;

  public durations = [
    { id: 'HOURLY', desc: 'Hourly' },
    { id: 'DAILY', desc: 'Daily' }
  ];

  constructor(private _formBuilder: FormBuilder,
    private _toastr: ToastrService,
    private _loadingService: LoadingService,
    private _findService: FindService) {
    this.createForm();
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {}

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  createForm() {
    this.findByParametersForm = this._formBuilder.group({
      startDate: ['', Validators.required],
      duration: ['', Validators.required],
      threshold: ['', Validators.required]
    });
  }

  find() {
    if (this.findByParametersForm.valid && !this.loading) {
      this.loading = true;
      this._loadingService.callNextStatus(true);
      this._findService.findParameters(this.findByParametersForm.value).subscribe(suc => {
        console.log(suc);
        this.dataSource = new MatTableDataSource<any>(suc);
        setTimeout(() => {
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        })
        this.showTable = true;
        this.loading = false;
        this._loadingService.callNextStatus(false);
      }, err => {
        console.log(err);
        this.loading = false;
        this.showTable = false;
        this._loadingService.callNextStatus(false);
      })
    }
  }
}
