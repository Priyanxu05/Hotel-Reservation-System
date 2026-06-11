import java.util.*;
import java.io.*;

public class HotelManager {

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public HotelManager(){
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
        rooms.add(new Room(302, "Suite"));

        loadBookings();
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public ArrayList<Reservation> getReservations(){
        return reservations;
    }

    public boolean bookRoom(String customer, int roomNo){
        for (Room room : rooms){

            if (room.getRoomNumber()==roomNo &&
                    room.isAvailable()){
                room.setAvailable(false);
                Reservation reservation=new Reservation(customer,room);
                reservations.add(reservation);
                saveBookings();
                return true;
            }
        }

        return false;
    }

    public boolean cancelBooking(int roomNo) {

        Iterator<Reservation> iterator=reservations.iterator();
        while (iterator.hasNext()){

            Reservation reservation=iterator.next();
            if (reservation.getRoom().getRoomNumber()==roomNo){
                reservation.getRoom().setAvailable(true);
                iterator.remove();
                saveBookings();
                return true;
            }}
        return false;
    }
    private void saveBookings(){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter("bookings.txt"));
            for (Reservation r : reservations){
                bw.write(r.getCustomerName() + "," +r.getRoom().getRoomNumber() + "," +r.getRoom().getCategory());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    private void loadBookings() {
        File file=new File("bookings.txt");
        if (!file.exists())
            return;

        try{
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine())!=null){
                String[] data=line.split(",");
                String customer=data[0];
                int roomNo=Integer.parseInt(data[1]);
                for (Room room : rooms){
                    if(room.getRoomNumber()==roomNo){
                        room.setAvailable(false);
                        reservations.add(new Reservation(customer,room));
                    }}}
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
