package br.com.banco.sistemabancario.model.conta;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FaixaScoreHelperTest {

    @Autowired
    private FaixaScoreHelper faixaScoreHelper;

    @Test
    public void testGetFaixaByScore() {
        Assert.assertEquals(FaixaScore.FAIXA0, faixaScoreHelper.getFaixaByScore(10));
        Assert.assertEquals(FaixaScore.FAIXA1, faixaScoreHelper.getFaixaByScore(0));
        Assert.assertEquals(FaixaScore.FAIXA1, faixaScoreHelper.getFaixaByScore(1));
        Assert.assertEquals(FaixaScore.FAIXA2, faixaScoreHelper.getFaixaByScore(2));
        Assert.assertEquals(FaixaScore.FAIXA2, faixaScoreHelper.getFaixaByScore(3));
        Assert.assertEquals(FaixaScore.FAIXA2, faixaScoreHelper.getFaixaByScore(4));
        Assert.assertEquals(FaixaScore.FAIXA2, faixaScoreHelper.getFaixaByScore(5));
        Assert.assertEquals(FaixaScore.FAIXA3, faixaScoreHelper.getFaixaByScore(6));
        Assert.assertEquals(FaixaScore.FAIXA3, faixaScoreHelper.getFaixaByScore(7));
        Assert.assertEquals(FaixaScore.FAIXA3, faixaScoreHelper.getFaixaByScore(8));
        Assert.assertEquals(FaixaScore.FAIXA4, faixaScoreHelper.getFaixaByScore(9));
    }
}
