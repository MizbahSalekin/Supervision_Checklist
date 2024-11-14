package com.example.mansup;

/**
 * Created by User on 9/29/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import Common.Connection;
import Common.Global;

public class ipc_view_list extends Activity {
    static final int DATE_DIALOG = 1;
    static final int TIME_DIALOG = 2;
    boolean netwoekAvailable = false;
    Location currentLocation;
    double currentLatitude, currentLongitude;
    Location currentLocationNet;
    double currentLatitudeNet, currentLongitudeNet;
    String VariableID;
    Connection C;
    Global g;
   // private String [] Mathod;
    Spinner spnUnion;
    Spinner spnEPICluster;
    Spinner spnEPIWord;
    LinearLayout secRegTypet;
    Spinner spnRegType;

   /* LinearLayout secDeathDT;
    EditText dtpFromDT;
    ImageButton btnFromDT;
    EditText dtpToDT;
    ImageButton btnToDT;*/
    SimpleAdapter dataAdapter;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    RadioButton rdoS77d1;
    RadioButton rdoS77d2;
    RadioGroup rdogrpEDD;
    RadioButton rdoGrp1;
    RadioButton rdoGrp2;
    RadioButton rdoGrp3;
    String StartTime;
    String TDeath;
    TextView txtNameSearch;
   // Spinner spnCategory;
    ListView list;
    int totalCount = 0;
    TextView lblTCount;
    private int hour;
    private int minute;
    static String lavel = "";
    static String Zila = "";
    static String UZila = "";
    static String UN = "";
    static String WD = "";
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            EditText tpTime;

        }
    };
    private int mDay;
    private int mMonth;
    private int mYear;
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;
            EditText dtpDate;

            dtpDate = (EditText) findViewById(R.id.dtpFromDT);

            if (VariableID.equals("btnFromDT")) {
                dtpDate = (EditText) findViewById(R.id.dtpFromDT);
            } else if (VariableID.equals("btnToDT")) {
                dtpDate = (EditText) findViewById(R.id.dtpToDT);
            }

            dtpDate.setText(new StringBuilder()
                    .append(Global.Right("00" + mDay, 2)).append("/")
                    .append(Global.Right("00" + mMonth, 2)).append("/")
                    .append(mYear));


        }
    };
    private String TableNameELCOVisit;

    //Disabled Back/Home key
    //--------------------------------------------------------------------------------------------------
    @Override
    public boolean onKeyDown(int iKeyCode, KeyEvent event) {
        return !(iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME);
    }

    //Top menu
    //--------------------------------------------------------------------------------------------------
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnuclose, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder adb = new AlertDialog.Builder(ipc_view_list.this);
        switch (item.getItemId()) {
            case R.id.menuClose:
                adb.setTitle("Close");
                adb.setMessage("আপনি কি এই ফর্ম থেকে বের হতে চান?");
                adb.setNegativeButton("না", null);
                adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                adb.show();
                return true;

        }
        return false;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.ipc_reg_view_list);
            C = new Connection(this);
            g = Global.getInstance();
            StartTime = g.CurrentTime24();
            lavel= C.ReturnSingleValue("select ifnull(level_id,'0') as level_id from providerdb");
            Zila = C.ReturnSingleValue("select ifnull(zillaid,'0') as zillaid from providerdb");
            UZila = C.ReturnSingleValue("select ifnull(upazilaid,'0') as upazilaid from providerdb");
            UN = C.ReturnSingleValue("select ifnull(unionid,'0') as unionid from providerdb");
            WD = C.ReturnSingleValue("select ifnull(ward,'0') as ward from providerdb");

            ((ImageButton) findViewById(R.id.cmdBack)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setlevel(lavel);
                    g.setDistrict(Zila);
                    g.setUpazila(UZila);
                    g.setUnion(UN);
                    g.setward(WD);
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
                    g.setUnion(UN);
                    g.setward(WD);
                    finish();
                    Intent f1 = new Intent(getApplicationContext(), manager_home.class);
                    startActivity(f1);
                }
            });



             spnUnion= ((Spinner) findViewById(R.id.spnUnion));
             spnUnion.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as unions union select unionid ||'- '||unionname as unions from unions where  zillaid='" + g.getDistrict()+ "' and upazilaid='" +g.getUpazila() + "'"));

            spnEPIWord= ((Spinner) findViewById(R.id.spnEPIWord));
           // spnEPIWord.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS word UNION SELECT ward_no||'-'||'ওয়ার্ড' AS word FROM [cluster]  where  zillaid='" + g.getDistrict()+ "' and upazilaid='" +g.getUpazila()+ "' and unionid='" + g.getUnion()+ "'"));
            spnEPIWord.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS word UNION SELECT ward_no||'-'||'ওয়ার্ড' AS word FROM [cluster]  where  zillaid='" + g.getDistrict()+ "' and upazilaid='" +g.getUpazila()+ "'"));
            spnEPICluster= ((Spinner) findViewById(R.id.spnEPICluster));
           // spnEPICluster.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS [cluster] UNION SELECT clusterid || '- ' ||epi_sub_block||': '|| epi_cluster_name AS cluster_name FROM [cluster]  where  zillaid='" + g.getDistrict() + "' and upazilaid='" + g.getUpazila() + "' and unionid='" + g.getUnion()  + "'"));

            spnEPIWord.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String[] ward = spnEPIWord.getSelectedItem().toString().split("-");
                String[] Union = spnUnion.getSelectedItem().toString().split("-");
                    if (spnEPIWord.getSelectedItemPosition() != 0) {

                        spnEPICluster.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS [cluster] UNION SELECT clusterid || '- ' ||epi_sub_block||': '|| epi_cluster_name AS cluster_name FROM [cluster]  where  zillaid='" + g.getDistrict() + "' and upazilaid='" + g.getUpazila() + "' and unionid='" + Union[0] + "' and ward_no='" + ward[0] + "'"));

                        //spnEPICluster.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS [cluster] UNION SELECT clusterid || '- ' ||epi_sub_block||': '|| epi_cluster_name AS cluster_name FROM [cluster]  where  zillaid='" + g.getDistrict() + "' and upazilaid='" + g.getUpazila() + "' and unionid='" + g.getUnion() + "' and ward_no='" + ward[0] + "'"));
                    }
                    // spnq106.setAdapter(C.getArrayAdapterMultiline("SELECT '---সিলেক্ট করুন--' AS [cluster] UNION SELECT clusterid || '- ' ||epi_sub_block||': '|| epi_cluster_name AS cluster_name FROM [cluster]  where  zillaid='" + zid[0] + "' and upazilaid='" + upid[0]+ "' and unionid='" + unid[0] + "' and ward_no='" + ward[0]  + "'"));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });


            secRegTypet=(LinearLayout) findViewById(R.id.secRegTypet);
            spnRegType=(Spinner) findViewById(R.id.spnRegType);
            //spnRegType.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as codelist union select code||'- '||cname as clinic from codelist where  typename='man'"));
            spnRegType.setAdapter(C.getArrayAdapterMultiline(" Select '---সিলেক্ট করুন--' as codelist union select code||'- '||cname as clinic from codelist where  typename='man' and code='" +g.getProvType() + "'"));
            spnEPICluster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";

                    try {

                        //String EPIWord = ((Spinner) findViewById(R.id.spnEPIWord)).getSelectedItemPosition() == 0 ? "" : Global.Left(((Spinner) findViewById(R.id.spnEPIWord)).getSelectedItem().toString(), 1);
                        // String EPICluster = ((Spinner) findViewById(R.id.spnEPICluster)).getSelectedItemPosition() == 0 ? "" : Global.Left(((Spinner) findViewById(R.id.spnEPICluster)).getSelectedItem().toString(), 1);
                        // String RegType = ((Spinner) findViewById(R.id.spnRegType)).getSelectedItemPosition() == 0 ? "" : Global.Left(((Spinner) findViewById(R.id.spnRegType)).getSelectedItem().toString(), 1);
                         String[] EPIWord = spnEPIWord.getSelectedItem().toString().split("-");
                         String[] cluster = spnEPICluster.getSelectedItem().toString().split("-");
                         String[] RegType = spnRegType.getSelectedItem().toString().split("-");
                         DataSearchRegList(list,"",EPIWord[0],cluster[0],RegType[0]);

                    } catch (Exception e) {

                    }


                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });



            spnRegType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";

                    try {
                        String[] EPIWord = spnEPIWord.getSelectedItem().toString().split("-");
                        String[] cluster = spnEPICluster.getSelectedItem().toString().split("-");
                        String[] RegType = spnRegType.getSelectedItem().toString().split("-");
                        DataSearchRegList(list,"",EPIWord[0],cluster[0],RegType[0]);

                    } catch (Exception e) {

                    }


                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });

            txtNameSearch = ((EditText) findViewById(R.id.txtNameSearch));
            txtNameSearch.setHint("অনুসন্ধান করুন: IDNO. নম্বর/নাম/তারিখ:DD/MM/YYYY দিয়ে");
            list = (ListView) findViewById(R.id.lstData);
            list.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Global.hideSoftKeyboard(ipc_view_list.this);
                    return false;
                }
            });

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                    view.setSelected(true);
                }
            });

