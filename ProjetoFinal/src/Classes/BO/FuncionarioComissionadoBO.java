package Classes.BO;
import java.util.List;
import Classes.DAO.FuncionarioComissionadoDAO;
import Classes.DTO.FuncionarioComissionado;
public class FuncionarioComissionadoBO {

    public boolean inserir(FuncionarioComissionado funcionarioComissionado, int idFuncionario){
        if (existe(funcionarioComissionado, idFuncionario) != true) {
        	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
            return funcionarioComissionadoDAO.inserir(funcionarioComissionado, idFuncionario);
        }
        return false;
    }
    
    public boolean alterarSalarioBase(double salario, int idFuncionario){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.alterarSalarioBase(salario, idFuncionario);
    }
    
    public boolean excluir(int idFuncionario){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.excluir(idFuncionario);
    }
    /*
    public Usuario procurarPorCodigo(Usuario usuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.procurarPorCodigo(usuario);
    }
    
    public Usuario procurarPorDescricao(Usuario usuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.procurarPorDescricao(usuario);
    }
    */
    public boolean existe(FuncionarioComissionado funcionarioComissionado, int idFuncionario){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.existe(funcionarioComissionado, idFuncionario);
    }
    
    public List<FuncionarioComissionado> pesquisarTodos(int idFuncionario){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.pesquisarTodos(idFuncionario);
    }
    
    public int pegarId(int id){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.pegarId(id);
    }
    
    public double pegarSalarioBase(int id){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.pegarSalarioBase(id);
    }
    
    public double pegarComissao(int id){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.pegarComissao(id);
    }
    
    public double pegarVendas(int id){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.pegarVendas(id);
    }
    
    public double pegarBonus(int id){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.pegarBonus(id);
    }
}
