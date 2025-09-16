package br.com.alura.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;

public class AbrigoServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private AbrigoService abrigoService = new AbrigoService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("Teste", "11975435667", "teste@teste.com.br");

    @Test
    public void deveVerificarSeDisparaRequisicaoGetSeraChamado() throws IOException, InterruptedException {
        // Implement test logic for listarAbrigo method
        // give
        abrigo.setId(0L);
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdNome = "0 - Teste";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        // when
        when(response.body()).thenReturn("[{" + abrigo.toString() + "}]");
        when(this.client.montarRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());

        String actualAbrigosCadastrados = lines[0];
        String actualIdENome = lines[1];

        // asserts
        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectedIdNome, actualIdENome);
    }

}
