package com.example.myannuairecontact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private  List<Contact> contacts ;
    ItemClicked activity ;

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public ContactAdapter(Context context , List<Contact> list){
        contacts=list ;
        activity=(ItemClicked) context;
    }

   public class ViewHolder extends RecyclerView.ViewHolder{
       private  TextView nameItemView;
       private  TextView numberItemView;
       private  TextView emailItemView;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           nameItemView = itemView.findViewById(R.id.tvName);
           numberItemView = itemView.findViewById(R.id.tvNumber);
           emailItemView = itemView.findViewById(R.id.tvEmail);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   activity.onItemClicked(contacts.indexOf((Contact) view.getTag()));

               }
           });
       }
   }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item ,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(contacts.get(position));
        holder.nameItemView.setText(contacts.get(position).getName());
        holder.emailItemView.setText(contacts.get(position).getEmail());
        holder.numberItemView.setText(contacts.get(position).getNumber());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context) activity).setTitle("Delete Contact")
                        .setMessage("Are you sure ?")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                contacts.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
