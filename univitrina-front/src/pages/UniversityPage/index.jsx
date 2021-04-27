import React from 'react';
import { useParams } from 'react-router-dom';
import SpecializationCard from '../../components/SpecializationCard';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import MainContainer from '../../components/MainContainer';

const universitiesExtendedExampleData = [
  {
    title: 'МГУ',
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
    title: 'ЛГУ',
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
    title: 'СГУ',
    description: 'Лучший Екатеринбургский ВУЗ',
    area: 'SverdlovskRegion',
    id: 3,
    extendedDescription: 'эту информацию вы не видели на предыдущей странице',
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
  const isNotMobile = useIsNotMobie();

  const { id } = useParams();

  const UniversityData = getUniversityData(id);

  const SpecializationList = ListCards(UniversityData.specializationList);

  return (
    <section position="static">
      <MainContainer fixed={isNotMobile}>
        <p>
          <strong>ID: {id} </strong>
        </p>
        <p>{UniversityData.title}</p>
        <p>{UniversityData.description}</p>
        <p>{UniversityData.extendedDescription}</p>
        {SpecializationList}
      </MainContainer>
    </section>
  );
}

export default () => (
  <>
    <p>Страница института</p>
    <UniversityPage />
  </>
);
