package moon.spec7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AwardListAdapter extends BaseAdapter {
    private ArrayList<AwardListItem> item = new ArrayList<>();
    public AwardListAdapter(){}
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
            convertView = inflater.inflate(R.layout.awardlist_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView awardname = (TextView) convertView.findViewById(R.id.awardname);
        TextView agency = (TextView) convertView.findViewById(R.id.agency);
        TextView content = (TextView) convertView.findViewById(R.id.content);

        AwardListItem awardListItem = item.get(position);

        name.setText(awardListItem.getName());
        date.setText(awardListItem.getDate());
        awardname.setText(awardListItem.getAwardname());
        agency.setText(awardListItem.getAgency());
        content.setText(awardListItem.getContent());

        return convertView;
    }
    @Override
    public long getItemId(int position){ return position; }
    @Override
    public Object getItem(int position)
    {
        return item.get(position);
    }
    public void addItem(String name, String date, String awardName, String agency, String content)
    {
        AwardListItem awardListItem = new AwardListItem();
        awardListItem.setName(name);
        awardListItem.setDate(date);
        awardListItem.setAwardname(awardName);
        awardListItem.setAgency(agency);
        awardListItem.setContent(content);

        item.add(awardListItem);
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
