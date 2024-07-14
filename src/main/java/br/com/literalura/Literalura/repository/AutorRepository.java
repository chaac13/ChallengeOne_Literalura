package br.com.literalura.Literalura.repository;

import br.com.literalura.Literalura.model.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("Select a from Autor a")
    List<Autor> autoresCadastrados();

    @Query("Select a from Autor a where a.anoNascimento <= :ano and a.anoFalecimento >= :ano")
    List<Autor> autoresVivosNaqueleAno(int ano);
}
