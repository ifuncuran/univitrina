const pathApi = 'api';

export default async (prefix, direction) => {
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
