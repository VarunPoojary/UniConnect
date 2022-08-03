//package com.example.mitadttrial.Fragments;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.mitadttrial.Adapters.CalendarAdapter;
//import com.example.mitadttrial.CalendarUtils;
//import com.example.mitadttrial.R;
//import com.example.mitadttrial.WeekViewActivity;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import static com.example.mitadttrial.CalendarUtils.daysInMonthArray;
//import static com.example.mitadttrial.CalendarUtils.monthYearFromDate;
//
//
//
//import android.content.Context;
//
//
//public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener
//{
//    private TextView monthYearText;
//    private RecyclerView calendarRecyclerView;
//    private Context context;
//    private FirebaseDatabase rootNode;
//    private DatabaseReference reference;
//    private View mainView;
//
//    private void initWidgets()
//    {
//        calendarRecyclerView = mainView.findViewById(R.id.calendarRecyclerView);
//        monthYearText = mainView.findViewById(R.id.monthYearTV);
//    }
//
//    private void setMonthView()
//    {
//        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
//        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);
//
//        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
//        calendarRecyclerView.setLayoutManager(layoutManager);
//        calendarRecyclerView.setAdapter(calendarAdapter);
//    }
//
//    public void previousMonthAction(View view)
//    {
//        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
//        setMonthView();
//    }
//
//    public void nextMonthAction(View view)
//    {
//        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
//        setMonthView();
//    }
//
//    @Override
//    public void onItemClick(int position, LocalDate date)
//    {
//        if(date != null)
//        {
//            CalendarUtils.selectedDate = date;
//            setMonthView();
//        }
//    }
//
//    public void weeklyAction(View view)
//    {
//        startActivity(new Intent(getActivity(), WeekViewActivity.class));
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        setViewLayout(R.layout.fragment_calendar);
//
//        super.onCreate(savedInstanceState);
//        initWidgets();
//        CalendarUtils.selectedDate = LocalDate.now();
//        setMonthView();
//
//
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_calendar, container, false);
//    }
//
//    private void setViewLayout(int id){
//        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mainView = inflater.inflate(id, null);
////        ViewGroup rootView = (ViewGroup) getView();
////        rootView.removeAllViews();
////        rootView.addView(mainView);
//    }
//}






package com.example.mitadttrial.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//import com.example.mitadttrial.CalendarActivity;
import com.example.mitadttrial.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;


import android.content.Context;


public class CalendarFragment extends Fragment
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private Context context;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private View mainView;



//    @Override
//    public void onItemClick(int position, LocalDate date)
//    {
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
//        setViewLayout(R.layout.fragment_calendar);

//        mainView = inflater.inflate(R.layout.fragment_calendar, container, false);
//
//        Button calendar = (Button) mainView.findViewById(R.id.calendar);
//        calendar.setVisibility(mainView.INVISIBLE);
//        calendar.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
////                Intent calendarIntent = new Intent(getActivity(), CalendarActivity.class);
//                startActivity(calendarIntent);
//            }
//        });


        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    private void setViewLayout(int id){
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
    }
}



















