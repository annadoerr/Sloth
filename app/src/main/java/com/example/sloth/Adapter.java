package com.example.sloth;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// Adapts text from ToDoActivity to List Item layout
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<ListItem> items;

    Adapter(Context context, List<ListItem> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = items.get(position).gettitle();
        String date = items.get(position).getDate();
        String time = items.get(position).getTime();
        String checkedNumber = items.get(position).getCheckedNumber();

        holder.title.setText(title);
        holder.dateView.setText(date);
        holder.timeView.setText(time);
        holder.checkedNumber.setText(checkedNumber);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, dateView, timeView, checkedNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            dateView = itemView.findViewById(R.id.dateView);
            timeView = itemView.findViewById(R.id.timeView);
            checkedNumber = itemView.findViewById(R.id.checkedNumber);

            // Get ID of clicked item and open details of the clicked item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MyTodo.class);
                    intent.putExtra("ID", items.get(getAdapterPosition()).getID());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
