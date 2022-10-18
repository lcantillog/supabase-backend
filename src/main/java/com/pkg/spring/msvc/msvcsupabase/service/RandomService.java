package com.pkg.spring.msvc.msvcsupabase.service;

import com.pkg.spring.msvc.msvcsupabase.dto.ChisteDto;
import com.pkg.spring.msvc.msvcsupabase.dto.RandomDto;
import com.pkg.spring.msvc.msvcsupabase.interfaz.RandomInterfaz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RandomService implements RandomInterfaz {

    private final static Logger LOGGER= LoggerFactory.getLogger(RandomService.class);

    @Value("${url.random}")
    private String urlApiRandom;

    @Override
    public Optional<RandomDto> getChisteApi() {


        RestTemplate restTemplate = new RestTemplate();

        LOGGER.info("antes de consumir el api");
        MultiValueMap<String, String> headerss = new LinkedMultiValueMap<>();
        headerss.add("Content-Type", "application/json");

        ResponseEntity<RandomDto> response =  restTemplate.getForEntity( urlApiRandom, RandomDto.class);
        RandomDto random = response.getBody();

        return Optional.of(random);
    }

    @Override
    public Optional<ChisteDto> getChisteChuckNorris() {

        var restTemplate = new RestTemplate();

        LOGGER.info("antes de consumir el api");
        MultiValueMap<String, String> headerss = new LinkedMultiValueMap<>();
        headerss.add("Content-Type", "application/json");
        ResponseEntity<RandomDto> response =  restTemplate.getForEntity( urlApiRandom, RandomDto.class);
        RandomDto random = response.getBody();

        return Optional.of(ChisteDto
                        .builder()
                        .idChuckNorris(random.getId())
                        .iconoUrl(random.getIconUrl())
                        .urlChuckNorris(random.getUrl())
                        .valorChuckNorris(random.getValue())
                        .build());
    }
}
