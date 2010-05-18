/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package connectFour;

import java.util.Observer;

/**
 *
 * @author Ross
 */
public interface ControllerInterface {

    /**
     * Giocatori
     */
    public static final Integer yellow=1;
    public static final Integer red=-1;

    /**
     * tipi dei giocatori
     */
    public static final String human="Human";
    public static final String alphaBeta="AlphaBeta";
    public static final String miniMax="MiniMax";

    /**
     * esiti della partita
     */
    public static final Integer win=1;
    public static final Integer draw=0;
    public static final Integer notFinished=2;

    /**
     * Inizializza i giocatori, invocato dalla GUI quando sono disponibili tutti
     * i dati per iniziare a giocare
     * Inoltre resetta lo stato del gioco ad ogni sua invocazione.
     * @param yellowPlayer tipo del giocatore giallo
     * @param yellowLevel livello del giocatore giallo se non è umano
     * @param redPlayer tipo del giocatore rosso
     * @param redLevel livello del giocatore rosso se non è umano
     * @param o observer sulle statistiche
     * @return true se la partita può iniziare
     */

    public Boolean initGame(String yellowPlayer, Integer yellowLevel, String redPlayer, Integer redLevel, Observer stats);

    /**
     * Invia la mossa effettuata dall'essere umano
     * @param move mossa effettuata
     * @param player il giocatore che ha effettuato la mossa
     * @return lo stato nuovo
     */
    public GameState sendHumanMove(Integer move, Integer player);

    /**
     * Attiva il calcolo della prossima mossa che deve effettuare l' AI
     * @param player il giocatore che ha effettuato la mossa
     * @return lo stato nuovo
     */
    public GameState calculateAIMove(Integer player);

       
}
