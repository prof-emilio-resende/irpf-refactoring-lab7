package fit.core;

import fit.application.abstracts.DiscountTable;
import fit.application.concretes.DiscountTable2021;
import fit.domain.Person;

public class IrpfCalculator {
    private static final double INSS_VALUE_ALIQUOT = 0.11;

    private Person person;
    private double baseSalary = 0;
    private DiscountTable discountTable;

    public IrpfCalculator(int year, Person person) {
        if (year == 2021)
            this.discountTable = new DiscountTable2021();
        else // poderia ter uma tabela padrão ou outra coisa ...
            this.discountTable = new DiscountTable2021();

        this.person = person;
        this.calculateBaseSalary();
    }

    // este cache é feito apenas como exemplo, não seria necessário
    public double calculateBaseSalary() {
        if (this.baseSalary == 0)
            this.baseSalary = 
                this.person.getTotalSalary() 
                - (this.person.getTotalSalary() * INSS_VALUE_ALIQUOT) 
                - this.discountTable.getDependentsValue(this.person.getDependentsNumber());
            
        return this.baseSalary;
    }

    public double calculateDiscount() {
        return this.baseSalary - this.discountTable.getExemptionValue();
    }

    public double calculateTaxLayer() {
        var baseSalary = this.baseSalary;

        if (baseSalary <= 1903.98)
            return 0.0;
        if (baseSalary <= 2826.65)
            return 0.075;
        if (baseSalary <= 3751.05)
            return 0.15;
        if (baseSalary <= 4664.68)
            return 0.225;
        return 0.275;
    }

    public double calculate() {
        var discountValue = this.calculateDiscount();
        var taxValue = this.calculateTaxLayer();

        return discountValue * taxValue;
    }
}
