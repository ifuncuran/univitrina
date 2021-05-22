const UNIVERSITIES_EXAMPLE_DATA = [
  {
    title: 'Московский государственный университет имени М.В.Ломоносова',
    description: 'Лучший Московский ВУЗ',
    area: 1,
    id: 1,
    extendedDescription: 'эту информацию вы не видели на предыдущей странице',
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
    title: 'Ленинградский государственный университет имени А.С.Пушкина',
    description: 'Лучший Санкт-Петербургский ВУЗ',
    area: 2,
    id: 2,
    extendedDescription: 'эту информацию вы не видели на предыдущей странице',
    specializationList: [
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
  {
    title:
      'Уральский федеральный университет имени первого Президента России Б.Н. Ельцина',
    description: 'Лучший Екатеринбургский ВУЗ',
    area: 3,
    id: 3,
    extendedDescription:
      'Уральский федеральный университет создан в контексте реализации концепции долгосрочного развития Российской Федерации как один из глобальных лидеров образования и научно-инновационных разработок. Миссия университета — повышение международной конкурентоспособности Уральского региона и обеспечение реиндустриализации, наращивание человеческого и научно-технического потенциала, сбалансированное обновление традиционных и развитие постиндустриальных отраслей экономики России, в первую очередь на территории Уральского федерального округа.',
    specializationList: [
      {
        id: 1,
        code: '01.03.01',
        name: 'Математика',
        description: 'Математика - самая древняя наука',
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

// в этой функции будем доставать данные с бэка, а пока заглушка:
export default function getUniversitiesData(id) {
  return UNIVERSITIES_EXAMPLE_DATA.find(
    (univer) => Number(univer.id) === Number(id)
  );
}

export async function getFilteredUniversitiesList(
  queryParameterNames,
  queryParameters
) {
  let universitiesFilteredExampleData = UNIVERSITIES_EXAMPLE_DATA;
  queryParameters.forEach((parameter, index) => {
    if (parameter) {
      universitiesFilteredExampleData = universitiesFilteredExampleData.filter(
        (univer) =>
          parameter.localeCompare(univer[queryParameterNames[index]]) === 0
      );
    }
  });
  const promise = new Promise((resolve) => {
    setTimeout(() => resolve(universitiesFilteredExampleData), 500);
  });
  const result = await promise;
  return result;
}
