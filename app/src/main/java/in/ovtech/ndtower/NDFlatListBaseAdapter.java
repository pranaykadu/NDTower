package in.ovtech.ndtower;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pranay on 26/4/15.
 */
public class NDFlatListBaseAdapter extends BaseAdapter implements Filterable {
    ArrayList<Flat> list= null;
    ArrayList<Flat> orig= null;

    public NDFlatListBaseAdapter(ArrayList<Flat>  list,Context context){
        this.list=list;
        this.mInflater= LayoutInflater.from(context);

    }
    private LayoutInflater mInflater;
    @Override
    public int getCount(){
        return list.size();
    }
    @Override
    public Flat getItem(int position){
        return list.get(position);
    }
    @Override
    public long getItemId(int position){

        return position;
    }
    @Override
    public View getView(int position,View createdview, final ViewGroup vg){
        ViewHolder holder;
        if (createdview==null){
           createdview = mInflater.inflate(R.layout.adaptor_layout,null);
            holder = new ViewHolder();
            holder.name=(TextView) createdview.findViewById(R.id.name);
             holder.flatid=(TextView) createdview.findViewById(R.id.cid);
            //holder.type=(TextView) createdview.findViewById(R.id.btype);
            createdview.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) createdview.getTag();
        }
        holder.name.setText(list.get(position).get_wing() + " "  + list.get(position).get_flatno());
        holder.flatid.setText(Integer.toString(list.get(position).get_id()));
        //holder.type.setText(list.get(position).get_hello());
        return createdview;

    }
    static class ViewHolder {
        TextView name;
        TextView flatid;
        //TextView rate;
        //TextView type;

    }
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Flat> results = new ArrayList();
                if (orig == null)
                    orig = list;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Flat g : orig) {
                            if (g.get_flatno().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                    oReturn.count = results.size();
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                list = (ArrayList<Flat>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
