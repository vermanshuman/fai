import { IGenericStoreAction } from '../types';

import { TOGGLE_SITE_MENU } from './types';

export interface ISiteMenuState {
  isToggleMenu: boolean;
}

export const initialState = {
  isToggleMenu: false,
};

const reducer = (
  state: ISiteMenuState = initialState, action: IGenericStoreAction = { type: null },
) => {
  switch (action.type) {
    case TOGGLE_SITE_MENU:
      const { isToggleMenu } = action.payload;
      return {
        ...state,
        isToggleMenu,
      };
    default:
      return state;
  }
};

export default reducer;
