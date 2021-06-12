const PROFESSIONS_BY_SPECIALIZATION_EXAMPLE_DATA = [
  {
    specializationId: 1,
    code: '01.03.01',
    name: 'Математика',
    description: 'Математика - самая древняя наука',
    professionsList: [
      {
        id: 1,
        name: 'Аналитик данных',
      },
      {
        id: 2,
        name: 'Криптограф',
      },
      {
        id: 6,
        name: 'Бухгалтер',
      },
      {
        id: 7,
        name: 'Инженер',
      },
      {
        id: 8,
        name: 'Экономист',
      },
    ],
  },
  {
    specializationId: 2,
    code: '02.03.01',
    name: 'Информатика',
    description: 'Информатика - самая молодая наука',
    professionsList: [
      {
        id: 3,
        name: 'Программист',
      },
      {
        id: 4,
        name: 'Консультант по SAP',
      },
    ],
  },
  {
    specializationId: 3,
    code: '03.03.01',
    name: 'Физика',
    description: 'Физика - наука, которая появилась немного позже математики',
    professionsList: [
      {
        id: 5,
        name: 'Инженер-технолог',
      },
    ],
  },
];

// в этой функции будем доставать данные с бэка, а пока заглушка:
export default function getSpecializationData(id) {
  return PROFESSIONS_BY_SPECIALIZATION_EXAMPLE_DATA.find(
    (specialization) => Number(specialization.specializationId) === Number(id)
  );
}

export async function getFilteredSpecializationsList(
  queryParameterNames,
  queryParameters
) {
  let specializationsFilteredExampleData = PROFESSIONS_BY_SPECIALIZATION_EXAMPLE_DATA;
  queryParameters.forEach((parameter, index) => {
    if (parameter) {
      specializationsFilteredExampleData = specializationsFilteredExampleData.filter(
        (univer) =>
          parameter.localeCompare(univer[queryParameterNames[index]]) === 0
      );
    }
  });
  const promise = new Promise((resolve) => {
    setTimeout(() => resolve(specializationsFilteredExampleData), 500);
  });
  const result = await promise;
  return result;
}
