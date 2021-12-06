import * as fetch from 'isomorphic-fetch';

import { getCookie, eraseCookie } from '@app/utils/cookie';
import { tokenName } from '@app/utils/public-config';
import { toastrs } from '@app/utils/toastr';
import Router from 'next/router';

const BASE_URL = 'api';

export interface IAPIResponse {
  data: any;
  message: string;
  result: boolean;
}

export interface IQueryParams {
  pageIndex: number;
  pageSize: number;
  keyWord: string;
  searchType?: number;
  totalRecords?: number;
  totalPages?: number;
}

export interface IRecorderParams {
  month: number;
  year: number;
}
export interface ICheckinParams {
  latitude: number;
  longitude: number;
}

const getApiUrl = (req?) => {
  if (req) {
    return process.env.WWW_API_SERVICE_HOST;
  }
  try { return `${window.location.origin}`; } catch (error) { return process.env.WWW_API_SERVICE_HOST;}
};

const request: any = async (req?: any, uri?: string, method?: string, body?: any) => {

  const url = `${getApiUrl(req)}/${BASE_URL}/${uri}`;
  let token = getCookie(tokenName, req);
  if (!token || token === 'null') token = undefined;
  try {
    const res = await fetch(url, {
      method,
      body: JSON.stringify(body),
      credentials: 'include',
      headers: !token ? {'Content-Type': 'application/json'} : {
        // tslint:disable-next-line:object-literal-key-quotes
        'Authorization': token,
        'Content-Type': 'application/json',
      },
    });
    if (res.status === 401) {
      toastrs.show('error', res.statusText || 'Can not get API.');
      eraseCookie(tokenName);
      Router.push('/signin', '/auth/signin');
      return {
        message: '',
        data: [],
        result: false,
      };
    }
    const json = await res.json();

    return json;
  } catch (error) {
    toastrs.show('error', 'Service unavailable !');
    //throw new Error(error);
    console.info(error, uri);
    return {
      message: '',
      data: [],
      success: false,
    };
  }
};

const $get = async (req?: any, uri?: string) => request(req, uri, 'GET', undefined);
const $post = async (uri: string, body?: any, req?: any) => request(req, uri, 'POST', body);
const $put = async (uri: string, body?: any, req?: any) => request(req, uri, 'PUT', body);
const $delete = async (uri: string, body?: any, req?: any) => request(req, uri, 'DELETE', body);

export {
  BASE_URL,
  getApiUrl,
  request,
  $get,
  $post,
  $put,
  $delete,
};
