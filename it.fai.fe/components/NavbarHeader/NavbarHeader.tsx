import * as React from 'react';
import { Dropdown, UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import Router from 'next/router';
import { i18n, Trans, withNamespaces } from '@app/i18nnext';

import { Avatar } from '@app/components/Common';
import { INavbarHeaderProps } from './types';
import { tokenName } from '@app/utils/public-config';
import { setCookie, getCookie, eraseCookie } from '@app/utils/cookie';
class NavbarHeader extends React.PureComponent<INavbarHeaderProps, any> {

  state = {
    dropdownLanOpen: false,
  };

  toggleLang = () => {
    this.setState(prevState => ({
      dropdownLanOpen: !prevState.dropdownLanOpen,
    }));
  }

  logOut = async () => {
    eraseCookie(tokenName);
    await this.props.logOut();
    Router.push('/signin', '/auth/signin');
  }

  profile = async () => {
    Router.push('/profile', '/profile');
  }

  changeLanguage = (language: string) => {
    i18n.changeLanguage(language === 'vn' ? 'en' : 'vn');
    return;
  }

  render() {
    const { userInfo } = this.props;
    return (
      <nav className="site-navbar navbar navbar-default navbar-fixed-top navbar-mega">
        <style jsx>{`
          .site-navbar {
            .navbar-header {
              :global(.navbar-toggler .nav-link) {
                background-color: unset;
              }
              :global(.navbar-brand) {
                  height: 100% !important;
                  max-height: 100% !important;
                  display: flex !important;
                  justify-content: center !important;
                  align-items: center !important;
                  img {
                    height: 16px !important;
                  }
              }
            }
          }
          :global(.site-navbar .navbar-container) {
              background-color: #324148 !important;
              max-height: 48px !important;
              min-height: unset !important;
              
              :global(.navbar-collapse) {
                  max-height: 48px !important;
                  min-height: unset !important;
                  
                  :global(.dropdown) {
                      height: 48px !important;
                      max-height: 48px !important;
                      min-height: unset !important;
                  }
              }
              :global(.nav-link) {
                  min-height: unset !important;
                  max-height: 100% !important;
                  display: flex !important;
                  flex-direction: column !important;
                  justify-content: center !important;
                  align-items: center !important;
              }
          }
          :global(.site-navbar) {
              background-color: #324148 !important;
          }
          :global(.navbar) {
              border-top: 1px solid transparent !important;
              border-bottom: 1px solid rgba(255,255,255,.1) !important;
              -ms-flex-align: stretch !important;
              align-items: stretch !important;
              max-height: 48px !important;
              min-height: unset !important;
              
              :global(.navbar-header) {
                  max-height: 48px !important;
                  min-height: unset !important;
                  button[data-toggle='menubar'] {
                    max-height: 100%;
                    margin-left: unset;
                    margin-top: unset;
                    padding-top: 13px;
                    width: 48px;
                  }
                  :global(div.navbar-toggler) {
                    max-height: 100% !important;
                    margin-left: unset;
                    margin-top: unset;
                    padding: unset;
                    padding-top: 13px;
                    width: 48px !important;
                  }
              }
          }
        `}</style>
        <div className="navbar-header">
          <button
            onClick={() => this.props.toggleSiteMenu(!this.props.isToggle)}
            type="button"
            className={`navbar-toggler
            hamburger hamburger-close navbar-toggler-left ${!this.props.isToggle ? 'hided' : ''}`}
            data-toggle="menubar">
            <span className="sr-only">Toggle navigation</span>
            <span className="hamburger-bar"></span>
          </button>
          <UncontrolledDropdown setActiveFromChild className="navbar-toggler m-0">
            <DropdownToggle href="javascript:void(0)" tag="a" className="nav-link white d-inline">
              <i className="icon wb-more-horizontal" aria-hidden="true"></i>
            </DropdownToggle>
            <DropdownMenu>
              <DropdownItem
                tag="a"
                onClick={() => this.props.toggleSiteMenu(!this.props.isToggle)}
                href="javascript:void(0)"
              >
                <i className="icon wb-user" aria-hidden="true"></i><Trans i18nKey="PROFILE" />
              </DropdownItem>
              <DropdownItem
                tag="a"
                onClick={this.logOut}
                href="javascript:void(0)"
              >
                <i className="icon wb-power" aria-hidden="true"></i><Trans i18nKey="LOGOUT" />
              </DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
          <div className="navbar-brand navbar-brand-center" data-toggle="gridmenu" style={{width: '100%'}}>
            <div style={{minWidth: '80px', minHeight: '48px', display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}} className='group-brand-logo'>
                <img className="navbar-brand-logo" src="/static/images/logo2.png" title="Remark" style={{}} />
                <div
                    style={{
                        flex: '1',
                        display: 'flex',
                    }}
                >
                    <i className='fa fa-bars' style={{fontSize: '22px', cursor: 'pointer'}}
                        onClick={() => this.props.toggleSiteMenu(!this.props.isToggle)}
                    />
                </div>
            </div>
          </div>
        </div>

        <div className="navbar-container container-fluid">
          <div className="collapse navbar-collapse navbar-collapse-toolbar">
            <ul className="nav navbar-toolbar navbar-right navbar-toolbar-right">
              <li className="nav-item dropdown">
                <Dropdown isOpen={this.state.dropdownLanOpen} toggle={this.toggleLang}>
                  <DropdownToggle href="javascript:void(0)" tag="a" className="nav-link">
                    <span className="text-uppercase">{i18n.language}</span>
                  </DropdownToggle>
                  <DropdownMenu>
                    <DropdownItem
                      tag="a"
                      href="javascript:void(0)"
                      onClick={() => this.changeLanguage('en')}
                    >
                      Tiếng việt
                    </DropdownItem>
                    <DropdownItem
                      tag="a"
                      href="javascript:void(0)"
                      onClick={() => this.changeLanguage('vn')}
                    >
                      English
                    </DropdownItem>
                  </DropdownMenu>
                </Dropdown>
              </li>
              <li className="nav-item dropdown">
                <UncontrolledDropdown setActiveFromChild>
                  <DropdownToggle href="javascript:void(0)" tag="a" className="nav-link navbar-avatar">
                    <Avatar
                      isOnline={userInfo.isCheckin}
                      src={userInfo.avatar}
                      gender={userInfo.gender}
                    />
                  </DropdownToggle>
                  <DropdownMenu>
                    <DropdownItem
                      tag="a"
                      onClick={() => this.props.toggleSiteMenu(!this.props.isToggle)}
                      href="javascript:void(0)"
                    >
                      <i className="icon wb-user" aria-hidden="true"></i><Trans i18nKey="PROFILE" />
                    </DropdownItem>
                    <DropdownItem
                      tag="a"
                      onClick={this.logOut}
                      href="javascript:void(0)"
                    >
                      <i className="icon wb-power" aria-hidden="true"></i><Trans i18nKey="LOGOUT" />
                    </DropdownItem>
                  </DropdownMenu>
                </UncontrolledDropdown>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    );
  }
}

export default withNamespaces('common')(NavbarHeader);
