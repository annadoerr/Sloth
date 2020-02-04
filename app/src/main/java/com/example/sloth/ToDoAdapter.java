package com.example.sloth;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{
    LayoutInflater inflater;
    List <ToDoItem> toDoItems;

    ToDoAdapter(Context context, List<ToDoItem> toDoItems) {
        this.inflater = LayoutInflater.from(context);
        this.toDoItems = toDoItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_to_do_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int checkBox = toDoItems.get(position).getChecked();
        String todo = toDoItems.get(position).getTodo();

        holder.checkBox.isChecked();
        holder.todo.setText(todo);
    }

    @Override
    public int getItemCount() {
        return toDoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        EditText todo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            todo = itemView.findViewById(R.id.todo);

            //Infos zu To-Do, auf das man gerade geklickt hat an MyTodo weitergeben
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),MyTodo.class);
                    intent.putExtra("ID",toDoItems.get(getAdapterPosition()).getID());
                    intent.putExtra("ToDoID",toDoItems.get(getAdapterPosition()).getTodo_id());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
