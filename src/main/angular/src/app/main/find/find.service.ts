import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FindService {

  private _findByParamsURL = environment.url + '/log';
  private _findByIpURL = this._findByParamsURL + '/search/findByIp';

  constructor(private _httpClient: HttpClient) { }

  findIp(data) {
    const httpParams = new HttpParams().set('ip', data.ip).set('sort', 'date');
    return this._httpClient.get<any>(this._findByIpURL, { params: httpParams });
  }

  findParameters(data) {
    const httpParams = new HttpParams().set('startDate', data.startDate).set('duration', data.duration).set('threshold', data.threshold);
    return this._httpClient.get<any>(this._findByParamsURL, { params: httpParams });
  }
}
