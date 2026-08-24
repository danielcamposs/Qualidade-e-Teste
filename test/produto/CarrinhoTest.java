package carrinho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import produto.Produto;
import produto.ProdutoNaoEncontradoException;

public class CarrinhoTest {

    Carrinho carrinho;
    Produto livro;
    Produto jogo;

    @BeforeEach
    public void inicializa() {
        carrinho = new Carrinho();
        livro = new Produto("Introdução ao Teste de Software", 100.00);
        jogo = new Produto("Elden Ring", 250.00);
    }

    @Test
    public void testCarrinhoInicial() {
        Assertions.assertAll("carrinhoVazio",
            () -> assertEquals(0, carrinho.getQtdeItems()),
            () -> assertTrue(0.0 == carrinho.getValorTotal())
        );
    }

    @Test
    public void testAdicionaItens() {
        carrinho.addItem(livro);
        carrinho.addItem(jogo);

        assertEquals(2, carrinho.getQtdeItems());
        assertThat(carrinho.getValorTotal(), equalTo(350.00));
    }

    @Test
    public void testRemoveItem() throws ProdutoNaoEncontradoException {
        carrinho.addItem(livro);
        carrinho.addItem(jogo);

        carrinho.removeItem(livro);

        assertEquals(1, carrinho.getQtdeItems());
        assertThat(carrinho.getValorTotal(), equalTo(250.00));
    }

    @Test
    public void testRemoveItemInexistenteLancaExcecao() {
        carrinho.addItem(livro);

        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            carrinho.removeItem(jogo);
        });
    }

    @Test
    public void testEsvaziaCarrinho() {
        carrinho.addItem(livro);
        carrinho.addItem(jogo);

        carrinho.esvazia();

        assertThat(carrinho.getQtdeItems(), equalTo(0));
        assertTrue(carrinho.getValorTotal() == 0.0);
    }

    @Test
    public void assertionComHamcrestMatcher() {
        carrinho.addItem(livro);

        assertThat(carrinho, instanceOf(Carrinho.class));
        assertThat(carrinho.getQtdeItems(), is(1));
        assertThat(carrinho.getValorTotal(), is(notNullValue()));
    }
}