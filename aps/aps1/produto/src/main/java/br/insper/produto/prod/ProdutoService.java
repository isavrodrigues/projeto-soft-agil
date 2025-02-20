package br.insper.produto.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto getProdutoPorId(String id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void diminuirEstoque(String id, int quantidade) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Produto não encontrado."));
        if (produto.getEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }
        produto.setEstoque(produto.getEstoque() - quantidade);
        produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
}
