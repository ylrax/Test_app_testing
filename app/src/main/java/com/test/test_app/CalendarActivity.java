package com.test.test_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.utils.DateUtils;


public class CalendarActivity extends AppCompatActivity {

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        List<EventDay> events = new ArrayList<>();
        Map<Date,String> eventNotes = new HashMap<Date, String>();
        Calendar calendar = Calendar.getInstance();
        tv1 = (TextView)findViewById(R.id.textView5);
        // calendar.set(2019, 7, 5);


        Drawable background = ContextCompat.getDrawable(this, R.drawable.sample_circle);
        Drawable textIn = CalendarUtils.getDrawableText(this, "samplee", null, android.R.color.black, 12);
        Drawable[] layers = {background, textIn};
        EventDay noteEvent = new EventDay(calendar, new LayerDrawable(layers));
        events.add(noteEvent);
        eventNotes.put(noteEvent.getCalendar().getTime(), "eventooooo");

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(1, 0); //??
        events.add(new EventDay(calendar1, R.drawable.sample_circle));

        //events.add(new EventDay(calendar1, R.drawable.sample_icon_2));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 10);
        events.add(new EventDay(calendar2, R.drawable.sample_three_icons, Color.parseColor("#228B22")));
        //events.add(new EventDay(calendar2, R.drawable.sample_icon_3, Color.parseColor("#228B22")));

        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DAY_OF_MONTH, 7);
        events.add(new EventDay(calendar3, R.drawable.sample_four_icons));

        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.DAY_OF_MONTH, 13);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.sample_three_icons);
        events.add(new EventDay(calendar4, new InsetDrawable(drawable, 100, 0, 100, 0)));

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -2);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 2);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        calendarView.setEvents(events);

        calendarView.setDisabledDays(getDisabledDays());

        calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(getApplicationContext(), eventDay.getCalendar().getTime().toString() + " aaaandddd " + eventNotes.get(eventDay.getCalendar().getTime()), Toast.LENGTH_SHORT).show());

        //test
        calendarView.setOnDayClickListener(eventDay -> tv1.setText(eventNotes.get(eventDay.getCalendar().getTime())));

        Button setDateButton = (Button) findViewById(R.id.setDateButton);
        setDateButton.setOnClickListener(v -> {
            try {
                Calendar randomCalendar = getRandomCalendar();
                String text = randomCalendar.getTime().toString();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                calendarView.setDate(randomCalendar);

            } catch (OutOfDateRangeException exception) {
                exception.printStackTrace();
                Toast.makeText(getApplicationContext(),"Date is out of range",  Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    private Calendar getRandomCalendar() {
        Random random = new Random();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, random.nextInt(1));

        return calendar;
    }

    public void volver(View view){
        Intent anter = new Intent(this, MainActivity.class);
        startActivity(anter);
    }
}