package controller;

import model.Contato;
import model.DAO.ContatoDAO;
import util.Teclado;

public class ControleDeContatos {

	
		private static final int CADASTRAR_MENSAGEM = 1;
		 private static final int VISUALIZAR_MENSAGENS = 2;
		 private static final int APAGAR_MENSAGEM = 3;
		 private static final int ATUALIZAR_MENSAGEM = 4;
		 private static final int SAIR = 5;
		 
		 public static void main(String[] args) {
				ControleDeContatos cdc = new ControleDeContatos();
		 int opcao = SAIR;
		 do {
		 cdc.mostrarMenu();
		 opcao = Teclado.lerInt("Digite sua op��o: ");
		 switch (opcao) {
		 case CADASTRAR_MENSAGEM:
		 cdc.cadastrarMensagem();
		 break;
		 case VISUALIZAR_MENSAGENS:
			 cdc.buscarMensagens();
			 break;

		 
		 
		 default:
		 System.out.println("Op��o inv�lida!");
		 }
		 Teclado.lerTexto("Pressione uma tecla para continuar...");
		 } while (opcao != SAIR);
		}
		
		 public void mostrarMenu() {
			 System.out.println("====================================");
			 System.out.println(" Cadastro de Mensagens "); 
			 System.out.println("====================================");
			 System.out.println("1 - Cadastrar Mensagem");
			 System.out.println("2 - Mostrar Mensagens");
			 System.out.println("3 - Apagar Mensagem");
			 System.out.println("4 - Atualizar Mensagem");
			 System.out.println("5 - Sair");
			 }
	
		 public void cadastrarMensagem() {
			 System.out.println("====================================");
			 System.out.println(" Cadastro de Mensagens "); 
			 System.out.println("====================================");
			 String nome = Teclado.lerTexto("Nome: ");
			 String email = Teclado.lerTexto("E-mail: ");
			 String mensagem = Teclado.lerTexto("Mensagem: ");
			 
			 Contato contato = ContatoDAO.inserir(nome, email, mensagem);
			 
			 System.out.println(contato);
			}

		 public void buscarMensagens(){
			 Contato[] contatos = ContatoDAO.buscarTodos();
			 
			 for (int i=0; i<contatos.length; i++){
			 if (contatos[i] !=null){
			 System.out.println(
			 contatos[i].getId() + "-----" +
			 contatos[i].getNome() + "-----" +
			 contatos[i].getEmail() + "-----" +
			 contatos[i].getMensagem());
			 }
			 }
			}








}


