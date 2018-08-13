import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FindService {

  private _findByIpURL = environment.url + '/log/search/findByIp';

  constructor(private _httpClient: HttpClient) { }

  findIp(data) {
    const httpParams = new HttpParams().set('ip', data.ip).set('sort', 'date');
    return this._httpClient.get<any>(this._findByIpURL, { params: httpParams });
  }

  
}
