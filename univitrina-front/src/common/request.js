import getSpecializationData from './getSpecializationData';

const pathApi = 'api';

export const requestDataForSuggest = async (prefix, direction) => {
  try {
    const pathSuggest = '/search/suggest';
    const urlSuggest = `${pathApi}${pathSuggest}`;
    const url = `${urlSuggest}${direction}?prefix=${prefix}`;
    const response = await fetch(url);
    const list = await response.json();
    return list ?? [];
  } catch {
    return [];
  }
};

export const requestDataForSpecializationPage = async (id) => {
  // пока заглушка
  const result = await getSpecializationData(id);
  return result;
};
