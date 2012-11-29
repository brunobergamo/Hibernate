

import java.util.List;


public interface IBanco {
 
	public Livros InserirLivro(Livros livro);
 
	public void AtualizarLivro(Livros livro);
 
	public void ApagarLivro(Livros livro);
 
	public List<Livros> SelecionarLivrosPorTitulo(String titulo);
 
	public Livros SelecionarLivroPorCodigo(long cod);
 
}