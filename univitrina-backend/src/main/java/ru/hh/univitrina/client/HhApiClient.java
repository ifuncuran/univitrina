package ru.hh.univitrina.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.univitrina.dto.AreaDto;

public class HhApiClient {

    private final String hhServer;
    private final String russiaArea;

    private final RestTemplate rest;

    public HhApiClient(FileSettings fileSettings) {
        this.hhServer = fileSettings.getString("hhServer");
        this.russiaArea = fileSettings.getString("russiaArea");

        this.rest = new RestTemplate();
    }

    public AreaDto getRussiaArea() {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.add("User-Agent", "Univitrina");

        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<AreaDto> responseEntity = rest.exchange(hhServer + russiaArea,
                HttpMethod.GET, requestEntity, AreaDto.class);

        return responseEntity.getBody();
    }

}
