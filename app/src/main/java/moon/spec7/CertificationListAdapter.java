package moon.spec7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CertificationListAdapter extends BaseAdapter {
    private ArrayList<CertificationListItem> item = new ArrayList<>();
    public CertificationListAdapter(){}
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
            convertView = inflater.inflate(R.layout.certificationlist_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView number = (TextView) convertView.findViewById(R.id.number);
        TextView agency = (TextView) convertView.findViewById(R.id.agency);

        CertificationListItem certificationListItem = item.get(position);

        name.setText(certificationListItem.getName());
        date.setText(certificationListItem.getDate());
        number.setText(certificationListItem.getNumber());
        agency.setText(certificationListItem.getAgency());

        return convertView;
    }
    @Override
    public long getItemId(int position){ return position; }
    @Override
    public Object getItem(int position)
    {
        return item.get(position);
    }
    public void addItem(String name, String date, String number, String agency)
    {
        CertificationListItem certificationListItem = new CertificationListItem();
        certificationListItem.setName(name);
        certificationListItem.setDate(date);
        certificationListItem.setNumber(number);
        certificationListItem.setAgency(agency);

        item.add(certificationListItem);
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
