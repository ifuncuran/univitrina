import { requestDataForListPage } from './request';

export default async function getFilteredSpecializationsList(query) {
  const response = await requestDataForListPage('specialties', query);
  return response;
}
