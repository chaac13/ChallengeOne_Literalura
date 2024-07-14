package br.com.literalura.Literalura.principal;

import br.com.literalura.Literalura.model.autor.Autor;
import br.com.literalura.Literalura.model.livro.Livro;
import br.com.literalura.Literalura.model.livro.LivroDTO;
import br.com.literalura.Literalura.repository.AutorRepository;
import br.com.literalura.Literalura.repository.LivroRepository;
import br.com.literalura.Literalura.service.ConsumoAPI;
import br.com.literalura.Literalura.service.ConversorDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    Scanner leitura = new Scanner(System.in);
    String menu;
    int opcao = -1;

    private final LivroRepository repositorioLivro;
    private final AutorRepository repositorioAutor;

    public Menu(LivroRepository repositorioLivro, AutorRepository repositorioAutor) {
        this.repositorioLivro = repositorioLivro;
        this.repositorioAutor = repositorioAutor;
    }

    public void chamarMenu() throws JsonProcessingException {

        while (opcao != 0){
            System.out.println("===================== MENU =====================");
            System.out.println("""
                Por favor, escolha uma das opções abaixo:
                1 ---> Buscar livro pelo título;
                2 ---> Listar livros registrados;
                3 ---> Listar autores registrados;
                4 ---> Listar autores vivos em um determinado ano;
                5 ---> Listar livros em um determinado idioma;
                0 ---> Sair.
                """);

            opcao = leitura.nextInt();

            switch (opcao){

                case 0:{
                    System.out.println("\n == Encerrando o programa! ==");
                    break;
                }

                case 1:{
                    System.out.println("Escolha o livro que você quer inserir na base de dados:");
                    leitura.nextLine();
                    menu = leitura.nextLine();

                    var consumoApi = new ConsumoAPI();
                    var json = consumoApi.obterDados(menu);
                    var conversor = new ConversorDados();
                    LivroDTO livroDTO = conversor.converterDados(json);
                    Livro livro = new Livro(livroDTO, livroDTO.autor());
                    repositorioLivro.save(livro);

                    System.out.println(livro);
                    break;
                }
                case 2:{
                    List<Livro> livrosNaBase = repositorioLivro.livrosCadastrados();
                    livrosNaBase.forEach(System.out::println);
                    break;
                }
                case 3:{
                    List<Autor> autoresNaBase = repositorioAutor.autoresCadastrados();
                    autoresNaBase.forEach(System.out::println);
                    break;
                }
                case 4:{
                    int anoInformado;
                    System.out.println("Informe o ano desejado: ");
                    anoInformado = leitura.nextInt();

                    List<Autor> autoresNaBase = repositorioAutor.autoresVivosNaqueleAno(anoInformado);
                    if(!autoresNaBase.isEmpty()){
                        autoresNaBase.forEach(System.out::println);
                    }
                    else{
                        System.out.println("Entre os autores cadastrados, não existia nenhum vivo neste ano.");
                    }
                    break;
                }
                case 5:{
                    String idioma;
                    System.out.println("Informe o idioma desejado: ");
                    leitura.nextLine();
                    idioma = leitura.nextLine();

                    List<Livro> livrosPorIdioma = repositorioLivro.livrosCadastrados();

                    List<Livro> livrosFiltrados = livrosPorIdioma.stream().filter(livro -> livro.getIdioma().contains(idioma)).toList();

                    if(!livrosFiltrados.isEmpty()){
                        livrosFiltrados.forEach(System.out::println);
                    }
                    else{
                        System.out.println("Não existe nenhum livro neste idioma em nosso banco de dados.");
                    }
                    break;
                }
            }
        }
    }
}
