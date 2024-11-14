
 package com.example.mansup;


 import android.app.Activity;
 import android.app.DatePickerDialog;
 import android.app.Dialog;
 import android.content.Context;
 import android.content.Intent;
 import android.location.Location;
 import android.location.LocationListener;
 import android.location.LocationManager;
 import android.net.Uri;
 import android.os.Bundle;
 import android.provider.Settings;
 import android.text.Editable;
 import android.text.TextWatcher;
 import android.view.KeyEvent;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.Button;
 import android.widget.CheckBox;
 import android.widget.CompoundButton;
 import android.widget.DatePicker;
 import android.widget.EditText;
 import android.widget.ImageButton;
 import android.widget.LinearLayout;
 import android.widget.RadioButton;
 import android.widget.RadioGroup;
 import android.widget.SimpleAdapter;
 import android.widget.TextView;

 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.HashMap;
 import java.util.List;

 import Common.Connection;
 import Common.Global;


 public class Section_1_Manager_Service extends Activity {
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
    private int mDay;
    private int mMonth;
    private int mYear;
    static final int DATE_DIALOG = 1;
    static final int TIME_DIALOG = 2;

     Connection C;
     Global g;
    SimpleAdapter dataAdapter;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        // TextView lblHeading;
        /* LinearLayout seclblsec-2;
         View linelblsec-2;*/
         LinearLayout secidno;
         View lineidno;
         TextView Vlblidno;
         EditText txtidno;

     LinearLayout secq111;
     View lineq111;
     TextView Vlblq111;
     RadioGroup rdogrpq111;

     RadioButton rdoq1111;
     RadioButton rdoq1112;

    LinearLayout secq111_0;

     LinearLayout secq111a;
     View lineq111a;
     TextView Vlblq111a;
     CheckBox chkq111a;

     LinearLayout secq111b;
     View lineq111b;
     TextView Vlblq111b;
     CheckBox chkq111b;

     LinearLayout secq111c;
     View lineq111c;
     TextView Vlblq111c;
     CheckBox chkq111c;

     LinearLayout secq111d;
     View lineq111d;
     TextView Vlblq111d;
     CheckBox chkq111d;

     LinearLayout secq111e;
     View lineq111e;
     TextView Vlblq111e;
     CheckBox chkq111e;

     LinearLayout secq111x;
     View lineq111x;
     TextView Vlblq111x;
     CheckBox chkq111x;

     LinearLayout secq111x1;
     View lineq111x1;
     TextView Vlblq111x1;
     EditText txtq111x1;

     LinearLayout secvdate;
     View linevdate;
     TextView Vlblvdate;
     EditText txtvdate;

     LinearLayout secepisubblock;
     View lineepisubblock;
     TextView Vlblepisubblock;
     TextView txtepisubblock;


     //LinearLayout secq111;
    // View lineq111;

 /*    LinearLayout secq111a;
     View lineq111a;
     TextView Vlblq111a;
     EditText txtq111a;*/


     LinearLayout secq112;
     View lineq112;
     LinearLayout secq112a;
     View lineq112a;
     TextView Vlblq112a;
     CheckBox chkq112a;
     LinearLayout secq112a1;
     View lineq112a1;
     TextView Vlblq112a1;
     EditText txtq112a1;
     LinearLayout secq112a2;
     View lineq112a2;
     TextView Vlblq112a2;
     EditText txtq112a2;

     /*LinearLayout secq112a3;
     View lineq112a3;
     TextView Vlblq112a3;
     RadioGroup rdogrpq112a3;

     RadioButton rdoq112a31;
     RadioButton rdoq112a32;
     LinearLayout secq112a4;

     LinearLayout secq112a4a;
     View lineq112a4a;
     TextView Vlblq112a4a;
     CheckBox chkq112a4a;
     LinearLayout secq112a4b;
     View lineq112a4b;
     TextView Vlblq112a4b;
     CheckBox chkq112a4b;
     LinearLayout secq112a4c;
     View lineq112a4c;
     TextView Vlblq112a4c;
     CheckBox chkq112a4c;
     LinearLayout secq112a4d;
     View lineq112a4d;
     TextView Vlblq112a4d;
     CheckBox chkq112a4d;
     LinearLayout secq112a4e;
     View lineq112a4e;
     TextView Vlblq112a4e;
     CheckBox chkq112a4e;

     LinearLayout secq112a4x;
     View lineq112a4x;
     TextView Vlblq112a4x;
     CheckBox chkq112a4x;
     LinearLayout secq112a4x1;
     View lineq112a4x1;
     TextView Vlblq112a4x1;
     EditText txtq112a4x1;*/

    // EditText txtq112a3;
     LinearLayout secq112b;
     View lineq112b;
     TextView Vlblq112b;
     CheckBox chkq112b;
     LinearLayout secq112b1;
     View lineq112b1;
     TextView Vlblq112b1;
     EditText txtq112b1;
     LinearLayout secq112b2;
     View lineq112b2;
     TextView Vlblq112b2;
     EditText txtq112b2;

     LinearLayout secq111p1_0;
     LinearLayout secq111p1a;
     View lineq111p1a;
     TextView Vlblq111p1a;
     CheckBox chkq111p1a;

     LinearLayout secq111p1b;
     View lineq111p1b;
     TextView Vlblq111p1b;
     CheckBox chkq111p1b;

     LinearLayout secq111p1c;
     View lineq111p1c;
     TextView Vlblq111p1c;
     CheckBox chkq111p1c;

     LinearLayout secq111p1d;
     View lineq111p1d;
     TextView Vlblq111p1d;
     CheckBox chkq111p1d;

     LinearLayout secq111p1e;
     View lineq111p1e;
     TextView Vlblq111p1e;
     CheckBox chkq111p1e;

     LinearLayout secq111p1x;
     View lineq111p1x;
     TextView Vlblq111p1x;
     CheckBox chkq111p1x;

     LinearLayout secq111p1x1;
     View lineq111p1x1;
     TextView Vlblq111p1x1;
     EditText txtq111p1x1;
    /* LinearLayout secq112b3;
     View lineq112b3;
     TextView Vlblq112b3;
     RadioGroup rdogrpq112b3;

     RadioButton rdoq112b31;
     RadioButton rdoq112b32;



     LinearLayout secq112b4;
     LinearLayout secq112b4a;
     View lineq112b4a;
     TextView Vlblq112b4a;
     CheckBox chkq112b4a;
     LinearLayout secq112b4b;
     View lineq112b4b;
     TextView Vlblq112b4b;
     CheckBox chkq112b4b;
     LinearLayout secq112b4c;
     View lineq112b4c;
     TextView Vlblq112b4c;
     CheckBox chkq112b4c;
     LinearLayout secq112b4d;
     View lineq112b4d;
     TextView Vlblq112b4d;
     CheckBox chkq112b4d;
     LinearLayout secq112b4e;
     View lineq112b4e;
     TextView Vlblq112b4e;
     CheckBox chkq112b4e;
     LinearLayout secq112b4x;
     View lineq112b4x;
     TextView Vlblq112b4x;
     CheckBox chkq112b4x;
     LinearLayout secq112b4x1;
     View lineq112b4x1;
     TextView Vlblq112b4x1;
     EditText txtq112b4x1;*/

     /*LinearLayout secq112b3;
     View lineq112b3;
     TextView Vlblq112b3;*/
     //EditText txtq112b3;
     LinearLayout secq112c;
     View lineq112c;
     TextView Vlblq112c;
     CheckBox chkq112c;
     LinearLayout secq112c1;
     View lineq112c1;
     TextView Vlblq112c1;
     EditText txtq112c1;
     LinearLayout secq112c2;
     View lineq112c2;
     TextView Vlblq112c2;
     EditText txtq112c2;

     LinearLayout secq112p2_0;
     LinearLayout secq112p2a;
     View lineq112p2a;
     TextView Vlblq112p2a;
     CheckBox chkq112p2a;

     LinearLayout secq112p2b;
     View lineq112p2b;
     TextView Vlblq112p2b;
     CheckBox chkq112p2b;

     LinearLayout secq112p2c;
     View lineq112p2c;
     TextView Vlblq112p2c;
     CheckBox chkq112p2c;

     LinearLayout secq112p2d;
     View lineq112p2d;
     TextView Vlblq112p2d;
     CheckBox chkq112p2d;

     LinearLayout secq112p2e;
     View lineq112p2e;
     TextView Vlblq112p2e;
     CheckBox chkq112p2e;

     LinearLayout secq112p2x;
     View lineq112p2x;
     TextView Vlblq112p2x;
     CheckBox chkq112p2x;

     LinearLayout secq112p2x1;
     View lineq112p2x1;
     TextView Vlblq112p2x1;
     EditText txtq112p2x1;

     /*LinearLayout secq112c3;
     View lineq112c3;
     TextView Vlblq112c3;
     RadioGroup rdogrpq112c3;

     RadioButton rdoq112c31;
     RadioButton rdoq112c32;

     LinearLayout secq112c4;
     LinearLayout secq112c4a;
     View lineq112c4a;
     TextView Vlblq112c4a;
     CheckBox chkq112c4a;
     LinearLayout secq112c4b;
     View lineq112c4b;
     TextView Vlblq112c4b;
     CheckBox chkq112c4b;
     LinearLayout secq112c4c;
     View lineq112c4c;
     TextView Vlblq112c4c;
     CheckBox chkq112c4c;
     LinearLayout secq112c4d;
     View lineq112c4d;
     TextView Vlblq112c4d;
     CheckBox chkq112c4d;
     LinearLayout secq112c4e;
     View lineq112c4e;
     TextView Vlblq112c4e;
     CheckBox chkq112c4e;
     LinearLayout secq112c4x;
     View lineq112c4x;
     TextView Vlblq112c4x;
     CheckBox chkq112c4x;
     LinearLayout secq112c4x1;
     View lineq112c4x1;
     TextView Vlblq112c4x1;
     EditText txtq112c4x1;*/

     //EditText txtq112c3;
     LinearLayout secq112d;
     View lineq112d;
     TextView Vlblq112d;
     CheckBox chkq112d;
     LinearLayout secq112d1;
     View lineq112d1;
     TextView Vlblq112d1;
     EditText txtq112d1;
     LinearLayout secq112d2;
     View lineq112d2;
     TextView Vlblq112d2;
     EditText txtq112d2;

     LinearLayout secq112p3_0;
     LinearLayout secq112p3a;
     View lineq112p3a;
     TextView Vlblq112p3a;
     CheckBox chkq112p3a;

     LinearLayout secq112p3b;
     View lineq112p3b;
     TextView Vlblq112p3b;
     CheckBox chkq112p3b;

     LinearLayout secq112p3c;
     View lineq112p3c;
     TextView Vlblq112p3c;
     CheckBox chkq112p3c;

     LinearLayout secq112p3d;
     View lineq112p3d;
     TextView Vlblq112p3d;
     CheckBox chkq112p3d;

     LinearLayout secq112p3e;
     View lineq112p3e;
     TextView Vlblq112p3e;
     CheckBox chkq112p3e;

     LinearLayout secq112p3x;
     View lineq112p3x;
     TextView Vlblq112p3x;
     CheckBox chkq112p3x;

     LinearLayout secq112p3x1;
     View lineq112p3x1;
     TextView Vlblq112p3x1;
     EditText txtq112p3x1;

     /*LinearLayout secq112d3;
     View lineq112d3;
     TextView Vlblq112d3;
     //EditText txtq112d3;
     RadioGroup rdogrpq112d3;

  RadioButton rdoq112d31;
  RadioButton rdoq112d32;

     LinearLayout secq112d4;
     LinearLayout secq112d4a;
     View lineq112d4a;
     TextView Vlblq112d4a;
     CheckBox chkq112d4a;
     LinearLayout secq112d4b;
     View lineq112d4b;
     TextView Vlblq112d4b;
     CheckBox chkq112d4b;
     LinearLayout secq112d4c;
     View lineq112d4c;
     TextView Vlblq112d4c;
     CheckBox chkq112d4c;
     LinearLayout secq112d4d;
     View lineq112d4d;
     TextView Vlblq112d4d;
     CheckBox chkq112d4d;
     LinearLayout secq112d4e;
     View lineq112d4e;
     TextView Vlblq112d4e;
     CheckBox chkq112d4e;
     LinearLayout secq112d4x;
     View lineq112d4x;
     TextView Vlblq112d4x;
     CheckBox chkq112d4x;
     LinearLayout secq112d4x1;
     View lineq112d4x1;
     TextView Vlblq112d4x1;
     EditText txtq112d4x1;*/

     LinearLayout secq112e;
     View lineq112e;
     TextView Vlblq112e;
     CheckBox chkq112e;
     LinearLayout secq112e1;
     View lineq112e1;
     TextView Vlblq112e1;
     EditText txtq112e1;
     LinearLayout secq112e2;
     View lineq112e2;
     TextView Vlblq112e2;
     EditText txtq112e2;

 /*    LinearLayout secq112e3;
     View lineq112e3;
     TextView Vlblq112e3;
     EditText txtq112e3;*/

     LinearLayout secq112f;
     View lineq112f;
     TextView Vlblq112f;
     CheckBox chkq112f;
     LinearLayout secq112f1;
     View lineq112f1;
     TextView Vlblq112f1;
     EditText txtq112f1;
     LinearLayout secq112f2;
     View lineq112f2;
     TextView Vlblq112f2;
     EditText txtq112f2;

 /*    LinearLayout secq112f3;
     View lineq112f3;
     TextView Vlblq112f3;
     EditText txtq112f3;*/

     LinearLayout secq112g;
     View lineq112g;
     TextView Vlblq112g;
     CheckBox chkq112g;
     LinearLayout secq112g1;
     View lineq112g1;
     TextView Vlblq112g1;
     EditText txtq112g1;
     LinearLayout secq112g2;
     View lineq112g2;
     TextView Vlblq112g2;
     EditText txtq112g2;

/*     LinearLayout secq112g3;
     View lineq112g3;
     TextView Vlblq112g3;
     EditText txtq112g3;*/

     LinearLayout secq112h;
     View lineq112h;
     TextView Vlblq112h;
     CheckBox chkq112h;
     LinearLayout secq112h1;
     View lineq112h1;
     TextView Vlblq112h1;
     EditText txtq112h1;
     LinearLayout secq112h2;
     View lineq112h2;
     TextView Vlblq112h2;
     EditText txtq112h2;

/*     LinearLayout secq112h3;
     View lineq112h3;
     TextView Vlblq112h3;
     EditText txtq112h3;*/

     LinearLayout secq112i;
     View lineq112i;
     TextView Vlblq112i;
     CheckBox chkq112i;
     LinearLayout secq112i1;
     View lineq112i1;
     TextView Vlblq112i1;
     EditText txtq112i1;
     LinearLayout secq112i2;
     View lineq112i2;
     TextView Vlblq112i2;
     EditText txtq112i2;

/*     LinearLayout secq112i3;
     View lineq112i3;
     TextView Vlblq112i3;
     EditText txtq112i3;*/

     LinearLayout secq112j;
     View lineq112j;
     TextView Vlblq112j;
     CheckBox chkq112j;
     LinearLayout secq112j1;
     View lineq112j1;
     TextView Vlblq112j1;
     EditText txtq112j1;
     LinearLayout secq112j2;
     View lineq112j2;
     TextView Vlblq112j2;
     EditText txtq112j2;

/*     LinearLayout secq112j3;
     View lineq112j3;
     TextView Vlblq112j3;
     EditText txtq112j3;*/

     LinearLayout secq112k;
     View lineq112k;
     TextView Vlblq112k;
     CheckBox chkq112k;
     LinearLayout secq112k1;
     View lineq112k1;
     TextView Vlblq112k1;
     EditText txtq112k1;
     LinearLayout secq112k2;
     View lineq112k2;
     TextView Vlblq112k2;
     EditText txtq112k2;

     LinearLayout secq112m1_0;
     LinearLayout secq112m1a;
     View lineq112m1a;
     TextView Vlblq112m1a;
     CheckBox chkq112m1a;

     LinearLayout secq112m1b;
     View lineq112m1b;
     TextView Vlblq112m1b;
     CheckBox chkq112m1b;

     LinearLayout secq112m1c;
     View lineq112m1c;
     TextView Vlblq112m1c;
     CheckBox chkq112m1c;

     LinearLayout secq112m1d;
     View lineq112m1d;
     TextView Vlblq112m1d;
     CheckBox chkq112m1d;

     LinearLayout secq112m1e;
     View lineq112m1e;
     TextView Vlblq112m1e;
     CheckBox chkq112m1e;

     LinearLayout secq112m1x;
     View lineq112m1x;
     TextView Vlblq112m1x;
     CheckBox chkq112m1x;

     LinearLayout secq112m1x1;
     View lineq112m1x1;
     TextView Vlblq112m1x1;
     EditText txtq112m1x1;

     /*LinearLayout secq112k3;
     View lineq112k3;
     TextView Vlblq112k3;
     //EditText txtq112k3;
     RadioGroup rdogrpq112k3;

     RadioButton rdoq112k31;
     RadioButton rdoq112k32;
     LinearLayout secq112k4;
     LinearLayout secq112k4a;
     View lineq112k4a;
     TextView Vlblq112k4a;
     CheckBox chkq112k4a;
     LinearLayout secq112k4b;
     View lineq112k4b;
     TextView Vlblq112k4b;
     CheckBox chkq112k4b;
     LinearLayout secq112k4c;
     View lineq112k4c;
     TextView Vlblq112k4c;
     CheckBox chkq112k4c;
     LinearLayout secq112k4d;
     View lineq112k4d;
     TextView Vlblq112k4d;
     CheckBox chkq112k4d;
     LinearLayout secq112k4e;
     View lineq112k4e;
     TextView Vlblq112k4e;
     CheckBox chkq112k4e;
     LinearLayout secq112k4x;
     View lineq112k4x;
     TextView Vlblq112k4x;
     CheckBox chkq112k4x;
     LinearLayout secq112k4x1;
     View lineq112k4x1;
     TextView Vlblq112k4x1;
     EditText txtq112k4x1;
*/
     LinearLayout secq112l;
     View lineq112l;
     TextView Vlblq112l;
     CheckBox chkq112l;
     LinearLayout secq112l1;
     View lineq112l1;
     TextView Vlblq112l1;
     EditText txtq112l1;
     LinearLayout secq112l2;
     View lineq112l2;
     TextView Vlblq112l2;
     EditText txtq112l2;

     LinearLayout secq113;
     View lineq113;
     TextView Vlblq113;
     EditText txtq113;

  /*   LinearLayout secq112l3;
     View lineq112l3;
     TextView Vlblq112l3;
     EditText txtq112l3;*/

     /*LinearLayout secq113;
     View lineq113;
     LinearLayout secq113a1;
     View lineq113a1;
     TextView Vlblq113a1;
     EditText txtq113a1;
     LinearLayout secq113a2;
     View lineq113a2;
     TextView Vlblq113a2;
     EditText txtq113a2;
     LinearLayout secq113b1;
     View lineq113b1;
     TextView Vlblq113b1;
     EditText txtq113b1;
     LinearLayout secq113b2;
     View lineq113b2;
     TextView Vlblq113b2;
     EditText txtq113b2;
     LinearLayout secq113c1;
     View lineq113c1;
     TextView Vlblq113c1;
     EditText txtq113c1;
     LinearLayout secq113c2;
     View lineq113c2;
     TextView Vlblq113c2;
     EditText txtq113c2;
     LinearLayout secq113d1;
     View lineq113d1;
     TextView Vlblq113d1;
     EditText txtq113d1;
     LinearLayout secq113d2;
     View lineq113d2;
     TextView Vlblq113d2;
     EditText txtq113d2;*/

 /*    CheckBox chkq113ap1;
     CheckBox chkq113ap2;
     CheckBox chkq113ap3;
     CheckBox chkq113ap4;*/

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

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.section_1_manager);
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
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

             }
             else if (lavel.equalsIgnoreCase("1"))
             {

             }
         }

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
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
                 finish();
                 Intent f1 = new Intent(getApplicationContext(), Section_1_ipc_reg.class);
                 startActivity(f1);
             }
         });

         ImageButton cmdClose = (ImageButton) findViewById(R.id.cmdClose);
         cmdClose.setOnClickListener(new View.OnClickListener() {
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
                 finish();
                 Intent f1 = new Intent(getApplicationContext(), Section_1_ipc_reg.class);
                 startActivity(f1);
             }
         });
         // DEVICEID  = sp.getValue(this, "deviceid");
         // ENTRYUSER = sp.getValue(this, "userid");

        // DEVICEID  = C.ReturnSingleValue("select ifnull(deviceno,'9999') as deviceno from deviceno");;
         //ENTRYUSER =  C.ReturnSingleValue("Select ifnull(userid,'999') as deviceno  from login");
        /* C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
        // DEVICEID  = sp.getValue(this, "deviceid");
        // ENTRYUSER = sp.getValue(this, "userid");

         IDbundle = getIntent().getExtras();
         IDNO = IDbundle.getString("idno");*/

         TableName = "section_1_manager_staff_service";
         secidno=(LinearLayout)findViewById(R.id.secidno);
         lineidno=(View)findViewById(R.id.lineidno);
         Vlblidno=(TextView) findViewById(R.id.Vlblidno);
         txtidno=(EditText) findViewById(R.id.txtidno);
         txtidno.setText(g.getIdNo());
         txtidno.setEnabled(false);

         secvdate=(LinearLayout)findViewById(R.id.secvdate);
         linevdate=(View)findViewById(R.id.linevdate);
         Vlblvdate=(TextView) findViewById(R.id.Vlblvdate);
         txtvdate=(EditText) findViewById(R.id.txtvdate);
         txtvdate.setText(g.getVDate());
         txtvdate.setEnabled(false);


         secepisubblock=(LinearLayout)findViewById(R.id.secepisubblock);
         lineepisubblock=(View)findViewById(R.id.lineepisubblock);
         Vlblepisubblock=(TextView) findViewById(R.id.Vlblepisubblock);
         txtepisubblock=(TextView) findViewById(R.id.txtepisubblock);
         txtepisubblock.setText(g.getEPISubBlock());

       //  secq111=(LinearLayout)findViewById(R.id.secq111);
        // lineq111=(View)findViewById(R.id.lineq111);


         secq112=(LinearLayout)findViewById(R.id.secq112);
         lineq112=(View)findViewById(R.id.lineq112);
         secq112a=(LinearLayout)findViewById(R.id.secq112a);
         lineq112a=(View)findViewById(R.id.lineq112a);
         Vlblq112a=(TextView) findViewById(R.id.Vlblq112a);
         chkq112a=(CheckBox) findViewById(R.id.chkq112a);

         chkq112a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112a.isChecked() == true) {
                    secq112a1.setVisibility(View.VISIBLE);
                     lineq112a1.setVisibility(View.VISIBLE);
                     secq112a2.setVisibility(View.VISIBLE);
                     lineq112a2.setVisibility(View.VISIBLE);
                    // secq112a3.setVisibility(View.VISIBLE);
                    // lineq112a3.setVisibility(View.VISIBLE);

                 } else if (chkq112a.isChecked() == false) {
                     txtq112a1.setText("");
                     txtq112a2.setText("");
                     rdogrpq111.clearCheck();
                    // txtq112a3.setText("");
                     secq112a1.setVisibility(View.GONE);
                     lineq112a1.setVisibility(View.GONE);
                     secq112a2.setVisibility(View.GONE);
                     lineq112a2.setVisibility(View.GONE);
                    // secq112a3.setVisibility(View.GONE);
                    // lineq112a3.setVisibility(View.GONE);
                    /* secq112a4.setVisibility(View.GONE);
                     // lineq112a4.setVisibility(View.GONE);
                     secq112a4a.setVisibility(View.GONE);
                     lineq112a4a.setVisibility(View.GONE);
                     secq112a4b.setVisibility(View.GONE);
                     lineq112a4b.setVisibility(View.GONE);
                     secq112a4c.setVisibility(View.GONE);
                     lineq112a4c.setVisibility(View.GONE);
                     secq112a4d.setVisibility(View.GONE);
                     lineq112a4d.setVisibility(View.GONE);
                     secq112a4e.setVisibility(View.GONE);
                     lineq112a4e.setVisibility(View.GONE);
                     secq112a4x.setVisibility(View.GONE);
                     lineq112a4x.setVisibility(View.GONE);
                     chkq112a4a.setChecked(false);
                     chkq112a4b.setChecked(false);
                     chkq112a4c.setChecked(false);
                     chkq112a4d.setChecked(false);
                     chkq112a4e.setChecked(false);
                     chkq112a4x.setChecked(false);*/


                 }

             }
         });
         secq112a1=(LinearLayout)findViewById(R.id.secq112a1);
         lineq112a1=(View)findViewById(R.id.lineq112a1);
         Vlblq112a1=(TextView) findViewById(R.id.Vlblq112a1);
         txtq112a1=(EditText) findViewById(R.id.txtq112a1);
         txtq112a1.addTextChangedListener(new TextWatcher() {
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {

                     try {
                         Integer a=Integer.valueOf(txtq112a1.getText().toString());
                         Integer b=Integer.valueOf(txtq112a2.getText().toString());

                         if(a>b)
                         {
                             secq111_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq111a.setVisibility(View.VISIBLE);
                             lineq111a.setVisibility(View.VISIBLE);
                             secq111b.setVisibility(View.VISIBLE);
                             lineq111b.setVisibility(View.VISIBLE);
                             secq111c.setVisibility(View.VISIBLE);
                             lineq111c.setVisibility(View.VISIBLE);
                             secq111d.setVisibility(View.VISIBLE);
                             lineq111d.setVisibility(View.VISIBLE);
                             secq111e.setVisibility(View.VISIBLE);
                             lineq111e.setVisibility(View.VISIBLE);
                             secq111x.setVisibility(View.VISIBLE);
                             lineq111x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq111_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq111a.setVisibility(View.GONE);
                             lineq111a.setVisibility(View.GONE);
                             secq111b.setVisibility(View.GONE);
                             lineq111b.setVisibility(View.GONE);
                             secq111c.setVisibility(View.GONE);
                             lineq111c.setVisibility(View.GONE);
                             secq111d.setVisibility(View.GONE);
                             lineq111d.setVisibility(View.GONE);
                             secq111e.setVisibility(View.GONE);
                             lineq111e.setVisibility(View.GONE);
                             secq111x.setVisibility(View.GONE);
                             lineq111x.setVisibility(View.GONE);
                             chkq111a.setChecked(false);
                             chkq111b.setChecked(false);
                             chkq111c.setChecked(false);
                             chkq111d.setChecked(false);
                             chkq111e.setChecked(false);
                             chkq111x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });

         secq112a2=(LinearLayout)findViewById(R.id.secq112a2);
         lineq112a2=(View)findViewById(R.id.lineq112a2);
         Vlblq112a2=(TextView) findViewById(R.id.Vlblq112a2);
         txtq112a2=(EditText) findViewById(R.id.txtq112a2);
         txtq112a2.addTextChangedListener(new TextWatcher() {

             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                       try {
                         Integer a=Integer.valueOf(txtq112a1.getText().toString());
                         Integer b=Integer.valueOf(txtq112a2.getText().toString());

                         if(a>b)
                         {
                              secq111_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq111a.setVisibility(View.VISIBLE);
                             lineq111a.setVisibility(View.VISIBLE);
                             secq111b.setVisibility(View.VISIBLE);
                             lineq111b.setVisibility(View.VISIBLE);
                             secq111c.setVisibility(View.VISIBLE);
                             lineq111c.setVisibility(View.VISIBLE);
                             secq111d.setVisibility(View.VISIBLE);
                             lineq111d.setVisibility(View.VISIBLE);
                             secq111e.setVisibility(View.VISIBLE);
                             lineq111e.setVisibility(View.VISIBLE);
                             secq111x.setVisibility(View.VISIBLE);
                             lineq111x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq111_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq111a.setVisibility(View.GONE);
                             lineq111a.setVisibility(View.GONE);
                             secq111b.setVisibility(View.GONE);
                             lineq111b.setVisibility(View.GONE);
                             secq111c.setVisibility(View.GONE);
                             lineq111c.setVisibility(View.GONE);
                             secq111d.setVisibility(View.GONE);
                             lineq111d.setVisibility(View.GONE);
                             secq111e.setVisibility(View.GONE);
                             lineq111e.setVisibility(View.GONE);
                             secq111x.setVisibility(View.GONE);
                             lineq111x.setVisibility(View.GONE);

                             chkq111a.setChecked(false);
                             chkq111b.setChecked(false);
                             chkq111c.setChecked(false);
                             chkq111d.setChecked(false);
                             chkq111e.setChecked(false);
                             chkq111x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });
         secq111=(LinearLayout)findViewById(R.id.secq111);
         lineq111=(View)findViewById(R.id.lineq111);
         Vlblq111 = (TextView) findViewById(R.id.Vlblq111);
         rdogrpq111 = (RadioGroup) findViewById(R.id.rdogrpq111);

         rdoq1111 = (RadioButton) findViewById(R.id.rdoq1111);
         rdoq1112 = (RadioButton) findViewById(R.id.rdoq1112);

         rdogrpq111.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrp111 = new String[] {"1","2"};
                 for (int i = 0; i < rdogrpq111.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpq111.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrp111[i];
                 }
                 if(rbData.equalsIgnoreCase("1"))
                 {
                    // secq111.setVisibility(View.VISIBLE);
                    /* secq111_0.setVisibility(View.VISIBLE);
                    // lineq112a4.setVisibility(View.GONE);
                     secq111a.setVisibility(View.VISIBLE);
                     lineq111a.setVisibility(View.VISIBLE);
                     secq111b.setVisibility(View.VISIBLE);
                     lineq111b.setVisibility(View.VISIBLE);
                     secq111c.setVisibility(View.VISIBLE);
                     lineq111c.setVisibility(View.VISIBLE);
                     secq111d.setVisibility(View.VISIBLE);
                     lineq111d.setVisibility(View.VISIBLE);
                     secq111e.setVisibility(View.VISIBLE);
                     lineq111e.setVisibility(View.VISIBLE);
                     secq111x.setVisibility(View.VISIBLE);
                     lineq111x.setVisibility(View.VISIBLE);*/


                     secq111_0.setVisibility(View.GONE);
                     // lineq112a4.setVisibility(View.GONE);
                     secq111a.setVisibility(View.GONE);
                     lineq111a.setVisibility(View.GONE);
                     secq111b.setVisibility(View.GONE);
                     lineq111b.setVisibility(View.GONE);
                     secq111c.setVisibility(View.GONE);
                     lineq111c.setVisibility(View.GONE);
                     secq111d.setVisibility(View.GONE);
                     lineq111d.setVisibility(View.GONE);
                     secq111e.setVisibility(View.GONE);
                     lineq111e.setVisibility(View.GONE);
                     secq111x.setVisibility(View.GONE);
                     lineq111x.setVisibility(View.GONE);

                     secq112.setVisibility(View.VISIBLE);
                     lineq112.setVisibility(View.VISIBLE);

                     secq112a.setVisibility(View.VISIBLE);
                     lineq112a.setVisibility(View.VISIBLE);

                     secq112b.setVisibility(View.VISIBLE);
                     lineq112b.setVisibility(View.VISIBLE);

                     secq112c.setVisibility(View.VISIBLE);
                     lineq112c.setVisibility(View.VISIBLE);

                     secq112d.setVisibility(View.VISIBLE);
                     lineq112d.setVisibility(View.VISIBLE);

                     secq112k.setVisibility(View.VISIBLE);
                     lineq112k.setVisibility(View.VISIBLE);



                 }
                 else if(rbData.equalsIgnoreCase("2"))
                 {
                    // secq111.setVisibility(View.GONE);
                     secq111_0.setVisibility(View.GONE);
                     // lineq112a4.setVisibility(View.GONE);
                     secq111a.setVisibility(View.GONE);
                     lineq111a.setVisibility(View.GONE);
                     secq111b.setVisibility(View.GONE);
                     lineq111b.setVisibility(View.GONE);
                     secq111c.setVisibility(View.GONE);
                     lineq111c.setVisibility(View.GONE);
                     secq111d.setVisibility(View.GONE);
                     lineq111d.setVisibility(View.GONE);
                     secq111e.setVisibility(View.GONE);
                     lineq111e.setVisibility(View.GONE);
                     secq111x.setVisibility(View.GONE);
                     lineq111x.setVisibility(View.GONE);

                     secq112.setVisibility(View.GONE);
                     lineq112.setVisibility(View.GONE);

                     secq112a.setVisibility(View.GONE);
                     lineq112a.setVisibility(View.GONE);

                     secq112b.setVisibility(View.GONE);
                     lineq112b.setVisibility(View.GONE);

                     secq112c.setVisibility(View.GONE);
                     lineq112c.setVisibility(View.GONE);

                     secq112d.setVisibility(View.GONE);
                     lineq112d.setVisibility(View.GONE);

                     secq112k.setVisibility(View.GONE);
                     lineq112k.setVisibility(View.GONE);

                     chkq112a.setChecked(false);
                     chkq112b.setChecked(false);
                     chkq112c.setChecked(false);
                     chkq112d.setChecked(false);
                     chkq112k.setChecked(false);


                     chkq111a.setChecked(false);
                     chkq111b.setChecked(false);
                     chkq111c.setChecked(false);
                     chkq111d.setChecked(false);
                     chkq111e.setChecked(false);
                     chkq111x.setChecked(false);

                     secq111p1_0.setVisibility(View.GONE);
                     // lineq112a4.setVisibility(View.GONE);
                     secq111p1a.setVisibility(View.GONE);
                     lineq111p1a.setVisibility(View.GONE);
                     secq111p1b.setVisibility(View.GONE);
                     lineq111p1b.setVisibility(View.GONE);
                     secq111p1c.setVisibility(View.GONE);
                     lineq111p1c.setVisibility(View.GONE);
                     secq111p1d.setVisibility(View.GONE);
                     lineq111p1d.setVisibility(View.GONE);
                     secq111p1e.setVisibility(View.GONE);
                     lineq111p1e.setVisibility(View.GONE);
                     secq111p1x.setVisibility(View.GONE);
                     lineq111p1x.setVisibility(View.GONE);

                     chkq111p1a.setChecked(false);
                     chkq111p1b.setChecked(false);
                     chkq111p1c.setChecked(false);
                     chkq111p1d.setChecked(false);
                     chkq111p1e.setChecked(false);
                     chkq111p1x.setChecked(false);

                     secq112p2_0.setVisibility(View.GONE);
                     // lineq112a4.setVisibility(View.GONE);
                     secq112p2a.setVisibility(View.GONE);
                     lineq112p2a.setVisibility(View.GONE);
                     secq112p2b.setVisibility(View.GONE);
                     lineq112p2b.setVisibility(View.GONE);
                     secq112p2c.setVisibility(View.GONE);
                     lineq112p2c.setVisibility(View.GONE);
                     secq112p2d.setVisibility(View.GONE);
                     lineq112p2d.setVisibility(View.GONE);
                     secq112p2e.setVisibility(View.GONE);
                     lineq112p2e.setVisibility(View.GONE);
                     secq112p2x.setVisibility(View.GONE);
                     lineq112p2x.setVisibility(View.GONE);


                     chkq112p2a.setChecked(false);
                     chkq112p2b.setChecked(false);
                     chkq112p2c.setChecked(false);
                     chkq112p2d.setChecked(false);
                     chkq112p2e.setChecked(false);
                     chkq112p2x.setChecked(false);

                     secq112p3_0.setVisibility(View.GONE);
                     // lineq112a4.setVisibility(View.GONE);
                     secq112p3a.setVisibility(View.GONE);
                     lineq112p3a.setVisibility(View.GONE);
                     secq112p3b.setVisibility(View.GONE);
                     lineq112p3b.setVisibility(View.GONE);
                     secq112p3c.setVisibility(View.GONE);
                     lineq112p3c.setVisibility(View.GONE);
                     secq112p3d.setVisibility(View.GONE);
                     lineq112p3d.setVisibility(View.GONE);
                     secq112p3e.setVisibility(View.GONE);
                     lineq112p3e.setVisibility(View.GONE);
                     secq112p3x.setVisibility(View.GONE);
                     lineq112p3x.setVisibility(View.GONE);
                     secq112p3x1.setVisibility(View.GONE);
                     lineq112p3x1.setVisibility(View.GONE);

                     chkq112p3a.setChecked(false);
                     chkq112p3b.setChecked(false);
                     chkq112p3c.setChecked(false);
                     chkq112p3d.setChecked(false);
                     chkq112p3e.setChecked(false);
                     chkq112p3x.setChecked(false);

                     secq112p2_0.setVisibility(View.GONE);
                     // lineq112a4.setVisibility(View.GONE);
                     secq112p2a.setVisibility(View.GONE);
                     lineq112p2a.setVisibility(View.GONE);
                     secq112p2b.setVisibility(View.GONE);
                     lineq112p2b.setVisibility(View.GONE);
                     secq112p2c.setVisibility(View.GONE);
                     lineq112p2c.setVisibility(View.GONE);
                     secq112p2d.setVisibility(View.GONE);
                     lineq112p2d.setVisibility(View.GONE);
                     secq112p2e.setVisibility(View.GONE);
                     lineq112p2e.setVisibility(View.GONE);
                     secq112p2x.setVisibility(View.GONE);
                     lineq112p2x.setVisibility(View.GONE);


                     chkq112p2a.setChecked(false);
                     chkq112p2b.setChecked(false);
                     chkq112p2c.setChecked(false);
                     chkq112p2d.setChecked(false);
                     chkq112p2e.setChecked(false);
                     chkq112p2x.setChecked(false);


                 }

             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });

         secq111=(LinearLayout)findViewById(R.id.secq111);
         secq111_0=(LinearLayout)findViewById(R.id.secq111_0);
         secq111a=(LinearLayout)findViewById(R.id.secq111a);
         lineq111a=(View)findViewById(R.id.lineq111a);
         Vlblq111a=(TextView) findViewById(R.id.Vlblq111a);
         chkq111a=(CheckBox) findViewById(R.id.chkq111a);
         secq111b=(LinearLayout)findViewById(R.id.secq111b);
         lineq111b=(View)findViewById(R.id.lineq111b);
         Vlblq111b=(TextView) findViewById(R.id.Vlblq111b);
         chkq111b=(CheckBox) findViewById(R.id.chkq111b);
         secq111c=(LinearLayout)findViewById(R.id.secq111c);
         lineq111c=(View)findViewById(R.id.lineq111c);
         Vlblq111c=(TextView) findViewById(R.id.Vlblq111c);
         chkq111c=(CheckBox) findViewById(R.id.chkq111c);
         secq111d=(LinearLayout)findViewById(R.id.secq111d);
         lineq111d=(View)findViewById(R.id.lineq111d);
         Vlblq111d=(TextView) findViewById(R.id.Vlblq111d);
         chkq111d=(CheckBox) findViewById(R.id.chkq111d);
         secq111e=(LinearLayout)findViewById(R.id.secq111e);
         lineq111e=(View)findViewById(R.id.lineq111e);
         Vlblq111e=(TextView) findViewById(R.id.Vlblq111e);
         chkq111e=(CheckBox) findViewById(R.id.chkq111e);
         secq111x=(LinearLayout)findViewById(R.id.secq111x);
         lineq111x=(View)findViewById(R.id.lineq111x);
         Vlblq111x=(TextView) findViewById(R.id.Vlblq111x);

         chkq111x=(CheckBox) findViewById(R.id.chkq111x);
         chkq111x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq111x.isChecked() == true) {

                     secq111x1.setVisibility(View.VISIBLE);
                     lineq111x1.setVisibility(View.VISIBLE);
                 } else if (chkq111x.isChecked() == false) {
                     secq111x1.setVisibility(View.GONE);
                     lineq111x1.setVisibility(View.GONE);

                     txtq111x1.setText("");


                 }

             }
         });

         secq111x1=(LinearLayout)findViewById(R.id.secq111x1);
         lineq111x1=(View)findViewById(R.id.lineq111x1);
         Vlblq111x1=(TextView) findViewById(R.id.Vlblq111x1);
         txtq111x1=(EditText) findViewById(R.id.txtq111x1);

        /* secq112a3=(LinearLayout)findViewById(R.id.secq112a3);
         lineq112a3=(View)findViewById(R.id.lineq112a3);
         Vlblq112a3=(TextView) findViewById(R.id.Vlblq112a3);*/
       //  txtq112a3=(EditText) findViewById(R.id.txtq112a3);

        /* secq112b3=(LinearLayout)findViewById(R.id.secq112b3);
         lineq112b3=(View)findViewById(R.id.lineq112b3);
         Vlblq112b3 = (TextView) findViewById(R.id.Vlblq112b3);
         rdogrpq112b3 = (RadioGroup) findViewById(R.id.rdogrpq112b3);

         rdoq112b31 = (RadioButton) findViewById(R.id.rdoq112b31);
         rdoq112b32 = (RadioButton) findViewById(R.id.rdoq112b32);

         rdogrpq112b3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrp112b3 = new String[] {"1","2"};
                 for (int i = 0; i < rdogrpq112b3.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpq112b3.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrp112b3[i];
                 }
                 if(rbData.equalsIgnoreCase("1"))
                 {
                     secq112b4.setVisibility(View.VISIBLE);
                     // lineq112b4.setVisibility(View.GONE);
                     secq112b4a.setVisibility(View.VISIBLE);
                     lineq112b4a.setVisibility(View.VISIBLE);
                     secq112b4b.setVisibility(View.VISIBLE);
                     lineq112b4b.setVisibility(View.VISIBLE);
                     secq112b4c.setVisibility(View.VISIBLE);
                     lineq112b4c.setVisibility(View.VISIBLE);
                     secq112b4d.setVisibility(View.VISIBLE);
                     lineq112b4d.setVisibility(View.VISIBLE);
                     secq112b4e.setVisibility(View.VISIBLE);
                     lineq112b4e.setVisibility(View.VISIBLE);
                     secq112b4x.setVisibility(View.VISIBLE);
                     lineq112b4x.setVisibility(View.VISIBLE);



                 }
                 else if(rbData.equalsIgnoreCase("2"))
                 {
                     secq112b4.setVisibility(View.GONE);
                     // lineq112b4.setVisibility(View.GONE);
                     secq112b4a.setVisibility(View.GONE);
                     lineq112b4a.setVisibility(View.GONE);
                     secq112b4b.setVisibility(View.GONE);
                     lineq112b4b.setVisibility(View.GONE);
                     secq112b4c.setVisibility(View.GONE);
                     lineq112b4c.setVisibility(View.GONE);
                     secq112b4d.setVisibility(View.GONE);
                     lineq112b4d.setVisibility(View.GONE);
                     secq112b4e.setVisibility(View.GONE);
                     lineq112b4e.setVisibility(View.GONE);
                     secq112b4x.setVisibility(View.GONE);
                     lineq112b4x.setVisibility(View.GONE);
                     chkq112b4a.setChecked(false);
                     chkq112b4b.setChecked(false);
                     chkq112b4c.setChecked(false);
                     chkq112b4d.setChecked(false);
                     chkq112b4e.setChecked(false);
                     chkq112b4x.setChecked(false);



                 }

             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });*/

         secq112b=(LinearLayout)findViewById(R.id.secq112b);
         lineq112b=(View)findViewById(R.id.lineq112b);
         Vlblq112b=(TextView) findViewById(R.id.Vlblq112b);
         chkq112b=(CheckBox) findViewById(R.id.chkq112b);
         chkq112b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112b.isChecked() == true) {



                     secq112b1.setVisibility(View.VISIBLE);
                     lineq112b1.setVisibility(View.VISIBLE);
                     secq112b2.setVisibility(View.VISIBLE);
                     lineq112b2.setVisibility(View.VISIBLE);
                     //secq112b3.setVisibility(View.VISIBLE);
                     //lineq112b3.setVisibility(View.VISIBLE);


                 } else if (chkq112b.isChecked() == false) {



                     txtq112b1.setText("");
                     txtq112b2.setText("");
                    // txtq112b3.setText("");
                     secq112b1.setVisibility(View.GONE);
                     lineq112b1.setVisibility(View.GONE);
                     secq112b2.setVisibility(View.GONE);
                     lineq112b2.setVisibility(View.GONE);
                   // secq112b3.setVisibility(View.GONE);
                   // lineq112b3.setVisibility(View.GONE);


                 }

             }
         });
         secq112b1=(LinearLayout)findViewById(R.id.secq112b1);
         lineq112b1=(View)findViewById(R.id.lineq112b1);
         Vlblq112b1=(TextView) findViewById(R.id.Vlblq112b1);
         txtq112b1=(EditText) findViewById(R.id.txtq112b1);
         txtq112b1.addTextChangedListener(new TextWatcher() {

             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                   try {
                         Integer a=Integer.valueOf(txtq112b1.getText().toString());
                         Integer b=Integer.valueOf(txtq112b2.getText().toString());

                         if(a>b)
                         {

                             secq111p1_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq111p1a.setVisibility(View.VISIBLE);
                             lineq111p1a.setVisibility(View.VISIBLE);
                             secq111p1b.setVisibility(View.VISIBLE);
                             lineq111p1b.setVisibility(View.VISIBLE);
                             secq111p1c.setVisibility(View.VISIBLE);
                             lineq111p1c.setVisibility(View.VISIBLE);
                             secq111p1d.setVisibility(View.VISIBLE);
                             lineq111p1d.setVisibility(View.VISIBLE);
                             secq111p1e.setVisibility(View.VISIBLE);
                             lineq111p1e.setVisibility(View.VISIBLE);
                             secq111p1x.setVisibility(View.VISIBLE);
                             lineq111p1x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq111p1_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq111p1a.setVisibility(View.GONE);
                             lineq111p1a.setVisibility(View.GONE);
                             secq111p1b.setVisibility(View.GONE);
                             lineq111p1b.setVisibility(View.GONE);
                             secq111p1c.setVisibility(View.GONE);
                             lineq111p1c.setVisibility(View.GONE);
                             secq111p1d.setVisibility(View.GONE);
                             lineq111p1d.setVisibility(View.GONE);
                             secq111p1e.setVisibility(View.GONE);
                             lineq111p1e.setVisibility(View.GONE);
                             secq111p1x.setVisibility(View.GONE);
                             lineq111p1x.setVisibility(View.GONE);
                             chkq111p1a.setChecked(false);
                             chkq111p1b.setChecked(false);
                             chkq111p1c.setChecked(false);
                             chkq111p1d.setChecked(false);
                             chkq111p1e.setChecked(false);
                             chkq111p1x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });
         secq112b2=(LinearLayout)findViewById(R.id.secq112b2);
         lineq112b2=(View)findViewById(R.id.lineq112b2);
         Vlblq112b2=(TextView) findViewById(R.id.Vlblq112b2);
         txtq112b2=(EditText) findViewById(R.id.txtq112b2);

         txtq112b2.addTextChangedListener(new TextWatcher() {
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                      try {
                         Integer a=Integer.valueOf(txtq112b1.getText().toString());
                         Integer b=Integer.valueOf(txtq112b2.getText().toString());
                         if(a>b)
                         {
                             secq111p1_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq111p1a.setVisibility(View.VISIBLE);
                             lineq111p1a.setVisibility(View.VISIBLE);
                             secq111p1b.setVisibility(View.VISIBLE);
                             lineq111p1b.setVisibility(View.VISIBLE);
                             secq111p1c.setVisibility(View.VISIBLE);
                             lineq111p1c.setVisibility(View.VISIBLE);
                             secq111p1d.setVisibility(View.VISIBLE);
                             lineq111p1d.setVisibility(View.VISIBLE);
                             secq111p1e.setVisibility(View.VISIBLE);
                             lineq111p1e.setVisibility(View.VISIBLE);
                             secq111p1x.setVisibility(View.VISIBLE);
                             lineq111p1x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq111p1_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq111p1a.setVisibility(View.GONE);
                             lineq111p1a.setVisibility(View.GONE);
                             secq111p1b.setVisibility(View.GONE);
                             lineq111p1b.setVisibility(View.GONE);
                             secq111p1c.setVisibility(View.GONE);
                             lineq111p1c.setVisibility(View.GONE);
                             secq111p1d.setVisibility(View.GONE);
                             lineq111p1d.setVisibility(View.GONE);
                             secq111p1e.setVisibility(View.GONE);
                             lineq111p1e.setVisibility(View.GONE);
                             secq111p1x.setVisibility(View.GONE);
                             lineq111p1x.setVisibility(View.GONE);
                             chkq111p1a.setChecked(false);
                             chkq111p1b.setChecked(false);
                             chkq111p1c.setChecked(false);
                             chkq111p1d.setChecked(false);
                             chkq111p1e.setChecked(false);
                             chkq111p1x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });
         secq111p1_0=(LinearLayout)findViewById(R.id.secq111p1_0);
         secq111p1a=(LinearLayout)findViewById(R.id.secq111p1a);
         lineq111p1a=(View)findViewById(R.id.lineq111p1a);
         Vlblq111p1a=(TextView) findViewById(R.id.Vlblq111p1a);
         chkq111p1a=(CheckBox) findViewById(R.id.chkq111p1a);
         secq111p1b=(LinearLayout)findViewById(R.id.secq111p1b);
         lineq111p1b=(View)findViewById(R.id.lineq111p1b);
         Vlblq111p1b=(TextView) findViewById(R.id.Vlblq111p1b);
         chkq111p1b=(CheckBox) findViewById(R.id.chkq111p1b);
         secq111p1c=(LinearLayout)findViewById(R.id.secq111p1c);
         lineq111p1c=(View)findViewById(R.id.lineq111p1c);
         Vlblq111p1c=(TextView) findViewById(R.id.Vlblq111p1c);
         chkq111p1c=(CheckBox) findViewById(R.id.chkq111p1c);
         secq111p1d=(LinearLayout)findViewById(R.id.secq111p1d);
         lineq111p1d=(View)findViewById(R.id.lineq111p1d);
         Vlblq111p1d=(TextView) findViewById(R.id.Vlblq111p1d);
         chkq111p1d=(CheckBox) findViewById(R.id.chkq111p1d);
         secq111p1e=(LinearLayout)findViewById(R.id.secq111p1e);
         lineq111p1e=(View)findViewById(R.id.lineq111p1e);
         Vlblq111p1e=(TextView) findViewById(R.id.Vlblq111p1e);
         chkq111p1e=(CheckBox) findViewById(R.id.chkq111p1e);
         secq111p1x=(LinearLayout)findViewById(R.id.secq111p1x);
         lineq111p1x=(View)findViewById(R.id.lineq111p1x);
         Vlblq111p1x=(TextView) findViewById(R.id.Vlblq111p1x);

         chkq111p1x=(CheckBox) findViewById(R.id.chkq111p1x);
         chkq111p1x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq111p1x.isChecked() == true) {

                     secq111p1x1.setVisibility(View.VISIBLE);
                     lineq111p1x1.setVisibility(View.VISIBLE);
                 } else if (chkq111p1x.isChecked() == false) {
                     secq111p1x1.setVisibility(View.GONE);
                     lineq111p1x1.setVisibility(View.GONE);

                     txtq111p1x1.setText("");


                 }

             }
         });

         secq111p1x1=(LinearLayout)findViewById(R.id.secq111p1x1);
         lineq111p1x1=(View)findViewById(R.id.lineq111p1x1);
         Vlblq111p1x1=(TextView) findViewById(R.id.Vlblq111p1x1);
         txtq111p1x1=(EditText) findViewById(R.id.txtq111p1x1);
         /*
         secq112b4=(LinearLayout)findViewById(R.id.secq112b4);
         secq112b4a=(LinearLayout)findViewById(R.id.secq112b4a);
         lineq112b4a=(View)findViewById(R.id.lineq112b4a);
         Vlblq112b4a=(TextView) findViewById(R.id.Vlblq112b4a);
         chkq112b4a=(CheckBox) findViewById(R.id.chkq112b4a);
         secq112b4b=(LinearLayout)findViewById(R.id.secq112b4b);
         lineq112b4b=(View)findViewById(R.id.lineq112b4b);
         Vlblq112b4b=(TextView) findViewById(R.id.Vlblq112b4b);
         chkq112b4b=(CheckBox) findViewById(R.id.chkq112b4b);
         secq112b4c=(LinearLayout)findViewById(R.id.secq112b4c);
         lineq112b4c=(View)findViewById(R.id.lineq112b4c);
         Vlblq112b4c=(TextView) findViewById(R.id.Vlblq112b4c);
         chkq112b4c=(CheckBox) findViewById(R.id.chkq112b4c);
         secq112b4d=(LinearLayout)findViewById(R.id.secq112b4d);
         lineq112b4d=(View)findViewById(R.id.lineq112b4d);
         Vlblq112b4d=(TextView) findViewById(R.id.Vlblq112b4d);
         chkq112b4d=(CheckBox) findViewById(R.id.chkq112b4d);
         secq112b4e=(LinearLayout)findViewById(R.id.secq112b4e);
         lineq112b4e=(View)findViewById(R.id.lineq112b4e);
         Vlblq112b4e=(TextView) findViewById(R.id.Vlblq112b4e);
         chkq112b4e=(CheckBox) findViewById(R.id.chkq112b4e);
         secq112b4x=(LinearLayout)findViewById(R.id.secq112b4x);
         lineq112b4x=(View)findViewById(R.id.lineq112b4x);
         Vlblq112b4x=(TextView) findViewById(R.id.Vlblq112b4x);
         chkq112b4x=(CheckBox) findViewById(R.id.chkq112b4x);
         chkq112b4x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112b4x.isChecked() == true) {

                     secq112b4x1.setVisibility(View.VISIBLE);
                     lineq112b4x1.setVisibility(View.VISIBLE);
                 } else if (chkq112b4x.isChecked() == false) {
                     secq112b4x1.setVisibility(View.GONE);
                     lineq112b4x1.setVisibility(View.GONE);

                     txtq112b4x1.setText("");


                 }

             }
         });

         secq112b4x1=(LinearLayout)findViewById(R.id.secq112b4x1);
         lineq112b4x1=(View)findViewById(R.id.lineq112b4x1);
         Vlblq112b4x1=(TextView) findViewById(R.id.Vlblq112b4x1);
         txtq112b4x1=(EditText) findViewById(R.id.txtq112b4x1);*/

        // secq112b3=(LinearLayout)findViewById(R.id.secq112b3);
         //lineq112b3=(View)findViewById(R.id.lineq112b3);
       //  Vlblq112b3=(TextView) findViewById(R.id.Vlblq112b3);
        // txtq112b3=(EditText) findViewById(R.id.txtq112b3);
         secq112c=(LinearLayout)findViewById(R.id.secq112c);
         lineq112c=(View)findViewById(R.id.lineq112c);
         Vlblq112c=(TextView) findViewById(R.id.Vlblq112c);
         chkq112c=(CheckBox) findViewById(R.id.chkq112c);
         chkq112c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112c.isChecked() == true) {



                     secq112c1.setVisibility(View.VISIBLE);
                     lineq112c1.setVisibility(View.VISIBLE);
                     secq112c2.setVisibility(View.VISIBLE);
                     lineq112c2.setVisibility(View.VISIBLE);
                    // secq112c3.setVisibility(View.VISIBLE);
                    // lineq112c3.setVisibility(View.VISIBLE);


                 } else if (chkq112c.isChecked() == false) {
                     txtq112c1.setText("");
                     txtq112c2.setText("");
                     //rdogrpq112c3.clearCheck();
                    // txtq112c3.setText("");
                     secq112c1.setVisibility(View.GONE);
                     lineq112c1.setVisibility(View.GONE);
                     secq112c2.setVisibility(View.GONE);
                     lineq112c2.setVisibility(View.GONE);
                    /* secq112c3  .setVisibility(View.GONE);
                     lineq112c3.setVisibility(View.GONE);

                     secq112c4.setVisibility(View.GONE);
                     // lineq112c4.setVisibility(View.GONE);
                     secq112c4a.setVisibility(View.GONE);
                     lineq112c4a.setVisibility(View.GONE);
                     secq112c4b.setVisibility(View.GONE);
                     lineq112c4b.setVisibility(View.GONE);
                     secq112c4c.setVisibility(View.GONE);
                     lineq112c4c.setVisibility(View.GONE);
                     secq112c4d.setVisibility(View.GONE);
                     lineq112c4d.setVisibility(View.GONE);
                     secq112c4e.setVisibility(View.GONE);
                     lineq112c4e.setVisibility(View.GONE);
                     secq112c4x.setVisibility(View.GONE);
                     lineq112c4x.setVisibility(View.GONE);
                     chkq112c4a.setChecked(false);
                     chkq112c4b.setChecked(false);
                     chkq112c4c.setChecked(false);
                     chkq112c4d.setChecked(false);
                     chkq112c4e.setChecked(false);
                     chkq112c4x.setChecked(false);
*/

                 }

             }
         });
         secq112c1=(LinearLayout)findViewById(R.id.secq112c1);
         lineq112c1=(View)findViewById(R.id.lineq112c1);
         Vlblq112c1=(TextView) findViewById(R.id.Vlblq112c1);
         txtq112c1=(EditText) findViewById(R.id.txtq112c1);
         txtq112c1.addTextChangedListener(new TextWatcher() {
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                     try {
                         Integer a=Integer.valueOf(txtq112c1.getText().toString());
                         Integer b=Integer.valueOf(txtq112c2.getText().toString());

                         if(a>b)
                         {



                             secq112p2_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112p2a.setVisibility(View.VISIBLE);
                             lineq112p2a.setVisibility(View.VISIBLE);
                             secq112p2b.setVisibility(View.VISIBLE);
                             lineq112p2b.setVisibility(View.VISIBLE);
                             secq112p2c.setVisibility(View.VISIBLE);
                             lineq112p2c.setVisibility(View.VISIBLE);
                             secq112p2d.setVisibility(View.VISIBLE);
                             lineq112p2d.setVisibility(View.VISIBLE);
                             secq112p2e.setVisibility(View.VISIBLE);
                             lineq112p2e.setVisibility(View.VISIBLE);
                             secq112p2x.setVisibility(View.VISIBLE);
                             lineq112p2x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq112p2_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112p2a.setVisibility(View.GONE);
                             lineq112p2a.setVisibility(View.GONE);
                             secq112p2b.setVisibility(View.GONE);
                             lineq112p2b.setVisibility(View.GONE);
                             secq112p2c.setVisibility(View.GONE);
                             lineq112p2c.setVisibility(View.GONE);
                             secq112p2d.setVisibility(View.GONE);
                             lineq112p2d.setVisibility(View.GONE);
                             secq112p2e.setVisibility(View.GONE);
                             lineq112p2e.setVisibility(View.GONE);
                             secq112p2x.setVisibility(View.GONE);
                             lineq112p2x.setVisibility(View.GONE);


                             chkq112p2a.setChecked(false);
                             chkq112p2b.setChecked(false);
                             chkq112p2c.setChecked(false);
                             chkq112p2d.setChecked(false);
                             chkq112p2e.setChecked(false);
                             chkq112p2x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });
         secq112c2=(LinearLayout)findViewById(R.id.secq112c2);
         lineq112c2=(View)findViewById(R.id.lineq112c2);
         Vlblq112c2=(TextView) findViewById(R.id.Vlblq112c2);
         txtq112c2=(EditText) findViewById(R.id.txtq112c2);

         txtq112c2.addTextChangedListener(new TextWatcher() {
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                     try {
                         Integer a=Integer.valueOf(txtq112c1.getText().toString());
                         Integer b=Integer.valueOf(txtq112c2.getText().toString());

                         if(a>b)
                         {
                             secq112p2_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112p2a.setVisibility(View.VISIBLE);
                             lineq112p2a.setVisibility(View.VISIBLE);
                             secq112p2b.setVisibility(View.VISIBLE);
                             lineq112p2b.setVisibility(View.VISIBLE);
                             secq112p2c.setVisibility(View.VISIBLE);
                             lineq112p2c.setVisibility(View.VISIBLE);
                             secq112p2d.setVisibility(View.VISIBLE);
                             lineq112p2d.setVisibility(View.VISIBLE);
                             secq112p2e.setVisibility(View.VISIBLE);
                             lineq112p2e.setVisibility(View.VISIBLE);
                             secq112p2x.setVisibility(View.VISIBLE);
                             lineq112p2x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq112p2_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112p2a.setVisibility(View.GONE);
                             lineq112p2a.setVisibility(View.GONE);
                             secq112p2b.setVisibility(View.GONE);
                             lineq112p2b.setVisibility(View.GONE);
                             secq112p2c.setVisibility(View.GONE);
                             lineq112p2c.setVisibility(View.GONE);
                             secq112p2d.setVisibility(View.GONE);
                             lineq112p2d.setVisibility(View.GONE);
                             secq112p2e.setVisibility(View.GONE);
                             lineq112p2e.setVisibility(View.GONE);
                             secq112p2x.setVisibility(View.GONE);
                             lineq112p2x.setVisibility(View.GONE);


                             chkq112p2a.setChecked(false);
                             chkq112p2b.setChecked(false);
                             chkq112p2c.setChecked(false);
                             chkq112p2d.setChecked(false);
                             chkq112p2e.setChecked(false);
                             chkq112p2x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });

         secq112p2_0=(LinearLayout)findViewById(R.id.secq112p2_0);
         secq112p2a=(LinearLayout)findViewById(R.id.secq112p2a);
         lineq112p2a=(View)findViewById(R.id.lineq112p2a);
         Vlblq112p2a=(TextView) findViewById(R.id.Vlblq112p2a);
         chkq112p2a=(CheckBox) findViewById(R.id.chkq112p2a);
         secq112p2b=(LinearLayout)findViewById(R.id.secq112p2b);
         lineq112p2b=(View)findViewById(R.id.lineq112p2b);
         Vlblq112p2b=(TextView) findViewById(R.id.Vlblq112p2b);
         chkq112p2b=(CheckBox) findViewById(R.id.chkq112p2b);
         secq112p2c=(LinearLayout)findViewById(R.id.secq112p2c);
         lineq112p2c=(View)findViewById(R.id.lineq112p2c);
         Vlblq112p2c=(TextView) findViewById(R.id.Vlblq112p2c);
         chkq112p2c=(CheckBox) findViewById(R.id.chkq112p2c);
         secq112p2d=(LinearLayout)findViewById(R.id.secq112p2d);
         lineq112p2d=(View)findViewById(R.id.lineq112p2d);
         Vlblq112p2d=(TextView) findViewById(R.id.Vlblq112p2d);
         chkq112p2d=(CheckBox) findViewById(R.id.chkq112p2d);
         secq112p2e=(LinearLayout)findViewById(R.id.secq112p2e);
         lineq112p2e=(View)findViewById(R.id.lineq112p2e);
         Vlblq112p2e=(TextView) findViewById(R.id.Vlblq112p2e);
         chkq112p2e=(CheckBox) findViewById(R.id.chkq112p2e);
         secq112p2x=(LinearLayout)findViewById(R.id.secq112p2x);
         lineq112p2x=(View)findViewById(R.id.lineq112p2x);
         Vlblq112p2x=(TextView) findViewById(R.id.Vlblq112p2x);
         chkq112p2x=(CheckBox) findViewById(R.id.chkq112p2x);
         chkq112p2x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112p2x.isChecked() == true) {
                     secq112p2x1.setVisibility(View.VISIBLE);
                     lineq112p2x1.setVisibility(View.VISIBLE);
                 } else if (chkq112p2x.isChecked() == false) {
                     secq112p2x1.setVisibility(View.GONE);
                     lineq112p2x1.setVisibility(View.GONE);

                     txtq112p2x1.setText("");

                 }

             }
         });
         secq112p2x1=(LinearLayout)findViewById(R.id.secq112p2x1);
         lineq112p2x1=(View)findViewById(R.id.lineq112p2x1);
         Vlblq112p2x1=(TextView) findViewById(R.id.Vlblq112p2x1);
         txtq112p2x1=(EditText) findViewById(R.id.txtq112p2x1);
       /*  secq112c3=(LinearLayout)findViewById(R.id.secq112c3);

         lineq112c3=(View)findViewById(R.id.lineq112c3);
         Vlblq112c3 = (TextView) findViewById(R.id.Vlblq112c3);
         rdogrpq112c3 = (RadioGroup) findViewById(R.id.rdogrpq112c3);

         rdoq112c31 = (RadioButton) findViewById(R.id.rdoq112c31);
         rdoq112c32 = (RadioButton) findViewById(R.id.rdoq112c32);
         secq112c3=(LinearLayout)findViewById(R.id.secq112c3);
         lineq112c3=(View)findViewById(R.id.lineq112c3);
         Vlblq112c3 = (TextView) findViewById(R.id.Vlblq112c3);
         rdogrpq112c3 = (RadioGroup) findViewById(R.id.rdogrpq112c3);

         rdoq112c31 = (RadioButton) findViewById(R.id.rdoq112c31);
         rdoq112c32 = (RadioButton) findViewById(R.id.rdoq112c32);

         rdogrpq112c3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrp112c3 = new String[] {"1","2"};
                 for (int i = 0; i < rdogrpq112c3.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpq112c3.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrp112c3[i];
                 }
                 if(rbData.equalsIgnoreCase("1"))
                 {
                     secq112c4.setVisibility(View.VISIBLE);
                     // lineq112c4.setVisibility(View.GONE);
                     secq112c4a.setVisibility(View.VISIBLE);
                     lineq112c4a.setVisibility(View.VISIBLE);
                     secq112c4b.setVisibility(View.VISIBLE);
                     lineq112c4b.setVisibility(View.VISIBLE);
                     secq112c4c.setVisibility(View.VISIBLE);
                     lineq112c4c.setVisibility(View.VISIBLE);
                     secq112c4d.setVisibility(View.VISIBLE);
                     lineq112c4d.setVisibility(View.VISIBLE);
                     secq112c4e.setVisibility(View.VISIBLE);
                     lineq112c4e.setVisibility(View.VISIBLE);
                     secq112c4x.setVisibility(View.VISIBLE);
                     lineq112c4x.setVisibility(View.VISIBLE);



                 }
                 else if(rbData.equalsIgnoreCase("2"))
                 {
                     secq112c4.setVisibility(View.GONE);
                     // lineq112c4.setVisibility(View.GONE);
                     secq112c4a.setVisibility(View.GONE);
                     lineq112c4a.setVisibility(View.GONE);
                     secq112c4b.setVisibility(View.GONE);
                     lineq112c4b.setVisibility(View.GONE);
                     secq112c4c.setVisibility(View.GONE);
                     lineq112c4c.setVisibility(View.GONE);
                     secq112c4d.setVisibility(View.GONE);
                     lineq112c4d.setVisibility(View.GONE);
                     secq112c4e.setVisibility(View.GONE);
                     lineq112c4e.setVisibility(View.GONE);
                     secq112c4x.setVisibility(View.GONE);
                     lineq112c4x.setVisibility(View.GONE);
                     chkq112c4a.setChecked(false);
                     chkq112c4b.setChecked(false);
                     chkq112c4c.setChecked(false);
                     chkq112c4d.setChecked(false);
                     chkq112c4e.setChecked(false);
                     chkq112c4x.setChecked(false);



                 }

             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         secq112c4=(LinearLayout)findViewById(R.id.secq112c4);
         secq112c4a=(LinearLayout)findViewById(R.id.secq112c4a);
         lineq112c4a=(View)findViewById(R.id.lineq112c4a);
         Vlblq112c4a=(TextView) findViewById(R.id.Vlblq112c4a);
         chkq112c4a=(CheckBox) findViewById(R.id.chkq112c4a);
         secq112c4b=(LinearLayout)findViewById(R.id.secq112c4b);
         lineq112c4b=(View)findViewById(R.id.lineq112c4b);
         Vlblq112c4b=(TextView) findViewById(R.id.Vlblq112c4b);
         chkq112c4b=(CheckBox) findViewById(R.id.chkq112c4b);
         secq112c4c=(LinearLayout)findViewById(R.id.secq112c4c);
         lineq112c4c=(View)findViewById(R.id.lineq112c4c);
         Vlblq112c4c=(TextView) findViewById(R.id.Vlblq112c4c);
         chkq112c4c=(CheckBox) findViewById(R.id.chkq112c4c);
         secq112c4d=(LinearLayout)findViewById(R.id.secq112c4d);
         lineq112c4d=(View)findViewById(R.id.lineq112c4d);
         Vlblq112c4d=(TextView) findViewById(R.id.Vlblq112c4d);
         chkq112c4d=(CheckBox) findViewById(R.id.chkq112c4d);
         secq112c4e=(LinearLayout)findViewById(R.id.secq112c4e);
         lineq112c4e=(View)findViewById(R.id.lineq112c4e);
         Vlblq112c4e=(TextView) findViewById(R.id.Vlblq112c4e);
         chkq112c4e=(CheckBox) findViewById(R.id.chkq112c4e);
         secq112c4x=(LinearLayout)findViewById(R.id.secq112c4x);
         lineq112c4x=(View)findViewById(R.id.lineq112c4x);
         Vlblq112c4x=(TextView) findViewById(R.id.Vlblq112c4x);
         chkq112c4x=(CheckBox) findViewById(R.id.chkq112c4x);
         chkq112c4x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112c4x.isChecked() == true) {

                     secq112c4x1.setVisibility(View.VISIBLE);
                     lineq112c4x1.setVisibility(View.VISIBLE);
                 } else if (chkq112c4x.isChecked() == false) {
                     secq112c4x1.setVisibility(View.GONE);
                     lineq112c4x1.setVisibility(View.GONE);

                     txtq112c4x1.setText("");


                 }

             }
         });

         secq112c4x1=(LinearLayout)findViewById(R.id.secq112c4x1);
         lineq112c4x1=(View)findViewById(R.id.lineq112c4x1);
         Vlblq112c4x1=(TextView) findViewById(R.id.Vlblq112c4x1);
         txtq112c4x1=(EditText) findViewById(R.id.txtq112c4x1);*/

         secq112d=(LinearLayout)findViewById(R.id.secq112d);
         lineq112d=(View)findViewById(R.id.lineq112d);
         Vlblq112d=(TextView) findViewById(R.id.Vlblq112d);
         chkq112d=(CheckBox) findViewById(R.id.chkq112d);

         chkq112d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112d.isChecked() == true) {



                     secq112d1.setVisibility(View.VISIBLE);
                     lineq112d1.setVisibility(View.VISIBLE);
                     secq112d2.setVisibility(View.VISIBLE);
                     lineq112d2.setVisibility(View.VISIBLE);
                     //secq112d3.setVisibility(View.VISIBLE);
                     //lineq112d3.setVisibility(View.VISIBLE);


                 } else if (chkq112d.isChecked() == false) {



                     txtq112d1.setText("");
                     txtq112d2.setText("");
                 //    txtq112d3.setText("");
                     secq112d1.setVisibility(View.GONE);
                     lineq112d1.setVisibility(View.GONE);
                     secq112d2.setVisibility(View.GONE);
                     lineq112d2.setVisibility(View.GONE);
                    /* secq112d3.setVisibility(View.GONE);
                     lineq112d3.setVisibility(View.GONE);

                     secq112d4.setVisibility(View.GONE);
                     // lineq112d4.setVisibility(View.GONE);
                     secq112d4a.setVisibility(View.GONE);
                     lineq112d4a.setVisibility(View.GONE);
                     secq112d4b.setVisibility(View.GONE);
                     lineq112d4b.setVisibility(View.GONE);
                     secq112d4c.setVisibility(View.GONE);
                     lineq112d4c.setVisibility(View.GONE);
                     secq112d4d.setVisibility(View.GONE);
                     lineq112d4d.setVisibility(View.GONE);
                     secq112d4e.setVisibility(View.GONE);
                     lineq112d4e.setVisibility(View.GONE);
                     secq112d4x.setVisibility(View.GONE);
                     lineq112d4x.setVisibility(View.GONE);
                     chkq112d4a.setChecked(false);
                     chkq112d4b.setChecked(false);
                     chkq112d4c.setChecked(false);
                     chkq112d4d.setChecked(false);
                     chkq112d4e.setChecked(false);
                     chkq112d4x.setChecked(false);
*/

                 }

             }
         });
         secq112d1=(LinearLayout)findViewById(R.id.secq112d1);
         lineq112d1=(View)findViewById(R.id.lineq112d1);
         Vlblq112d1=(TextView) findViewById(R.id.Vlblq112d1);
         txtq112d1=(EditText) findViewById(R.id.txtq112d1);

         txtq112d1.addTextChangedListener(new TextWatcher() {
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                     try {
                         Integer a=Integer.valueOf(txtq112d1.getText().toString());
                         Integer b=Integer.valueOf(txtq112d2.getText().toString());

                         if(a>b)
                         {
                             secq112p3_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112p3a.setVisibility(View.VISIBLE);
                             lineq112p3a.setVisibility(View.VISIBLE);
                             secq112p3b.setVisibility(View.VISIBLE);
                             lineq112p3b.setVisibility(View.VISIBLE);
                             secq112p3c.setVisibility(View.VISIBLE);
                             lineq112p3c.setVisibility(View.VISIBLE);
                             secq112p3d.setVisibility(View.VISIBLE);
                             lineq112p3d.setVisibility(View.VISIBLE);
                             secq112p3e.setVisibility(View.VISIBLE);
                             lineq112p3e.setVisibility(View.VISIBLE);
                             secq112p3x.setVisibility(View.VISIBLE);
                             lineq112p3x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq112p3_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112p3a.setVisibility(View.GONE);
                             lineq112p3a.setVisibility(View.GONE);
                             secq112p3b.setVisibility(View.GONE);
                             lineq112p3b.setVisibility(View.GONE);
                             secq112p3c.setVisibility(View.GONE);
                             lineq112p3c.setVisibility(View.GONE);
                             secq112p3d.setVisibility(View.GONE);
                             lineq112p3d.setVisibility(View.GONE);
                             secq112p3e.setVisibility(View.GONE);
                             lineq112p3e.setVisibility(View.GONE);
                             secq112p3x.setVisibility(View.GONE);
                             lineq112p3x.setVisibility(View.GONE);
                             secq112p3x1.setVisibility(View.GONE);
                             lineq112p3x1.setVisibility(View.GONE);

                             chkq112p3a.setChecked(false);
                             chkq112p3b.setChecked(false);
                             chkq112p3c.setChecked(false);
                             chkq112p3d.setChecked(false);
                             chkq112p3e.setChecked(false);
                             chkq112p3x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });



         secq112d2=(LinearLayout)findViewById(R.id.secq112d2);
         lineq112d2=(View)findViewById(R.id.lineq112d2);
         Vlblq112d2=(TextView) findViewById(R.id.Vlblq112d2);
         txtq112d2=(EditText) findViewById(R.id.txtq112d2);

         txtq112d2.addTextChangedListener(new TextWatcher() {
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                     try {
                         Integer a=Integer.valueOf(txtq112d1.getText().toString());
                         Integer b=Integer.valueOf(txtq112d2.getText().toString());

                         if(a>b)
                         {
                             secq112p3_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112p3a.setVisibility(View.VISIBLE);
                             lineq112p3a.setVisibility(View.VISIBLE);
                             secq112p3b.setVisibility(View.VISIBLE);
                             lineq112p3b.setVisibility(View.VISIBLE);
                             secq112p3c.setVisibility(View.VISIBLE);
                             lineq112p3c.setVisibility(View.VISIBLE);
                             secq112p3d.setVisibility(View.VISIBLE);
                             lineq112p3d.setVisibility(View.VISIBLE);
                             secq112p3e.setVisibility(View.VISIBLE);
                             lineq112p3e.setVisibility(View.VISIBLE);
                             secq112p3x.setVisibility(View.VISIBLE);
                             lineq112p3x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq112p3_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112p3a.setVisibility(View.GONE);
                             lineq112p3a.setVisibility(View.GONE);
                             secq112p3b.setVisibility(View.GONE);
                             lineq112p3b.setVisibility(View.GONE);
                             secq112p3c.setVisibility(View.GONE);
                             lineq112p3c.setVisibility(View.GONE);
                             secq112p3d.setVisibility(View.GONE);
                             lineq112p3d.setVisibility(View.GONE);
                             secq112p3e.setVisibility(View.GONE);
                             lineq112p3e.setVisibility(View.GONE);
                             secq112p3x.setVisibility(View.GONE);
                             lineq112p3x.setVisibility(View.GONE);
                             secq112p3x1.setVisibility(View.GONE);
                             lineq112p3x1.setVisibility(View.GONE);

                             chkq112p3a.setChecked(false);
                             chkq112p3b.setChecked(false);
                             chkq112p3c.setChecked(false);
                             chkq112p3d.setChecked(false);
                             chkq112p3e.setChecked(false);
                             chkq112p3x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });



         secq112p3_0=(LinearLayout)findViewById(R.id.secq112p3_0);
         secq112p3a=(LinearLayout)findViewById(R.id.secq112p3a);
         lineq112p3a=(View)findViewById(R.id.lineq112p3a);
         Vlblq112p3a=(TextView) findViewById(R.id.Vlblq112p3a);
         chkq112p3a=(CheckBox) findViewById(R.id.chkq112p3a);
         secq112p3b=(LinearLayout)findViewById(R.id.secq112p3b);
         lineq112p3b=(View)findViewById(R.id.lineq112p3b);
         Vlblq112p3b=(TextView) findViewById(R.id.Vlblq112p3b);
         chkq112p3b=(CheckBox) findViewById(R.id.chkq112p3b);
         secq112p3c=(LinearLayout)findViewById(R.id.secq112p3c);
         lineq112p3c=(View)findViewById(R.id.lineq112p3c);
         Vlblq112p3c=(TextView) findViewById(R.id.Vlblq112p3c);
         chkq112p3c=(CheckBox) findViewById(R.id.chkq112p3c);
         secq112p3d=(LinearLayout)findViewById(R.id.secq112p3d);
         lineq112p3d=(View)findViewById(R.id.lineq112p3d);
         Vlblq112p3d=(TextView) findViewById(R.id.Vlblq112p3d);
         chkq112p3d=(CheckBox) findViewById(R.id.chkq112p3d);
         secq112p3e=(LinearLayout)findViewById(R.id.secq112p3e);
         lineq112p3e=(View)findViewById(R.id.lineq112p3e);
         Vlblq112p3e=(TextView) findViewById(R.id.Vlblq112p3e);
         chkq112p3e=(CheckBox) findViewById(R.id.chkq112p3e);
         secq112p3x=(LinearLayout)findViewById(R.id.secq112p3x);
         lineq112p3x=(View)findViewById(R.id.lineq112p3x);
         Vlblq112p3x=(TextView) findViewById(R.id.Vlblq112p3x);

         chkq112p3x=(CheckBox) findViewById(R.id.chkq112p3x);
         chkq112p3x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112p3x.isChecked() == true) {

                     secq112p3x1.setVisibility(View.VISIBLE);
                     lineq112p3x1.setVisibility(View.VISIBLE);
                 } else if (chkq112p3x.isChecked() == false) {
                     secq112p3x1.setVisibility(View.GONE);
                     lineq112p3x1.setVisibility(View.GONE);

                     txtq112p3x1.setText("");


                 }

             }
         });

         secq112p3x1=(LinearLayout)findViewById(R.id.secq112p3x1);
         lineq112p3x1=(View)findViewById(R.id.lineq112p3x1);
         Vlblq112p3x1=(TextView) findViewById(R.id.Vlblq112p3x1);
         txtq112p3x1=(EditText) findViewById(R.id.txtq112p3x1);



         /*secq112d3=(LinearLayout)findViewById(R.id.secq112d3);
         lineq112d3=(View)findViewById(R.id.lineq112d3);
         Vlblq112d3=(TextView) findViewById(R.id.Vlblq112d3);
        // txtq112d3=(EditText) findViewById(R.id.txtq112d3);
         rdogrpq112d3 = (RadioGroup) findViewById(R.id.rdogrpq112d3);

         rdoq112d31 = (RadioButton) findViewById(R.id.rdoq112d31);
         rdoq112d32 = (RadioButton) findViewById(R.id.rdoq112d32);

         rdogrpq112d3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrp112d3 = new String[] {"1","2"};
                 for (int i = 0; i < rdogrpq112d3.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpq112d3.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrp112d3[i];
                 }
                 if(rbData.equalsIgnoreCase("1"))
                 {
                     secq112d4.setVisibility(View.VISIBLE);
                     // lineq112d4.setVisibility(View.GONE);
                     secq112d4a.setVisibility(View.VISIBLE);
                     lineq112d4a.setVisibility(View.VISIBLE);
                     secq112d4b.setVisibility(View.VISIBLE);
                     lineq112d4b.setVisibility(View.VISIBLE);
                     secq112d4c.setVisibility(View.VISIBLE);
                     lineq112d4c.setVisibility(View.VISIBLE);
                     secq112d4d.setVisibility(View.VISIBLE);
                     lineq112d4d.setVisibility(View.VISIBLE);
                     secq112d4e.setVisibility(View.VISIBLE);
                     lineq112d4e.setVisibility(View.VISIBLE);
                     secq112d4x.setVisibility(View.VISIBLE);
                     lineq112d4x.setVisibility(View.VISIBLE);



                 }
                 else if(rbData.equalsIgnoreCase("2"))
                 {
                     secq112d4.setVisibility(View.GONE);
                     // lineq112d4.setVisibility(View.GONE);
                     secq112d4a.setVisibility(View.GONE);
                     lineq112d4a.setVisibility(View.GONE);
                     secq112d4b.setVisibility(View.GONE);
                     lineq112d4b.setVisibility(View.GONE);
                     secq112d4c.setVisibility(View.GONE);
                     lineq112d4c.setVisibility(View.GONE);
                     secq112d4d.setVisibility(View.GONE);
                     lineq112d4d.setVisibility(View.GONE);
                     secq112d4e.setVisibility(View.GONE);
                     lineq112d4e.setVisibility(View.GONE);
                     secq112d4x.setVisibility(View.GONE);
                     lineq112d4x.setVisibility(View.GONE);
                     chkq112d4a.setChecked(false);
                     chkq112d4b.setChecked(false);
                     chkq112d4c.setChecked(false);
                     chkq112d4d.setChecked(false);
                     chkq112d4e.setChecked(false);
                     chkq112d4x.setChecked(false);



                 }

             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         secq112d4=(LinearLayout)findViewById(R.id.secq112d4);
         secq112d4a=(LinearLayout)findViewById(R.id.secq112d4a);
         lineq112d4a=(View)findViewById(R.id.lineq112d4a);
         Vlblq112d4a=(TextView) findViewById(R.id.Vlblq112d4a);
         chkq112d4a=(CheckBox) findViewById(R.id.chkq112d4a);
         secq112d4b=(LinearLayout)findViewById(R.id.secq112d4b);
         lineq112d4b=(View)findViewById(R.id.lineq112d4b);
         Vlblq112d4b=(TextView) findViewById(R.id.Vlblq112d4b);
         chkq112d4b=(CheckBox) findViewById(R.id.chkq112d4b);
         secq112d4c=(LinearLayout)findViewById(R.id.secq112d4c);
         lineq112d4c=(View)findViewById(R.id.lineq112d4c);
         Vlblq112d4c=(TextView) findViewById(R.id.Vlblq112d4c);
         chkq112d4c=(CheckBox) findViewById(R.id.chkq112d4c);
         secq112d4d=(LinearLayout)findViewById(R.id.secq112d4d);
         lineq112d4d=(View)findViewById(R.id.lineq112d4d);
         Vlblq112d4d=(TextView) findViewById(R.id.Vlblq112d4d);
         chkq112d4d=(CheckBox) findViewById(R.id.chkq112d4d);
         secq112d4e=(LinearLayout)findViewById(R.id.secq112d4e);
         lineq112d4e=(View)findViewById(R.id.lineq112d4e);
         Vlblq112d4e=(TextView) findViewById(R.id.Vlblq112d4e);
         chkq112d4e=(CheckBox) findViewById(R.id.chkq112d4e);
         secq112d4x=(LinearLayout)findViewById(R.id.secq112d4x);
         lineq112d4x=(View)findViewById(R.id.lineq112d4x);
         Vlblq112d4x=(TextView) findViewById(R.id.Vlblq112d4x);
         chkq112d4x=(CheckBox) findViewById(R.id.chkq112d4x);
         chkq112d4x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112d4x.isChecked() == true) {

                     secq112d4x1.setVisibility(View.VISIBLE);
                     lineq112d4x1.setVisibility(View.VISIBLE);
                 } else if (chkq112d4x.isChecked() == false) {
                     secq112d4x1.setVisibility(View.GONE);
                     lineq112d4x1.setVisibility(View.GONE);

                     txtq112d4x1.setText("");


                 }

             }
         });

         secq112d4x1=(LinearLayout)findViewById(R.id.secq112d4x1);
         lineq112d4x1=(View)findViewById(R.id.lineq112d4x1);
         Vlblq112d4x1=(TextView) findViewById(R.id.Vlblq112d4x1);
         txtq112d4x1=(EditText) findViewById(R.id.txtq112d4x1);*/



         secq112e=(LinearLayout)findViewById(R.id.secq112e);
         lineq112e=(View)findViewById(R.id.lineq112e);
         Vlblq112e=(TextView) findViewById(R.id.Vlblq112e);
         chkq112e=(CheckBox) findViewById(R.id.chkq112e);
         chkq112e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112e.isChecked() == true) {

                     secq112e1.setVisibility(View.VISIBLE);
                     lineq112e1.setVisibility(View.VISIBLE);
                     secq112e2.setVisibility(View.VISIBLE);
                     lineq112e2.setVisibility(View.VISIBLE);
                    /* secq112e3.setVisibility(View.VISIBLE);
                     lineq112e3.setVisibility(View.VISIBLE);*/


                 } else if (chkq112e.isChecked() == false) {


                     txtq112e1.setText("");
                     txtq112e2.setText("");
                   //  txtq112e3.setText("");
                     secq112e1.setVisibility(View.GONE);
                     lineq112e1.setVisibility(View.GONE);
                     secq112e2.setVisibility(View.GONE);
                     lineq112e2.setVisibility(View.GONE);
                   /*  secq112e3.setVisibility(View.GONE);
                     lineq112e3.setVisibility(View.GONE);*/


                 }

             }
         });
         secq112e1=(LinearLayout)findViewById(R.id.secq112e1);
         lineq112e1=(View)findViewById(R.id.lineq112e1);
         Vlblq112e1=(TextView) findViewById(R.id.Vlblq112e1);
         txtq112e1=(EditText) findViewById(R.id.txtq112e1);
         secq112e2=(LinearLayout)findViewById(R.id.secq112e2);
         lineq112e2=(View)findViewById(R.id.lineq112e2);
         Vlblq112e2=(TextView) findViewById(R.id.Vlblq112e2);
         txtq112e2=(EditText) findViewById(R.id.txtq112e2);
         /*secq112e3=(LinearLayout)findViewById(R.id.secq112e3);
         lineq112e3=(View)findViewById(R.id.lineq112e3);
         Vlblq112e3=(TextView) findViewById(R.id.Vlblq112e3);
         txtq112e3=(EditText) findViewById(R.id.txtq112e3);*/
         secq112f=(LinearLayout)findViewById(R.id.secq112f);
         lineq112f=(View)findViewById(R.id.lineq112f);
         Vlblq112f=(TextView) findViewById(R.id.Vlblq112f);
         chkq112f=(CheckBox) findViewById(R.id.chkq112f);
         chkq112f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112f.isChecked() == true) {
                     secq112f1.setVisibility(View.VISIBLE);
                     lineq112f1.setVisibility(View.VISIBLE);
                     secq112f2.setVisibility(View.VISIBLE);
                     lineq112f2.setVisibility(View.VISIBLE);
                    /* secq112f3.setVisibility(View.VISIBLE);
                     lineq112f3.setVisibility(View.VISIBLE);*/

                 } else if (chkq112f.isChecked() == false) {

                     txtq112f1.setText("");
                     txtq112f2.setText("");
                    // txtq112f3.setText("");
                     secq112f1.setVisibility(View.GONE);
                     lineq112f1.setVisibility(View.GONE);
                     secq112f2.setVisibility(View.GONE);
                     lineq112f2.setVisibility(View.GONE);
                    /* secq112f3.setVisibility(View.GONE);
                     lineq112f3.setVisibility(View.GONE);*/


                 }

             }
         });
         secq112f1=(LinearLayout)findViewById(R.id.secq112f1);
         lineq112f1=(View)findViewById(R.id.lineq112f1);
         Vlblq112f1=(TextView) findViewById(R.id.Vlblq112f1);
         txtq112f1=(EditText) findViewById(R.id.txtq112f1);
         secq112f2=(LinearLayout)findViewById(R.id.secq112f2);
         lineq112f2=(View)findViewById(R.id.lineq112f2);
         Vlblq112f2=(TextView) findViewById(R.id.Vlblq112f2);
         txtq112f2=(EditText) findViewById(R.id.txtq112f2);
        /* secq112f3=(LinearLayout)findViewById(R.id.secq112f3);
         lineq112f3=(View)findViewById(R.id.lineq112f3);
         Vlblq112f3=(TextView) findViewById(R.id.Vlblq112f3);
         txtq112f3=(EditText) findViewById(R.id.txtq112f3);*/
         secq112g=(LinearLayout)findViewById(R.id.secq112g);
         lineq112g=(View)findViewById(R.id.lineq112g);
         Vlblq112g=(TextView) findViewById(R.id.Vlblq112g);
         chkq112g=(CheckBox) findViewById(R.id.chkq112g);
         chkq112g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112g.isChecked() == true) {



                     secq112g1.setVisibility(View.VISIBLE);
                     lineq112g1.setVisibility(View.VISIBLE);
                     secq112g2.setVisibility(View.VISIBLE);
                     lineq112g2.setVisibility(View.VISIBLE);
                   /*  secq112g3.setVisibility(View.VISIBLE);
                     lineq112g3.setVisibility(View.VISIBLE);*/


                 } else if (chkq112g.isChecked() == false) {



                     txtq112g1.setText("");
                     txtq112g2.setText("");
                     //txtq112g3.setText("");
                     secq112g1.setVisibility(View.GONE);
                     lineq112g1.setVisibility(View.GONE);
                     secq112g2.setVisibility(View.GONE);
                     lineq112g2.setVisibility(View.GONE);
                    /* secq112g3.setVisibility(View.GONE);
                     lineq112g3.setVisibility(View.GONE);*/


                 }

             }
         });

         secq112g1=(LinearLayout)findViewById(R.id.secq112g1);
         lineq112g1=(View)findViewById(R.id.lineq112g1);
         Vlblq112g1=(TextView) findViewById(R.id.Vlblq112g1);
         txtq112g1=(EditText) findViewById(R.id.txtq112g1);
         secq112g2=(LinearLayout)findViewById(R.id.secq112g2);
         lineq112g2=(View)findViewById(R.id.lineq112g2);
         Vlblq112g2=(TextView) findViewById(R.id.Vlblq112g2);
         txtq112g2=(EditText) findViewById(R.id.txtq112g2);
       /*  secq112g3=(LinearLayout)findViewById(R.id.secq112g3);
         lineq112g3=(View)findViewById(R.id.lineq112g3);
         Vlblq112g3=(TextView) findViewById(R.id.Vlblq112g3);
         txtq112g3=(EditText) findViewById(R.id.txtq112g3);*/
         secq112h=(LinearLayout)findViewById(R.id.secq112h);
         lineq112h=(View)findViewById(R.id.lineq112h);
         Vlblq112h=(TextView) findViewById(R.id.Vlblq112h);
         chkq112h=(CheckBox) findViewById(R.id.chkq112h);
         chkq112h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112h.isChecked() == true) {



                     secq112h1.setVisibility(View.VISIBLE);
                     lineq112h1.setVisibility(View.VISIBLE);
                     secq112h2.setVisibility(View.VISIBLE);
                     lineq112h2.setVisibility(View.VISIBLE);
                   /*  secq112h3.setVisibility(View.VISIBLE);
                     lineq112h3.setVisibility(View.VISIBLE);*/


                 } else if (chkq112h.isChecked() == false) {



                     txtq112h1.setText("");
                     txtq112h2.setText("");
                  //   txtq112h3.setText("");
                     secq112h1.setVisibility(View.GONE);
                     lineq112h1.setVisibility(View.GONE);
                     secq112h2.setVisibility(View.GONE);
                     lineq112h2.setVisibility(View.GONE);
                   /*  secq112h3.setVisibility(View.GONE);
                     lineq112h3.setVisibility(View.GONE);*/


                 }

             }
         });
         secq112h1=(LinearLayout)findViewById(R.id.secq112h1);
         lineq112h1=(View)findViewById(R.id.lineq112h1);
         Vlblq112h1=(TextView) findViewById(R.id.Vlblq112h1);
         txtq112h1=(EditText) findViewById(R.id.txtq112h1);
         secq112h2=(LinearLayout)findViewById(R.id.secq112h2);
         lineq112h2=(View)findViewById(R.id.lineq112h2);
         Vlblq112h2=(TextView) findViewById(R.id.Vlblq112h2);
         txtq112h2=(EditText) findViewById(R.id.txtq112h2);
        /* secq112h3=(LinearLayout)findViewById(R.id.secq112h3);
         lineq112h3=(View)findViewById(R.id.lineq112h3);
         Vlblq112h3=(TextView) findViewById(R.id.Vlblq112h3);
         txtq112h3=(EditText) findViewById(R.id.txtq112h3);*/
         secq112i=(LinearLayout)findViewById(R.id.secq112i);
         lineq112i=(View)findViewById(R.id.lineq112i);
         Vlblq112i=(TextView) findViewById(R.id.Vlblq112i);
         chkq112i=(CheckBox) findViewById(R.id.chkq112i);
         chkq112i.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112i.isChecked() == true) {



                     secq112i1.setVisibility(View.VISIBLE);
                     lineq112i1.setVisibility(View.VISIBLE);
                     secq112i2.setVisibility(View.VISIBLE);
                     lineq112i2.setVisibility(View.VISIBLE);
                   /*  secq112i3.setVisibility(View.VISIBLE);
                     lineq112i3.setVisibility(View.VISIBLE);*/


                 } else if (chkq112i.isChecked() == false) {



                     txtq112i1.setText("");
                     txtq112i2.setText("");
                  //   txtq112i3.setText("");
                     secq112i1.setVisibility(View.GONE);
                     lineq112i1.setVisibility(View.GONE);
                     secq112i2.setVisibility(View.GONE);
                     lineq112i2.setVisibility(View.GONE);
                  /*   secq112i3.setVisibility(View.GONE);
                     lineq112i3.setVisibility(View.GONE);*/


                 }

             }
         });
         secq112i1=(LinearLayout)findViewById(R.id.secq112i1);
         lineq112i1=(View)findViewById(R.id.lineq112i1);
         Vlblq112i1=(TextView) findViewById(R.id.Vlblq112i1);
         txtq112i1=(EditText) findViewById(R.id.txtq112i1);
         secq112i2=(LinearLayout)findViewById(R.id.secq112i2);
         lineq112i2=(View)findViewById(R.id.lineq112i2);
         Vlblq112i2=(TextView) findViewById(R.id.Vlblq112i2);
         txtq112i2=(EditText) findViewById(R.id.txtq112i2);
  /*       secq112i3=(LinearLayout)findViewById(R.id.secq112i3);
         lineq112i3=(View)findViewById(R.id.lineq112i3);
         Vlblq112i3=(TextView) findViewById(R.id.Vlblq112i3);
         txtq112i3=(EditText) findViewById(R.id.txtq112i3);*/
         secq112j=(LinearLayout)findViewById(R.id.secq112j);
         lineq112j=(View)findViewById(R.id.lineq112j);
         Vlblq112j=(TextView) findViewById(R.id.Vlblq112j);
         chkq112j=(CheckBox) findViewById(R.id.chkq112j);
         chkq112j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112j.isChecked() == true) {
                     secq112j1.setVisibility(View.VISIBLE);
                     lineq112j1.setVisibility(View.VISIBLE);
                     secq112j2.setVisibility(View.VISIBLE);
                     lineq112j2.setVisibility(View.VISIBLE);
                    /* secq112j3.setVisibility(View.VISIBLE);
                     lineq112j3.setVisibility(View.VISIBLE);*/
                 } else if (chkq112j.isChecked() == false) {
                     txtq112j1.setText("");
                     txtq112j2.setText("");
                     //txtq112j3.setText("");
                     secq112j1.setVisibility(View.GONE);
                     lineq112j1.setVisibility(View.GONE);
                     secq112j2.setVisibility(View.GONE);
                     lineq112j2.setVisibility(View.GONE);
                   /*  secq112j3.setVisibility(View.GONE);
                     lineq112j3.setVisibility(View.GONE);*/


                 }

             }
         });
         secq112j1=(LinearLayout)findViewById(R.id.secq112j1);
         lineq112j1=(View)findViewById(R.id.lineq112j1);
         Vlblq112j1=(TextView) findViewById(R.id.Vlblq112j1);
         txtq112j1=(EditText) findViewById(R.id.txtq112j1);
         secq112j2=(LinearLayout)findViewById(R.id.secq112j2);
         lineq112j2=(View)findViewById(R.id.lineq112j2);
         Vlblq112j2=(TextView) findViewById(R.id.Vlblq112j2);
         txtq112j2=(EditText) findViewById(R.id.txtq112j2);
        /* secq112j3=(LinearLayout)findViewById(R.id.secq112j3);
         lineq112j3=(View)findViewById(R.id.lineq112j3);
         Vlblq112j3=(TextView) findViewById(R.id.Vlblq112j3);
         txtq112j3=(EditText) findViewById(R.id.txtq112j3);*/
         secq112k=(LinearLayout)findViewById(R.id.secq112k);
         lineq112k=(View)findViewById(R.id.lineq112k);
         Vlblq112k=(TextView) findViewById(R.id.Vlblq112k);
         chkq112k=(CheckBox) findViewById(R.id.chkq112k);
         chkq112k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112k.isChecked() == true) {



                     secq112k1.setVisibility(View.VISIBLE);
                     lineq112k1.setVisibility(View.VISIBLE);
                     secq112k2.setVisibility(View.VISIBLE);
                     lineq112k2.setVisibility(View.VISIBLE);
                    /* secq112k3.setVisibility(View.VISIBLE);
                     lineq112k3.setVisibility(View.VISIBLE);*/


                 } else if (chkq112k.isChecked() == false) {



                     txtq112k1.setText("");
                     txtq112k2.setText("");
                    // txtq112k3.setText("");
                     secq112k1.setVisibility(View.GONE);
                     lineq112k1.setVisibility(View.GONE);
                     secq112k2.setVisibility(View.GONE);
                     lineq112k2.setVisibility(View.GONE);
                    /* secq112k3.setVisibility(View.GONE);
                     lineq112k3.setVisibility(View.GONE);*/


                 }

             }
         });
         secq112k1=(LinearLayout)findViewById(R.id.secq112k1);
         lineq112k1=(View)findViewById(R.id.lineq112k1);
         Vlblq112k1=(TextView) findViewById(R.id.Vlblq112k1);
         txtq112k1=(EditText) findViewById(R.id.txtq112k1);
         txtq112k1.addTextChangedListener(new TextWatcher() {
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                     try {
                         Integer a=Integer.valueOf(txtq112k1.getText().toString());
                         Integer b=Integer.valueOf(txtq112k2.getText().toString());

                         if(a>b)
                         {
                             secq112m1_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112m1a.setVisibility(View.VISIBLE);
                             lineq112m1a.setVisibility(View.VISIBLE);
                             secq112m1b.setVisibility(View.VISIBLE);
                             lineq112m1b.setVisibility(View.VISIBLE);
                             secq112m1c.setVisibility(View.VISIBLE);
                             lineq112m1c.setVisibility(View.VISIBLE);
                             secq112m1d.setVisibility(View.VISIBLE);
                             lineq112m1d.setVisibility(View.VISIBLE);
                             secq112m1e.setVisibility(View.VISIBLE);
                             lineq112m1e.setVisibility(View.VISIBLE);
                             secq112m1x.setVisibility(View.VISIBLE);
                             lineq112m1x.setVisibility(View.VISIBLE);
                         }

                         else
                         {
                             secq112m1_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112m1a.setVisibility(View.GONE);
                             lineq112m1a.setVisibility(View.GONE);
                             secq112m1b.setVisibility(View.GONE);
                             lineq112m1b.setVisibility(View.GONE);
                             secq112m1c.setVisibility(View.GONE);
                             lineq112m1c.setVisibility(View.GONE);
                             secq112m1d.setVisibility(View.GONE);
                             lineq112m1d.setVisibility(View.GONE);
                             secq112m1e.setVisibility(View.GONE);
                             lineq112m1e.setVisibility(View.GONE);
                             secq112m1x.setVisibility(View.GONE);
                             lineq112m1x.setVisibility(View.GONE);
                             secq112m1x1.setVisibility(View.GONE);
                             lineq112m1x1.setVisibility(View.GONE);

                             chkq112m1a.setChecked(false);
                             chkq112m1b.setChecked(false);
                             chkq112m1c.setChecked(false);
                             chkq112m1d.setChecked(false);
                             chkq112m1e.setChecked(false);
                             chkq112m1x.setChecked(false);
                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });
         secq112k2=(LinearLayout)findViewById(R.id.secq112k2);
         lineq112k2=(View)findViewById(R.id.lineq112k2);
         Vlblq112k2=(TextView) findViewById(R.id.Vlblq112k2);
         txtq112k2=(EditText) findViewById(R.id.txtq112k2);

         txtq112k2.addTextChangedListener(new TextWatcher() {
             public void onTextChanged(CharSequence s, int start, int before,
                                       int count) {
                 if (!s.equals("")) {
                     try {
                         Integer a=Integer.valueOf(txtq112k1.getText().toString());
                         Integer b=Integer.valueOf(txtq112k2.getText().toString());

                         if(a>b)
                         {


                             secq112m1_0.setVisibility(View.VISIBLE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112m1a.setVisibility(View.VISIBLE);
                             lineq112m1a.setVisibility(View.VISIBLE);
                             secq112m1b.setVisibility(View.VISIBLE);
                             lineq112m1b.setVisibility(View.VISIBLE);
                             secq112m1c.setVisibility(View.VISIBLE);
                             lineq112m1c.setVisibility(View.VISIBLE);
                             secq112m1d.setVisibility(View.VISIBLE);
                             lineq112m1d.setVisibility(View.VISIBLE);
                             secq112m1e.setVisibility(View.VISIBLE);
                             lineq112m1e.setVisibility(View.VISIBLE);
                             secq112m1x.setVisibility(View.VISIBLE);
                             lineq112m1x.setVisibility(View.VISIBLE);

                         }

                         else
                         {
                             secq112m1_0.setVisibility(View.GONE);
                             // lineq112a4.setVisibility(View.GONE);
                             secq112m1a.setVisibility(View.GONE);
                             lineq112m1a.setVisibility(View.GONE);
                             secq112m1b.setVisibility(View.GONE);
                             lineq112m1b.setVisibility(View.GONE);
                             secq112m1c.setVisibility(View.GONE);
                             lineq112m1c.setVisibility(View.GONE);
                             secq112m1d.setVisibility(View.GONE);
                             lineq112m1d.setVisibility(View.GONE);
                             secq112m1e.setVisibility(View.GONE);
                             lineq112m1e.setVisibility(View.GONE);
                             secq112m1x.setVisibility(View.GONE);
                             lineq112m1x.setVisibility(View.GONE);
                             secq112m1x1.setVisibility(View.GONE);
                             lineq112m1x1.setVisibility(View.GONE);

                             chkq112m1a.setChecked(false);
                             chkq112m1b.setChecked(false);
                             chkq112m1c.setChecked(false);
                             chkq112m1d.setChecked(false);
                             chkq112m1e.setChecked(false);
                             chkq112m1x.setChecked(false);



                         }

                     } catch (Exception e) {

                     }
                 }
             }

             public void beforeTextChanged(CharSequence s, int start, int count,
                                           int after) {
             }

             public void afterTextChanged(Editable s) {
             }
         });


         secq112m1_0=(LinearLayout)findViewById(R.id.secq112m1_0);
         secq112m1a=(LinearLayout)findViewById(R.id.secq112m1a);
         lineq112m1a=(View)findViewById(R.id.lineq112m1a);
         Vlblq112m1a=(TextView) findViewById(R.id.Vlblq112m1a);
         chkq112m1a=(CheckBox) findViewById(R.id.chkq112m1a);
         secq112m1b=(LinearLayout)findViewById(R.id.secq112m1b);
         lineq112m1b=(View)findViewById(R.id.lineq112m1b);
         Vlblq112m1b=(TextView) findViewById(R.id.Vlblq112m1b);
         chkq112m1b=(CheckBox) findViewById(R.id.chkq112m1b);
         secq112m1c=(LinearLayout)findViewById(R.id.secq112m1c);
         lineq112m1c=(View)findViewById(R.id.lineq112m1c);
         Vlblq112m1c=(TextView) findViewById(R.id.Vlblq112m1c);
         chkq112m1c=(CheckBox) findViewById(R.id.chkq112m1c);
         secq112m1d=(LinearLayout)findViewById(R.id.secq112m1d);
         lineq112m1d=(View)findViewById(R.id.lineq112m1d);
         Vlblq112m1d=(TextView) findViewById(R.id.Vlblq112m1d);
         chkq112m1d=(CheckBox) findViewById(R.id.chkq112m1d);
         secq112m1e=(LinearLayout)findViewById(R.id.secq112m1e);
         lineq112m1e=(View)findViewById(R.id.lineq112m1e);
         Vlblq112m1e=(TextView) findViewById(R.id.Vlblq112m1e);
         chkq112m1e=(CheckBox) findViewById(R.id.chkq112m1e);
         secq112m1x=(LinearLayout)findViewById(R.id.secq112m1x);
         lineq112m1x=(View)findViewById(R.id.lineq112m1x);
         Vlblq112m1x=(TextView) findViewById(R.id.Vlblq112m1x);

         chkq112m1x=(CheckBox) findViewById(R.id.chkq112m1x);
         chkq112m1x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112m1x.isChecked() == true) {

                     secq112m1x1.setVisibility(View.VISIBLE);
                     lineq112m1x1.setVisibility(View.VISIBLE);
                 } else if (chkq112m1x.isChecked() == false) {
                     secq112m1x1.setVisibility(View.GONE);
                     lineq112m1x1.setVisibility(View.GONE);

                     txtq112m1x1.setText("");


                 }

             }
         });

         secq112m1x1=(LinearLayout)findViewById(R.id.secq112m1x1);
         lineq112m1x1=(View)findViewById(R.id.lineq112m1x1);
         Vlblq112m1x1=(TextView) findViewById(R.id.Vlblq112m1x1);
         txtq112m1x1=(EditText) findViewById(R.id.txtq112m1x1);

         /*secq112k3 =(LinearLayout)findViewById(R.id.secq112k3);
         lineq112k3=(View)findViewById(R.id.lineq112k3);
         Vlblq112k3=(TextView) findViewById(R.id.Vlblq112k3);
        // txtq112k3=(EditText) findViewById(R.id.txtq112k3);
         rdogrpq112k3 = (RadioGroup) findViewById(R.id.rdogrpq112k3);

         rdoq112k31 = (RadioButton) findViewById(R.id.rdoq112k31);
         rdoq112k32 = (RadioButton) findViewById(R.id.rdoq112k32);
         rdogrpq112k3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrp112k3 = new String[] {"1","2"};
                 for (int i = 0; i < rdogrpq112k3.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpq112k3.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrp112k3[i];
                 }
                 if(rbData.equalsIgnoreCase("1"))
                 {
                     secq112k4.setVisibility(View.VISIBLE);
                     // lineq112k4.setVisibility(View.GONE);
                     secq112k4a.setVisibility(View.VISIBLE);
                     lineq112k4a.setVisibility(View.VISIBLE);
                     secq112k4b.setVisibility(View.VISIBLE);
                     lineq112k4b.setVisibility(View.VISIBLE);
                     secq112k4c.setVisibility(View.VISIBLE);
                     lineq112k4c.setVisibility(View.VISIBLE);
                     secq112k4d.setVisibility(View.VISIBLE);
                     lineq112k4d.setVisibility(View.VISIBLE);
                     secq112k4e.setVisibility(View.VISIBLE);
                     lineq112k4e.setVisibility(View.VISIBLE);
                     secq112k4x.setVisibility(View.VISIBLE);
                     lineq112k4x.setVisibility(View.VISIBLE);



                 }
                 else if(rbData.equalsIgnoreCase("2"))
                 {
                     secq112k4.setVisibility(View.GONE);
                     // lineq112k4.setVisibility(View.GONE);
                     secq112k4a.setVisibility(View.GONE);
                     lineq112k4a.setVisibility(View.GONE);
                     secq112k4b.setVisibility(View.GONE);
                     lineq112k4b.setVisibility(View.GONE);
                     secq112k4c.setVisibility(View.GONE);
                     lineq112k4c.setVisibility(View.GONE);
                     secq112k4d.setVisibility(View.GONE);
                     lineq112k4d.setVisibility(View.GONE);
                     secq112k4e.setVisibility(View.GONE);
                     lineq112k4e.setVisibility(View.GONE);
                     secq112k4x.setVisibility(View.GONE);
                     lineq112k4x.setVisibility(View.GONE);
                     chkq112k4a.setChecked(false);
                     chkq112k4b.setChecked(false);
                     chkq112k4c.setChecked(false);
                     chkq112k4d.setChecked(false);
                     chkq112k4e.setChecked(false);
                     chkq112k4x.setChecked(false);



                 }

             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });
         secq112k4=(LinearLayout)findViewById(R.id.secq112k4);
         secq112k4a=(LinearLayout)findViewById(R.id.secq112k4a);
         lineq112k4a=(View)findViewById(R.id.lineq112k4a);
         Vlblq112k4a=(TextView) findViewById(R.id.Vlblq112k4a);
         chkq112k4a=(CheckBox) findViewById(R.id.chkq112k4a);
         secq112k4b=(LinearLayout)findViewById(R.id.secq112k4b);
         lineq112k4b=(View)findViewById(R.id.lineq112k4b);
         Vlblq112k4b=(TextView) findViewById(R.id.Vlblq112k4b);
         chkq112k4b=(CheckBox) findViewById(R.id.chkq112k4b);
         secq112k4c=(LinearLayout)findViewById(R.id.secq112k4c);
         lineq112k4c=(View)findViewById(R.id.lineq112k4c);
         Vlblq112k4c=(TextView) findViewById(R.id.Vlblq112k4c);
         chkq112k4c=(CheckBox) findViewById(R.id.chkq112k4c);
         secq112k4d=(LinearLayout)findViewById(R.id.secq112k4d);
         lineq112k4d=(View)findViewById(R.id.lineq112k4d);
         Vlblq112k4d=(TextView) findViewById(R.id.Vlblq112k4d);
         chkq112k4d=(CheckBox) findViewById(R.id.chkq112k4d);
         secq112k4e=(LinearLayout)findViewById(R.id.secq112k4e);
         lineq112k4e=(View)findViewById(R.id.lineq112k4e);
         Vlblq112k4e=(TextView) findViewById(R.id.Vlblq112k4e);
         chkq112k4e=(CheckBox) findViewById(R.id.chkq112k4e);
         secq112k4x=(LinearLayout)findViewById(R.id.secq112k4x);
         lineq112k4x=(View)findViewById(R.id.lineq112k4x);
         Vlblq112k4x=(TextView) findViewById(R.id.Vlblq112k4x);
         chkq112k4x=(CheckBox) findViewById(R.id.chkq112k4x);
         chkq112k4x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112k4x.isChecked() == true) {

                     secq112k4x1.setVisibility(View.VISIBLE);
                     lineq112k4x1.setVisibility(View.VISIBLE);
                 } else if (chkq112k4x.isChecked() == false) {
                     secq112k4x1.setVisibility(View.GONE);
                     lineq112k4x1.setVisibility(View.GONE);

                     txtq112k4x1.setText("");


                 }

             }
         });

         secq112k4x1=(LinearLayout)findViewById(R.id.secq112k4x1);
         lineq112k4x1=(View)findViewById(R.id.lineq112k4x1);
         Vlblq112k4x1=(TextView) findViewById(R.id.Vlblq112k4x1);
         txtq112k4x1=(EditText) findViewById(R.id.txtq112k4x1);*/
         secq112l=(LinearLayout)findViewById(R.id.secq112l);
         lineq112l=(View)findViewById(R.id.lineq112l);
         Vlblq112l=(TextView) findViewById(R.id.Vlblq112l);
         chkq112l=(CheckBox) findViewById(R.id.chkq112l);
         chkq112l.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq112l.isChecked() == true) {



                     secq112l1.setVisibility(View.VISIBLE);
                     lineq112l1.setVisibility(View.VISIBLE);
                     secq112l2.setVisibility(View.VISIBLE);
                     lineq112l2.setVisibility(View.VISIBLE);
/*                     secq112l3.setVisibility(View.VISIBLE);
                       lineq112l3.setVisibility(View.VISIBLE);*/


                 } else if (chkq112l.isChecked() == false) {



                     txtq112l1.setText("");
                     txtq112l2.setText("");
                    // txtq112l3.setText("");
                     secq112l1.setVisibility(View.GONE);
                     lineq112l1.setVisibility(View.GONE);
                     secq112l2.setVisibility(View.GONE);
                     lineq112l2.setVisibility(View.GONE);
                   /*  secq112l3.setVisibility(View.GONE);
                     lineq112l3.setVisibility(View.GONE);*/


                 }

             }
         });
         secq112l1=(LinearLayout)findViewById(R.id.secq112l1);
         lineq112l1=(View)findViewById(R.id.lineq112l1);
         Vlblq112l1=(TextView) findViewById(R.id.Vlblq112l1);
         txtq112l1=(EditText) findViewById(R.id.txtq112l1);
         secq112l2=(LinearLayout)findViewById(R.id.secq112l2);
         lineq112l2=(View)findViewById(R.id.lineq112l2);
         Vlblq112l2=(TextView) findViewById(R.id.Vlblq112l2);
         txtq112l2=(EditText) findViewById(R.id.txtq112l2);
         secq113=(LinearLayout)findViewById(R.id.secq113);
         lineq113=(View)findViewById(R.id.lineq113);
         Vlblq113=(TextView) findViewById(R.id.Vlblq113);
         txtq113=(EditText) findViewById(R.id.txtq113);
       /*  secq112l3=(LinearLayout)findViewById(R.id.secq112l3);
         lineq112l3=(View)findViewById(R.id.lineq112l3);
         Vlblq112l3=(TextView) findViewById(R.id.Vlblq112l3);
         txtq112l3=(EditText) findViewById(R.id.txtq112l3);*/
         /*secq113=(LinearLayout)findViewById(R.id.secq113);
         lineq113=(View)findViewById(R.id.lineq113);

         chkq113ap1=(CheckBox) findViewById(R.id.chkq113ap1);
         chkq113ap2=(CheckBox) findViewById(R.id.chkq113ap2);
         chkq113ap3=(CheckBox) findViewById(R.id.chkq113ap3);
         chkq113ap4=(CheckBox) findViewById(R.id.chkq113ap4);*/

        /* chkq113ap1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq113ap1.isChecked() == true) {



                     secq113a1.setVisibility(View.VISIBLE);
                     lineq113a1.setVisibility(View.VISIBLE);
                     secq113a2.setVisibility(View.VISIBLE);
                     lineq113a2.setVisibility(View.VISIBLE);



                 } else if (chkq113ap1.isChecked() == false) {



                     txtq113a1.setText("");
                     txtq113a2.setText("");


                     secq113a1.setVisibility(View.GONE);
                     lineq113a1.setVisibility(View.GONE);
                     secq113a2.setVisibility(View.GONE);
                     lineq113a2.setVisibility(View.GONE);


                 }

             }
         });*/
        /* secq113a1=(LinearLayout)findViewById(R.id.secq113a1);
         lineq113a1=(View)findViewById(R.id.lineq113a1);
         Vlblq113a1=(TextView) findViewById(R.id.Vlblq113a1);
         txtq113a1=(EditText) findViewById(R.id.txtq113a1);
         secq113a2=(LinearLayout)findViewById(R.id.secq113a2);
         lineq113a2=(View)findViewById(R.id.lineq113a2);
         Vlblq113a2=(TextView) findViewById(R.id.Vlblq113a2);
         txtq113a2=(EditText) findViewById(R.id.txtq113a2);*/

         /*chkq113ap2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq113ap2.isChecked() == true) {



                     secq113b1.setVisibility(View.VISIBLE);
                     lineq113b1.setVisibility(View.VISIBLE);
                     secq113b2.setVisibility(View.VISIBLE);
                     lineq113b2.setVisibility(View.VISIBLE);



                 } else if (chkq113ap2.isChecked() == false) {



                     txtq113b1.setText("");
                     txtq113b2.setText("");


                     secq113b1.setVisibility(View.GONE);
                     lineq113b1.setVisibility(View.GONE);
                     secq113b2.setVisibility(View.GONE);
                     lineq113b2.setVisibility(View.GONE);


                 }

             }
         });*/
        /* secq113b1=(LinearLayout)findViewById(R.id.secq113b1);
         lineq113b1=(View)findViewById(R.id.lineq113b1);
         Vlblq113b1=(TextView) findViewById(R.id.Vlblq113b1);
         txtq113b1=(EditText) findViewById(R.id.txtq113b1);
         secq113b2=(LinearLayout)findViewById(R.id.secq113b2);
         lineq113b2=(View)findViewById(R.id.lineq113b2);
         Vlblq113b2=(TextView) findViewById(R.id.Vlblq113b2);
         txtq113b2=(EditText) findViewById(R.id.txtq113b2);*/

        /* chkq113ap3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq113ap3.isChecked() == true) {



                     secq113c1.setVisibility(View.VISIBLE);
                     lineq113c1.setVisibility(View.VISIBLE);
                     secq113c2.setVisibility(View.VISIBLE);
                     lineq113c2.setVisibility(View.VISIBLE);



                 } else if (chkq113ap3.isChecked() == false) {



                     txtq113c1.setText("");
                     txtq113c2.setText("");


                     secq113c1.setVisibility(View.GONE);
                     lineq113c1.setVisibility(View.GONE);
                     secq113c2.setVisibility(View.GONE);
                     lineq113c2.setVisibility(View.GONE);


                 }

             }
         });*/
         /*secq113c1=(LinearLayout)findViewById(R.id.secq113c1);
         lineq113c1=(View)findViewById(R.id.lineq113c1);
         Vlblq113c1=(TextView) findViewById(R.id.Vlblq113c1);
         txtq113c1=(EditText) findViewById(R.id.txtq113c1);
         secq113c2=(LinearLayout)findViewById(R.id.secq113c2);
         lineq113c2=(View)findViewById(R.id.lineq113c2);
         Vlblq113c2=(TextView) findViewById(R.id.Vlblq113c2);
         txtq113c2=(EditText) findViewById(R.id.txtq113c2);*/

        /* chkq113ap4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (chkq113ap4.isChecked() == true) {



                     secq113d1.setVisibility(View.VISIBLE);
                     lineq113d1.setVisibility(View.VISIBLE);
                     secq113d2.setVisibility(View.VISIBLE);
                     lineq113d2.setVisibility(View.VISIBLE);



                 } else if (chkq113ap4.isChecked() == false) {



                     txtq113d1.setText("");
                     txtq113d2.setText("");


                     secq113d1.setVisibility(View.GONE);
                     lineq113d1.setVisibility(View.GONE);
                     secq113d2.setVisibility(View.GONE);
                     lineq113d2.setVisibility(View.GONE);


                 }

             }
         });*/
         /*secq113d1=(LinearLayout)findViewById(R.id.secq113d1);
         lineq113d1=(View)findViewById(R.id.lineq113d1);
         Vlblq113d1=(TextView) findViewById(R.id.Vlblq113d1);
         txtq113d1=(EditText) findViewById(R.id.txtq113d1);
         secq113d2=(LinearLayout)findViewById(R.id.secq113d2);
         lineq113d2=(View)findViewById(R.id.lineq113d2);
         Vlblq113d2=(TextView) findViewById(R.id.Vlblq113d2);
         txtq113d2=(EditText) findViewById(R.id.txtq113d2);*/

         secq111x1.setVisibility(View.GONE);
         lineq111x1.setVisibility(View.GONE);

         secq112a1.setVisibility(View.GONE);
         lineq112a1.setVisibility(View.GONE);
         secq112a2.setVisibility(View.GONE);
         lineq112a2.setVisibility(View.GONE);
        // secq112a3.setVisibility(View.GONE);
        // lineq112a3.setVisibility(View.GONE);
         secq112b1.setVisibility(View.GONE);
         lineq112b1.setVisibility(View.GONE);
         secq112b2.setVisibility(View.GONE);
         lineq112b2.setVisibility(View.GONE);
       //  secq112b3.setVisibility(View.GONE);
        // lineq112b3.setVisibility(View.GONE);
         secq112c1.setVisibility(View.GONE);
         lineq112c1.setVisibility(View.GONE);
         secq112c2.setVisibility(View.GONE);
         lineq112c2.setVisibility(View.GONE);
        // secq112c3.setVisibility(View.GONE);
        // lineq112c3.setVisibility(View.GONE);
         secq112d1.setVisibility(View.GONE);
         lineq112d1.setVisibility(View.GONE);
         secq112d2.setVisibility(View.GONE);
         lineq112d2.setVisibility(View.GONE);
        /* secq112d3.setVisibility(View.GONE);
         lineq112d3.setVisibility(View.GONE);*/
         secq112e1.setVisibility(View.GONE);
         lineq112e1.setVisibility(View.GONE);
         secq112e2.setVisibility(View.GONE);
         lineq112e2.setVisibility(View.GONE);
        // secq112e3.setVisibility(View.GONE);
         //lineq112e3.setVisibility(View.GONE);
         secq112f1.setVisibility(View.GONE);
         lineq112f1.setVisibility(View.GONE);
         secq112f2.setVisibility(View.GONE);
         lineq112f2.setVisibility(View.GONE);
         //secq112f3.setVisibility(View.GONE);
         //lineq112f3.setVisibility(View.GONE);
         secq112g1.setVisibility(View.GONE);
         lineq112g1.setVisibility(View.GONE);
         secq112g2.setVisibility(View.GONE);
         lineq112g2.setVisibility(View.GONE);
         //secq112g3.setVisibility(View.GONE);
         //lineq112g3.setVisibility(View.GONE);
         secq112h1.setVisibility(View.GONE);
         lineq112h1.setVisibility(View.GONE);
         secq112h2.setVisibility(View.GONE);
         lineq112h2.setVisibility(View.GONE);
         //secq112h3.setVisibility(View.GONE);
         //lineq112h3.setVisibility(View.GONE);
         secq112i1.setVisibility(View.GONE);
         lineq112i1.setVisibility(View.GONE);
         secq112i2.setVisibility(View.GONE);
         lineq112i2.setVisibility(View.GONE);
         //secq112i3.setVisibility(View.GONE);
         //lineq112i3.setVisibility(View.GONE);
         secq112j1.setVisibility(View.GONE);
         lineq112j1.setVisibility(View.GONE);
         secq112j2.setVisibility(View.GONE);
         lineq112j2.setVisibility(View.GONE);
         //secq112j3.setVisibility(View.GONE);
         //lineq112j3.setVisibility(View.GONE);
         secq112k1.setVisibility(View.GONE);
         lineq112k1.setVisibility(View.GONE);
         secq112k2.setVisibility(View.GONE);
         lineq112k2.setVisibility(View.GONE);
       /*  secq112k3.setVisibility(View.GONE);
         lineq112k3.setVisibility(View.GONE);*/
         secq112l1.setVisibility(View.GONE);
         lineq112l1.setVisibility(View.GONE);
         secq112l2.setVisibility(View.GONE);
         lineq112l2.setVisibility(View.GONE);

         //secq112l3.setVisibility(View.GONE);
         //lineq112l3.setVisibility(View.GONE);


        // secq112a3.setVisibility(View.GONE);
       //  lineq112a3.setVisibility(View.GONE);

       //  secq111.setVisibility(View.GONE);
         secq111_0.setVisibility(View.GONE);
         // lineq112a4.setVisibility(View.GONE);
         secq111a.setVisibility(View.GONE);
         lineq111a.setVisibility(View.GONE);
         secq111b.setVisibility(View.GONE);
         lineq111b.setVisibility(View.GONE);
         secq111c.setVisibility(View.GONE);
         lineq111c.setVisibility(View.GONE);
         secq111d.setVisibility(View.GONE);
         lineq111d.setVisibility(View.GONE);
         secq111e.setVisibility(View.GONE);
         lineq111e.setVisibility(View.GONE);
         secq111x.setVisibility(View.GONE);
         lineq111x.setVisibility(View.GONE);

         secq111p1x1.setVisibility(View.GONE);
         lineq111p1x1.setVisibility(View.GONE);

         secq112.setVisibility(View.GONE);
         lineq112.setVisibility(View.GONE);

         secq112a.setVisibility(View.GONE);
         lineq112a.setVisibility(View.GONE);

         secq112b.setVisibility(View.GONE);
         lineq112b.setVisibility(View.GONE);

         secq112c.setVisibility(View.GONE);
         lineq112c.setVisibility(View.GONE);

         secq112d.setVisibility(View.GONE);
         lineq112d.setVisibility(View.GONE);

         secq112k.setVisibility(View.GONE);
         lineq112k.setVisibility(View.GONE);

         secq111p1_0.setVisibility(View.GONE);
         // lineq112a4.setVisibility(View.GONE);
         secq111p1a.setVisibility(View.GONE);
         lineq111p1a.setVisibility(View.GONE);
         secq111p1b.setVisibility(View.GONE);
         lineq111p1b.setVisibility(View.GONE);
         secq111p1c.setVisibility(View.GONE);
         lineq111p1c.setVisibility(View.GONE);
         secq111p1d.setVisibility(View.GONE);
         lineq111p1d.setVisibility(View.GONE);
         secq111p1e.setVisibility(View.GONE);
         lineq111p1e.setVisibility(View.GONE);
         secq111p1x.setVisibility(View.GONE);
         lineq111p1x.setVisibility(View.GONE);

         secq112p2_0.setVisibility(View.GONE);
         // lineq112a4.setVisibility(View.GONE);
         secq112p2a.setVisibility(View.GONE);
         lineq112p2a.setVisibility(View.GONE);
         secq112p2b.setVisibility(View.GONE);
         lineq112p2b.setVisibility(View.GONE);
         secq112p2c.setVisibility(View.GONE);
         lineq112p2c.setVisibility(View.GONE);
         secq112p2d.setVisibility(View.GONE);
         lineq112p2d.setVisibility(View.GONE);
         secq112p2e.setVisibility(View.GONE);
         lineq112p2e.setVisibility(View.GONE);
         secq112p2x.setVisibility(View.GONE);
         lineq112p2x.setVisibility(View.GONE);
         secq112p2x1.setVisibility(View.GONE);
         lineq112p2x1.setVisibility(View.GONE);

         secq112p3_0.setVisibility(View.GONE);
         // lineq112a4.setVisibility(View.GONE);
         secq112p3a.setVisibility(View.GONE);
         lineq112p3a.setVisibility(View.GONE);
         secq112p3b.setVisibility(View.GONE);
         lineq112p3b.setVisibility(View.GONE);
         secq112p3c.setVisibility(View.GONE);
         lineq112p3c.setVisibility(View.GONE);
         secq112p3d.setVisibility(View.GONE);
         lineq112p3d.setVisibility(View.GONE);
         secq112p3e.setVisibility(View.GONE);
         lineq112p3e.setVisibility(View.GONE);
         secq112p3x.setVisibility(View.GONE);
         lineq112p3x.setVisibility(View.GONE);
         secq112p3x1.setVisibility(View.GONE);
         lineq112p3x1.setVisibility(View.GONE);

         secq112m1_0.setVisibility(View.GONE);
         // lineq112a4.setVisibility(View.GONE);
         secq112m1a.setVisibility(View.GONE);
         lineq112m1a.setVisibility(View.GONE);
         secq112m1b.setVisibility(View.GONE);
         lineq112m1b.setVisibility(View.GONE);
         secq112m1c.setVisibility(View.GONE);
         lineq112m1c.setVisibility(View.GONE);
         secq112m1d.setVisibility(View.GONE);
         lineq112m1d.setVisibility(View.GONE);
         secq112m1e.setVisibility(View.GONE);
         lineq112m1e.setVisibility(View.GONE);
         secq112m1x.setVisibility(View.GONE);
         lineq112m1x.setVisibility(View.GONE);
         secq112m1x1.setVisibility(View.GONE);
         lineq112m1x1.setVisibility(View.GONE);




       /*  secq112b4x1.setVisibility(View.GONE);
         lineq112b4x1.setVisibility(View.GONE);

         secq112b3.setVisibility(View.GONE);
         lineq112b3.setVisibility(View.GONE);

         secq112b4.setVisibility(View.GONE);
         // lineq112b4.setVisibility(View.GONE);
         secq112b4a.setVisibility(View.GONE);
         lineq112b4a.setVisibility(View.GONE);
         secq112b4b.setVisibility(View.GONE);
         lineq112b4b.setVisibility(View.GONE);
         secq112b4c.setVisibility(View.GONE);
         lineq112b4c.setVisibility(View.GONE);
         secq112b4d.setVisibility(View.GONE);
         lineq112b4d.setVisibility(View.GONE);
         secq112b4e.setVisibility(View.GONE);
         lineq112b4e.setVisibility(View.GONE);
         secq112b4x.setVisibility(View.GONE);
         lineq112b4x.setVisibility(View.GONE);*/

        /* secq112c4x1.setVisibility(View.GONE);
         lineq112c4x1.setVisibility(View.GONE);

         secq112c4.setVisibility(View.GONE);
        // lineq112c4.setVisibility(View.GONE);
         secq112c4a.setVisibility(View.GONE);
         lineq112c4a.setVisibility(View.GONE);
         secq112c4b.setVisibility(View.GONE);
         lineq112c4b.setVisibility(View.GONE);
         secq112c4c.setVisibility(View.GONE);
         lineq112c4c.setVisibility(View.GONE);
         secq112c4d.setVisibility(View.GONE);
         lineq112c4d.setVisibility(View.GONE);
         secq112c4e.setVisibility(View.GONE);
         lineq112c4e.setVisibility(View.GONE);
         secq112c4x.setVisibility(View.GONE);
         lineq112c4x.setVisibility(View.GONE);
         secq112c4x1.setVisibility(View.GONE);
         lineq112c4x1.setVisibility(View.GONE);*/



         /*secq112d4.setVisibility(View.GONE);
         // lineq112d4.setVisibility(View.GONE);
         secq112d4a.setVisibility(View.GONE);
         lineq112d4a.setVisibility(View.GONE);
         secq112d4b.setVisibility(View.GONE);
         lineq112d4b.setVisibility(View.GONE);
         secq112d4c.setVisibility(View.GONE);
         lineq112d4c.setVisibility(View.GONE);
         secq112d4d.setVisibility(View.GONE);
         lineq112d4d.setVisibility(View.GONE);
         secq112d4e.setVisibility(View.GONE);
         lineq112d4e.setVisibility(View.GONE);
         secq112d4x.setVisibility(View.GONE);
         lineq112d4x.setVisibility(View.GONE);


         secq112d4x1.setVisibility(View.GONE);
         lineq112d4x1.setVisibility(View.GONE);*/

         /*secq112k4x1.setVisibility(View.GONE);
         lineq112k4x1.setVisibility(View.GONE);

         secq112k4.setVisibility(View.GONE);
         // lineq112k4.setVisibility(View.GONE);
         secq112k4a.setVisibility(View.GONE);
         lineq112k4a.setVisibility(View.GONE);
         secq112k4b.setVisibility(View.GONE);
         lineq112k4b.setVisibility(View.GONE);
         secq112k4c.setVisibility(View.GONE);
         lineq112k4c.setVisibility(View.GONE);
         secq112k4d.setVisibility(View.GONE);
         lineq112k4d.setVisibility(View.GONE);
         secq112k4e.setVisibility(View.GONE);
         lineq112k4e.setVisibility(View.GONE);
         secq112k4x.setVisibility(View.GONE);
         lineq112k4x.setVisibility(View.GONE);*/

        /* secq113a1.setVisibility(View.GONE);
         lineq113a1.setVisibility(View.GONE);
         secq113a2.setVisibility(View.GONE);
         lineq113a2.setVisibility(View.GONE);
         secq113b1.setVisibility(View.GONE);
         lineq113b1.setVisibility(View.GONE);
         secq113b2.setVisibility(View.GONE);
         lineq113b2.setVisibility(View.GONE);
         secq113c1.setVisibility(View.GONE);
         lineq113c1.setVisibility(View.GONE);
         secq113c2.setVisibility(View.GONE);
         lineq113c2.setVisibility(View.GONE);
         secq113d1.setVisibility(View.GONE);
         lineq113d1.setVisibility(View.GONE);
         secq113d2.setVisibility(View.GONE);
         lineq113d2.setVisibility(View.GONE);*/
         DataSearch(txtidno.getText().toString());
        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 

           DataSave();
        }});
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Section_1_Manager_Service.this, e.getMessage());
         return;
     }
 }

 private void DataSave()
 {
   try
     {
 
         String DV="";

         if(txtidno.getText().toString().length()==0 & secidno.isShown())
           {
             Connection.MessageBox(Section_1_Manager_Service.this, "IDNo খালি হতে পারে না ।");
             txtidno.requestFocus(); 
             return;	
           }

 
         String SQL = "";
         RadioButton rb;

         Section_1_Manager_Service_DataModel objSave = new Section_1_Manager_Service_DataModel();
         objSave.setidno(txtidno.getText().toString());
         String[] d_rdogrpq111 = new String[] {"1","2"};
         objSave.setq111("");
         for (int i = 0; i < rdogrpq111.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpq111.getChildAt(i);
             if (rb.isChecked()) objSave.setq111(d_rdogrpq111[i]);
         }
         //objSave.setq111a(txtq111a.getText().toString());
         objSave.setq111a((chkq111a.isChecked()?"1":(secq111a.isShown()?"2":"")));
         objSave.setq111b((chkq111b.isChecked()?"1":(secq111b.isShown()?"2":"")));
         objSave.setq111c((chkq111c.isChecked()?"1":(secq111c.isShown()?"2":"")));
         objSave.setq111d((chkq111d.isChecked()?"1":(secq111d.isShown()?"2":"")));
         objSave.setq111e((chkq111e.isChecked()?"1":(secq111e.isShown()?"2":"")));
         objSave.setq111x((chkq111x.isChecked()?"1":(secq111x.isShown()?"2":"")));
         objSave.setq111x1(txtq111x1.getText().toString());
         objSave.setq112a((chkq112a.isChecked()?"1":(secq112a.isShown()?"2":"")));
         objSave.setq112a1(txtq112a1.getText().toString());
         objSave.setq112a2(txtq112a2.getText().toString());
      //   objSave.setq112a3(txtq112a3.getText().toString());
         objSave.setq112b((chkq112b.isChecked()?"1":(secq112b.isShown()?"2":"")));
         objSave.setq112b1(txtq112b1.getText().toString());
         objSave.setq112b2(txtq112b2.getText().toString());
       //  objSave.setq112b3(txtq112b3.getText().toString());
         objSave.setq112c((chkq112c.isChecked()?"1":(secq112c.isShown()?"2":"")));
         objSave.setq112c1(txtq112c1.getText().toString());
         objSave.setq112c2(txtq112c2.getText().toString());
         //objSave.setq112c3(txtq112c3.getText().toString());
         objSave.setq112d((chkq112d.isChecked()?"1":(secq112d.isShown()?"2":"")));
         objSave.setq112d1(txtq112d1.getText().toString());
         objSave.setq112d2(txtq112d2.getText().toString());
        // objSave.setq112d3(txtq112d3.getText().toString());
         objSave.setq112e((chkq112e.isChecked()?"1":(secq112e.isShown()?"2":"")));
         objSave.setq112e1(txtq112e1.getText().toString());
         objSave.setq112e2(txtq112e2.getText().toString());
        // objSave.setq112e3(txtq112e3.getText().toString());
         objSave.setq112f((chkq112f.isChecked()?"1":(secq112f.isShown()?"2":"")));
         objSave.setq112f1(txtq112f1.getText().toString());
         objSave.setq112f2(txtq112f2.getText().toString());
         //objSave.setq112f3(txtq112f3.getText().toString());
         objSave.setq112g((chkq112g.isChecked()?"1":(secq112g.isShown()?"2":"")));
         objSave.setq112g1(txtq112g1.getText().toString());
         objSave.setq112g2(txtq112g2.getText().toString());
         //objSave.setq112g3(txtq112g3.getText().toString());
         objSave.setq112h((chkq112h.isChecked()?"1":(secq112h.isShown()?"2":"")));
         objSave.setq112h1(txtq112h1.getText().toString());
         objSave.setq112h2(txtq112h2.getText().toString());
        // objSave.setq112h3(txtq112h3.getText().toString());
         objSave.setq112i((chkq112i.isChecked()?"1":(secq112i.isShown()?"2":"")));
         objSave.setq112i1(txtq112i1.getText().toString());
         objSave.setq112i2(txtq112i2.getText().toString());
        // objSave.setq112i3(txtq112i3.getText().toString());
         objSave.setq112j((chkq112j.isChecked()?"1":(secq112j.isShown()?"2":"")));
         objSave.setq112j1(txtq112j1.getText().toString());
         objSave.setq112j2(txtq112j2.getText().toString());
         //objSave.setq112j3(txtq112j3.getText().toString());
         objSave.setq112k((chkq112k.isChecked()?"1":(secq112k.isShown()?"2":"")));
         objSave.setq112k1(txtq112k1.getText().toString());
         objSave.setq112k2(txtq112k2.getText().toString());
        // objSave.setq112k3(txtq112k3.getText().toString());
         objSave.setq112l((chkq112l.isChecked()?"1":(secq112l.isShown()?"2":"")));
     /*    objSave.setq113ap1((chkq113ap1.isChecked()?"1":(chkq113ap1.isShown()?"2":"")));
         objSave.setq113ap2((chkq113ap2.isChecked()?"1":(chkq113ap2.isShown()?"2":"")));
         objSave.setq113ap3((chkq113ap3.isChecked()?"1":(chkq113ap3.isShown()?"2":"")));
         objSave.setq113ap4((chkq113ap4.isChecked()?"1":(chkq113ap4.isShown()?"2":"")));*/
         objSave.setq112l1(txtq112l1.getText().toString());
         objSave.setq112l2(txtq112l2.getText().toString());
         objSave.setq113(txtq113.getText().toString());

         objSave.setq112p1a((chkq111p1a.isChecked()?"1":(secq111p1a.isShown()?"2":"")));
         objSave.setq112p1b((chkq111p1b.isChecked()?"1":(secq111p1b.isShown()?"2":"")));
         objSave.setq112p1c((chkq111p1c.isChecked()?"1":(secq111p1c.isShown()?"2":"")));
         objSave.setq112p1d((chkq111p1d.isChecked()?"1":(secq111p1d.isShown()?"2":"")));
         objSave.setq112p1e((chkq111p1e.isChecked()?"1":(secq111p1e.isShown()?"2":"")));
         objSave.setq112p1x((chkq111p1x.isChecked()?"1":(secq111p1x.isShown()?"2":"")));
         objSave.setq112p1x1(txtq111p1x1.getText().toString());

         objSave.setq112p2a((chkq112p2a.isChecked()?"1":(secq112p2a.isShown()?"2":"")));
         objSave.setq112p2b((chkq112p2b.isChecked()?"1":(secq112p2b.isShown()?"2":"")));
         objSave.setq112p2c((chkq112p2c.isChecked()?"1":(secq112p2c.isShown()?"2":"")));
         objSave.setq112p2d((chkq112p2d.isChecked()?"1":(secq112p2d.isShown()?"2":"")));
         objSave.setq112p2e((chkq112p2e.isChecked()?"1":(secq112p2e.isShown()?"2":"")));
         objSave.setq112p2x((chkq112p2x.isChecked()?"1":(secq112p2x.isShown()?"2":"")));
         objSave.setq112p2x1(txtq112p2x1.getText().toString());

         objSave.setq112p3a((chkq112p3a.isChecked()?"1":(secq112p3a.isShown()?"2":"")));
         objSave.setq112p3b((chkq112p3b.isChecked()?"1":(secq112p3b.isShown()?"2":"")));
         objSave.setq112p3c((chkq112p3c.isChecked()?"1":(secq112p3c.isShown()?"2":"")));
         objSave.setq112p3d((chkq112p3d.isChecked()?"1":(secq112p3d.isShown()?"2":"")));
         objSave.setq112p3e((chkq112p3e.isChecked()?"1":(secq112p3e.isShown()?"2":"")));
         objSave.setq112p3x((chkq112p3x.isChecked()?"1":(secq112p3x.isShown()?"2":"")));
         objSave.setq112p3x1(txtq112p3x1.getText().toString());

         objSave.setq112m1a((chkq112m1a.isChecked()?"1":(secq112m1a.isShown()?"2":"")));
         objSave.setq112m1b((chkq112m1b.isChecked()?"1":(secq112m1b.isShown()?"2":"")));
         objSave.setq112m1c((chkq112m1c.isChecked()?"1":(secq112m1c.isShown()?"2":"")));
         objSave.setq112m1d((chkq112m1d.isChecked()?"1":(secq112m1d.isShown()?"2":"")));
         objSave.setq112m1e((chkq112m1e.isChecked()?"1":(secq112m1e.isShown()?"2":"")));
         objSave.setq112m1x((chkq112m1x.isChecked()?"1":(secq112m1x.isShown()?"2":"")));
         objSave.setq112m1x1(txtq112m1x1.getText().toString());

        // objSave.setq112l3(txtq112l3.getText().toString());
        /* objSave.setq113a1(txtq113a1.getText().toString());
         objSave.setq113a2(txtq113a2.getText().toString());
         objSave.setq113b1(txtq113b1.getText().toString());
         objSave.setq113b2(txtq113b2.getText().toString());
         objSave.setq113c1(txtq113c1.getText().toString());
         objSave.setq113c2(txtq113c2.getText().toString());
         objSave.setq113d1(txtq113d1.getText().toString());
         objSave.setq113d2(txtq113d2.getText().toString());*/
         objSave.setEnDt(Global.DateTimeNowYMDHMS());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         objSave.setmodifyDate(Global.DateTimeNowYMDHMS());
         //objSave.setLat(Double.toString(currentLatitude));
         //objSave.setLon(Double.toString(currentLongitude));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {
             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Connection.MessageBox(Section_1_Manager_Service.this, "Saved Successfully");
         }
         else{
             Connection.MessageBox(Section_1_Manager_Service.this, status);
             return;
         }

         g.setIdNo(txtidno.getText().toString());
         finish();
         Intent f1 = new Intent(getApplicationContext(), iron_gps.class);
         startActivity(f1);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Section_1_Manager_Service.this, e.getMessage());
         return;
     }
 }

 private void DataSearch(String idno)
     {
       try
        {
     
           RadioButton rb;
            Section_1_Manager_Service_DataModel d = new Section_1_Manager_Service_DataModel();
           String SQL = "Select * from "+ TableName +"  Where idno='"+ idno +"'";
           List<Section_1_Manager_Service_DataModel> data = d.SelectAll(this, SQL);
           for(Section_1_Manager_Service_DataModel item : data){
             txtidno.setText(item.getidno());
            // txtq111a.setText(item.getq111a());
               String[] d_rdogrpq111 = new String[] {"1","2"};
               for (int i = 0; i < d_rdogrpq111.length; i++)
               {
                   if (item.getq111().equals(String.valueOf(d_rdogrpq111[i])))
                   {
                       rb = (RadioButton)rdogrpq111.getChildAt(i);
                       rb.setChecked(true);
                   }
               }
               /*if(item.getq111().equals("1"))
               {
                   chkq111.setChecked(true);
               }
               else if(item.getq112a().equals("2"))
               {
                   chkq112a.setChecked(false);
               }*/


               if(item.getq111a().equals("1"))
               {
                   chkq111a.setChecked(true);
               }
               else if(item.getq111a().equals("2"))
               {
                   chkq111a.setChecked(false);
               }

               if(item.getq111b().equals("1"))
               {
                   chkq111b.setChecked(true);
               }
               else if(item.getq111b().equals("2"))
               {
                   chkq111b.setChecked(false);
               }
               if(item.getq111c().equals("1"))
               {
                   chkq111c.setChecked(true);
               }
               else if(item.getq111c().equals("2"))
               {
                   chkq111c.setChecked(false);
               }
               if(item.getq111d().equals("1"))
               {
                   chkq111d.setChecked(true);
               }
               else if(item.getq111d().equals("2"))
               {
                   chkq111d.setChecked(false);
               }
               if(item.getq111e().equals("1"))
               {
                   chkq111e.setChecked(true);
               }
               else if(item.getq111e().equals("2"))
               {
                   chkq111e.setChecked(false);
               }
               if(item.getq111x().equals("1"))
               {
                   chkq111x.setChecked(true);
               }
               else if(item.getq111x().equals("2"))
               {
                   chkq111x.setChecked(false);
               }
               txtq111x1.setText(item.getq111x1());

               if(item.getq112a().equals("1"))
               {
                   chkq112a.setChecked(true);
               }
               else if(item.getq112a().equals("2"))
               {
                   chkq112a.setChecked(false);
               }

               txtq112a1.setText(item.getq112a1());
               txtq112a2.setText(item.getq112a2());
             //  txtq112a3.setText(item.getq112a3());


               if(item.getq112b().equals("1"))
               {
                   chkq112b.setChecked(true);
               }
               else if(item.getq112b().equals("2"))
               {
                   chkq112b.setChecked(false);
               }
               txtq112b1.setText(item.getq112b1());
               txtq112b2.setText(item.getq112b2());
             //  txtq112b3.setText(item.getq112b3());
               if(item.getq112c().equals("1"))
               {
                   chkq112c.setChecked(true);
               }
               else if(item.getq112c().equals("2"))
               {
                   chkq112c.setChecked(false);
               }
               txtq112c1.setText(item.getq112c1());
               txtq112c2.setText(item.getq112c2());
              // txtq112c3.setText(item.getq112c3());
               if(item.getq112d().equals("1"))
               {
                   chkq112d.setChecked(true);
               }
               else if(item.getq112d().equals("2"))
               {
                   chkq112d.setChecked(false);
               }
               txtq112d1.setText(item.getq112d1());
               txtq112d2.setText(item.getq112d2());
             //  txtq112d3.setText(item.getq112d3());
               if(item.getq112e().equals("1"))
               {
                   chkq112e.setChecked(true);
               }
               else if(item.getq112e().equals("2"))
               {
                   chkq112e.setChecked(false);
               }
               txtq112e1.setText(item.getq112e1());
               txtq112e2.setText(item.getq112e2());
               //txtq112e3.setText(item.getq112e3());
               if(item.getq112f().equals("1"))
               {
                   chkq112f.setChecked(true);
               }
               else if(item.getq112f().equals("2"))
               {
                   chkq112f.setChecked(false);
               }
               txtq112f1.setText(item.getq112f1());
               txtq112f2.setText(item.getq112f2());
              // txtq112f3.setText(item.getq112f3());
               if(item.getq112g().equals("1"))
               {
                   chkq112g.setChecked(true);
               }
               else if(item.getq112g().equals("2"))
               {
                   chkq112g.setChecked(false);
               }
               txtq112g1.setText(item.getq112g1());
               txtq112g2.setText(item.getq112g2());
              // txtq112g3.setText(item.getq112g3());
               if(item.getq112h().equals("1"))
               {
                   chkq112h.setChecked(true);
               }
               else if(item.getq112h().equals("2"))
               {
                   chkq112h.setChecked(false);
               }
               txtq112h1.setText(item.getq112h1());
               txtq112h2.setText(item.getq112h2());
              // txtq112h3.setText(item.getq112h3());
               if(item.getq112i().equals("1"))
               {
                   chkq112i.setChecked(true);
               }
               else if(item.getq112i().equals("2"))
               {
                   chkq112i.setChecked(false);
               }
               txtq112i1.setText(item.getq112i1());
               txtq112i2.setText(item.getq112i2());
              // txtq112i3.setText(item.getq112i3());
               if(item.getq112j().equals("1"))
               {
                   chkq112j.setChecked(true);
               }
               else if(item.getq112j().equals("2"))
               {
                   chkq112j.setChecked(false);
               }
               txtq112j1.setText(item.getq112j1());
               txtq112j2.setText(item.getq112j2());
               //txtq112j3.setText(item.getq112j3());
               if(item.getq112k().equals("1"))
               {
                   chkq112k.setChecked(true);
               }
               else if(item.getq112k().equals("2"))
               {
                   chkq112k.setChecked(false);
               }
               txtq112k1.setText(item.getq112k1());
               txtq112k2.setText(item.getq112k2());
             //  txtq112k3.setText(item.getq112k3());
               if(item.getq112l().equals("1"))
               {
                   chkq112l.setChecked(true);
               }
               else if(item.getq112l().equals("2"))
               {
                   chkq112l.setChecked(false);
               }

               /*if(item.getq113ap1().equals("1"))
               {
                   chkq113ap1.setChecked(true);
               }
               else if(item.getq113ap1().equals("2"))
               {
                   chkq113ap1.setChecked(false);
               }

               if(item.getq113ap2().equals("1"))
               {
                   chkq113ap2.setChecked(true);
               }
               else if(item.getq113ap2().equals("2"))
               {
                   chkq113ap2.setChecked(false);
               }

               if(item.getq113ap3().equals("1"))
               {
                   chkq113ap3.setChecked(true);
               }
               else if(item.getq113ap3().equals("2"))
               {
                   chkq113ap3.setChecked(false);
               }

               if(item.getq113ap4().equals("1"))
               {
                   chkq113ap4.setChecked(true);
               }
               else if(item.getq113ap4().equals("2"))
               {
                   chkq113ap4.setChecked(false);
               }*/

               txtq112l1.setText(item.getq112l1());
               txtq112l2.setText(item.getq112l2());
               txtq113.setText(item.getq113());

               if(item.getq112p1a().equals("1"))
               {
                   chkq111p1a.setChecked(true);
               }
               else if(item.getq112p1a().equals("2"))
               {
                   chkq111p1a.setChecked(false);
               }

               if(item.getq112p1b().equals("1"))
               {
                   chkq111p1b.setChecked(true);
               }
               else if(item.getq112p1b().equals("2"))
               {
                   chkq111p1b.setChecked(false);
               }
               if(item.getq112p1c().equals("1"))
               {
                   chkq111p1c.setChecked(true);
               }
               else if(item.getq112p1c().equals("2"))
               {
                   chkq111p1c.setChecked(false);
               }
               if(item.getq112p1d().equals("1"))
               {
                   chkq111p1d.setChecked(true);
               }
               else if(item.getq112p1d().equals("2"))
               {
                   chkq111p1d.setChecked(false);
               }
               if(item.getq112p1e().equals("1"))
               {
                   chkq111p1e.setChecked(true);
               }
               else if(item.getq112p1e().equals("2"))
               {
                   chkq111p1e.setChecked(false);
               }
               if(item.getq112p1x().equals("1"))
               {
                   chkq111p1x.setChecked(true);
               }
               else if(item.getq112p1x().equals("2"))
               {
                   chkq111p1x.setChecked(false);
               }
               txtq111p1x1.setText(item.getq112p1x1());

            /*   d._q112m1a = cur.getString(cur.getColumnIndex("q112m1a"));
               d._q112m1b = cur.getString(cur.getColumnIndex("q112m1b"));
               d._q112m1c = cur.getString(cur.getColumnIndex("q112m1c"));
               d._q112m1d = cur.getString(cur.getColumnIndex("q112m1d"));
               d._q112m1e = cur.getString(cur.getColumnIndex("q112m1e"));
               d._q112m1x = cur.getString(cur.getColumnIndex("q112m1x"));
               d._q112m1x1 = cur.getString(cur.getColumnIndex("q112m1x1"));*/



               if(item.getq112p2a().equals("1"))
               {
                   chkq112p2a.setChecked(true);
               }
               else if(item.getq112p2a().equals("2"))
               {
                   chkq112p2a.setChecked(false);
               }

               if(item.getq112p2b().equals("1"))
               {
                   chkq112p2b.setChecked(true);
               }
               else if(item.getq112p2b().equals("2"))
               {
                   chkq112p2b.setChecked(false);
               }
               if(item.getq112p2c().equals("1"))
               {
                   chkq112p2c.setChecked(true);
               }
               else if(item.getq112p2c().equals("2"))
               {
                   chkq112p2c.setChecked(false);
               }
               if(item.getq112p2d().equals("1"))
               {
                   chkq112p2d.setChecked(true);
               }
               else if(item.getq112p2d().equals("2"))
               {
                   chkq112p2d.setChecked(false);
               }
               if(item.getq112p2e().equals("1"))
               {
                   chkq112p2e.setChecked(true);
               }
               else if(item.getq112p2e().equals("2"))
               {
                   chkq112p2e.setChecked(false);
               }
               if(item.getq112p2x().equals("1"))
               {
                   chkq112p2x.setChecked(true);
               }
               else if(item.getq112p2x().equals("2"))
               {
                   chkq112p2x.setChecked(false);
               }
               txtq112p2x1.setText(item.getq112p2x1());

               if(item.getq112p3a().equals("1"))
               {
                   chkq112p3a.setChecked(true);
               }
               else if(item.getq112p3a().equals("2"))
               {
                   chkq112p3a.setChecked(false);
               }

               if(item.getq112p3b().equals("1"))
               {
                   chkq112p3b.setChecked(true);
               }
               else if(item.getq112p3b().equals("2"))
               {
                   chkq112p3b.setChecked(false);
               }
               if(item.getq112p3c().equals("1"))
               {
                   chkq112p3c.setChecked(true);
               }
               else if(item.getq112p3c().equals("2"))
               {
                   chkq112p3c.setChecked(false);
               }
               if(item.getq112p3d().equals("1"))
               {
                   chkq112p3d.setChecked(true);
               }
               else if(item.getq112p3d().equals("2"))
               {
                   chkq112p3d.setChecked(false);
               }
               if(item.getq112p3e().equals("1"))
               {
                   chkq112p3e.setChecked(true);
               }
               else if(item.getq112p3e().equals("2"))
               {
                   chkq112p3e.setChecked(false);
               }
               if(item.getq112p3x().equals("1"))
               {
                   chkq112p3x.setChecked(true);
               }
               else if(item.getq112p3x().equals("2"))
               {
                   chkq112p3x.setChecked(false);
               }
               txtq112p3x1.setText(item.getq112p3x1());

               if(item.getq112m1a().equals("1"))
               {
                   chkq112m1a.setChecked(true);
               }
               else if(item.getq112m1a().equals("2"))
               {
                   chkq112m1a.setChecked(false);
               }

               if(item.getq112m1b().equals("1"))
               {
                   chkq112m1b.setChecked(true);
               }
               else if(item.getq112m1b().equals("2"))
               {
                   chkq112m1b.setChecked(false);
               }
               if(item.getq112m1c().equals("1"))
               {
                   chkq112m1c.setChecked(true);
               }
               else if(item.getq112m1c().equals("2"))
               {
                   chkq112m1c.setChecked(false);
               }
               if(item.getq112m1d().equals("1"))
               {
                   chkq112m1d.setChecked(true);
               }
               else if(item.getq112m1d().equals("2"))
               {
                   chkq112m1d.setChecked(false);
               }
               if(item.getq112m1e().equals("1"))
               {
                   chkq112m1e.setChecked(true);
               }
               else if(item.getq112m1e().equals("2"))
               {
                   chkq112m1e.setChecked(false);
               }
               if(item.getq112m1x().equals("1"))
               {
                   chkq112m1x.setChecked(true);
               }
               else if(item.getq112m1x().equals("2"))
               {
                   chkq112m1x.setChecked(false);
               }
               txtq112m1x1.setText(item.getq112m1x1());

              // txtq112l3.setText(item.getq112l3());
              /* txtq113a1.setText(item.getq113a1());
               txtq113a2.setText(item.getq113a2());
               txtq113b1.setText(item.getq113b1());
               txtq113b2.setText(item.getq113b2());
               txtq113c1.setText(item.getq113c1());
               txtq113c2.setText(item.getq113c2());
               txtq113d1.setText(item.getq113d1());
               txtq113d2.setText(item.getq113d2());*/

           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Section_1_Manager_Service.this, e.getMessage());
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
      // case TIME_DIALOG:
       //    return new TimePickerDialog(this, timePickerListener, hour, minute,false);
       }
     return null;
 }

 private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      mYear = year; mMonth = monthOfYear+1; mDay = dayOfMonth;
      EditText dtpDate;


              dtpDate = (EditText)findViewById(R.id.dtpinterviewer_date);
             if (VariableID.equals("btnq205"))
              {
                  dtpDate = (EditText)findViewById(R.id.dtpinterviewer_date);
              }
      dtpDate.setText(new StringBuilder()
      .append(Global.Right("00"+mDay,2)).append("/")
      .append(Global.Right("00"+mMonth,2)).append("/")
      .append(mYear));

       /* Vlblq205a.setText("205a. দিন:"+Global.DateDifferenceDays(g.getVDate(),dtpq205.getText().toString())+" (D)");
        Vlblq205b.setText("সপ্তাহ: "+Global.DateDifferenceWeeks(g.getVDate(),dtpq205.getText().toString())+" (W)");
        String Times="";

        if(Integer.valueOf(Global.DateDifferenceDays(g.getVDate(),dtpq205.getText().toString()))<=91)
        {
            Times="1st";
        }

        else  if(Integer.valueOf(Global.DateDifferenceDays(g.getVDate(),dtpq205.getText().toString()))<=182)
        {
            Times="2nd";
        }

        else  if(Integer.valueOf(Global.DateDifferenceDays(g.getVDate(),dtpq205.getText().toString()))<=300)
        {
            Times="3rd";
        }

        else  if(Integer.valueOf(Global.DateDifferenceDays(g.getVDate(),dtpq205.getText().toString()))>300)
        {
            Times="No";
        }

        Vlblq205c.setText("Timester: "+Times);*/

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