package diceGamePozoHector.exception;

public class PlayerAlreadyExistsException extends RuntimeException{
    public PlayerAlreadyExistsException(String msg){
        super(msg);
    }
}
