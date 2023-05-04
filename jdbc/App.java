package jdbc;

import java.util.List;

public class App {
    public static void main(String[] args){
        PassengerList list = new PassengerList();
        List<Passenger> passnegers = list.getDate();
        
        for(Passenger p: passnegers){
            System.out.println(p);
        }

    }
}
