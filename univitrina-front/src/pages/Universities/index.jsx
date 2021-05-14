import React from 'react';
import { Link } from 'react-router-dom';
import useQuery from '../../hooks/useQuery';

// база странички списка - для того, чтобы показать как будет работать роутинг (плюс можно начать верстать без получения данных с бэка):

const universitiesExampleData = [
  {
    title: 'МГУ',
    description: 'Лучший Московский ВУЗ',
    area: 'MoscowRegion',
    id: 1,
  },
  {
    title: 'ЛГУ',
    description: 'Лучший Санкт-Петербургский ВУЗ',
    area: 'LeningradRegion',
    id: 2,
  },
  {
    title: 'СГУ',
    description: 'Лучший Екатеринбургский ВУЗ',
    area: 'SverdlovskRegion',
    id: 3,
  },
];

function Universities() {
  const query = useQuery();
  const selectedArea = query.get('area');

  // пример удаления фильтра
  function DeleteArea(searchParam) {
    query.delete('area', searchParam);
    return query.toString();
  }

  // пример фильтра (скорее всего будет на бэке)
  let filterUniversities = universitiesExampleData;
  if (selectedArea) {
    filterUniversities = universitiesExampleData.filter(
      (univer) => selectedArea.localeCompare(univer.area) === 0
    );
  }

  const ListFilterResult = filterUniversities.map(
    ({ title, description, area, id }) => (
      <Link
        to={{
          pathname: `/universities/${id}`,
          search: DeleteArea(selectedArea),
        }}
        key={id}
      >
        <p>{title}</p>
        <p>{description}</p>
        <p>{area}</p>
        <p>{id}</p>
      </Link>
    )
  );

  return (
    <>
      {selectedArea ? (
        <Link
          to={{ pathname: '/universities', search: DeleteArea(selectedArea) }}
        >
          Удалить фильтр по {selectedArea}
        </Link>
      ) : null}
      {ListFilterResult}
    </>
  );
}

export default Universities;
