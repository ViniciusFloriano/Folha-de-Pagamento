package Classes.BO;
import Classes.DAO.FuncionarioHoristaDAO;
import Classes.DTO.FuncionarioHorista;
public class FuncionarioHoristaBO {

	public boolean inserir(FuncionarioHorista funcionarioHorista, int idUsuario){
        if (existe(funcionarioHorista, idUsuario) != true) {
        	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
            return funcionarioHoristaDAO.inserir(funcionarioHorista, idUsuario);
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
    public boolean existe(FuncionarioHorista funcionarioHorista, int idUsuario){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.existe(funcionarioHorista, idUsuario);
    }
    /*
    public List<Usuario> pesquisarTodos(){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.pesquisarTodos();
    }*/
	
    public int pegarId(int id){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.pegarId(id);
    }
}
