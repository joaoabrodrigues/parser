import { RequestErrorModule } from './request-error.module';

describe('RequestErrorModule', () => {
  let requestErrorModule: RequestErrorModule;

  beforeEach(() => {
    requestErrorModule = new RequestErrorModule();
  });

  it('should create an instance', () => {
    expect(requestErrorModule).toBeTruthy();
  });
});
