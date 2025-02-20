package br.insper.loja.evento;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventoService {

    public void salvarEvento(String usuario, String acao) {
        Evento evento = new Evento();
        evento.setEmail(usuario);
        evento.setAcao(acao);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8080/api/evento", evento, Evento.class);

    }

}
