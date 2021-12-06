import * as React from 'react';
import Router from 'next/router';
import { connect } from 'react-redux';
import css from 'styled-jsx/css';
import { Modal, ModalBody } from 'reactstrap';
import { ProgressLoading } from '@app/components/Common';
import { IBasicComponentProps } from '@app/components/Types';
import I18nLink from '@app/components/I18nLink';
import { checkLoginedOrNot, redirect, checkLoginedOrNotAndIgnoreTrue } from '@app/utils/app-init';

import { UserForm } from './UserForm';
import { loadGoogleApi } from './utils';

import { socialLogin, login, getProfile, forgotPassword, sendForgotPassword } from '@app/api/user';
import { tokenName } from '@app/utils/public-config';
import { setCookie, eraseCookie, getCookie } from '@app/utils/cookie';
const Swal = require('sweetalert2');

const getParameterByName = (name, url) => {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

class LoginKit extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			isOn: !!this.props.isOn,
		}
	}

	async componentDidMount() {
		/*loadGoogleApi(async (isSignedIn, userInfo) => {

			if (!!this.state.isLoggedIn) {
				return;
			}

			if (!!isSignedIn && !!this.props.userInfo && !this.props.userInfo.userId) {
				let params = {
					accessToken: userInfo.accessToken,
					avatar: userInfo.picture,
					email: userInfo.email,
					firstName: userInfo.firstName,
					lastName: userInfo.lastName,
					type: 'google',
				}
				let res = await socialLogin(params);
				if (!res || !res.response || !res.response.token) {
					return;
				}
			}
		});

		this.props._ref && this.props._ref(this);
		let code = getParameterByName('code');
		if (!!code) {
			this.props.dispatch({ type: "HEADER_LOADING", payload: true });
			await this.signIn('normal', {code: code, grant_type: 'authorization_code'})
			this.props.dispatch({ type: "HEADER_LOADING", payload: false });
			return;
		}
		this.checkSSO();*/
	}
	
	getPrevious() {
		
		if (typeof localStorage == 'object') {
			let previousUrl = getCookie('__login_success__');
			if (previousUrl) {
				eraseCookie('__login_success__', '');
			}
			return previousUrl;
		}
	}
	 
	responseGoogle = async (userInfo) => {
		let params = {
			accessToken: userInfo.accessToken,
			avatar: userInfo.picture,
			email: userInfo.email,
			firstName: userInfo.firstName,
			lastName: userInfo.lastName,
			type: 'google',
		}
		let res = await socialLogin(params);
		if (res && res.response && res.response.token) {
			const token = `Bearer ${ res.response.token }`;
			setCookie(tokenName, token);
			this.setState({
				userInfo: userInfo,
				isLoggedIn: 1,
			}, async () => {
				this.props.setUserInfo(this.state.userInfo);
				if (!this.checkSSO()) {
					if (!this.props.popup) {
						require('@app/utils/next-routes').Router.pushRoute(this.getPrevious() || '/');
					} else {
						this.props.onSuccess && this.props.onSuccess();
					}
				}

				this.setState({loading: false,});
			});
		} else {
			this.setState({loading: false,})
			try {window.gapi.auth2.getAuthInstance().signOut();} catch(err) {console.info(err);}
		}
	}

	async signIn(type, params) {

		await this.setState({loading: true,})

		if (type == 'facebook' || type == 'zalo') {

			alert('Chưa hỗ trợ login bằng Facebook và Zalo')
			return;
		}

		if (type == 'normal') {

			try {
				params = params || {
					email: this.state.username,
					password: this.state.password,
					grant_type: 'password',
				}

				let res = await await fetch('api/v1/login', {
					method: 'POST',
					body: JSON.stringify(params),
					credentials: 'include',
					headers: {
						'Content-Type': 'application/json',
					},
				});
				res = await res.json();
                if (res && res.response && res.response.token) {
                    const token = `Bearer ${ res.response.token }`;
                    setCookie(tokenName, token);
					if (!this.checkSSO()) {
						if (!this.props.popup) {
							require('@app/utils/next-routes').Router.pushRoute(this.getPrevious() || '/');
						} else {
							this.props.onSuccess && this.props.onSuccess();
							let resProfile = await getProfile();
							this.props.setUserInfo(resProfile.data);
						}
					}
				} else {
					alert('Error: ' + (res.error.message || 'Username or password invalid!'));
					this.setState({loading: false,})
				}
			} catch(err) {this.setState({loading: false,}); console.info(err);}

			return;
		}
		if (type == 'google') {
			try {
				window.gapi.auth2.getAuthInstance().signIn()
				.then(response => {

					let profile = window.gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile();
					let access_token = window.gapi.auth2.getAuthInstance().currentUser.get().getAuthResponse().access_token;
					let userInfo = {
						userId: profile.getId(),
						name: profile.getName(),
						firstName: profile.getGivenName(),
						lastName: profile.getFamilyName(),
						email: profile.getEmail(),
						picture: profile.getImageUrl(),
						accessToken: access_token,
					}
					this.responseGoogle(userInfo);
				})
				.catch(err => {
					console.info(err);
					this.props.setUserInfo({});
				})
			} catch(e) {
			}
			return;
		}
	}

	checkSSO() {

		let client_id = getParameterByName('client_id');
		let redirect_uri = getParameterByName('redirect_uri');
		let isTokenExisted = getCookie(tokenName, null) && getCookie(tokenName, 'LOGIN_INFO');
		if (!!client_id && !!redirect_uri && isTokenExisted) {
			let url = 'api/_/oauth2/authorize';
			url += '?client_id=' + client_id + '&redirect_uri=' + redirect_uri + '&response_type=code';
			window.location.href = url;
			return true;
		}
		return false;
	}

	render0() {

	    if (this.props.type === 'form-edit') {
	        return <UserForm type={this.props.type} data={{...this.props.userInfo}} onSuccess={this.props.onSuccess}/>
	    }

		return (
			<div id="login-box" style={{maxWidth: '350px', width: '100%', margin: 'auto',}}>
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
									})
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
									})
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
                		style={{top: '180px', bottom: undefined,}}
                	/>
                : null}
			</div>
		);
	}

	hide() {
		this.setState({isShowModal: false, loading: false,})
	}
	show() {
		this.setState({isShowModal: true,})
	}

	render() {

		if (!this.props.popup)
		return this.render0();
		return (
			<Modal
				centered
				isOpen={this.state.isShowModal}
				toggle={this.handleToggle}
				style={{zIndex: 3001,}}
				className='login-kit-popup'
			>
				<ModalBody>
					<div style={{position: 'absolute', top: '-10px', right: '-10px', width: '20px', height: '20px',
						borderRadius: '50%',
						cursor: 'pointer',
						backgroundColor: '#ffffff',
						boxShadow: 'rgb(150, 150, 150) -1px 1px 1px',
						display: 'flex',
						justifyContent: 'center',
						alignItems: 'center',
					}}
						onClick={() => {this.hide();}}
					>
						<i className="fa fa-close"/>
					</div>
					{this.render0()}
				</ModalBody>
			</Modal>
		)
	}
}

const mapStateToProps = state => {
	return {
	    userInfo: state.userInfo || {},
	}
};

const mapDispatchToProps = dispatch => {
	return {
		setUserInfo: (userInfo) => dispatch({type: 'SET_USER_INFO', payload: userInfo}),
		dispatch,
	}
};

LoginKit = connect(mapStateToProps, mapDispatchToProps)(LoginKit);

export { LoginKit };
