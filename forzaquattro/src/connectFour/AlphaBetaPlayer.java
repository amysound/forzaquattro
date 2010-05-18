package connectFour;
/**
 * AlphaBetaPlayer.java
 *
 * File generated from the ConnectFour::AlphaBetaPlayer uml Class
 * Generated by the Acceleo UML 2.1 to Java generator module (Obeo)
 * $ Date : 23/04/10 15.19.33 (23 aprile 2010) $
 */

/** COSE DA FARE (ANCHE IN MINIMAXPLAYER):
 * 1 - CAMBIARE IL NOME DELLA VARIABILE E DEL METODO euristic();
 * 2 - aggiungere costante letterale x -infinito e + infinito (in alternativa un metodo)
 * 3 - cancellare le versioni senza orizzonte (nextmove, maxvalue e minvalue)
 * 4 - togliere le stampe di controllo
 * 5 - convertire il metodo stampa di gamestate in toString
 * 6 - aggiustare la javadoc (rivederla anche in minimaxplayer)
 * 7 - aggiustare (in minimaxplayer) in minvalue e maxvalue l'assegnazione del minValue
 *     e del maxValue eliminando la chiamata (inutile) a Math.max
 */

/**
 * Description of the class AlphaBetaPlayer.
 *
 */

public class AlphaBetaPlayer implements AIPlayerInterface {
		
    
    private final Integer maxUtilityValue = 100;
    private Integer playerId;
     //CAMBIARE IL NOME DELLA VARIABILE E DEL METODO euristic();
    private Heuristic heuristic = new Heuristic();
    private Integer horizon = 0;
    private Integer examinatedNodeNumber = 0;

    /**
     * Constructor.
     */
    public AlphaBetaPlayer(Integer playerId) {
        setPlayerId(playerId);
    }

    public AlphaBetaPlayer(Integer playerId, Integer horizon) {
        this(playerId);
        if(horizon>0)setHorizon(horizon);
    }

    @Override
    public Integer getExaminatedNodeNumber() {
            return examinatedNodeNumber;
    }

    /**
     * MiniMax Decision
     * determina la prossima mossa da eseguire e restituisce il nuovo stato
     * @param gameState Stato attuale del gioco
     * @return nuovo stato
     */
    @Override
    public GameState nextMove(GameState gameState) {
//        System.out.println("Horizon: "+this.horizon);
        this.setExaminatedNodeNumber((Integer) 0);
        if(this.horizon!=null)return nextMove(gameState, this.horizon);
        ValueStatePair maxValueStatePair = maxValue(gameState);
        System.out.println(maxValueStatePair.getState());
        return maxValueStatePair.getState();
    }

//MODIFICARE ANCHE LE VERSIONE SENZA ORIZZONTE OPPURE ELIMINARLE
    private ValueStatePair maxValue(GameState gameState){
        setExaminatedNodeNumber(this.examinatedNodeNumber+1);
        System.out.println("MAX VALUE - Stampa dello stato in input");
        gameState.stampa();
        if(gameState.isTerminal())return new ValueStatePair(utility(gameState),gameState);
        //calcolare il massimo degli stati successori e ritornarlo in output
        Integer maxValue = (-1)*this.maxUtilityValue-1;
        GameState maxState = null;
        for(Integer i=0;i<gameState.getColumns();i++){
            try{
                GameState succ = gameState.clone();
                System.out.println("MAX VALUE - Stampa dello stato clone");
                succ.stampa();
                if(succ.doMove(this.playerId, i)){
                    System.out.println("MAX VALUE - Stampa dello stato a cui è applicata la mossa "+i);
                    succ.stampa();
                    ValueStatePair minValueStatePair = minValue(succ);
                    if(maxValue<minValueStatePair.getValue()){
                        maxValue=Math.max(maxValue,minValueStatePair.getValue());
                        maxState=succ;
                    }
                }
            }catch(CloneNotSupportedException exc){
                exc.printStackTrace();
            }
        }
        return new ValueStatePair(maxValue, maxState);
    }

    private ValueStatePair minValue(GameState gameState){
        setExaminatedNodeNumber(this.examinatedNodeNumber+1);
        System.out.println("MIN VALUE - Stampa dello stato in input");
        gameState.stampa();
        if(gameState.isTerminal())return new ValueStatePair(utility(gameState),gameState);
        //calcolare il minimo degli stati successori e ritornarlo in output
        Integer minValue = this.maxUtilityValue+1;
        GameState minState=null;
        for(Integer i=0;i<gameState.getColumns();i++){
            try{
                GameState succ = gameState.clone();
                System.out.println("MIN VALUE - Stampa dello stato clone");
                succ.stampa();
                if(succ.doMove(-1*this.playerId, i)){
                    System.out.println("MIN VALUE - Stampa dello stato a cui è applicata la mossa "+i);
                    succ.stampa();
                    ValueStatePair maxValueStatePair = maxValue(succ);
                    if(minValue>maxValueStatePair.getValue()){
                        minValue=Math.min(minValue,maxValueStatePair.getValue());
                        minState=succ;
                    }
                }
            }catch(CloneNotSupportedException exc){
                exc.printStackTrace();
            }
        }
        return new ValueStatePair(minValue, minState);
    }
//AGGIUSTARE I COMMENTI DELLA JAVADOC
    /**
     * MiniMax Decision con orizzonte
     * determina la prossima mossa da eseguire e restituisce il nuovo stato
     * @param gameState Stato attuale del gioco
     * @param horizon intero che rappresenta il numero di livelli da sviluppare
     * @return nuovo stato
     */
    public GameState nextMove(GameState gameState, Integer horizon) {
        ValueStatePair maxValueStatePair = maxValue(gameState, (-1)*this.maxUtilityValue-1, this.maxUtilityValue+1, horizon);
        return maxValueStatePair.getState();
    }

