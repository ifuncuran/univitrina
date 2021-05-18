import { Typography } from '@material-ui/core';
import React from 'react';
import { useParams } from 'react-router-dom';
import SpecializationCard from '../../components/SpecializationCard';
import UniversityInfo from '../../components/UniversityInfo';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import MainContainer from '../../components/MainContainer';
import useStyles from './style';

const universitiesExtendedExampleData = [
  {
    title: 'Московский государственный университет имени М.В.Ломоносова',
    description: 'Лучший Московский ВУЗ',
    area: 'MoscowRegion',
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
    area: 'LeningradRegion',
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
    area: 'SverdlovskRegion',
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
function getUniversityData(id) {
  return universitiesExtendedExampleData.filter(
    (univer) => Number(univer.id) === Number(id)
  )[0];
}

function ListCards(specializationList) {
  return specializationList.map(({ id, name, code, description }) => (
    <SpecializationCard
      key={id}
      specializationId={id}
      title={name}
      code={code}
      description={description}
    />
  ));
}

function UniversityPage() {
  const classes = useStyles();

  const isNotMobile = useIsNotMobie();

  const { id } = useParams();
  const UniversityData = getUniversityData(id);

  const SpecializationList = ListCards(UniversityData.specializationList);

  return (
    <section position="static">
      <UniversityInfo
        title={UniversityData.title}
        description={UniversityData.description}
        extendedDescription={UniversityData.extendedDescription}
      />
      <MainContainer fixed={isNotMobile}>
        <Typography className={classes.title} variant="h2">
          Специальности
        </Typography>
        {SpecializationList}
      </MainContainer>
    </section>
  );
}

export default () => <UniversityPage />;
