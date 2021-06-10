const pathApi = 'api';
const { host } = window.location;
const { protocol } = window.location;
const baseUrl = `${protocol}//${host}`;

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
  try {
    const path = `/specialties/${id}`;
    const url = `${baseUrl}/${pathApi}${path}`;
    const response = await fetch(url);
    const list = await response.json();
    return list ?? null;
  } catch {
    return null;
  }
};
