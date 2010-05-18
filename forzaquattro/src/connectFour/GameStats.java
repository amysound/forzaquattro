/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package connectFour;

import java.util.Observable;

/**
 *
 * @author Ross
 */
public class GameStats extends Observable {
    //nodi esaminati nell'ultima mossa x ogni giocatore
    //nodi totali x giocatore
    //tempo di calcolo ultima mossa x ogni giocatore
    //tempo totale di calcolo
    private Integer yellowTotalExaminatedNodeNumber = 0;
    private Integer redTotalExaminatedNodeNumber = 0;
    private Integer yellowLastExaminatedNodeNumber = 0;
    private Integer redLastExaminatedNodeNumber = 0;
    private Integer yellowTotalTime = 0;
    private Integer redTotalTime = 0;
    private Integer yellowLastTime = 0;
    private Integer redLastTime = 0;

    /**aggiunte da Ross con relativi setter e getter
     * questo attributo serve a tener traccia della fine della partita
     * se la partita è finita devono essere stampate le statistiche totale
     * altrimenti no, ogni volta che viene settato un endGame dal controller
     * viene anche invocata la notifica agli observer perchè si presuppone
     * che l'endGame viene settato alla fine di ogni mossa di ogni giocatore.
     */

    private Boolean endGame=false;

    /**
     * @return the yellowTotalExaminatedNodeNumber
     */
    public Integer getYellowTotalExaminatedNodeNumber() {
        return yellowTotalExaminatedNodeNumber;
    }

    /**
     * @return the redTotalExaminatedNodeNumber
     */
    public Integer getRedTotalExaminatedNodeNumber() {
        return redTotalExaminatedNodeNumber;
    }

    /**
     * @return the yellowLastExaminatedNodeNumber
     */
    public Integer getYellowLastExaminatedNodeNumber() {
        return yellowLastExaminatedNodeNumber;
    }

    /**
     * @return the redLastExaminatedNodeNumber
     */
    public Integer getRedLastExaminatedNodeNumber() {
        return redLastExaminatedNodeNumber;
    }

    /**
     * @return the yellowTotalTime
     */
    public Integer getYellowTotalTime() {
        return yellowTotalTime;
    }

    /**
     * @return the redTotalTime
     */
    public Integer getRedTotalTime() {
        return redTotalTime;
    }

    /**
     * @return the yellowLastTime
     */
    public Integer getYellowLastTime() {
        return yellowLastTime;
    }

    /**
     * @return the redLastTime
     */
    public Integer getRedLastTime() {
        return redLastTime;
    }


    /**
     * @param yellowLastExaminatedNodeNumber the yellowLastExaminatedNodeNumber to set
     * inoltre aggiorna anche yellowTotalExaminatedNodeNumber
     */
    public void setYellowLastExaminatedNodeNumber(Integer yellowLastExaminatedNodeNumber) {
        setYellowTotalExaminatedNodeNumber(getYellowTotalExaminatedNodeNumber()+yellowLastExaminatedNodeNumber);
        this.yellowLastExaminatedNodeNumber = yellowLastExaminatedNodeNumber;
        this.setChanged();
    }

    /**
     * @param redLastExaminatedNodeNumber the redLastExaminatedNodeNumber to set
     * inoltre aggiorna anche redTotalExaminatedNodeNumber
     */
    public void setRedLastExaminatedNodeNumber(Integer redLastExaminatedNodeNumber) {
        setRedTotalExaminatedNodeNumber(getRedTotalExaminatedNodeNumber()+redLastExaminatedNodeNumber);
        this.redLastExaminatedNodeNumber = redLastExaminatedNodeNumber;
        this.setChanged();
    }

    /**
     * @param yellowLastTime the yellowLastTime to set
     * inoltre aggiorna anche yellowTotalTime
     */
    public void setYellowLastTime(Integer yellowLastTime) {
        setYellowTotalTime(getYellowTotalTime()+yellowLastTime);
        this.yellowLastTime = yellowLastTime;
        this.setChanged();
    }

    /**
     * @param redLastTime the redLastTime to set
     * inoltre aggiorna anche redTotalTime
     */
    public void setRedLastTime(Integer redLastTime) {
        setRedTotalTime(getRedTotalTime()+redLastTime);
        this.redLastTime = redLastTime;
        this.setChanged();
    }

    /**
     * @param yellowTotalExaminatedNodeNumber the yellowTotalExaminatedNodeNumber to set
     */
    private void setYellowTotalExaminatedNodeNumber(Integer yellowTotalExaminatedNodeNumber) {
        this.yellowTotalExaminatedNodeNumber = yellowTotalExaminatedNodeNumber;
        this.setChanged();
    }

    /**
     * @param redTotalExaminatedNodeNumber the redTotalExaminatedNodeNumber to set
     */
    private void setRedTotalExaminatedNodeNumber(Integer redTotalExaminatedNodeNumber) {
        this.redTotalExaminatedNodeNumber = redTotalExaminatedNodeNumber;
        this.setChanged();
    }

    /**
     * @param yellowTotalTime the yellowTotalTime to set
     */
    private void setYellowTotalTime(Integer yellowTotalTime) {
        this.yellowTotalTime = yellowTotalTime;
        this.setChanged();
    }

    /**
     * @param redTotalTime the redTotalTime to set
     */
    private void setRedTotalTime(Integer redTotalTime) {
        this.redTotalTime = redTotalTime;
        this.setChanged();
    }

    /**
     * @param endGame the endGame to set
     */

    //Ross: MODIFICABILE PER EVITARE CHE VENGA CHIAMATO IL NOTIFY ANCHE SE
    //IL GIOCATORE E' UMANO.
    public void setEndGame(Boolean endGame) {
        this.endGame = endGame;
        this.setChanged();
        notifyObservers(this);
    }

    /**
     * @return the endGame
     */
    public Boolean getEndGame() {
        return endGame;
    }

}