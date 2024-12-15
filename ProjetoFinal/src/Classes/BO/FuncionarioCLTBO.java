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
    
    public boolean alterarSalario(double salario, int idFuncionario){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.alterarSalario(salario, idFuncionario);
    }
    
    public boolean excluir(int idFuncionario){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.excluir(idFuncionario);
    }
    
    public FuncionarioCLT procurarPorId(int idFuncionario){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.procurarPorId(idFuncionario);
    }
    
    public FuncionarioCLT procurarPorDescricao(int idFuncionario){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.procurarPorDescricao(idFuncionario);
    }
    
    public boolean existe(FuncionarioCLT funcionarioclt, int idUsuario){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.existe(funcionarioclt, idUsuario);
    }
    
    public List<FuncionarioCLT> pesquisarTodos(){
    	FuncionarioCLTDAO funcionarioCLTDAO = new FuncionarioCLTDAO();
        return funcionarioCLTDAO.pesquisarTodos();
    }
    
    public int pegarId(int id){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.pegarId(id);
    }
    
    public double pegarSalario(int id){
    	FuncionarioCLTDAO funcionariocltDAO = new FuncionarioCLTDAO();
        return funcionariocltDAO.pegarSalario(id);
    }
}
