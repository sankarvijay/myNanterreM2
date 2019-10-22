package miage.parisnanterre.fr.mynanterre.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.implem.Crous;
import miage.parisnanterre.fr.mynanterre.implem.ListeProduit;


public class CrousGridAdapter extends BaseAdapter {

    private List<Crous> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    public CrousGridAdapter(Context aContext, List<Crous> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CrousGridAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_batiment, null);
            holder = new CrousGridAdapter.ViewHolder();
            holder.batiment = (TextView) convertView.findViewById(R.id.batiment);
            holder.lieu = (TextView) convertView.findViewById(R.id.lieu);
            holder.sandwich = (ImageView) convertView.findViewById(R.id.buttonSand);
            holder.vote = (TextView) convertView.findViewById(R.id.vote);
            convertView.setTag(holder);
        } else {
            holder = (CrousGridAdapter.ViewHolder) convertView.getTag();
        }

        Crous crous = this.listData.get(position);
        holder.batiment.setText(crous.getBatiment());
        holder.lieu.setText(crous.getLieu());
        holder.vote.setText(crous.getVote());

        if (crous.getFrequentation() == 1) {
            convertView.setBackgroundColor(Color.rgb(147, 194, 6));
        } else if (crous.getFrequentation() == 2) {
            convertView.setBackgroundColor(Color.rgb(242, 178, 55));
        } else {
            convertView.setBackgroundColor(Color.rgb(191, 10, 1));
        }


        holder.sandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context.getApplicationContext(), ListeProduit.class);
                Bundle extras = new Bundle();
                extras.putString(EXTRA_MESSAGE, String.valueOf(crous.getId()));
                myIntent.putExtras(extras);
                context.startActivity(myIntent);
            }
        });
        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();

        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    private static class ViewHolder {
        private TextView batiment;
        private TextView lieu;
        private ImageView sandwich;
        private TextView vote;
    }

}