import { TOGGLE_SITE_MENU } from './types';
import { getSiteMenu } from './selectors';

const toggleSiteMenu = (isToggleMenu: boolean) =>
  async (dispatch, getState) => {
    if (getSiteMenu(getState()) !== isToggleMenu) {
      dispatch({
        type: TOGGLE_SITE_MENU,
        payload: { isToggleMenu },
      });
    }
  };

export  {
  toggleSiteMenu,
};
