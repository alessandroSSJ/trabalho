/**
 * A interface do tipo peça é o coração de todas
 * as peças de um jogo de xadrez, todas as operações
 * que podem ser feitas pelo programador são definidas
 * nessa interface.
 * 
 * @version 1.0
 * @author Alessandro e Marcelo
 * @since 1.0
 */

package engine;

public interface Peca {
	
	/** Retorna a qual lado a devida peça pertençe */
	public abstract char getLado();
	
	/** Verifica se a peça pode ir para uma dada posição */
	public abstract boolean ChecaMovimentoPeca(int xFinal , int yFinal); 
	
	/** Retorna tipo de peça */
	public abstract String getTipo();
	
	/** Muda ponto na qual a peça está */
	public abstract void setPonto(int x, int y);
}
