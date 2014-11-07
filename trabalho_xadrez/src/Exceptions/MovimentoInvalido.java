package Exceptions;

public class MovimentoInvalido extends Exception{
	public MovimentoInvalido() {}
	public MovimentoInvalido(String mensagem) 
	{
		super(mensagem);
	}
}
