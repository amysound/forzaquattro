/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package connectFour;

/**
 *
 * @author barnap
 */
class ValueMovePair {
    
    private Integer value;
    private Integer move;
    /**
     * Constructor
     */
    public ValueMovePair(Integer value, Integer move){
        this.move=move;
        this.value=value;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return the move
     */
    public Integer getMove() {
        return move;
    }

    /**
     * @param move the move to set
     */
    public void setMove(Integer move) {
        this.move = move;
    }
}
