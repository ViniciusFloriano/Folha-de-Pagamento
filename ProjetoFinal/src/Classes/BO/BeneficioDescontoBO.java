package Classes.BO;
import Classes.DAO.BeneficioDescontoDAO;
import Classes.DTO.BeneficioDesconto;
public class BeneficioDescontoBO {

	public boolean inserir(BeneficioDesconto beneficioDesconto, int idFuncionario){
    	BeneficioDescontoDAO funcionariocltDAO = new BeneficioDescontoDAO();
        return funcionariocltDAO.inserir(beneficioDesconto, idFuncionario);
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
