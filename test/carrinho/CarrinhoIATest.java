package carrinho;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produto.Produto;
import produto.ProdutoNaoEncontradoException;

@DisplayName("Testes do carrinho gerados com IA (100% de cobertura)")
public class CarrinhoIATest {

	private Carrinho carrinho;
	private Produto livro;
	private Produto jogo;

	@BeforeEach
	public void inicializa() {
		carrinho = new Carrinho();
		livro = new Produto("Introducao ao Teste de Software", 100.00);
		jogo = new Produto("Elden Ring", 250.00);
	}

	// ---------- construtor ----------

	@Test
	@DisplayName("Carrinho novo esta vazio e com valor total zero")
	public void testCarrinhoNovoVazio() {
		assertAll("carrinhoVazio",
			() -> assertEquals(0, carrinho.getQtdeItems()),
			() -> assertEquals(0.0, carrinho.getValorTotal(), 0.001)
		);
	}

	// ---------- addItem / getQtdeItems / getValorTotal ----------

	@Test
	@DisplayName("Adicionar um item atualiza quantidade e valor total")
	public void testAdicionaUmItem() {
		carrinho.addItem(livro);

		assertAll("umItem",
			() -> assertEquals(1, carrinho.getQtdeItems()),
			() -> assertEquals(100.00, carrinho.getValorTotal(), 0.001)
		);
	}

	@Test
	@DisplayName("Valor total soma o preco de todos os itens")
	public void testValorTotalVariosItens() {
		carrinho.addItem(livro);
		carrinho.addItem(jogo);

		assertAll("doisItens",
			() -> assertEquals(2, carrinho.getQtdeItems()),
			() -> assertEquals(350.00, carrinho.getValorTotal(), 0.001)
		);
	}

	@Test
	@DisplayName("O mesmo produto pode ser adicionado mais de uma vez")
	public void testAdicionaItemDuplicado() {
		carrinho.addItem(livro);
		carrinho.addItem(livro);

		assertAll("duplicado",
			() -> assertEquals(2, carrinho.getQtdeItems()),
			() -> assertEquals(200.00, carrinho.getValorTotal(), 0.001)
		);
	}

	@Test
	@DisplayName("Valor total reflete alteracao de preco feita depois de adicionar")
	public void testValorTotalAposAlterarPreco() {
		carrinho.addItem(livro);
		livro.setPreco(80.00);

		assertEquals(80.00, carrinho.getValorTotal(), 0.001);
	}

	@Test
	@DisplayName("Valor total com precos decimais")
	public void testValorTotalPrecosDecimais() {
		carrinho.addItem(new Produto("Caneta", 0.10));
		carrinho.addItem(new Produto("Lapis", 0.20));

		assertEquals(0.30, carrinho.getValorTotal(), 0.001);
	}

	// ---------- removeItem ----------

	@Test
	@DisplayName("Remover item existente atualiza quantidade e valor total")
	public void testRemoveItemExistente() throws ProdutoNaoEncontradoException {
		carrinho.addItem(livro);
		carrinho.addItem(jogo);

		carrinho.removeItem(livro);

		assertAll("aposRemover",
			() -> assertEquals(1, carrinho.getQtdeItems()),
			() -> assertEquals(250.00, carrinho.getValorTotal(), 0.001)
		);
	}

	@Test
	@DisplayName("Remover usa equals: produto com mesmo nome e removido")
	public void testRemoveItemPorNome() {
		carrinho.addItem(livro);
		Produto mesmoNome = new Produto("Introducao ao Teste de Software", 1.00);

		assertDoesNotThrow(() -> carrinho.removeItem(mesmoNome));
		assertEquals(0, carrinho.getQtdeItems());
	}

	@Test
	@DisplayName("Remover item duplicado remove apenas uma ocorrencia")
	public void testRemoveItemDuplicado() throws ProdutoNaoEncontradoException {
		carrinho.addItem(livro);
		carrinho.addItem(livro);

		carrinho.removeItem(livro);

		assertEquals(1, carrinho.getQtdeItems());
	}

	@Test
	@DisplayName("Remover item que nao esta no carrinho lanca excecao")
	public void testRemoveItemInexistente() {
		carrinho.addItem(livro);

		assertThrows(ProdutoNaoEncontradoException.class, () -> carrinho.removeItem(jogo));
		assertEquals(1, carrinho.getQtdeItems());
	}

	@Test
	@DisplayName("Remover de carrinho vazio lanca excecao")
	public void testRemoveDeCarrinhoVazio() {
		assertThrows(ProdutoNaoEncontradoException.class, () -> carrinho.removeItem(livro));
	}

	// ---------- esvazia ----------

	@Test
	@DisplayName("Esvaziar remove todos os itens")
	public void testEsvazia() {
		carrinho.addItem(livro);
		carrinho.addItem(jogo);

		carrinho.esvazia();

		assertAll("esvaziado",
			() -> assertEquals(0, carrinho.getQtdeItems()),
			() -> assertEquals(0.0, carrinho.getValorTotal(), 0.001)
		);
	}

	@Test
	@DisplayName("Esvaziar carrinho ja vazio nao causa erro")
	public void testEsvaziaCarrinhoVazio() {
		assertDoesNotThrow(() -> carrinho.esvazia());
		assertEquals(0, carrinho.getQtdeItems());
	}

	@Test
	@DisplayName("Carrinho pode ser reutilizado depois de esvaziado")
	public void testReutilizaAposEsvaziar() {
		carrinho.addItem(livro);
		carrinho.esvazia();
		carrinho.addItem(jogo);

		assertAll("reutilizado",
			() -> assertEquals(1, carrinho.getQtdeItems()),
			() -> assertEquals(250.00, carrinho.getValorTotal(), 0.001)
		);
	}

}
