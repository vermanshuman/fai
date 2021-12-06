import { connect } from 'react-redux';

import { logOut } from '@app/redux/user/actions';
import { getReducer } from '@app/redux/user/selectors';
import { toggleSiteMenu } from '@app/redux/site-menu/actions';

import MenuBar from './MenuBar';
import { getSiteMenu } from '@app/redux/site-menu/selectors';

const mapStateToProps = state => ({
  isToggle: getSiteMenu(state),
  userInfo: getReducer(state),
});

const mapDispatchToProps = {
  logOut,
  toggleSiteMenu,
};

export default connect(mapStateToProps, mapDispatchToProps)(MenuBar);
