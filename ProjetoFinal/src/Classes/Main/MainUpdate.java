package Classes.Main;
import java.util.Scanner;
import Classes.BO.*;
import Classes.DTO.*;
public class MainUpdate {
	public static void main(String[] args) {
		
		// Teste Alterar	
		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuario = new Usuario("Amor" ,"vinicius.floriano@gmail.com", "123eq123",TipoUsuario.ADMINISTRADOR, StatusUsuario.ATIVADO);
		Scanner scan = new Scanner(System.in);

		int idUsuario1 = usuarioBO.pegarId(usuario);

		System.out.println("---  Menu de Atualização e Pesquisa  ---"
						+ "\n" + "1. Atualizar Nome"
						+ "\n" + "2. Atualizar Email"
						+ "\n" + "3. Atualizar Senha"
						+ "\n" + "4. Sair");
		System.out.print("Escholha sua opção: ");
		int op = scan.nextInt();
		while (op < 1 || op > 4) {
			System.out.println("Opção inválida");
			System.out.print("Escholha sua opção: ");
			op = scan.nextInt();
		}
		if (op == 1) {
			scan.nextLine();
			System.out.print("\n" + "Qual nome você que colocar: ");
			String novoNome = scan.nextLine();
			usuario.setNome(novoNome);
			usuarioBO.alterar(usuario, idUsuario1);
			System.out.println(usuario);
		} else if (op == 2) {
			scan.nextLine();
			System.out.print("\n" + "Qual email você quer colocar: ");
			String novoEmail = scan.nextLine();
			usuario.setEmail(novoEmail);
			usuarioBO.alterar(usuario, idUsuario1);
			System.out.println(usuario);
		} else if (op == 3) {
			scan.nextLine();
			System.out.print("\n" + "Qual senha você quer colocar: ");
			String novaSenha = scan.nextLine();
			usuario.setSenha(novaSenha);
			usuarioBO.alterar(usuario, idUsuario1);
			System.out.println(usuario);
		} else if (op == 4) {
			System.out.println("\n" + "---  Fim do programa  ---");		
		} 
		
		//usuario = usuarioBO.procurarPorEmail(usuario, pesquisa);			
		
		scan.close();
	}

}
