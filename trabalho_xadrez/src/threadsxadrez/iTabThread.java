package threadsxadrez;

import gui.*;

public class iTabThread extends Thread {
	
	iTabuleiro iTab = null;
	
	public iTabThread()
	{
		super("Thread da interface do tabuleiro");
		iTab = new iTabuleiro();
		iTab.DrawPecas(TabThread.getTabuleiro());
		iTab.DrawBackground();
	}
	
	/** Metodo run*/
	
	@Override
	public void run()
	{
		while(true)
		{	
			iTab.repaint();
	    }
	}
}
