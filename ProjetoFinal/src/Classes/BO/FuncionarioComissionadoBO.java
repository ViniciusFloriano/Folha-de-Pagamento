package Classes.BO;
import Classes.DAO.FuncionarioComissionadoDAO;
import Classes.DTO.FuncionarioComissionado;
//import java.util.List;
public class FuncionarioComissionadoBO {

    public boolean inserir(FuncionarioComissionado funcionarioComissionado, int idUsuario){
        if (existe(funcionarioComissionado, idUsuario) != true) {
        	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
            return funcionarioComissionadoDAO.inserir(funcionarioComissionado, idUsuario);
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
    public boolean existe(FuncionarioComissionado funcionarioComissionado, int idUsuario){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.existe(funcionarioComissionado, idUsuario);
    }
    /*
    public List<Usuario> pesquisarTodos(){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.pesquisarTodos();
    }*/
    
    public int pegarId(int id){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.pegarId(id);
    }
}
