package Classes.BO;
import Classes.DAO.BeneficioDescontoDAO;
import java.util.List;
import Classes.DTO.BeneficioDesconto;
public class BeneficioDescontoBO {

	public boolean inserir(BeneficioDesconto beneficioDesconto, int idFuncionario){
    	BeneficioDescontoDAO beneficioDescontoDAO = new BeneficioDescontoDAO();
        return beneficioDescontoDAO.inserir(beneficioDesconto, idFuncionario);
    }
    
    public boolean alterarDescricao(BeneficioDesconto beneficioDesconto, int idFuncionario){
    	BeneficioDescontoDAO beneficioDescontoDAO = new BeneficioDescontoDAO();
        return beneficioDescontoDAO.alterarDescricao(beneficioDesconto, idFuncionario);
    }
    
    public boolean excluir(int idFuncionario){
    	BeneficioDescontoDAO beneficioDescontoDAO = new BeneficioDescontoDAO();
        return beneficioDescontoDAO.excluir(idFuncionario);
    }
    
    public BeneficioDesconto procurarPorFuncionario(int idFuncionario){
    	BeneficioDescontoDAO beneficioDescontoDAO = new BeneficioDescontoDAO();
        return beneficioDescontoDAO.procurarPorFuncionario(idFuncionario);
    }
    
    public BeneficioDesconto procurarPorDescricao(String pesquisa){
    	BeneficioDescontoDAO beneficioDescontoDAO = new BeneficioDescontoDAO();
        return beneficioDescontoDAO.procurarPorDescricao(pesquisa);
    }
    
    public boolean existe(int idFuncionario){
    	BeneficioDescontoDAO beneficioDescontoDAO = new BeneficioDescontoDAO();
        return beneficioDescontoDAO.existe(idFuncionario);
    }
    
    public List<BeneficioDesconto> pesquisarTodos(){
    	BeneficioDescontoDAO beneficioDescontoDAO = new BeneficioDescontoDAO();
        return beneficioDescontoDAO.pesquisarTodos();
    }
	
	public List<BeneficioDesconto> buscarDescontosPorFuncionario(int idFuncionario){
    	BeneficioDescontoDAO beneficioDescontoDAO = new BeneficioDescontoDAO();
        return beneficioDescontoDAO.buscarDescontosPorFuncionario(idFuncionario);
    }
}
