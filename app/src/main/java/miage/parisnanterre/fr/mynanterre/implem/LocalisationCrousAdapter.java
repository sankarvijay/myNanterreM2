package miage.parisnanterre.fr.mynanterre.implem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import miage.parisnanterre.fr.mynanterre.R;

public class LocalisationCrousAdapter extends RecyclerView.Adapter<LocalisationCrousAdapter.MyViewHolder> {

    private List<LocalisationCafet> cafets;

    // constructor
    public LocalisationCrousAdapter(List<LocalisationCafet> films){
        this.cafets = films;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // inflate item_layout
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.localisationcafetitem, null);

        MyViewHolder vh = new MyViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.image.setImageResource(cafets.get(position).getImage());
        holder.nom.setText(cafets.get(position).getNom());
        holder.distance.setText(cafets.get(position).getDistance());
        holder.info.setText(cafets.get(position).getInfo());
        holder.plat.setText(cafets.get(position).getPlat());


    }

    @Override
    public int getItemCount() {
        if(cafets != null)
            return cafets.size();
        else
            return 0;
    }

    // inner static class
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView nom;
        public TextView distance;
        public TextView info;
        public TextView plat;


        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            image = itemLayoutView.findViewById(R.id.imageCafet);
            nom = itemLayoutView.findViewById(R.id.NomCafet);
            distance = itemLayoutView.findViewById(R.id.DistanceCafet);
            info = itemLayoutView.findViewById(R.id.infoCafet);
            plat = itemLayoutView.findViewById(R.id.plat);
        }
    }
}
