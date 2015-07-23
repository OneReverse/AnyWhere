import java.util.*;

/**
 * Created by Administrator on 2015/7/23.
 */
public class ReturnPerMonthCalculator {

    public static List calculateReturnByMonth(int _returnCorpus, double interest, int loanDays) {
        int returnTotal = _returnCorpus * 100;

        //���������
        double monthRate = interest / 12;

        //����ȶ���,��ʽ��ÿ�»����=������[�����ʡ���1+�����ʣ�^��������]��[��1+�����ʣ�^����������1]
        //--����Ҫ����ɷ�Ϊ��λ
        int everyReturn = (int) (returnTotal * (monthRate * Math.pow((1 + monthRate), loanDays)) / (Math.pow((1 + monthRate), loanDays) - 1));

        //��ʼ���ѻ�������λ�Ƿ�
        int alreturnTotal = 0;
        //��ʼ���ѻ���Ϣ����λ�Ƿ�
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
