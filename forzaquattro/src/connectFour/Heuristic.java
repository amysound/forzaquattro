package connectFour;
/**
 * Heuristic.java
 *
 * File generated from the ConnectFour::Heuristic uml Class
 * Generated by the Acceleo UML 2.1 to Java generator module (Obeo)
 * $ Date : 23/04/10 15.19.33 (23 aprile 2010) $
 */

/**
 * Description of the class Heuristic.
 * classe che fornisce l'euristica con cui valutare gli stati non terminali
 */

public class Heuristic {

    /**
     * Constructor.
     */
    public Heuristic() {
            // Start of user code for constructor Heuristic
            super();
            // End of user code
    }

    /**
     * metodo che valuta la funzione euristica sullo stato passato come argomento
     * @param gameState stato di cui si vuole valutare l'euristica
     * @return il valore della funzione euristica
     */
    public Integer calculateHeuristic(GameState gameState){
        return countConnectThreeHeuristic(gameState);
    }

    private Integer naiveHeuristic(GameState gameState){
        return 0;
    }


    /**
     * valori valutati per il giocatore yellow
     * @param gameState
     * @return
     */
    private Integer basicHeuristic(GameState gameState){
        if(findConnectThree(gameState, ControllerInterface.yellow)) return 90;
        if(findConnectThree(gameState, ControllerInterface.red)) return -90;
        return 0;
    }


    /**
     * valori valutati per il giocatore yellow
     * @param gameState
     * @return
     */
    private Integer countConnectThreeHeuristic(GameState gameState){
        Integer temp = countConnectThree(gameState, ControllerInterface.yellow)-countConnectThree(gameState, ControllerInterface.red);
        return temp;
    }


    /**
     *
     * @param gameState
     * @param player
     * @return true se trova tre pedine consecutive per player con una vuota adiacente
     */
    private Boolean findConnectThree(GameState gameState, Integer player){

        Integer row;
        Integer column;

        for(row=0;row<gameState.getRows();row++){

            for(column=0;column<gameState.getColumns();column++){
                //CONTROLLO ORIZZONTALE
                if(checkHorizontal(row,column, gameState, player)) return true;

                //CONTROLLO DIAGONALE
                if(checkDiagonal(row,column, gameState, player)) return true;

                //CONTROLLO ANTIDIAGONALE
                if(checkAntiDiagonal(row,column, gameState, player)) return true;

                //CONTROLLO VERTICALE
                if(checkVertical(row,column, gameState, player)) return true;
            }
        }
        return false;
    }

    private Integer countConnectThree(GameState gameState, Integer player){
        Integer row;
        Integer column;
        Integer connectThree;

        connectThree=0;
        for(row=0;row<gameState.getRows();row++){

            for(column=0;column<gameState.getColumns();column++){
                //CONTROLLO ORIZZONTALE
                if(checkHorizontal(row,column, gameState, player)) connectThree++;

                //CONTROLLO DIAGONALE
                if(checkDiagonal(row,column, gameState, player)) connectThree++;

                //CONTROLLO ANTIDIAGONALE
                if(checkAntiDiagonal(row,column, gameState, player)) connectThree++;

                //CONTROLLO VERTICALE
                if(checkVertical(row,column, gameState, player)) connectThree++;
            }
        }
        return connectThree;
    }


    /**
     * @param x riga di partenza
     * @param y colonna di partenza
     * @return true se trova il forza quattro; false nei seguenti casi:
     * - se le coordinate inserite non sono valide; ciò avviene se la riga o
     *   la colonna inserita è fuori dai limiti della matrice oppure se la colonna
     *   è tale che la funzione non è applicabile
     * - se nelle quattro colonne a partire dalla cella [x][y] verso sinistra non
     *   si trova un forza quattro
     */
    private Boolean checkHorizontal(Integer x, Integer y, GameState gameState, Integer player){
        Integer numberToSearch=3;
        Integer i;
        Integer countPieces; //contiene il numero di celle occupate da pedine di player
        Integer countFreeCells; //contiene il numero di celle vuote

        /**
         * controllo sulle coordinate inserite:
         * - x deve essere una riga valida, e quindi un intero tra 0 e this.rows
         * - y deve essere una colonna valida a cui è applicabile la funzione
         *   e quindi deve essere compreso tra 3 e this.columns
         */
        if(x<0 || x>=gameState.getRows() || y<3 || y>=gameState.getColumns()) return false;

        // controllo forza quattro sulla riga
        i = 0;
        countPieces=0;
        countFreeCells=0;
        while(i < 4){
            if(gameState.getCellState(x, y-i).equals(-1*player)) return false;
            if(gameState.getCellState(x, y-i).equals(player)) countPieces++;
            i++;
        }

        if (countPieces.equals(numberToSearch)) return true;

        return false;
    }

