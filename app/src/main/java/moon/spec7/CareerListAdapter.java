package moon.spec7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CareerListAdapter extends BaseAdapter {
    private ArrayList<CareerListItem> item = new ArrayList<>();
    public CareerListAdapter(){}
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
            convertView = inflater.inflate(R.layout.careerlist_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView date1 = (TextView) convertView.findViewById(R.id.date1);
        TextView date2 = (TextView) convertView.findViewById(R.id.date2);
        TextView form = (TextView) convertView.findViewById(R.id.form);
        TextView position1 = (TextView) convertView.findViewById(R.id.position1);
        TextView position2 = (TextView) convertView.findViewById(R.id.position2);
        TextView adress = (TextView) convertView.findViewById(R.id.adress);
        TextView task = (TextView) convertView.findViewById(R.id.task);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        TextView etc = (TextView) convertView.findViewById(R.id.etc);

        CareerListItem careerListItem = item.get(position);

        name.setText(careerListItem.getName());
        date1.setText(careerListItem.getDate1());
        status.setText(careerListItem.getStatus());
        task.setText(careerListItem.getTask());
        date2.setText(careerListItem.getDate2());
        etc.setText(careerListItem.getEtc());
        form.setText(careerListItem.getForm());
        position1.setText(careerListItem.getPosition1());
        position2.setText(careerListItem.getPosition2());
        adress.setText(careerListItem.getAdress());
        return convertView;
    }
    @Override
    public long getItemId(int position){ return position; }
    @Override
    public Object getItem(int position)
    {
        return item.get(position);
    }
    public void addItem(String name, String adress, String status, String form, String position1, String position2, String task, String date1, String date2, String etc)
    {
        CareerListItem careerListItem = new CareerListItem();
        careerListItem.setName(name);
        careerListItem.setDate1(date1);
        careerListItem.setDate2(date2);
        careerListItem.setForm(form);
        careerListItem.setEtc(etc);
        careerListItem.setStatus(status);
        careerListItem.setTask(task);
        careerListItem.setPosition1(position1);
        careerListItem.setPosition2(position2);
        careerListItem.setAdress(adress);

        item.add(careerListItem);
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
