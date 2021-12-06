import React from 'react';
import Router from 'next/router';
import css from 'styled-jsx/css';
import I18nLink from '@app/components/I18nLink';
import { withNamespaces } from '@app/i18nnext';
import {INavbarHeaderProps} from '@app/components/NavbarHeader/types';


const styles = css`
  .site-menubar {
    top: 48.1px !important;
    .site-menu > .site-menu-item.active {
      border: 0;
      //backgroundColor: '#26a69a',
    }
  }
`;

class MenuBar extends React.Component<INavbarHeaderProps, any> {

    constructor(props) {
        super(props);
        let path = window.location.href.substring(window.location.origin.length);
        this.state = {
            userCollapse: false,
            bgColor: '#263238',
            path,
            menuItems: [
                {
                   "className": "fa fa-upload",
                   "label": "Import Order",
                    "status": "",
                   "path": '/',
                },
            ],
        };

        this.state.userCollapse =
            /\/user-list*/g.test(path);

        this.state.deviceManagementCollapse =
            /\/firm-ware*/g.test(path) ||
            /\/upgrade-firmwave*/g.test(path) ||
            /\/exports*/g.test(path) ||
            /\/devices*/g.test(path) ||
            /\/device-group*/g.test(path);

        this.state.dashboardCollapse =
             /\/$/g.test(path);
    }
    logOut = async () => {
        await this.props.logOut();
        Router.push('/signin', '/auth/signin');
    };

