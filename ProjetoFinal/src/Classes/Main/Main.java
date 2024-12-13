package Classes.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Classes.BO.*;
import Classes.DTO.*;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		UsuarioBO usuarioBO = new UsuarioBO();
		FuncionarioCLTBO funcionarioCLTBO = new FuncionarioCLTBO();
		FuncionarioHoristaBO funcionarioHoristaBO = new FuncionarioHoristaBO();
		FuncionarioComissionadoBO funcionarioComissionadoBO = new FuncionarioComissionadoBO();
		BancoBO bancoBO = new BancoBO();
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
			if (op == 1) { // opção de logar no inicio do sistema
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
						do { // menu do admim
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
							while (op < 1 || op > 12) {
								System.out.println("Opção inválida");
								System.out.print("Escholha sua opção: ");
								op = scan.nextInt();
								if (op == 12) {
									termina = true;
									break;
								}
							}
							if (op == 3) { // opção de cadastrar funcionario no menu do admim
								scan.nextLine();
								System.out.println("\n" + "---  Menu de Cadastro (funcionario) ---");
								System.out.print("\n" + "Nome: ");
								String nome = scan.nextLine();
								System.out.print("\n" + "Email: ");
								email = scan.nextLine();
								System.out.print("\n" + "Senha: ");
								senha = scan.nextLine();
								System.out.println("\n" + "Selecione um tipo de funcionário: "
										+ "\n" + "  1. CLT"
										+ "\n" + "  2. Horista"
										+ "\n" + "  3. Comissionado");
								System.out.print("\n" + "Tipo: ");
								int tipo = scan.nextInt();
								scan.nextLine();
								System.out.print("\n" + "Cargo: ");
								String cargo = scan.nextLine();
								Usuario usuario = new Usuario(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
								if (usuarioBO.inserir(usuario)) {
									int idUsuario = usuarioBO.pegarId(usuario);
									if (tipo == 1) { // cadastro CLT pelo admim
										System.out.print("\n" + "Salário Mensal: ");
										double salarioMensal = scan.nextDouble();
										FuncionarioCLT funcCLT = new FuncionarioCLT(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioMensal);
										if (funcionarioCLTBO.inserir(funcCLT, idUsuario)) {
											System.out.println("\n" + "Cadastrado com sucesso");
										} else {
											System.out.println("\n" + "Algo deu errado");
										}
									} else if (tipo == 2) { // cadastro Horista pelo admim
										System.out.print("\n" + "Salário por Hora: ");
										double salarioHora = scan.nextDouble();
										FuncionarioHorista funcHorista = new FuncionarioHorista(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioHora);
										if (funcionarioHoristaBO.inserir(funcHorista, idUsuario)) {
											System.out.println("\n" + "Cadastrado com sucesso");
										} else {
											System.out.println("\n" + "Algo deu errado");
										}
									} else if (tipo == 3) { // cadastro Comisisonado pelo admim
										System.out.print("\n" + "Salário Base: ");
										double salarioBase = scan.nextDouble();
										System.out.print("\n" + "Porcentagem da Comissão (número inteiro): ");
										double porcentagemNaoAjustada = scan.nextDouble();
										double porcentagemReal = porcentagemNaoAjustada / 100;
										FuncionarioComissionado funcComissionado = new FuncionarioComissionado(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioBase, porcentagemReal);
										if (funcionarioComissionadoBO.inserir(funcComissionado, idUsuario)) {
											System.out.println("\n" + "Cadastrado com sucesso");
										} else {
											System.out.println("\n" + "Algo deu errado");
										}
									}
								}
							} else if (op == 7) { // opção de cadastrar o banco no menu do admim
								scan.nextLine();
								List<Usuario> lista = new ArrayList<Usuario>();
								lista = usuarioBO.pesquisarTodos();
								System.out.println("\n" + "---  Menu de Cadastro (Dados Bancarios)  ---");
								System.out.print("\n" + "Selecione para que usuário você ira cadastrar os dados bancarios");
								System.out.println("\n" + "Usuários disponíveis:");
								for (int i = 0; i < lista.size(); i++) {
									System.out.println((i + 1) + ". " + lista.get(i).toString());
								}
								System.out.print("Selecione o número do usuário: ");
								int escolha = scan.nextInt();
								while (op < 1 || op > lista.size()) {
									System.out.println("Opção inválida");
									System.out.print("Selecione o número do usuário: ");
									op = scan.nextInt();
								}
								Usuario funcionarioSelecionado = lista.get(escolha - 1);
								int idFuncionario = usuarioBO.pegarId(funcionarioSelecionado);
								System.out.print("\n" + "Nome do Banco: ");
								String nomeBanco = scan.nextLine();
								System.out.print("\n" + "Agência: ");
								String agencia = scan.nextLine();
								System.out.print("\n" + "Número da Conta: ");
								String numConta = scan.nextLine();
								System.out.println("\n" + "Selecione um tipo de conta: "
										+ "\n" + "  1. Corrente"
										+ "\n" + "  2. Poupança");
								System.out.print("\n" + "Tipo: ");
								int tipo = scan.nextInt();
								if (tipo == 1) { // cadastro conta corrente
									Banco dadoBanco = new Banco(funcionarioSelecionado, nomeBanco, agencia, numConta, TipoConta.CORRENTE);
									if (bancoBO.inserir(dadoBanco, idFuncionario)) {
										System.out.println("\n" + "Cadastrado com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								} else if (tipo == 2) { // cadastro conta poupança
									Banco dadoBanco = new Banco(funcionarioSelecionado, nomeBanco, agencia, numConta, TipoConta.POUPANCA);
									if (bancoBO.inserir(dadoBanco, idFuncionario)) {
										System.out.println("\n" + "Cadastrado com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								}
							} else if (op == 12) { // opção de sair no menu do admim
								System.out.println("\n" + "---  Fim do programa  ---");
								termina = true;
							}
						} while (termina != true);
					} else if ("FUNCIONARIO".equals(usuarioLogado.getTipoString())) {
						do { //menu funcionario
							System.out.println("\n" + "---  Menu Funcionário  ---"
											 + "\n" + "1. atualizar informações"
											 + "\n" + "2. cadastrar um banco"
											 + "\n" + "3. atualizar o banco"
											 + "\n" + "4. consulta"
											 + "\n" + "5. sair");
							System.out.print("Escholha sua opção: ");
							op = scan.nextInt();
							while (op < 1 || op > 5) {
								System.out.println("Opção inválida");
								System.out.print("Escholha sua opção: ");
								op = scan.nextInt();
								if (op == 5) {
									termina = true;
									break;
								}
							}
							if (op == 5) { // opção de sair no menu do funcionario
								System.out.println("\n" + "---  Fim do programa  ---");
								termina = true;
							}
						} while (termina != true);
					}
				} 
			} else if (op == 2) { // opção de cadastro no inicio do sistema
				scan.nextLine();
				System.out.println("\n" + "---  Menu de Cadastro  ---");
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
				if (tipo == 1) { // cadastro de admim
					Usuario usuario = new Usuario(nome, email, senha, TipoUsuario.ADMINISTRADOR, StatusUsuario.ATIVADO);
					if (usuarioBO.inserir(usuario)) {
						System.out.println("\n" + "Cadastrado com sucesso" + "\n");
					} else {
						System.out.println("\n" + "Algo deu errado" + "\n");
					}
				} else if (tipo == 2) { // cadastro funcionario
					Usuario usuario = new Usuario(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
					usuarioBO.inserir(usuario);
					int idUsuario = usuarioBO.pegarId(usuario);
					System.out.println("\n" + "Selecione um tipo de funcionário: "
							+ "\n" + "  1. CLT"
							+ "\n" + "  2. Horista"
							+ "\n" + "  3. Comissionado");
					System.out.print("\n" + "Tipo: ");
					tipo = scan.nextInt();
					scan.nextLine();
					System.out.print("\n" + "Cargo: ");
					String cargo = scan.nextLine();
					if (tipo == 1) { // cadastro CLT
						System.out.print("\n" + "Salário Mensal: ");
						double salarioMensal = scan.nextDouble();
						FuncionarioCLT funcCLT = new FuncionarioCLT(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioMensal);
						if (funcionarioCLTBO.inserir(funcCLT, idUsuario)) {
							System.out.println("\n" + "Cadastrado com sucesso");
						} else {
							System.out.println("\n" + "Algo deu errado");
						}
					} else if (tipo == 2) { // cadastro Horista
						System.out.print("\n" + "Salário por Hora: ");
						double salarioHora = scan.nextDouble();
						FuncionarioHorista funcHorista = new FuncionarioHorista(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioHora);
						if (funcionarioHoristaBO.inserir(funcHorista, idUsuario)) {
							System.out.println("\n" + "Cadastrado com sucesso");
						} else {
							System.out.println("\n" + "Algo deu errado");
						}
					} else if (tipo == 3) { // cadastro Comissionado
						System.out.print("\n" + "Salário Base: ");
						double salarioBase = scan.nextDouble();
						System.out.print("\n" + "Porcentagem da Comissão (número inteiro): ");
						double porcentagemNaoAjustada = scan.nextDouble();
						double porcentagemReal = porcentagemNaoAjustada / 100;
						FuncionarioComissionado funcComissionado = new FuncionarioComissionado(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioBase, porcentagemReal);
						if (funcionarioComissionadoBO.inserir(funcComissionado, idUsuario)) {
							System.out.println("\n" + "Cadastrado com sucesso");
						} else {
							System.out.println("\n" + "Algo deu errado");
						}
					}
				}
			} else if (op == 3) { // opção de sair no inicio do sistema
				System.out.println("\n" + "---  Fim do programa  ---");
				termina = true;
			}
			
		} while (termina != true);
		
		scan.close();		
	}
}
