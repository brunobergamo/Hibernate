
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
@GenericGenerator(name = "LIVROS_SEQ", strategy = "increment")
public class Livros implements Serializable {

	
   
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "LIVROS_SEQ")
	@GeneratedValue(generator = "LIVROS_SEQ")
	@Column(name = "id")
	private int id;
	
	@Column(name = "titulo")
    private String titulo;
 
    public Livros() {
    }
 
    public int getid() {
        return id;
    }
 
    public void setid(int id) {
        this.id = id;
    }
 
    public String getTitulo() {
        return titulo;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    public String toString()
    {
    	return this.id+"-"+this.titulo;
    }
    
  
}