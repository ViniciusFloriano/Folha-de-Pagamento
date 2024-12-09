package Classes.Main;
import Classes.BO.*;
import Classes.DTO.*;
public class MainInsert {
	public static void main(String[] args) {
		
		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuarioADM = new Usuario("Vinicius da Silva Floriano", "vinicius.floriano@gmail.com", "123eq123", TipoUsuario.ADMINISTRADOR, StatusUsuario.ATIVADO);
		Usuario usuario1 = new Usuario("Teste CLT", "CLT@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		Usuario usuario2 = new Usuario("Teste Horista", "Horista@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		Usuario usuario3 = new Usuario("Teste Comissionado", "Comissionado@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		
		if (usuarioBO.inserir(usuarioADM) && usuarioBO.inserir(usuario1) && usuarioBO.inserir(usuario2) && usuarioBO.inserir(usuario3)) {
			System.out.println("Inserido com Sucesso");
			
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
		int idUsuario1 = usuarioBO.pegarId(usuario1);
		int idUsuario2 = usuarioBO.pegarId(usuario2);
		int idUsuario3 = usuarioBO.pegarId(usuario3);
		
		FuncionarioCLTBO funcionariocltBO = new FuncionarioCLTBO();
		FuncionarioCLT funcionarioclt = new FuncionarioCLT(usuario1.getNome(), usuario1.getEmail(), usuario1.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Analista de TI", 2000.00);
		
		FuncionarioHoristaBO funcionarioHoristaBO = new FuncionarioHoristaBO();
		FuncionarioHorista funcionarioHorista = new FuncionarioHorista(usuario2.getNome(), usuario2.getEmail(), usuario2.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Freelancer", 50.00);
		
		FuncionarioComissionadoBO funcionarioComissionadoBO = new FuncionarioComissionadoBO();
		FuncionarioComissionado funcionarioComissionado = new FuncionarioComissionado(usuario3.getNome(), usuario3.getEmail(), usuario3.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Vendedor", 1500.00, 15.00);
		
		if (funcionariocltBO.inserir(funcionarioclt, idUsuario1) && funcionarioHoristaBO.inserir(funcionarioHorista, idUsuario2) && funcionarioComissionadoBO.inserir(funcionarioComissionado, idUsuario3)) {
			System.out.println("Inserido com Sucesso");
			
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
	}
}
