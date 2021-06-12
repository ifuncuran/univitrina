const pathApi = 'api';
const { host } = window.location;
const { protocol } = window.location;
const baseUrl = `${protocol}//${host}`;

export const requestDataForSuggest = async (prefix, direction) => {
  try {
    let pathDirection;
    switch (direction) {
      case '/specializations':
        pathDirection = '/specialty';
        break;
      case '/professions':
        pathDirection = '/profession';
        break;
      case '/universities':
        pathDirection = '/university';
        break;
      default:
        return [];
    }
    const pathSuggest = '/search/suggest';
    const urlSuggest = `${pathApi}${pathSuggest}`;
    const url = `${urlSuggest}${pathDirection}?prefix=${prefix}`;
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
    const result = await response.json();
    return result ?? null;
  } catch {
    return null;
  }
};

export const requestDataForProfessionPage = async (id) => {
  try {
    const path = `/professions/${id}`;
    const url = `${baseUrl}/${pathApi}${path}`;
    const response = await fetch(url);
    const result = await response.json();
    return result ?? null;
  } catch {
    return null;
  }
};
