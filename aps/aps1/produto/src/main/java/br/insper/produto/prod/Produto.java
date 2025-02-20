package br.insper.produto.prod;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Produto {
    //um id, um nome, um preço e uma quantidade em estoque.
    @Id
    private String id;
    private String nome;
    private Double preco;
    private Integer estoque;

    public String getId(){ return id; }
    public void setId(String id){ this.id = id; }

    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome; }

    public Double getPreco(){ return preco; }
    public void setPreco(Double preco){ this.preco = preco; }

    public Integer getEstoque(){ return estoque; }
    public void setEstoque(Integer estoque){ this.estoque = estoque; }

}

