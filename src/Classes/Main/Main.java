package Classes.Main;
import java.time.LocalDate;
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
		int op = 0;
		
		
		while (true) {
			System.out.println("\n---  Menu do sistema  ---"
					+ "\n" + "1. Logar"
					+ "\n" + "2. Cadastrar"
					+ "\n" + "0. Sair");
			System.out.print("Escholha sua opção: ");
			op = scan.nextInt();
			while (op < 0 || op > 2) {
				System.out.println("Opção inválida");
				System.out.print("Escholha sua opção: ");
				op = scan.nextInt();
				if (op == 0) {
					break;
				}
			}
			if (op == 1) { // opção de logar no inicio do sistema
				MenuLogin(scan, folhaDePagamentoBO, usuarioBO, funcionarioCLTBO, benDesBO, funcionarioHoristaBO, funcionarioComissionadoBO, bancoBO);
			} else if (op == 2) { // opção de cadastro no inicio do sistema
				MenuCadastroUsuario(scan, usuarioBO, funcionarioCLTBO, benDesBO, funcionarioHoristaBO, funcionarioComissionadoBO);
			} else if (op == 0) { // opção de sair no inicio do sistema
				System.out.println("\n" + "---  Fim do programa  ---");
				break;
			}
		}
		
		scan.close();		
	}
	
	public static void MenuCadastroUsuario(Scanner scan, UsuarioBO usuarioBO, FuncionarioCLTBO funcionarioCLTBO, BeneficioDescontoBO benDesBO, FuncionarioHoristaBO funcionarioHoristaBO, FuncionarioComissionadoBO funcionarioComissionadoBO) {
		while (true) {
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
					System.out.println("\n" + "Usuario cadastrado com sucesso");
				} else {
					System.out.println("\n" + "Algo deu errado");
				}
			} else if (tipo == 2) { // cadastro de funcionario
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
				if (tipo == 1) { // cadastro do funcionario CLT
					System.out.print("\n" + "Salário Mensal: ");
					double salarioMensal = scan.nextDouble();
					FuncionarioCLT funcCLT = new FuncionarioCLT(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioMensal);
					if (funcionarioCLTBO.inserir(funcCLT, idUsuario)) {
						int idFuncionario = funcionarioCLTBO.pegarId(idUsuario);
						// criação dos descontos obrigatorios para o clt
						// INSS
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
						
						// FGTS
						BeneficioDesconto fgts = new BeneficioDesconto("FGTS", funcCLT.getSalarioMensal() * 0.08, TipoBenDes.DESCONTO);
						
						
						// IRRF = Imposto de Renda Retida na Fonte
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
				} else if (tipo == 2) { // cadastro do funcionario Horista
					System.out.print("\n" + "Salário por Hora: ");
					double salarioHora = scan.nextDouble();
					System.out.print("\n" + "Horas Trabalhadas (no mês): ");
					double horasTrabalhadas = scan.nextDouble();
					System.out.print("\n" + "Horas Extras (caso houver): ");
					double horasExtras = scan.nextDouble();
					FuncionarioHorista funcHorista = new FuncionarioHorista(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioHora, horasTrabalhadas, horasExtras);
					if (funcionarioHoristaBO.inserir(funcHorista, idUsuario)) {
						System.out.println("\n" + "Funcionario Horista cadastrado com sucesso");
					} else {
						System.out.println("\n" + "Algo deu errado");
					}
				} else if (tipo == 3) { // cadastro do funcionario Comissionado
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
						System.out.println("\n" + "Funcionario Comissioando cadastrado com sucesso");
					} else {
						System.out.println("\n" + "Algo deu errado");
					}
				}
			}
			return;
		}
	}
	
	public static void MenuLogin(Scanner scan, FolhaDePagamentoBO folhaDePagamentoBO, UsuarioBO usuarioBO, FuncionarioCLTBO funcionarioCLTBO, BeneficioDescontoBO benDesBO, FuncionarioHoristaBO funcionarioHoristaBO, FuncionarioComissionadoBO funcionarioComissionadoBO, BancoBO bancoBO) {
		while (true) {
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
				usuarioBO.atualizaSenha(senha, idUsuarioLogado); // cadastra a senha para o funcionario sem senha
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
			}
			if (usuarioLogado != null) {
				System.out.println("\n" + "Logado com sucesso");
				if ("ADMINISTRADOR".equals(usuarioLogado.getTipoString())) { // caso o usuario seja admim
					MenuAdmim(scan, folhaDePagamentoBO, usuarioBO, funcionarioCLTBO, benDesBO, funcionarioHoristaBO, funcionarioComissionadoBO, bancoBO);
					break;
				} else if ("FUNCIONARIO".equals(usuarioLogado.getTipoString())) { // caso seja funcionario
					int idUsuarioLogado = usuarioBO.pegarId(usuarioLogado);
					boolean temBanco = usuarioBO.temBanco(usuarioLogado, idUsuarioLogado); // verifica se o funcionario tem algum dado bancario cadastrado
					if (temBanco == true) { // caso o usuario tenha dados bancarios cadastrados
						MenuFuncionarioComBanco(scan, folhaDePagamentoBO, usuarioBO, usuarioLogado);
						break;
					} else if (temBanco == false) { // caso o usuario NÂO tenha os dados bancarios cadastrados
						MenuFuncionario(scan, folhaDePagamentoBO, usuarioBO, bancoBO, usuarioLogado);
						break;
					}
				}
			}
		}
	}
	
	public static void MenuFuncionario(Scanner scan, FolhaDePagamentoBO folhaDePagamentoBO, UsuarioBO usuarioBO, BancoBO bancoBO, Usuario usuarioLogado) {
		while (true) {
			int idUsuarioLogado = usuarioBO.pegarId(usuarioLogado);
			System.out.println("\n" + "---  Menu Funcionário  ---"
					 + "\n" + "1. cadastrar um banco"
					 + "\n" + "2. gerar folha de pagamento"
					 + "\n" + "00. voltar ao menu principal");
			System.out.print("Escholha sua opção: ");
			int op = scan.nextInt();
			scan.nextLine();
			while (op < 00 || op > 2) {
				System.out.println("Opção inválida");
				System.out.print("Escholha sua opção: ");
				op = scan.nextInt();
				if (op == 00) {
					break;
				}
			}
			switch (op) {
				case 1: // opção de cadastrar um banco para o funconario logado
					MenuCadastrarBancoPeloFuncionario(scan, bancoBO, usuarioLogado,idUsuarioLogado);
					break;
				case 2: // opção de gerar o pdf da folha de pagamento do funcionario logado
					MenuGeramentoDeFolhaDePagamentoPorFuncionario(scan, folhaDePagamentoBO, usuarioBO, idUsuarioLogado);
					break;
				case 00: // voltar ao menu principal
					return;
			}
		}
	}
	
	public static void MenuFuncionarioComBanco(Scanner scan, FolhaDePagamentoBO folhaDePagamentoBO, UsuarioBO usuarioBO, Usuario usuarioLogado) {
		while (true) {
			int idUsuarioLogado = usuarioBO.pegarId(usuarioLogado);
			System.out.println("\n" + "---  Menu Funcionário  ---"
					 + "\n" + "1. gerar folha de pagamento"
					 + "\n" + "00. voltar ao menu principal");
			System.out.print("Escholha sua opção: ");
			int op = scan.nextInt();
			scan.nextLine();
			while (op < 00 || op > 1) {
				System.out.println("Opção inválida");
				System.out.print("Escholha sua opção: ");
				op = scan.nextInt();
				if (op == 00) {
					break;
				}
			}
			switch (op) {
				case 1: // opção de gerar o pdf da folha de pagamento do funcionario logado
					MenuGeramentoDeFolhaDePagamentoPorFuncionario(scan, folhaDePagamentoBO, usuarioBO, idUsuarioLogado);
					break;
				case 00: // voltar ao menu principal
					return;
			}
		}
	}
	
	public static void MenuAdmim(Scanner scan, FolhaDePagamentoBO folhaDePagamentoBO, UsuarioBO usuarioBO, FuncionarioCLTBO funcionarioCLTBO, BeneficioDescontoBO benDesBO, FuncionarioHoristaBO funcionarioHoristaBO, FuncionarioComissionadoBO funcionarioComissionadoBO, BancoBO bancoBO) {
		while (true) { // menu do admim
			int op;
			System.out.println("\n" + "---  Menu Admim  ---"
							 + "\n" + "1. ativar/desativar um usuário"
							 + "\n" + "2. cadastrar um funcionário"
							 + "\n" + "3. cadastrar os beneficios/descontos do funcionario CLT"
							 + "\n" + "4. cadastrar um banco"
							 + "\n" + "5. gerar uma folha de pagamento"
							 + "\n" + "00. voltar");
			System.out.print("Escholha sua opção: ");
			op = scan.nextInt();
			while (op < 00 || op > 5) {
				System.out.println("Opção inválida");
				System.out.print("Escholha sua opção: ");
				op = scan.nextInt();
				if (op == 00) {
					break;
				}
			}
			switch (op) {
				case 1:
					MenuAtivarDesativarUsuarioPeloAdmim(scan, usuarioBO);
					break;
				case 2:
					MenuCadastrarFuncionarioPeloAdmim(scan, usuarioBO, funcionarioCLTBO, benDesBO, funcionarioHoristaBO, funcionarioComissionadoBO);
					break;
				case 3:
					MenuCadastroBeneficioDescontoPeloAdmim(scan, usuarioBO, funcionarioCLTBO, benDesBO);
					break;
				case 4:
					MenuCadastroBancoPeloAdmim(scan, usuarioBO, bancoBO);
					break;
				case 5:
					MenuGeracaoDeFolhaDePagamentoPeloAdmim(scan, usuarioBO, funcionarioCLTBO, funcionarioHoristaBO, funcionarioComissionadoBO, folhaDePagamentoBO);
					break;
				case 00:
					return;
			}
		}	
	}
	
	public static void MenuCadastrarBancoPeloFuncionario(Scanner scan, BancoBO bancoBO, Usuario usuarioLogado, int idUsuarioLogado) {
		while (true) {
			System.out.println("\n" + "---  Menu de Cadastro (dados bancarios) ---");
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
			Banco dadoBanco;
			switch (tipo) {
				case 1:
					dadoBanco = new Banco(nomeBanco, agencia, numConta, TipoConta.CORRENTE);
					if (bancoBO.inserir(dadoBanco, idUsuarioLogado)) {
						System.out.println("\n" + "Banco cadastrada com sucesso");
						break;
					} else {
						System.out.println("\n" + "Algo deu errado");
						break;
					}
				case 2:
					dadoBanco = new Banco(nomeBanco, agencia, numConta, TipoConta.POUPANCA);
					if (bancoBO.inserir(dadoBanco, idUsuarioLogado)) {
						System.out.println("\n" + "Banco cadastrado com sucesso");
						break;
					} else {
						System.out.println("\n" + "Algo deu errado");
						break;
					}
			}
			return;
		}
	}
	
	public static void MenuGeramentoDeFolhaDePagamentoPorFuncionario(Scanner scan, FolhaDePagamentoBO folhaDePagamentoBO, UsuarioBO usuarioBO, int idUsuarioLogado) {
		while (true) {
			scan.nextLine();
			List<FolhaDePagamento> lista = new ArrayList<FolhaDePagamento>();
			lista = folhaDePagamentoBO.pesquisarTodos(idUsuarioLogado);
			System.out.println("\n" + "---  Menu de Geração (Folha de Pagamento)  ---");
			System.out.print("\n" + "Selecione que folha de pagamento você quer gerar");
			System.out.println("\n\n" + "folhas de pagamento disponíveis:");
			for (int i = 0; i < lista.size(); i++) { // lista das folhas de pagamento do funcionario logado
				String mesPorExtenso = lista.get(i).getPeriodoInicio().getMonth().getDisplayName(TextStyle.FULL, Locale.of("pt", "BR"));
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
			int funcTipo = usuarioBO.verificaFuncTipo(idUsuarioLogado);
			if (funcTipo == 1) {
				String padrao = "###,###.00";
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.of("pt", "BR"));
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
		            break;
		        } catch (Exception e) {
		            System.out.println("Erro ao gerar PDF: " + e.getMessage());
		            break;
		        }
			} else if (funcTipo == 2) {
				String padrao = "###,###.00";
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.of("pt", "BR"));
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
		            break;
		        } catch (Exception e) {
		            System.out.println("Erro ao gerar PDF: " + e.getMessage());
		            break;
		        }
			} else if (funcTipo == 3) {
				String padrao = "###,###.00";
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.of("pt", "BR"));
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
		            break;
		        } catch (Exception e) {
		            System.out.println("Erro ao gerar PDF: " + e.getMessage());
		            break;
		        }
			}
			return;
		}
	}
	
	public static void MenuAtivarDesativarUsuarioPeloAdmim(Scanner scan, UsuarioBO usuarioBO) {
		while (true) {
			scan.nextLine();
			List<Usuario> lista = new ArrayList<Usuario>();
			lista = usuarioBO.pesquisarTodos();
			System.out.println("\n" + "---  Menu de Ativação/Desativação (usuário) ---");
			System.out.print("\n" + "Selecione qual usuário você quer ativar/desativar");
			System.out.println("\n\n" + "Usuários disponíveis:");
			for (int i = 0; i < lista.size(); i++) { // lista de usuarios do sistema
				System.out.println((i + 1) + ". " + lista.get(i).mostrarTodos());
			}
			System.out.print("Selecione o número do usuário: ");
			int escolha = scan.nextInt();
			while (escolha < 1 || escolha > lista.size()) {
				System.out.println("Opção inválida");
				System.out.print("Selecione o número do usuário: ");
				escolha = scan.nextInt();
			}
			Usuario usuarioSelecionado = lista.get(escolha - 1); // escolha - 1 para pegar o usuario certo dentro da lista
			int idUsuarioSelecionado = usuarioBO.pegarId(usuarioSelecionado);
			System.out.println("\n" + "Selecione: "
					+ "\n" + "  1. Ativar"
					+ "\n" + "  2. Desativar");
			System.out.print("\n" + "Escolha: ");
			int escolhaAtvDtv = scan.nextInt();
			scan.nextLine();
			if (escolhaAtvDtv == 1) {
				if(usuarioBO.ativar(idUsuarioSelecionado)) { // opção de Ativar usuario
					System.out.println("\n" + "Usuário ativado com sucesso");
				} else {
					System.out.println("\n" + "Algo deu errado");
				}
			} else if (escolhaAtvDtv == 2) {
				if(usuarioBO.desativar(idUsuarioSelecionado)) { // opção de Desativar usuario
					System.out.println("\n" + "Usuário desativado com sucesso");
				} else {
					System.out.println("\n" + "Algo deu errado");
				}
			}
			return;
		}
	}
	
	public static void MenuCadastrarFuncionarioPeloAdmim(Scanner scan, UsuarioBO usuarioBO, FuncionarioCLTBO funcionarioCLTBO, BeneficioDescontoBO benDesBO, FuncionarioHoristaBO funcionarioHoristaBO, FuncionarioComissionadoBO funcionarioComissionadoBO) {
		while (true) {
			scan.nextLine();
			System.out.println("\n" + "---  Menu de Cadastro (funcionario) ---");
			System.out.print("\n" + "Nome: ");
			String nome = scan.nextLine();
			System.out.print("\n" + "Email: ");
			String email = scan.nextLine();
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
				if (tipo == 1) { // cadastro do funcionario CLT pelo admim
					System.out.print("\n" + "Salário Mensal: ");
					double salarioMensal = scan.nextDouble();
					FuncionarioCLT funcCLT = new FuncionarioCLT(nome, email, "", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo, salarioMensal);
					int idTransicao = usuarioBO.pegarId(usuario);
					if (funcionarioCLTBO.inserir(funcCLT, idUsuario)) {
						int idFuncionario = funcionarioCLTBO.pegarId(idTransicao);
						// criação dos descontos obrigatorios para o clt
						// INSS
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
						
						// FGTS
						BeneficioDesconto fgts = new BeneficioDesconto("FGTS", funcCLT.getSalarioMensal() * 0.08, TipoBenDes.DESCONTO);
						
						// IRRF = Imposto de Renda Retida na Fonte
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
				} else if (tipo == 2) { // cadastro do funcionario Horista pelo admim
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
				} else if (tipo == 3) { // cadastro do funcionario Comisisonado pelo admim
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
			return;
		}
	}
	
	public static void MenuCadastroBeneficioDescontoPeloAdmim(Scanner scan, UsuarioBO usuarioBO, FuncionarioCLTBO funcionarioCLTBO, BeneficioDescontoBO benDesBO) {
		while (true) {
			scan.nextLine();
			List<Usuario> lista = new ArrayList<Usuario>();
			lista = usuarioBO.pesquisarTodosFuncionariosCLT();
			System.out.println("\n" + "---  Menu de Cadastro (Beneficio/Desconto)  ---");
			System.out.print("\n" + "Selecione para que usuário você ira cadastrar o beneficio/desconto");
			System.out.println("\n\n" + "Funcionários disponíveis:");
			for (int i = 0; i < lista.size(); i++) { // lista de funcionarios do sistema
				System.out.println((i + 1) + ". " + lista.get(i).mostrarTodosFuncionarios());
			}
			System.out.print("Selecione o número do funcionário: ");
			int escolha = scan.nextInt();
			while (escolha < 1 || escolha > lista.size()) {
				System.out.println("Opção inválida");
				System.out.print("Selecione o número do funcionário: ");
				escolha = scan.nextInt();
			}
			Usuario transicao = lista.get(escolha - 1); // escolha - 1 para pegar o usuario certo dentro da lista
			int idTransicao = usuarioBO.pegarId(transicao);
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
			return;
		}
	}
	
	public static void MenuCadastroBancoPeloAdmim(Scanner scan, UsuarioBO usuarioBO, BancoBO bancoBO) {
		while(true) {
			scan.nextLine();
			int op = 0;
			List<Usuario> lista = new ArrayList<Usuario>();
			lista = usuarioBO.pesquisarTodosFuncionariosSemBanco();
			System.out.println("\n" + "---  Menu de Cadastro (Dados Bancarios)  ---");
			System.out.print("\n" + "Selecione para que usuário você ira cadastrar os dados bancarios");
			System.out.println("\n" + "Usuários disponíveis:");
			for (int i = 0; i < lista.size(); i++) { // lista de usuarios do sistema
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
			return;
		}
	}
	
	public static void MenuGeracaoDeFolhaDePagamentoPeloAdmim(Scanner scan, UsuarioBO usuarioBO, FuncionarioCLTBO funcionarioCLTBO, FuncionarioHoristaBO funcionarioHoristaBO, FuncionarioComissionadoBO funcionarioComissionadoBO, FolhaDePagamentoBO folhaDePagamentoBO) {
		while (true) {
			scan.nextLine();
			List<Usuario> lista = new ArrayList<Usuario>();
			lista = usuarioBO.pesquisarTodosFuncionarios();
			System.out.println("\n" + "---  Menu de Geração (Folha de Pagamento)  ---");
			System.out.print("\n" + "Selecione para que funcionário você ira gerar a folha de pagamento");
			System.out.println("\n\n" + "Funcionários disponíveis:");
			for (int i = 0; i < lista.size(); i++) { // lista de funcionarios do sistema
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
			int funcTipo = usuarioBO.verificaFuncTipo(idTransicao);
			if (funcTipo == 1) { // tipo CLT
				GeracaoFolhaCLT(scan, funcionarioCLTBO, folhaDePagamentoBO, transicao, idTransicao);
			} else if (funcTipo == 2) { // tipo horista
				GeracaoFolhaHorista(scan, funcionarioHoristaBO, folhaDePagamentoBO, transicao, idTransicao);
			} else if (funcTipo == 3) { // tipo comissionado
				GeracaoFolhaComissionado(scan, funcionarioComissionadoBO, folhaDePagamentoBO, transicao, idTransicao);
			}
			return;
		}
	}
	
	public static void GeracaoFolhaCLT(Scanner scan, FuncionarioCLTBO funcionarioCLTBO, FolhaDePagamentoBO folhaDePagamentoBO, Usuario transicao, int idTransicao) {
		while (true) {
			LocalDate hoje = LocalDate.now();
			YearMonth mesAnterior = YearMonth.from(hoje.minusMonths(1));
			LocalDate primeiroDia = mesAnterior.atDay(1);
			LocalDate ultimoDia = mesAnterior.atEndOfMonth();
			StringBuilder texto = new StringBuilder();	
		    int idFuncionario = funcionarioCLTBO.pegarId(idTransicao);
			double salario = funcionarioCLTBO.pegarSalario(idFuncionario);
			FuncionarioCLT funcionarioSelecionadoCLT = new FuncionarioCLT(transicao.getNome(), transicao.getEmail(), transicao.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "", salario);
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
			FolhaDePagamento folha = new FolhaDePagamento(funcionarioSelecionadoCLT, hoje, primeiroDia, ultimoDia, salarioLiquido, texto.toString());
			System.out.println("\n" + "Você quer gerar o pdf da folha de pagamento? "
					+ "\n" + "  1. Sim"
					+ "\n" + "  2. Não");
			System.out.print("\n" + "Escolha: ");
			int geraPDF = scan.nextInt();
			if (geraPDF == 1) {
				String padrao = "###,###.00";
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.of("pt", "BR"));
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
			if (folhaDePagamentoBO.inserir(folha, idFuncionario)) {
				System.out.println("\n" + "Folha gerada com sucesso com Sucesso");
			} else {
				System.out.println("\n" + "Erro ao Inserir");			
			}
			return;
		}
	}
	
	public static void GeracaoFolhaHorista(Scanner scan, FuncionarioHoristaBO funcionarioHoristaBO, FolhaDePagamentoBO folhaDePagamentoBO, Usuario transicao, int idTransicao) {
		while (true) {
			LocalDate hoje = LocalDate.now();
			YearMonth mesAnterior = YearMonth.from(hoje.minusMonths(1));
			LocalDate primeiroDia = mesAnterior.atDay(1);
			LocalDate ultimoDia = mesAnterior.atEndOfMonth();
			StringBuilder texto = new StringBuilder();
			int idFuncionario = funcionarioHoristaBO.pegarId(idTransicao);
			double salario = funcionarioHoristaBO.pegarSalarioHora(idFuncionario);
			double horasTrabalhadas = funcionarioHoristaBO.pegarHorasTrabalhadas(idFuncionario);
			double horasExtras = funcionarioHoristaBO.pegarHorasExtras(idFuncionario);
			FuncionarioHorista funcionarioSelecionadoHorista = new FuncionarioHorista(transicao.getNome(), transicao.getEmail(), transicao.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "", salario, horasTrabalhadas, horasExtras);
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
			FolhaDePagamento folha = new FolhaDePagamento(funcionarioSelecionadoHorista, hoje, primeiroDia, ultimoDia, salarioLiquido, texto.toString());
			System.out.println("\n" + "Você quer gerar o pdf da folha de pagamento? "
					+ "\n" + "  1. Sim"
					+ "\n" + "  2. Não");
			System.out.print("\n" + "Escolha: ");
			int geraPDF = scan.nextInt();
			if (geraPDF == 1) {
				String padrao = "###,###.00";
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.of("pt", "BR"));
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
			if (folhaDePagamentoBO.inserir(folha, idFuncionario)) {
				System.out.println("\n" + "Folha gerada com sucesso com Sucesso");
			} else {
				System.out.println("\n" + "Erro ao Inserir");			
			}
			return;
		}
	}
	
	public static void GeracaoFolhaComissionado(Scanner scan, FuncionarioComissionadoBO funcionarioComissionadoBO, FolhaDePagamentoBO folhaDePagamentoBO, Usuario transicao, int idTransicao) {
		while (true) {
			LocalDate hoje = LocalDate.now();
			YearMonth mesAnterior = YearMonth.from(hoje.minusMonths(1));
			LocalDate primeiroDia = mesAnterior.atDay(1);
			LocalDate ultimoDia = mesAnterior.atEndOfMonth();
			StringBuilder texto = new StringBuilder();
			int idFuncionario = funcionarioComissionadoBO.pegarId(idTransicao);
			double salario = funcionarioComissionadoBO.pegarSalarioBase(idFuncionario);
			double comissao = funcionarioComissionadoBO.pegarComissao(idFuncionario);
			double vendas = funcionarioComissionadoBO.pegarVendas(idFuncionario);
			double bonus = funcionarioComissionadoBO.pegarBonus(idFuncionario);
			FuncionarioComissionado funcionarioSelecionadoComissionado = new FuncionarioComissionado(transicao.getNome(), transicao.getEmail(), transicao.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "", salario, comissao, vendas, bonus);
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
			FolhaDePagamento folha = new FolhaDePagamento(funcionarioSelecionadoComissionado, hoje, primeiroDia, ultimoDia, salarioLiquido, texto.toString());
			System.out.println("\n" + "Você quer gerar o pdf da folha de pagamento? "
					+ "\n" + "  1. Sim"
					+ "\n" + "  2. Não");
			System.out.print("\n" + "Escolha: ");
			int geraPDF = scan.nextInt();
			if (geraPDF == 1) {
				String padrao = "###,###.00";
				DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.of("pt", "BR"));
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
			if (folhaDePagamentoBO.inserir(folha, idFuncionario)) {
				System.out.println("\n" + "Folha gerada com sucesso com Sucesso");
			} else {
				System.out.println("\n" + "Erro ao Inserir");			
			}
			return;
		}
	}
	
}
