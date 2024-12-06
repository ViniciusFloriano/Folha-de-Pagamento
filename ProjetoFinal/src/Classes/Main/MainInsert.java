package Classes.Main;
import Classes.BO.*;
import Classes.DTO.*;
public class MainInsert {
	public static void main(String[] args) {
		
		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuario1 = new Usuario("Vinicius", "vini@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		Usuario usuario2 = new Usuario("Teste", "teste@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		
		if (usuarioBO.inserir(usuario1) && usuarioBO.inserir(usuario2)) {
			System.out.println("Inserido com Sucesso");
			
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
		int idUsuario1 = usuarioBO.pegarId(usuario1);
		int idUsuario2 = usuarioBO.pegarId(usuario2);
		
		FuncionarioCLTBO funcionariocltBO = new FuncionarioCLTBO();
		FuncionarioCLT funcionarioclt = new FuncionarioCLT(usuario1.getNome(), usuario1.getEmail(), usuario1.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Analista de TI", 2000.00);
		
		FuncionarioHoristaBO funcionarioHoristaBO = new FuncionarioHoristaBO();
		FuncionarioHorista funcionarioHorista = new FuncionarioHorista(usuario2.getNome(), usuario2.getEmail(), usuario2.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Freelancer", 50.00);
		
		if (funcionariocltBO.inserir(funcionarioclt, idUsuario1) && funcionarioHoristaBO.inserir(funcionarioHorista, idUsuario2)) {
			System.out.println("Inserido com Sucesso");
			
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
	}
}
