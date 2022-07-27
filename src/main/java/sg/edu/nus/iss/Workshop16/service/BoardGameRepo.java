package sg.edu.nus.iss.Workshop16.service;

import sg.edu.nus.iss.Workshop16.model.Mastermind;

public interface BoardGameRepo {
    public int save(final Mastermind ctc);

    public Mastermind findById(final String msId);

    public int update(final Mastermind ctc);
}
