package com.TUGASUAS10120165.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.TUGASUAS10120165.R;
import com.TUGASUAS10120165.Service.MyFirebaseMessagingService;
import com.TUGASUAS10120165.database.FirebaseHelper;
import com.TUGASUAS10120165.model.Note;

import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    ImageButton button;
    EditText editTitle;
    EditText editCategory;
    EditText editDesc;
    Button addButton;
    Button deleteButton;
    TextView titleAdd;

    private FirebaseHelper FirebaseHelp;
    private static final String CHANNEL_ID = "testing-notification_channel";
    private static final int NOTIFICATION_ID = 1;
    MyFirebaseMessagingService myFirebaseMessagingService = new MyFirebaseMessagingService();

    Note note = null;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().hide();
        note = (Note) getIntent().getSerializableExtra("Note");
        button = findViewById(R.id.back);
        editTitle = findViewById(R.id.title);
        editCategory = findViewById(R.id.txt_category);
        editDesc = findViewById(R.id.txt_desc);
        addButton = findViewById(R.id.buttonAdd);

        deleteButton = findViewById(R.id.buttonDelete);
        FirebaseHelp = new FirebaseHelper();
        button.setOnClickListener(v -> {
            finish();
        });

        if (note == null) {
            deleteButton.setVisibility(View.GONE);

            addButton.setOnClickListener(v -> {
                if (editTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Judul Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editCategory.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Kategori Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editDesc.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Isi Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }

                Date d = new Date();
                CharSequence date = DateFormat.format("EEEE, d MMM yyyy HH:mm", d.getTime());
                Note n = new Note(
                        d.getTime() + "",
                        editTitle.getText().toString(),
                        editCategory.getText().toString(),
                        editDesc.getText().toString(),
                         date + ""
                );
                FirebaseHelp.create(n);
                myFirebaseMessagingService.sendNotification("Berhasil", "Menambahkan catatan baru");
                finish();
            });
        } else {
            editTitle.setText(note.getTitle());
            editCategory.setText(note.getCategory());
            editDesc.setText(note.getDesc());
            deleteButton.setVisibility(View.VISIBLE);
            ;
            addButton.setOnClickListener(v -> {
                if (editTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Judul Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editCategory.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Kategori Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editDesc.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Isi Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }

                Date d = new Date();
                CharSequence date = DateFormat.format("EEEE, d MMMM yyyy HH:mm", d.getTime());
                note.setTitle(editTitle.getText().toString());
                note.setCategory(editCategory.getText().toString());
                note.setDesc(editDesc.getText().toString());
                note.setDate("terakhir di edit " + date + "");
                myFirebaseMessagingService.sendNotification("Edit", "Catatan Telah Dirubah");
                FirebaseHelp.update(note);
                finish();
            });
        }

        deleteButton.setOnClickListener(v -> {
            FirebaseHelp.delete(note.getId());
            finish();
            myFirebaseMessagingService.sendNotification("Hapus", "Catatan Telah dihapus");
        });


    }

}
// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4
