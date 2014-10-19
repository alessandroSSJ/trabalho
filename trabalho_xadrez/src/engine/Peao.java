/**
 * Um objeto do tipo peao, representa o peao
 * de um jogo de xadrez
 * 
 * @version 1.0
 * @author Alessandro e Marcelo
 * @since 1.0
 */

package engine;

import auxiliar.Ponto;

public class Peao implements Peca {
	
	/** Representa qual lado esta peça é B de branco e P de preto */
	private char lado;
	
	/** Representa qual é a peça*/
	private static final String tipo = "peao";
	
	/** Ponto do espaço tabuleiro (quadrado) no qual a peça se encontra */
	private Ponto pt0;
	
	public Peao( char lado , int y, int x)
	{
		this.lado = lado ;
		
		pt0 = new Ponto(x,y) ;
	}
	
	public boolean ChecaMovimentoPeca(int xFinal , int yFinal)
	{
		int distX = pt0.getX() - xFinal ;
		int distY = pt0.getY() - yFinal ;
		
		if ( distX != 0 )
			return false;
		
		if ( lado == 'b' )
		{
			if ( distY == -1 )
				return true;
			if ( pt0.getY() == 1 && distY == -2 )
				return true;
		}
		else if ( lado == 'p' )
		{
			if ( distY == 1 )
				return true;
			if ( pt0.getY() == 6 && distY == 2 )
				return true;
		}
		
		return false;
	}
	
	public void setPonto(int x, int y)
	{
		pt0.setX(x);
		pt0.setY(y);
	}
	
	public char getLado()
	{
		return lado;
	}

	public String getTipo()
	{
		return tipo ;
	}

}
