public class Room {

    private int roomNumber;
    private String category;
    private boolean available;
    public Room(int roomNumber,String category){
        this.roomNumber=roomNumber;
        this.category=category;
        this.available=true;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public String getCategory(){
        return category;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available=available;
    }
}
