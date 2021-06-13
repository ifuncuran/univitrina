import { Typography } from '@material-ui/core';
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import SpecializationCard from '../../components/SpecializationCard';
import UniversityInfo from '../../components/UniversityInfo';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import MainContainer from '../../components/MainContainer';
import useStyles from './style';
import { requestDataForUniversityPage } from '../../common/request';
import NoMatch from '../../components/NoMatch';
import Spacer from '../../components/Spacer';

function listCards(specializationList) {
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
  const [universityData, setUniversityData] = useState(false);

  useEffect(() => {
    async function fetchData() {
      const response = await requestDataForUniversityPage(id);
      setUniversityData(response);
    }
    fetchData();
  }, [id]);

  return (
    <section position="static">
      {!!universityData && (
        <>
          <UniversityInfo
            title={universityData.name}
            description={universityData.description}
            extendedDescription={universityData.extendedDescription}
          />
          <MainContainer fixed={isNotMobile}>
            <Typography className={classes.title} variant="h2">
              Специальности
            </Typography>
            {listCards(universityData.specialtyList)}
          </MainContainer>
        </>
      )}
      {universityData === false && <Spacer />}
      {universityData === null && <NoMatch />}
    </section>
  );
}

export default () => <UniversityPage />;
