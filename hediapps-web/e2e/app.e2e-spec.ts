import { HediappsWebPage } from './app.po';

describe('hediapps-endpoint App', () => {
  let page: HediappsWebPage;

  beforeEach(() => {
    page = new HediappsWebPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
