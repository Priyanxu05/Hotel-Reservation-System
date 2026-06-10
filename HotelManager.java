import java.util.*;
import java.io.*;

public class HotelManager {

    private ArrayList<Room> rooms=new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    public HotelManager(){
        rooms.add(new Room(101,"Standard"));
        rooms.add(new Room(102,"Standard"));
        rooms.add(new Room(201,"Deluxe"));
        rooms.add(new Room(202,"Deluxe"));
        rooms.add(new Room(301,"Suite"));
        rooms.add(new Room(302,"Suite"));
    }
    public ArrayList<Room> getRooms(){
        return rooms;
    }
    public boolean bookRoom(String customer,int roomNo){
        for(Room room: rooms){
            if(room.getRoomNumber()==roomNo&&room.isAvailable()){
                room.setAvailable(false);
                Reservation r=new Reservation(customer,room);
                reservations.add(r);
                saveBooking(customer,room);
                return true;
            }}
        return false;
    }
    public boolean cancelBooking(int roomNo){
        for(Reservation r: reservations){
            if(r.getRoom().getRoomNumber()==roomNo){
                r.getRoom().setAvailable(true);
                reservations.remove(r);
                return true;
            }}
        return false;
    }
    private void saveBooking(
            String customer,
            Room room){
        try{
            FileWriter fw=new FileWriter("bookings.txt",true);
            fw.write(customer +" | Room "+ room.getRoomNumber()+ " | "+ room.getCategory()+ "\n");

            fw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }}
    }