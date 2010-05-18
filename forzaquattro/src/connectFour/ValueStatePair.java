/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package connectFour;

/**
 *
 * @author barnap
 */


class ValueStatePair{

    private Integer value;
    private GameState state;

    public ValueStatePair(Integer value, GameState state) {
        this.value = value;
        this.state = state;
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
     * @return the state
     */
    public GameState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(GameState state) {
        this.state = state;
    }
}
