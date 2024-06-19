import java.util.*;
public class Main  {


    public static void bookingTicket(passenger p){
        tickerbooker booker=new tickerbooker();
        if(tickerbooker.aval_waiting_list==0){
            System.out.println("No tickets available");
            return;
        }
        if((p.breth_pref.equals("L") && tickerbooker.aval_lower_breth>0)||
         (p.breth_pref.equals("M") && tickerbooker.aval_middle_breth>0) ||
          (p.breth_pref.equals("U") && tickerbooker.aval_upper_breth>0))
          {
            System.out.println("Preferred breth available");
            if(p.breth_pref.equals("L")){
                System.out.println("Lower breth given");
                booker.bookTicket(p,(tickerbooker.lowerberthpositions.get(0)),"L");
                tickerbooker.lowerberthpositions.remove(0);
                tickerbooker.aval_lower_breth--;
            }
            else if(p.breth_pref.equals("M")){
                System.out.println("Middle breth given");
                booker.bookTicket(p,(tickerbooker.middleberthpositions.get(0)),"M");
                tickerbooker.middleberthpositions.remove(0);
                tickerbooker.aval_middle_breth--;
            }
            else if(p.breth_pref.equals("U")){
                System.out.println("Upper breth given");
                booker.bookTicket(p,(tickerbooker.upperberthpositions.get(0)),"U");
                tickerbooker.upperberthpositions.remove(0);
                tickerbooker.aval_upper_breth--;
            }
          }

        else if(tickerbooker.aval_lower_breth>0){
            System.out.println("Lower breth giver");
            booker.bookTicket(p,(tickerbooker.lowerberthpositions.get(0)),"L");
            tickerbooker.lowerberthpositions.remove(0);
            tickerbooker.aval_lower_breth--;
        }
        else if(tickerbooker.aval_middle_breth>0){
            System.out.println("Middle breth given");
            booker.bookTicket(p, (tickerbooker.middleberthpositions.get(0)),"M");
            tickerbooker.middleberthpositions.remove(0);
            tickerbooker.aval_middle_breth--;
        }
        else if(tickerbooker.aval_upper_breth>0){
            System.out.println("Upper breth given");
            booker.bookTicket(p,(tickerbooker.upperberthpositions.get(0)),"U");
            tickerbooker.upperberthpositions.remove(0);
            tickerbooker.aval_upper_breth--;
        }
        else if(tickerbooker.aval_rac_tickets>0){
            System.out.println("RAC available");
            booker.addtoRAC(p,(tickerbooker.racpositions.get(0)),"RAC");

        }
        else if(tickerbooker.aval_waiting_list>0){
            System.out.println("Added to waiting list");
            booker.addtoWaitList(p,(tickerbooker.waitinglistpositions.get(0)), "Wait");
        }
    }
    public static void cancelTicket(int id){
        tickerbooker booker=new tickerbooker();
        if(!booker.passengers.containsKey(id)){
            System.out.println("Passenger detail unknown");
        }
        else 
        booker.cancelTicket(id);
    }
    /**
     * @param args
     */
    public static void main(String[] args){
    try (Scanner scan = new Scanner(System.in)) {
        boolean loop=true;
        while(loop){
            System.out.println("1.BOOK TICKET \n 2.CANCEL TICKET \n 3.AVAILABLE TICKET \n 4.BOOKED TICKETS \n 5.EXIT");
            int choice=scan.nextInt();
            switch(choice){
                case 1:
                {
                    System.out.println("Enter passenger name,age and breth perference(L M or U)");
                    String name=scan.next();
                    int age=scan.nextInt();
                    String berth_pref=scan.next();
                    //passenger class ku here we are creating a object
                    passenger p=new passenger(name, age, berth_pref);
                    //calling a function for booking a ticket for the passenger
                    bookingTicket(p);

                }
                break;
                case 2:{
                    System.out.println("Enter passenger ID to cancel");
                    int id=scan.nextInt();
                    cancelTicket(id);
                }
                break;
                //print available tickets
                case 3:
                {
                 tickerbooker booker=new tickerbooker();
                 booker.printavailable();
                }
                break;
                //occupied tickets
                case 4:
                {
                    tickerbooker booker=new tickerbooker();
                    booker.printPassengers();
                }
                case 5:
                loop=false;
                break;

                default:
                break;
            }
        }
    }
}
}
