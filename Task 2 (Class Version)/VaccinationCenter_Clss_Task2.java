import java.util.*;
import java.io.*;
public class VaccinationCenter_Clss_Task2 {
    //Constant to store the Number of Vaccines
    static int NumVaccination = 150;

    public static void main(String[] args) {

        //Array initialised in type Booth
        Booth[] PatBooth = new Booth[6];
        boolean Exit=false;
        String Choice = "";
        Scanner input = new Scanner(System.in);

        //Calls method to make all array elements "Empty"
        Initialize(PatBooth);
        //Displays the console menu
        do{
            System.out.println("""
                    100 or VVB: View all Vaccination Booths
                    101 or VEB: View all Empty Booths
                    102 or APB: Add Patient to a Booth
                    103 or RPB: Remove Patient from a Booth
                    104 or VPS: View Patients Sorted in alphabetical order
                    105 or SPD: Store Program Data into file
                    106 or LPD: Load Program Data from file
                    107 or VRV: View Remaining Vaccinations
                    108 or AVS: Add Vaccinations to the Stock
                    999 or EXT: Exit the Program""");
            System.out.println(".....................................................");

            System.out.println("Enter you choice");
            Choice = input.next();
            //Gets users choice can compare and calls the relevant method
            switch (Choice) {
                case ("100"), ("VVB") -> ViewBooths(PatBooth);
                case ("101"), ("VEB") -> ViewEmpty(PatBooth);
                case ("102"), ("APB") -> AddPatient(PatBooth);
                case ("103"), ("RPB") -> RemovePatient(PatBooth);
                case ("104"), ("VPS") -> SortPatients(PatBooth);
                case ("105"), ("SPD") -> WriteFile(PatBooth);
                case ("106"), ("LPD") -> ReadFile();
                case ("107"), ("VRV") -> ViewRemaining();
                case ("108"), ("AVS") -> AddStock();
                case ("999"), ("EXT") -> Exit = true;
            }
            //Exits when the user enters 999 or EXT
        } while (!Exit);
    }

    //Method to initialize the array
    private static void Initialize(Booth[] PatBoothRef) {
        for (int i = 0; i <6;i++){
            PatBoothRef[i] = new Booth( i,"Empty");
        }
        System.out.println("Array Initialized\n");
    }

    //Method to view all six booths
    private static void ViewBooths(Booth[] PatBooth){
        System.out.println(".....Displaying Booth.....");
        for (int i = 0; i <6;i++){
            System.out.println("Patient: "+PatBooth[i].PatientDetails+" is in Booth number "+i);
        }
    }

    //Method to view only the empty Booths
    private static void ViewEmpty(Booth[] PatBooth){
        System.out.println("..Displaying Empty Booths..");
        for (int i = 0; i<6;i++){
            if (PatBooth[i].PatientDetails.equals("Empty")){
                System.out.println("Booth"+i+"is Empty");
            }
        }
    }

    //Method to add new Patient to Booth
    private static void AddPatient(Booth[] PatBooth) {
        String PatientName;
        int BoothNum;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Booth Number (0-5)");
        BoothNum = input.nextInt();

        System.out.println("Enter Patient Name");
        PatientName = input.next();

        PatBooth[BoothNum].PatientDetails = PatientName;

        System.out.println("Patient Added Successfully!");
        System.out.println(".....................................................");

        NumVaccination = NumVaccination -1;
        if (NumVaccination<20){
            System.out.println("Vaccines needs to be Ordered!");
            System.out.println(".....................................................");
        }
    }

    //Method to remove patient from Booth
    private static void RemovePatient(Booth[] PatBooth){
        int BoothNum;
        String PatientName;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Booth Number to Remove: ");
        BoothNum = input.nextInt();
        PatientName = PatBooth[BoothNum].PatientDetails;
        if (PatientName.equals("Empty")){
            System.out.println("Booth already empty");
        }
        else{
            PatBooth[BoothNum].PatientDetails = "Empty";
            System.out.println("Patient "+PatientName+" removed from Booth");
        }
    }

    //Method to sort Booth according to the Alphabetical order of patient names ref: https://beginnersbook.com/2019/04/java-program-to-perform-bubble-sort-on-strings/
    private static void SortPatients(Booth[] PatBooth){
        String temp;
        for (int i = 0; i <PatBooth.length; i++){
            for (int j = i+1; j<PatBooth.length; j++){
                if (PatBooth[j].PatientDetails.compareTo(PatBooth[i].PatientDetails)<0){
                    temp = PatBooth[i].PatientDetails;
                    PatBooth[i].PatientDetails = PatBooth[j].PatientDetails;
                    PatBooth[j].PatientDetails = temp;
                }
            }
            System.out.println("Patient: "+PatBooth[i].PatientDetails+" is in Booth number "+i);
        }
    }

    //Deletes any existing file ref: https://www.w3schools.com/java/java_files_delete.asp
    private static void DeleteFile(){
        File tf = new File("Vaccination.txt");
        tf.delete();
    }

    //Creates a new Text File called Vaccination.txt ref: https://www.w3schools.com/java/java_files_create.asp
    private static void CreateFile(){
        DeleteFile();
        try{
            File tf = new File("Vaccination.txt");
            if (tf.createNewFile()){
                System.out.println("File created: "+tf.getName());
            }
            else{
                System.out.println("File already exists");
            }
        }
        catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    //Writes data from Booth to Text File ref: https://www.w3schools.com/java/java_files_create.asp
    private static void WriteFile(Booth[] PatBooth){
        CreateFile();
        try{
            FileWriter tfWrite = new FileWriter("Vaccination.txt");
            for (Booth booth : PatBooth) {
                if (!booth.PatientDetails.equals("Empty")) {
                    tfWrite.write(booth.PatientDetails + "\n");
                }
            }
            tfWrite.close();
            System.out.println("Successfully wrote to the file");
            System.out.println("_______________________________");
        }
        catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    //Reads from the saved Text File ref: https://www.w3schools.com/java/java_files_read.asp
    private static void ReadFile(){

        try{
            File tr = new File("Vaccination.txt");
            Scanner myReader = new Scanner(tr);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }
        catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    //Displays the remaining Vaccines from stock
    private static void ViewRemaining(){
        if (NumVaccination<=20){
            System.out.println("Vaccines need to be Ordered!");
        }
        System.out.println("Number of remaining Vaccines: "+ NumVaccination);
    }

    //Method to Add more vaccines to Stock
    private static void AddStock(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of Vaccines to be added: ");
        int Num = input.nextInt();
        NumVaccination+=Num;
        System.out.println("Updated Stock: "+NumVaccination);
    }
}
