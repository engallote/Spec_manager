package moon.spec7;

public class CertificationListItem {
    private String name, date, number, agency;
    public void setName(String name2)
    {
        name = name2;
    }
    public void setDate(String date2)
    {
        date = date2;
    }
    public void setNumber(String number2)
    {
        number = number2;
    }
    public void setAgency(String agency2)
    {
        agency = agency2;
    }
    public String getName(){
        return this.name;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getAgency() {
        return agency;
    }
}
