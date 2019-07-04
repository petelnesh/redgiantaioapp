package com.keredgiantaio.techsavanna.redgiantaio.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.keredgiantaio.techsavanna.redgiantaio.R;
import com.keredgiantaio.techsavanna.redgiantaio.activities.SingleSweepActivity;
import com.keredgiantaio.techsavanna.redgiantaio.methods.Sweep;

import java.util.List;

public class SweepSearchAdapter extends  RecyclerView.Adapter<SweepSearchAdapter.SweepSearchHolder> {
    private List<Sweep> sweepList;
    private Context contexts;
    private LayoutInflater mInflater;
    public SweepSearchAdapter(List<Sweep> sweepList, Context context) {
        this.sweepList = sweepList;
        this.contexts = context;
    }

    @Override
    public SweepSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sweep, parent, false);

        return new SweepSearchHolder(view);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public void onBindViewHolder(SweepSearchHolder holder, final int position) {
        final Sweep structure = sweepList.get(position);
        System.out.println("Data detiles" + structure.getName());
        // holder.cardView.set;
        holder.name.setText(structure.getName());
        holder.startdate.setText(structure.getDate());
        holder.enddate.setText(structure.getClosed_on());

        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(contexts, SingleSweepActivity.class);

//                    intent.putExtra(STRUCTURE_ID, structure.getId());
//                    intent.putExtra(STRUCTURE_NAME, structure.getStructure_name());
//                    intent.putExtra(STRUCTURE_REGISTRATIONDATE, structure.getRegistrationdate());
//                    intent.putExtra(STRUCTURE_REGISTATIONTYPE, structure.getCounty());
//                    intent.putExtra(STRUCTURE_REGION, structure.getRegion());
//                    intent.putExtra(STRUCTURE_COUNTRY, structure.getCounty());
//                    intent.putExtra(STRUCTURE_TELEPHONE,structure.getTelephone());

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contexts.startActivity(intent);

                }
            }
        });



    }


    @Override
    public int getItemCount() {
        return sweepList.size();
    }

    public static class SweepSearchHolder extends RecyclerView.ViewHolder {
        TextView name, startdate, enddate;
        CheckBox confirm;

        CardView cardView;
        public SweepSearchHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            startdate = itemView.findViewById(R.id.startdate);
            enddate = itemView.findViewById(R.id.enddate);
            confirm = itemView.findViewById(R.id.confirm);
            cardView=itemView.findViewById(R.id.cardveiw);

        }
    }
}
