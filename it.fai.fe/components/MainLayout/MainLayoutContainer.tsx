import { connect } from 'react-redux';

import { MainLayout } from './MainLayout';

import { toggleSiteMenu } from '@app/redux/site-menu/actions';
import { getSiteMenu } from '@app/redux/site-menu/selectors';

const mapStateToProps = state => ({
  isToggle: getSiteMenu(state),
});

const mapDispatchToProps = {
  toggleSiteMenu,
};

export default connect(mapStateToProps, mapDispatchToProps)(MainLayout);
