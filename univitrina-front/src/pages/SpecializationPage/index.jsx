import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { requestDataForSpecializationPage } from '../../common/request';
import ProfessionCards from '../../components/ProfessionCards';
import Description from '../../components/DescriptionSpecialization';
import NoMatch from '../../components/NoMatch';

function SpecializationPage() {
  const { id } = useParams();

  const [SpecializationData, setSpecializationData] = useState(null);

  //  без функции-обертки не работает и ругается линтер,
  //  в примерах асинхронные запросы в useEffect тоже с оберткой почему то идут
  useEffect(() => {
    async function fetchData() {
      const response = await requestDataForSpecializationPage(id);
      setSpecializationData(response);
    }
    fetchData();
  }, [id]);

  return (
    <section position="static">
      {!!SpecializationData && (
        <>
          <Description
            name={SpecializationData.name}
            description={SpecializationData.description}
            id={SpecializationData.id}
          />
          <ProfessionCards cards={SpecializationData.professionList} />
        </>
      )}
      {!SpecializationData && <NoMatch />}
    </section>
  );
}

export default () => <SpecializationPage />;
