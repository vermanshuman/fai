import { key } from '.';
import { ISiteMenuState } from './reducer';

export const getReducer = (state): ISiteMenuState => state[key];
export const getSiteMenu = state => getReducer(state).isToggleMenu;
