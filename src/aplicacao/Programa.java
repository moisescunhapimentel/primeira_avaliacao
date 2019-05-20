package aplicacao;

import java.util.List;
import java.util.Scanner;

import dominio.Disciplina;

public class Programa {

	public static void main(String[] args) {

		DisciplinaCRUD disciplinaCRUD = new DisciplinaCRUD();
		disciplinaCRUD.inicializarConexao();

		Scanner entrada = new Scanner(System.in);

		for (int i = 0; i < 50; i++) {
			imprimir("");
		}

		String opcao;

		while (true) {
			imprimir("\nEscolha uma das opcoes abaixo\n");
			Menu.menu();

			opcao = entrada.nextLine();

			switch (opcao) {

			case "1":
				List<Disciplina> disciplinas = disciplinaCRUD.listarDisciplinas();
				if (disciplinas.isEmpty()) {
					imprimir("Não há disciplinas cadastradas!");
				} else {
					disciplinas.forEach(System.out::println);
				}
				break;
			case "2":
				do {
					obterPessoaId(disciplinaCRUD, entrada);
				} while (repetirAcao(entrada));
				break;

			case "3":

				List<Disciplina> disciplinas1;

				String nome;
				Integer ch;

				boolean existe = false;

				do {
					do {
						if (existe) {
							imprimir("Esta disciplina ja existe! Preencha os dados novamente!");
						}

						nome = pedirString(entrada, "o nome");
						ch = pedirInteiro(entrada, "a carga horaria");
						existe = false;
						disciplinas1 = disciplinaCRUD.listarDisciplinas();

						if (!disciplinas1.isEmpty()) {
							for (int i = 0; i < disciplinas1.size(); i++) {
								if (disciplinas1.get(i).getNome().equals(nome)) {
									existe = true;
									break;
								}
							}
						}
					} while (existe == true);

					Disciplina disciplina = new Disciplina(null, nome, ch);

					disciplinaCRUD.cadastrarDisciplina(disciplina);
				} while (repetirAcao(entrada));

				break;
			case "4":

				do {
					Disciplina disciplina = obterPessoaId(disciplinaCRUD, entrada);

					if (disciplina != null) {
						imprimir("Deseja mudar o nome? S/N");
						if (entrada.nextLine().toLowerCase().contains("s")) {
							disciplina.setNome(pedirString(entrada, "o nome"));
						}
						imprimir("Deseja mudar a carga horaria? S/N");
						if (entrada.nextLine().toLowerCase().contains("s")) {
							disciplina.setCh(pedirInteiro(entrada, "a carga horaria"));
						}

						disciplinaCRUD.cadastrarDisciplina(disciplina);
					}

				} while (repetirAcao(entrada));

				break;
			case "5":

				do {
					Disciplina disciplina = obterPessoaId(disciplinaCRUD, entrada);

					if (disciplina != null) {
						disciplinaCRUD.deletarDisciplina(disciplina);
					}

				} while (repetirAcao(entrada));

				break;
			case "0":
				imprimir("Finalizando programa!");
				disciplinaCRUD.fecharConecoesEntity();
				System.exit(0);
			default:
				System.out.println("Número inválido!");
			}

		}
	}

	public static void imprimir(String texto) {
		System.out.println("\n" + texto + "\n");
	}

	public static boolean repetirAcao(Scanner entrada) {
		System.out.println("Deseja repetir a ação? S/N");
		return entrada.nextLine().toLowerCase().contains("s");
	}

	public static String pedirString(Scanner entrada, String texto) {
		imprimir("\nDigite " + texto + ":");
		String textoEntrada;

		do {
			textoEntrada = entrada.nextLine();
		} while (textoEntrada.equals(""));

		return textoEntrada;
	}

	public static Integer pedirInteiro(Scanner entrada, String texto) {
		while (true) {
			try {
				return Integer.parseInt(pedirString(entrada, texto));
			} catch (NumberFormatException e) {
				System.out.println("Numero invalido!");
			}
		}
	}

	public static Disciplina obterPessoaId(DisciplinaCRUD disciplinaCRUD, Scanner entrada) {
		Integer id;

		while (true) {
			try {
				imprimir("Digite um número para buscar por id: ");
				id = Integer.parseInt(entrada.nextLine());
				break;
			} catch (NumberFormatException e) {
				imprimir("Número inválido!");
			}
		}

		Disciplina disciplina = disciplinaCRUD.buscarDisciplinaPorId(id);
		if (disciplina == null) {
			imprimir("Disciplina invalida");
		} else {
			imprimir(disciplina.toString());
		}

		return disciplina;
	}
}
