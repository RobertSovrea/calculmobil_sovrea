package com.example.myjournallapp2.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.example.myjournallapp2.R;
import com.example.myjournallapp2.model.JournalEntry;
import com.example.myjournallapp2.adapters.JournalAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private List<JournalEntry> journalEntries;
    private JournalAdapter adapter;
    private RecyclerView recyclerView;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        journalEntries = new ArrayList<>();
        adapter = new JournalAdapter(journalEntries);
        recyclerView = findViewById(R.id.recyclerViewJournal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button buttonAddEntry = findViewById(R.id.buttonAddEntry);
        buttonAddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        textViewMessage = findViewById(R.id.textViewMessage);

        // Inițiați un thread separat
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Simulați o sarcină care durează 3 secunde
                try {
                    Thread.sleep(3000); // 3 secunde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Actualizați UI-ul pe firul principal
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewMessage.setText("Sarcină finalizată!");
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
            JournalEntry entry = new JournalEntry(title, content, dateTime);
            journalEntries.add(entry);
            adapter.notifyDataSetChanged();
        }
    }
}
