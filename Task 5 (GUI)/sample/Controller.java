package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    @FXML
    private TextField FName,LName,Age,City,NIC;

    @FXML
    private RadioButton Radio1,Radio2,Radio3;
    @FXML
    private RadioButton Booth0,Booth1,Booth2,Booth3,Booth4,Booth5;

    @FXML
    public void PrintReceipt() {
        //Ref: https://javacodex.com/Date-and-Time/Add-Time-To-A-Timestamp
        //To show the data and time in the Receipt
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());

        //Initialize 'a' as an Alert Type
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String radio = "";
        String radio1 = "";

        //Gets the user's text inputs from the Text Fields
        String fname = FName.getText();
        String lname = LName.getText();
        String age = Age.getText();
        String city = City.getText();
        String nic = NIC.getText();

        a.setTitle("Receipt");
        a.setHeaderText("Summary");

        //Check which of the Vaccination types the User has Selected
        if (Radio1.isSelected()){
            radio = Radio1.getText();
        }
        else if (Radio2.isSelected()){
            radio = Radio2.getText();
        }
        else if (Radio3.isSelected()){
            radio = Radio3.getText();
        }

        //Check which booth number has been selected
        if (Booth0.isSelected()){
            radio1 = Booth0.getText();
        }
        else if (Booth1.isSelected()){
            radio1 = Booth1.getText();
        }
        else if (Booth2.isSelected()){
            radio1 = Booth2.getText();
        }
        else if (Booth3.isSelected()){
            radio1 = Booth3.getText();
        }
        else if (Booth4.isSelected()){
            radio1 = Booth4.getText();
        }
        else if (Booth5.isSelected()){
            radio1 = Booth5.getText();
        }

        //Displays the details as a summary in an alert box
        a.setContentText("First Name: " + fname + "\nLast Name: " + lname + "\nAge: " + age + "\nCity: " + city +
                        "\nNIC: " + nic + "\nVaccination Type: " + radio +"\nBooth Number: "+radio1+"\n Time: " + timestamp);
        a.showAndWait();
    }
}
