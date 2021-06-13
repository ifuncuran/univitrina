import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { requestDataForProfessionPage } from '../../common/request';
import SpecializationCards from '../../components/SpecializationCards';
import Description from '../../components/DescriptionProfession';
import NoMatch from '../../components/NoMatch';
import Spacer from '../../components/Spacer';

function ProfessionPage() {
  const { id } = useParams();

  const [professionData, setProfessionData] = useState(false);

  //  без функции-обертки не работает и ругается линтер,
  //  в примерах асинхронные запросы в useEffect тоже с оберткой почему то идут
  useEffect(() => {
    async function fetchData() {
      const response = await requestDataForProfessionPage(id);
      setProfessionData(response);
    }
    fetchData();
  }, [id]);

  return (
    <section position="static">
      {!!professionData && (
        <>
          <Description
            name={professionData.name}
            description={professionData.description}
            id={professionData.id}
          />
          <SpecializationCards cards={professionData.specialties} />
        </>
      )}
      {professionData === false && <Spacer />}
      {professionData === null && <NoMatch />}
    </section>
  );
}

export default () => <ProfessionPage />;
