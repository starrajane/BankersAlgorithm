//package mp01v2;

import java.util.Scanner;

/**
 *
 * @author Furton
 */
public class DeadlockPrevention {
    private int process_size;
    private int available;
    private int processes[];
    
    public DeadlockPrevention(int process, int avail){
        this.process_size = process;
        this.processes = new int [process_size];
        this.available = avail;
    }
    
    //asks for user input then sets it to the processes array
    public void setProcesses(){
        Scanner input = new Scanner(System.in);
        int allocate, ascii = 97;
        
        System.out.println("");
        
        for(int i=0; i < process_size; i++){
            System.out.print("Allocation for P" + i + 
                 " in resource " + (char) ascii + ": ");
            allocate = input.nextInt();
            processes[i] = allocate;
            ascii++;
        }
    }
    
    //does banker's algorithm
    public void bankers(){
        boolean done[] = new boolean[process_size];
        int safeSequence[] = new int[process_size];
        boolean allocated=false;
        int temp=0, j=0;

        while(temp < process_size){
            allocated = false;

            for(int i=0; i < process_size; i++)
                if((processes[i] <= available) && !done[i]){
                    safeSequence[j] = i;
                    available += processes[i];
                    done[i] = true;
                    allocated = true;
                    temp++;
                    j++;
                }

                if(!allocated)
                    break;
        }
        
        if(temp==process_size){
            System.out.println("\nSTATUS: System is SAFE.");
            System.out.println("Safe sequence: " + java.util.Arrays.toString(safeSequence));
        }
        else{
            System.out.println("\nSTATUS: System encountered a DEADLOCK.");
        }
    }
}
