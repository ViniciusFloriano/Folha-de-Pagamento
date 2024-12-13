package Classes.BO;
import Classes.DAO.FuncionarioCLTDAO;
import Classes.DTO.FuncionarioCLT;
import java.util.List;
public class FuncionarioCLTBO {

    public boolean inserir(FuncionarioCLT funcionarioclt, int idUsuario){
        if (existe(funcionarioclt, idUsuario) != true) {
        	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
            return funcionariocltDAO.inserir(funcionarioclt, idUsuario);
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
    public boolean existe(FuncionarioCLT funcionarioclt, int idUsuario){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.existe(funcionarioclt, idUsuario);
    }
    
    public List<FuncionarioCLT> pesquisarTodos(int idFuncionario){
    	FuncionarioCLTDAO funcionarioCLTDAO = new FuncionarioCLTDAO();
        return funcionarioCLTDAO.pesquisarTodos(idFuncionario);
    }
    
    public int pegarId(int id){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.pegarId(id);
    }
    
    public int pegarSalario(int id){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.pegarSalario(id);
    }
    
    public boolean atualizarSalario(double salario, int idFuncionario){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.atualizarSalario(salario, idFuncionario);
    }
}
