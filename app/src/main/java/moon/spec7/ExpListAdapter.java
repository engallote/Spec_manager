package moon.spec7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpListAdapter extends BaseAdapter {
    private ArrayList<ExpListItem> item = new ArrayList<>();
    public ExpListAdapter(){}
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
            convertView = inflater.inflate(R.layout.explist_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView content = (TextView) convertView.findViewById(R.id.content);

        ExpListItem expListItem = item.get(position);

        title.setText(expListItem.getTitle());
        date.setText(expListItem.getDate());
        content.setText(expListItem.getContent());

        return convertView;
    }
    @Override
    public long getItemId(int position){ return position; }
    @Override
    public Object getItem(int position)
    {
        return item.get(position);
    }
    public void addItem(String title, String date, String content)
    {
        ExpListItem expListItem = new ExpListItem();
        expListItem.setTitle(title);
        expListItem.setDate(date);
        expListItem.setContent(content);

        item.add(expListItem);
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
