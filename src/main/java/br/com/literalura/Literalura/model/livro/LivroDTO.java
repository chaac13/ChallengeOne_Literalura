package br.com.literalura.Literalura.model.livro;

import br.com.literalura.Literalura.model.autor.AutorDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        int id,
        @JsonAlias("title") String nome,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("authors") List<AutorDTO> autor,
        @JsonAlias("download_count") int numeroDownloads
) {
}
