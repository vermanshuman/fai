import { $post, $get, $put, $delete } from '../request';

const getUsers = async (params, req): Promise<any> => {
  params = params || {offset: 0, limit: 10000}
  const res = await $post('users', params, req);
  return res;
};
const getRolesOfUser = async (params, req, userId): Promise<any> => {
  const res = await $post(`user/${userId}/roles`, params, req);
  return res;
};

const getGroupsOfUser = async (params, req, userId): Promise<any> => {
  const res = await $post(`user/${userId}/groups`, params, req);
  return res;
};

const getPermissionsOfUser = async (params, req, userId): Promise<any> => {
  const res = await $post(`user/${userId}/permissions`, params, req);
  return res;
};
const getAccessLogsOfUser = async (params, req, userId): Promise<any> => {
  const res = await $post(`user/${userId}/accessLogs`, params, req);
  return res;
};

const createUser = async (params): Promise<any> => {
  const res = await $post('user', params);
  return res;
};

const deleteUser = async (params): Promise<any> => {
  const res = await $delete('user/' + params.id);
  return res;
};

const updateUser = async (params): Promise<any> => {
  const res = await $post('v1/updateProfile', params);
  return res;
};

const disableUser = async (userId): Promise<any> => {
  const res = await $put(`user/${userId}/disable`);
  return res;
};

const enableUser = async (userId): Promise<any> => {
  const res = await $put(`user/${userId}/enable`);
  return res;
};

const socialLogin = async (params): Promise<any> => {
  const res = await $post(`v1/user/social`, params);
  return res;
};

const logout = async (req): Promise<any> => {
  const res = await $get(req, `logout`);
  return res;
};

const login = async (params): Promise<any> => {
  const res = await $post(`_/oauth2/token`, params);
  return res;
};

const getProfile = async (req): Promise<any> => {
  const res = await $get(req, `whoami`);
  return res;
};

const sendForgotPassword = async (email): Promise<any> => {
  const res = await $post(`user/forgot-password/${email}`);
  return res;
};

const resetPassword = async (params): Promise<any> => {

  const res = await $post(`user/reset-password`, params);
  return res;
};

const userVerify = async (token): Promise<any> => {
  const res = await $get(null, `user/verify?token=${token}`);
  return res;
};

const getAccessLogs = async (params, req): Promise<any> => {
  const res = await $post('accessLogs', params, req);
  return res;
};

const getSystemLogs = async (req, command): Promise<any> => {
  const res = await $get(req, '/jc?&apiKey=(<3)&pwd=/&command=' + encodeURIComponent(command));
  return res;
};

const unassignRoleFromUser = async (userId, roleIds): Promise<any> => {
  const res = await $put(`user/${userId}/unassign/role/${roleIds}`);
  return res;
};

const assignRoleToUser = async (userId, roleIds): Promise<any> => {
  const res = await $put(`user/${userId}/assign/role/${roleIds}`);
  return res;
};

const hasPermission = (views, asPath, profile) => {

	if (!views || !(Object.keys(views).length)) {
		return true;
	}
	if (asPath && asPath.indexOf('?') != -1) {
		asPath = asPath.substring(0, asPath.indexOf('?'));
	}
	let v = views[asPath];

	if (!!v && v.status == 'DISABLE') return false;

	if (!v || !v.accept || v.accept === '*') return true;

	if (!profile || !profile.authorities || !profile.authorities.length) {
		return false;
	}

	let rules = v.accept.split(/ *, */g);
	for (let j = 0; j < rules.length; j++) {
		let rule = rules[j];
		for (let i = 0; i < profile.authorities.length; i++) {
			if (profile.authorities[i].authority === rule) {
				return true;
			}
		}
	}

	return false;
}

export {
    getUsers,
    createUser,
    updateUser,
    disableUser,
    enableUser,
    socialLogin,
    logout,
    login,
    getProfile,
    sendForgotPassword,
    resetPassword,
    userVerify,
    getRolesOfUser,
    getGroupsOfUser,
    getPermissionsOfUser,
    getAccessLogsOfUser,
    getAccessLogs,
    unassignRoleFromUser,
    assignRoleToUser,
    hasPermission,
    getSystemLogs,
    deleteUser,
};
