package aplicacao;

public class Menu {

	public static void menu() {
		final String textos[] = { "Listar Disciplinas cadastradas", "Buscar uma Disciplina pelo id", "Cadastrar Disciplina",
				"Atualizar Disciplina", "Remover uma Disciplina", "Sair" };

		for (int i = 1; i < textos.length; i++) {
			imprimir(i + " - " + textos[i - 1]);
		}

		imprimir(0 + " - " + textos[textos.length - 1] + "\n");
	}

	public static void imprimir(String texto) {
		System.out.println(texto);
	}

	public static void main(String[] args) {
		menu();
	}

}
