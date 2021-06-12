const PROFESSIONS_EXAMPLE_DATA = [
  {
    professionId: 1,
    name: 'Аналитик данных',
    description: 'Он анализирует данные, делает выводы',
    specializationList: [
      {
        id: 1,
        code: '01.03.01',
        name: 'Математика',
        description: 'Математика - самая древняя наука',
      },
      {
        id: 2,
        code: '02.03.01',
        name: 'Информатика',
        description: 'Информатика - самая молодая наука',
      },
    ],
  },
  {
    professionId: 2,
    name: 'Криптограф',
    description: 'Он шифрует и расшифровывает данные',
    specializationList: [
      {
        id: 1,
        code: '01.03.01',
        name: 'Математика',
        description: 'Математика - самая древняя наука',
      },
      {
        id: 2,
        code: '02.03.01',
        name: 'Информатика',
        description: 'Информатика - самая молодая наука',
      },
      {
        id: 3,
        code: '03.03.01',
        name: 'Физика',
        description:
          'Физика - наука, которая появилась немного позже математики',
      },
    ],
  },
];

export default async function getFilteredProfessionsList(
  queryParameterNames,
  queryParameters
) {
  let professionsFilteredExampleData = PROFESSIONS_EXAMPLE_DATA;
  queryParameters.forEach((parameter, index) => {
    if (parameter) {
      professionsFilteredExampleData = professionsFilteredExampleData.filter(
        (univer) =>
          parameter.localeCompare(univer[queryParameterNames[index]]) === 0
      );
    }
  });
  const promise = new Promise((resolve) => {
    setTimeout(() => resolve(professionsFilteredExampleData), 500);
  });
  const result = await promise;
  return result;
}
