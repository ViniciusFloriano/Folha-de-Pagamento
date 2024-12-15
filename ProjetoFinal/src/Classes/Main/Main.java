package Classes.Main;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Classes.BO.*;
import Classes.DTO.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		UsuarioBO usuarioBO = new UsuarioBO();
		FuncionarioCLTBO funcionarioCLTBO = new FuncionarioCLTBO();
		FuncionarioHoristaBO funcionarioHoristaBO = new FuncionarioHoristaBO();
		FuncionarioComissionadoBO funcionarioComissionadoBO = new FuncionarioComissionadoBO();
		BancoBO bancoBO = new BancoBO();
		BeneficioDescontoBO benDesBO = new BeneficioDescontoBO();
		FolhaDePagamentoBO folhaDePagamentoBO = new FolhaDePagamentoBO();
		LocalDate hoje = LocalDate.now();
		YearMonth mesAnterior = YearMonth.from(hoje.minusMonths(1));
		LocalDate primeiroDia = mesAnterior.atDay(1);
		LocalDate ultimoDia = mesAnterior.atEndOfMonth();
		int op = 0;
		boolean termina = false;
		boolean continua = false;
		
		
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
				Usuario usuarioLogado = null;
				if (senha.isBlank()) { // caso a senha seja em branco
					usuarioLogado = UsuarioBO.logarSemSenha(email);
					while (usuarioLogado == null) {
						System.out.println("\n" + "Algo deu errado, email incorreto ou usuario desativado");
						System.out.print("\n" + "email: ");
						email = scan.nextLine();
						usuarioLogado = UsuarioBO.logarSemSenha(email);
					}
					System.out.print("\n" + "Cadastre sua senha: ");
					senha = scan.nextLine();
					int idUsuarioLogado = usuarioBO.pegarId(usuarioLogado);
					usuarioBO.atualizaSenha(senha, idUsuarioLogado);
					continua = true;
				} else { // caso tenha senha
					usuarioLogado = UsuarioBO.logar(email, senha);
					while (usuarioLogado == null) {
						System.out.println("\n" + "Algo deu errado, email ou senha incorretos, ou usuario desativado");
						System.out.print("\n" + "email: ");
						email = scan.nextLine();
						System.out.print("\n" + "Senha: ");
						senha = scan.nextLine();
						usuarioLogado = UsuarioBO.logar(email, senha);
					}
					continua = true;
				}
				if (usuarioLogado != null && continua == true) {
					System.out.println("\n" + "Logado com sucesso");
					int idUsuarioLogado = usuarioBO.pegarId(usuarioLogado);
					if ("ADMINISTRADOR".equals(usuarioLogado.getTipoString())) {
						do { // menu do admim
							System.out.println("\n" + "---  Menu Admim  ---"
											 + "\n" + "1. ativar/desativar um usuário"
											 + "\n" + "2. cadastrar um usuário"
											 + "\n" + "3. cadastrar um funcionário"
											 + "\n" + "4. cadastrar os beneficios/descontos do funcionario CLT"
											 + "\n" + "5. cadastrar um banco"
											 + "\n" + "6. gerar uma folha de pagamento"
											 + "\n" + "7. sair");
							System.out.print("Escholha sua opção: ");
							op = scan.nextInt();
							while (op < 1 || op > 7) {
								System.out.println("Opção inválida");
								System.out.print("Escholha sua opção: ");
								op = scan.nextInt();
								if (op == 7) {
									termina = true;
									break;
								}
							}
							if (op == 1) { // opção de ativar/desativar
								scan.nextLine();
								List<Usuario> lista = new ArrayList<Usuario>();
								lista = usuarioBO.pesquisarTodos();
								System.out.println("\n" + "---  Menu de Ativação/Desativação (usuário) ---");
								System.out.print("\n" + "Selecione qual usuário você quer ativar/desativar");
								System.out.println("\n\n" + "Usuários disponíveis:");
								for (int i = 0; i < lista.size(); i++) {
									System.out.println((i + 1) + ". " + lista.get(i).mostrarTodos());
								}
								System.out.print("Selecione o número do usuário: ");
								int escolha = scan.nextInt();
								while (escolha < 1 || escolha > lista.size()) {
									System.out.println("Opção inválida");
									System.out.print("Selecione o número do usuário: ");
									escolha = scan.nextInt();
								}
								Usuario usuarioSelecionado = lista.get(escolha - 1);
								int idUsuarioSelecionado = usuarioBO.pegarId(usuarioSelecionado);
								System.out.println("\n" + "Selecione: "
										+ "\n" + "  1. Ativar"
										+ "\n" + "  2. Desativar");
								System.out.print("\n" + "Escolha: ");
								int escolhaAtvDtv = scan.nextInt();
								scan.nextLine();
								if (escolhaAtvDtv == 1) {
									if(usuarioBO.ativar(idUsuarioSelecionado)) {
										System.out.println("\n" + "Usuário ativado com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								} else if (escolhaAtvDtv == 2) {
									if(usuarioBO.desativar(idUsuarioSelecionado)) {
										System.out.println("\n" + "Usuário desativado com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								}
							} else if (op == 2) { // opção de cadastro no menu do admim
								
								
								
								
								// em pensamento 
								
								
								
							} else if (op == 3) { // opção de cadastrar funcionario no menu do admim
								scan.nextLine();
								System.out.println("\n" + "---  Menu de Cadastro (funcionario) ---");
								System.out.print("\n" + "Nome: ");
								String nome = scan.nextLine();
								System.out.print("\n" + "Email: ");
								email = scan.nextLine();
								System.out.println("\n" + "Selecione um tipo de funcionário: "
										+ "\n" + "  1. CLT"
										+ "\n" + "  2. Horista"
										+ "\n" + "  3. Comissionado");
								System.out.print("\n" + "Tipo: ");
								int tipo = scan.nextInt();
								scan.nextLine();
								System.out.print("\n" + "Cargo: ");
								String cargo = scan.nextLine();
								Usuario usuario = new Usuario(nome, email, "", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
								if (usuarioBO.inserir(usuario)) {
									int idUsuario = usuarioBO.pegarId(usuario);
									if (tipo == 1) { // cadastro CLT pelo admim
										System.out.print("\n" + "Salário Mensal: ");
										double salarioMensal = scan.nextDouble();
										FuncionarioCLT funcCLT = new FuncionarioCLT(nome, email, "", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioMensal);
										int idTransicao = usuarioBO.pegarId(usuario);
										if (funcionarioCLTBO.inserir(funcCLT, idUsuario)) {
											int idFuncionario = funcionarioCLTBO.pegarId(idTransicao);
											// criação dos descontos obrigatorios para o clt
											BeneficioDesconto inss = new BeneficioDesconto("INSS", 0, TipoBenDes.DESCONTO);
											if (funcCLT.getSalarioMensal() <= 1412.00) {
												inss.setValor(funcCLT.getSalarioMensal() * 0.075);
											} else if (funcCLT.getSalarioMensal() >= 1412.01 && funcCLT.getSalarioMensal() <= 2666.68) {
												inss.setValor(funcCLT.getSalarioMensal() * 0.09);
											} else if (funcCLT.getSalarioMensal() >= 2666.69 && funcCLT.getSalarioMensal() <= 4000.03) {
												inss.setValor(funcCLT.getSalarioMensal() * 0.12);
											} else if (funcCLT.getSalarioMensal() >= 4000.04 && funcCLT.getSalarioMensal() <= 7768.02) {
												inss.setValor(funcCLT.getSalarioMensal() * 0.14);
											}
											
											BeneficioDesconto fgts = new BeneficioDesconto("FGTS", funcCLT.getSalarioMensal() * 0.08, TipoBenDes.DESCONTO);
											
											BeneficioDesconto irrf = new BeneficioDesconto("IRRF", 0, TipoBenDes.DESCONTO); //Imposto de Renda Retido na Fonte
											if (funcCLT.getSalarioMensal() >= 1903.99 && funcCLT.getSalarioMensal() <= 2826.65) {
												irrf.setValor(funcCLT.getSalarioMensal() * 0.075);
											} else if (funcCLT.getSalarioMensal() >= 2826.66 && funcCLT.getSalarioMensal() <= 3751.05) {
												irrf.setValor(funcCLT.getSalarioMensal() * 0.15);
											} else if (funcCLT.getSalarioMensal() >= 3751.06 && funcCLT.getSalarioMensal() <= 4664.68) {
												irrf.setValor(funcCLT.getSalarioMensal() * 0.225);
											} else if (funcCLT.getSalarioMensal() >= 4664.69) {
												irrf.setValor(funcCLT.getSalarioMensal() * 0.275);
											}
											
											if (benDesBO.inserir(inss, idFuncionario) && benDesBO.inserir(fgts, idFuncionario) && benDesBO.inserir(irrf, idFuncionario)) {
												System.out.println("\n" + "Funcionario CLT cadastrado com sucesso");
											} else {
												System.out.println("\n" + "Algo deu errado");
											}
										}
									} else if (tipo == 2) { // cadastro Horista pelo admim
										System.out.print("\n" + "Salário por Hora: ");
										double salarioHora = scan.nextDouble();
										System.out.print("\n" + "Horas Trabalhadas (no mês): ");
										double horasTrabalhadas = scan.nextDouble();
										System.out.print("\n" + "Horas Extras (caso houver): ");
										double horasExtras = scan.nextDouble();
										FuncionarioHorista funcHorista = new FuncionarioHorista(nome, email, "", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioHora, horasTrabalhadas, horasExtras);
										if (funcionarioHoristaBO.inserir(funcHorista, idUsuario)) {
											System.out.println("\n" + "Funcionario Horista cadastrado com sucesso");
										} else {
											System.out.println("\n" + "Algo deu errado");
										}
									} else if (tipo == 3) { // cadastro Comisisonado pelo admim
										System.out.print("\n" + "Salário Base: ");
										double salarioBase = scan.nextDouble();
										System.out.print("\n" + "Porcentagem da Comissão (número inteiro): ");
										double porcentagemNaoAjustada = scan.nextDouble();
										double porcentagemReal = porcentagemNaoAjustada / 100;
										System.out.print("\n" + "Valor total de vendas realizadas (no mês): ");
										double vendaRealizadas = scan.nextInt();
										System.out.print("\n" + "Bonus (caso houver): ");
										double bonus = scan.nextDouble();
										FuncionarioComissionado funcComissionado = new FuncionarioComissionado(nome, email, "", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioBase, porcentagemReal, vendaRealizadas, bonus);
										if (funcionarioComissionadoBO.inserir(funcComissionado, idUsuario)) {
											System.out.println("\n" + "Funcionario Comissioando cadastrado com sucesso");
										} else {
											System.out.println("\n" + "Algo deu errado");
										}
									}
								}
							} else if (op == 4) { // opção de cadastrar um beneficio/desconto no menu do admim
								scan.nextLine();
								List<Usuario> lista = new ArrayList<Usuario>();
								lista = usuarioBO.pesquisarTodosFuncionarios();
								System.out.println("\n" + "---  Menu de Cadastro (Beneficio/Desconto)  ---");
								System.out.print("\n" + "Selecione para que usuário você ira cadastrar o beneficio/desconto");
								System.out.println("\n\n" + "Funcionários disponíveis:");
								for (int i = 0; i < lista.size(); i++) {
									System.out.println((i + 1) + ". " + lista.get(i).mostrarTodosFuncionarios());
								}
								System.out.print("Selecione o número do funcionário: ");
								int escolha = scan.nextInt();
								while (escolha < 1 || escolha > lista.size()) {
									System.out.println("Opção inválida");
									System.out.print("Selecione o número do funcionário: ");
									escolha = scan.nextInt();
								}
								Usuario transicao = lista.get(escolha - 1);
								int idTransicao = usuarioBO.pegarId(transicao);
								//FuncionarioCLT funcionarioSelecionado = new FuncionarioCLT(transicao.getNome(), transicao.getEmail(), transicao.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "", 0);
								int idFuncionario = funcionarioCLTBO.pegarId(idTransicao);
								System.out.println("\n" + "Selecione: "
										+ "\n" + "  1. Beneficio"
										+ "\n" + "  2. Desconto");
								System.out.print("\n" + "Tipo: ");
								int tipo = scan.nextInt();
								scan.nextLine();
								if (tipo == 1) { // caso queira adicionar um beneficio
									System.out.print("\n" + "Descrição do beneficio: ");
									String descBen = scan.nextLine();
									System.out.print("\n" + "Valor do beneficio: ");
									double valor = scan.nextDouble();
									BeneficioDesconto beneficio = new BeneficioDesconto(descBen, valor, TipoBenDes.BENEFICIO);
									if (benDesBO.inserir(beneficio, idFuncionario)) {
										System.out.println("\n" + "Benficio cadastrado com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								} else if (tipo == 2) { // caso queira adicionar um desconto
									System.out.print("\n" + "Descrição do desconto: ");
									String descDes = scan.nextLine();
									System.out.print("\n" + "Valor do beneficio: ");
									double valor = scan.nextDouble();
									BeneficioDesconto desconto = new BeneficioDesconto(descDes, valor, TipoBenDes.DESCONTO);
									if (benDesBO.inserir(desconto, idFuncionario)) {
										System.out.println("\n" + "Desconto cadastrado com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								}
							} else if (op == 5) { // opção de cadastrar o banco no menu do admim
								scan.nextLine();
								List<Usuario> lista = new ArrayList<Usuario>();
								lista = usuarioBO.pesquisarTodosFuncionarios();
								System.out.println("\n" + "---  Menu de Cadastro (Dados Bancarios)  ---");
								System.out.print("\n" + "Selecione para que usuário você ira cadastrar os dados bancarios");
								System.out.println("\n" + "Usuários disponíveis:");
								for (int i = 0; i < lista.size(); i++) {
									System.out.println((i + 1) + ". " + lista.get(i).mostrarTodosFuncionarios());
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
										System.out.println("\n" + "Banco cadastrada com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								} else if (tipo == 2) { // cadastro conta poupança
									Banco dadoBanco = new Banco(funcionarioSelecionado, nomeBanco, agencia, numConta, TipoConta.POUPANCA);
									if (bancoBO.inserir(dadoBanco, idFuncionario)) {
										System.out.println("\n" + "Banco cadastrado com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								}
							} else if (op == 6) { // opção de criar uma folha de pagamento
								scan.nextLine();
								List<Usuario> lista = new ArrayList<Usuario>();
								lista = usuarioBO.pesquisarTodosFuncionarios();
								System.out.println("\n" + "---  Menu de Geração (Folha de Pagamento)  ---");
								System.out.print("\n" + "Selecione para que funcionário você ira gerar a folha de pagamento");
								System.out.println("\n\n" + "Funcionários disponíveis:");
								for (int i = 0; i < lista.size(); i++) {
									System.out.println((i + 1) + ". " + lista.get(i).mostrarTodosFuncionarios());
								}
								System.out.print("Selecione o número do funcionário: ");
								int escolha = scan.nextInt();
								while (escolha < 1 || escolha > lista.size()) {
									System.out.println("Opção inválida");
									System.out.print("Selecione o número do funcionário: ");
									escolha = scan.nextInt();
								}
								scan.nextLine();
								Usuario transicao = lista.get(escolha - 1);
								int idTransicao = usuarioBO.pegarId(transicao);
								FuncionarioCLT funcionarioSelecionadoCLT = null;
								FuncionarioHorista funcionarioSelecionadoHorista = null;
								FuncionarioComissionado funcionarioSelecionadoComissionado = null;
								FolhaDePagamento folha = null;
								int idFuncionario = 0;
								double salario = 0;
								double comissao = 0;
								double vendas = 0;
								double bonus = 0;
								double horasTrabalhadas = 0;
								double horasExtras = 0;
								int funcTipo = usuarioBO.verificaFuncTipo(idTransicao);
								if (funcTipo == 1) {
									StringBuilder texto = new StringBuilder();	
								    idFuncionario = funcionarioCLTBO.pegarId(idTransicao);
									salario = funcionarioCLTBO.pegarSalario(idFuncionario);
									funcionarioSelecionadoCLT = new FuncionarioCLT(transicao.getNome(), transicao.getEmail(), transicao.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "", salario);
									double salarioLiquido = funcionarioSelecionadoCLT.calcularPagamento(idFuncionario);
									System.out.print("\n" + "Observações da Folha (digite 'FIM' em uma nova linha para terminar): ");
									texto.delete(0, texto.length());
									while (true) {
							            String linha = scan.nextLine(); // Lê uma linha do texto
							            if (linha.equalsIgnoreCase("FIM")) { // Verifica se a linha é "FIM"
							                break;
							            }
							            texto.append(linha).append("\n"); // Adiciona a linha ao texto, incluindo a quebra de linha
							        }
									folha = new FolhaDePagamento(funcionarioSelecionadoCLT, hoje, primeiroDia, ultimoDia, salarioLiquido, texto.toString());
									System.out.println("\n" + "Você quer gerar o pdf da folha de pagamento? "
											+ "\n" + "  1. Sim"
											+ "\n" + "  2. Não");
									System.out.print("\n" + "Escolha: ");
									int geraPDF = scan.nextInt();
									if (geraPDF == 1) {
										String padrao = "###,###.00";
										DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
										simbolos.setDecimalSeparator(',');
								        simbolos.setGroupingSeparator('.');
										DecimalFormat formato = new DecimalFormat(padrao, simbolos);
										DateTimeFormatter formatacaoDaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
										try {
								            // Caminho onde o PDF será salvo
								            String caminhoArquivo = "FolhaPagamento_" + folha.getFuncionarioCLT().getNome() + ".pdf";

								            // Inicializa o PDF Writer e o Document
								            PdfWriter writer = new PdfWriter(new File(caminhoArquivo));
								            PdfDocument pdfDoc = new PdfDocument(writer);
								            Document document = new Document(pdfDoc);

								            // Adiciona o conteúdo ao PDF
								            document.add(new Paragraph("--- Folha de Pagamento ---\n\n"));
								            document.add(new Paragraph("Nome do Funcionário: " + folha.getFuncionarioCLT().getNome()));
								            document.add(new Paragraph("Data de Emissão da Folha de Pagamento: " + folha.getDataGeracao().format(formatacaoDaData)));
								            document.add(new Paragraph("Folha de Pagamento referente à: " + formatacaoDaData.format(folha.getPeriodoInicio()) + " a " + formatacaoDaData.format(folha.getPeriodoFim())));
								            document.add(new Paragraph("Salário Líquido: R$" + formato.format(folha.getValorPago())));
								            document.add(new Paragraph("\nObservações:\n" + folha.getObservacoes()));

								            // Fecha o documento
								            document.close();
								            System.out.println("\nPDF gerado com sucesso: " + caminhoArquivo);
								        } catch (Exception e) {
								            System.out.println("Erro ao gerar PDF: " + e.getMessage());
								        }
									}
									scan.nextLine();
								} else if (funcTipo == 2) {
									StringBuilder texto = new StringBuilder();
									idFuncionario = funcionarioHoristaBO.pegarId(idTransicao);
									salario = funcionarioHoristaBO.pegarSalarioHora(idFuncionario);
									horasTrabalhadas = funcionarioHoristaBO.pegarHorasTrabalhadas(idFuncionario);
									horasExtras = funcionarioHoristaBO.pegarHorasExtras(idFuncionario);
									funcionarioSelecionadoHorista = new FuncionarioHorista(transicao.getNome(), transicao.getEmail(), transicao.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "", salario, horasTrabalhadas, horasExtras);
									double salarioLiquido = funcionarioSelecionadoHorista.calcularPagamento(idFuncionario);
									System.out.print("\n" + "Observações da Folha (digite 'FIM' em uma nova linha para terminar): ");
									texto.delete(0, texto.length());
									while (true) {
							            String linha = scan.nextLine(); // Lê uma linha do texto
							            if (linha.equalsIgnoreCase("FIM")) { // Verifica se a linha é "FIM"
							                break;
							            }
							            texto.append(linha).append("\n"); // Adiciona a linha ao texto, incluindo a quebra de linha
							        }
									folha = new FolhaDePagamento(funcionarioSelecionadoHorista, hoje, primeiroDia, ultimoDia, salarioLiquido, texto.toString());
									System.out.println("\n" + "Você quer gerar o pdf da folha de pagamento? "
											+ "\n" + "  1. Sim"
											+ "\n" + "  2. Não");
									System.out.print("\n" + "Escolha: ");
									int geraPDF = scan.nextInt();
									if (geraPDF == 1) {
										String padrao = "###,###.00";
										DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
										simbolos.setDecimalSeparator(',');
								        simbolos.setGroupingSeparator('.');
										DecimalFormat formato = new DecimalFormat(padrao, simbolos);
										DateTimeFormatter formatacaoDaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
										try {
								            // Caminho onde o PDF será salvo
								            String caminhoArquivo = "FolhaPagamento_" + folha.getFuncionarioHorista().getNome() + ".pdf";

								            // Inicializa o PDF Writer e o Document
								            PdfWriter writer = new PdfWriter(new File(caminhoArquivo));
								            PdfDocument pdfDoc = new PdfDocument(writer);
								            Document document = new Document(pdfDoc);

								            // Adiciona o conteúdo ao PDF
								            document.add(new Paragraph("--- Folha de Pagamento ---\n\n"));
								            document.add(new Paragraph("Nome do Funcionário: " + folha.getFuncionarioHorista().getNome()));
								            document.add(new Paragraph("Data de Emissão da Folha de Pagamento: " + folha.getDataGeracao().format(formatacaoDaData)));
								            document.add(new Paragraph("Folha de Pagamento referente à: " + formatacaoDaData.format(folha.getPeriodoInicio()) + " a " + formatacaoDaData.format(folha.getPeriodoFim())));
								            document.add(new Paragraph("Salário Líquido: R$" + formato.format(folha.getValorPago())));
								            document.add(new Paragraph("\nObservações:\n" + folha.getObservacoes()));

								            // Fecha o documento
								            document.close();
								            System.out.println("\nPDF gerado com sucesso: " + caminhoArquivo);
								        } catch (Exception e) {
								            System.out.println("Erro ao gerar PDF: " + e.getMessage());
								        }
									}
									scan.nextLine();
								} else if (funcTipo == 3) {
									StringBuilder texto = new StringBuilder();
									idFuncionario = funcionarioComissionadoBO.pegarId(idTransicao);
									salario = funcionarioComissionadoBO.pegarSalarioBase(idFuncionario);
									comissao = funcionarioComissionadoBO.pegarComissao(idFuncionario);
									vendas = funcionarioComissionadoBO.pegarVendas(idFuncionario);
									bonus = funcionarioComissionadoBO.pegarBonus(idFuncionario);
									funcionarioSelecionadoComissionado = new FuncionarioComissionado(transicao.getNome(), transicao.getEmail(), transicao.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "", salario, comissao, vendas, bonus);
									double salarioLiquido = funcionarioSelecionadoComissionado.calcularPagamento(idFuncionario);
									System.out.print("\n" + "Observações da Folha (digite 'FIM' em uma nova linha para terminar): ");
									texto.delete(0, texto.length());
									while (true) {
							            String linha = scan.nextLine(); // Lê uma linha do texto
							            if (linha.equalsIgnoreCase("FIM")) { // Verifica se a linha é "FIM"
							                break;
							            }
							            texto.append(linha).append("\n"); // Adiciona a linha ao texto, incluindo a quebra de linha
							        }
									folha = new FolhaDePagamento(funcionarioSelecionadoComissionado, hoje, primeiroDia, ultimoDia, salarioLiquido, texto.toString());
									System.out.println("\n" + "Você quer gerar o pdf da folha de pagamento? "
											+ "\n" + "  1. Sim"
											+ "\n" + "  2. Não");
									System.out.print("\n" + "Escolha: ");
									int geraPDF = scan.nextInt();
									if (geraPDF == 1) {
										String padrao = "###,###.00";
										DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
										simbolos.setDecimalSeparator(',');
								        simbolos.setGroupingSeparator('.');
										DecimalFormat formato = new DecimalFormat(padrao, simbolos);
										DateTimeFormatter formatacaoDaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
										try {
								            // Caminho onde o PDF será salvo
								            String caminhoArquivo = "FolhaPagamento_" + folha.getFuncionarioComissionado().getNome() + ".pdf";

								            // Inicializa o PDF Writer e o Document
								            PdfWriter writer = new PdfWriter(new File(caminhoArquivo));
								            PdfDocument pdfDoc = new PdfDocument(writer);
								            Document document = new Document(pdfDoc);

								            // Adiciona o conteúdo ao PDF
								            document.add(new Paragraph("--- Folha de Pagamento ---\n\n"));
								            document.add(new Paragraph("Nome do Funcionário: " + folha.getFuncionarioComissionado().getNome()));
								            document.add(new Paragraph("Data de Emissão da Folha de Pagamento: " + folha.getDataGeracao().format(formatacaoDaData)));
								            document.add(new Paragraph("Folha de Pagamento referente à: " + formatacaoDaData.format(folha.getPeriodoInicio()) + " a " + formatacaoDaData.format(folha.getPeriodoFim())));
								            document.add(new Paragraph("Salário Líquido: R$" + formato.format(folha.getValorPago())));
								            document.add(new Paragraph("\nObservações:\n" + folha.getObservacoes()));

								            // Fecha o documento
								            document.close();
								            System.out.println("\nPDF gerado com sucesso: " + caminhoArquivo);
								        } catch (Exception e) {
								            System.out.println("Erro ao gerar PDF: " + e.getMessage());
								        }
									}
									scan.nextLine();
								}
								if (folhaDePagamentoBO.inserir(folha, idFuncionario)) {
									System.out.println("\n" + "Folha gerada com sucesso com Sucesso");
								} else {
									System.out.println("\n" + "Erro ao Inserir");			
								}
							} else if (op == 7) { // opção de sair no menu do admim
								System.out.println("\n" + "---  Fim do programa  ---");
								termina = true;
							}
						} while (termina != true);
					} else if ("FUNCIONARIO".equals(usuarioLogado.getTipoString())) {
						do { //menu funcionario
							System.out.println("\n" + "---  Menu Funcionário  ---"
											 + "\n" + "1. cadastrar um banco"
											 + "\n" + "2. gerar folha de pagamento"
											 + "\n" + "3. sair");
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
							if (op == 1) { // opção de cadastrar um banco no menu do funcionario
								scan.nextLine();
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
									Banco dadoBanco = new Banco(nomeBanco, agencia, numConta, TipoConta.CORRENTE);
									if (bancoBO.inserir(dadoBanco, idUsuarioLogado)) {
										System.out.println("\n" + "Banco cadastrada com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								} else if (tipo == 2) { // cadastro conta poupança
									Banco dadoBanco = new Banco(nomeBanco, agencia, numConta, TipoConta.POUPANCA);
									if (bancoBO.inserir(dadoBanco, idUsuarioLogado)) {
										System.out.println("\n" + "Banco cadastrado com sucesso");
									} else {
										System.out.println("\n" + "Algo deu errado");
									}
								}
							} else if (op == 2)  { // opção de gerar a folha de pagamento no menu do funcionario
								scan.nextLine();
								List<FolhaDePagamento> lista = new ArrayList<FolhaDePagamento>();
								lista = folhaDePagamentoBO.pesquisarTodos(idUsuarioLogado);
								System.out.println("\n" + "---  Menu de Geração (Folha de Pagamento)  ---");
								System.out.print("\n" + "Selecione que folha de pagamento você quer gerar");
								System.out.println("\n\n" + "folhas de pagamento disponíveis:");
								for (int i = 0; i < lista.size(); i++) {
									String mesPorExtenso = lista.get(i).getPeriodoInicio().getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
									int mes = lista.get(i).getPeriodoInicio().getMonthValue();
									System.out.println((i + 1) + ". Folha de Pagamento refente ao mês " + mes + " (" + mesPorExtenso + ")");
								}
								System.out.print("Selecione o número da folha de pagamento: ");
								int escolha = scan.nextInt();
								while (escolha < 1 || escolha > lista.size()) {
									System.out.println("Opção inválida");
									System.out.print("Selecione o número da folha de pagamento: ");
									escolha = scan.nextInt();
								}
								scan.nextLine();
								FolhaDePagamento folha = lista.get(escolha - 1);
								String padrao = "###,###.00";
								DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
								simbolos.setDecimalSeparator(',');
						        simbolos.setGroupingSeparator('.');
								DecimalFormat formato = new DecimalFormat(padrao, simbolos);
								DateTimeFormatter formatacaoDaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								try {
						            // Caminho onde o PDF será salvo
						            String caminhoArquivo = "FolhaPagamento_" + folha.getFuncionarioCLT().getNome() + ".pdf";

						            // Inicializa o PDF Writer e o Document
						            PdfWriter writer = new PdfWriter(new File(caminhoArquivo));
						            PdfDocument pdfDoc = new PdfDocument(writer);
						            Document document = new Document(pdfDoc);

						            // Adiciona o conteúdo ao PDF
						            document.add(new Paragraph("--- Folha de Pagamento ---\n\n"));
						            document.add(new Paragraph("Nome do Funcionário: " + folha.getFuncionarioCLT().getNome()));
						            document.add(new Paragraph("Data de Emissão da Folha de Pagamento: " + folha.getDataGeracao().format(formatacaoDaData)));
						            document.add(new Paragraph("Folha de Pagamento referente à: " + formatacaoDaData.format(folha.getPeriodoInicio()) + " a " + formatacaoDaData.format(folha.getPeriodoFim())));
						            document.add(new Paragraph("Salário Líquido: R$" + formato.format(folha.getValorPago())));
						            document.add(new Paragraph("\nObservações:\n" + folha.getObservacoes()));

						            // Fecha o documento
						            document.close();
						            System.out.println("\nPDF gerado com sucesso: " + caminhoArquivo);
						        } catch (Exception e) {
						            System.out.println("Erro ao gerar PDF: " + e.getMessage());
						        }
							} else if (op == 3) { // opção de sair no menu do funcionario
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
						System.out.println("\n" + "Usuario cadastrado com sucesso" + "\n");
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
							int idFuncionario = funcionarioCLTBO.pegarId(idUsuario);
							// criação dos descontos obrigatorios para o clt
							BeneficioDesconto inss = new BeneficioDesconto("INSS", 0, TipoBenDes.DESCONTO);
							if (funcCLT.getSalarioMensal() <= 1412.00) {
								inss.setValor(funcCLT.getSalarioMensal() * 0.075);
							} else if (funcCLT.getSalarioMensal() >= 1412.01 && funcCLT.getSalarioMensal() <= 2666.68) {
								inss.setValor(funcCLT.getSalarioMensal() * 0.09);
							} else if (funcCLT.getSalarioMensal() >= 2666.69 && funcCLT.getSalarioMensal() <= 4000.03) {
								inss.setValor(funcCLT.getSalarioMensal() * 0.12);
							} else if (funcCLT.getSalarioMensal() >= 4000.04 && funcCLT.getSalarioMensal() <= 7768.02) {
								inss.setValor(funcCLT.getSalarioMensal() * 0.14);
							}
							
							BeneficioDesconto fgts = new BeneficioDesconto("FGTS", funcCLT.getSalarioMensal() * 0.08, TipoBenDes.DESCONTO);
							
							BeneficioDesconto irrf = new BeneficioDesconto("IRRF", 0, TipoBenDes.DESCONTO); //Imposto de Renda Retido na Fonte
							if (funcCLT.getSalarioMensal() >= 1903.99 && funcCLT.getSalarioMensal() <= 2826.65) {
								irrf.setValor(funcCLT.getSalarioMensal() * 0.075);
							} else if (funcCLT.getSalarioMensal() >= 2826.66 && funcCLT.getSalarioMensal() <= 3751.05) {
								irrf.setValor(funcCLT.getSalarioMensal() * 0.15);
							} else if (funcCLT.getSalarioMensal() >= 3751.06 && funcCLT.getSalarioMensal() <= 4664.68) {
								irrf.setValor(funcCLT.getSalarioMensal() * 0.225);
							} else if (funcCLT.getSalarioMensal() >= 4664.69) {
								irrf.setValor(funcCLT.getSalarioMensal() * 0.275);
							}
							
							if (benDesBO.inserir(inss, idFuncionario) && benDesBO.inserir(fgts, idFuncionario) && benDesBO.inserir(irrf, idFuncionario)) {
								System.out.println("\n" + "Funcionario CLT cadastrado com sucesso" + "\n");
							} else {
								System.out.println("\n" + "Algo deu errado");
							}
						}
					} else if (tipo == 2) { // cadastro Horista
						System.out.print("\n" + "Salário por Hora: ");
						double salarioHora = scan.nextDouble();
						System.out.print("\n" + "Horas Trabalhadas (no mês): ");
						double horasTrabalhadas = scan.nextDouble();
						System.out.print("\n" + "Horas Extras (caso houver): ");
						double horasExtras = scan.nextDouble();
						FuncionarioHorista funcHorista = new FuncionarioHorista(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioHora, horasTrabalhadas, horasExtras);
						if (funcionarioHoristaBO.inserir(funcHorista, idUsuario)) {
							System.out.println("\n" + "Funcionario Horista cadastrado com sucesso" + "\n");
						} else {
							System.out.println("\n" + "Algo deu errado");
						}
					} else if (tipo == 3) { // cadastro Comissionado
						System.out.print("\n" + "Salário Base: ");
						double salarioBase = scan.nextDouble();
						System.out.print("\n" + "Porcentagem da Comissão (número inteiro): ");
						double porcentagemNaoAjustada = scan.nextDouble();
						double porcentagemReal = porcentagemNaoAjustada / 100;
						System.out.print("\n" + "Valor total de vendas realizadas (no mês): ");
						double vendaRealizadas = scan.nextInt();
						System.out.print("\n" + "Bonus (caso houver): ");
						double bonus = scan.nextDouble();
						FuncionarioComissionado funcComissionado = new FuncionarioComissionado(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioBase, porcentagemReal, vendaRealizadas, bonus);
						if (funcionarioComissionadoBO.inserir(funcComissionado, idUsuario)) {
							System.out.println("\n" + "Funcionario Comissioando cadastrado com sucesso" + "\n");
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
