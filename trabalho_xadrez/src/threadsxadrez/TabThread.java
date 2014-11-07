/**
 * Classe responsável por implementar a thread que irá lidar
 * com a parte funcional do jogo, isto é, organizará o mesmo
 * em rodadas, atribuirá vitórias, derrotas e etc.
 * 
 * @version 1.0
 * @author Alessandro e Marcelo
 * @since 1.0
 */

package threadsxadrez;

import java.io.*;

import javax.sound.sampled.*;

import auxiliar.*;
import engine.*;
import gui.*;
import Exceptions.*;

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
	    	/** Som da movimentacao das peças */
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
		
		/* Falta tratar melhor utilizando o try catch (FICARAO PARA DEPOIS DO MARCO 1) */
		try{
			if(pecaOrigem==null)
				throw new OrigemSemPeca();
			else if(pecaOrigem.ChecaMovimentoPeca(ptDest.getX(), ptDest.getY() ) == false)
				throw new MovimentoInvalido();
			else if( pecaDestino == null )
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
		catch(OrigemSemPeca osp)
		{
			System.out.printf("Peca origem null\n");
		}
		catch(MovimentoInvalido mi)
		{
			System.out.printf("Jogada n�o pode ser realizada\n") ;
		}
		
		/*
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
				System.out.printf("Jogada nao pode ser realizada\n") ;
		}
		else
			System.out.printf("Peca origem null\n");
		*/
		
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
