package Classes.BO;
import java.util.List;
import Classes.DAO.BancoDAO;
import Classes.DTO.Banco;
public class BancoBO {

	public boolean inserir(Banco banco, int idFuncionario){
		BancoDAO bancoDAO = new BancoDAO();
        return bancoDAO.inserir(banco, idFuncionario);
    }
    
    public boolean alterarNomeBanco(Banco banco, int idFuncionario){
    	BancoDAO bancoDAO = new BancoDAO();
        return bancoDAO.alterarNomeBanco(banco, idFuncionario);
    }
    
    public boolean excluir(int idFuncionario){
    	BancoDAO bancoDAO = new BancoDAO();
        return bancoDAO.excluir(idFuncionario);
    }
    
    public Banco procurarPorId(int idFuncionario){
    	BancoDAO bancoDAO = new BancoDAO();
        return bancoDAO.procurarPorId(idFuncionario);
    }
    
    public Banco procurarPorNomeBanco(String pesquisa){
    	BancoDAO bancoDAO = new BancoDAO();
        return bancoDAO.procurarPorNomeBanco(pesquisa);
    }
    
    public boolean existe(int idFuncionario){
    	BancoDAO bancoDAO = new BancoDAO();
        return bancoDAO.existe(idFuncionario);
    }
    
    public List<Banco> pesquisarTodos(){
    	BancoDAO bancoDAO = new BancoDAO();
        return bancoDAO.pesquisarTodos();
    }
}
