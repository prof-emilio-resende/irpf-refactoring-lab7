package fit.application.concretes;

import fit.application.abstracts.DiscountTable;

public class DiscountTable2021 implements DiscountTable {
    public static final double EXEMPTION_VALUE = 1903.98;

    @Override
    public double getExemptionValue() {
        return EXEMPTION_VALUE;
    }

    @Override
    public double getDependentsValue(int number) {
        var discountPerDependent = 189.59;
        return number * discountPerDependent;
    }
    
}
