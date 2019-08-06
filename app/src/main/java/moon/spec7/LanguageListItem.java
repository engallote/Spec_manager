package moon.spec7;

public class LanguageListItem {
    private String name, date1, date2, number, number2, agency, score, chk;

    public void setChk(String chk) {
        this.chk = chk;
    }

    public void setName(String name1)
    {
        name = name1;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public void setDate1(String date11)
    {
        date1 = date11;
    }

    public void setDate2(String date22)
    {
        date2 = date22;
    }

    public void setNumber(String number1)
    {
        number = number1;
    }

    public void setAgency(String agency1)
    {
        agency = agency1;
    }

    public void setScore(String score1)
    {
        score = score1;
    }

    public String getChk() {
        return chk;
    }

    public String getAgency() {
        return agency;
    }

    public String getNumber2() {
        return number2;
    }

    public String getName() {
        return name;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

    public String getNumber() {
        return number;
    }

    public String getScore() {
        return score;
    }
}
