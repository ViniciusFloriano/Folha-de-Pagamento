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
    
    public FuncionarioComissionado procurarPorId(int idFuncionario){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.procurarPorId(idFuncionario);
    }
    
    public FuncionarioComissionado procurarPorDescricao(int idFuncionario){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.procurarPorDescricao(idFuncionario);
    }
    
    public boolean existe(FuncionarioComissionado funcionarioComissionado, int idFuncionario){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.existe(funcionarioComissionado, idFuncionario);
    }
    
    public List<FuncionarioComissionado> pesquisarTodos(){
    	FuncionarioComissionadoDAO funcionarioComissionadoDAO = new FuncionarioComissionadoDAO();
        return funcionarioComissionadoDAO.pesquisarTodos();
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
