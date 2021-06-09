import React from 'react';
import { Button } from '@material-ui/core';
import { Link } from 'react-router-dom';

const AreasData = [
  {
    title: 'Московская область',
    area: 1,
  },
  {
    title: 'Ленинградская область',
    area: 2,
  },
  {
    title: 'Свердловская область',
    area: 3,
  },
];

function UniversitiesAreas() {
  const ListAreas = AreasData.map(({ title, area }) => (
    <Button
      key={area}
      to={{ pathname: '/universities', search: `area=${area}` }}
      component={Link}
    >
      {title}
    </Button>
  ));

  return <>{ListAreas}</>;
}

export default UniversitiesAreas;
