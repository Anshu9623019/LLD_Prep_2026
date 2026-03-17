package LLD.DesignPettern.CreationalDesignPettern.SingletonPettern;

class  Logger{
    private static Logger logger;

    private Logger(){

    }

    public  static  Logger getLogger(){
        if(logger==null){
            synchronized (Logger.class){
                if (logger==null){
                    logger = new Logger();
                }
            }
        }
        return logger;
    }
}


public class MAin {
        Logger logger = Logger.getLogger();

}