/*            String Status = ((Spinner) findViewById(R.id.spnEPICluster)).getSelectedItemPosition() == 0 ? "" : Global.Left(((Spinner) findViewById(R.id.spnEPICluster)).getSelectedItem().toString(), 1);
            String VStatus = ((Spinner) findViewById(R.id.spnEPIWord)).getSelectedItemPosition() == 0 ? "" : Global.Left(((Spinner) findViewById(R.id.spnEPIWord)).getSelectedItem().toString(), 1);
            String Methodrecipient = ((Spinner) findViewById(R.id.spnRegType)).getSelectedItemPosition() == 0 ? "" : Global.Left(((Spinner) findViewById(R.id.spnRegType)).getSelectedItem().toString(), 1);

            DataSearchRegList(list,Status,VStatus,Methodrecipient);*/



            lblTCount = (TextView) findViewById(R.id.lblTCount);
            //lblTCount.setText(":" + String.valueOf(totalCount));

           // secRegTypet.setVisibility(View.GONE);
           /* spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String spnData = "";
                    if (spnStatus.getSelectedItem().toString().length() != 0)
                    {
                        spnData = Connection.SelectedSpinnerValue(spnStatus.getSelectedItem().toString(), "-");
                    }

                    if(spnData.equalsIgnoreCase("1"))
                    {
                        secMethodrecipient.setVisibility(View.GONE);
                        spnMethodrecipient.setSelection(0);



                    }
                    else if(spnData.equalsIgnoreCase("2"))
                    {
                        secMethodrecipient.setVisibility(View.VISIBLE);
                    }

                    else if (spnData.equalsIgnoreCase(""))
                    {
                        secMethodrecipient.setVisibility(View.GONE);
                        spnMethodrecipient.setSelection(0);

                    }
                   // DataSearchRegList(list,Status,VStatus,spnData);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });*/

            txtNameSearch.addTextChangedListener(new TextWatcher() {

                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (!s.equals("")) {



                        try {
                            String[] EPIWord = spnEPIWord.getSelectedItem().toString().split("-");
                            String[] cluster = spnEPICluster.getSelectedItem().toString().split("-");
                            String[] RegType = spnRegType.getSelectedItem().toString().split("-");
                            DataSearchRegList(list,"",EPIWord[0],cluster[0],RegType[0]);

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

           // String[] EPIWord = spnEPIWord.getSelectedItem().toString().split("-");
           // String[] cluster = spnEPICluster.getSelectedItem().toString().split("-");
           // String[] RegType = spnRegType.getSelectedItem().toString().split("-");
            DataSearchRegList(list,"","","","");

        } catch (Exception e) {
            Connection.MessageBox(ipc_view_list.this, e.getMessage());
            return;
        }
    }

  /*
    @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        } else {
            //DataSearch(list, Global.Left(spnCategory.getSelectedItem().toString(), 2));
            String Mathod = ((Spinner) findViewById(R.id.txtMethods)).getSelectedItemPosition() == 0 ? "" : Global.Left(((Spinner) findViewById(R.id.txtMethods)).getSelectedItem().toString(), 2);

           // DataSearchElcoList(list,Mathod);
        }
    }
    */

   private void DataSearchRegList(ListView list,String Un,String EPIWord,String EPICluster,String RegType) {
        try {



                String SQL = "";
                if(EPIWord.equalsIgnoreCase(""))
                {
                   EPIWord="AND (ri.q105 LIKE '%%' OR ri.q105 is null)";


                 }

                 else
                 {

                    EPIWord=" and ri.q105='" + EPIWord+ "'";



                 }

            if(EPICluster.equalsIgnoreCase(""))
            {
                EPICluster="AND (ri.q106 LIKE '%%' OR ri.q106 is null)";


            }

            else
            {

                EPICluster=" and ri.q106='" + EPICluster+ "'";



            }

            if(RegType.equalsIgnoreCase(""))
            {
                RegType="AND (ri.q107 LIKE '%%' OR ri.q107 is null)";


            }

            else
            {

                RegType=" and ri.q107='" + RegType+ "'";



            }

                SQL = "SELECT ifnull(ri.zillaid,'') as zillaid,ifnull(ri.upazilaid,'') as upazilaid,ifnull(ri.unionid,'') as unionid,ifnull(ri.slno,'') AS rslno,ifnull(ri.idno,'') as idno,ifnull(ri.interviewer_date,'') as interviewer_date,ifnull(ri.q105,'') as q105,ifnull(ri.q106,'') as q106,ifnull(ri.q107,'') as q107,ifnull(ri.upload,'') as upload,ifnull(z.zillanameeng,'') as zillanameeng,ifnull(z.zillaname,'') as zillaname,ifnull(up.upazilanameeng,'') as upazilanameeng,ifnull(up.upazilaname,'') as upazilaname,ifnull(u.unionnameeng,'') as unionnameeng,ifnull(u.unionname,'') as unionname,ifnull(c.epi_cluster_name,'') as epi_cluster_name,ifnull(c.clusterid,'') as clusterid,ifnull(c.epi_sub_block,'') as epi_sub_block,ifnull(c.ward_no,'') as ward_no,ifnull(gp.lat,'') as lat,ifnull(gp.lon,'') as lon,ifnull(iss.q111a,'') as q111a ";
                //SQL = "SELECT ifnull(ri.zillaid,'') as zillaid,ifnull(ri.upazilaid,'') as upazilaid,ifnull(ri.unionid,'') as unionid,ifnull(ri.slno,'') AS rslno,ifnull(ri.idno,'') as idno,ifnull(ri.q108,'') as q108,ifnull(ri.q105,'') as q105,ifnull(ri.q106,'') as q106,ifnull(ri.q107,'') as q107,ifnull(ri.q109,'') as q109,ifnull(ri.upload,'') as upload,ifnull(z.zillanameeng,'') as zillanameeng,ifnull(z.zillaname,'') as zillaname,ifnull(up.upazilanameeng,'') as upazilanameeng,ifnull(up.upazilaname,'') as upazilaname,ifnull(u.unionnameeng,'') as unionnameeng,ifnull(u.unionname,'') as unionname,ifnull(c.epi_cluster_name,'') as epi_cluster_name,ifnull(c.clusterid,'') as clusterid,ifnull(c.epi_sub_block,'') as epi_sub_block,ifnull(c.ward_no,'') as ward_no,ifnull(vi.q201,'') as q201,ifnull(vi.q203,'') as q203,ifnull(vi.q206a,'') as q206a,ifnull(vi.q206b,'') as q206b,ifnull(vi.q206c,'') as q206c,ifnull(vi.q208,'0') as q208,ifnull(vi.q209,'0') as q209,ifnull(vi.q210,'0') as q210,ifnull(vi.q211,'0') as q211 ";
                SQL += " FROM section_1_identifications_ipc_reg ri";
                SQL += " INNER JOIN zilla z  ON z.ZILLAID = ri.zillaid";
                SQL += " INNER JOIN upazila up  ON up.ZILLAID = ri.zillaid and up.upazilaid=ri.upazilaid";
                SQL += " LEFT OUTER JOIN unions u  ON u.ZILLAID = ri.zillaid and u.upazilaid=ri.upazilaid and u.unionid=ri.unionid";
                 //SQL += " left outer join  section_1_ha_service hs on  hs.idno= ri.idno";
              //  SQL += " left outer join  section_1_supervisor_service ss on  ss.idno= ri.idno";
                SQL += " left outer join  section_1_manager_staff_service iss on  iss.idno= ri.idno";
                SQL += " LEFT OUTER JOIN [cluster] c  ON c.zillaid = ri.zillaid and c.upazilaid=ri.upazilaid and c.unionid=ri.unionid and c.ward_no=ri.q105 and c.clusterid=ri.q106";
                SQL += " left outer join  gps_info gp on  gp.idno= ri.idno";
                SQL += " Where (ri.idno LIKE '%" + ((EditText) findViewById(R.id.txtNameSearch)).getText().toString().trim() +
                        "%' OR strftime('%d/%m/%Y',ri.interviewer_date) like '%" + ((EditText) findViewById(R.id.txtNameSearch)).getText().toString().trim() + "%' OR " +
                         " ri.q105 like '%" + ((EditText) findViewById(R.id.txtNameSearch)).getText().toString().trim() + "%'  OR " +
                        " ri.q106 like '%" + ((EditText) findViewById(R.id.txtNameSearch)).getText().toString().trim() + "%'  OR " +
                        " ri.q107 like '%(" + ((EditText) findViewById(R.id.txtNameSearch)).getText().toString().trim() + "%')";
                SQL += EPIWord;
                SQL += EPICluster;
                SQL += RegType;
               // SQL += EPICluster + " order by ri.interviewer_date Desc";
                SQL +=  " order by ri.idno Desc";

                Cursor cur = C.ReadData(SQL);
                cur.moveToFirst();
                dataList = new ArrayList<HashMap<String, String>>();
                int slno = 0;

                list.setAdapter(null);
                while (!cur.isAfterLast()) {
                    slno += 1;
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("slno", Integer.toString(slno));
                    map.put("idno", cur.getString(cur.getColumnIndex("idno")));
                    map.put("zillaid", cur.getString(cur.getColumnIndex("zillaid")));
                    map.put("upazilaid", cur.getString(cur.getColumnIndex("upazilaid")));
                    map.put("unionid", cur.getString(cur.getColumnIndex("unionid")));
                    map.put("upname", cur.getString(cur.getColumnIndex("upazilaname")));
                    map.put("uname", cur.getString(cur.getColumnIndex("unionname")));
                    map.put("vdate", cur.getString(cur.getColumnIndex("interviewer_date")));
                    map.put("ward", cur.getString(cur.getColumnIndex("ward_no")));
                    map.put("cluster_name", cur.getString(cur.getColumnIndex("epi_cluster_name")));
                    map.put("epi_sub", cur.getString(cur.getColumnIndex("epi_sub_block")));
                    map.put("lat", cur.getString(cur.getColumnIndex("lat")));
                    map.put("lon", cur.getString(cur.getColumnIndex("lon")));
                    if (cur.getString(cur.getColumnIndex("upload")).equalsIgnoreCase("1")) {
                        map.put("upload", "Yes");
                    } else if (cur.getString(cur.getColumnIndex("upload")).equalsIgnoreCase("2")) {
                        map.put("upload", "No");
                    }
                    //map.put("stype", cur.getString(cur.getColumnIndex("q107")));

                    //gps

                /*    if (cur.getString(cur.getColumnIndex("q107")).equalsIgnoreCase("1")) {
                        map.put("q107", "স্বাস্থ্য কর্মী");
                    } else if (cur.getString(cur.getColumnIndex("q107")).equalsIgnoreCase("2")) {
                        map.put("q107", "সুপারভাইজার");
                    }
                    else if (cur.getString(cur.getColumnIndex("q107")).equalsIgnoreCase("3")) {
                        map.put("q107", "আইসিডিডিআর,বি স্টাফ ");
                    }
*/


                    dataList.add(map);

                    dataAdapter = new SimpleAdapter(ipc_view_list.this, dataList, R.layout.ha_reg_view_listrow, new String[]{"SNo", "EntryDate"},
                            new int[]{R.id.Idno});

                    list.setAdapter(new DataListAdapter(this));

                    cur.moveToNext();
                    totalCount += 1;
                }


            cur.close();
            ((TextView) findViewById(R.id.lblTCount)).setText(String.valueOf(slno));
        } catch (Exception e) {
            Connection.MessageBox(ipc_view_list.this, e.getMessage());
            return;
        }
    }




    protected Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        Integer Y = g.mYear;
        Integer M = g.mMonth;
        Integer D = g.mDay;

        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mDateSetListener, Y, M - 1, D);
            case TIME_DIALOG:
                return new TimePickerDialog(this, timePickerListener, hour, minute, false);
        }
        return null;
    }

    public class DataListAdapter extends BaseAdapter {
        private Context context;

        public DataListAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return dataAdapter.getCount();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                //  convertView = inflater.inflate(R.layout.eddcompleterow, null);
                convertView = inflater.inflate(R.layout.ha_reg_view_listrow, null);
            }
            final TextView Slno = (TextView) convertView.findViewById(R.id.slno);
            final LinearLayout memtab = (LinearLayout) convertView.findViewById(R.id.memtab);
            final TextView Idno = (TextView) convertView.findViewById(R.id.Idno);
            final TextView VName = (TextView) convertView.findViewById(R.id.villname);
            final TextView Ward = (TextView) convertView.findViewById(R.id.ward);
            final TextView EPICluster = (TextView) convertView.findViewById(R.id.epicluster_Name);
            final TextView Regtype = (TextView) convertView.findViewById(R.id.regtype);
            final TextView GPs = (TextView) convertView.findViewById(R.id.gps);
          /*  final TextView MobP_No = (TextView) convertView.findViewById(R.id.mobile_no);
            final TextView Name = (TextView) convertView.findViewById(R.id.Name);
            final TextView Hus_Name = (TextView) convertView.findViewById(R.id.Hus_Name);
           */

            final TextView VDate = (TextView) convertView.findViewById(R.id.VDate);

            final TextView Upload = (TextView) convertView.findViewById(R.id.Upload);



            final HashMap<String, String> o = (HashMap<String, String>) dataAdapter.getItem(position);
            Slno.setText(o.get("slno"));
            Idno.setText(o.get("idno"));
            VName.setText(o.get("uname"));
            Ward.setText(o.get("ward"));
            EPICluster.setText(o.get("ward"));
            EPICluster.setText(o.get("cluster_name")+"["+o.get("epi_sub")+"]");
            GPs.setText("Lat:"+o.get("lat")+",Lon:"+o.get("lon")+"");
            Regtype.setText(o.get("q107"));



            VDate.setText(Global.DateConvertDMY(o.get(("vdate"))));
          //  Q101.setText(o.get("q101"));
            Upload.setText(o.get("upload"));



            memtab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                     g.setCallFrom("update");
                     g.setIdNo(o.get("idno"));
                    g.setDistrict(o.get("zillaid"));
                    g.setUpazila(o.get("upazilaid"));
                    g.setUnion(o.get("unionid"));
                    g.setward(o.get("ward"));

                    Intent f1 = new Intent(getApplicationContext(), Section_1_ipc_reg.class);
                    startActivityForResult(f1,1);
                }
            });

            /*btnVisit1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                   // g.setSLno(o.get("sec_4_V1"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });
            btnVisit2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                  //  g.setSLno(o.get("sec_4_V2"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });
            btnVisit3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                  //  g.setSLno(o.get("sec_4_V3"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });
            btnVisit4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                   // g.setSLno(o.get("sec_4_V4"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });
            btnVisit5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                   // g.setSLno(o.get("sec_4_V5"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });

            btnVisit6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                   // g.setSLno(o.get("sec_7_V1"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });
            btnVisit7.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                 //   g.setSLno(o.get("sec_7_V2"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });
            btnVisit8.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                 //   g.setSLno(o.get("sec_7_V3"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });
            btnVisit9.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                  //  g.setSLno(o.get("sec_7_V4"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });
            btnVisit10.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    g.setCallFrom("update");
                    g.setIdNo(o.get("idno"));
                   // g.setSLno(o.get("sec_7_V5"));
                    Intent f1 = new Intent(getApplicationContext(), Section_1_identifications.class);
                    startActivityForResult(f1,1);
                }
            });*/


            final AlertDialog.Builder adb = new AlertDialog.Builder(ipc_view_list.this);
            return convertView;
        }
    }
}