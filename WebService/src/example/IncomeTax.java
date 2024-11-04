package example;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService()
public class IncomeTax {
    @WebMethod
    public double calculatePersonalIncomeTax(double salary) {
        double tax = 0.0;
        double deduction = 5000;
        double[] rate = {0.03, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45};
        double[] deductionAmount = {0, 210, 1410, 2660, 4410, 7160, 15160};
        if (salary > deduction) {
            double taxableIncome = salary - deduction;
            int index = 0;

            for (int i = 1; i < rate.length; i++) {
                if (taxableIncome > step(i)) {
                    index = i;
                }
            }

            tax = (taxableIncome - step(index - 1)) * rate[index] - deductionAmount[index];
        }

        return tax;
    }
    private double step(int level) {
        double[] levels = {3000, 12000, 25000, 35000, 55000, 80000};
        return levels[level - 1];
    }
    public static void main(String[] argv) {
        Object implementor = new IncomeTax();
        String address = "http://localhost:9000/IncomeTax";
        Endpoint.publish(address, implementor);
    }
}
