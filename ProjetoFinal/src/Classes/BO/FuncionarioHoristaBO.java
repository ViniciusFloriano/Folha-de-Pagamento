package Classes.BO;
import java.util.List;
import Classes.DAO.FuncionarioHoristaDAO;
import Classes.DTO.FuncionarioHorista;
public class FuncionarioHoristaBO {

	public boolean inserir(FuncionarioHorista funcionarioHorista, int idFuncionario){
        if (existe(idFuncionario) != true) {
        	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
            return funcionarioHoristaDAO.inserir(funcionarioHorista, idFuncionario);
        }
        return false;
    }
    
    public boolean alterar(double salario, int idFuncionario){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.alterarSalarioHora(salario, idFuncionario);
    }
    
    public boolean excluir(int idFuncionario){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.excluir(idFuncionario);
    }
    
    public FuncionarioHorista procurarPorCodigo(int idFuncionario){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.procurarPorId(idFuncionario);
    }
    
    public FuncionarioHorista procurarPorDescricao(int idFuncionario){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.procurarPorDescricao(idFuncionario);
    }
    
    public boolean existe(int idUsuario){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.existe(idUsuario);
    }
    
    public List<FuncionarioHorista> pesquisarTodos(){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.pesquisarTodos();
    }
	
    public int pegarId(int id){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.pegarId(id);
    }
    
    public double pegarSalarioHora(int id){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.pegarSalarioHora(id);
    }
    
    public double pegarHorasTrabalhadas(int id){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.pegarHorasTrabalhadas(id);
    }
    
    public double pegarHorasExtras(int id){
    	FuncionarioHoristaDAO funcionarioHoristaDAO = new FuncionarioHoristaDAO();
        return funcionarioHoristaDAO.pegarHorasExtras(id);
    }
}
