
import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class Banco implements IBanco {

	private SessionFactory sessionFactory;

	private Session 
	sessao;

	private Transaction transacao;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.sessao = sessionFactory.openSession();
	}

	public Livros InserirLivro(Livros livro) {
		try {
			if(!sessao.isOpen()) {
				sessao = sessionFactory.openSession();
			}
			transacao = sessao.beginTransaction();
			int cod = (Integer) sessao.save(livro);
			transacao.commit();
			livro.setid(cod);
		}
		catch(HibernateException e) {
			System.out.println("Erro: " + e.getMessage());
			transacao.rollback();
		}
		finally {
			try {
				if(sessao.isOpen()) {
					sessao.close();
				}
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao finalizar inserção: " + e.getMessage());
			}
		}

		return livro;

	}

	public void AtualizarLivro(Livros livro) {

		try {
			if(!sessao.isOpen()) {
				sessao = sessionFactory.openSession();
			}
			transacao = sessao.beginTransaction();
			sessao.update(livro);
			transacao.commit();
		}
		catch(HibernateException e)
		{
			System.out.println("Erro: " + e.getMessage());
			transacao.rollback();
		}
		finally {
			try {
				if(sessao.isOpen()) {
					sessao.close();
				}
			}
			catch(Throwable e){
				System.out.println("Erro ao finalizar atualização: " + e.getMessage());
			}
		}

	}

	public void ApagarLivro(Livros livro) {
		try {
			if(!sessao.isOpen()) {
				sessao = sessionFactory.openSession();
			}
			transacao = sessao.beginTransaction();
			sessao.delete(livro);
			transacao.commit();
		}
		catch(HibernateException e) {
			System.out.println("Erro: " + e.getMessage());
			transacao.rollback();
		}
		finally {
			try {
				if(sessao.isOpen()) {
					sessao.close();
				}
			}
			catch(Throwable e) {
				System.out.println("Erro ao finalizar : " + e.getMessage());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public Object SelecionarPorUnique( java.lang.Class readClass,String field,Object id)
	throws SecurityException, NoSuchFieldException 
	{
		if(!sessao.isOpen()) {
			sessao = sessionFactory.openSession();
		}
		Field d = readClass.getDeclaredField(field);
		Criteria criteria = sessao.createCriteria(readClass);
		criteria.add(Restrictions.eq(d.getName(), id));
		Object object = (Object) criteria.uniqueResult();
		return object;
		
	}
	
	@SuppressWarnings("rawtypes")
	public List readObjects(java.lang.Class readClass,String field,Object id) throws SecurityException, NoSuchFieldException {
		if(!sessao.isOpen()) {
			sessao = sessionFactory.openSession();
		}
		Field d = readClass.getDeclaredField(field);
		Criteria criteria = sessao.createCriteria(readClass);
		criteria.add(Restrictions.eq(d.getName(), id));
		return criteria.list();
	}

	public Livros SelecionarLivroPorCodigo(long cod) {
		if(!sessao.isOpen()) {
			sessao = sessionFactory.openSession();
		}

		
		Criteria criteria = sessao.createCriteria(Livros.class);
		criteria.add(Restrictions.eq("id", (int)cod));
		Livros livro = (Livros) criteria.uniqueResult();
		return livro;
	}

	@Override
	public List<Livros> SelecionarLivrosPorTitulo(String titulo) {
		// TODO Auto-generated method stub
		return null;
	}

}