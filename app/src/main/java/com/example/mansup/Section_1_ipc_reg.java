
package com.example.mansup;




import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;

import static Common.Connection.SelectedSpinnerValue;

public class Section_1_ipc_reg extends Activity {
   boolean networkAvailable=false;
   Location currentLocation;
   double currentLatitude,currentLongitude;
   //Disabled Back/Home key
   //--------------------------------------------------------------------------------------------------
   @Override
   public boolean onKeyDown(int iKeyCode, KeyEvent event)
   {
       if(iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME)
            { return false; }
       else { return true;  }
   }
   String VariableID;
   private int hour;
   private int minute;
    /*private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            EditText tpTime;

        }
    };*/
   private int mDay;
   private int mMonth;
   private int mYear;
   static final int DATE_DIALOG = 1;
   static final int TIME_DIALOG = 2;

   Connection C;

   Global g;
   SimpleAdapter dataAdapter;
   ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


    LinearLayout secidno;
    View lineidno;
    TextView Vlblidno;
    TextView txtidno;
    TextView txtslno;

    LinearLayout seczillaid;
        View linezillaid;
        TextView Vlblzillaid;
        Spinner spnzillaid;
        LinearLayout secupazilaid;
        View lineupazilaid;
        TextView Vlblupazilaid;
        Spinner spnupazilaid;
        LinearLayout secunionid;
        View lineunionid;
        TextView Vlblunionid;
        Spinner spnunionid;

       LinearLayout secunionother;
       View lineunionother;
       TextView Vlblunionother;
       EditText txtunionother;

        LinearLayout secvillageid;
        View linevillageid;
       //  TextView txtmouzaid;
       // TextView   txtvillage;
        TextView Vlblvillageid;
        EditText txtvillage_name;

    LinearLayout secq105;
    View lineq105;
    TextView Vlblq105;
    Spinner spnq105;

    LinearLayout secq106;
    View lineq106;
    TextView Vlblq106;
    Spinner spnq106;
    LinearLayout secq107;
    View lineq107;
    TextView Vlblq107;
    Spinner spnq107;

    LinearLayout secinterviewer_date;
    View lineinterviewer_date;
    TextView Vlblinterviewer_date;
    EditText dtpinterviewer_date;

    LinearLayout secinterviewer_id;
    View lineinterviewer_id;
    TextView Vlblinterviewer_id;
    EditText txtinterviewer_id;

    Button cmdSave;
    //Button cmdSave1;
   static String TableName;

