package threadsxadrez;

import auxiliar.*;
import engine.*;
import gui.*;

public class TabThread extends Thread{
	
	public static Tabuleiro tab = null;
	
	public TabThread()
	{
		super("Thread do tabuleiro");
		tab = new Tabuleiro();
	}
	
	/** Faz uma jogada no tabuleiro */
	public void Rodada()
	{
		
		if ( !iTabuleiro.getJogadaValida() )
			return;
		
		Ponto ptOrig = iTabuleiro.getOrig();
		Ponto ptDest = iTabuleiro.getDest();
			
		Peca pecaOrigem = tab.getPeca( ptOrig.getY() , ptOrig.getX() ) ;
		Peca pecaDestino = tab.getPeca( ptDest.getY() , ptDest.getX() );
		
		if ( pecaOrigem != null )
		{	
			if ( pecaOrigem.ChecaMovimentoPeca(ptDest.getX(), ptDest.getY() ) == true)
			{
				if( pecaDestino == null )
					tab.ChangePeca(ptOrig.getY() , ptOrig.getX() , ptDest.getY() , ptDest.getX() ) ;
				else
					System.out.printf("Peca Destino nao null\n");
			}
			else
				System.out.printf("Jogada não pode ser realizada\n") ;
		}
		else
			System.out.printf("Peca origem null\n");
		
		iTabuleiro.setJogadaValida(false);
		
		iTabuleiro.ZerarRodada();	
	}
	
	/** get Tabuleiro */
	public static Tabuleiro getTabuleiro()
	{
		return TabThread.tab;
	}
	
	/** Metodo run*/
	@Override public void run()
	{
		while(true)
		{
			Rodada();
		}
	}
	
}
