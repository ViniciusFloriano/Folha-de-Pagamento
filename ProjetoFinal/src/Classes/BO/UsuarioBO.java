package Classes.BO;
import Classes.DAO.UsuarioDAO;
import Classes.DTO.Usuario;
import java.util.List;
public class UsuarioBO {

	    public boolean inserir(Usuario usuario){
	        if (existe(usuario) != true) {
	        	UsuarioDAO usuarioDAO = new UsuarioDAO();
	            return usuarioDAO.inserir(usuario);
	        }
	        return false;
	    }
	    /*
	    public boolean alterar(Usuario usuario){
	    	UsuarioDAO usuarioDAO = new UsuarioDAO();
	        return usuarioDAO.alterar(usuario);
	    }
	    
	    public boolean excluir(Usuario usuario){
	    	UsuarioDAO usuarioDAO = new UsuarioDAO();
	        return usuarioDAO.excluir(usuario);
	    }
	    
	    public Usuario procurarPorCodigo(Usuario usuario){
	    	UsuarioDAO usuarioDAO = new UsuarioDAO();
	        return usuarioDAO.procurarPorCodigo(usuario);
	    }
	    
	    public Usuario procurarPorDescricao(Usuario usuario){
	    	UsuarioDAO usuarioDAO = new UsuarioDAO();
	        return usuarioDAO.procurarPorDescricao(usuario);
	    }
	    */
	    public boolean existe(Usuario usuario){
	    	UsuarioDAO usuarioDAO = new UsuarioDAO();
	        return usuarioDAO.existe(usuario);
	    }
	    /*
	    public List<Usuario> pesquisarTodos(){
	    	UsuarioDAO usuarioDAO = new UsuarioDAO();
	        return usuarioDAO.pesquisarTodos();
	    }*/
}