   static String STARTTIME = "";
   static String DEVICEID  = "";
   static String ENTRYUSER = "";
   //MySharedPreferences sp;
   static String lavel = "";
    static String Zila = "";
    static String UZila = "";
    static String UN = "";
    static String WD = "";
   Bundle IDbundle;
   static String IDNO = "";
   // static String SLNo = "";
   static String Type = "";
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  try
    {
        setContentView(R.layout.respd_identifications_ipc);
        C = new Connection(this);
        g = Global.getInstance();
        DEVICEID  = C.ReturnSingleValue("select ifnull(deviceno,'9999') as deviceno from deviceno");;
        ENTRYUSER =  C.ReturnSingleValue("Select ifnull(userid,'0') as deviceno  from login");
        lavel= C.ReturnSingleValue("select ifnull(level_id,'0') as level_id from providerdb");
        Zila = C.ReturnSingleValue("select ifnull(zillaid,'0') as zillaid from providerdb");
        UZila = C.ReturnSingleValue("select ifnull(upazilaid,'0') as upazilaid from providerdb");
        //UN = C.ReturnSingleValue("select ifnull(unionid,'0') as unionid from providerdb");
        //WD = C.ReturnSingleValue("select ifnull(ward,'0') as ward from providerdb");
        if(g.getCallFrom().equalsIgnoreCase("update"))
        {

        }
        else {
            if (lavel.equalsIgnoreCase("3")) {
                UN = C.ReturnSingleValue("select ifnull(unionid,'0') as unionid from providerdb");
                WD = C.ReturnSingleValue("select ifnull(ward,'0') as ward from providerdb");
            } else if (lavel.equalsIgnoreCase("2")) {
                UN = C.ReturnSingleValue("select ifnull(unionid,'0') as unionid from providerdb");

            } else if (lavel.equalsIgnoreCase("1")) {

            }
        }
        // Type=ENTRYUSER;

        IDbundle = getIntent().getExtras();
        // IDNO = IDbundle.getString("idno");

        TableName = "section_1_identifications_ipc_reg";
        STARTTIME = g.CurrentTime24();
        ((ImageButton) findViewById(R.id.cmdBack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g.setlevel(lavel);
                g.setDistrict(Zila);
                g.setUpazila(UZila);
                if(g.getCallFrom().equalsIgnoreCase("update"))
                {

                }
                else {
                    if (lavel.equalsIgnoreCase("3")) {
                        g.setUnion(UN);
                        g.setward(WD);
                    } else if (lavel.equalsIgnoreCase("2")) {
                        g.setUnion(UN);
                    }
                    else if (lavel.equalsIgnoreCase("1"))
                    {
                        g.setUnion("");
                        g.setward("");
                    }
                }
               // g.setUnion(UN);
               // g.setward(WD);
                finish();
                Intent f1 = new Intent(getApplicationContext(), manager_home.class);
                startActivity(f1);
            }
        });
        ((ImageButton) findViewById(R.id.cmdClose)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g.setlevel(lavel);
                g.setDistrict(Zila);
                g.setUpazila(UZila);
               // g.setUnion(UN);
               // g.setward(WD);
                if(g.getCallFrom().equalsIgnoreCase("update"))
                {

                }
                else {
                    if (lavel.equalsIgnoreCase("3")) {
                        g.setUnion(UN);
                        g.setward(WD);
                    } else if (lavel.equalsIgnoreCase("2")) {
                        g.setUnion(UN);
                    }
                    else if (lavel.equalsIgnoreCase("1"))
                    {
                        g.setUnion("");
                        g.setward("");
                    }
                }
                finish();
                Intent f1 = new Intent(getApplicationContext(), manager_home.class);
                startActivity(f1);
            }
        });




        turnGPSOn();

        //GPS Location
        FindLocation();
         Double.toString(currentLatitude);
         Double.toString(currentLongitude);


        secidno=(LinearLayout)findViewById(R.id.secidno);
        lineidno=(View)findViewById(R.id.lineidno);
        Vlblidno=(TextView) findViewById(R.id.Vlblidno);
        txtidno=(TextView) findViewById(R.id.txtidno);



       // cmdSave = (Button) findViewById(R.id.cmdSave);
        //cmdSave1 = (Button) findViewById(R.id.cmdSave1);

        seczillaid=(LinearLayout)findViewById(R.id.seczillaid);
        linezillaid=(View)findViewById(R.id.linezillaid);
        Vlblzillaid=(TextView) findViewById(R.id.Vlblzillaid);
        spnzillaid=(Spinner) findViewById(R.id.spnzillaid);

        /*if(g.getCallFrom().equalsIgnoreCase("update"))
        {
            spnzillaid.setAdapter(C.getArrayAdapterMultiline("Select '---সিলেক্ট করুন--' as zilla union select (zillaid ||'- '||  zillaname) as zilla from zilla"));//in('09','12','13','14') order by EvCode asc"));

        }
        else {
            spnzillaid.setAdapter(C.getArrayAdapterMultiline("Select '---সিলেক্ট করুন--' as zilla union select (zillaid ||'- '||  zillaname) as zilla from zilla where zillaid='" + g.getDistrict() + "'"));//in('09','12','13','14') order by EvCode asc"));
        }
*/
        secupazilaid=(LinearLayout)findViewById(R.id.secupazilaid);
        lineupazilaid=(View)findViewById(R.id.lineupazilaid);
        Vlblupazilaid=(TextView) findViewById(R.id.Vlblupazilaid);
        spnupazilaid=(Spinner) findViewById(R.id.spnupazilaid);


      /*if(g.getCallFrom().equalsIgnoreCase("update"))
        {
            spnupazilaid.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as upazila union select upazilaid||'- '||upazilaname as upazila from upazila"));//in('09','12','13','14') order by EvCode asc"));

        }
    else {
            spnzillaid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String[] zid = spnzillaid.getSelectedItem().toString().split("-");
                    // SpinnerItem(spnUpz, "select \"UPAZILAID\"||'-'||\"UPAZILANAME\" from \"Upazila\" where \"ZILLAID\"='" + zid[0] + "'");
                    // SpinnerItem(spnUpz, "select upazilaid||'-'||upazilaname from upazila where zillaid='" + zid[0] + "'");
                   // spnupazilaid.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as upazila union select upazilaid||'- '||upazilaname as upazila from upazila where zillaid='" + zid[0] + "'"));//in('09','12','13','14') order by EvCode asc"));
                    spnupazilaid.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as upazila union select upazilaid||'- '||upazilaname as upazila from upazila where zillaid='" + zid[0] + "'"));//in('09','12','13','14') order by EvCode asc"));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
        }
*/

        if (g.getCallFrom().equalsIgnoreCase("update")) {
            spnzillaid.setAdapter(C.getArrayAdapterMultiline("select DISTINCT (zillaid ||'- '||  zillaname) as zilla from zilla where zillaid"));

        } else {
            spnzillaid.setAdapter(C.getArrayAdapterMultiline("select DISTINCT (zillaid ||'- '||  zillaname) as zilla from zilla where zillaid='" + g.getDistrict() + "'"));
        }

        if(g.getCallFrom().equalsIgnoreCase("update"))
        {
            spnupazilaid.setAdapter(C.getArrayAdapterMultiline("select DISTINCT upazilaid||'- '||upazilaname as upazila from upazila"));

        }
        else {
            spnupazilaid.setAdapter(C.getArrayAdapterMultiline("select DISTINCT upazilaid||'- '||upazilaname as upazila from upazila where zillaid='" + g.getDistrict() + "' and upazilaid='" + g.getUpazila()+ "'"));


        }


        secunionid=(LinearLayout)findViewById(R.id.secunionid);
        lineunionid=(View)findViewById(R.id.lineunionid);
        Vlblunionid=(TextView) findViewById(R.id.Vlblunionid);
        spnunionid=(Spinner) findViewById(R.id.spnunionid);

        if(g.getCallFrom().equalsIgnoreCase("update"))
        {

            spnunionid.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as unions union select unionid ||'- '||unionname as unions from unions"));
        }
        else {
            spnupazilaid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String[] zid = spnzillaid.getSelectedItem().toString().split("-");
                    String[] upid = spnupazilaid.getSelectedItem().toString().split("-");
                    //String UNID=C.ReturnSingleValue("select cast(unionid as varchar(2)) from unions where zillaid='" + g.getDistrict() + "' and upazilaid='" + g.getUpazila() + "' and unionid='" + g.getUnion() + "'");
                    if(g.getlevel().equalsIgnoreCase("1")) {
                        spnunionid.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as unions union select unionid ||'- '||unionname as unions from unions where  zillaid='" + zid[0] + "' and upazilaid='" + upid[0] + "'"));
                    }

                    if(g.getlevel().equalsIgnoreCase("2")) {
                        spnunionid.setAdapter(C.getArrayAdapterMultiline("select unionid ||'- '||unionname as unions from unions where  zillaid='" + g.getDistrict()  + "' and upazilaid='" +g.getUpazila() + "' and unionid='" + g.getUnion()+ "'"));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
        }



       secunionother=(LinearLayout)findViewById(R.id.secunionother);
       lineunionother=(View)findViewById(R.id.lineunionother);
       Vlblunionother=(TextView) findViewById(R.id.Vlblunionother);
        txtunionother=(EditText) findViewById(R.id.txtunionother);



        secvillageid=(LinearLayout)findViewById(R.id.secvillageid);
        linevillageid=(View)findViewById(R.id.linevillageid);
        Vlblvillageid=(TextView) findViewById(R.id.Vlblvillageid);
       // txtmouzaid=(TextView) findViewById(R.id.txtmouzaid);
       // txtvillage=(TextView) findViewById(R.id.txtvillage);

        txtvillage_name=(EditText) findViewById(R.id.txtvillage_name);
        txtslno=(TextView) findViewById(R.id.txtslno);

        secq105=(LinearLayout)findViewById(R.id.secq105);
        lineq105=(View)findViewById(R.id.lineq105);
        Vlblq105=(TextView) findViewById(R.id.Vlblq105);
        spnq105=(Spinner) findViewById(R.id.spnq105);

        if(g.getCallFrom().equalsIgnoreCase("update"))
        {
         spnq105.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS word UNION SELECT ward_no||CASE WHEN ward_no = 1 THEN '- ওয়ার্ড 1' WHEN ward_no = 2 THEN '- ওয়ার্ড 2' WHEN ward_no = 3 THEN '- ওয়ার্ড 3' WHEN ward_no = 4 THEN '- ওয়ার্ড 1(অতিঃ 1 )' WHEN ward_no = 5 THEN '- ওয়ার্ড 2( অতিঃ 2 )' WHEN ward_no = 6 THEN '-ওয়ার্ড 3(অতিঃ 3 )' else '' end AS word FROM [cluster]"));
        }
        else {
            spnunionid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String[] zid = spnzillaid.getSelectedItem().toString().split("-");
                    String[] upid = spnupazilaid.getSelectedItem().toString().split("-");
                    String[] unid = spnunionid.getSelectedItem().toString().split("-");
                    spnq105.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS word UNION SELECT ward_no||CASE WHEN ward_no = 1 THEN '- ওয়ার্ড 1' WHEN ward_no = 2 THEN '- ওয়ার্ড 2' WHEN ward_no = 3 THEN '- ওয়ার্ড 3' WHEN ward_no = 4 THEN '- ওয়ার্ড 1(অতিঃ 1 )' WHEN ward_no = 5 THEN '- ওয়ার্ড 2( অতিঃ 2 )' WHEN ward_no = 6 THEN '-ওয়ার্ড 3(অতিঃ 3 )' else '' end AS word FROM [cluster]  where  zillaid='" + zid[0] + "' and upazilaid='" + upid[0]+ "' and unionid='" + unid[0]  + "'"));
                    //spnq105.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS word UNION SELECT ward_no||'-'||'ওয়ার্ড' AS word FROM [cluster]  where  upazilaid='" + upid[0] + "' and unionid='" + unid[0] + "'"));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
        }




        secq106=(LinearLayout)findViewById(R.id.secq106);
        lineq106=(View)findViewById(R.id.lineq106);
        Vlblq106=(TextView) findViewById(R.id.Vlblq106);
        spnq106=(Spinner) findViewById(R.id.spnq106);


       if(g.getCallFrom().equalsIgnoreCase("update"))
        {
            spnq106.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS [cluster] UNION SELECT clusterid || '- ' ||epi_sub_block||': '|| epi_cluster_name AS cluster_name FROM [cluster]  where  zillaid='" + g.getDistrict() + "' and upazilaid='" + g.getUpazila()+ "' and unionid='" + g.getUnion()+ "' and ward_no='" +g.getward() + "'"));
          //spnq106.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS [cluster] UNION SELECT clusterid || '- ' ||epi_sub_block||': '||  epi_cluster_name AS cluster_name FROM [cluster]"));
        }
        else {
            spnq105.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String[] zid = spnzillaid.getSelectedItem().toString().split("-");
                    String[] upid = spnupazilaid.getSelectedItem().toString().split("-");
                    String[] unid = spnunionid.getSelectedItem().toString().split("-");
                    String[] ward = spnq105.getSelectedItem().toString().split("-");

                    spnq106.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS [cluster] UNION SELECT clusterid || '- ' ||epi_sub_block||': '|| epi_cluster_name AS cluster_name FROM [cluster]  where  zillaid='" + zid[0] + "' and upazilaid='" + upid[0]+ "' and unionid='" + unid[0] + "' and ward_no='" + ward[0]  + "'"));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
      }




        secq107=(LinearLayout)findViewById(R.id.secq107);
        lineq107=(View)findViewById(R.id.lineq107);
        Vlblq107=(TextView) findViewById(R.id.Vlblq107);
        spnq107=(Spinner) findViewById(R.id.spnq107);


        if(g.getCallFrom().equalsIgnoreCase("update"))
        {

            spnq107.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as codelist union select code||'- '||cname as clinic from codelist where typename='man'"));

        }
        else {
            spnq106.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    spnq107.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as codelist union select code||'- '||cname as clinic from codelist where  typename='man' and code='" +g.getProvType() + "'"));
                   // spnq107.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as codelist union select code||'- '||cname as clinic from codelist where typename='man'"));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
        }
        secinterviewer_date=(LinearLayout)findViewById(R.id.secinterviewer_date);
        lineinterviewer_date=(View)findViewById(R.id.lineinterviewer_date);
        Vlblinterviewer_date=(TextView) findViewById(R.id.Vlblinterviewer_date);
        dtpinterviewer_date=(EditText) findViewById(R.id.dtpinterviewer_date);

        secinterviewer_id=(LinearLayout)findViewById(R.id.secinterviewer_id);
        lineinterviewer_id=(View)findViewById(R.id.lineinterviewer_id);
        Vlblinterviewer_id=(TextView) findViewById(R.id.Vlblinterviewer_id);
        txtinterviewer_id=(EditText) findViewById(R.id.txtinterviewer_id);
        if(g.getCallFrom().equalsIgnoreCase("update"))
        {

            txtinterviewer_id.setText(C.ReturnSingleValue("Select userid||' - '||username from login"));

        }
       if(g.getCallFrom().equalsIgnoreCase("")) {
            spnq107.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String[] zid = spnzillaid.getSelectedItem().toString().split("-");
                    String[] upid = spnupazilaid.getSelectedItem().toString().split("-");
                    String[] unid = spnunionid.getSelectedItem().toString().split("-");
                  //  String[] cluster = spnq106.getSelectedItem().toString().split("-");
                    String[] type = spnq107.getSelectedItem().toString().split("-");
                    String idno = "";
                    String IncrementNo = "";

                    if (spnq107.getSelectedItemPosition() != 0) {

                           IncrementNo = IncrementNumber_new(zid[0], upid[0],type[0], g.getDeviceNo());
                            txtslno.setText(IncrementNo);
                            String Device_no = C.ReturnSingleValue("select ifnull(deviceno,'99') as deviceno from deviceno");
                            String upazilaid = "";
                            if (upid[0].length() == 1) {
                                upazilaid = "0" + upid[0];
                            } else {
                                upazilaid = upid[0];
                            }

                            String unionid;
                            if (unid[0].length() == 1) {
                                unionid = "0" + unid[0];
                            } else {
                                unionid = unid[0];
                            }


                            String IncrementNumber;

                            if (IncrementNo.length() == 1) {
                                IncrementNumber = "0" + IncrementNo;
                            } else {
                                IncrementNumber = IncrementNo;
                            }


                            // SLNo=IncrementNo;
                           // idno = upazilaid + unionid + IncrementNumber + Device_no;
                           // idno = zid[0]+upazilaid + unionid + IncrementNumber + Device_no+cluster[0];
                           // idno = Device_no+zid[0]+upazilaid + type[0]+IncrementNumber;
                            idno = Device_no+zid[0]+upazilaid + type[0] +IncrementNumber;
                            txtidno.setText(idno);
                            txtinterviewer_id.setText(C.ReturnSingleValue("Select userid||' - '||username from login"));

                        }
                   // }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
        }





        dtpinterviewer_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT  = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (dtpinterviewer_date.getRight() - dtpinterviewer_date.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        VariableID = "btninterviewer_date"; showDialog(DATE_DIALOG);
                     return true;
                    }
                }
                return false;
            }
        });



        //Hide all skip variables

        secunionother.setVisibility(View.GONE);
        lineunionother.setVisibility(View.GONE);
        if(g.getCallFrom().equalsIgnoreCase("update"))
        {
            txtidno.setText(g.getIdNo());
            // secunionother.setVisibility(View.GONE);
            // lineunionother.setVisibility(View.GONE);
            DataSearch(g.getIdNo());

        }


       cmdSave = (Button) findViewById(R.id.cmdSave);
       cmdSave.setOnClickListener(new View.OnClickListener() {
       public void onClick(View v) {
           DataSave();

       }});


    }
    catch(Exception  e)
    {
        Connection.MessageBox(Section_1_ipc_reg.this, e.getMessage());
        return;
    }
}

    private String IncrementNumber_new(String zillaid,String upazilaid,String type,String deviceid) {

    //
        String SQL = "";
        SQL = "Select (ifnull(max(cast(slno as int)),0)+1)slno from section_1_identifications_ipc_reg";
        // SQL += " where zillaid='" + zillaid + "' and upazilaid='"+upazilaid+ "' and unionid='"+unionid+ "' and mouzaid='"+mouzaid+ "' and villageid='"+villageid+ "' and deviceid='"+deviceid+"'"; //deviceid
        SQL += " where zillaid='" + zillaid + "' and upazilaid='"+upazilaid+ "' and q107='"+type+ "' and deviceid='"+deviceid+"'"; //deviceid
        String Increment =C.ReturnSingleValue(SQL);
        return Increment;
    }



