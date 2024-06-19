import java.util.*;
public class tickerbooker {
    static int aval_lower_breth=1;
    static int aval_upper_breth=1;
    static int aval_middle_breth=1;
    static int aval_rac_tickets=1;
    static int aval_waiting_list=1;

    static Queue<Integer> waitingList=new LinkedList<>();
    static Queue<Integer> racList=new LinkedList<>();
    static List<Integer> bookedticketList=new ArrayList<>();

    static List<Integer> lowerberthpositions=new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleberthpositions=new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperberthpositions=new ArrayList<>(Arrays.asList(1));
    static List<Integer> racpositions=new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitinglistpositions=new ArrayList<>(Arrays.asList(1));

    @SuppressWarnings({ "rawtypes", "unchecked" })
    Map<Integer,passenger> passengers=new HashMap();

    public void bookTicket(passenger p,int breth_info,String alloted_breth){
        p.number=breth_info;
        p.allotted=alloted_breth;
        passengers.put(p.passenger_Id, p);
        bookedticketList.add(p.passenger_Id);
        System.out.println("-----------------------------Booked successfully");
    }
    public void addtoRAC(passenger p,int  rac_info,String alloted_rac){
        p.number=rac_info;
        p.allotted=alloted_rac;
        passengers.put(p.passenger_Id,p);
        racList.add(p.passenger_Id);
        aval_rac_tickets--;
        racpositions.remove(0);
        System.out.println("--------------------------------Added to RAC successfully");
    }
    public void addtoWaitList(passenger p,int wait_info,String alloted_WL){
        p.number=wait_info;
        p.allotted=alloted_WL;
        passengers.put(p.passenger_Id,p);
        waitingList.add(p.passenger_Id);
        aval_waiting_list--;
        waitingList.remove(0);
        System.out.println("----------------------------------added to waiting list successfully");
    }
    public void cancelTicket(int passenger_Id){
        passenger p=passengers.get(passenger_Id);
        passengers.remove(passenger_Id);
        bookedticketList.remove(passenger_Id);
        int positionBooked=p.number;
        System.out.println("------------------Cancelled Successfully");
        if(p.allotted.equals("L")){
            aval_lower_breth++;
            lowerberthpositions.add(positionBooked);
        }
        else if(p.allotted.equals("M")){
            aval_middle_breth++;
            middleberthpositions.add(positionBooked);
        }
        else if(p.allotted.equals("U")){
            aval_upper_breth++;
            upperberthpositions.add(positionBooked);
        }
    

    if(racList.size()>0){
        passenger passengerfromrac=passengers.get(racList.poll());//poll returns the top element from the queue
        int positionRac=passengerfromrac.number;
        racpositions.add(positionRac);
        racList.remove(passengerfromrac.passenger_Id);
        aval_rac_tickets++;


        if(waitingList.size()>0){
            passenger passengerfromWaitingList=passengers.get(waitingList.poll());
            int positionWL=passengerfromWaitingList.number;
            waitinglistpositions.add(positionWL);
            waitingList.remove(passengerfromWaitingList.passenger_Id);

            passengerfromWaitingList.number=racpositions.get(0);
            passengerfromWaitingList.allotted="RAC";
            racpositions.remove(0);
            racList.add(passengerfromWaitingList.passenger_Id);

            aval_waiting_list++;
            aval_rac_tickets--;
        }

        Main.bookingTicket(passengerfromrac);
    }
}

    /**
     * 
     */
    public void printavailable()
    {
        System.out.println("Available lower berths "+aval_lower_breth);
        System.out.println("Available upper berths "+aval_upper_breth);
        System.out.println("Available middle berths "+aval_middle_breth);
        System.out.println("Available RAC "+aval_rac_tickets);
        System.out.println("Available Waiting List "+aval_waiting_list);
        System.out.println("----------------------------------------------------");

    }
    
    /* /* public void printPassengers(){
       /*  if(passengers.size()==0){
            System.out.println("No details of passengers found");
            return;
        } */
      /*   for(passenger p : passengers.values())
        {
           System.out.println("Passenger Id "+ p.passenger_Id); 
           System.out.println("Name "+ p.name); 
           System.out.println("Age"+ p.age);
           System.out.println("Status "+ p.number+p.allotted);
           System.out.println("---------------------------------------------------------");  
        } */ 

        public void printPassengers()
        {
            if(passengers.size() <= 0)
            {
                System.out.println("No details of passengers");
                return;
            }
            for(passenger p : passengers.values())
            {
                System.out.println("PASSENGER ID " + p.passenger_Id );
                System.out.println(" Name " + p.name );
                System.out.println(" Age " + p.age );
                System.out.println(" Status " + p.number + p.allotted);
                System.out.println("--------------------------");
            }
        }

    }

