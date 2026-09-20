package LLD_Interview_Questions.Easy.NotificationSystem;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


interface Notification {
     String getContent();
}


@Getter
@Setter
class SimpleNotification implements Notification {

    String text;

    SimpleNotification(String text){
        this.text = text;
    }
    public String getContent(){
        return text;
    }

}


//Decorator Pattern component

 abstract  class NotificationDecorator implements Notification{

    protected  Notification notification;
    public NotificationDecorator(Notification notification){
        this.notification = notification;
    }
}

class TimeStampNotification extends NotificationDecorator{
    Notification notification;

    public TimeStampNotification(Notification notification) {
        super(notification);
    }

    @Override
    public String getContent(){
        return new Date().toString() + notification.getContent();
    }
}

class SignatureNotification extends NotificationDecorator{

    String signature;
    public SignatureNotification(Notification notification,String signature) {
        super(notification);
        this.signature = signature;
    }

    @Override
    public String getContent(){
        return signature + notification.getContent();
    }
}


// Observer pattern component

interface Observable{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}

class NotificationObservable{
    Notification currNotification;
    List<Observer> observers;

    NotificationObservable(){
        currNotification = null;
    }

    void addObserver(Observer observer){
        observers.add(observer);
    }
    void removeObserver(Observer observer){
        observers.remove(observer);
    }
    void notifyObserver(){
        for (int i=0;i<observers.size();i++){
            observers.get(i).update();
        }
    }
    void setNotification(Notification notification){
        this.currNotification = notification;
    }
    Notification getNotification(){
        return currNotification;
    }

    String getNotificationContent(){
        return currNotification.getContent();
    }
}

interface Observer{
   void update();
}

class LoggingObserver implements Observer{

    NotificationObservable notificationObservable;
    @Override
    public void update() {
        System.out.println("Logging new notification : "+ notificationObservable.getNotification());
    }
}


//
interface NotificationStrategy{
      void sendNotification(String content);
}


//Strategy pattern component
class EmailStrategy implements NotificationStrategy{
    String emailId;

    EmailStrategy(String emailId){
        this.emailId = emailId;
    }
    public void sendNotification(String content){
        System.out.println("Sending email notification to:"+ emailId +""+content);
    }

}

class SMSStrategy implements NotificationStrategy{
    String mobileNumber;

    SMSStrategy(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    public void sendNotification(String content){
        System.out.println("Sending SMS notification to:"+ mobileNumber +""+content);
    }

}

class PopUpStrategy implements NotificationStrategy{

    public void sendNotification(String content){
        System.out.println("Sending PopUp notification to:" +content);
    }

}

class NotificationEngine implements Observer{
    NotificationObservable notificationObservable;
    List<NotificationStrategy> notificationStrategies;

    NotificationEngine(NotificationObservable observable){
        this.notificationObservable = observable;
    }

    void addNotificationStrategy(NotificationStrategy ns){
        this.notificationStrategies.add(ns);
    }

    public void update(){
        String notificationContent = notificationObservable.getNotificationContent();
        for(NotificationStrategy notificationStrategy : notificationStrategies){
            notificationStrategy.sendNotification(notificationContent);
        }
    }
}


// Notification service

// The Notification Service manages notifications. it keeps track of notification
// Any client code will interact with services

//Singleton class

class NotificationService {
    private NotificationObservable observable;
    private static NotificationService instance;
    private List<Notification> notifications;

    private NotificationService(){
        observable = new NotificationObservable();
    }

    public static NotificationService getInstance(){
        if(instance==null){
            instance = new NotificationService();
        }
        return instance;
    }

    NotificationObservable getObservable(){
        return  observable;
    }

    void sendNotification(Notification notification){
        notifications.add(notification);
        observable.setNotification(notification);
    }


    public static void main(String[] args) {
        NotificationService notificationService = NotificationService.getInstance();

        //Get Observable
        NotificationObservable notificationObservable = notificationService.getObservable();

        // Create Logger Observer
//        Logger logger = new Logger(notificationObservable);


        // Create notification observer
        NotificationEngine  notificationEngine = new NotificationEngine(notificationObservable);
        notificationEngine.addNotificationStrategy(new EmailStrategy("anshu.kumar@gmail.com"));
        notificationEngine.addNotificationStrategy(new SMSStrategy("7070698364"));
        notificationEngine.addNotificationStrategy(new PopUpStrategy());

        // Attach these observers
        notificationObservable.addObserver(notificationEngine);

        //Create s notification with decorators
        Notification notification = new SimpleNotification("Your oreder has been shipped");
        notification = new TimeStampNotification(notification);
        notification = new SignatureNotification(notification,"Customer Care");

        notificationService.sendNotification(notification);
    }
}


