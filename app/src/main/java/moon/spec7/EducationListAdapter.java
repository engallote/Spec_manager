package moon.spec7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EducationListAdapter extends BaseAdapter {
    private ArrayList<EducationListItem> item = new ArrayList<>();
    public EducationListAdapter(){}
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
            convertView = inflater.inflate(R.layout.educationlist_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView area = (TextView) convertView.findViewById(R.id.area);
        TextView school = (TextView) convertView.findViewById(R.id.school);
        TextView major = (TextView) convertView.findViewById(R.id.major);
        TextView submajor = (TextView) convertView.findViewById(R.id.submajor);
        TextView majorscore = (TextView) convertView.findViewById(R.id.majorscore);
        TextView majorgrade = (TextView) convertView.findViewById(R.id.majorgrade);
        TextView grade = (TextView) convertView.findViewById(R.id.grade);
        TextView score = (TextView) convertView.findViewById(R.id.score);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        TextView etc = (TextView) convertView.findViewById(R.id.etc);
        TextView date1 = (TextView) convertView.findViewById(R.id.date1);
        TextView date2 = (TextView) convertView.findViewById(R.id.date2);

        EducationListItem educationListItem = item.get(position);

        name.setText(educationListItem.getName());
        time.setText(educationListItem.getTime());
        area.setText(educationListItem.getArea());
        school.setText(educationListItem.getSchool());
        major.setText(educationListItem.getMajor());
        submajor.setText(educationListItem.getSubmajor());
        majorscore.setText(educationListItem.getMajorscore());
        score.setText(educationListItem.getScore());
        date1.setText(educationListItem.getDate1());
        date2.setText(educationListItem.getDate2());
        majorgrade.setText(educationListItem.getMajorgrade());
        grade.setText(educationListItem.getGrade());
        status.setText(educationListItem.getStatus());
        etc.setText(educationListItem.getEtc());
        return convertView;
    }
    @Override
    public long getItemId(int position){ return position; }
    @Override
    public Object getItem(int position)
    {
        return item.get(position);
    }
    public void addItem(String name, String time, String school, String area, String major, String submajor, String majorscore, String score, String majorgrade, String grade, String status, String date1, String date2, String etc)
    {
        EducationListItem educationListItem = new EducationListItem();
        educationListItem.setName(name);
        educationListItem.setTime(time);
        educationListItem.setSchool(school);
        educationListItem.setArea(area);
        educationListItem.setMajor(major);
        educationListItem.setSubmajor(submajor);
        educationListItem.setMajorscore(majorscore);
        educationListItem.setScore(score);
        educationListItem.setDate1(date1);
        educationListItem.setDate2(date2);
        educationListItem.setStatus(status);
        educationListItem.setMajorgrade(majorgrade);
        educationListItem.setGrade(grade);
        educationListItem.setEtc(etc);

        item.add(educationListItem);
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
