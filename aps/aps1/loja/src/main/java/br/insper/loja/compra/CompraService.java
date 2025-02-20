package br.insper.loja.compra;

import br.insper.loja.usuario.Usuario;
import br.insper.loja.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioService usuarioService;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String produtoServiceUrl = "http://localhost:8081/api/produto";

    public Compra salvarCompra(Compra compra) {
        Usuario usuario = usuarioService.getUsuario(compra.getUsuario());
        compra.setNome(usuario.getNome());
        compra.setDataCompra(LocalDateTime.now());

        double totalCompra = 0.0;

        for (String produtoId : compra.getProdutos()) {
            ProdutoDTO produto = restTemplate.getForObject(produtoServiceUrl + "/" + produtoId, ProdutoDTO.class);

            if (produto == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado: " + produtoId);
            }

            if (produto.getEstoque() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto sem estoque: " + produto.getNome());
            }
            restTemplate.put(produtoServiceUrl + "/diminuir-estoque/" + produtoId, Map.of("quantidade", 1));
            totalCompra += produto.getPreco();
        }

        compra.setTotal(totalCompra);
        return compraRepository.save(compra);
    }

    public List<Compra> getCompras() {
        return compraRepository.findAll();
    }

    private static class ProdutoDTO {
        private String id;
        private String nome;
        private Double preco;
        private Integer estoque;

        public String getId() { return id; }
        public String getNome() { return nome; }
        public Double getPreco() { return preco; }
        public Integer getEstoque() { return estoque; }
    }
}