import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) throws SecurityException, NoSuchFieldException 
	{

		ApplicationContext ctx = new ClassPathXmlApplicationContext("banco.xml");
		System.out.println("Spring inicializado");

		Banco banco = (Banco) ctx.getBean("banco_dados");

		Livros livro1 = new Livros();
		livro1.setTitulo("Spring + Hibernate");
		Livros livro2 = new Livros();
		livro2.setTitulo("Hibernate Avan�ado");
		System.out.println("Objetos Livro inicializados");

		livro1 = banco.InserirLivro(livro1);
		livro2 = banco.InserirLivro(livro2);

		int cod1 = livro1.getid();
		int cod2 = livro2.getid();
		System.out.println("Livros gravados no banco");

		//zerando os objetos Livro
		livro1 = null;
		livro2 = null;


		System.out.println("Selecionando livros por c�digo");

		livro1 = (Livros) banco.SelecionarPorUnique(Livros.class,"id",cod1);


		exibe(livro1);
		livro2 = banco.SelecionarLivroPorCodigo(cod2);
		exibe(livro2);

		System.out.println("Selecionando livros que contenham 'Hibernate' no t�tulo");
		List<Livros> listas = extracted(banco);
		//List lista = banco.SelecionarLivrosPorTitulo("%Hibernate%");
		for (Livros l : listas)
		{
			exibe(l);
		}

		System.out.println("Atualizando livro");
		livro1.setTitulo(livro1.getTitulo()+" Segunda Edi��o");
		banco.AtualizarLivro(livro1);

		System.out.println("Apagando livro");
		banco.ApagarLivro(livro2);
	}

	private static List extracted(Banco banco) throws NoSuchFieldException 
	{
		return banco.readObjects(Livros.class, "titulo", "Hibernate Avan�ado");
	}

	public static void exibe(Livros livro) 
	{
		if (livro == null)
			System.out.println("Livro n�o encontrado");
		else
			System.out.println("Livro -> cod: " + livro.getid() + " | T�tulo: " + livro.getTitulo());
	}

}