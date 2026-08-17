package calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTestGpt {

    private Calculadora calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new Calculadora();
    }

    // =========================================================
    // TESTES DA SOMA
    // =========================================================

    @Test
    void deveSomarDoisNumerosPositivos() {
        assertEquals(8, calculadora.soma(3, 5));
    }

    @Test
    void deveSomarNumeroPositivoComZero() {
        assertEquals(10, calculadora.soma(10, 0));
    }

    @Test
    void deveSomarDoisNumerosNegativos() {
        assertEquals(-8, calculadora.soma(-3, -5));
    }

    @Test
    void deveSomarNumeroPositivoComNumeroNegativo() {
        assertEquals(2, calculadora.soma(5, -3));
    }

    @Test
    void deveRetornarZeroAoSomarNumerosOpostos() {
        assertEquals(0, calculadora.soma(5, -5));
    }

    // =========================================================
    // TESTES DA SUBTRACAO
    // =========================================================

    @Test
    void deveSubtrairDoisNumerosPositivos() {
        assertEquals(2, calculadora.subtracao(5, 3));
    }

    @Test
    void deveSubtrairNumeroNegativo() {
        assertEquals(8, calculadora.subtracao(5, -3));
    }

    @Test
    void deveSubtrairDoisNumerosNegativos() {
        assertEquals(2, calculadora.subtracao(-3, -5));
    }

    @Test
    void deveRetornarZeroAoSubtrairNumerosIguais() {
        assertEquals(0, calculadora.subtracao(10, 10));
    }

    @Test
    void deveSubtrairZero() {
        assertEquals(10, calculadora.subtracao(10, 0));
    }

    // =========================================================
    // TESTES DA MULTIPLICACAO
    // =========================================================

    @Test
    void deveMultiplicarDoisNumerosPositivos() {
        assertEquals(20, calculadora.multiplicacao(4, 5));
    }

    @Test
    void deveMultiplicarNumeroPositivoPorNegativo() {
        assertEquals(-20, calculadora.multiplicacao(4, -5));
    }

    @Test
    void deveMultiplicarDoisNumerosNegativos() {
        assertEquals(20, calculadora.multiplicacao(-4, -5));
    }

    @Test
    void deveRetornarZeroQuandoUmDosNumerosForZero() {
        assertEquals(0, calculadora.multiplicacao(10, 0));
    }

    @Test
    void deveMultiplicarPorUm() {
        assertEquals(10, calculadora.multiplicacao(10, 1));
    }

    // =========================================================
    // TESTES DA DIVISAO
    // =========================================================

    @Test
    void deveDividirDoisNumerosPositivos() {
        assertEquals(5, calculadora.divisao(10, 2));
    }

    @Test
    void deveDividirNumeroPositivoPorNegativo() {
        assertEquals(-5, calculadora.divisao(10, -2));
    }

    @Test
    void deveDividirDoisNumerosNegativos() {
        assertEquals(5, calculadora.divisao(-10, -2));
    }

    @Test
    void deveRealizarDivisaoInteira() {
        assertEquals(3, calculadora.divisao(10, 3));
    }

    @Test
    void deveDividirPorUm() {
        assertEquals(10, calculadora.divisao(10, 1));
    }

    @Test
    void deveLancarExcecaoAoDividirPorZero() {
        assertThrows(
            ArithmeticException.class,
            () -> calculadora.divisao(10, 0)
        );
    }

    // =========================================================
    // TESTES DA SOMATORIA
    // =========================================================

    @Test
    void deveRetornarZeroParaSomatoriaDeZero() {
        assertEquals(0, calculadora.somatoria(0));
    }

    @Test
    void deveCalcularSomatoriaDeCinco() {
        assertEquals(15, calculadora.somatoria(5));
    }

    @Test
    void deveCalcularSomatoriaDeDez() {
        assertEquals(55, calculadora.somatoria(10));
    }

    @Test
    void deveRetornarZeroParaNumeroNegativo() {
        assertEquals(0, calculadora.somatoria(-1));
    }

    @Test
    void deveCalcularSomatoriaDeUm() {
        assertEquals(1, calculadora.somatoria(1));
    }

    // =========================================================
    // TESTES DO EH POSITIVO
    // =========================================================

    @Test
    void deveRetornarTrueParaNumeroPositivo() {
        assertTrue(calculadora.ehPositivo(10));
    }

    @Test
    void deveRetornarTrueParaZero() {
        assertTrue(calculadora.ehPositivo(0));
    }

    @Test
    void deveRetornarFalseParaNumeroNegativo() {
        assertFalse(calculadora.ehPositivo(-10));
    }

    @Test
    void deveRetornarTrueParaMaiorInteiro() {
        assertTrue(calculadora.ehPositivo(Integer.MAX_VALUE));
    }

    @Test
    void deveRetornarFalseParaMenorInteiro() {
        assertFalse(calculadora.ehPositivo(Integer.MIN_VALUE));
    }

    // =========================================================
    // TESTES DO COMPARA
    // =========================================================

    @Test
    void deveRetornarZeroQuandoOsNumerosForemIguais() {
        assertEquals(0, calculadora.compara(5, 5));
    }

    @Test
    void deveRetornarUmQuandoPrimeiroNumeroForMaior() {
        assertEquals(1, calculadora.compara(10, 5));
    }

    @Test
    void deveRetornarMenosUmQuandoPrimeiroNumeroForMenor() {
        assertEquals(-1, calculadora.compara(5, 10));
    }

    @Test
    void deveCompararNumerosNegativosIguais() {
        assertEquals(0, calculadora.compara(-5, -5));
    }

    @Test
    void deveRetornarMenosUmQuandoNegativoForComparadoComPositivo() {
        assertEquals(-1, calculadora.compara(-5, 5));
    }

    @Test
    void deveRetornarUmQuandoPositivoForComparadoComNegativo() {
        assertEquals(1, calculadora.compara(5, -5));
    }

    @Test
    void deveCompararZeroComNumeroPositivo() {
        assertEquals(-1, calculadora.compara(0, 5));
    }

    @Test
    void deveCompararZeroComNumeroNegativo() {
        assertEquals(1, calculadora.compara(0, -5));
    }
}