/**
 * O objeto iTabuleiro (interface de um tabuliro) é responsável pela
 * parte gráfico do tabuleiro do xadrez.
 * 
 * @version 1.0
 * @author Alessandro e Marcelo
 * @since 1.0
 */

package gui;

import java.awt.* ;
import java.awt.event.*;

import javax.swing.*;

import engine.*;

import auxiliar.*;

public class iTabuleiro extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	/** Dimensões reais de um tabuleiro de xadrez */
	private static final int HEIGHT =  8 * iFundo.getAltura()  ;
	private static final int WIDTH =   8 * iFundo.getLargura() ;
	
	/** Pontos clicados pelo mouse para movimentar peças */
	private static Ponto ptOrig = null ;
	private static Ponto ptDest = null ;
	
	/** Variável para determinar se ja é o momento para realizar a jogada*/
	private static boolean jogadaValida = false;
	
	public iTabuleiro()
	{
		super("Jogo de Xadrez, by Marcelo e Alessandro") ;
		
		Toolkit tk=Toolkit.getDefaultToolkit();
		
		Dimension screenSize=tk.getScreenSize();
		
		int x = screenSize.width/2 - WIDTH/2   ;
		int y = screenSize.height/2 - HEIGHT/2 ;
		
		setBounds(x,y,WIDTH,HEIGHT); /*Posiciona o tabuleiro no meio da tela do monitor*/
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false);
		
		addMouseListener( (MouseListener) this);
	}
	
	/** Desenha o LayeredPane do fundo */
	
	public void DrawBackground()
	{
		iFundo f = new iFundo() ;
		this.setVisible(true) ;
		this.add(f) ;
	}
	
	/** Desenha o LayeredPane das peças */
	
	public void DrawPecas(Tabuleiro tab)
	{
		iPeca p = new iPeca(tab) ;
		this.setVisible(true);
		this.add(p) ;
	}
	
	/** Retorna a largura do tabuleiro */
	public static int getLargura()
	{
		return iTabuleiro.WIDTH;
	}
	
	/** Retorna a altura do tabuleiro */
	public static int getAltura()
	{
		return iTabuleiro.HEIGHT;
	}
	
	/** Retorna a peça a ser movimentada */
	public static Ponto getOrig()
	{
		return iTabuleiro.ptOrig;
	}
	
	/** Retorna ponto destino */
	public static Ponto getDest()
	{
		return iTabuleiro.ptDest;
	}
	
	/** Retorna se jogada é válida*/
	public static boolean getJogadaValida()
	{
		return iTabuleiro.jogadaValida;
	}
	
	/** Atribui valor a jogada */
	public static void setJogadaValida(boolean var)
	{
		iTabuleiro.jogadaValida = var;
	}
	
	/** Zerar os pontos */
	public static void ZerarRodada()
	{
		iTabuleiro.ptOrig = null;
		iTabuleiro.ptDest = null;
	}
	
	/** Implementa os tratadores de mouse input */
	
	@Override
    public void mouseClicked(MouseEvent e)
	{
		int xi = e.getX() / iFundo.getLargura() ;
		int yi = (8*iFundo.getAltura() - e.getY() ) / iFundo.getAltura()  ;
		
		if( ptOrig == null && ptDest == null )
		{
			ptOrig = new Ponto(xi , yi ) ;
		}
		else if( ptOrig != null && ptDest == null)
		{
		    ptDest = new Ponto(xi , yi ) ;
		    setJogadaValida(true);
		}
	}
	
	@Override
    public void mousePressed(MouseEvent e) 
	{
		
	}
	
    @Override
    public void mouseEntered(MouseEvent e) 
    {
    	
    }
    
    @Override
    public void mouseExited(MouseEvent e)
    {
    	
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
    	
    }
	
}
