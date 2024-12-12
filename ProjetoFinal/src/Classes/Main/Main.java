package Classes.Main;
import java.util.Scanner;
import Classes.BO.*;
import Classes.DTO.*;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		UsuarioBO usuarioBO = new UsuarioBO();
		int op = 0;
		boolean termina = false;
		
		do {
			System.out.println("---  Menu do sistema  ---"
					+ "\n" + "1. Logar"
					+ "\n" + "2. Cadastrar"
					+ "\n" + "3. Sair");
			System.out.print("Escholha sua opção: ");
			op = scan.nextInt();
			while (op < 1 || op > 3) {
				System.out.println("Opção inválida");
				System.out.print("Escholha sua opção: ");
				op = scan.nextInt();
				if (op == 3) {
					termina = true;
					break;
				}
			}
			if (op == 1) {
				System.out.println("\n" + "---  Login do sistema  ---");
				scan.nextLine();
				System.out.print("\n" + "email: ");
				String email = scan.nextLine();
				System.out.print("\n" + "Senha: ");
				String senha = scan.nextLine();
				Usuario usuarioLogado = UsuarioBO.logar(email, senha);
				while (usuarioLogado == null) {
					System.out.println("\n" + "Algo deu errado, email ou senha incorretos");
					System.out.print("\n" + "email: ");
					email = scan.nextLine();
					System.out.print("\n" + "Senha: ");
					senha = scan.nextLine();
					usuarioLogado = UsuarioBO.logar(email, senha);
				}
				if (usuarioLogado != null) {
					System.out.println("\n" + "Logado com sucesso");
					int idUsuarioLogado = usuarioBO.pegarId(usuarioLogado);
					if ("ADMINISTRADOR".equals(usuarioLogado.getTipoString())) {
						do {
							System.out.println("\n" + "---  Menu Admim  ---"
											 + "\n" + "1. ativar/desativar um usuario"
											 + "\n" + "2. atualizar um usuario"
											 + "\n" + "3. cadastrar um funcionario"
											 + "\n" + "4. atualizar um funcionario"
											 + "\n" + "5. cadastrar os beneficios/descontos do funcionario CLT"
											 + "\n" + "6. atualizar os beneficios/descontos do funcionario CLT"
											 + "\n" + "7. cadastrar um banco"
											 + "\n" + "8. atualizar um banco"
											 + "\n" + "9. criar uma folha de pagamento"
											 + "\n" + "10. atualizar uma folha de pagamento"
											 + "\n" + "11. consulta"
											 + "\n" + "12. sair");
							System.out.print("Escholha sua opção: ");
							op = scan.nextInt();
							if (op == 12) {
								System.out.println("\n" + "---  Fim do programa  ---");
								termina = true;
							}
						} while (termina != true);
					} else if ("FUNCIONARIO".equals(usuarioLogado.getTipoString())) {
						do {
							System.out.println("\n" + "---  Menu Funcionário  ---"
											 + "\n" + "1. atualizar informações"
											 + "\n" + "2. cadastrar um banco"
											 + "\n" + "3. atualizar o banco"
											 + "\n" + "4. consulta"
											 + "\n" + "5. sair");
							System.out.print("Escholha sua opção: ");
							op = scan.nextInt();
							if (op == 5) {
								System.out.println("\n" + "---  Fim do programa  ---");
								termina = true;
							}
						} while (termina != true);
					}
				} 
			} else if (op == 2) {
				scan.nextLine();
				System.out.print("\n" + "Nome: ");
				String nome = scan.nextLine();
				System.out.print("\n" + "Email: ");
				String email = scan.nextLine();
				System.out.print("\n" + "Senha: ");
				String senha = scan.nextLine();
				System.out.print("\n" + "Selecione um tipo de usuário: "
								+ "\n" + "  1. Administrador"
								+ "\n" + "  2. Funcionario");
				System.out.print("\n\n" + "Tipo: ");
				int tipo = scan.nextInt();
				if (tipo == 1) {
					Usuario usuario = new Usuario(nome, email, senha, TipoUsuario.ADMINISTRADOR, StatusUsuario.ATIVADO);
					if (usuarioBO.inserir(usuario)) {
						System.out.println("\n" + "Cadastrado com sucesso" + "\n");
					} else {
						System.out.println("\n" + "Algo deu errado" + "\n");
					}
				} else if (tipo == 2) {
					Usuario usuario = new Usuario(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
					if (usuarioBO.inserir(usuario)) {
						System.out.println("\n" + "Cadastrado com sucesso" + "\n");
					} else {
						System.out.println("\n" + "Algo deu errado" + "\n");
					}
				}
			} else if (op == 3) {
				System.out.println("\n" + "---  Fim do programa  ---");
				termina = true;
			}
			
		} while (termina != true);
		
		scan.close();		
	}
}
