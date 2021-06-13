import { requestDataForListPage } from './request';

export default async function getFilteredUniversitiesList(query) {
  const response = await requestDataForListPage('universities', query);
  return response;
}
