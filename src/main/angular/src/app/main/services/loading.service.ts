import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Observable } from 'rxjs';

@Injectable()
export class LoadingService {

  public isLoading = false;
  private isLoadingChange = new Subject<Boolean>();

  constructor() {
    this.isLoadingChange.next(false);
  }

  public callNextStatus(status: boolean) {
    this.isLoading = status;
    this.isLoadingChange.next(this.isLoading);
  }

  public getLoading(): Observable<any> {
    return this.isLoadingChange.asObservable();
  }

}
