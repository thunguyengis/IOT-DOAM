package com.example.nguyenthithu.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3Fragment extends Fragment  {
    private Spinner spinner1, spinner2;
    private Button btnSubmit;

    // bảng cài đặt thông số kỹ thuật
    private ArrayList<String> data = new ArrayList<String>();
    private TableLayout table;
    private TableRow row1;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_three, container, false);

            //addItemsOnSpinner2();
            /*
            spinner2 = (Spinner) view.findViewById(R.id.spinner2);
            List<String> list = new ArrayList<String>();
            list.add("list 1");
            list.add("list 2");
            list.add("list 3");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(),
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(dataAdapter);
            */
          ///  addListenerOnButton();
            /*spinner1 = (Spinner) view.findViewById(R.id.spinner1);
            //spinner2 = (Spinner) view.findViewById(R.id.spinner2);
            btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),
                            "OnClickListener : " +
                                    "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                    "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                            Toast.LENGTH_SHORT).show();
                }

            });
            */


           // addListenerOnSpinnerItemSelection();
            spinner1 = (Spinner) view.findViewById(R.id.spinner1);
            spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    /*Toast.makeText(adapterView.getContext(),
                            "OnItemSelectedListener : " + adapterView.getItemAtPosition(i).toString(),
                            Toast.LENGTH_SHORT).show();*/
                    Log.d("spinner",i+"");

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            //==================================================
            data.add("Samsung");
            data.add("Apple");
            data.add("Pixle");
            data.add("Vivo");



            table = (TableLayout)view.findViewById(R.id.tableHistory);
            Context context = table.getContext();
            for(int i=0;i<data.size();i++)
            {
                final TableRow row=new TableRow(context);

                SensorHistorys sh = new SensorHistorys("234","13h30","....");

                TextView tv1=new TextView(context);
                tv1.setText(i+"");

                TextView tv2=new TextView(context);
                tv2.setText(sh.getTime());

                TextView tv3=new TextView(context);
                tv3.setText(sh.getDoam());

                TextView tv4=new TextView(context);
                tv4.setText(sh.getNote());

                row.addView(tv1);
                row.addView(tv2);
                row.addView(tv3);
                row.addView(tv4);

                row.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        v.setBackgroundColor(34);
                    }
                });
                table.addView(row);
            }


            return view;

        }
    // add items into spinner dynamically



}



