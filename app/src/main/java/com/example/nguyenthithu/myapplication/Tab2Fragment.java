package com.example.nguyenthithu.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2Fragment extends Fragment {

    TextView textView;

    // bảng cài đặt thông số kỹ thuật
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private TableLayout table;
    private TableRow row1;

    // Dialog
    public Dialog d;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View view =  inflater.inflate(R.layout.fragment_two, container, false);

            //  chuyện chế độ tự động tưới nước

            /*textView = view.findViewById(R.id.textview_id);
            //switchCompat = view.findViewById(R.id.switch_id);

           // switchCompat.setOnCheckedChangeListener(
            //        new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                textView.setText("Chê đội tưới Tự động " +
                                    (switchCompat.isChecked() ? "On" : "Off"));
                        }


                    });
                    */

            // đổ dữ liệu bảng

            data.add("Samsung");
            data.add("Apple");
            data.add("Pixle");
            data.add("Vivo");
            data1.add(" $109");
            data1.add(" $200");
            data1.add(" $399");
            data1.add(" $290");


            //table = (TableLayout)view.findViewById(R.id.tableLayout);
            //Context context = table.getContext();

            LinearLayout  nearLayout = (LinearLayout)view.findViewById(R.id.linear_two);
            Context context = nearLayout.getContext();

            table = new TableLayout(context);
            table.setBackgroundResource(R.drawable.ic_tablelayout);
            nearLayout.addView(table,0);

           final TableRow rowH =new TableRow(context);
            rowH.setBackgroundResource(R.drawable.ic_tablehead);
            //rowH.setPadding(10,10,10,10);

            TextView tvH0=new TextView(context);
            tvH0.setText("TÊN");
            //tvH0.setTextAppearance(context, R.style.textRow);
            tvH0.setBackgroundResource(R.drawable.ic_textview);

            TextView tvH1=new TextView(context);
            tvH1.setText("ĐỘ ẨM");
            tvH1.setTextAppearance(context, R.style.textRow);
            tvH1.setBackgroundResource(R.drawable.ic_textview);

            //tv1.setPadding(5,5,5,5);
            //tv1.setBackground();

            TextView tvH2=new TextView(context);
            tvH2.setText("ĐỘ ẨM");
            tvH2.setTextAppearance(context, R.style.textRow);
            tvH2.setBackgroundResource(R.drawable.ic_textview);

            TextView tvH3=new TextView(context);
            tvH3.setText("THỜI GIAN");
            tvH3.setTextAppearance(context, R.style.textRow);
            tvH3.setBackgroundResource(R.drawable.ic_textview);

            TextView tvH4=new TextView(context);
            tvH4.setText("THỜI GIAN");
            tvH4.setTextAppearance(context, R.style.textRow);
            tvH4.setBackgroundResource(R.drawable.ic_textview);

            TextView tvH5 =new TextView(context);
            tvH5.setText("TỰ ĐỘNG");
            tvH5.setTextAppearance(context, R.style.textRow);
            tvH5.setBackgroundResource(R.drawable.ic_textview);

            rowH.addView(tvH0);
            rowH.addView(tvH1);
            rowH.addView(tvH2);
            rowH.addView(tvH3);
            rowH.addView(tvH4);
            rowH.addView(tvH5);

            table.addView(rowH);

            for(int i=0;i<data.size();i++)
            {
                final TableRow row=new TableRow(context);
                row.setBackgroundResource(R.drawable.ic_tablerow);
               // row.setR(context, R.style.textRow);

                String phone = data.get(i);
                String amount = data1.get(i);
                final SensorSettings ss = new SensorSettings("10%","60%","1h","24h","ghi chú", true);

              TextView tv0=new TextView(context);
                tv0.setText(i+"");
                tv0.setTextAppearance(context, R.style.textRow);

               TextView tv1=new TextView(context);
                tv1.setText(ss.getMinDoAm());
                tv1.setTextAppearance(context, R.style.textRow);
                //tv1.setPadding(5,5,5,5);
                //tv1.setBackground();

                TextView tv2=new TextView(context);
                tv2.setText(ss.getMaxDoAm());
                tv2.setTextAppearance(context, R.style.textRow);

                TextView tv3=new TextView(context);
                tv3.setText(ss.getMinTime());
                tv3.setTextAppearance(context, R.style.textRow);

                TextView tv4=new TextView(context);
                tv4.setText(ss.getMaxTime());
                tv4.setTextAppearance(context, R.style.textRow);

                TextView tv5=new TextView(context);
                tv5.setText(ss.getStatus()+"");
                tv5.setTextAppearance(context, R.style.textRow);
               /// tv5.setL

                row.addView(tv0);
                row.addView(tv1);
                row.addView(tv2);
                row.addView(tv3);
                row.addView(tv4);
                row.addView(tv5);
                row.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        displayAlertDialog(view.getContext(),ss);
                        /*
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        ViewGroup viewGroup = (ViewGroup)view.findViewById(android.R.id.content);
                        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_dialog, viewGroup, false);
                        builder.setView(dialogView);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        */
                    }
                });
                table.addView(row);
            }
            // lấy Id của TableLayout
         ///   table.
           /* row1 = (TableRow) view.findViewById(R.id.row1);
            row1.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    Log.d("Click ", "Row 1");
                    arg0.setBackgroundColor(56);
                }
            });*/
            //------------------------------------------
            return view;
        }

    public void displayAlertDialog(Context activity,SensorSettings ss ) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_dialog, null);
        //
        final EditText minDoam = (EditText) alertLayout.findViewById(R.id.minDoam);
        final EditText maxDoam = (EditText) alertLayout.findViewById(R.id.maxDoam);
        final EditText minTime = (EditText) alertLayout.findViewById(R.id.minTime);
        final EditText maxTime = (EditText) alertLayout.findViewById(R.id.maxTime);
        final EditText Note = (EditText) alertLayout.findViewById(R.id.Note);
        final  SwitchCompat switchCompat = (SwitchCompat)alertLayout.findViewById(R.id.switch_id);

        //
        minDoam.setText(ss.getMinDoAm());
        maxDoam.setText(ss.getMaxDoAm());
        minTime.setText(ss.getMinTime());
        maxTime.setText(ss.getMinTime());
        Note.setText(ss.getNote());
        switchCompat.setChecked(ss.getStatus());
        //
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("THÔNG SỐ KỸ THUẬT");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("HUỶ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("LƯU", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                String str_minDoam = minDoam.getText().toString();
                String str_maxDoam = maxDoam.getText().toString();
                /// Toast.makeText(getBaseContext(), "Username: " + user + " Password: " + pass, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}
