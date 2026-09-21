package br.com.fiap.Challenge_Sprint_2.Services;

import br.com.fiap.Challenge_Sprint_2.DTOs.AnaliseAiResponse;
import br.com.fiap.Challenge_Sprint_2.Model.Ideia;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

@Service
public class GeminiAiService {

    private final ChatClient chatClient;

    public GeminiAiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public AnaliseAiResponse avaliarIdeia(Ideia ideia) {
        var converter = new BeanOutputConverter<>(AnaliseAiResponse.class);

        String promptEstatico = """
                Aja como um consultor de inovação sénior e avalie a seguinte ideia de projeto.
                
                Título da Ideia: %s
                Descrição: %s
                Categoria: %s
                
                Analisa a viabilidade técnica, o potencial de mercado e a inovação.
                %s
                """;

        String promptFormatado = String.format(promptEstatico,
                ideia.getTitulo(),
                ideia.getDescricao(),
                ideia.getCategoria(),
                converter.getFormat()
        );

        String respostaAi = chatClient.prompt()
                .user(promptFormatado)
                .call()
                .content();

        return converter.convert(respostaAi);
    }
}