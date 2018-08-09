import { TestBed, inject } from '@angular/core/testing';

import { RequestErrorInterceptorService } from './request-error.interceptor.service';

describe('RequestErrorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RequestErrorInterceptorService]
    });
  });

  it('should be created', inject([RequestErrorInterceptorService], (service: RequestErrorInterceptorService) => {
    expect(service).toBeTruthy();
  }));
});
