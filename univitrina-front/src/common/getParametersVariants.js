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

const SPECIALIZATIONS_PARAMETERS_VARIANTS = {
  university: [
    {
      id: 1,
      name: 'МГУ',
    },
    {
      id: 2,
      name: 'ЛГУ',
    },
    {
      id: 3,
      name: 'СГУ',
    },
  ],
  profession: [
    {
      id: 1,
      name: 'Бизнес-аналитик',
    },
    {
      id: 2,
      name: 'Технический писатель',
    },
    {
      id: 3,
      name: 'Инженер',
    },
  ],
  training_direction: [
    {
      id: 1,
      name: 'Математика и технические науки',
    },
    {
      id: 2,
      name: 'Машиностроение',
    },
    {
      id: 3,
      name: 'Менеджмент',
    },
  ],
};

const PROFESSIONS_PARAMETERS_VARIANTS = {
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

export async function getSpecializationsParametersVariants() {
  const promise = new Promise((resolve) => {
    setTimeout(() => resolve(SPECIALIZATIONS_PARAMETERS_VARIANTS), 100);
  });
  const result = await promise;
  return result;
}

export async function getProfessionsParametersVariants() {
  const promise = new Promise((resolve) => {
    setTimeout(() => resolve(PROFESSIONS_PARAMETERS_VARIANTS), 100);
  });
  const result = await promise;
  return result;
}
