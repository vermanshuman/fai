import { connect } from 'react-redux';

import NavbarHeader from './NavbarHeader';

import { toggleSiteMenu } from '@app/redux/site-menu/actions';
import { logOut } from '@app/redux/user/actions';
import { getSiteMenu } from '@app/redux/site-menu/selectors';
import { getReducer } from '@app/redux/user/selectors';

const mapStateToProps = state => ({
  isToggle: getSiteMenu(state),
  userInfo: getReducer(state),
});

const mapDispatchToProps = {
  toggleSiteMenu,
  logOut,
};

export default connect(mapStateToProps, mapDispatchToProps)(NavbarHeader);
