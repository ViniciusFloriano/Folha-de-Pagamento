package Classes.BO;
import Classes.DAO.BancoDAO;
import Classes.DTO.Banco;
public class BancoBO {

	public boolean inserir(Banco banco, int idFuncionario){
		BancoDAO bancoDAO = new BancoDAO();
        return bancoDAO.inserir(banco, idFuncionario);
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
    
    public boolean existe(FuncionarioCLT funcionarioclt, int idUsuario){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.existe(funcionarioclt, idUsuario);
    }
    
    public List<Usuario> pesquisarTodos(){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.pesquisarTodos();
    }*/
}
