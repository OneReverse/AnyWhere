
import org.junit.Before;
import org.junit.Test;

import java.util.List;
/**
 * Created by Administrator on 2015/7/23.
 */
public class MonthBalanceCalculator {

    private List<PaymentDetailPerMonth> details;

    @Before
    public void setUp() {

    }

    @Test
    public void watchNormalResults() {
        details = ReturnPerMonthCalculator.calculateReturnByMonth(3000, 0.5, 3);
        printfDetails(0);
        details = ReturnPerMonthCalculatorWithBigDecimal.calculateReturnByMonth(3000, 0.5, 3);
        printfDetails(1);
        details = ReturnPerMonthCalculator.calculateReturnByMonth(10000, 0.05, 6);
        printfDetails(0);
        details = ReturnPerMonthCalculatorWithBigDecimal.calculateReturnByMonth(10000, 0.05, 6);
        printfDetails(1);
        details = ReturnPerMonthCalculator.calculateReturnByMonth(5000, 0.003, 9);
        printfDetails(0);
        details = ReturnPerMonthCalculatorWithBigDecimal.calculateReturnByMonth(5000, 0.003, 9);
        printfDetails(1);
    }

    private void printfDetails(int no) {
        if (no == 0)
            System.out.println("****************************************** Normal *****************************************");
        else if (no == 1)
            System.out.println("*************************************** BigDecimal *****************************************");

        int i = 1;
        int corpusIntotal = 0;
        int interestintotal = 0;
        int returnIntotal = 0;
        System.out.println("month:\t"+"corpus\t"+"interest\t"+"total");
        for (PaymentDetailPerMonth detail : details) {
            System.out.println("\t"+ i++ +":\t" + detail.corpusPerMonth + "\t" + detail.interestPerMonth + "\t\t" + detail.totalPerMonth);
            corpusIntotal += detail.corpusPerMonth;
            interestintotal += detail.interestPerMonth;
        }
        returnIntotal = corpusIntotal + interestintotal;

        System.out.println("total:\t" + corpusIntotal + "\t" + interestintotal + "\t\t" + returnIntotal);
    }
}
