import React from 'react';
import { Button } from '@material-ui/core';
import { Link } from 'react-router-dom';

const AreasData = [
  {
    title: 'Московская область',
    area: 'MoscowRegion',
  },
  {
    title: 'Ленинградская область',
    area: 'LeningradRegion',
  },
  {
    title: 'Свердловская область',
    area: 'SverdlovskRegion',
  },
];

function UniversitiesAreas() {
  const ListAreas = AreasData.map(({ title, area }) => (
    <Button
      to={{ pathname: '/universities', search: `area=${area}` }}
      component={Link}
    >
      {title}
    </Button>
  ));

  return <>{ListAreas}</>;
}

export default UniversitiesAreas;
