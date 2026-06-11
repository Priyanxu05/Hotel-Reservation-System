import javax.swing.*;
import java.awt.*;

public class HotelReservationGUI extends JFrame {

    private HotelManager manager;
    private JTextArea area;

    public HotelReservationGUI() {

        manager = new HotelManager();
        setTitle("Hotel Reservation System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        area = new JTextArea();
        area.setEditable(false);
        JButton viewBtn=new JButton("View Rooms");
        JButton bookBtn=new JButton("Book Room");
        JButton cancelBtn=new JButton("Cancel Booking");
        JButton bookingBtn=new JButton("View Bookings");
        JPanel panel=new JPanel();

        panel.add(viewBtn);
        panel.add(bookBtn);
        panel.add(cancelBtn);
        panel.add(bookingBtn);

        add(panel,BorderLayout.NORTH);

        add(new JScrollPane(area),BorderLayout.CENTER);
        viewBtn.addActionListener(e -> displayRooms());

        bookingBtn.addActionListener(e -> displayBookings());
        bookBtn.addActionListener(e ->{
            String name=JOptionPane.showInputDialog("Customer Name");

            String room=JOptionPane.showInputDialog("Room Number");
            if (name==null||room==null)
                return;

            int roomNo=Integer.parseInt(room);
            int option=JOptionPane.showConfirmDialog(this,"Payment Successful?","Payment",JOptionPane.YES_NO_OPTION);

            if (option==JOptionPane.YES_OPTION){
                boolean success =manager.bookRoom(name,roomNo);
                if(success)
                    JOptionPane.showMessageDialog(this,"Booking Confirmed");
                else
                    JOptionPane.showMessageDialog(this,"Room Not Available");
                displayRooms();
            }
        });

        cancelBtn.addActionListener(e ->{
            String room=JOptionPane.showInputDialog("Room Number");

            if (room==null)
                return;

            boolean success=manager.cancelBooking(
                    Integer.parseInt(room));

            if(success)
                JOptionPane.showMessageDialog(this,"Booking Cancelled");
            else
                JOptionPane.showMessageDialog(this,"Booking Not Found");

            displayRooms();
        });

        displayRooms();
    }

    private void displayRooms(){
        area.setText("");
        area.append("===== ROOM STATUS =====\n\n");
        for(Room room : manager.getRooms()){
            area.append("Room: "+ room.getRoomNumber()+ " | "+ room.getCategory()+ " | "+ (room.isAvailable()   ? "Available"   : "Booked")+ "\n");}
    }

    private void displayBookings(){
        area.setText("");
        area.append("===== BOOKING DETAILS =====\n\n");
        if (manager.getReservations().isEmpty()){
            area.append("No Bookings Found");
            return;
        }
        for(Reservation r : manager.getReservations()){
            area.append("Customer: "+ r.getCustomerName()+ "\nRoom: "+ r.getRoom().getRoomNumber()+ "\nCategory: "+ r.getRoom().getCategory()+ "\n-----------------\n");
        }}

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> new HotelReservationGUI().setVisible(true));
    }
}
