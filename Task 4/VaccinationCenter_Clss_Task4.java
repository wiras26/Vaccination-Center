import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VaccinationCenter_Clss_Task4 {
    //Constant to store the Number of Vaccines
    static int NumVaccination = 150;
    //Create list of type LinkedList
    static LinkedList list = new LinkedList();

    public static void main(String[] args) {

        //Array initialised in type BoothNew
        BoothNew[] PatBooth = new BoothNew[6];
        boolean Exit = false;
        String Choice = "";
        Scanner input = new Scanner(System.in);

        //Calls method to make all array elements "Empty"
        Initialize(PatBooth);
        //Displays the console menu
        do {
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
    private static void Initialize(BoothNew[] PatBoothRef) {
        for (int i = 0; i < 6; i++) {
            PatBoothRef[i] = new BoothNew(i, "Empty");
            list.insertAt(i,"Empty");

        }
        System.out.println("Array Initialized");
    }

    //Method to view all six booths
    private static void ViewBooths(BoothNew[] PatBooth) {
        System.out.println(".....Displaying Booth.....");
        for (int i = 0; i < 6; i++) {
            System.out.println("Patient: " + PatBooth[i].PatientDetails + " is in Booth number " + i);
        }
        System.out.println("...............................");
    }

    //Method to view only the empty Booths
    private static void ViewEmpty(BoothNew[] PatBooth) {
        System.out.println("..Displaying Empty Booths..");
        for (int i = 0; i < 6; i++) {
            if (PatBooth[i].PatientDetails.equals("Empty")) {
                System.out.println("Booth " + i + " is Empty");
            }
        }
    }

    //Method to add new Patient to Booth
    private static void AddPatient(BoothNew[] PatBooth) {
        String PatientName;
        String Add = "y";
        Scanner input = new Scanner(System.in);
        while (Add.equals("y")) {
            int BoothNum = -1;

            System.out.println("Enter Patient's First Name");
            PatientName = input.next();
            Patient NewPatient = new Patient(PatientName);


            System.out.println("Enter Patient's Last Name");
            String lName = input.next();
            NewPatient.LName = lName;

            System.out.println("Enter Patient's Age");
            int age = input.nextInt();
            NewPatient.Age = age;

            System.out.println("Enter Patient's City");
            String city = input.next();
            NewPatient.City = city;

            System.out.println("Enter Patient's NIC");
            String nic = input.next();
            NewPatient.NIC = nic;

            System.out.println("Enter Type of Vaccination");
            String vacType = input.next();
            NewPatient.VacType = vacType;

            System.out.println("Patient First Name: " + NewPatient.FName + "\nPatient Last Name: " +
                    NewPatient.LName + "\nVaccination Type: " + NewPatient.VacType);
            System.out.println("____________________________________");

            String VacTypeRef = vacType.toLowerCase();

            //Checks which type of Vaccination the Patient wants and adds the patient to the relevant Booth location
            switch (VacTypeRef) {
                case ("astrazeneca"):
                    if (PatBooth[0].PatientDetails.equals("Empty")) {
                        BoothNum = 0;
                        break;
                    } else if (PatBooth[1].PatientDetails.equals("Empty")) {
                        BoothNum = 1;
                        break;
                    } else {
                        System.out.println("Both Booths Full");
                        list.insertAt(0,NewPatient.FName + " " + NewPatient.LName + "|" + NewPatient.Age + "|" + NewPatient.City + "|" + NewPatient.NIC + "|" + NewPatient.VacType);
                        list.insertAt(1,NewPatient.FName + " " + NewPatient.LName + "|" + NewPatient.Age + "|" + NewPatient.City + "|" + NewPatient.NIC + "|" + NewPatient.VacType);
                        break;
                    }
                case ("sinopharm"):
                    if (PatBooth[2].PatientDetails.equals("Empty")) {
                        BoothNum = 2;
                        break;
                    } else if (PatBooth[3].PatientDetails.equals("Empty")) {
                        BoothNum = 3;
                        break;
                    } else {
                        System.out.println("Both Booths Full");
                        list.insertAt(2,NewPatient.FName + " " + NewPatient.LName + "|" + NewPatient.Age + "|" + NewPatient.City + "|" + NewPatient.NIC + "|" + NewPatient.VacType);
                        list.insertAt(3,NewPatient.FName + " " + NewPatient.LName + "|" + NewPatient.Age + "|" + NewPatient.City + "|" + NewPatient.NIC + "|" + NewPatient.VacType);
                        break;
                    }
                case ("pfizer"):
                    if (PatBooth[4].PatientDetails.equals("Empty")) {
                        BoothNum = 4;
                        break;
                    } else if (PatBooth[5].PatientDetails.equals("Empty")) {
                        BoothNum = 5;
                        break;
                    } else {
                        System.out.println("Both Booths Full");
                        list.insertAt(4,NewPatient.FName + " " + NewPatient.LName + "|" + NewPatient.Age + "|" + NewPatient.City + "|" + NewPatient.NIC + "|" + NewPatient.VacType);
                        list.insertAt(5,NewPatient.FName + " " + NewPatient.LName + "|" + NewPatient.Age + "|" + NewPatient.City + "|" + NewPatient.NIC + "|" + NewPatient.VacType);
                        list.show();
                        break;
                    }
            }

            //If the Booth is Full Patients will be added to the Waiting List
            if (BoothNum == -1){
                System.out.println("Patient Added to Waiting List");
                list.show();
            }
            else{

                PatientName = NewPatient.FName + " " + NewPatient.LName + "|" + NewPatient.Age + "|" + NewPatient.City + "|" + NewPatient.NIC + "|" + NewPatient.VacType;
                PatBooth[BoothNum].Patient = NewPatient;
                PatBooth[BoothNum].PatientDetails = PatientName;
                System.out.println("Booth Number: " + BoothNum + "\nPatient First Name: " + NewPatient.FName + "\nPatient Last Name: " +
                        NewPatient.LName + "\nVaccination Type: " + NewPatient.VacType);

                System.out.println("____________________________________");

                NumVaccination = NumVaccination - 1;
                if (NumVaccination<20){
                    System.out.println("Vaccines needs to be Ordered!");
                    System.out.println(".....................................................");
                }
            }

            //Check whether user wants to add more patients
            System.out.println("Do you wish to add more Patients? (y/n)");
            Add = input.next();
        }
    }

    //Method to remove patient from Booth
    private static void RemovePatient(BoothNew[] PatBooth){
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
            PatBooth[BoothNum] = new BoothNew(BoothNum,"Empty");
            System.out.println("Patient "+PatientName+" removed from Booth");

            //Gets the relevant Patient From the Waiting List
            String ListPatient =list.get(BoothNum);
            //Deletes the Patient from the Waiting List
            list.deleteNode(BoothNum);

            //Patient Added to the relevant Booth Location
            PatBooth[BoothNum] = new BoothNew(BoothNum,ListPatient);

            System.out.println(ListPatient+" was added from Waiting List to Booth");
            System.out.println(".....................................");

            //Test Case to check whether Patient has been moved from waiting list to Booth
            //list.show();
        }
    }

    //Method to sort Booth according to the Alphabetical order of patient names ref: https://beginnersbook.com/2019/04/java-program-to-perform-bubble-sort-on-strings/
    private static void SortPatients(BoothNew[] PatBooth){
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

    //Writes data from Booth to Text File  ref: https://www.w3schools.com/java/java_files_create.asp
    private static void WriteFile(BoothNew[] PatBooth){
        CreateFile();
        try{
            FileWriter tfWrite = new FileWriter("Vaccination.txt");
            for (BoothNew booth : PatBooth) {
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