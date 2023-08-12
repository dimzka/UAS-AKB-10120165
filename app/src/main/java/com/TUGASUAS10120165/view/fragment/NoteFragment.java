package com.TUGASUAS10120165.view.fragment;
// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.TUGASUAS10120165.NoteInterface;
import com.TUGASUAS10120165.R;
import com.TUGASUAS10120165.adapter.NoteAdapter;
import com.TUGASUAS10120165.database.DatabaseHelper;
import com.TUGASUAS10120165.model.Note;
import com.TUGASUAS10120165.view.activity.AddNoteActivity;
import com.TUGASUAS10120165.view.activity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment  {

    private MainActivity mainActivity;
    private List<Note> notes;
    private DatabaseReference database;
    private RecyclerView recyclerView;
    private com.TUGASUAS10120165.adapter.NoteAdapter noteAdapter;
    private FloatingActionButton addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().hide();

        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mynote);
        addButton = view.findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddNoteActivity.class));
        });
        database = FirebaseDatabase.getInstance().getReference().child("Notes").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        read();
    }

    @Override
    public void onResume() {
        super.onResume();
        read();
    }

    private void read() {
        notes = new ArrayList<Note>();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notes.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Note note = snapshot.getValue(Note.class);
                    notes.add(note);
                }
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        noteAdapter = new NoteAdapter(notes);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }




}

// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4

