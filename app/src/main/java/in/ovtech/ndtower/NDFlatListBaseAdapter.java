package in.ovtech.ndtower;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pranay on 26/4/15.
 */
public class NDFlatListBaseAdapter extends BaseAdapter implements Filterable {
    ArrayList<Flat> list= null;
    ArrayList<Flat> orig= null;
    final Context context;
    public NDFlatListBaseAdapter(ArrayList<Flat>  list,Context context){
        this.list=list;
        this.mInflater= LayoutInflater.from(context);
        this.context=context;
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
            holder.flatName=(TextView) createdview.findViewById(R.id.Flatname);
            holder.img=(ImageView) createdview.findViewById(R.id.profile_pic);
            createdview.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) createdview.getTag();
        }
        holder.name.setText(list.get(position).get_wing() + " "  + list.get(position).get_flatno());
        holder.flatid.setText(Integer.toString(list.get(position).get_id()));
        //holder.type.setText(list.get(position).get_hello());
        holder.img.setImageResource(R.mipmap.ic_launcher1);
        holder.flatName.setText(list.get(position).get_OwnerName());


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Intent intent= new Intent(context,ShowImage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("f",R.mipmap.image1);
                context.startActivity(intent);
                //Toast.makeText(context,"clicked",Toast.LENGTH_LONG).show();


                View layout = mInflater.inflate(R.layout.activity_add ,null);
               PopupWindow pwindo1 = new PopupWindow(layout, 750, 750, true);
                pwindo1.showAtLocation(layout, Gravity.CENTER, 0, 0);
*/

                final Dialog settingsDialog = new Dialog(context);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

                // settingsDialog.getWindow()
                settingsDialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.GRAY));
                settingsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        //nothing;

                    }
                });

                ImageView imageView = new ImageView(context);
                imageView.setMinimumWidth(100);
                imageView.setMinimumHeight(100);
                imageView.setImageResource(R.mipmap.ic_launcher1);
                settingsDialog.addContentView(imageView, new RelativeLayout.LayoutParams(500, 500));
                //ViewGroup.LayoutParams.MATCH_PARENT,
                // ViewGroup.LayoutParams.MATCH_PARENT));

                settingsDialog.show();


            }
        });

        return createdview;

    }
    static class ViewHolder {
        TextView name;
        TextView flatid;
        ImageView img;
        TextView flatName;

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
