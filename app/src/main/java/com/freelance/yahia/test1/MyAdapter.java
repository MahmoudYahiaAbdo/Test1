package com.freelance.yahia.test1;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] itemsName;
    private String[] fabrics;
    private String[] colorsAvail;
    private String[] prices;
    private Boolean[] itemsStatus;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public RelativeLayout itemView;


        public ViewHolder(RelativeLayout relativeLayout) {
            super(relativeLayout);
            itemView = relativeLayout;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter( String[] itemsNamea, String[] fabricsa, String[] colorsAvaila, String[] pricesa, Boolean[] itemsStatusa) {

        itemsName = itemsNamea;
        fabrics = fabricsa;
        colorsAvail = colorsAvaila;
        prices = pricesa;
        itemsStatus = itemsStatusa;



    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.relativeview, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView itemName = holder.itemView.findViewById(R.id.itemNameValue);
        TextView fabric = holder.itemView.findViewById(R.id.fabricValue);
        TextView colorA = holder.itemView.findViewById(R.id.colorAvailabilityValue);
        TextView price = holder.itemView.findViewById(R.id.priceValue);
        Switch status = holder.itemView.findViewById(R.id.itemStatusValue);
        Button button = holder.itemView.findViewById(R.id.buttonx);
        itemName.setText(itemsName[position]);
        fabric.setText(fabrics[position]);
        colorA.setText(colorsAvail[position]);
        price.setText(prices[position]);
        status.setChecked(itemsStatus[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsName.length;
    }
}