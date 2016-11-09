
//package mp01v2;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Furton
 */
public class DeadlockAvoidance {
    private int allocation[][];
    private int maximum[][];
    private int need[][];
    private int available[];
    private int updatedAvailable[];
    private int process_size;
    private int resource_size;
    
    //initializing variables
    public DeadlockAvoidance(int process, int resource){
        this.process_size = process;
        this.resource_size = resource;
        allocation = new int [process_size][resource_size];
        maximum = new int [process_size][resource_size];
        need = new int [process_size][resource_size];
        available = new int[resource_size];
        updatedAvailable = new int[resource_size];
    }
    
    //asks for user input then sets it to the allocation matrix
    public void setAllocation(){
        Scanner input = new Scanner(System.in);
        int allocate, ascii = 97;
        
        System.out.println("");
        
        for(int i=0; i < process_size; i++){
           for(int j=0; j < resource_size; j++){
               System.out.print("Allocation for P" + i + 
                    " in resource " + (char) ascii + ": ");
               allocate = input.nextInt();
               allocation[i][j] = allocate;
               ascii++;
           }
           ascii=97;
        }
    }
    
    //asks for user input then sets it to the maximum matrix
    public void setMaximum(){
        Scanner input = new Scanner(System.in);
        int allocate, ascii = 97;
        
        System.out.println("");
        
        for(int i=0;i<process_size;i++){
           for(int j=0;j<resource_size;j++){
               System.out.print("Maximum for P" + i + 
                    " in resource " + (char) ascii + ": ");
               allocate = input.nextInt();
               maximum[i][j] = allocate;
               ascii++;
           }
           ascii = 97;
        }
    }
    
    //asks for user input then sets it to the available array
    public void setAvailable(){
        Scanner input = new Scanner(System.in);
        int avail, ascii=97;
        
        System.out.println("");
        for(int j=0; j < resource_size; j++){
            System.out.print("Available for resource " + (char) ascii + ": ");
            avail = input.nextInt();
            available[j] = avail;
            updatedAvailable[j] = avail;
            ascii++;
        }
    }
    
    //calculates the need by subtracting allocation[i][j] from max[i][j]
    //then sets it to the need matrix
    public void setNeed(){
       for(int i=0; i < process_size; i++){
           for(int j=0; j < resource_size; j++){
              need[i][j] = maximum[i][j] - allocation[i][j]; 
           }
       }
    }
    
    //checks whether the resources needed can be allocated
    private boolean checkResource(int i){
        for(int j=0; j <resource_size; j++) 
            if(updatedAvailable[j] < need[i][j])
                return false;
    
        return true;
    }
    
    //does the banker's algorithm
    public void bankers(){
       boolean finish[] = new boolean[process_size];
       int safeSequence[] = new int[process_size];
       int temp=0, a=0;
 
       while(temp < process_size){
        boolean allocated=false;
        
            for(int i=0; i < process_size; i++)
                if(!finish[i] && checkResource(i)){
                    for(int j=0; j < resource_size; j++)
                        updatedAvailable[j] = updatedAvailable[j] + allocation[i][j];
                        allocated = true;
                        finish[i] = true;
                        safeSequence[a] = i;
                        a++;
                        temp++;
                }
                if(!allocated)
                    break;
       }
       
        if(temp==process_size){
            System.out.println("\n\nSTATUS: System is SAFE.");
            System.out.println("Safe sequence: " + java.util.Arrays.toString(safeSequence));
        }
        else
            System.out.println("\nSTATUS: System encountered a DEADLOCK.");
    }
    
    public void newRequest(){
        Scanner input = new Scanner(System.in);
        int processnumber, resource, ascii = 97;
        
        System.out.println("\nEnter process number: ");
        processnumber = input.nextInt();
        
        //sets the updated available to the original available
        setToDefaultAllocation();
        
        for(int i=0; i < resource_size; i++){
            System.out.print("Allocation for P" + processnumber + 
                " in resource " + (char) ascii + ": ");
            resource = input.nextInt();
            allocation[processnumber][i] =  allocation[processnumber][i] + resource;
            updatedAvailable[i] -= resource;
            ascii++;
        }
        
        bankers();
    }
    
    //sets the updated available to the original available
    public void setToDefaultAllocation(){
        for(int i=0; i < process_size; i++){
            for(int j=0; j < resource_size; j++){
                updatedAvailable[j] = available[j];
            }
        }
    }
    
    public void displayAllocation(){
        System.out.println("\n------------------");
        System.out.println("ALLOCATION MATRIX");
        for(int i=0; i < process_size; i++){
            System.out.print("\nP" + i + " ");
            for(int j=0; j < resource_size; j++){
                System.out.print(allocation[i][j] + " ");
            }
        }
    }
    
    public void displayMaximum(){
        System.out.println("\n------------------");
        System.out.println("MAXIMUM MATRIX");
        for(int i=0; i < process_size; i++){
            System.out.print("\nP" + i + " ");
            for(int j=0; j < resource_size; j++){
                System.out.print(maximum[i][j] + " ");
            }
        }
    }
    
    public void displayNeed(){
        System.out.print("\n------------------");
        System.out.print("\nNEED MATRIX");
        for(int i=0; i < process_size; i++){
            System.out.print("\nP" + i + " ");
            for(int j=0; j < resource_size; j++){
                System.out.print(need[i][j] + " ");
            }
        }
    }
    
    public void displayUpdatedAvailable(){
        System.out.print("\n------------------");
        System.out.print("\nUPDATED AVAILABLE MATRIX\n");
        
        for(int j=0; j < resource_size; j++){
            System.out.print(updatedAvailable[j] + " ");
        }
        
    }
}
