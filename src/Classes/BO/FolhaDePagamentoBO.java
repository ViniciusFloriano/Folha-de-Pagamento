package Classes.BO;
import java.util.List;
import Classes.DAO.FolhaDePagamentoDAO;
import Classes.DTO.FolhaDePagamento;
public class FolhaDePagamentoBO {

	public boolean inserir(FolhaDePagamento folhaDePagamento, int idFuncionario){
		FolhaDePagamentoDAO folhaDePagamentoDAO = new FolhaDePagamentoDAO();
        return folhaDePagamentoDAO.inserir(folhaDePagamento, idFuncionario);
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
    */
    public List<FolhaDePagamento> pesquisarTodos(int idFuncionario){
    	FolhaDePagamentoDAO folhaDePagamentoDAO = new FolhaDePagamentoDAO();
        return folhaDePagamentoDAO.pesquisarTodos(idFuncionario);
    }
}
