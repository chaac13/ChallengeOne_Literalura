package br.com.literalura.Literalura.repository;

import br.com.literalura.Literalura.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("Select l from Livro l")
    List<Livro> livrosCadastrados();

    @Query("SELECT l FROM Livro l WHERE l.idioma IN (:idiomaEscolhido)")
    List<Livro> livrosPorIdioma(@Param("idiomaEscolhido") String idiomaEscolhido);

}
