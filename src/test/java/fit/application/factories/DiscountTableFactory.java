package fit.application.factories;

import fit.application.abstracts.DiscountTable;
import fit.application.concretes.DiscountTable2021;

public abstract class DiscountTableFactory {
    public static DiscountTable build(int year) {
        if (year == 2021) return new DiscountTable2021();

        return null;
    }
}
