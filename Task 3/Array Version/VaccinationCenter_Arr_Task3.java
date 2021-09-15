import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class VaccinationCenter_Arr_Task3 {
    //Constant to store the Number of Vaccines
    static int NumVaccination = 150;

    public static void main(String[] args) {

        //Array which stores Patient First Name
        String[] PatBooth = new String[6];
        //Array which stores Patient Last Name
        String[] LName = new String[6];
        //Array which stores Patient's Vaccination Type
        String[] VacType = new String[6];
        boolean Exit=false;
        Scanner input = new Scanner(System.in);
        String Choice;

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

            switch (Choice) {
                case ("100"), ("VVB") -> ViewBooths(PatBooth);
                case ("101"), ("VEB") -> ViewEmpty(PatBooth);
                case ("102"), ("APB") -> AddPatient(PatBooth, LName, VacType);
                case ("103"), ("RPB") -> RemovePatient(PatBooth);
                case ("104"), ("VPS") -> SortPatients(PatBooth);
                case ("105"), ("SPD") -> WriteFile(PatBooth, LName, VacType);
                case ("106"), ("LPD") -> ReadFile();
                case ("107"), ("VRV") -> ViewRemaining();
                case ("108"), ("AVS") -> AddStock();
                case ("999"), ("EXT") -> Exit = true;
            }
            //Exits when the user enters 999 or EXT
        } while (!Exit);
    }

    //Method to initialize the array
    private static void Initialize(String[] PatBoothRef){
        for (int i=0;i<6;i++) PatBoothRef[i] = "Empty";
        System.out.println("Array Initialize\n");
    }

    //Method to view all six booths
    private static void ViewBooths(String[] PatBooth){
        System.out.println(".....Displaying Booth.....");
        for (int i = 0; i <6;i++){
            System.out.println(i+":"+PatBooth[i]);
        }
    }

    //Method to view only the empty Booths
    private static void ViewEmpty(String[] PatBooth){
        System.out.println("..Displaying Empty Booths..");
        for (int i = 0; i<6;i++){
            if (PatBooth[i].equals("Empty")){
                System.out.println("Booth"+i+"is Empty");
            }
        }
    }

    //Method to add new Patient to Booth
    private static void AddPatient(String[] PatBooth, String[] LName, String[] VacType) {
        String PatientName;
        int BoothNum = 0;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i <2;i++) {

            System.out.println("Enter Patient's First Name");
            PatientName = input.next();


            System.out.println("Enter Patient's Last Name");
            String lName = input.next();

            System.out.println("Enter Type of Vaccinations");
            String vacType = input.next();

            String VacTypeRef =  vacType.toLowerCase();

            //Check which type of Vaccination the Patients wants and add to the relevant Booth location
            switch(VacTypeRef){
                case ("astrazeneca"):
                    if (PatBooth[0].equals("Empty")) {
                        BoothNum = 0;
                        break;
                    } else if (PatBooth[1].equals("Empty")) {
                        BoothNum = 1;
                        break;
                    } else {
                        System.out.println("Both Booths Full");
                        break;
                    }
                case ("sinopharm"):
                    if (PatBooth[2].equals("Empty")) {
                        BoothNum = 2;
                        break;
                    } else if (PatBooth[3].equals("Empty")){
                        BoothNum = 3;
                        break;
                    }else{
                        System.out.println("Both Booths Full");
                        break;
                    }
                case ("pfizer"):
                    if (PatBooth[4].equals("Empty")){
                        BoothNum = 4;
                        break;
                    }else if (PatBooth[5].equals("Empty")){
                        BoothNum = 5;
                        break;
                    }else{
                        System.out.println("Both Booths Full");
                        break;
                    }
            }

            PatBooth[BoothNum] = PatientName+"||"+lName+"||"+vacType;
            LName[BoothNum] = lName;
            VacType[BoothNum] = vacType;

            System.out.println("Booth Number: " + BoothNum + "\nPatient First Name: " + PatBooth[BoothNum] + "\nPatient Last Name: " +
                    LName[BoothNum] + "\nVaccination Type: " + VacType[BoothNum]);
            System.out.println("____________________________________");

            NumVaccination = NumVaccination - 1;
            if (NumVaccination<20){
                System.out.println("Vaccines needs to be Ordered!");
                System.out.println(".....................................................");
            }
        }
    }

    //Method to remove patient from Booth
    private static void RemovePatient(String[] PatBooth){
        int BoothNum;
        String PatientName;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Booth Number to Remove: ");
        BoothNum = input.nextInt();
        PatientName = PatBooth[BoothNum];

        if (PatientName.equals("Empty")){
            System.out.println("Booth already empty");
        }
        else{
            PatBooth[BoothNum] = "Empty";
            System.out.println("Patient "+PatientName+" removed from Booth");
        }
    }


    //Method to sort Booth according to the Alphabetical order of patient names ref: https://beginnersbook.com/2019/04/java-program-to-perform-bubble-sort-on-strings/
    private static void SortPatients(String[] PatBooth){
        String temp;
        for (int i = 0; i <PatBooth.length; i++){
            for (int j = i+1; j<PatBooth.length; j++){
                if (PatBooth[j].compareTo(PatBooth[i])<0){
                    temp = PatBooth[i];
                    PatBooth[i] = PatBooth[j];
                    PatBooth[j] = temp;
                }
            }
            System.out.println(PatBooth[i]);
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
    private static void WriteFile(String[] PatBooth, String[] LName, String[] VacType){
        CreateFile();
        try{
            FileWriter tfWrite = new FileWriter("Vaccination.txt");
            for (String s : PatBooth) {
                if (!s.equals("Empty")) {
                    tfWrite.write(s + "\n");
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
