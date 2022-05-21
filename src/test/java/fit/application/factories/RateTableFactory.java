package fit.application.factories;

import fit.application.abstracts.RateTable;
import fit.application.concretes.RateTable2021;

public abstract class RateTableFactory {
    public static RateTable build(int year) {
        if (year == 2021) return new RateTable2021();

        return null;
    }
}
