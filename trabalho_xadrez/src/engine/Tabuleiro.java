/**
 * O objeto tabuleiro é responsável por armazenar e processar toda a informação
 * do jogo referente à localização das peças. Ou seja, ele será responsável por
 * identificar xeques-mates, xeques, jogadas inválidas e etc. Ou seja, em suma, 
 * este objeto será um tabuleiro inteligente.
 * 
 * @version 1.0
 * @author Alessandro e Marcelo
 * @since 1.0
 */

package engine;

public class Tabuleiro {
	
	/** Dimensões de um tabuleiro de xadrez*/
	
	private static final int LINHAS = 8 ; 
	private static final int COLUNAS = 8;
	
	/** Matriz de peças que irá gerar o jogo, caso alguma posição da matriz seja
	 * null, significa que não há peças naquela posição. O tamanho da matriz
	 * é o tamanho da dimensão do tabuleiro.
	 */
	
	private Peca pecas[][] ;
	
	public Tabuleiro()
	{
		int i;
		pecas = new Peca[LINHAS][COLUNAS] ;
		
		/* Alocação dos peões */
		for ( i = 0 ; i < COLUNAS ; i++ )
		{
			pecas[1][i] = new Peao('b' , 1 , i);
			pecas[(LINHAS-1)-1][i] = new Peao('p' , (LINHAS-1)-1 , i) ;
		}
		
		/* Alocação das torres */
		pecas[0][0] = new Torre('b' , 0 , 0);
		pecas[0][COLUNAS-1] = new Torre('b' , 0 , COLUNAS-1); 
		pecas[LINHAS-1][0] = new Torre('p' , LINHAS-1 , 0);
		pecas[LINHAS-1][COLUNAS-1] = new Torre('p' , LINHAS-1 , COLUNAS-1);
		
		/* Alocação dos cavalos */
		pecas[0][1] = new Cavalo('b' , 0 , 1);
		pecas[0][COLUNAS-2] = new Cavalo('b' , 0 , COLUNAS-2); 
		pecas[LINHAS-1][1] = new Cavalo('p' , LINHAS-1 , 1);
		pecas[LINHAS-1][COLUNAS-2] = new Cavalo('p' , LINHAS-1 , COLUNAS-2);
		
		/* Alocação dos bispos */
		pecas[0][2] = new Bispo('b' , 0 , 2);
		pecas[0][COLUNAS-3] = new Bispo('b' , 0 , COLUNAS-3); 
		pecas[LINHAS-1][2] = new Bispo('p' , LINHAS-1 , 2);
		pecas[LINHAS-1][COLUNAS-3] = new Bispo('p' , LINHAS-1, COLUNAS-3);
		
		/* Alocação das damas */
		pecas[0][3] = new Dama('b' , 0 , 3);
		pecas[LINHAS-1][3] = new Dama('p' , LINHAS-1 , 3); 
		
		/* Alocação dos reis */
		pecas[0][4] = new Rei('b' , 0 , 4 );
		pecas[LINHAS-1][4] = new Rei('p' , LINHAS-1 , 4); 
		
	}
	
	/** Retorna uma peça do tabuleiro */
	public Peca getPeca(int linha, int coluna)
	{
		return pecas[linha][coluna] ;
	}
	
	/** Muda a posição de duas peças */
	public void ChangePeca(int linhaO, int colunaO, int linhaD, int colunaD)
	{
		Peca temp = pecas[linhaD][colunaD];
		Peca orig = pecas[linhaO][colunaO];
		Peca dest = pecas[linhaD][colunaD];
		
		orig.setPonto(colunaD,linhaD);
		
		if( dest != null )
			dest.setPonto(colunaO,linhaO);
		
		pecas[linhaD][colunaD] = pecas[linhaO][colunaO];
		pecas[linhaO][colunaO] = temp;
	}
	
	/** Retira uma peca do tabuleiro*/
	public void ComePeca(int linha, int coluna)
	{
		pecas[linha][coluna] = null;
	}
	
	/** Retorna o número de linhas do tabuleiro */
	public static int getLinhas()
	{
		return Tabuleiro.LINHAS;
	}
	
	/** Retorna o número de colunas do tabuleiro */
	public static int getColunas()
	{
		return Tabuleiro.COLUNAS;
	}
	
	
}
