package threadsxadrez;

import java.io.*;

import javax.sound.sampled.*;

import auxiliar.*;
import engine.*;
import gui.*;

public class TabThread extends Thread{
	
	public static Tabuleiro tab = null;
	
	/** Arquivos de som*/
	private static File mov = new File("Sons/mov.wav");
	private static Clip clipMov;
	
	public TabThread()
	{
		super("Thread do tabuleiro");
		tab = new Tabuleiro();
		
		AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;

	    try
	    {
	    	/** Som da movimentação das peças */
		    stream = AudioSystem.getAudioInputStream(mov);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clipMov = (Clip) AudioSystem.getLine(info);
		    clipMov.open(stream);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	/** Faz uma jogada no tabuleiro */
	public void Rodada()
	{
		
		/* Uma rodada normal */
		
		if ( !iTabuleiro.getJogadaValida() )
			return;
		
		Ponto ptOrig = iTabuleiro.getOrig();
		Ponto ptDest = iTabuleiro.getDest();
			
		Peca pecaOrigem = tab.getPeca( ptOrig.getY() , ptOrig.getX() ) ;
		Peca pecaDestino = tab.getPeca( ptDest.getY() , ptDest.getX() );
		
		/* Falta tratar melhor utilizando o try catch (FICARÁ PARA DEPOIS DO MARCO 1) */
		
		if ( pecaOrigem != null )
		{	
			if ( pecaOrigem.ChecaMovimentoPeca(ptDest.getX(), ptDest.getY() ) == true)
			{
				if( pecaDestino == null )
				{
					tab.ChangePeca(ptOrig.getY() , ptOrig.getX() , ptDest.getY() , ptDest.getX() ) ;
					clipMov.loop(1);
				}
				else if ( pecaOrigem.getLado() != pecaDestino.getLado() )
				{
					tab.ComePeca(ptDest.getY(), ptDest.getX());
					tab.ChangePeca(ptOrig.getY() , ptOrig.getX() , ptDest.getY() , ptDest.getX() ) ;
					clipMov.loop(1);
				}
			}
			else
				System.out.printf("Jogada não pode ser realizada\n") ;
		}
		else
			System.out.printf("Peca origem null\n");
		
		iTabuleiro.setJogadaValida(false);
		iTabuleiro.ZerarRodada();	
		
		/* *********************************************** */
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
