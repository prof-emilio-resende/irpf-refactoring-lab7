package fit.core;

import fit.application.abstracts.DiscountTable;
import fit.application.abstracts.RateTable;
import fit.application.factories.DiscountTableFactory;
import fit.application.factories.RateTableFactory;
import fit.domain.Person;

public class IrpfCalculator {
    private Person person;
    private double baseSalary = 0;
    private DiscountTable discountTable;
    private RateTable rateTable;

    public IrpfCalculator(int year, Person person) {
        this.discountTable = DiscountTableFactory.build(year);
        this.rateTable = RateTableFactory.build(year);
        this.person = person;
        this.calculateBaseSalary();
    }

    // este cache é feito apenas como exemplo, não seria necessário
    public double calculateBaseSalary() {
        if (this.baseSalary == 0)
            this.baseSalary = 
                this.person.getTotalSalary() 
                - this.rateTable.getInss(this.person.getTotalSalary())
                - this.discountTable.getDependentsValue(this.person.getDependentsNumber());
            
        return this.baseSalary;
    }

    public double calculateDiscount() {
        return this.baseSalary - this.discountTable.getExemptionValue();
    }

    public double calculateTaxLayer() {
        return this.rateTable.getRate(this.baseSalary);
    }

    public double calculate() {
        return this.calculateDiscount() * this.calculateTaxLayer();
    }
}
