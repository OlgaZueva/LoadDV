package TietoRus.CountsTests;

public class zzz {
    java.util.ArrayList<String> futureMonths = new java.util.ArrayList<String>();

    int month = 8;

switch (month) {
        case 1:
            futureMonths.add("January");
        case 2:
            futureMonths.add("February");
        case 3:
            futureMonths.add("March");
        case 4:
            futureMonths.add("April");
        case 5:
            futureMonths.add("May");
        case 6:
            futureMonths.add("June");
        case 7:
            futureMonths.add("July");
        case 8:
            futureMonths.add("August");
        case 9:
            futureMonths.add("September");
        case 10:
            futureMonths.add("October");
        case 11:
            futureMonths.add("November");
        case 12:
            futureMonths.add("December");
            break;
        default:
            break;
    }

if (futureMonths.isEmpty()) {
        mInfoTextView.setText("Invalid month number");
    } else {
        for (String monthName : futureMonths) {
            mInfoTextView.setText(monthName);
        }
    }
}
