package modelo;

public class ArvoreAVL {

	No raiz;

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public ArvoreAVL() {
		this.raiz = null;
	}

	public ArvoreAVL(No r) {
		this.raiz = r;
	}

	public void preOrdem() {
		this.preOrdem(getRaiz());
	}

	private void preOrdem(No arv) {

		if (arv != null) {

			System.out.println(arv.getChave() + " - " + arv.getValor());
			this.preOrdem(arv.getEsq());
			this.preOrdem(arv.getDir());

		}

	}

	public void ordem() {
		this.ordem(this.getRaiz());
	}

	private void ordem(No arv) {

		if (arv != null) {

			this.ordem(arv.getEsq());
			System.out.println(arv.getChave() + " - " + arv.getValor());
			this.ordem(arv.getDir());

		}

	}

	public No buscar(int chave) {
		return this.buscar(getRaiz(), chave);
	}

	private No buscar(No arv, int chave) {

		if (arv == null)
			return null;

		else if (arv.getChave() > chave)

			return this.buscar(arv.getEsq(), chave);

		else if (arv.getChave() < chave)

			return this.buscar(arv.getDir(), chave);

		else
			return arv;

	}

	public int maior(int a, int b) {

		return (a > b) ? a : b;

	}

	public int altura(No arv) {

		if (arv == null)

			return -1;

		return arv.alturaNo;
	}

	public int obterFB(No arv) {

		if (arv == null)

			return 0;

		return this.altura(arv.getEsq()) - this.altura(arv.getDir());
	}

	public void inserir(int chave, String valor) {

		this.raiz = this.inserir(getRaiz(), chave, valor);

	}

	private No inserir(No arv, int chave, String valor) {

		if (arv == null)

			return new No(chave, valor);

		else if (chave < arv.getChave()) {

			arv.esq = this.inserir(arv.getEsq(), chave, valor);
			// arv.setEsq(this.inserir(arv.getEsq(), chave, valor));

		} else if (chave > arv.getChave()) {

			arv.dir = this.inserir(arv.getDir(), chave, valor);
			// arv.setDir(this.inserir(arv.getDir(), chave, valor));

		} else

			return arv;

		arv = verificarBalanceamento(arv);
		
		return arv;
	}

	public void remover(int chave) {
		raiz = remover(this.getRaiz(), chave);
	}

	private No remover(No arv, int chave) {

		/* Executar remoção em ABB */

		if (arv == null)
			return arv;

		if (chave < arv.chave)

			arv.esq = remover(arv.esq, chave);

		else if (chave > arv.chave)

			arv.dir = remover(arv.dir, chave);

		/* chave ch == no.chave: nó a ser removido */
		else {

			/* achou o nó a remover */

			/* caso 1: nó sem filhos */
			if (arv.esq == null && arv.dir == null) {
				arv = null;
			}
			/* caso 2: nó só tem filho à direita */
			else if (arv.esq == null) {
				No temp = arv;
				arv = temp.dir;
				temp = null;
			}
			/* caso 2: só tem filho à esquerda */
			else if (arv.dir == null) {
				No temp = arv;
				arv = temp.esq;
				temp = null;
			}

			else {

				/*
				 * Nó com 2 filhos: pegue o sucessor do percurso em ordem Menor chave a esquerda
				 */

				No temp = noMenorChave(arv.dir);

				arv.chave = temp.chave;
				arv.valor = temp.valor;

				temp.chave = chave;

				arv.dir = remover(arv.dir, temp.chave);

			}

		}
		
		if (arv == null)

			return arv;
		
		arv = verificarBalanceamento(arv);
		
		return arv;
	}

	private No verificarBalanceamento(No arv) {
		
		
		/* 2. Atualiza altura do ancestral (pai) do nó inserido */
		arv.alturaNo = 1 + maior(altura(arv.esq), altura(arv.dir));

		/*
		 * 3. Obtenha o fator de balanceamento deste nó ancestral para verificar se ele
		 * se tornou desbalanceado
		 */
		int fb = obterFB(arv);
		int fbSubArvEsq = obterFB(arv.esq);
		int fbSubArvDir = obterFB(arv.dir);

		/*
		 * Se ele se tornar desbalanceado, então são 4 casos a serem analisados
		 */

		// Rotação direita simples
		if (fb > 1 && fbSubArvEsq >= 0)
			arv = rds(arv);

		// Rotação esquerda simples
		if (fb < -1 && fbSubArvDir <= 0)
			arv = res(arv);

		// Rotação dupla direita
		if (fb > 1 && fbSubArvEsq < 0) {
			arv.esq = res(arv.esq);
			arv = rds(arv);
		}

		// Rotação dupla esquerda
		if (fb < -1 && fbSubArvDir > 0) {
			arv.dir = rds(arv.dir);
			arv = res(arv);
		}
		
		return arv;
	}

	
	private No noMenorChave(No no) {

		No temp = no;

		while (temp.esq != null)

			temp = temp.esq;

		return temp;

	}

	private No res(No x) {

		No y = x.dir;
		No z = y.esq;

		// executa rotação

		y.esq = x;
		x.dir = z;

		x.alturaNo = 1 + this.maior(this.altura(x.getEsq()), this.altura(x.getDir()));

		y.alturaNo = 1 + this.maior(this.altura(y.getEsq()), this.altura(y.getDir()));

		// nova raiz
		return y;
	}

	public No rds(No y) {

		No x = y.getEsq();
		No z = x.getDir();

		// executa rotação

		x.dir = y;
		y.esq = z;

		y.alturaNo = 1 + this.maior(this.altura(y.getEsq()), this.altura(y.getDir()));

		x.alturaNo = 1 + this.maior(this.altura(x.getEsq()), this.altura(x.getDir()));

		return x;
	}

	public void imprime() {

		System.out.println(raiz.chave + "(" + raiz.alturaNo + ")");
		String tab = "  ";
		imprime(raiz.esq, tab, true);
		imprime(raiz.dir, tab, false);
		// System.out.println();
	}

	private void imprime(No arv, String tab, boolean isEsq) {

		if (arv == null)
			return;

		if (isEsq) {
			System.out.print(tab + "E:");
			tab += "|  ";

		} else {
			System.out.print(tab + "D:");
			tab += "   ";
		}

		System.out.println(arv.chave + "(" + arv.alturaNo + ")");
		imprime(arv.esq, tab, true);
		imprime(arv.dir, tab, false);
	}

}
