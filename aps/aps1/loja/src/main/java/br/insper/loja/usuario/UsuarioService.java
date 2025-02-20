package br.insper.loja.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    public Usuario getUsuario(String email) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate
                    .getForEntity("http://localhost:8080/api/usuario/" + email,
                            Usuario.class)
                    .getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
