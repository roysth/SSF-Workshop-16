package sg.edu.nus.iss.Workshop16.model;

import java.io.Serializable;

public class Pieces implements Serializable {
    private DecodingBoard decodingBoard;
    private Pegs pegs;
    private Rulebook rulebook;

    public DecodingBoard getDecodingBoard() {
        return decodingBoard;
    }

    public void setDecodingBoard(DecodingBoard value) {
        this.decodingBoard = value;
    }

    public Pegs getPegs() {
        return pegs;
    }

    public void setPegs(Pegs value) {
        this.pegs = value;
    }

    public Rulebook getRulebook() {
        return rulebook;
    }

    public void setRulebook(Rulebook value) {
        this.rulebook = value;
    }

}