    /**
     * @param x riga di partenza
     * @param y colonna di partenza
     * @return true se trova il forza quattro; false nei seguenti casi:
     * - se le coordinate inserite non sono valide; ciò avviene se la riga o
     *   la colonna inserita è fuori dai limiti della matrice oppure se la riga
     *   (o la colonna) è tale che la funzione non è applicabile
     * - se nelle quattro colonne a partire dalla cella [x][y] verso sinistra non
     *   si trova un forza quattro
     */
    private Boolean checkDiagonal(Integer x, Integer y, GameState gameState, Integer player){
        Integer i;
        Integer countPieces; //contiene il numero di celle occupate da pedine di player
        Integer countFreeCells; //contiene il numero di celle vuote

        /**
         * controllo sulle coordinate inserite:
         * - x deve essere una riga valida a cui è applicabile la funzione
         *   e quindi un intero tra 3 e this.rows
         * - y deve essere una colonna valida a cui è applicabile la funzione
         *   e quindi deve essere compreso tra 3 e this.columns
         */
        if(x<3 || x>=gameState.getRows() || y<3 || y>=gameState.getColumns()) return false;

        // controllo forza quattro sulla diagonale
        i = 0;
        countPieces=0;
        countFreeCells=0;
        while(i < 4){
            if(countFreeCells.equals(2)) return false;
            if(gameState.getCellState(x-i, y-i).equals(player)) countPieces++;
            else if(gameState.getCellState(x-i, y-i).equals(0)) countFreeCells++;
            else return false;
            i++;
        }

        if (countPieces.equals(3)) return true;

        return false;
    }

    /**
     * @param x riga di partenza
     * @param y colonna di partenza
     * @return true se trova il forza quattro; false nei seguenti casi:
     * - se le coordinate inserite non sono valide; ciò avviene se la riga o
     *   la colonna inserita è fuori dai limiti della matrice oppure se la riga
     *   (o colonna) inserita è tale che la funzione non è applicabile.
     * - se nelle quattro colonne a partire dalla cella [x][y] verso sinistra
     *   e verso l'alto non si trova un forza quattro
     */
    private Boolean checkAntiDiagonal(Integer x, Integer y, GameState gameState, Integer player){
        Integer i;
        Integer countPieces; //contiene il numero di celle occupate da pedine di player
        Integer countFreeCells; //contiene il numero di celle vuote

        /**
         * controllo sulle coordinate inserite:
         * - x deve essere una riga valida a cui è applicabile la funzione
         *   e quindi un intero tra 0 e this.rows-3
         * - y deve essere una colonna valida a cui è applicabile la funzione
         *   e quindi deve essere compreso tra 3 e this.columns
         */
        if(x<0 || x>=gameState.getRows()-3 || y<3 || y>=gameState.getColumns()) return false;

        // controllo forza quattro sull'antidiagonale
        i = 0;
        countPieces=0;
        countFreeCells=0;
        while(i < 4){
            if(countFreeCells.equals(2)) return false;
            if(gameState.getCellState(x+i, y-i).equals(player)) countPieces++;
            else if(gameState.getCellState(x+i, y-i).equals(0)) countFreeCells++;
            else return false;
            i++;
        }

        if (countPieces.equals(3)) return true;

        return false;
    }


    /**
     * @param x riga di partenza
     * @param y colonna di partenza
     * @return true se trova il forza quattro; false nei seguenti casi:
     * - se le coordinate inserite non sono valide; ciò avviene se la riga o
     *   la colonna inserita è fuori dai limiti della matrice oppure se la riga
     *   è tale che la funzione non è applicabile
     * - se nelle quattro colonne a partire dalla cella [x][y] verso il basso non
     *   si trova un forza quattro
     */
    private Boolean checkVertical(Integer x, Integer y, GameState gameState, Integer player){
        Integer i;

        /**
         * controllo sulle coordinate inserite:
         * - x deve essere una riga valida a cui è applicabile la funzione
         *   e quindi un intero tra 3 e this.rows
         * - y deve essere una colonna valida, e quindi deve essere compreso tra
         *   0 e this.columns
         */
        if(x<3 || x>=gameState.getRows() || y<0 || y>=gameState.getColumns()) return false;

        // controllo forza quattro sulla colonna
        if(gameState.getCellState(x, y).equals(0)){
            i = 1;

            while((i < 4)&&(gameState.getCellState(x-i, y).equals(player))) i++;

            if (i.equals(4)) return true;
        }
        return false;
    }

}