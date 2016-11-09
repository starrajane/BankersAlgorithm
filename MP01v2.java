
//package mp01v2;

import java.util.Scanner;

/**
 *
 * @author Furton
 */
public class MP01v2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        MP01v2 mp = new MP01v2();
        
        //menu
        System.out.println("Choose one of the following: ");
        System.out.println("1 - Deadlock Prevention");
        System.out.println("2 - Deadlock Avoidance");
        System.out.println("3 - Quit");
        System.out.println("\nEnter choice: ");
        choice = input.nextInt();
        
        switch(choice){
            case 1:
                System.out.println("\n\n---DEADLOCK PREVENTION---\n");
                mp.deadlockPrevention();
                break;
            case 2:
                System.out.println("\n\n---DEADLOCK AVOIDANCE---\n");
                mp.deadlockAvoidance();
                break;
            case 3:
                System.out.println("Quitting program...");
                break;
        }
    }
    
    public void deadlockPrevention(){
        Scanner input = new Scanner(System.in);
        int process_size, available;
        
        System.out.print("Enter total number of processes: ");
        process_size = input.nextInt();
        
        System.out.print("Available single resource: ");
        available = input.nextInt();
        
        DeadlockPrevention dp = new DeadlockPrevention(process_size, available);
        dp.setProcesses();
        dp.bankers();
    }
    
    public void deadlockAvoidance(){
        Scanner input = new Scanner(System.in);
        int process_size, resource_size, choice;
        
        System.out.print("Enter total number of processes: ");
        process_size = input.nextInt();
        
        System.out.print("Enter total number of resources: ");
        resource_size = input.nextInt();
        
        DeadlockAvoidance da = new DeadlockAvoidance(process_size, resource_size);
        da.setAllocation();
        da.setMaximum();
        da.setAvailable();
        da.setNeed();
        da.displayAllocation();
        da.displayMaximum();
        da.displayNeed();
        da.bankers();
        
        System.out.println("\n\nMake another request?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        System.out.println("Enter number of choice: ");
        choice = input.nextInt();

        switch(choice){
            case 1:
                da.newRequest();
                break;
            case 2:
                break;
        }
    }
}