private void DataSave()
{
  try
    {

        String DV="";
       // String[] zid = spnzillaid.getSelectedItem().toString().split("-");
/*
        if(spnzillaid.getSelectedItemPosition()==0  & seczillaid.isShown())
          {
            Connection.MessageBox(Section_1_ipc_reg.this, "101. জেলা  সিলেক্ট করুন ।");
            spnzillaid.requestFocus();
            return;
          }
         if(spnupazilaid.getSelectedItemPosition()==0  & secupazilaid.isShown())
          {
            Connection.MessageBox(Section_1_ipc_reg.this, "102. উপজেলা সিলেক্ট করুন ।");
            spnupazilaid.requestFocus();
            return;
          }*/
        if(g.getlevel().equalsIgnoreCase("1")) {
            if (spnunionid.getSelectedItemPosition() == 0 & secunionid.isShown()) {
                Connection.MessageBox(Section_1_ipc_reg.this, "103. ইউনিয়ন  সিলেক্ট করুন ।");
                spnunionid.requestFocus();
                return;
            }
        }
        // if(spnvillageid.getSelectedItemPosition()==0  & secvillageid.isShown())
       /* if(txtvillage_name.getText().toString().length()==0 & secvillageid.isShown())
        {
            //txtvillage_name
            Connection.MessageBox(Section_1_ipc_reg.this, "3a. গ্রাম নাম ফাঁকা ।");
            txtvillage_name.requestFocus();
            return;
        }
*/

      /*  if(txtvillage_name.getText().toString().length()==0 & secvillageid.isShown())
          {
              //txtvillage_name
            Connection.MessageBox(Section_1_ipc_reg.this, "104. গ্রাম  নাম ফাঁকা ।");
              txtvillage_name.requestFocus();
            return;
          }*/

        if(spnq105.getSelectedItemPosition()==0  & secq105.isShown())
        {
            Connection.MessageBox(Section_1_ipc_reg.this, "105. ওয়ার্ড নম্বর সিলেক্ট করুন ।");
            spnq105.requestFocus();
            return;
        }

        if(spnq106.getSelectedItemPosition()==0  & secq106.isShown())
        {
            Connection.MessageBox(Section_1_ipc_reg.this, "106. ইপিআই কেন্দ্রের নাম/ব্লক সিলেক্ট করুন ।");
            spnq106.requestFocus();
            return;
        }

        if(spnq107.getSelectedItemPosition()==0  & secq107.isShown())
        {
            Connection.MessageBox(Section_1_ipc_reg.this, "107. রেজিস্ট্রেশনের ধরন সিলেক্ট করুন ।");
            spnq107.requestFocus();
            return;
        }

      /*  if(spnclinicid.getSelectedItemPosition()==0  & secclinicid.isShown())
        {
            Connection.MessageBox(Section_1_ipc_reg.this, "5. ক্লিনিকের নাম সিলেক্ট করুন ।");
            spnclinicid.requestFocus();
            return;
        }*/
         if(txtidno.getText().toString().length()==0 & secidno.isShown())
          {
            Connection.MessageBox(Section_1_ipc_reg.this, "IDNo খালি হতে পারে না ।");
            txtidno.requestFocus();
            return;
          }


        if(txtinterviewer_id.getText().toString().length()==0 & secinterviewer_id.isShown())
          {
            Connection.MessageBox(Section_1_ipc_reg.this, "109. সাক্ষাৎকার গ্রহণকারীর নাম ফাঁকা ।");
            txtinterviewer_id.requestFocus();
            return;
          }
        DV = Global.DateValidate(dtpinterviewer_date.getText().toString());
        if(DV.length()!=0 & secinterviewer_date.isShown())
          {
            Connection.MessageBox(Section_1_ipc_reg.this, DV);
            dtpinterviewer_date.requestFocus();
            return;
          }


        String SQL = "";
        RadioButton rb;


        Section_1_ipc_reg_DataModel objSave = new Section_1_ipc_reg_DataModel();
       // objSave.setzillaid((spnzillaid.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnzillaid.getSelectedItem().toString(), "-")));
       // objSave.setupazilaid((spnupazilaid.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnupazilaid.getSelectedItem().toString(), "-")));
        objSave.setzillaid(SelectedSpinnerValue(spnzillaid.getSelectedItem().toString(), "-"));
        objSave.setupazilaid( SelectedSpinnerValue(spnupazilaid.getSelectedItem().toString(), "-"));

        if(g.getlevel().equalsIgnoreCase("1"))
        {
            objSave.setzillaid(SelectedSpinnerValue(spnzillaid.getSelectedItem().toString(), "-"));
            objSave.setupazilaid( SelectedSpinnerValue(spnupazilaid.getSelectedItem().toString(), "-"));
            //objSave.setq105("");
            objSave.setunionid((spnunionid.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnunionid.getSelectedItem().toString(), "-")));

        }

        if(g.getlevel().equalsIgnoreCase("2"))
        {
            objSave.setzillaid(SelectedSpinnerValue(spnzillaid.getSelectedItem().toString(), "-"));
            objSave.setupazilaid( SelectedSpinnerValue(spnupazilaid.getSelectedItem().toString(), "-"));
            objSave.setunionid( SelectedSpinnerValue(spnunionid.getSelectedItem().toString(), "-"));
            //objSave.setunionid("");
           // objSave.setq105("");
        }

        objSave.setunion_name(txtunionother.getText().toString());
        objSave.setvill_name(txtvillage_name.getText().toString());
        objSave.setslno(txtslno.getText().toString());
        objSave.setq105((spnq105.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnq105.getSelectedItem().toString(), "-")));
        objSave.setq106((spnq106.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnq106.getSelectedItem().toString(), "-")));
        objSave.setq107((spnq107.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnq107.getSelectedItem().toString(), "-")));
         objSave.setidno(txtidno.getText().toString());
        String[] interviewer_id =txtinterviewer_id.getText().toString().split("-");
        objSave.setinterviewer_id(interviewer_id[0]);
        objSave.setinterviewer_date(dtpinterviewer_date.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpinterviewer_date.getText().toString()) : dtpinterviewer_date.getText().toString());
        objSave.setEnDt(Global.DateTimeNowYMDHMS());
        objSave.setEndTime(g.CurrentTime24());
        objSave.setDeviceID(DEVICEID);
        objSave.setEntryUser(ENTRYUSER); //from data entry user list
        objSave.setmodifyDate(Global.DateTimeNowYMDHMS());
        //objSave.setmodifyDate(Global.DateTimeNowYMDHMS());
        objSave.setLat(Double.toString(currentLatitude));
        objSave.setLon(Double.toString(currentLongitude));

        String status = objSave.SaveUpdateData(this);
        if(status.length()==0) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("res", "");
            setResult(Activity.RESULT_OK, returnIntent);

            Connection.MessageBox(Section_1_ipc_reg.this, "Saved Successfully");


        }



        else{
            Connection.MessageBox(Section_1_ipc_reg.this, status);
            return;
        }



            g.setIdNo(txtidno.getText().toString());
            g.setVDate(dtpinterviewer_date.getText().toString());
            g.setEPISubBlock(spnq106.getSelectedItem().toString());
            g.setlevel(lavel);
            g.setDistrict(Zila);
            g.setUpazila(UZila);
        if(g.getCallFrom().equalsIgnoreCase("update"))
        {

        }
        else {
            if(lavel.equalsIgnoreCase("3")) {
                g.setUnion(UN);
                g.setward(WD);
            }
            else if(lavel.equalsIgnoreCase("2")) {
                g.setUnion(UN);
            }
            else if(lavel.equalsIgnoreCase("1")) {
                g.setUnion("");
                g.setward("");
            }
        }
           // g.setUnion(UN);
           // g.setward(WD);
            finish();
            Intent f1 = new Intent(getApplicationContext(), Section_1_Manager_Service.class);
            startActivity(f1);



    }
    catch(Exception  e)
    {
        Connection.MessageBox(Section_1_ipc_reg.this, e.getMessage());
        return;
    }
}

    private void DataSearch(String idno)
    {
        try
        {


            Section_1_ipc_reg_DataModel d = new Section_1_ipc_reg_DataModel();
            String SQL = "Select * from "+ TableName +"  Where idno='"+ idno +"'";
            List<Section_1_ipc_reg_DataModel> data = d.SelectAll(this, SQL);
            for(Section_1_ipc_reg_DataModel item : data){
                txtidno.setText(item.getidno());
                spnzillaid.setSelection(Connection.SpinnerItemPositionAnyLength(spnzillaid, item.getzillaid()));
                spnupazilaid.setSelection(Connection.SpinnerItemPositionAnyLength(spnupazilaid, item.getupazilaid()));
                spnunionid.setSelection(Connection.SpinnerItemPositionAnyLength(spnunionid, item.getunionid()));
                txtunionother.setText(item.getunion_name());
                txtvillage_name.setText(item.getvill_name());
                txtslno.setText(item.getslno());
                spnq105.setSelection(Connection.SpinnerItemPositionAnyLength(spnq105, item.getq105()));
                spnq106.setSelection(Connection.SpinnerItemPositionAnyLength(spnq106, item.getq106()));
                spnq107.setSelection(Connection.SpinnerItemPositionAnyLength(spnq107, item.getq107()));
                dtpinterviewer_date.setText(item.getinterviewer_date().toString().length()==0 ? "" : Global.DateConvertDMY(item.getinterviewer_date()));
                //txtinterviewer_id.setText(item.getinterviewer_id());



            }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Section_1_ipc_reg.this, e.getMessage());
            return;
        }



    }



    protected Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mDateSetListener,g.mYear,g.mMonth-1,g.mDay);
            case TIME_DIALOG:
                return new TimePickerDialog(this, timePickerListener, hour, minute,false);
        }
        return null;
    }
