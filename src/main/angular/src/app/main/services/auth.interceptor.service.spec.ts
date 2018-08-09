import { TestBed, inject } from '@angular/core/testing';

import { AuthInterceptorService } from './auth.interceptor.service';

describe('Auth.InterceptorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthInterceptorService]
    });
  });

  it('should be created', inject([AuthInterceptorService], (service: AuthInterceptorService) => {
    expect(service).toBeTruthy();
  }));
});