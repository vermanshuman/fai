import { $post, $get, $put, $delete } from '../request';

const getAllMagazzino = async (params, req): Promise<any> => {
  const res = await $get(req, 'magazzino/all');
  return res;
};

const faiImportCsv = async (data, params): Promise<any> => {
  const url = 'fai/import/csv' + '?acronym=' + params;
  const res = await $post(url, data);
  return res;
};

export {
  getAllMagazzino,
  faiImportCsv,
};
