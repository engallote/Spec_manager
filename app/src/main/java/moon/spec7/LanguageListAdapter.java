package moon.spec7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LanguageListAdapter extends BaseAdapter {
    private ArrayList<LanguageListItem> item = new ArrayList<>();
    public LanguageListAdapter(){}
    @Override
    public int getCount()
    {
        return item.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lanlist_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView date1 = (TextView) convertView.findViewById(R.id.date1);
        TextView date2 = (TextView) convertView.findViewById(R.id.date2);
        TextView number = (TextView) convertView.findViewById(R.id.number);
        TextView number2 = (TextView) convertView.findViewById(R.id.number2);
        TextView score = (TextView) convertView.findViewById(R.id.score);
        TextView agency = (TextView) convertView.findViewById(R.id.agency);
        TextView chk = (TextView) convertView.findViewById(R.id.chk);

        LanguageListItem languageListItem = item.get(position);

        name.setText(languageListItem.getName());
        date1.setText(languageListItem.getDate1());
        date2.setText(languageListItem.getDate2());
        number.setText(languageListItem.getNumber());
        number2.setText(languageListItem.getNumber2());
        score.setText(languageListItem.getScore());
        agency.setText(languageListItem.getAgency());
        chk.setText(languageListItem.getChk());
        return convertView;
    }
    @Override
    public long getItemId(int position){ return position; }
    @Override
    public Object getItem(int position)
    {
        return item.get(position);
    }
    public void addItem(String name, String date1, String date2, String score, String number, String number2, String agency, String chk)
    {
        LanguageListItem languageListItem = new LanguageListItem();
        languageListItem.setName(name);
        languageListItem.setDate1(date1);
        languageListItem.setDate2(date2);
        languageListItem.setScore(score);
        languageListItem.setNumber(number);
        languageListItem.setNumber2(number2);
        languageListItem.setAgency(agency);
        languageListItem.setChk(chk);
        item.add(languageListItem);
    }
    public void deleteItem(int position)
    {
        item.remove(position);
    }
    public void deleteAllItem()
    {
        item.clear();
    }
}
