import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import static java.math.BigDecimal.*;
/**
 * Created by OneReverse on 2015/7/23.
 */
public class ReturnPerMonthCalculatorWithBigDecimal {
    public static List calculateReturnByMonth(int _returnCorpus, double interest, int monthsToReturn) {
        BigDecimal totalInvestment  = getTotalInvestmentBy(_returnCorpus);
        BigDecimal ratePerMonth = getRatePerMonthBy(interest);
        //计算等额金额,公式：每月还款额=贷款本金×[月利率×（1+月利率）^还款月数]÷[（1+月利率）^还款月数—1]
        BigDecimal extraRatePerMonth = ratePerMonth.add(ONE).pow(monthsToReturn);
        BigDecimal totalReturnByMonth = totalInvestment.multiply(ratePerMonth).multiply(extraRatePerMonth).divide(extraRatePerMonth.subtract(ONE), 8, RoundingMode.HALF_EVEN );

        BigDecimal alreturnTotal = ZERO;
        BigDecimal alinvesTotal = ZERO;
        List<PaymentDetailPerMonth> detailedList = new ArrayList();
        for (int i = 0; i < monthsToReturn; i++) {
            BigDecimal returnInterest = totalInvestment.subtract(alreturnTotal).multiply(ratePerMonth);
            BigDecimal returnCorpus = totalReturnByMonth.subtract(returnInterest);
            alreturnTotal = alreturnTotal.add(returnCorpus);
            alinvesTotal = alinvesTotal.add(returnInterest);

            PaymentDetailPerMonth detail = new PaymentDetailPerMonth();
            detail.interestPerMonth = returnInterest.intValue();
            detail.corpusPerMonth = returnCorpus.intValue();
            detail.totalPerMonth = totalReturnByMonth.intValue();
            detailedList.add(detail);
        }

        return detailedList;
    }

    private static BigDecimal getTotalInvestmentBy(int investment) {
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        return BigDecimal.valueOf(investment).multiply(oneHundred);
    }

    private static BigDecimal getRatePerMonthBy(double interest) {
        BigDecimal interestPerYear = BigDecimal.valueOf(interest);
        BigDecimal months = BigDecimal.valueOf(12);
        return interestPerYear.divide(months, 8, RoundingMode.HALF_EVEN);
    }

}
