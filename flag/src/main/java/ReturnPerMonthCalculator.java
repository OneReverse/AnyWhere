import java.util.*;

/**
 * Created by Administrator on 2015/7/23.
 */
public class ReturnPerMonthCalculator {

    public static List calculateReturnByMonth(int _returnCorpus, double interest, int loanDays) {
        int returnTotal = _returnCorpus * 100;

        //算出月利率
        double monthRate = interest / 12;

        //计算等额金额,公式：每月还款额=贷款本金×[月利率×（1+月利率）^还款月数]÷[（1+月利率）^还款月数—1]
        //--这里要换算成分为单位
        int everyReturn = (int) (returnTotal * (monthRate * Math.pow((1 + monthRate), loanDays)) / (Math.pow((1 + monthRate), loanDays) - 1));

        //初始化已还本金额，单位是分
        int alreturnTotal = 0;
        //初始化已还利息，单位是分
        int alinvesTotal = 0;
        List<PaymentDetailPerMonth> detailedList = new ArrayList();
        for (int i = 0; i < loanDays; i++) {
            int returnSed = i + 1;
            int returnCount = everyReturn;
            int returnInterest = (int) ((returnTotal - alreturnTotal) * monthRate);
            int returnCorpus = everyReturn - returnInterest;
            alreturnTotal += returnCorpus;
            alinvesTotal += returnInterest;

            PaymentDetailPerMonth detail = new PaymentDetailPerMonth();
            detail.interestPerMonth = returnInterest;
            detail.corpusPerMonth = returnCorpus;
            detail.totalPerMonth = everyReturn;
            detailedList.add(detail);
        }

        return detailedList;
    }
}
