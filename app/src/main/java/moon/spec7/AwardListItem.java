package moon.spec7;

public class AwardListItem {
    private String name, date, awardname, agency, content;
    public void setName(String name2)
    {
        name = name2;
    }
    public void setDate(String date2)
    {
        date = date2;
    }
    public void setAwardname(String awardname2)
    {
        awardname = awardname2;
    }
    public void setAgency(String agency2)
    {
        agency = agency2;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getName(){
        return this.name;
    }

    public String getAwardname() {
        return awardname;
    }

    public String getDate() {
        return date;
    }

    public String getAgency() {
        return agency;
    }
}
