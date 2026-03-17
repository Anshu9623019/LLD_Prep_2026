package LLD.DesignPettern.Behavioral.Strategy;

interface PaymentStrategy{
    void processPayment();
}
class  UPI implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("UPI payment processing...");
    }
}
class  CreditCard implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("Credit Card payment processing...");
    }
}
class  Paypal implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("Paypal payment processing...");
    }
}

class PaymentProcessor{
    PaymentStrategy paymentStrategy;

    PaymentProcessor(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public  void paymentProcess(){
        paymentStrategy.processPayment();
    }
    public void setPaymentProcess(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
}
public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor(new Paypal());
        processor.paymentProcess();
        processor.setPaymentProcess(new CreditCard());
        processor.paymentProcess();
    }
}
