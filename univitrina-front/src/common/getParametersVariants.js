import { requestDictionary } from './request';

export async function getProfessionsParametersVariants() {
  const response = {};
  const speciality = await requestDictionary('speciality');
  response.speciality = speciality;
  return response;
}

export async function getSpecializationsParametersVariants() {
  const response = {};
  const university = await requestDictionary('university');
  response.university = university;
  const profession = await requestDictionary('profession');
  response.profession = profession;
  return response;
}

export default async function getUniversitiesParametersVariants() {
  const response = {};
  const area = await requestDictionary('area');
  response.area = area;
  const speciality = await requestDictionary('speciality');
  response.speciality = speciality;
  return response;
}