private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
   public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
     mYear = year; mMonth = monthOfYear+1; mDay = dayOfMonth;
     EditText dtpDate;


             dtpDate = (EditText)findViewById(R.id.dtpinterviewer_date);
            if (VariableID.equals("btninterviewer_date"))
             {
                 dtpDate = (EditText)findViewById(R.id.dtpinterviewer_date);
             }
     dtpDate.setText(new StringBuilder()
     .append(Global.Right("00"+mDay,2)).append("/")
     .append(Global.Right("00"+mMonth,2)).append("/")
     .append(mYear));
     }
 };
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour; minute = selectedMinute;
            EditText tpTime;

/*

            tpTime = (EditText)findViewById(R.id.txtq111);
            if (VariableID.equals("btnq111"))
            {
                tpTime = (EditText)findViewById(R.id.txtq111);
            }
            tpTime.setText(new StringBuilder().append(Global.Right("00"+hour,2)).append(":").append(Global.Right("00"+minute,2)));
*/

        }
    };
/* private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
   public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
      hour = selectedHour; minute = selectedMinute;
      EditText tpTime;


         tpTime.setText(new StringBuilder().append(Global.Right("00"+hour,2)).append(":").append(Global.Right("00"+minute,2)));

   }
 };*/


//GPS Reading
//.....................................................................................................
public void FindLocation() {
LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

LocationListener locationListener = new LocationListener() {
    public void onLocationChanged(Location location) {
        updateLocation(location);
    }
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    public void onProviderEnabled(String provider) {
    }
    public void onProviderDisabled(String provider) {
    }
  };
 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
}

void updateLocation(Location location) {
    currentLocation  = location;
    currentLatitude  = currentLocation.getLatitude();
    currentLongitude = currentLocation.getLongitude();
}


// Method to turn on GPS
public void turnGPSOn(){
    try
    {
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }
    catch (Exception e) {
    }
}

// Method to turn off the GPS
public void turnGPSOff(){
    String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

    if(provider.contains("gps")){ //if gps is enabled
        final Intent poke = new Intent();
        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
        poke.setData(Uri.parse("3"));
        sendBroadcast(poke);
    }
}

// turning off the GPS if its in on state. to avoid the battery drain.
@Override
protected void onDestroy() {
    // TODO Auto-generated method stub
    super.onDestroy();
    turnGPSOff();
}
}