  render () {
      return(
        <div
          className="site-menubar menu"
          onMouseOver={() => this.props.toggleSiteMenu(true)}
          onMouseLeave={() => this.props.toggleSiteMenu(false)}
        >
          <style jsx>{styles}</style>
          <div className="site-menubar-body">
            <div style={{
                height: '78px',
                display: 'flex',
                justifyContent: 'flex-start',
                alignItems: 'center',
                flexDirection: 'row',
                padding: '20px',
                position: 'ralative',
            }}>
                <div
                    className='app_name'
                    style={{
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'flex-start',
                        flexDirection: 'column',
                    }}
                >
                    <div className=""
                        style={{
                            color: '#fff',
                            fontSize: '12px',
                            fontWeight: '400',
                            paddingLeft: '20px',
                        }}
                    >{''}</div>
                    <div className=""
                        style={{
                            color: '#fff',
                            fontSize: '10px',
                            fontWeight: '400',
                            paddingLeft: '20px',
                            opacity: '0.5',
                        }}
                    >{''}</div>
                    <div
                        style = {{
                            position: 'absolute',
                            right: '15px',
                            fontSize: '18px',
                            color: '#fff'
                        }}>
                        <i className = "fa fa-gear"/>
                    </div>
                </div>
            </div>
            <ul className="site-menu scrollbar-black" data-plugin="menu"
                style={{maxHeight: '100%', overflow: 'auto', paddingBottom: '50px'}}
            >
              {!this.props.asPath.startsWith('/pl/') ?
              <>
                  <>
                      {
                          this.state.menuItems
                          .map((it, idx) => {
                              return (
                                  it.items?.length ?
                                      <li
                                        className={
                                          "site-menu-item has-sub " +
                                           (!!this.state.userCollapse && it.label === 'User Management' ||
                                            !!this.state.deviceManagementCollapse && it.label === 'Device Management' ||
                                            it.status === 'open' ? 'open' : '')
                                        }
                                        key={idx + '_'}>
                                          <a
                                            href="javascript:void(0)"
                                            onClick={() => {
                                                it.status = it.status === 'open' ? ''
                                                  : (!!this.state.userCollapse && it.label === 'User Management'
                                                  || !!this.state.deviceManagementCollapse && it.label === 'Device Management'
                                                  ? '' : 'open');
                                                this.setState({
                                                  menuItems: [].concat(this.state.menuItems),
                                                  userCollapse: false,
                                                  deviceManagementCollapse: false,
                                                });
                                            }}
                                          >
                                            <i className={"site-menu-icon " + it.className.replace('fa-fw', '')} aria-hidden="true"></i>
                                            <span className="site-menu-title">{it.label}</span>
                                            <span className="site-menu-arrow"/>
                                          </a>
                                          <ul className="site-menu-sub">
                                              {
                                                  it.items.map((subIt, subIdx) => {
                                                      return (
                                                          <I18nLink activeClassName="active" href={subIt.path || ('/' + subIt.label.toLowerCase().replace(/[ ]/g, '-') + '-list')} index={idx + '_' + subIdx} key={idx + '_' + subIdx}>
                                                              <li className="site-menu-item">
                                                                  <a href="javascript:void(0)"
                                                                     style={{
                                                                         color: '#fff',
                                                                         backgroundColor: 'inherit',
                                                                         paddingLeft: '55px',
                                                                     }}
                                                                     onClick = {() => {
                                                                         require('@app/utils/next-routes').Router.pushRoute(subIt.path || ('/' + subIt.label.toLowerCase().replace(/[ ]/g, '-') + '-list'));
                                                                     }}
                                                                  >
                                                                      <i className={"site-menu-icon " + subIt.className.replace('fa-fw', '')} aria-hidden="true"></i>
                                                                      <span className="site-menu-title">
                                                                          {subIt.label}
                                                                      </span>
                                                                  </a>
                                                              </li>
                                                          </I18nLink>
                                                      )
                                                  })
                                              }
                                          </ul>
                                      </li>
                                  :
                                  <I18nLink activeClassName={!!this.state.dashboardCollapse ? 'active' : 'none'} href={it.path || ('/' + it.label.toLowerCase().replace(/[ ]/g, '-') + '-list')} index={idx + '_'} key={idx + '_'}>
                                      <li className="site-menu-item">
                                          <a href="javascript:void(0)"
                                             style={{
                                                 color: '#fff',
                                                 backgroundColor: 'inherit'
                                             }}
                                             onClick = {() => {
                                                 require('@app/utils/next-routes').Router.pushRoute(it.path || ('/' + it.label.toLowerCase().replace(/[ ]/g, '-') + '-list'));
                                             }}
                                          >
                                              <i className={"site-menu-icon " + it.className.replace('fa-fw', '')} aria-hidden="true"></i>
                                              <span className="site-menu-title">
                                                  {it.label}
                                              </span>
                                          </a>
                                      </li>
                                  </I18nLink>
                              )
                          })

                      }
                  </>
                </>
                :
                <>
                    <li className="site-menu-item">
                        <a href="javascript:void(0)"
                            style={{
                                color : '#fff',
                                backgroundColor: !!this.state.smartSocketCollapse ? '#26a69a' : 'inherit'
                            }}
                            onClick={() => {
                                this.setState({smartSocketCollapse: !this.state.smartSocketCollapse, userCollapse: false}, () => {
                                    require('@app/utils/next-routes').Router.pushRoute('/pl/smart-sockets?gatewayId=' + this.props.gatewayId);
                                })

                            }}
                        >
                          <i className="site-menu-icon fa fa-cogs" aria-hidden="true"></i>
                          <span className="site-menu-title">
                            Smart Sockets
                          </span>
                        </a>
                    </li>
                    <li className="site-menu-item">
                        <a href="javascript:void(0)"
                            style={{
                                color : '#fff',
                                backgroundColor: !!this.state.wirelessSensorCollapse ? '#26a69a' : 'inherit'
                            }}
                            onClick={() => {
                                this.setState({wirelessSensorCollapse: !this.state.wirelessSensorCollapse, userCollapse: false}, () => {
                                    require('@app/utils/next-routes').Router.pushRoute('/pl/wireless-sensors?gatewayId=' + this.props.gatewayId);
                                })

                            }}
                        >
                          <img src='static/images/broadcast-white.png' style={{width: '18px', height: '18px', marginRight: '12px'}}/>
                          <span className="site-menu-title">
                            Wireless Sensors
                          </span>
                        </a>
                    </li>
                    <li className="site-menu-item">
                        <a href="javascript:void(0)"
                            style={{
                                color : '#fff',
                                backgroundColor: !!this.state.otherCollapse ? '#26a69a' : 'inherit'
                            }}
                            onClick={() => {
                                this.setState({otherCollapse: !this.state.otherCollapse, userCollapse: false}, () => {
                                    require('@app/utils/next-routes').Router.pushRoute('/pl/others?gatewayId=' + this.props.gatewayId);
                                })

                            }}
                        >
                          <i className="site-menu-icon fa fa-sitemap" aria-hidden="true"></i>
                          <span className="site-menu-title">
                            Others
                          </span>
                        </a>
                    </li>
                </>
                }
            </ul>
          </div>
          <div className="site-menubar-footer">
            <a href="javascript: void(0);" className="fold-show" data-placement="top" data-toggle="tooltip"
              data-original-title="Settings">
              <span className="icon wb-settings" aria-hidden="true"></span>
            </a>
            <I18nLink href="javascript: void(0);">
              <a href="javascript: void(0);">
                <span className="icon wb-user" aria-hidden="true"></span>
              </a>
            </I18nLink>
            <a
              href="javascript: void(0);" data-placement="top" data-toggle="tooltip" data-original-title="Logout">
              <span className="icon wb-power" aria-hidden="true"></span>
            </a>
          </div>
        </div>
      );
  }
};

export default withNamespaces('common')(MenuBar);
