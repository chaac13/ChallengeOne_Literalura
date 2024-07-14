package br.com.literalura.Literalura.service;

import br.com.literalura.Literalura.model.autor.AutorDTO;
import br.com.literalura.Literalura.model.livro.LivroDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDados {

    public LivroDTO converterDados(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        var node = mapper.readTree(json);
        var jsonLivro = node.get("results").get(0);

        return mapper.treeToValue(jsonLivro, LivroDTO.class);
    }


}