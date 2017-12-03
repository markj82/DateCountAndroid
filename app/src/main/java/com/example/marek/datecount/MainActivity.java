package com.example.marek.datecount;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView mDisplayDate;
    private TextView resultForUser;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDisplayDate = (TextView) findViewById(R.id.Date);
        resultForUser = (TextView) findViewById(R.id.answerID);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        mDateSetListener,
                year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();



            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);

                LocalDate todayDate = LocalDate.now();
                LocalDate birthdayDate = LocalDate.of(year, month, day);

                long yearsOfUser = ChronoUnit.YEARS.between(birthdayDate, todayDate);
                long weeksOfUser = ChronoUnit.WEEKS.between(birthdayDate, todayDate);
                long daysOfUser = ChronoUnit.DAYS.between(birthdayDate, todayDate);
                String yearResult = String.valueOf(yearsOfUser);
                String weekResult = String.valueOf(weeksOfUser);
                String daysResult = String.valueOf(daysOfUser);

                resultForUser.setText("You live for " + yearResult + " years, " + weekResult + " weeks, and " + daysResult + " days.");

            }
        };
    }
}
