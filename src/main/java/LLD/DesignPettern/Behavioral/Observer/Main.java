package LLD.DesignPettern.Behavioral.Observer;

import java.util.ArrayList;
import java.util.List;

interface Subscriber{
    void update(String video);
}

class YoutubeSubscriber implements  Subscriber{
    String name;

    YoutubeSubscriber(String name){
        this.name = name;
    }

    @Override
    public void update(String video){
        System.out.println(name+" is watching the video " + video);
    }
}

class EmailSubscriber implements  Subscriber{
    String name;

    EmailSubscriber(String name){
        this.name = name;
    }

    @Override
    public void update(String video){
        System.out.println(name+" is watching the video " + video);
    }
}

class NotificationSubscriber implements  Subscriber{
    String name;

    NotificationSubscriber(String name){
        this.name = name;
    }

    @Override
    public void update(String video){
        System.out.println(name+" is watching the video  " + video);
    }
}

interface YoutubeChannel{
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifySubscriber();
}

// Subject Impl
class  YoutubeChannelImpl implements YoutubeChannel{

    List<Subscriber> subscribers = new
            ArrayList<>();
    private  String video;

    @Override
    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber(){
        for(Subscriber subscriber : subscribers){
            subscriber.update(video);
        }
    }
    public void uploadNewVideo(String video){
        this.video = video;
        notifySubscriber();
    }
}

//Driver class
public class Main {

    public static void main(String[] args) {
        YoutubeChannelImpl channel = new YoutubeChannelImpl();
        YoutubeSubscriber alice = new YoutubeSubscriber("Alice");
        YoutubeSubscriber bob = new YoutubeSubscriber("Bob");
        EmailSubscriber emailSubs = new EmailSubscriber("Email Subscriber");
        channel.addSubscriber(alice);
        channel.addSubscriber(bob);
        channel.addSubscriber(emailSubs);
        channel.addSubscriber(new NotificationSubscriber("Notification Subs"));
        channel.uploadNewVideo("Java new video uploaded");

    }




}
