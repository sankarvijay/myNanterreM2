package miage.parisnanterre.fr.mynanterre.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.implem.Sport;

/**
 * Created by Sankar Vijay on 26/01/2019.
 */
public class SportGridAdapter extends BaseAdapter {

    private List<Sport> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public SportGridAdapter(Context aContext, List<Sport> listData) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.logo = (ImageView) convertView.findViewById(R.id.image);
            holder.sportsName = (TextView) convertView.findViewById(R.id.texte);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Sport sport = this.listData.get(position);
        holder.sportsName.setText(sport.getTexte());

        int imageId = this.getMipmapResIdByName(String.valueOf(sport.getImage()));

        holder.logo.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    private int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();

        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    static class ViewHolder {
        ImageView logo;
        TextView sportsName;
    }

}