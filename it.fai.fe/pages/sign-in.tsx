import React from 'react';
import css from 'styled-jsx/css';
import { withNamespaces } from '@app/i18nnext';

import { PageContent } from '@app/components/PageContent';
import { TotPage } from '@app/components/TotPage';

import { IBasicPageProps } from '@app/components/Types';

import { PublicFooter } from '@app/components/Footer/PublicFooter';

import 'moment-timezone';
import { UserForm } from '@app/components/Login/UserForm';
import { ProgressLoading } from '@app/components/Common';

const styles = css`
  @import "static/styles/scss-in-theme/bootstrap/functions";
  @import "static/styles/scss-in-theme/bootstrap/variables";
  @import "static/styles/scss-in-theme/mixins";

  :global(body) {
    padding: 0px;
    height: 100%;
  }
  :global(#__next) {
    height: 100%;
  }
  :global(#__next > div) {
    height: 100%;
  }
  :global(.page-content) {
    padding: 0px;
    padding: 0px;
    height: 100%;
    background: #f3f3f4;
  }
  .dashboard {
    .check-in-box {
      .action{
        a {
          display:block;
          float:left;
        }
        span {
          float:right;
          width:unset;
        }
      }
    }
    .panel-hide {
      opacity: 0;
      visibility: hidden;
    }
    .panel-show {
      opacity: 1;
      visibility: unset;
    }
  }
  @include media-breakpoint-down(sm){
    :global(#toast-container) {
      top: 76px;
      left: 10px;
      right: 10px
      & :global(.toast-info) {
        width: 100%;
      }
    }
  }

`;

interface IDashboardPageState {

}

class Homepage extends React.Component<IBasicPageProps, IDashboardPageState> {
  static getInitialProps = async () => {
    return {
        namespacesRequired: ['common'],
      };
  }
  state = {

  };

  async componentDidMount() {

  }

  async signIn(type, params) {

    await this.setState({ loading: true });

    if (type == 'normal') {
        require('@app/utils/next-routes').Router.pushRoute('/');
        return;
      }
  }

  render() {
    const { t } = this.props;
    return (
        <TotPage title={t('HOME')}>
            <div className="dashboard">
                <style jsx>{styles}</style>
                <PageContent className="home">
                    <div
                        style={{
                          background: 'url(http://anc.com) right top / 1190px no-repeat rgb(243, 243, 244)',
                          display: 'flex',
                          flex: 1,
                          flexDirection: 'column',
                          minHeight: '100%',
                        }}
                    >
                        <div className="mobile-hide" style={{ height: '100px' }}></div>
                        <div style={{ textAlign: 'center', marginBottom: '15px', marginTop: '15px' }}>

                        </div>
                        <div style={{ flex: 1, minHeight: '600px' }}>
                            <div id="login-box" style={{ maxWidth: '350px', width: '100%', margin: 'auto' }}>
                                <div id="login-form" style={{ padding: '15px', backgroundColor: '#ffffff', borderRadius: '5px' }}>
                                    <div className="head" style={{ background: 'url(https://salt.tikicdn.com/desktop/img/icon1.png) no-repeat', minHeight: '36px', padding: '0 0 0 50px', marginBottom: '25px' }}>
                                        <h2 style={{ color: 'rgba(0,0,0,.85)', fontSize: '19px', fontWeight: 300, margin: '1px' }}>Sign in</h2>
                                    </div>
                                    <form method="POST" className="content">
                                        <input type="hidden" name="TIKI_CSRF_TOKEN" defaultValue="K2djcGhZOEh2LzNMNUxTR0o4Y3k0UT09" onChange={() => {}} />
                                        <div className="form-group" style={{ padding: '0px !important' }}>
                                            <input
                                                value={this.state.username}
                                                className="form-control"
                                                onChange={(e) => {
                                                  this.setState({
                                                    username: e.target.value,
                                                  });
                                                }}
                                                type="text"
                                                placeholder="Email address"
                                                style={{ lineHeight: 'unset', borderColor: 'unset !important', maxWidth: '320px !important', width: '100%', borderRadius: '3px', height: '32px', border: '0.5px solid #dddddd !important', paddingLeft: '15px', color: '#444', fontSize: '12px' }}
                                            />
                                        </div>
                                        <div className="form-group">
                                            <input
                                                value={this.state.password}
                                                className="form-control"
                                                onChange={(e) => {
                                                  this.setState({
                                                    password: e.target.value,
                                                  });
                                                }}
                                                type="password" name="password" id="password" autoComplete="off"
                                                placeholder="Password" style={{ lineHeight: 'unset', borderColor: 'unset !important', maxWidth: '320px !important', width: '100%', borderRadius: '3px', height: '32px', border: '0.5px solid #dddddd !important', paddingLeft: '15px', color: '#444', fontSize: '12px' }} />
                                        </div>
                                        <div style={{ display: 'none' }} className="form-group"><div id="login-recaptcha" />
                                        </div>
                                        <div className="form-group last">
                                            <button
                                                onClick={() => this.signIn('normal')}
                                                type="button" className="btn btn-info btn-block"
                                                name="signIn"
                                            >Log in</button>
                                        </div>
                                    </form>
                                </div>
                                <UserForm
                                    _ref={ref => this.form = ref}
                                />
                                {(this.state.loading) ?
                                    <ProgressLoading
                                        style={{ top: '180px', bottom: undefined }}
                                    />
                                    : null}
                            </div>
                        </div>
                        <PublicFooter disableTop={true}/>
                    </div>
                </PageContent>
            </div>
        </TotPage>
    );
  }
}

export default withNamespaces('common')(Homepage);