    /**
     * maxvalue con orizzonte, procedura ausiliaria al calcolo della prossima
     * mossa con l'algoritmo minmax
     * @param gameState stato da esaminare
     * @param horizon intero che rappresenta il numero di livelli che è ancora
     * possibile esaminare
     * @return una coppia Valore-Stato, contenente il max value e lo stato
     * corrispondente
     */
    private ValueStatePair maxValue(GameState gameState, Integer alpha, Integer beta, Integer horizon){
        setExaminatedNodeNumber(this.examinatedNodeNumber+1);
//        System.out.println("MAX VALUE - ORIZZONTE " + horizon);
//        System.out.println("MAX VALUE - Stampa dello stato in input");
//        gameState.stampa();
        if(gameState.isTerminal())return new ValueStatePair(utility(gameState),gameState);
        if(horizon<=0)return new ValueStatePair(heuristic.heuristic(gameState),gameState);

        //calcolare il massimo degli stati successori e ritornarlo in output
        Integer maxValue = (-1)*this.maxUtilityValue-1;
        GameState maxState = null;
        for(Integer i=0;i<gameState.getColumns();i++){
            try{
                GameState succ = gameState.clone();
//                System.out.println("MAX VALUE - Stampa dello stato clone");
//                succ.stampa();
                if(succ.doMove(this.playerId, i)){
//                    System.out.println("MAX VALUE - Stampa dello stato a cui è applicata la mossa "+i);
//                    succ.stampa();
                    ValueStatePair minValueStatePair = minValue(succ, alpha, beta, horizon-1);
                    if(maxValue<minValueStatePair.getValue()){
                        maxValue=minValueStatePair.getValue();
                        maxState=succ;
                    }
                    if (maxValue<=beta) return new ValueStatePair(maxValue, maxState);
                    alpha=Math.max(alpha, maxValue);
                }
            }catch(CloneNotSupportedException exc){
                exc.printStackTrace();
            }
        }
        return new ValueStatePair(maxValue, maxState);
    }

    /**
     * minvalue con orizzonte, procedura ausiliaria al calcolo della prossima
     * mossa con l'algoritmo minmax
     * @param gameState stato da esaminare
     * @param horizon intero che rappresenta il numero di livelli che è ancora
     * possibile esaminare
     * @return una coppia Valore-Stato, contenente il min value e lo stato
     * corrispondente
     */
    private ValueStatePair minValue(GameState gameState, Integer alpha, Integer beta, Integer horizon){
        setExaminatedNodeNumber(this.examinatedNodeNumber+1);
//        System.out.println("MIN VALUE - ORIZZONTE " + horizon);
//        System.out.println("MIN VALUE - Stampa dello stato in input");
//        gameState.stampa();
        if(gameState.isTerminal())return new ValueStatePair(utility(gameState),gameState);
        if(horizon<=0)return new ValueStatePair(heuristic.heuristic(gameState),gameState);
        //calcolare il minimo degli stati successori e ritornarlo in output
        Integer minValue = this.maxUtilityValue+1;
        GameState minState=null;
        for(Integer i=0;i<gameState.getColumns();i++){
            try{
                GameState succ = gameState.clone();
//                System.out.println("MIN VALUE - Stampa dello stato clone");
//                succ.stampa();
                if(succ.doMove(-1*this.playerId, i)){
//                    System.out.println("MIN VALUE - Stampa dello stato a cui è applicata la mossa "+i);
//                    succ.stampa();
                    ValueStatePair maxValueStatePair = maxValue(succ, alpha, beta, horizon-1);
                    if(minValue>maxValueStatePair.getValue()){
                        minValue=maxValueStatePair.getValue();
                        minState=succ;
                    }
                    if(minValue<=alpha) return new ValueStatePair(minValue, minState);
                    beta=Math.min(beta, minValue);
                }
            }catch(CloneNotSupportedException exc){
                exc.printStackTrace();
            }
        }
        return new ValueStatePair(minValue, minState);
    }

    /**
     * valuta la funzione di utilità
     * @param gameState stato di cui si vuole valutare la funzione di utilità
     * @return intero che rappresenta l'utilità dello stato
     */
    public Integer utility(GameState gameState){
        return this.maxUtilityValue*getPlayerId()*gameState.getWinner();
    }

    /**
     * @return the playerId
     */
    public Integer getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the horizon
     */
    public Integer getHorizon() {
        return horizon;
    }

    /**
     * @param horizon the horizon to set
     */
    public void setHorizon(Integer horizon) {
        this.horizon = horizon;
    }

    /**
     * @param examinatedNodeNumber the examinatedNodeNumber to set
     */
    private void setExaminatedNodeNumber(Integer examinatedNodeNumber) {
        this.examinatedNodeNumber = examinatedNodeNumber;
    }

}