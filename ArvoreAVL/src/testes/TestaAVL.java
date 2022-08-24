package testes;

import modelo.ArvoreAVL;

public class TestaAVL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArvoreAVL avl = new ArvoreAVL();
		
		avl.inserir(11, "Lápis");
		avl.inserir(12, "Borracha");
		avl.inserir(13, "Caderno");
		avl.inserir(14, "Apontador");
		
		
		avl.inserir(15, "Corretivo");
		
		avl.inserir(17, "Caneta");
		avl.inserir(7, "Copo");
		avl.inserir(10, "Chilito");
		avl.inserir(16, "Cocada");
		
		
		avl.preOrdem();
		System.out.println();
		avl.imprime();
		
		avl.remover(10);
		
		avl.imprime();
		
	}

}
