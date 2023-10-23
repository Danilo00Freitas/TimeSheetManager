package TimeSheetApp.FrontEnd.TsMgmtScreens;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntryValidator {
    public EntryValidator(){

    }
    public boolean isValidTime(String time) {
        // Crie um formato SimpleDateFormat para o padrão hh:mm:ss
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setLenient(false); // Impede conversões flexíveis

        try {
            Date parsedTime = timeFormat.parse(time);
            return time.equals(timeFormat.format(parsedTime));
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean validateTimeEntrys(String arrival, String brk, String brkReturn, String exit) {
        if (!isValidTime(arrival) || !isValidTime(brk) || !isValidTime(brkReturn) || !isValidTime(exit)) {
            return false;
        }

        return true;
    }
}


