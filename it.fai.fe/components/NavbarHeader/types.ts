import { IBasicComponentProps } from '@app/components/Types';

interface INavbarHeaderProps extends IBasicComponentProps {
  isToggle: boolean;
  userInfo: any;
  toggleSiteMenu: (toggle: boolean) => void;
  logOut: () => void;
}

export {
  INavbarHeaderProps,
};
