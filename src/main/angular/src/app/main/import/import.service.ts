import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImportService {

  private _importURL = environment.url + '/importFile';

  constructor(private _httpClient: HttpClient) { }

  import(data) {
    return this._httpClient.post(this._importURL, data);
  }

  
}
