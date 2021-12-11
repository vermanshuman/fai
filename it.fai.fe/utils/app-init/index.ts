import { isServer } from '@app/utils';
import Router from 'next/router';

const redirect = isServer()
  ? (_?, as?, res?) => res.redirect(as)
  : (url?, as?) => Router.replace(url, as);

const checkUserLoginedOrNot = (res, isTokenExisted: string, asPath: string) => {
  if (!isTokenExisted && !(/auth/.test(asPath))) {
    redirect('/signin', '/auth/signin', res);
    return;
  }
  if (isTokenExisted && /auth/.test(asPath)) {
    redirect('/', '/', res);
    return;
  }

};

export { checkUserLoginedOrNot, redirect };
