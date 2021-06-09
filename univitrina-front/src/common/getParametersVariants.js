const UNIVERSITIES_PARAMETERS_VARIANTS = {
  area: [
    {
      id: 1,
      name: 'Москва',
    },
    {
      id: 2,
      name: 'Санкт-Петербург',
    },
    {
      id: 3,
      name: 'Екатеринбург',
    },
  ],
  speciality: [
    {
      id: 1,
      name: 'Математика',
    },
    {
      id: 2,
      name: 'Информатика',
    },
    {
      id: 3,
      name: 'Физика',
    },
  ],
};

export default async function getUniversitiesParametersVariants() {
  const promise = new Promise((resolve) => {
    setTimeout(() => resolve(UNIVERSITIES_PARAMETERS_VARIANTS), 100);
  });
  const result = await promise;
  return result;
}
