public class passenger {
    int id=1;//intial id
    String name;
    int age;
    String breth_pref;//L,U,M
    int passenger_Id;//automatically created for each passenger
    int number;//seat number
    String allotted;
    public passenger(String name,int age,String breth_pref){
        this.name=name;
        this.age=age;
        this.breth_pref=breth_pref;
        this.passenger_Id=id++;
        allotted="";
        number=-1;
    }
}
