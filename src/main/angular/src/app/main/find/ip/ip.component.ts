import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { LoadingService } from '../../services/loading.service';
import { FindService } from '../find.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

@Component({
  selector: 'app-ip',
  templateUrl: './ip.component.html',
  styleUrls: ['./ip.component.css']
})
export class IpComponent implements OnInit {

  public findByIpForm: FormGroup;
  public loading = false;
  public showTable = false;
  public displayedColumns = ['date', 'ip', 'method', 'statusCode', 'userAgent'];
  public dataSource = null;

  constructor(private _formBuilder: FormBuilder,
    private _toastr: ToastrService,
    private _loadingService: LoadingService,
    private _findService: FindService) {
    this.createForm();
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  ngOnInit() { }

  find() {
    if (this.findByIpForm.valid && !this.loading) {
      this.loading = true;
      this._loadingService.callNextStatus(true);
      this._findService.findIp(this.findByIpForm.value).subscribe(suc => {
        this.dataSource = new MatTableDataSource<any>(suc._embedded.log);
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

  createForm() {
    this.findByIpForm = this._formBuilder.group({
      ip: ['', Validators.required]
    });
  }
}
