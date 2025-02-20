package br.insper.produto.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.criarProduto(produto);
    }

    @GetMapping("/{id}")
    public Produto getProdutoPorId(@PathVariable String id) {
        return produtoService.getProdutoPorId(id);
    }

    @PutMapping("/diminuir-estoque/{id}")
    public void diminuirEstoque(@PathVariable String id, @RequestBody Map<String, Integer> request) {
        int quantidade = request.get("quantidade");
        produtoService.diminuirEstoque(id, quantidade);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }
}