import { requestDataForListPage } from './request';

export default async function getFilteredProfessionsList(query) {
  // подменяем speciality на specialty_id
  const searchParams = new URLSearchParams(query);
  if (searchParams.has('speciality')) {
    searchParams.append('specialty_id', searchParams.get('speciality'));
    searchParams.delete('speciality');
  }
  // подменяем text на profession_name
  if (searchParams.has('text')) {
    searchParams.append('profession_name', searchParams.get('text'));
    searchParams.delete('text');
  }

  const response = await requestDataForListPage(
    'professions',
    `?${searchParams.toString()}`
  );
  return response;
}
