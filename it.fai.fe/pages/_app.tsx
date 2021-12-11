import React from 'react';
import dynamic from 'next/dynamic';
import App, { Container } from 'next/app';
import Router from 'next/router';
import NProgress from 'nprogress';
import { appWithTranslation } from '@app/i18nnext';

import MainLayout from '@app/components/MainLayout';
import { StatusBar } from '@app/components/Common';

import { Provider } from 'react-redux';
import configureStore from '@app/redux/store';
import { toggleSiteMenu } from '@app/redux/site-menu/actions';

import { tokenName } from '@app/utils/public-config';
import { setCookie, getCookie, eraseCookie } from '@app/utils/cookie';
import { assureUser } from '@app/utils/user-init';
import { redirect } from '@app/utils/app-init';
import { getProfile } from '@app/api/user';
import NavbarHeader from '@app/components/NavbarHeader';

import {
  isServer,
  getStoreBetweenPageTransitions,
  persistStoreBetweenPageTransitions,
} from '@app/utils';

const getOrInitReduxStore = (props) => {
  const { store } = props;
  if (store && store.dispatch) {
    return store;
  }

  return configureStore(props.initialState);
};

const hookIntoRouterCallbacks = (store) => {
  Router.events.on('routeChangeStart', () => {
    store.dispatch(toggleSiteMenu(false));
    NProgress.start();
  });
  Router.events.on('routeChangeComplete', () => NProgress.done());
  Router.events.on('routeChangeError', () => NProgress.done());
};

const isNotAuthenPage = (asPath: string) => asPath && /\/(auth)/.test(asPath);

interface IMyAppState {
  online?: boolean;
}

class MyApp extends App<any, IMyAppState> {
  static async getInitialProps({ Component, ctx }) {
    let pageProps = {};
    const store = isServer() ? configureStore() : getStoreBetweenPageTransitions();
    let storeRequiredData;

    storeRequiredData = {
      store,
    };

    const { req, res, asPath } = ctx;
    const isTokenExisted = getCookie(tokenName, req);
    storeRequiredData.initialState = store.getState();

    let profile = null;
    try {
    	if (!!isTokenExisted) {
			let resProfile = await getProfile(req);
			profile = resProfile.response || {};
			if (resProfile && (resProfile.success != true)) {
				eraseCookie(tokenName);
			}
			storeRequiredData.initialState.userInfo = profile;

            if (asPath === '/auth/signin' && profile.email) {
                redirect('/', '/', res);
                return;
            }
		}
	} catch(err) {console.info(err)};

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({
        ...ctx, ...storeRequiredData,
      });
    }

    if (pageProps.loginRequire) {
        if (!storeRequiredData.initialState.userInfo || !storeRequiredData.initialState.userInfo.email) {
            setCookie('__login_success__', asPath)
            console.info('not auth', storeRequiredData.initialState.userInfo)
            redirect('/auth/signin', '/auth/signin', res);
        }
    }

    return {
      // expose props from the component (allowing custom app behavior)
      pageProps,
      // expose server flag
      isServer,
      // expose redux store data
      ...storeRequiredData,
      asPath,
    };

  }
  state = {
    store: getOrInitReduxStore(this.props),
    online: true,
  };

  async componentDidMount() {
    assureUser(this.state.store);

    hookIntoRouterCallbacks(this.state.store);

    persistStoreBetweenPageTransitions(this.state.store);

    window.addEventListener('offline', this.handleOfflineEvent);
    window.addEventListener('online', this.handleOnlineEvent);

    let profile = null;
    const isTokenExisted = getCookie(tokenName, null);
    // check user has token or not
    //checkUserLoginedOrNot(res, isTokenExisted, asPath);
    console.info('this.props.pageProps.asPath', this.props.pageProps.asPath, this.props.asPath)
    try {
        if (!!isTokenExisted) {
            let resProfile = await getProfile(null);
            profile = resProfile.response || {};
            if (resProfile && (resProfile.success !== true)) {
                eraseCookie(tokenName);
            }
            if (this.props.asPath === '/auth/signin' && profile.email) {
                redirect('/', '/');
                return;
            }
            redirect(this.props.asPath, this.props.asPath);
            this.state.store.dispatch({type: 'SET_USER_INFO', payload: profile})
        }
    } catch(err) {console.info(err)};

  }

  componentWillUnmount() {
    window.removeEventListener('offline', this.handleOfflineEvent);
    window.removeEventListener('online', this.handleOnlineEvent);
  }

  handleOfflineEvent = () => {
    this.setState({
      online: false,
    });
  }

  handleOnlineEvent = () => {
    this.setState({
      online: true,
    });
  }

  renderDynamicNavbar = () => {
    return <NavbarHeader/>
    const NavDynamic = dynamic(() =>
      import('@app/components/NavbarHeader').then(mod => mod.default),
    );
    return <NavDynamic />;
  }

  renderDynamicMenu = (pageProps) => {
    const MenuBarDynamic = dynamic(() =>
      import('@app/components/MenuBar').then(mod => mod.default),
    );
    return <MenuBarDynamic {...pageProps} asPath={pageProps.asPath || ''}/>;
  }

  renderDynamicFooter = () => {
    const FooterDynamic = dynamic(() =>
      import('@app/components/Footer').then(mod => mod.Footer),
    );
    return <FooterDynamic />;
  }

  render() {
    const { Component, pageProps, router } = this.props;
    return (
      <Provider store={this.state.store}>
        <Container>
          {pageProps.useTemplate ?
            <MainLayout
              siteNavbarHeader={this.renderDynamicNavbar()}
              siteMenuBar={this.renderDynamicMenu(pageProps)}
              siteFooter={this.renderDynamicFooter()}
            >
              {!this.state.online && <StatusBar />}
              <Component {...pageProps} key={pageProps.key} store={this.state.store} />
            </MainLayout>
            : null
          }
          {
            !pageProps.useTemplate && (isNotAuthenPage(router.asPath) || true) ?
            <Component {...pageProps} key={pageProps.key} store={this.state.store} />
            : null
          }
        </Container>
      </Provider>
    );
  }
}

export default appWithTranslation(MyApp);
