package Common;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//import com.example.pwc.R;
import com.example.mansup.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import DataSync.DataClass;
import DataSync.DataClassProperty;
import DataSync.DownloadJSONData;
import DataSync.DownloadLMISJSONData;
import DataSync.DownloadRequestClass;
import DataSync.UploadJSONData;
import DataSync.UploadLMISJSONData;
import DataSync.downloadClass;
//com.example..pwc


///update from Nisan
//--------------------------------------------------------------------------------------------------
// Created by NisanHossain on 17/03/2015.
//--------------------------------------------------------------------------------------------------

public class Connection extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DB_NAME = Global.DatabaseName;
    private static final String DBLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Global.DatabaseFolder + File.separator + DB_NAME;

    // Todo table name
    private static final String TABLE_TODO = "todo_items";

    public Context dbContext;
    public static Context ud_context;
    public Connection(Context context) {
        super(context, DBLocation, null, DATABASE_VERSION);
        dbContext = context;
        ud_context=context;
        try {


        } catch (Exception ex) {

        }
    }

    //Split function
    //----------------------------------------------------------------------------------------------
    public static String[] split(String s, char separator) {
        ArrayList<String> d = new ArrayList<String>();
        for (int ini = 0, end = 0; ini <= s.length(); ini = end + 1) {
            end = s.indexOf(separator, ini);
            if (end == -1) {
                end = s.length();
            }

            String st = s.substring(ini, end).trim();


            if (st.length() > 0) {
                d.add(st);
            } else {
                d.add("");
            }
        }

        String[] temp = new String[d.size()];
        temp = d.toArray(temp);
        return temp;
    }

    //Message Box
    //----------------------------------------------------------------------------------------------
    public static void MessageBox(Context ClassName, String Msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ClassName);
        builder
                .setMessage(Msg)
                .setTitle("মেসেজ")
                .setCancelable(true)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ok", null);
        builder.show();

    }

    //Check whether internet connectivity available or not
    //----------------------------------------------------------------------------------------------
    public static boolean haveNetworkConnection(Context con) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected())
                        haveConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected())
                        haveConnectedMobile = true;
            }
        } catch (Exception e) {

        }
        return haveConnectedWifi || haveConnectedMobile;
    }



    // Creating our initial tablesSave("delete from delivery");
    // These is where we need to write create table statements.
    // This is called when database is created.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("Create Table abc(sid varchar(10))");
    }


    // Upgrading the database between versions
    // This method is called when database is upgraded like modifying the table structure,
    // adding constraints to database, etc
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 1) {
            // Wipe older tables if existed
            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
            // Create tables again
            onCreate(db);
        }
    }

    //Check the existence of database table
    //----------------------------------------------------------------------------------------------
    public boolean TableExists(String TableName) {
        Cursor c = null;
        boolean tableExists = false;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            c = db.rawQuery("Select * from " + TableName, null);
            tableExists = true;
            c.close();
            db.close();
        } catch (Exception e) {
        }
        return tableExists;
    }

    //Create database tables
    //----------------------------------------------------------------------------------------------
    public void CreateTable(String TableName, String SQL) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!TableExists(TableName)) {
            db.execSQL(SQL);
        }
    }

    //Read data from database and return to Cursor variable
    //----------------------------------------------------------------------------------------------
    public Cursor ReadData(String SQL) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(SQL, null);
        return cur;
    }

    //Check existence of data in database
    //----------------------------------------------------------------------------------------------
    public boolean Existence(String SQL) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(SQL, null);
        if (cur.getCount() == 0) {
            cur.close();
            db.close();
            return false;
        } else {
            cur.close();
            db.close();
            return true;
        }
    }

    //Return single result based on the SQL query
    //----------------------------------------------------------------------------------------------
    public String ReturnSingleValue(String SQL) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(SQL, null);
        String retValue = "";
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            retValue = cur.getString(0);
            cur.moveToNext();
        }
        cur.close();
        db.close();
        return retValue;
    }

    //Save/Update/Delete data in to database
    //----------------------------------------------------------------------------------------------
    public void Save(String SQL) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL);
        db.close();
    }

    public String SaveData(String SQL) {
        SQLiteDatabase db = this.getWritableDatabase();
        String resp = "";
        try {
            db.execSQL(SQL);
        }catch (Exception ex1){
            resp=ex1.getMessage();
        }
        finally {
            db.close();
        }
        return resp;
    }

    public String SaveDataTransaction(String SQL) {
        SQLiteDatabase database = this.getWritableDatabase();
        String resp = "";
        try {
            database.execSQL("PRAGMA synchronous=OFF");
            database.setLockingEnabled(false);
            database.beginTransaction();
            database.execSQL(SQL);
            database.setTransactionSuccessful();
        }catch (Exception ex1){
            resp=ex1.getMessage();
        }
        finally {
            database.endTransaction();
            database.setLockingEnabled(true);
            database.execSQL("PRAGMA synchronous=NORMAL");
            database.close();
        }
        return resp;
    }

    //Generate data list
    //----------------------------------------------------------------------------------------------
    public List<String> getDataList(String SQL) {
        List<String> data = new ArrayList<String>();
        Cursor cursor = ReadData(SQL);
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return data;
    }

    //Array adapter for spinner item
    //----------------------------------------------------------------------------------------------
    public ArrayAdapter<String> getArrayAdapter(String SQL) {
        List<String> dataList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        if (cursor.moveToFirst()) {
            do {
                dataList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.dbContext,
                R.layout.multiline_spinner_dropdown_item, dataList);

        return dataAdapter;
    }

    public ArrayAdapter<String> getArrayAdapterMultiline(String SQL) {
        List<String> dataList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        if (cursor.moveToFirst()) {
            do {
                dataList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.dbContext,
                R.layout.multiline_spinner_dropdown_item, dataList);
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter.createFromResource(this.dbContext, dataList,
        //   R.layout.multiline_spinner_dropdown_item);
        return dataAdapter;
    }


    // Download data from Database Server
    //----------------------------------------------------------------------------------------------
    /*public String DownloadData(String SQLStr, String TableName,String ColumnList, String UniqueField)
    {
        String rep = "";
        String SQL = "";

        int totalDownload=0;
        String DownloadStatus="";
        String WhereClause="";
        int varPos=0;

        try
        {
            DownloadData d=new DownloadData();
            d.Method_Name="DownloadData";
            d.SQLStr=SQLStr;

            String DataArray[]=null;
            DataArray=d.execute("").get();

            String UField[]  = UniqueField.split(",");
            String VarList[] = ColumnList.split(",");

            for(int i=0;i<DataArray.length;i++)
            {
                String VarData[] = split(DataArray[i],'^');

                //Generate where clause
                SQL="";
                WhereClause="";
                varPos=0;
                for(int j=0; j< UField.length; j++)
                {
                    varPos = VarPosition(UField[j].toString(),VarList);
                    if(j==0)
                    {
                        WhereClause = UField[j].toString()+"="+ "'"+ VarData[varPos].toString() +"'";
                    }
                    else
                    {
                        WhereClause += " and "+ UField[j].toString()+"="+ "'"+ VarData[varPos].toString() +"'";
                    }
                }

                //Update command
                if(Existence("Select "+ VarList[0] +" from "+ TableName +" Where "+ WhereClause))
                {
                    for(int r=0;r<VarList.length;r++)
                    {
                        if(r==0)
                        {
                            SQL = "Update "+ TableName +" Set ";
                            SQL+= VarList[r] + " = '"+ VarData[r].toString() +"'";
                        }
                        else
                        {
                            if(r == VarData.length-1)
                            {
                                SQL+= ","+ VarList[r] + " = '"+ VarData[r].toString() +"'";
                                SQL += " Where "+ WhereClause;
                            }
                            else
                            {
                                SQL+= ","+ VarList[r] + " = '"+ VarData[r].toString() +"'";
                            }
                        }
                    }

                    Save(SQL);
                    totalDownload+=1;
                }
                //Insert command
                else
                {
                    for(int r=0;r<VarList.length;r++)
                    {
                        if(r==0)
                        {
                            SQL = "Insert into "+ TableName +"("+ ColumnList +")Values(";
                            SQL+= "'"+ VarData[r].toString() +"'";
                        }
                        else
                        {
                            SQL+= ",'"+ VarData[r].toString() +"'";
                        }
                    }
                    SQL += ")";

                    Save(SQL);
                    totalDownload+=1;
                }

                //update download status on server
                //rep = ExecuteCommandOnServer("Update "+ TableName +" set Download='1', DownloadDt='"+ Global.DateTimeNowYMDHMS() +"' Where "+ WhereClause);
            }

            DownloadStatus="Total download completed: "+ totalDownload +" of "+ DataArray.length;

            return DownloadStatus;
        }
        catch(Exception e)
        {
            return "Download Error:"+ e.getMessage();
        }
    }*/


    // Data Update
    //----------------------------------------------------------------------------------------------
   /* public String DataUpdate(String SQLStr, String TableName,String ColumnList, String UniqueField)
    {
        String rep = "";
        String SQL = "";

        int totalDownload=0;
        String DownloadStatus="";
        String WhereClause="";
        int varPos=0;

        try
        {
            DownloadData d=new DownloadData();
            d.Method_Name="DownloadData";
            d.SQLStr=SQLStr;

            String DataArray[]=null;
            DataArray=d.execute("").get();

            String UField[]  = UniqueField.split(",");
            String VarList[] = ColumnList.split(",");

            for(int i=0;i<DataArray.length;i++)
            {
                String VarData[] = split(DataArray[i],'^');

                //Generate where clause
                SQL="";
                WhereClause="";
                varPos=0;
                for(int j=0; j< UField.length; j++)
                {
                    varPos = VarPosition(UField[j].toString(),VarList);
                    if(j==0)
                    {
                        WhereClause = UField[j].toString()+"="+ "'"+ VarData[varPos].toString() +"'";
                    }
                    else
                    {
                        WhereClause += " and "+ UField[j].toString()+"="+ "'"+ VarData[varPos].toString() +"'";
                    }
                }

                //Update command
                if(Existence("Select "+ VarList[0] +" from "+ TableName +" Where "+ WhereClause))
                {
                    for(int r=0;r<VarList.length;r++)
                    {
                        if(r==0)
                        {
                            SQL = "Update "+ TableName +" Set ";
                            SQL+= VarList[r] + " = '"+ VarData[r].toString() +"'";
                        }
                        else
                        {
                            if(r == VarData.length-1)
                            {
                                SQL+= ","+ VarList[r] + " = '"+ VarData[r].toString() +"'";
                                SQL += " Where "+ WhereClause;
                            }
                            else
                            {
                                SQL+= ","+ VarList[r] + " = '"+ VarData[r].toString() +"'";
                            }
                        }
                    }

                    Save(SQL);
                    totalDownload+=1;
                }
                //Insert command
                else
                {
                    for(int r=0;r<VarList.length;r++)
                    {
                        if(r==0)
                        {
                            SQL = "Insert into "+ TableName +"("+ ColumnList +")Values(";
                            SQL+= "'"+ VarData[r].toString() +"'";
                        }
                        else
                        {
                            SQL+= ",'"+ VarData[r].toString() +"'";
                        }
                    }
                    SQL += ")";

                    Save(SQL);
                    totalDownload+=1;
                }

                //update download status on server
                rep = ExecuteCommandOnServer("Update "+ TableName +" set Upload='2' Where "+ WhereClause);
            }

            DownloadStatus="Total download completed: "+ totalDownload +" of "+ DataArray.length;

            return DownloadStatus;
        }
        catch(Exception e)
        {
            return "Download Error:"+ e.getMessage();
        }
   }
*/

    //Execute command on Database Server
    //----------------------------------------------------------------------------------------------
    /*public String ExecuteCommandOnServer(String SQLList)
    {
        ExecuteCommandClass dr = new ExecuteCommandClass();
        dr.setmethodname("ExecuteSQLList");
        dr.setSQL(SQLList);
        dr.setSecutiryCodeL("org.postgresql");
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        DownloadJSONData dload = new DownloadJSONData();
        String response = null;
        try {
            response = dload.execute(json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
      /*  String response="";
        String result="";
        ExecuteCommandClass e=new ExecuteCommand();

        try {
            response = e.execute(SQLStr).get();
            if(response.equals("done"))
            {
                result = "done";
            }
            else
            {
                result = "not";
            }
        }
        catch (Exception e1){
            result = "not";
        }

        return result;*/
    //}

    // Data Upload to Database Server
    //----------------------------------------------------------------------------------------------
    /*public String UploadData(String[] DataArray,String TableName,String ColumnList,String UniqueFields)
    {
        String[] D=new String[DataArray.length];
        String[] Col=ColumnList.split(",");
        String VarName[]=UniqueFields.split(",");
        int varPos=0;
        String WhereClause="";

        String response="";
        int totalRec=0;
        for(int i=0;i<DataArray.length;i++)
        {
            //Generate Where Clause
            String VarData[]=DataArray[i].toString().split("\\^");
            varPos=0;
            WhereClause="";

            for(int j=0; j< VarName.length; j++)
            {
                varPos=VarPosition(VarName[j].toString(),Col);
                if(j==0)
                {
                    WhereClause = VarName[j].toString()+"="+ "'"+ VarData[varPos].toString() +"'";
                }
                else
                {
                    WhereClause += " and "+VarName[j].toString()+"="+ "'"+ VarData[varPos].toString() +"'";
                }
            }

            //Calling web service through class: UploadData
            UploadData u = new UploadData();
            u.TableName            = TableName;
            u.ColumnList        = ColumnList;
            u.UniqueFieldWithData = WhereClause;
            try{
                response=u.execute(DataArray[i]).get();
                if(response.equalsIgnoreCase("done"))
                {
                    Save("Update " + TableName + " Set Upload='1' where " + WhereClause);
                    totalRec+=1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Integer.toString(totalRec);
    }
*/
    //Find the variable positions in an array list
    //----------------------------------------------------------------------------------------------
    public int VarPosition(String VariableName, String[] ColumnList) {
        int pos = 0;
        for (int i = 0; i < ColumnList.length; i++) {
            if (VariableName.trim().equalsIgnoreCase(ColumnList[i].toString().trim())) {
                pos = i;
                i = ColumnList.length;
            }
        }
        return pos;
    }


    // Getting array list for Upload with ^ separator from Cursor
    //----------------------------------------------------------------------------------------------
    public String[] GenerateArrayList(String VariableList, String TableName) {
        Cursor cur_H;
        // cur_H = ReadData("Select "+ VariableList +" from "+ TableName);
        cur_H = ReadData("Select " + VariableList + " from " + TableName + " where upload='2'");
        cur_H.moveToFirst();
        String[] Data = new String[cur_H.getCount()];
        String DataList = "";
        String[] Count = VariableList.toString().split(",");
        int RecordCount = 0;

        while (!cur_H.isAfterLast()) {
            for (int c = 0; c < Count.length; c++) {
                if (c == 0) {
                    if (cur_H.getString(c) == null)
                        DataList = "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList = "";
                    else
                        DataList = cur_H.getString(c).toString();

                } else {
                    if (cur_H.getString(c) == null)
                        DataList += "^" + "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList += "^" + "";
                    else
                        DataList += "^" + cur_H.getString(c).toString();
                }
            }
            Data[RecordCount] = DataList;
            RecordCount += 1;
            cur_H.moveToNext();
        }
        cur_H.close();

        return Data;
    }

    // Getting result from database server based on SQL
    //----------------------------------------------------------------------------------------------
    public String ReturnResult(String MethodName, String SQL) {
        ReturnResult r = new ReturnResult();
        String response = "";
        r.Method_Name = MethodName;
        r.SQLStr = SQL;
        try {
            response = r.execute("").get();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }
        return response;
    }

    public String ReturnSingleValueJSON(String SQL) {
        DownloadRequestClass dr = new DownloadRequestClass();
        dr.setmethodname("ReturnSingleValue");
        dr.setSQL(SQL);
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        //For Web Api
        //--------------------------------------------------------------------------------------
        DownloadJSONData dload = new DownloadJSONData();
        String response = null;
        try {
            response = dload.execute(json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String DataStringJSON(String SQL) {
        DownloadRequestClass dr = new DownloadRequestClass();
        dr.setmethodname("Existence");
        dr.setSQL(SQL);
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        //For Web Api
        //--------------------------------------------------------------------------------------
        DownloadJSONData dload = new DownloadJSONData();
        String response = null;
        try {
            response = dload.execute(json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return response;
    }


    //Generate table in local database
   /* public void DatabaseTableSync(String TableName) {
        String SQLStr = "";
        DownloadData d = new DownloadData();
        d.Method_Name = "DownloadData";
        d.SQLStr = "Select TableName, TableScript from DatabaseTab where TableName='"+ TableName +"'";

        String DataArray[] = null;

        try {
            DataArray = d.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < DataArray.length; i++) {
            String VarData[] = split(DataArray[i], '^');
            CreateTable(VarData[0], VarData[1]);
        }
    }*/


    //Sync data between local and server
/*    public void DatabaseTableDataSync(String[] TableList) {
        String response = "";
        String TableName = "";
        String VariableList ="";
        String UniqueField = "";
        String[] V;

        for(int i=0; i< TableList.length;i++) {
            if(TableList[i].toLowerCase().equals("household"))
                UniqueField = "Div, Dist, Upz, UN, Mouza, Vill,ProvType,ProvCode, HHNo";
            else if(TableList[i].toLowerCase().equals("member"))
                UniqueField = "Dist, Upz, UN, Mouza, Vill, ProvType,ProvCode, HHNo, SNo";
            else if(TableList[i].toLowerCase().equals("visits"))
                UniqueField = "Dist, Upz, UN, Mouza, Vill,ProvType,ProvCode, HHNo, VDate";

            VariableList = GetColumnList(TableName);
            V = GenerateArrayList(VariableList, TableName);
            response = UploadData(V, TableName, VariableList, UniqueField);
        }
    }*/

    public List<String> DataListJSON(String SQL) {
        DownloadRequestClass dr = new DownloadRequestClass();
        dr.setmethodname("DownloadData");
        dr.setSQL(SQL);
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        //For Web Api
        //--------------------------------------------------------------------------------------
        DownloadJSONData dload = new DownloadJSONData();
        String response = null;
        try {
            response = dload.execute(json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<String> data = new ArrayList<String>();
        downloadClass responseData = (downloadClass) gson.fromJson(response, downloadClass.class);
        data = responseData.getdata();
        return data;
    }


    //Rebuild Local Database from Server
//----------------------------------------------------------------------------------------------

   // ,String WD
   public void RebuildDatabase(final String Flavel,final String Dist,final String Upz, String UN,final String WD, final String ProvType,final String ProvCode, ProgressDialog progress, String message, Handler progressHandler, int jumpTime, boolean DownloadOnlyAppropriateRecords)
   {
        Global.getInstance().setProvType(ProvType);
        Global.getInstance().setProvCode(ProvCode);
        jumpTime = 2;
        Global.getInstance().setProgressMessage("(1) প্রয়োজনীয় ডাটাবেস নির্মাণ হচ্ছে");
        progressHandler.sendMessage(progressHandler.obtainMessage());



        //Create Database Tables
        //------------------------------------------------------------------------------------------
       // String SQL = "Select \"tableName\", \"tableScript\" from \"databaseSetting\"";
       // String SQL = "Select tablename, tablescript from databasesetting )";


       //Create Database Tables
       //------------------------------------------------------------------------------------------
       // String SQL = "Select \"tableName\", \"tableScript\" from \"databaseSetting\" where \"downloadType\"='1'";
       String SQL = "Select \"tableName\", \"tableScript\" from \"databaseSetting\" where \"downloadType\"='1'";
       // String SQL = "Select tablename, tablescript from databasesetting )";


        List<String> tableList = new ArrayList<String>();
        tableList = DataListJSON(SQL);

        for (int i = 0; i < tableList.size(); i++) {
            String VarData[] = split(tableList.get(i), '^');
            CreateTable(VarData[0], VarData[1]);
        }

        //------------------------------------------------------------------------------------------
        //Data Sync: Download data from server
        //------------------------------------------------------------------------------------------
        String Res = "";
        String SQLStr = "";

        try {
            final DataSyncManagement DS = new DataSyncManagement(ud_context,Dist,Upz,ProvType,ProvCode,300);

            //Clean Data_Sync_Management into Server
            jumpTime += 1;
            jumpTime += 1;
            message = "(2) প্রয়োজনীয় ডাটাবেস নির্মাণ হচ্ছে";
            Global.getInstance().setProgressMessage(message);
            progressHandler.sendMessage(progressHandler.obtainMessage());
            ExecuteFunctionOnServer("delete from data_sync_management where \"provCode\"='" + ProvCode + "'");

            //DatabaseSetting


            jumpTime += 1;
            jumpTime += 1;
            message = "ডাটাবেস সেটিং ডাউনলোড";
            Global.getInstance().setProgressMessage(message);
            progressHandler.sendMessage(progressHandler.obtainMessage());
            SQLStr="Select \"tableName\", \"tableScript\",\"columnList\",\"uniqueId\",\"batchSizeDown\",\"batchSizeUp\",\"syncType\",\"modifiedDate\" from \"databaseSetting\"";
            Res = DS.DownloadJSON(SQLStr, "databaseSetting", "tableName,tableScript,columnList,uniqueId,batchSizeDown,batchSizeUp,syncType,modifiedDate", "tableName");



            //DeviceNo
            jumpTime += 1;
            jumpTime += 1;
            message = "ডাটাবেস সেটিং ডাউনলোড";
            Global.getInstance().setProgressMessage(message);
            progressHandler.sendMessage(progressHandler.obtainMessage());
            Save("Insert or Replace into DeviceNo(DeviceNo)Values('" + (ProvCode) + "')");

            jumpTime += 1;
            jumpTime += 1;
            Global.getInstance().setProgressMessage("ডিভাইস প্রস্তুত করা হচ্ছে");
            progressHandler.sendMessage(progressHandler.obtainMessage());



            //ProviderDB
            //--------------------------------------------------------------------------------------
            jumpTime += 1;
            jumpTime += 1;
            message = "সেবা প্রদানকারীর তথ্য ডাউনলোড হচ্ছে";
            Global.getInstance().setProgressMessage(message);
            progressHandler.sendMessage(progressHandler.obtainMessage());


            SQLStr = "Select distinct divid,zillaid,upazilaid,unionid,ward,provtype,providerid,provname,mobileno,endate,exdate,active,devicesetting,systemupdatedt,healthidrequest,tablestructurerequest,areaupdate,level_id,supervisorcode,provpass,facilityname,csba,systementrydate,modifydate,uploaddate,1 upload from providerdb where ";//
/*          SQLStr += " zillaid='" + Dist + "' and";
            SQLStr += " upazilaid='" + Upz + "' and";
            SQLStr += " unionid='" + UN + "' and";
            SQLStr += " provtype='" + ProvType + "' and";*/
            SQLStr += " providerid='" + ProvCode + "' and";
            SQLStr += " active='1'";
            Res = DS.DownloadJSON(SQLStr, "providerdb", "divid,zillaid,upazilaid,unionid,ward,provtype,providerid,provname,mobileno,endate,exdate,active,devicesetting,systemupdatedt,healthidrequest,tablestructurerequest,areaupdate,level_id,supervisorcode,provpass,facilityname,csba,systementrydate,modifydate,uploaddate,upload", "providerid");


//Login
            //--------------------------------------------------------------------------------------
            jumpTime += 1;
            jumpTime += 1;
            Global.getInstance().setProgressMessage("সেবা প্রদানকারীর কর্ম এলাকার তথ্য ডাউনলোড হচ্ছে");
            progressHandler.sendMessage(progressHandler.obtainMessage());

            CreateTable("login","Create table login(userid varchar(20),username varchar(50),pass varchar(20),systementrydate timestamp,modifydate timestamp,uploaddate timestamp,upload integer)");

            SQLStr = "Select distinct providerid,provname,provpass,systementrydate,modifydate,uploaddate,1 upload from providerdb Where ";
            /*SQLStr += " zillaid='" + Dist + "' and";
            SQLStr += " upazilaid='" + Upz + "' and";
            SQLStr += " unionid='" + UN + "' and";
            SQLStr += " provtype='" + ProvType + "' and";*/
            SQLStr += " providerid='" + ProvCode + "' and";
            SQLStr += " Active='1'";
            Res = DS.DownloadJSON(SQLStr, "login", "userid,username,pass,systementrydate,modifydate,uploaddate,upload", "userid");

            jumpTime += 1;
            jumpTime += 1;
            Global.getInstance().setProgressMessage("Code list");
            progressHandler.sendMessage(progressHandler.obtainMessage());
            SQLStr = "SELECT typename,code, cname,systementrydate,modifydate,uploaddate,1 upload FROM codelist where typename in('man')";
            Res = DownloadJSON(SQLStr, "codelist", "typename,code, cname,systementrydate,modifydate,uploaddate,upload", "typename, code");


            //Division
            //--------------------------------------------------------------------------------------
            jumpTime += 1;
            jumpTime += 1;
            Global.getInstance().setProgressMessage("বিভাগ ডাউনলোড হচ্ছে");
            progressHandler.sendMessage(progressHandler.obtainMessage());
            SQLStr = "Select id,division,divisioneng,systementrydate,modifydate,uploaddate,1 upload from division";
            Res = DS.DownloadJSON(SQLStr, "division", "id,division,divisioneng,systementrydate,modifydate,uploaddate,upload", "id");


            //District
            //--------------------------------------------------------------------------------------
            jumpTime += 1;
            jumpTime += 1;
            Global.getInstance().setProgressMessage("জেলা তথ্য ডাউনলোড হচ্ছে");
            progressHandler.sendMessage(progressHandler.obtainMessage());
           // SQLStr = "Select divid,zillaid,zillanameeng,zillaname,systementrydate,modifydate,uploaddate,1 upload from zilla where zillaid='" + Dist + "'";
            SQLStr = "Select divid,zillaid,zillanameeng,zillaname,systementrydate,modifydate,uploaddate,1 upload from zilla where zillaid='" + Dist + "'";
            Res = DS.DownloadJSON(SQLStr, "zilla", "divid,zillaid,zillanameeng,zillaname,systementrydate,modifydate,uploaddate,upload", "divid,zillaid");


            jumpTime += 1;
            jumpTime += 1;
            Global.getInstance().setProgressMessage("section_1_identifications_ipc_reg তথ্য ডাউনলোড হচ্ছে");
            progressHandler.sendMessage(progressHandler.obtainMessage());
            // SQLStr = "Select divid,zillaid,zillanameeng,zillaname,systementrydate,modifydate,uploaddate,1 upload from zilla where zillaid='" + Dist + "'";
            SQLStr = "Select zillaid,upazilaid,unionid,union_name,vill_name,slno,idno,q105,q106,q107,interviewer_id,interviewer_date,starttime,endtime,deviceid,entryuser,lat,lon,endt,modifydate,1 upload from section_1_identifications_ipc_reg where entryuser='" + ProvCode + "'";
            Res = DS.DownloadJSON(SQLStr, "section_1_identifications_ipc_reg", "zillaid,upazilaid,unionid,union_name,vill_name,slno,idno,q105,q106,q107,interviewer_id,interviewer_date,starttime,endtime,deviceid,entryuser,lat,lon,endt,modifydate,upload", "idno");


            jumpTime += 1;
            jumpTime += 1;
            Global.getInstance().setProgressMessage("section_1_manager_staff_service তথ্য ডাউনলোড হচ্ছে");
            progressHandler.sendMessage(progressHandler.obtainMessage());
            // SQLStr = "Select divid,zillaid,zillanameeng,zillaname,systementrydate,modifydate,uploaddate,1 upload from zilla where zillaid='" + Dist + "'";
            SQLStr = "Select idno,q111,q111a,q111b,q111c,q111d,q111e,q111x,q111x1,q112a,q112a1,q112a2,q112b,q112b1,q112b2,q112c,q112c1,q112c2,q112d,q112d1,q112d2,q112e,q112e1,q112e2,q112f,q112f1,q112f2,q112g,q112g1,q112g2,q112h,q112h1,q112h2,q112i,q112i1,q112i2,q112j,q112j1,q112j2,q112k,q112k1,q112k2,q112l,q112l1,q112l2,q113,q112p1a,q112p1b,q112p1c,q112p1d,q112p1e,q112p1x,q112p1x1,q112p2a,q112p2b,q112p2c,q112p2d,q112p2e,q112p2x,q112p2x1,q112p3a,q112p3b,q112p3c,q112p3d,q112p3e,q112p3x,q112p3x1,q112m1a,q112m1b,q112m1c,q112m1d,q112m1e,q112m1x,q112m1x1,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,1 upload from section_1_manager_staff_service where entryuser='" + ProvCode + "'";
            Res = DS.DownloadJSON(SQLStr, "section_1_manager_staff_service", "idno,q111,q111a,q111b,q111c,q111d,q111e,q111x,q111x1,q112a,q112a1,q112a2,q112b,q112b1,q112b2,q112c,q112c1,q112c2,q112d,q112d1,q112d2,q112e,q112e1,q112e2,q112f,q112f1,q112f2,q112g,q112g1,q112g2,q112h,q112h1,q112h2,q112i,q112i1,q112i2,q112j,q112j1,q112j2,q112k,q112k1,q112k2,q112l,q112l1,q112l2,q113,q112p1a,q112p1b,q112p1c,q112p1d,q112p1e,q112p1x,q112p1x1,q112p2a,q112p2b,q112p2c,q112p2d,q112p2e,q112p2x,q112p2x1,q112p3a,q112p3b,q112p3c,q112p3d,q112p3e,q112p3x,q112p3x1,q112m1a,q112m1b,q112m1c,q112m1d,q112m1e,q112m1x,q112m1x1,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload", "idno");



            if(Flavel.equalsIgnoreCase("1"))
            {

                //Upazila
                //--------------------------------------------------------------------------------------
                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("উপজেলা তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,1 upload from upazila where zillaid='" + Dist + "' and upazilaid='" + Upz + "'";
                Res = DS.DownloadJSON(SQLStr, "upazila", "zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid");


                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("ইউনিয়ন তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select distinct u.zillaid,u.upazilaid,u.municipalityid,u.unionid,u.unionnameeng,u.unionname,u.systementrydate,u.modifydate,u.uploaddate,1 upload from unions u";
                SQLStr += " where u.zillaid='" + Dist + "' and u.upazilaid='" + Upz + "'";
                Res = DS.DownloadJSON(SQLStr, "unions", "zillaid,upazilaid,municipalityid,unionid,unionnameeng,unionname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid,unionid");

                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("ক্লাস্টার তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select zillaid,district_name,upazilaid,upazila_name,unionid,union_name,ward_no,epi_sub_block,clusterid,epi_cluster_name,year,month_1,month_2,month_3,month_4,month_5,month_6,month_7,month_8,month_9,month_10,month_11,month_12,hh_from,hh_to,fac_type from cluster where zillaid='" + Dist + "' and upazilaid='" + Upz+ "'";
                Res = DS.DownloadJSON(SQLStr, "cluster", "zillaid,district_name,upazilaid,upazila_name,unionid,union_name,ward_no,epi_sub_block,clusterid,epi_cluster_name,year,month_1,month_2,month_3,month_4,month_5,month_6,month_7,month_8,month_9,month_10,month_11,month_12,hh_from,hh_to,fac_type", "zillaid,upazilaid,unionid,clusterid");




            }
            else if(Flavel.equalsIgnoreCase("2"))
            {

                //Upazila
                //--------------------------------------------------------------------------------------
                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("উপজেলা তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,1 upload from upazila where zillaid='" + Dist + "' and upazilaid='" + Upz + "'";
                Res = DS.DownloadJSON(SQLStr, "upazila", "zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid");

                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("ইউনিয়ন তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select distinct u.zillaid,u.upazilaid,u.municipalityid,u.unionid,u.unionnameeng,u.unionname,u.systementrydate,u.modifydate,u.uploaddate,1 upload from unions u";
                SQLStr += " where u.zillaid='" + Dist + "' and u.upazilaid='" + Upz + "' and unionid='" + UN + "'";
                Res = DS.DownloadJSON(SQLStr, "unions", "zillaid,upazilaid,municipalityid,unionid,unionnameeng,unionname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid,unionid");

                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("ক্লাস্টার তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select zillaid,district_name,upazilaid,upazila_name,unionid,union_name,ward_no,epi_sub_block,clusterid,epi_cluster_name,year,month_1,month_2,month_3,month_4,month_5,month_6,month_7,month_8,month_9,month_10,month_11,month_12,hh_from,hh_to,fac_type from cluster where zillaid='" + Dist + "' and upazilaid='" + Upz + "'";
                Res = DS.DownloadJSON(SQLStr, "cluster", "zillaid,district_name,upazilaid,upazila_name,unionid,union_name,ward_no,epi_sub_block,clusterid,epi_cluster_name,year,month_1,month_2,month_3,month_4,month_5,month_6,month_7,month_8,month_9,month_10,month_11,month_12,hh_from,hh_to,fac_type", "zillaid,upazilaid,unionid,clusterid");



            }

            else  if(Flavel.equalsIgnoreCase("3"))
            {

                //Upazila
                //--------------------------------------------------------------------------------------
                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("উপজেলা তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,1 upload from upazila where zillaid='" + Dist + "' and upazilaid='" + Upz + "'";
                Res = DS.DownloadJSON(SQLStr, "upazila", "zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid");


                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("ইউনিয়ন তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select distinct u.zillaid,u.upazilaid,u.municipalityid,u.unionid,u.unionnameeng,u.unionname,u.systementrydate,u.modifydate,u.uploaddate,1 upload from unions u";
                SQLStr += " where u.zillaid='" + Dist + "' and u.upazilaid='" + Upz + "' and unionid='" + UN + "'";
                Res = DS.DownloadJSON(SQLStr, "unions", "zillaid,upazilaid,municipalityid,unionid,unionnameeng,unionname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid,unionid");

                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("ক্লাস্টার তথ্য ডাউনলোড হচ্ছে");
                progressHandler.sendMessage(progressHandler.obtainMessage());
                SQLStr = "Select zillaid,district_name,upazilaid,upazila_name,unionid,union_name,ward_no,epi_sub_block,clusterid,epi_cluster_name,year,month_1,month_2,month_3,month_4,month_5,month_6,month_7,month_8,month_9,month_10,month_11,month_12,hh_from,hh_to,fac_type from cluster where zillaid='" + Dist + "' and upazilaid='" + Upz+ "' and unionid='" + UN + "' and ward_no='" + WD + "'";
                Res = DS.DownloadJSON(SQLStr, "cluster", "zillaid,district_name,upazilaid,upazila_name,unionid,union_name,ward_no,epi_sub_block,clusterid,epi_cluster_name,year,month_1,month_2,month_3,month_4,month_5,month_6,month_7,month_8,month_9,month_10,month_11,month_12,hh_from,hh_to,fac_type", "zillaid,upazilaid,unionid,clusterid");

                jumpTime += 1;
                jumpTime += 1;

                Global.getInstance().setProgressMessage("Code list");
                progressHandler.sendMessage(progressHandler.obtainMessage());


                SQLStr = "SELECT typename,code, cname,systementrydate,modifydate,uploaddate,1 upload FROM codelist where typename in('sc','dg1','dg2','dg3','sc1')";
                Res = DS.DownloadJSON(SQLStr, "codelist", "typename,code, cname,systementrydate,modifydate,uploaddate,upload", "typename, code");


            }

            else  if(Flavel.equalsIgnoreCase("4"))
            {
                jumpTime += 1;
                jumpTime += 1;
                Global.getInstance().setProgressMessage("Code list");
                progressHandler.sendMessage(progressHandler.obtainMessage());


                SQLStr = "SELECT typename,code, cname,systementrydate,modifydate,uploaddate,1 upload FROM codelist where typename in('sc','dg1','dg2','dg3','sc1','26','30','33','34','5','302')";
                Res = DownloadJSON(SQLStr, "codelist", "typename,code, cname,systementrydate,modifydate,uploaddate,upload", "typename, code");


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //Provider wise starting household number by village: 10 Apr 2016
//----------------------------------------------------------------------------------------------
    public String StartingHouseholdNo_Village(String ProvType, String ProvCode, String zillaid, String upazilaid, String unionid, String Mouza, String Vill) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select StartHHno from ProviderArea\n" +
                "where provType='" + ProvType + "' and provCode='" + ProvCode + "' and zillaid=='" + zillaid + "' and  upazilaid='" + upazilaid + "' and unionid='" + unionid + "' and  mouzaid='" + Mouza + "' and villageid='" + Vill + "'", null);
        String retValue = "";
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            retValue = cur.getString(0);
            cur.moveToNext();
        }
        cur.close();
        db.close();
        return retValue;
    }


    public String HouseholdNumber(String ProvType, String ProvCode, String zillaid, String upazilaid, String unionid, String Mouza, String Vill) {
        String SQL = "";

        SQL = "Select (ifnull(max(cast(HHNo as int)),10000)+1)MaxHH from Household where";
        /*SQL += " dist='"+ g.getDistrict() +"' and upz='"+ g.getUpazila() +"' and un='"+ g.getUnion() +"' and Mouza='"+ g.getMouza() +"' and vill='"+ g.getVillage() +"' and ProvType='"+ g.getLoginProvType() +"' and ProvCode='"+ g.getLoginProvCode() +"'";*/
        SQL += " dist='" + zillaid + "' and upz='" + upazilaid + "' and un='" + unionid + "' and Mouza='" + Mouza + "' and vill='" + Vill + "'  and ProvType='" + ProvType + "'";

        String HHNo = ReturnSingleValue(SQL);

        String CompairHHNoFromProviderArea = StartingHouseholdNo_Village(ProvType, ProvCode,zillaid,upazilaid,unionid,Mouza, Vill);

        if(Integer.valueOf(HHNo)<Integer.valueOf(CompairHHNoFromProviderArea))
        {
            return CompairHHNoFromProviderArea;
        }
        else
        {
            return HHNo;
        }


    }


    //To get the list of columns(string) in table
    //----------------------------------------------------------------------------------------------
    public String GetColumnList(String TableName) {
        String CList = "";
        Cursor cur_H;
        cur_H = ReadData("pragma table_info('" + TableName + "')");

        cur_H.moveToFirst();
        int RecordCount = 0;

        while (!cur_H.isAfterLast()) {
            if (RecordCount == 0)
                CList += cur_H.getString(cur_H.getColumnIndex("name"));
            else
                CList += "," + cur_H.getString(cur_H.getColumnIndex("name"));

            RecordCount += 1;
            cur_H.moveToNext();
        }
        cur_H.close();

        return CList;
    }

    //To get the list of columns(string array) in table
    //----------------------------------------------------------------------------------------------
    public String[] GetColumnListArray(String TableName) {
        Cursor cur = ReadData("SELECT * FROM " + TableName + " WHERE 0");
        String[] columnNames;
        try {
            columnNames = cur.getColumnNames();
        } finally {
            cur.close();
        }
        return columnNames;
    }



 /*   public void DownloadHealthID(String Dist, String Upz, String UN, String ProvType, String ProvCode, int totalId) {
        String SQLStr = "";
        String Res = "";

        //ID Sync
        try {
            SQLStr = "Select sp_GenerateHealthID ('" + Dist + "','" + Upz + "','" + UN + "','" + ProvType + "','" + ProvCode + "'," + totalId + ")";
            //List<String> str = new ArrayList<String>();
            List<String> str = new ArrayList<String>();
            str.add(SQLStr);
            // ExecuteCommandOnServerJSON(str);
            ExecuteFunctionOnServer(SQLStr);

            //Service Provider
            SQLStr = "Select * from sp_HealthIDDownload ('" + Dist + "','" + Upz + "','" + UN + "','" + ProvType + "','" + ProvCode + "')";
            Res = DownloadJSON(SQLStr, "HealthIDRepository", "ZillaID, UpazilaID, UnionID, ProvType, ProvCode, AreaCode, HealthID,Status", "HealthID");

            //SQLStr = "Update ProviderDB set HealthIDRequest='2' where  Zillaid='"+ Dist +"' and UpazilaID='"+ Upz +"' and UnionID='"+ UN +"' and ProvType='"+ ProvType +"' and ProvCode='"+ ProvCode +"'";
            SQLStr = "Update \"ProviderDB\" set \"HealthIDRequest\"='2' where  zillaid='" + Dist + "' and upazilaid='" + Upz + "' and unionid='" + UN + "' and \"ProvType\"='" + ProvType + "' and \"ProvCode\"='" + ProvCode + "'";
            //Update "ProviderDB" set "HealthIDRequest"='2' where  zillaid='93' and upazilaid='9' and unionid='11' and "ProvType"='3' and "ProvCode"='93004'
            str = new ArrayList<String>();
            str.add(SQLStr);
            ExecuteCommandOnServerJSON(str);

        } catch (Exception ex) {

        }
    }


    public void DownloadHHNo(String Dist, String Upz, String UN,String Mouza, String vill,String ProvType, String ProvCode,int totalhhno) {
        String SQLStr = "";
        String Res = "";

        //ID Sync
        try {
            SQLStr = "Select sp_generate_hhno ('" + Dist + "','" + Upz + "','" + UN + "','" + Mouza + "','" + vill + "','" + ProvType + "','" + ProvCode + "'," + totalhhno + ")";
            //List<String> str = new ArrayList<String>();
            List<String> str = new ArrayList<String>();
            str.add(SQLStr);
            // ExecuteCommandOnServerJSON(str);
            ExecuteFunctionOnServer(SQLStr);

            //Service Provider
            //SQLStr = "Select * from sp_Downloading_hhno ('" + Dist + "','" + Upz + "','" + UN + "','" + Mouza + "','" + vill + "')";

            //SQLStr = "select  \"Div\",\"Dist\",\"Upz\",\"UN\",\"Mouza\",\"Vill\",\"ProvType\",\"ProvCode\",\"HHNo\",\"status\",\"issuedate\",\"upload\" from \"hhno_repository\"  where \"Dist\"='" + Dist + "' and \"Upz\"='" + Upz + "' and \"UN\"='" + UN + "' and \"Mouza\"='" + Mouza + "' and \"Vill\"='" + vill + "' and \"ProvType\"='" + ProvType + "' and \"ProvCode\"='" + ProvCode + "' and  \"upload\"='2'";
            SQLStr = "select  \"Div\",\"Dist\",\"Upz\",\"UN\",\"Mouza\",\"Vill\",\"ProvType\",\"ProvCode\",\"HHNo\",\"status\",\"issuedate\",\"modifyDate\",\"upload\" from \"hhno_repository\"  where \"Dist\"='" + Dist + "' and \"Upz\"='" + Upz + "' and \"UN\"='" + UN + "' and \"Mouza\"='" + Mouza + "' and \"Vill\"='" + vill + "' and \"ProvType\"='" + ProvType + "' and \"ProvCode\"='" + ProvCode + "' and  \"upload\"='2'";

            Res = DownloadJSON(SQLStr, "hhno_repository", "Div,Dist,Upz,UN,Mouza,Vill,ProvType,ProvCode,HHNo,status,issuedate,modifyDate,upload", "Dist, Upz, UN, Mouza, Vill, HHNo");

            SQLStr = "Update hhno_repository Set upload='1' Where ";
            SQLStr += " \"Dist\"='" + Dist + "' and";
            SQLStr += " \"Upz\"='" + Upz + "' and";
            SQLStr += " \"UN\"='" + UN + "' and";
            SQLStr += " \"Mouza\"='" + Mouza + "' and";
            SQLStr += " \"Vill\"='" + vill + "' and";
            SQLStr += " \"ProvType\"='" + ProvType + "' and";
            SQLStr += " \"ProvCode\"='" + ProvCode + "'";
            ExecuteFunctionOnServer(SQLStr);

            *//*SQLStr = "Update \"ProviderDB\" set \"HealthIDRequest\"='2' where  zillaid='" + Dist + "' and upazilaid='" + Upz + "' and unionid='" + UN + "' and \"ProvType\"='" + ProvType + "' and \"ProvCode\"='" + ProvCode + "'";
            str = new ArrayList<String>();
            str.add(SQLStr);
            //ExecuteCommandOnServerJSON(str);
            ExecuteFunctionOnServer(SQLStr);*//*

        } catch (Exception ex) {

        }
    }
*/
    public void ExecuteFunctionOnServer(String SQL) {
        ReturnSingleValueJSON(SQL);
    }

    public int TotalRecordCount(String TableName) {
        return Integer.parseInt(this.ReturnSingleValue("Select COUNT(*) AS TotalRecords from " + TableName + " WHERE Upload='2'"));
    }

    public List<DataClassProperty> GetDataListJSON(String VariableList, String TableName, String UniqueField, int limit_of_records, int offset_settings) {

      /*//  Save("PRAGMA journal_mode = TRUNCATE");
        Save("PRAGMA auto_vacuum = 1");
        Save("PRAGMA locking_mod = EXCLUSIVE");*/
        Cursor cur_H;
        if(TableName.equalsIgnoreCase("service_record"))
      {
          cur_H = ReadData("Select " + VariableList + " from " + TableName + " WHERE Upload='2' and healthid is not null LIMIT " + limit_of_records);

      }

      else {
          cur_H = ReadData("Select " + VariableList + " from " + TableName + " WHERE Upload='2' LIMIT " + limit_of_records);

      }
        cur_H.moveToFirst();
        List<DataClassProperty> data = new ArrayList<DataClassProperty>();
        DataClassProperty d;

        String DataList = "";

        String[] Count = VariableList.toString().replace(" ", "").split(",");
        String[] UField = UniqueField.toString().replace(" ", "").split(",");
        int RecordCount = 0;

        String WhereClause = "";
        String VarData[];
        int varPos = 0;
        while (!cur_H.isAfterLast()) {
            //Prepare Data List
            for (int c = 0; c < Count.length; c++) {
                if (c == 0) {
                    if (cur_H.getString(c) == null)
                        DataList = "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList = "";
                    else
                        DataList = cur_H.getString(c).toString();

                } else {
                    if (cur_H.getString(c) == null)
                        DataList += "^" + "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList += "^" + "";
                    else
                        DataList += "^" + cur_H.getString(c).toString();
                }
            }

            //Prepare Where Clause
            //VarData = DataList.split("\\^");
            VarData = Connection.split(DataList,'^');//.split("\\^");
            varPos = 0;


            for (int j = 0; j < UField.length; j++) {
                varPos = VarPosition(UField[j].toString(), Count);
                if (j == 0) {
                    WhereClause = "\"" + UField[j].toString() + "\"=" + "'" + VarData[varPos].toString() + "'";
                } else {
                    WhereClause += " and \"" + UField[j].toString() + "\"=" + "'" + VarData[varPos].toString() + "'";
                }
            }

            d = new DataClassProperty();
            d.setdatalist(DataList);
            d.setuniquefieldwithdata(WhereClause);
            data.add(d);

            RecordCount += 1;
            cur_H.moveToNext();
        }
        cur_H.close();

        return data;
    }




    ///New DownloadJSON
    //----------------------------------------------------------------------------------------------
    public String DownloadJSON(String SQL, String TableName, String ColumnList, String UniqueField) {
        DownloadRequestClass dr = new DownloadRequestClass();
        dr.setmethodname("DownloadData");
        dr.setSQL(SQL);
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        int varPos = 0;
        int varPos_modifyDate = 0;

        String response = "";
        String resp = "";
        String IDNO = "";
        String SaveResp = "";
        try {
            //Download from server
            //--------------------------------------------------------------------------------------
            DownloadJSONData dload = new DownloadJSONData();
            //response = dload.execute(json).get();
            response = new DownloadJSONData().execute(json).get();

            downloadClass responseData = (downloadClass) gson.fromJson(response, downloadClass.class);

            //--------------------------------------------------------------------------------------
            String UField[]  = UniqueField.toString().replace(" ", "").split(",");
            String VarList[] = ColumnList.toString().replace(" ", "").split(",");

            List<DataClassProperty> dataTemp = new ArrayList<DataClassProperty>();
            List<DataClassProperty> data = new ArrayList<DataClassProperty>();
            DataClassProperty d;
            String DataList = "";

            String modify_Date = "";
            //Integer insertBatchSize = responseData.getdata().size()/100 + 1;
            if (responseData != null) {
                SQL = "Insert or replace into "+ TableName +"("+ ColumnList +")Values";
                for (int i = 0; i < responseData.getdata().size(); i++) {
                    String VarData[] = Connection.split(responseData.getdata().get(i).toString(), '^');

                    //Generate Unique ID
                    //------------------------------------------------------------------------------
                    for (int j = 0; j < UField.length; j++) {
                        varPos = VarPosition(UField[j].toString(), VarList);
                        if (j == 0) {
                            IDNO += VarData[varPos].toString();
                        } else {
                            IDNO += VarData[varPos].toString();
                        }
                    }

                    //Variable Position: modifyDate
                    if(TableName.equalsIgnoreCase("Member")||TableName.equalsIgnoreCase("Household")||TableName.equalsIgnoreCase("ses")||TableName.equalsIgnoreCase("visits")) {
                        varPos_modifyDate = VarPosition("EnDt", VarList);
                        modify_Date = VarData[varPos_modifyDate].toString().replace("null", "");
                    }else{
                        varPos_modifyDate = VarPosition("modifyDate", VarList);
                        modify_Date = VarData[varPos_modifyDate].toString().replace("null", "");
                    }

                    //------------------------------------------------------------------------------
                    if (i == 0) {
                        SQL += "('" + responseData.getdata().get(i).toString().replace("^","','").replace("null","") +"')";
                    } else {
                        SQL += ",('" + responseData.getdata().get(i).toString().replace("^","','").replace("null","") +"')";
                    }

                    String PT = Global.getInstance().getProvType();
                    String PC = Global.getInstance().getProvCode();

                    //Populate class with data for sync_management
                    //------------------------------------------------------------------------------
                    DataList = TableName + "^" + IDNO + "^" + modify_Date + "^" + PT + "^" + PC;
                    d = new DataClassProperty();
                    d.setdatalist(DataList);
                    d.setuniquefieldwithdata("" +
                            "\"tableName\"  ='" + TableName + "' and " +
                            "\"recordId\"   ='" + IDNO + "' and " +
                            "\"modifyDate\" ='" + modify_Date + "' and " +
                            "\"provType\"   ='" + PT + "' and " +
                            "\"provCode\"   ='" + PC + "'");
                    dataTemp.add(d);

                    IDNO = "";
                }
                SaveResp = SaveData(SQL);
                if(SaveResp.length()==0){
                    data = dataTemp;
                }

                //Update data on sync management
                //------------------------------------------------------------------------------
                DataClass dt = new DataClass();
                dt.setmethodname("UploadData_Sync");
                dt.settablename("data_sync_management");
                dt.setcolumnlist("tableName,recordId,modifyDate,provType,provCode");
                dt.setdata(data);

                Gson gson1   = new Gson();
                String json1 = gson1.toJson(dt);
                String response1  = new UploadJSONData().execute(json1).get();
            }

        } catch (Exception e) {
            resp = e.getMessage();
            e.printStackTrace();
        }
        return resp;
    }

    //Download LMIS Json
    //----------------------------------------------------------------------------------------------
    public String DownloadLMISJSON(String SQL, String TableName, String ColumnList, String UniqueField) {
        DownloadRequestClass dr = new DownloadRequestClass();
        dr.setmethodname("DownloadData");
        dr.setSQL(SQL);
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        int varPos = 0;
        int varPos_modifyDate = 0;

        String response = "";
        String resp = "";
        String IDNO = "";
        String SaveResp = "";
        try {
            //Download from server
            //--------------------------------------------------------------------------------------
            DownloadLMISJSONData dload = new DownloadLMISJSONData();
            //response = dload.execute(json).get();
            response = new DownloadLMISJSONData().execute(json).get();

            downloadClass responseData = (downloadClass) gson.fromJson(response, downloadClass.class);

            //--------------------------------------------------------------------------------------
            String UField[]  = UniqueField.toString().replace(" ", "").split(",");
            String VarList[] = ColumnList.toString().replace(" ", "").split(",");

            List<DataClassProperty> dataTemp = new ArrayList<DataClassProperty>();
            List<DataClassProperty> data = new ArrayList<DataClassProperty>();
            DataClassProperty d;
            String DataList = "";

            String modify_Date = "";
            //Integer insertBatchSize = responseData.getdata().size()/100 + 1;
            if (responseData != null) {
                SQL = "Insert or replace into "+ TableName +"("+ ColumnList +")Values";
                for (int i = 0; i < responseData.getdata().size(); i++) {
                    String VarData[] = Connection.split(responseData.getdata().get(i).toString(), '^');

                    //Generate Unique ID
                    //------------------------------------------------------------------------------
                    for (int j = 0; j < UField.length; j++) {
                        varPos = VarPosition(UField[j].toString(), VarList);
                        if (j == 0) {
                            IDNO += VarData[varPos].toString();
                        } else {
                            IDNO += VarData[varPos].toString();
                        }
                    }

                    //Variable Position: modifyDate
                    if(TableName.equalsIgnoreCase("Member")||TableName.equalsIgnoreCase("Household")||TableName.equalsIgnoreCase("ses")||TableName.equalsIgnoreCase("visits")) {
                        varPos_modifyDate = VarPosition("EnDt", VarList);
                        modify_Date = VarData[varPos_modifyDate].toString().replace("null", "");
                    }else{
                        varPos_modifyDate = VarPosition("modifyDate", VarList);
                        modify_Date = VarData[varPos_modifyDate].toString().replace("null", "");
                    }

                    //------------------------------------------------------------------------------
                    if (i == 0) {
                        SQL += "('" + responseData.getdata().get(i).toString().replace("^","','").replace("null","") +"')";
                    } else {
                        SQL += ",('" + responseData.getdata().get(i).toString().replace("^","','").replace("null","") +"')";
                    }

                    String PT = Global.getInstance().getProvType();
                    String PC = Global.getInstance().getProvCode();

                    //Populate class with data for sync_management
                    //------------------------------------------------------------------------------
                    DataList = TableName + "^" + IDNO + "^" + modify_Date + "^" + PT + "^" + PC;
                    d = new DataClassProperty();
                    d.setdatalist(DataList);
                    d.setuniquefieldwithdata("" +
                            "\"tableName\"  ='" + TableName + "' and " +
                            "\"recordId\"   ='" + IDNO + "' and " +
                            "\"modifyDate\" ='" + modify_Date + "' and " +
                            "\"provType\"   ='" + PT + "' and " +
                            "\"provCode\"   ='" + PC + "'");
                    dataTemp.add(d);

                    IDNO = "";
                }
                SaveResp = SaveData(SQL);
                if(SaveResp.length()==0){
                    data = dataTemp;
                }

                //Update data on sync management
                //------------------------------------------------------------------------------
                DataClass dt = new DataClass();
                dt.setmethodname("UploadData_Sync");
                dt.settablename("data_sync_management");
                dt.setcolumnlist("tableName,recordId,modifyDate,provType,provCode");
                dt.setdata(data);

                Gson gson1   = new Gson();
                String json1 = gson1.toJson(dt);
                String response1  = new UploadLMISJSONData().execute(json1).get();
            }

        } catch (Exception e) {
            resp = e.getMessage();
            e.printStackTrace();
        }
        return resp;
    }



/*
    public String DownloadJSON1(String SQL, String TableName, String ColumnList, String UniqueField) {
        DownloadRequestClass dr = new DownloadRequestClass();
        dr.setmethodname("DownloadData");
        dr.setSQL(SQL);
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        String WhereClause = "";
        int varPos = 0;

        String response = "";
        String resp = "";
        String IDNO = "";
        try {
            //For Web Api
            //--------------------------------------------------------------------------------------
            DownloadJSONData dload = new DownloadJSONData();
            response = dload.execute(json).get();
            downloadClass responseData = (downloadClass) gson.fromJson(response, downloadClass.class);
            //--------------------------------------------------------------------------------------

            String UField[] = UniqueField.toString().replace(" ", "").split(",");
            String VarList[] = ColumnList.toString().replace(" ", "").split(",");
            String InsertSQL = "";

            List<String> dataStatus = new ArrayList<String>();
            List<DataClassProperty> data = new ArrayList<DataClassProperty>();
            DataClassProperty d;
            String DataList = "";

            String modify_Date = "";
            if (responseData != null) {
                for (int i = 0; i < responseData.getdata().size(); i++) {
                    String VarData[] = split(responseData.getdata().get(i).toString(), '^');

                    //Generate where clause
                    SQL = "";
                    WhereClause = "";
                    varPos = 0;
                    for (int j = 0; j < UField.length; j++) {
                        varPos = VarPosition(UField[j].toString(), VarList);
                        if (j == 0) {
                            WhereClause = UField[j].toString() + "=" + "'" + VarData[varPos].toString() + "'";
                            IDNO += VarData[varPos].toString();
                        } else {
                            WhereClause += " and " + UField[j].toString() + "=" + "'" + VarData[varPos].toString() + "'";
                            IDNO += VarData[varPos].toString();
                        }
                    }

                    //Update command
                    if (Existence("Select " + VarList[0] + " from " + TableName + " Where " + WhereClause)) {
                        for (int r = 0; r < VarList.length; r++) {
                            if (r == 0) {
                                SQL = "Update " + TableName + " Set ";
                                SQL += VarList[r] + " = '" + VarData[r].toString() + "'";
                            } else {
                                if (r == VarData.length - 1) {
                                    SQL += "," + VarList[r] + " = '" + VarData[r].toString() + "'";
                                    SQL += " Where " + WhereClause;
                                } else {
                                    SQL += "," + VarList[r] + " = '" + VarData[r].toString() + "'";
                                }
                            }
                            if ("modifyDate".equalsIgnoreCase(VarList[r])) {
                                modify_Date = VarData[r].toString().replace("null", "");
                            }
                        }

                        Save(SQL);
                    }
                    //Insert command
                    else {
                        for (int r = 0; r < VarList.length; r++) {
                            if (r == 0) {
                                SQL = "Insert into " + TableName + "(" + ColumnList + ")Values(";
                                SQL += "'" + VarData[r].toString() + "'";
                            } else {
                                SQL += ",'" + VarData[r].toString() + "'";
                            }
                            if (VarList[r].toString().equalsIgnoreCase("modifyDate")) {
                                modify_Date = VarData[r].toString().replace("null", "");
                            }
                        }
                        SQL += ")";

                        Save(SQL);
                    }

                */
/* #######*//*

                    //Way - 2
                    DataList = TableName + "^" + IDNO + "^" + modify_Date + "^" + Global.getInstance().getProvType() + "^" + Global.getInstance().getProvCode();
                    d = new DataClassProperty();
                    d.setdatalist(DataList);
                    d.setuniquefieldwithdata("\"recordId\"='" + IDNO + "' and \"modifyDate\"='" + modify_Date + "' and \"provCode\"='" + Global.getInstance().getProvCode() + "'");
                    data.add(d);

               */
/*dataStatus.add("Insert into \"data_sync_management\"(\"tableName\",\"recordId\",\"modifyDate\",\"provType\",\"provCode\") " +
                        "VALUES" +
                        "('" + TableName + "', '" + IDNO + "','" + modify_Date + "', '" +
                        Global.getInstance().getProvType() + "', '" +
                        Global.getInstance().getProvCode() + "') ");*//*

                    IDNO = "";
                }


                //Way - 2
                DataClass dt = new DataClass();
                dt.setmethodname("UploadData_For_Sync");
                dt.settablename("data_sync_management");
                dt.setcolumnlist("tableName,recordId,modifyDate,provType,provCode");
                dt.setdata(data);

                Gson gson1   = new Gson();
                String json1 = gson1.toJson(dt);
                String response1 = "";

                UploadJSONData u = new UploadJSONData();

                try{
                    response1=u.execute(json1).get();
                } catch (Exception e) {
                    //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }


            //Status back to server
            */
/*if(dataStatus.size()>0)
            {
                ExecuteCommandOnServerJSON(dataStatus);
            }*//*



        } catch (Exception e) {
            resp = e.getMessage();
            e.printStackTrace();
        }

        return resp;
    }
*/


    public String ExecuteCommandOnServerJSON(List<String> SQLList) {
        ExecuteCommandClass dr = new ExecuteCommandClass();
        dr.setmethodname("ExecuteSQLList");
        dr.setSQL(SQLList);
        dr.setSecutiryCodeL("org.postgresql");
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        DownloadJSONData dload = new DownloadJSONData();
        String response = null;
        try {
            response = dload.execute(json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return response;
    }
    //Upload Data to Server (Soap Service/Web API)

   /* public void UploadJSON(String TableName, String ColumnList, String UniqueFields, int total_records_in_table) {
        // int total_records_in_table = this.TotalRecordCount(TableName);
        int limit_of_records = total_records_in_table;
        int offset_settings = 0;
        DataClassProperty d1;
        String DataList = "";
        String IDNO = "";
        String modify_Date = "";
        DataClass dt = new DataClass();
        //Old Upload Method
        dt.setmethodname("UploadData");
        // New Upload Method
        //dt.setmethodname("UploadData_Sync");
        dt.settablename(TableName);
        dt.setcolumnlist(ColumnList);
        List<DataClassProperty> data = GetDataListJSON(ColumnList, TableName, UniqueFields, limit_of_records, offset_settings);
        dt.setdata(data);

        Gson gson = new Gson();
        String json = gson.toJson(dt);
        String response = "";

        //Web Service(asmx)
        //------------------------------------------------------------------------------------------
        //UploadDataJSON u = new UploadDataJSON();

        //Web APIs(java)
        //------------------------------------------------------------------------------------------
        UploadJSONData u = new UploadJSONData();

        try {
            response = u.execute(json).get();

            //Process Response
            downloadClass d = new downloadClass();
            Type collType = new TypeToken<downloadClass>() {
            }.getType();
            downloadClass responseData = (downloadClass) gson.fromJson(response, collType);

            //if (responseData != null) {
            for (int i = 0; i < responseData.getdata().size(); i++) {
                // String s ="Update " + TableName + " Set Upload='2' where " + responseData.getdata().get(i).toString();
                Save("Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(i).toString());

                *//*DataList = TableName + "^" + IDNO + "^" + modify_Date + "^" + Global.getInstance().getProvType() + "^" + Global.getInstance().getProvCode();
                d1 = new DataClassProperty();
                d1.setdatalist(DataList);
                d1.setuniquefieldwithdata("\"recordId\"='" + IDNO + "' and \"modifyDate\"='" + modify_Date + "' and \"provCode\"='" + Global.getInstance().getProvCode() + "'");
                data.add(d1);

                IDNO = "";*//*
            }

            //Upload status to data_sync_management
            *//*DataClass dc = new DataClass();
            dc.setmethodname("UploadData_For_Sync");
            dc.settablename("data_sync_management");
            dc.setcolumnlist("tableName,recordId,modifyDate,provType,provCode");
            dc.setdata(data);

            Gson gson1   = new Gson();
            String json1 = gson1.toJson(dt);
            String response1 = "";

            UploadJSONData u1 = new UploadJSONData();
            try{
                response1=u1.execute(json1).get();
            } catch (Exception e) {
                //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }*//*
        } catch (Exception e) {
            //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
*/

    public static String SelectedSpinnerValue(String SelectedTest, String SplitValue) {
        String[] D = SelectedTest.split(SplitValue);
        return D[0];
    }
    public static int SpinnerItemPositionAnyLength(Spinner spn, String Value)
    {
        //String SelectedSpinnerValue;
        int pos = 0;
        if(Value.length()!=0)
        {
            for(int i=0;i<spn.getCount();i++)
            {
                if(spn.getItemAtPosition(i).toString().length()!=0)
                {
                    if(SelectedSpinnerValue(spn.getItemAtPosition(i).toString(),"-").equalsIgnoreCase(Value))
                    {
                        pos = i;
                        i   = spn.getCount();
                    }
                }
            }
        }
        return pos;
    }
    public List<String> WorkPlanMonthList() {
       List<String> Monthlist = new ArrayList<String>();
       Cursor cur = ReadData("Select ifnull(month,'')||':'||ifnull(providerId,'') from workPlanMaster");
       String retValue = "";
       cur.moveToFirst();
       while (!cur.isAfterLast()) {
           Monthlist.add(cur.getString(0));

           cur.moveToNext();
       }
       cur.close();
       return Monthlist;
   }

    //for data sync by village: 22 nov 2016: Nisan
    public List<String> VillageList() {
        List<String> vlist = new ArrayList<String>();
        Cursor cur = ReadData("Select zillaid||':'||upazilaid||':'||unionid||':'||mouzaid||':'||villageid from Village");
        String retValue = "";
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            vlist.add(cur.getString(0));

            cur.moveToNext();
        }
        cur.close();
        return vlist;
    }

    public List<String> VillageWiseRestofHH() {
        List<String> vlist = new ArrayList<String>();
        String SQL="select village.zillaid||':'||village.upazilaid||':'||village.unionid||':'||village.mouzaid||':'||village.villageid\n" +
                "||':'||count(hhno_repository.HHNo) as HHNo \n" +
                "from village \n" +
                "left  join hhno_repository on village.zillaid=hhno_repository.dist and\n" +
                "village.upazilaid=hhno_repository.upz and\n" +
                "village.unionid=hhno_repository.un and\n" +
                "village.mouzaid=hhno_repository.mouza and\n" +
                "village.villageid=hhno_repository.vill\n" +
                "and hhno_repository.status=1\n" +
                "group by village.zillaid,village.upazilaid,village.unionid,village.mouzaid,village.villageid\n" +
                "having count(hhno_repository.HHNo)<70";
        Cursor cur = ReadData(SQL);
        String retValue = "";
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            vlist.add(cur.getString(0));

            cur.moveToNext();
        }
        cur.close();
        return vlist;
    }

    public List<String> RemoveSpecialCharForNameList() {
        List<String> CharForNamelist = new ArrayList<String>();
        Cursor cur = ReadData("Select nameeng||':'||Dist||':'||Upz||':'||UN||':'||Mouza||':'||Vill||':'||HHNo||':'||SNo from Member where nameeng glob '*[^ .a-zA-Z]*'");
        String retValue = "";
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            CharForNamelist.add(cur.getString(0));

            cur.moveToNext();
        }
        cur.close();
        return CharForNamelist;
    }

    public List<String> RemoveSpecialCharForFNameList() {
        List<String> CharForFNamelist = new ArrayList<String>();
        Cursor cur = ReadData("Select Father||':'||Dist||':'||Upz||':'||UN||':'||Mouza||':'||Vill||':'||HHNo||':'||SNo  from Member where Father glob '*[^ .a-zA-Z]*'");
        String retValue = "";
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            CharForFNamelist.add(cur.getString(0));

            cur.moveToNext();
        }
        cur.close();
        return CharForFNamelist;
    }

    public List<String> RemoveSpecialCharForMNameList() {
        List<String> CharForMNamelist = new ArrayList<String>();
        Cursor cur = ReadData("Select Mother||':'||Dist||':'||Upz||':'||UN||':'||Mouza||':'||Vill||':'||HHNo||':'||SNo  from Member where Mother glob '*[^ .a-zA-Z]*'");
        String retValue = "";
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            CharForMNamelist.add(cur.getString(0));

            cur.moveToNext();
        }
        cur.close();
        return CharForMNamelist;
    }

    public int batchSizeDown(String TableName)
    {
        return Integer.parseInt(ReturnSingleValue("select batchSizeDown from databasesetting where tableName='" + TableName + "'"));
    }

    public int batchSizeUp(String TableName)
    {
        return Integer.parseInt(ReturnSingleValue("select batchSizeDown from databasesetting where tableName='" + TableName + "'"));
    }
    //Download Data from Server (Soap Service/Web API)
    public String DownloadJSON_InsertOnly(String SQL, String TableName, String ColumnList, String UniqueField) {
        DownloadRequestClass dr = new DownloadRequestClass();
        dr.setmethodname("DownloadData");
        dr.setSQL(SQL);
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        String WhereClause = "";
        int varPos = 0;

        String response = "";
        String resp = "";
        String IDNO = "";
        try {
            //For Web Api
            //--------------------------------------------------------------------------------------
            DownloadJSONData dload = new DownloadJSONData();
            response = dload.execute(json).get();
            downloadClass responseData = (downloadClass) gson.fromJson(response, downloadClass.class);
            //--------------------------------------------------------------------------------------


            String UField[] = UniqueField.toString().replace(" ", "").split(",");
            String VarList[] = ColumnList.toString().replace(" ", "").split(",");
            String InsertSQL = "";

            List<String> dataStatus = new ArrayList<String>();
            List<DataClassProperty> data = new ArrayList<DataClassProperty>();
            DataClassProperty d;
            String DataList = "";

            String modify_Date = "";
            if (responseData != null) {
                for (int i = 0; i < responseData.getdata().size(); i++) {
                    String VarData[] = split(responseData.getdata().get(i).toString(), '^');

                    //Generate where clause
                    SQL = "";
                    WhereClause = "";
                    varPos = 0;
                    for (int j = 0; j < UField.length; j++) {
                        varPos = VarPosition(UField[j].toString(), VarList);
                        if (j == 0) {
                            WhereClause = UField[j].toString() + "=" + "'" + VarData[varPos].toString() + "'";
                            IDNO += VarData[varPos].toString();
                        } else {
                            WhereClause += " and " + UField[j].toString() + "=" + "'" + VarData[varPos].toString() + "'";
                            IDNO += VarData[varPos].toString();
                        }
                    }

                    for (int r = 0; r < VarList.length; r++) {
                        if (r == 0) {
                            SQL = "Insert into " + TableName + "(" + ColumnList + ")Values(";
                            SQL += "'" + VarData[r].toString() + "'";
                        } else {
                            SQL += ",'" + VarData[r].toString() + "'";
                        }
                        if(TableName.equalsIgnoreCase("Member")||TableName.equalsIgnoreCase("Household")||TableName.equalsIgnoreCase("ses")||TableName.equalsIgnoreCase("visits")) {
                            if ("EnDt".equalsIgnoreCase(VarList[r])) {
                                modify_Date = VarData[r].toString().replace("null", "");
                            }
                        }
                        else
                        {
                            if ("modifyDate".equalsIgnoreCase(VarList[r])) {
                                modify_Date = VarData[r].toString().replace("null", "");
                            }
                        }
                    }
                    SQL += ")";

                    try{
                        Save(SQL);
                    }
                    catch (Exception ex){

                        Log.e("Error from Connection", ex.getMessage());
                        //Connection.MessageBox(Connection.this, ex.getMessage());
                        //return;

                    }
                    //Populate class with data for sync_management
                    //------------------------------------------------------------------------------
                    String PT = Global.getInstance().getProvType();
                    String PC = Global.getInstance().getProvCode();

                    DataList = TableName + "^" + IDNO + "^" + modify_Date + "^" + PT + "^" + PC;
                    d = new DataClassProperty();
                    d.setdatalist(DataList);
                    d.setuniquefieldwithdata("" +
                            "\"tableName\"  ='" + TableName + "' and " +
                            "\"recordId\"   ='" + IDNO + "' and " +
                            "\"modifyDate\" ='" + modify_Date + "' and " +
                            "\"provType\"   ='" + PT + "' and " +
                            "\"provCode\"   ='" + PC + "'");
                    data.add(d);

                    IDNO = "";

                }

                //Update data on sync management
                //------------------------------------------------------------------------------
                DataClass dt = new DataClass();
                //Insert or Update
                dt.setmethodname("UploadData_For_Sync");
                //Insert only
                //dt.setmethodname("UploadData_Sync_Management");
                dt.settablename("data_sync_management");
                dt.setcolumnlist("tableName,recordId,modifyDate,provType,provCode");
                dt.setdata(data);

                Gson gson1   = new Gson();
                String json1 = gson1.toJson(dt);
                String response1 = "";

                try{
                    response1 = new UploadJSONData().execute(json1).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            resp = e.getMessage();
            e.printStackTrace();
        }

        return resp;
    }

    //Date: 31 Jul 2017 : Nisan
    //----------------------------------------------------------------------------------------------
    public void UploadJSON(String TableName, String ColumnList, String UniqueFields) {
        int totalRecord = this.TotalRecordCount(TableName);
        int BatchSize = 0;


        BatchSize = Integer.parseInt(this.ReturnSingleValue("select batchsizeup AS Total from databasesetting   WHERE tableName = '" + TableName + "'"));

        int totalBatch = (totalRecord / BatchSize) + 1;
        DataClass dt;

        List<DataClassProperty> data;
        String SQLUpdateStatement = "";

        for (int i = 0; i < totalBatch; i++) {
            data = GetDataListJSON(ColumnList, TableName, UniqueFields, BatchSize, 0);
            if(data.size()>0) {
                dt = new DataClass();
                dt.setmethodname("UploadData_Sync");
                dt.settablename(TableName);
                dt.setcolumnlist(ColumnList);
                dt.setdata(data);

                Gson gson = new Gson();
                String json = gson.toJson(dt);
                try {
                    String response = new UploadJSONData().execute(json).get();

                    //Process Response
                    if(response.length()>0) {
                        downloadClass d = new downloadClass();
                        Type collType = new TypeToken<downloadClass>() {
                        }.getType();
                        downloadClass responseData = (downloadClass) gson.fromJson(response, collType);

                        for (int j = 0; j < responseData.getdata().size(); j++) {
                            SQLUpdateStatement += "Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(j).toString() + ";";
                        }
                        Save(SQLUpdateStatement);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }


   /* public void UploadJSON(String TableName, String ColumnList, String UniqueFields) {
        int totalRecord = this.TotalRecordCount(TableName);
        int BatchSize = 0;

        BatchSize = Integer.parseInt(this.ReturnSingleValue("select batchsizeup as Total from databasesetting   WHERE tableName = '" + TableName + "'"));

        int totalBatch = (totalRecord / BatchSize) + 1;
        DataClass dt;

        List<DataClassProperty> data;
        String SQLUpdateStatement = "";

        for (int i = 0; i < totalBatch; i++) {
            data = GetDataListJSON(ColumnList, TableName, UniqueFields, BatchSize, 0);
            if (data.size() > 0) {
                dt = new DataClass();

                dt.setmethodname("UploadData");
                dt.settablename(TableName);
                dt.setcolumnlist(ColumnList);
                dt.setdata(data);

                Gson gson = new Gson();
                String json = gson.toJson(dt);
                try {
                    String response = new UploadJSONData().execute(json).get();

                    //Process Response
                    if (response.length() > 0) {
                        downloadClass d = new downloadClass();
                        Type collType = new TypeToken<downloadClass>() {
                        }.getType();
                        downloadClass responseData = gson.fromJson(response, collType);

                        for (int j = 0; j < responseData.getdata().size(); j++) {
                            SQLUpdateStatement += "Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(j).toString() + ";";
                        }
                        Save(SQLUpdateStatement);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    //Date: 9 August 2017 : Fazlu
    //----------------------------------------------------------------------------------------------
    public void UploadLMISJSON(String TableName, String ColumnList, String UniqueFields) {
        int totalRecord = this.TotalRecordCount(TableName);
        int BatchSize = 0;
        BatchSize = Integer.parseInt(this.ReturnSingleValue("select batchsizeup AS Total from databasesetting   WHERE tableName = '" + TableName + "'"));

        int totalBatch = (totalRecord / BatchSize) + 1;
        DataClass dt;

        List<DataClassProperty> data;
        String SQLUpdateStatement = "";

        for (int i = 0; i < totalBatch; i++) {
            data = GetDataListJSON(ColumnList, TableName, UniqueFields, BatchSize, 0);
            if(data.size()>0) {
                dt = new DataClass();
                dt.setmethodname("UploadData_Sync");
                dt.settablename(TableName);
                dt.setcolumnlist(ColumnList);
                dt.setdata(data);

                Gson gson = new Gson();
                String json = gson.toJson(dt);
                try {
                    String response = new UploadLMISJSONData().execute(json).get();

                    //Process Response
                    if(response.length()>0) {
                        downloadClass d = new downloadClass();
                        Type collType = new TypeToken<downloadClass>() {
                        }.getType();
                        downloadClass responseData = (downloadClass) gson.fromJson(response, collType);

                        for (int j = 0; j < responseData.getdata().size(); j++) {
                            SQLUpdateStatement += "Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(j).toString() + ";";
                        }
                        Save(SQLUpdateStatement);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    public void UploadJSONworkPlanMaster(String TableName, String ColumnList, String UniqueFields, String Month, String providerId, int total_records_in_table) {
        // int total_records_in_table = this.TotalRecordCount(TableName);
        int limit_of_records = total_records_in_table;
        int offset_settings = 0;


        DataClass dt = new DataClass();
         dt.setmethodname("UploadData_Sync");
        dt.settablename(TableName);
        dt.setcolumnlist(ColumnList);
        List<DataClassProperty> data = GetDataListJSONworkPlanMaster(ColumnList, TableName, UniqueFields, Month, providerId, limit_of_records, offset_settings);
        dt.setdata(data);

        Gson gson = new Gson();
        String json = gson.toJson(dt);
        String response = "";

        //Web Service(asmx)
        //------------------------------------------------------------------------------------------
        //UploadDataJSON u = new UploadDataJSON();

        //Web APIs(java)
        //------------------------------------------------------------------------------------------
        UploadJSONData u = new UploadJSONData();

        try {
            response = u.execute(json).get();

            //Process Response
            downloadClass d = new downloadClass();
            Type collType = new TypeToken<downloadClass>() {
            }.getType();
            downloadClass responseData = (downloadClass) gson.fromJson(response, collType);


            for (int i = 0; i < responseData.getdata().size(); i++) {
                // String s ="Update " + TableName + " Set Upload='2' where " + responseData.getdata().get(i).toString();
                Save("Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(i).toString());


            }

        } catch (Exception e) {
            //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public void UploadJSONworkPlanMaster(String TableName, String ColumnList, String UniqueFields, String Month, String providerId) {
        int total_records_in_table = this.TotalRecordCount(TableName);
        int records_to_sync_at_a_time = Integer.parseInt(this.ReturnSingleValue("select batchsizeup AS Total from databasesetting   WHERE tableName = '" + TableName + "'"));
        //int records_to_sync_at_a_time = Integer.parseInt(this.ReturnSingleValue("SELECT  dataLength AS Total FROM DataLengthTable  WHERE tableName = '" + TableName + "'"));
        if (records_to_sync_at_a_time <= 0) {
            records_to_sync_at_a_time = 20;
        }
        int limit_of_records = records_to_sync_at_a_time;
        int offset_settings = 0;
        int total_sent = 0;

        if (total_records_in_table == 0)
            return;

        if (total_records_in_table <= records_to_sync_at_a_time) {
            UploadJSONworkPlanMaster(TableName, ColumnList, UniqueFields, Month, providerId, total_records_in_table);
            return;
        }


        int total_count = total_records_in_table / records_to_sync_at_a_time;

        for (int start = 0; start <= total_count; start = start + 1) {
            if ((total_records_in_table - total_sent) <= records_to_sync_at_a_time) {
                UploadJSONworkPlanMaster(TableName, ColumnList, UniqueFields, Month, providerId, total_records_in_table);
                return;
            }

            DataClass dt = new DataClass();
             dt.setmethodname("UploadData_Sync");
            dt.settablename(TableName);
            dt.setcolumnlist(ColumnList);
            List<DataClassProperty> data = GetDataListJSONworkPlanMaster(ColumnList, TableName, UniqueFields, Month, providerId, limit_of_records, offset_settings);
            dt.setdata(data);

            Gson gson = new Gson();
            String json = gson.toJson(dt);
            String response = "";

            //Web Service(asmx)
            //------------------------------------------------------------------------------------------
            //UploadDataJSON u = new UploadDataJSON();

            //Web APIs(java)
            //------------------------------------------------------------------------------------------
            UploadJSONData u = new UploadJSONData();

            try {
                response = u.execute(json).get();

                //Process Response
                downloadClass d = new downloadClass();
                Type collType = new TypeToken<downloadClass>() {
                }.getType();
                downloadClass responseData = (downloadClass) gson.fromJson(response, collType);


                for (int i = 0; i < responseData.getdata().size(); i++) {
                    // String s ="Update " + TableName + " Set Upload='2' where " + responseData.getdata().get(i).toString();
                    Save("Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(i).toString());
                    total_sent = total_sent + 1;

                }

            } catch (Exception e) {
                //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            offset_settings = limit_of_records + offset_settings;
            // return response;
        }


    }


    //workPlanDetail
    // Month,providerId

    public void UploadJSONworkPlanDetail(String TableName, String ColumnList, String UniqueFields, String Month, String providerId, int total_records_in_table) {
        // int total_records_in_table = this.TotalRecordCount(TableName);
        int limit_of_records = total_records_in_table;
        int offset_settings = 0;


        DataClass dt = new DataClass();
         dt.setmethodname("UploadData_Sync");
        dt.settablename(TableName);
        dt.setcolumnlist(ColumnList);
        List<DataClassProperty> data = GetDataListJSONworkPlanDetail(ColumnList, TableName, UniqueFields, Month, providerId, limit_of_records, offset_settings);
        dt.setdata(data);

        Gson gson = new Gson();
        String json = gson.toJson(dt);
        String response = "";

        //Web Service(asmx)
        //------------------------------------------------------------------------------------------
        //UploadDataJSON u = new UploadDataJSON();

        //Web APIs(java)
        //------------------------------------------------------------------------------------------
        UploadJSONData u = new UploadJSONData();

        try {
            response = u.execute(json).get();

            //Process Response
            downloadClass d = new downloadClass();
            Type collType = new TypeToken<downloadClass>() {
            }.getType();
            downloadClass responseData = (downloadClass) gson.fromJson(response, collType);


            for (int i = 0; i < responseData.getdata().size(); i++) {
                // String s ="Update " + TableName + " Set Upload='2' where " + responseData.getdata().get(i).toString();
                Save("Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(i).toString());


            }

        } catch (Exception e) {
            //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public void UploadJSONworkPlanDetail(String TableName, String ColumnList, String UniqueFields, String Month, String providerId) {
        int total_records_in_table = this.TotalRecordCount(TableName);
        //int records_to_sync_at_a_time = Integer.parseInt(this.ReturnSingleValue("SELECT  dataLength AS Total FROM DataLengthTable  WHERE tableName = '" + TableName + "'"));
        int records_to_sync_at_a_time = Integer.parseInt(this.ReturnSingleValue("select batchsizeup AS Total from databasesetting   WHERE tableName = '" + TableName + "'"));
        if (records_to_sync_at_a_time <= 0) {
            records_to_sync_at_a_time = 20;
        }
        int limit_of_records = records_to_sync_at_a_time;
        int offset_settings = 0;
        int total_sent = 0;

        if (total_records_in_table == 0)
            return;

        if (total_records_in_table <= records_to_sync_at_a_time) {
            UploadJSONworkPlanDetail(TableName, ColumnList, UniqueFields, Month, providerId, total_records_in_table);
            return;
        }


        int total_count = total_records_in_table / records_to_sync_at_a_time;

        for (int start = 0; start <= total_count; start = start + 1) {
            if ((total_records_in_table - total_sent) <= records_to_sync_at_a_time) {
                UploadJSONworkPlanDetail(TableName, ColumnList, UniqueFields, Month, providerId, total_records_in_table);
                return;
            }

            DataClass dt = new DataClass();
             dt.setmethodname("UploadData_Sync");
            dt.settablename(TableName);
            dt.setcolumnlist(ColumnList);
            List<DataClassProperty> data = GetDataListJSONworkPlanDetail(ColumnList, TableName, UniqueFields, Month, providerId, limit_of_records, offset_settings);
            dt.setdata(data);

            Gson gson = new Gson();
            String json = gson.toJson(dt);
            String response = "";

            //Web Service(asmx)
            //------------------------------------------------------------------------------------------
            //UploadDataJSON u = new UploadDataJSON();

            //Web APIs(java)
            //------------------------------------------------------------------------------------------
            UploadJSONData u = new UploadJSONData();

            try {
                response = u.execute(json).get();

                //Process Response
                downloadClass d = new downloadClass();
                Type collType = new TypeToken<downloadClass>() {
                }.getType();
                downloadClass responseData = (downloadClass) gson.fromJson(response, collType);


                for (int i = 0; i < responseData.getdata().size(); i++) {
                    // String s ="Update " + TableName + " Set Upload='2' where " + responseData.getdata().get(i).toString();
                    Save("Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(i).toString());
                    total_sent = total_sent + 1;

                }

            } catch (Exception e) {
                //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            offset_settings = limit_of_records + offset_settings;
            // return response;
        }


    }

    //New code 10/31/2016
    //NotApproved

    public void UploadJSONworkPlanDetailNotApproved(String TableName, String ColumnList, String UniqueFields, String Month, String providerId, String status, int total_records_in_table) {
        // int total_records_in_table = this.TotalRecordCount(TableName);
        int limit_of_records = total_records_in_table;
        int offset_settings = 0;


        DataClass dt = new DataClass();
         dt.setmethodname("UploadData_Sync");
        dt.settablename(TableName);
        dt.setcolumnlist(ColumnList);
        List<DataClassProperty> data = GetDataListJSONworkPlanDetailNotApproved(ColumnList, TableName, UniqueFields, Month, providerId, status, limit_of_records, offset_settings);
        dt.setdata(data);

        Gson gson = new Gson();
        String json = gson.toJson(dt);
        String response = "";

        //Web Service(asmx)
        //------------------------------------------------------------------------------------------
        //UploadDataJSON u = new UploadDataJSON();

        //Web APIs(java)
        //------------------------------------------------------------------------------------------
        UploadJSONData u = new UploadJSONData();

        try {
            response = u.execute(json).get();

            //Process Response
            downloadClass d = new downloadClass();
            Type collType = new TypeToken<downloadClass>() {
            }.getType();
            downloadClass responseData = (downloadClass) gson.fromJson(response, collType);


            for (int i = 0; i < responseData.getdata().size(); i++) {
                // String s ="Update " + TableName + " Set Upload='2' where " + responseData.getdata().get(i).toString();
                Save("Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(i).toString());


            }

        } catch (Exception e) {
            //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public void UploadJSONworkPlanDetailNotApproved(String TableName, String ColumnList, String UniqueFields, String Month, String providerId, String status) {
        int total_records_in_table = this.TotalRecordCount(TableName);
        int records_to_sync_at_a_time = Integer.parseInt(this.ReturnSingleValue("select batchsizeup AS Total from databasesetting   WHERE tableName = '" + TableName + "'"));
        //int records_to_sync_at_a_time = Integer.parseInt(this.ReturnSingleValue("SELECT  dataLength AS Total FROM DataLengthTable  WHERE tableName = '" + TableName + "'"));
        if (records_to_sync_at_a_time <= 0) {
            records_to_sync_at_a_time = 20;
        }
        int limit_of_records = records_to_sync_at_a_time;
        int offset_settings = 0;
        int total_sent = 0;

        if (total_records_in_table == 0)
            return;

        if (total_records_in_table <= records_to_sync_at_a_time) {
            UploadJSONworkPlanDetailNotApproved(TableName, ColumnList, UniqueFields, Month, providerId, status, total_records_in_table);
            return;
        }


        int total_count = total_records_in_table / records_to_sync_at_a_time;

        for (int start = 0; start <= total_count; start = start + 1) {
            if ((total_records_in_table - total_sent) <= records_to_sync_at_a_time) {
                UploadJSONworkPlanDetailNotApproved(TableName, ColumnList, UniqueFields, Month, providerId, status, total_records_in_table);
                return;
            }

            DataClass dt = new DataClass();
             dt.setmethodname("UploadData_Sync");
            dt.settablename(TableName);
            dt.setcolumnlist(ColumnList);
            List<DataClassProperty> data = GetDataListJSONworkPlanDetailNotApproved(ColumnList, TableName, UniqueFields, Month, providerId, status, limit_of_records, offset_settings);
            dt.setdata(data);

            Gson gson = new Gson();
            String json = gson.toJson(dt);
            String response = "";

            //Web Service(asmx)
            //------------------------------------------------------------------------------------------
            //UploadDataJSON u = new UploadDataJSON();

            //Web APIs(java)
            //------------------------------------------------------------------------------------------
            UploadJSONData u = new UploadJSONData();

            try {
                response = u.execute(json).get();

                //Process Response
                downloadClass d = new downloadClass();
                Type collType = new TypeToken<downloadClass>() {
                }.getType();
                downloadClass responseData = (downloadClass) gson.fromJson(response, collType);


                for (int i = 0; i < responseData.getdata().size(); i++) {
                    // String s ="Update " + TableName + " Set Upload='2' where " + responseData.getdata().get(i).toString();
                    Save("Update " + TableName + " Set Upload='1' where " + responseData.getdata().get(i).toString());
                    total_sent = total_sent + 1;

                }

            } catch (Exception e) {
                //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            offset_settings = limit_of_records + offset_settings;
            // return response;
        }


    }

    public List<DataClassProperty> GetDataListJSONworkPlanDetailNotApproved(String VariableList, String TableName, String UniqueField, String Month, String providerId, String status, int limit_of_records, int offset_settings) {

  /*//  Save("PRAGMA journal_mode = TRUNCATE");
    Save("PRAGMA auto_vacuum = 1");
    Save("PRAGMA locking_mod = EXCLUSIVE");*/

 /*   select * from workPlanMaster A
    INNER JOIN workPlanDetail B ON A.workPlanId = B.workPlanId
    WHERE  A.month='2016-01' and A.providerId='93002'*/

        // cur_H = ReadData("Select "+ VariableList +" from "+ TableName +" s1 inner join Section2 s2 inner join Section3 s3 inner join Section4 s4 inner join Section5 s5 inner join Section6 s6 inner join Section7 s7 inner join Section8 s8 inner join Section9 s9 inner join Section10 s10  inner join Section77 s77 where s1.Upload='2' and s2.idno=s1.idno and s3.idno=s1.idno and s4.idno=s1.idno and s5.idno=s1.idno and s6.idno=s1.idno and s7.idno=s1.idno and s8.idno=s1.idno and s9.idno=s1.idno and (s10.idno=s1.idno and s10.Slno='01') and (s77.idno=s1.idno and s77.Slno='01')");
        // Cursor cur_H = ReadData("Select " + VariableList + " from " + TableName + " WHERE Upload='2' LIMIT "+ limit_of_records);
        Cursor cur_H = ReadData("Select " + VariableList + " from " + TableName + "  WHERE Upload='1' and status='" + status + "' AND substr( workPlanDate, 1, 7 )='" + Month + "' AND providerId='" + providerId + "' LIMIT " + limit_of_records); // +" OFFSET "+ offset_settings
        cur_H.moveToFirst();
        List<DataClassProperty> data = new ArrayList<DataClassProperty>();
        DataClassProperty d;

        String DataList = "";
        //String[] Count  = VariableList.toString().split(",");
        String[] Count = VariableList.toString().replace(" ", "").split(",");
        //String[] UField = UniqueField.toString().split(",");
        String[] UField = UniqueField.toString().replace(" ", "").split(",");
        int RecordCount = 0;

        String WhereClause = "";
        String VarData[];
        int varPos = 0;
        while (!cur_H.isAfterLast()) {
            //Prepare Data List
            for (int c = 0; c < Count.length; c++) {
                if (c == 0) {
                    if (cur_H.getString(c) == null)
                        DataList = "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList = "";
                    else
                        DataList = cur_H.getString(c).toString();

                } else {
                    if (cur_H.getString(c) == null)
                        DataList += "^" + "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList += "^" + "";
                    else
                        DataList += "^" + cur_H.getString(c).toString();
                }
            }

            //Prepare Where Clause
            VarData = DataList.split("\\^");
            varPos = 0;


            for (int j = 0; j < UField.length; j++) {
                varPos = VarPosition(UField[j].toString(), Count);
                if (j == 0) {
                    WhereClause = "\"" + UField[j].toString() + "\"=" + "'" + VarData[varPos].toString() + "'";
                } else {
                    WhereClause += " and \"" + UField[j].toString() + "\"=" + "'" + VarData[varPos].toString() + "'";
                }
            }

            d = new DataClassProperty();
            d.setdatalist(DataList);
            d.setuniquefieldwithdata(WhereClause);
            data.add(d);

            RecordCount += 1;
            cur_H.moveToNext();
        }
        cur_H.close();

        return data;
    }


    //New add code by nisan
    public List<DataClassProperty> GetDataListJSONworkPlanMaster(String VariableList, String TableName, String UniqueField, String Month, String providerId, int limit_of_records, int offset_settings) {

      /*//  Save("PRAGMA journal_mode = TRUNCATE");
        Save("PRAGMA auto_vacuum = 1");
        Save("PRAGMA locking_mod = EXCLUSIVE");*/

     /*   select * from workPlanMaster A
        INNER JOIN workPlanDetail B ON A.workPlanId = B.workPlanId
        WHERE  A.month='2016-01' and A.providerId='93002'*/

        // cur_H = ReadData("Select "+ VariableList +" from "+ TableName +" s1 inner join Section2 s2 inner join Section3 s3 inner join Section4 s4 inner join Section5 s5 inner join Section6 s6 inner join Section7 s7 inner join Section8 s8 inner join Section9 s9 inner join Section10 s10  inner join Section77 s77 where s1.Upload='2' and s2.idno=s1.idno and s3.idno=s1.idno and s4.idno=s1.idno and s5.idno=s1.idno and s6.idno=s1.idno and s7.idno=s1.idno and s8.idno=s1.idno and s9.idno=s1.idno and (s10.idno=s1.idno and s10.Slno='01') and (s77.idno=s1.idno and s77.Slno='01')");
        // Cursor cur_H = ReadData("Select " + VariableList + " from " + TableName + " WHERE Upload='2' LIMIT "+ limit_of_records);
        Cursor cur_H = ReadData("Select " + VariableList + " from " + TableName + "  WHERE Upload='2' AND month='" + Month + "' AND providerId='" + providerId + "' LIMIT " + limit_of_records); // +" OFFSET "+ offset_settings
        cur_H.moveToFirst();
        List<DataClassProperty> data = new ArrayList<DataClassProperty>();
        DataClassProperty d;

        String DataList = "";
        //String[] Count  = VariableList.toString().split(",");
        String[] Count = VariableList.toString().replace(" ", "").split(",");
        //String[] UField = UniqueField.toString().split(",");
        String[] UField = UniqueField.toString().replace(" ", "").split(",");
        int RecordCount = 0;

        String WhereClause = "";
        String VarData[];
        int varPos = 0;
        while (!cur_H.isAfterLast()) {
            //Prepare Data List
            for (int c = 0; c < Count.length; c++) {
                if (c == 0) {
                    if (cur_H.getString(c) == null)
                        DataList = "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList = "";
                    else
                        DataList = cur_H.getString(c).toString();

                } else {
                    if (cur_H.getString(c) == null)
                        DataList += "^" + "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList += "^" + "";
                    else
                        DataList += "^" + cur_H.getString(c).toString();
                }
            }

            //Prepare Where Clause
            VarData = DataList.split("\\^");
            varPos = 0;


            for (int j = 0; j < UField.length; j++) {
                varPos = VarPosition(UField[j].toString(), Count);
                if (j == 0) {
                    WhereClause = "\"" + UField[j].toString() + "\"=" + "'" + VarData[varPos].toString() + "'";
                } else {
                    WhereClause += " and \"" + UField[j].toString() + "\"=" + "'" + VarData[varPos].toString() + "'";
                }
            }

            d = new DataClassProperty();
            d.setdatalist(DataList);
            d.setuniquefieldwithdata(WhereClause);
            data.add(d);

            RecordCount += 1;
            cur_H.moveToNext();
        }
        cur_H.close();

        return data;
    }

    public List<DataClassProperty> GetDataListJSONworkPlanDetail(String VariableList, String TableName, String UniqueField, String Month, String providerId, int limit_of_records, int offset_settings) {

      /*//  Save("PRAGMA journal_mode = TRUNCATE");
        Save("PRAGMA auto_vacuum = 1");
        Save("PRAGMA locking_mod = EXCLUSIVE");*/

     /*   select * from workPlanMaster A
        INNER JOIN workPlanDetail B ON A.workPlanId = B.workPlanId
        WHERE  A.month='2016-01' and A.providerId='93002'*/

        // cur_H = ReadData("Select "+ VariableList +" from "+ TableName +" s1 inner join Section2 s2 inner join Section3 s3 inner join Section4 s4 inner join Section5 s5 inner join Section6 s6 inner join Section7 s7 inner join Section8 s8 inner join Section9 s9 inner join Section10 s10  inner join Section77 s77 where s1.Upload='2' and s2.idno=s1.idno and s3.idno=s1.idno and s4.idno=s1.idno and s5.idno=s1.idno and s6.idno=s1.idno and s7.idno=s1.idno and s8.idno=s1.idno and s9.idno=s1.idno and (s10.idno=s1.idno and s10.Slno='01') and (s77.idno=s1.idno and s77.Slno='01')");
        // Cursor cur_H = ReadData("Select " + VariableList + " from " + TableName + " WHERE Upload='2' LIMIT "+ limit_of_records);
        Cursor cur_H = ReadData("Select " + VariableList + " from " + TableName + "  WHERE Upload='2' AND substr( workPlanDate, 1, 7 )='" + Month + "' AND providerId='" + providerId + "' LIMIT " + limit_of_records); // +" OFFSET "+ offset_settings
        cur_H.moveToFirst();
        List<DataClassProperty> data = new ArrayList<DataClassProperty>();
        DataClassProperty d;

        String DataList = "";
        //String[] Count  = VariableList.toString().split(",");
        String[] Count = VariableList.toString().replace(" ", "").split(",");
        //String[] UField = UniqueField.toString().split(",");
        String[] UField = UniqueField.toString().replace(" ", "").split(",");
        int RecordCount = 0;

        String WhereClause = "";
        String VarData[];
        int varPos = 0;
        while (!cur_H.isAfterLast()) {
            //Prepare Data List
            for (int c = 0; c < Count.length; c++) {
                if (c == 0) {
                    if (cur_H.getString(c) == null)
                        DataList = "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList = "";
                    else
                        DataList = cur_H.getString(c).toString();

                } else {
                    if (cur_H.getString(c) == null)
                        DataList += "^" + "";
                    else if (cur_H.getString(c).equals("null"))
                        DataList += "^" + "";
                    else
                        DataList += "^" + cur_H.getString(c).toString();
                }
            }

            //Prepare Where Clause
            VarData = DataList.split("\\^");
            varPos = 0;


            for (int j = 0; j < UField.length; j++) {
                varPos = VarPosition(UField[j].toString(), Count);
                if (j == 0) {
                    WhereClause = "\"" + UField[j].toString() + "\"=" + "'" + VarData[varPos].toString() + "'";
                } else {
                    WhereClause += " and \"" + UField[j].toString() + "\"=" + "'" + VarData[varPos].toString() + "'";
                }
            }

            d = new DataClassProperty();
            d.setdatalist(DataList);
            d.setuniquefieldwithdata(WhereClause);
            data.add(d);

            RecordCount += 1;
            cur_H.moveToNext();
        }
        cur_H.close();

        return data;
    }


    public void ExecuteSQLFromFile(String fileName) {
        List<String> dataList = Global.ReadTextFile(fileName);
        for (int i = 0; i < dataList.size(); i++) {
            Save(dataList.get(i));
        }
    }

    //**********************************************************************************************
    //PRS Sync: 15 June 2016
    //**********************************************************************************************
  /*  public static void Sync_PRS(String Dist,String Upz,String ProvType,String ProvCode,int BatchSize)
    {
        Connection C = new Connection(ud_context);
        String SQLStr = "";
        String Res="";

        /////////////////Start Download HouseHold,Member,SES,Visits based on with FWAUnit and Ward//////////////////
        //update by Nisan: 22 11 2016
        List<String> VList = C.VillageList();
        if(Global.ProjectType.equalsIgnoreCase("FWAUnitWise")) {
            //For FWA and HA
            //******************************************************************************************************************************
            if (ProvType.equalsIgnoreCase("3")) {
                SQLStr = "update Village \n" +
                        "set fwaunit=\n" +
                        "(select p.fwaunit from ProviderArea p\n" +
                        "where Village.ZILLAID=p.zillaid and Village.UPAZILAID=p.upazilaid and \n" +
                        "Village.UNIONID=p.unionid and  Village.MOUZAID=p.mouzaid and  Village.VILLAGEID=p.villageid)";
                C.Save(SQLStr);
            } else if (ProvType.equalsIgnoreCase("2")) {
                //InsertWardToVillage();
                SQLStr = "update Village \n" +
                        "set ward=\n" +
                        "(select p.ward  from ProviderArea p\n" +
                        "where Village.ZILLAID=p.zillaid and Village.UPAZILAID=p.upazilaid and \n" +
                        "Village.UNIONID=p.unionid and  Village.MOUZAID=p.mouzaid and  Village.VILLAGEID=p.villageid)";
                C.Save(SQLStr);
            }



            //Download SES With FWAUnit with Batch
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");
                SQLStr = "Select count(*)totalRecord ";
                SQLStr += " from \"Village\" v";
                SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                if (ProvType.equalsIgnoreCase("3")) {
                    SQLStr += " inner join \"Household\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\" AND a.\"FWAUnit\" = h.\"unit\"";
                } else if (ProvType.equalsIgnoreCase("2")) {
                    SQLStr += " inner join \"Household\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\" AND cast(a.\"Ward\" as integer) = cast(h.\"wardOld\" as integer)";
                }
                SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Household", ProvCode);
                String TR = C.ReturnSingleValueJSON(SQLStr);
                int totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                //int batchSize = 500;
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {

                        //Household data
                        SQLStr = "Select distinct h.\"Dist\", h.\"Upz\", h.\"UN\",h.\"wardNew\",h.\"wardOld\",h.\"Mouza\", h.\"Vill\", h.\"PAddr\", h.\"PermaAddress\", h.\"ProvType\", h.\"ProvCode\", h.\"HHNo\", h.\"Religion\", h.\"VGFCard\",";
                        SQLStr += " h.\"subBlock\",h.\"unit\", h.\"StartTime\", h.\"EndTime\", h.\"Lat\", h.\"Lon\", h.\"UserId\", h.\"EnDt\", '1' Upload,h.\"hidDistributed\",h.\"hidDistributionDate\"";
                        SQLStr += " from \"Village\" v";
                        SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                        if (ProvType.equalsIgnoreCase("3")) {
                            SQLStr += " inner join \"Household\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\" AND a.\"FWAUnit\" = h.\"unit\"";
                        } else if (ProvType.equalsIgnoreCase("2")) {
                            SQLStr += " inner join \"Household\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\" AND cast(a.\"Ward\" as integer) = cast(h.\"wardOld\" as integer)";
                        }
                        SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Household", ProvCode);
                        SQLStr+= " limit "+ BatchSize;

                        Res = C.DownloadJSON_InsertOnly(SQLStr, "Household", "Dist, Upz, UN,wardNew,wardOld,Mouza, Vill, PAddr, PermaAddress, ProvType, ProvCode, HHNo, Religion, VGFCard,subBlock,unit, StartTime, EndTime, Lat, Lon, UserId, EnDt, Upload,hidDistributed,hidDistributionDate", "Dist, Upz, UN, Mouza, Vill, HHNo");
                    }
                }
            }

            //Member Data
            //Download SES With FWAUnit with Batch
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");

                SQLStr = " Select count(*)totalRecord from \"Village\" v";
                SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                SQLStr += " inner join \"Member\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\"";
                if (ProvType.equalsIgnoreCase("3")) {
                    SQLStr += " INNER JOIN \"Household\" hh ON h.\"Dist\" = hh.\"Dist\"  AND h.\"Upz\"  = hh.\"Upz\"  AND h.\"UN\"  = hh.\"UN\"  AND h.\"Mouza\" = hh.\"Mouza\"  AND h.\"Vill\" = hh.\"Vill\" AND h.\"HHNo\"=hh.\"HHNo\" AND cast(a.\"FWAUnit\" as integer) = cast(hh.\"unit\" as integer)";
                } else if (ProvType.equalsIgnoreCase("2")) {
                    SQLStr += " INNER JOIN \"Household\" hh ON h.\"Dist\" = hh.\"Dist\"  AND h.\"Upz\"  = hh.\"Upz\"  AND h.\"UN\"  = hh.\"UN\"  AND h.\"Mouza\" = hh.\"Mouza\"  AND h.\"Vill\" = hh.\"Vill\" AND h.\"HHNo\"=hh.\"HHNo\" AND cast(a.\"Ward\" as integer) = cast(hh.\"wardOld\" as integer)";
                }
                SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Member", ProvCode);
                String TR = C.ReturnSingleValueJSON(SQLStr);
                int totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                //int batchSize = 500;
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {

                        SQLStr = " Select h.\"Dist\", h.\"Upz\", h.\"UN\", h.\"Mouza\", h.\"Vill\", h.\"ProvType\", h.\"ProvCode\", h.\"HHNo\", h.\"SNo\", h.\"HealthID\", h.\"NameEng\", h.\"NameBang\", h.\"Rth\", h.\"HaveNID\", h.\"NID\", h.\"NIDStatus\", h.\"HaveBR\",";
                        SQLStr += " h.\"BRID\", h.\"BRIDStatus\", h.\"MobileNo1\", h.\"MobileNo2\",h.mobileyn, h.\"DOB\", h.\"Age\", h.\"DOBSource\", h.\"BPlace\", h.\"FNo\", h.\"Father\", h.\"FDontKnow\", h.\"MNo\", h.\"Mother\", h.\"MDontKnow\", h.\"Sex\", h.\"MS\", h.\"SPNO1\",";
                        SQLStr += " h.\"SPNO2\", h.\"SPNO3\", h.\"SPNO4\", h.\"ELCONo\", h.\"ELCODontKnow\", h.\"EDU\", h.\"Rel\", h.\"Nationality\", h.\"OCP\", h.\"StartTime\", h.\"EnType\", h.\"EnDate\", coalesce(h.\"ExType\", '')  AS \"ExType\", h.\"ExDate\", h.\"EndTime\", h.\"Lat\", h.\"Lon\", h.\"UserId\", h.\"EnDt\",  h.\"hidDistributed\",  h.\"hidDistributionDate\",h.\"generatedId\", '1' upload";
                        SQLStr += " from \"Village\" v";
                        SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                        SQLStr += " inner join \"Member\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\"";
                        if (ProvType.equalsIgnoreCase("3")) {
                            SQLStr += " INNER JOIN \"Household\" hh ON h.\"Dist\" = hh.\"Dist\"  AND h.\"Upz\"  = hh.\"Upz\"  AND h.\"UN\"  = hh.\"UN\"  AND h.\"Mouza\" = hh.\"Mouza\"  AND h.\"Vill\" = hh.\"Vill\" AND h.\"HHNo\"=hh.\"HHNo\" AND cast(a.\"FWAUnit\" as integer) = cast(hh.\"unit\" as integer)";
                        } else if (ProvType.equalsIgnoreCase("2")) {
                            SQLStr += " INNER JOIN \"Household\" hh ON h.\"Dist\" = hh.\"Dist\"  AND h.\"Upz\"  = hh.\"Upz\"  AND h.\"UN\"  = hh.\"UN\"  AND h.\"Mouza\" = hh.\"Mouza\"  AND h.\"Vill\" = hh.\"Vill\" AND h.\"HHNo\"=hh.\"HHNo\" AND cast(a.\"Ward\" as integer) = cast(hh.\"wardOld\" as integer)";
                        }

                        SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Member", ProvCode);
                        SQLStr += " limit " + BatchSize;

                        Res = C.DownloadJSON_InsertOnly(SQLStr, "Member", "Dist, Upz, UN, Mouza, Vill, ProvType, ProvCode, HHNo, SNo, HealthID, NameEng, NameBang, Rth, HaveNID, NID, NIDStatus, HaveBR, BRID, BRIDStatus, MobileNo1, MobileNo2,MobileYN, DOB, Age, DOBSource, BPlace, FNo, Father, FDontKnow, MNo, Mother, MDontKnow, Sex, MS, SPNO1, SPNO2, SPNO3, SPNO4, ELCONo, ELCODontKnow, EDU, Rel, Nationality, OCP, StartTime, EnType, EnDate, ExType, ExDate, EndTime, Lat, Lon, UserId, EnDt,hidDistributed,hidDistributionDate,generatedId,upload", "Dist, Upz, UN, Mouza, Vill, HHNo, SNo");
                    }
                }
            }

            //Download SES With FWAUnit with Batch
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");
                SQLStr = "Select count(*)totalRecord from \"Village\" v ";
                SQLStr += " INNER JOIN \"ProviderArea\" a on v.\"ZILLAID\" = a.zillaid ";
                SQLStr += " and v.\"UPAZILAID\" = a.upazilaid ";
                SQLStr += " and v.\"UNIONID\" = a.unionid ";
                SQLStr += " and v.\"MOUZAID\" = a.mouzaid ";
                SQLStr += " and v.\"VILLAGEID\" = a.villageid ";
                SQLStr += " INNER JOIN \"ses\" C on a.zillaid = CAST(C.\"dist\" as Integer) ";
                SQLStr += " and a.upazilaid = CAST(C.\"upz\" as Integer) ";
                SQLStr += " and a.unionid = CAST(C.\"un\" as Integer) ";
                SQLStr += " and a.mouzaid = CAST(C.\"mouza\" as Integer) ";
                SQLStr += " and a.villageid = CAST(C.\"vill\" as Integer) ";
                if (ProvType.equalsIgnoreCase("3")) {
                    //"---=========FWAUnit------------\n" +
                    SQLStr += " INNER JOIN \"Household\" h ON CAST(C.\"dist\" as Integer) = h.\"Dist\" ";
                    SQLStr += " AND CAST(C.\"upz\" as Integer)  = h.\"Upz\" ";
                    SQLStr += " AND CAST(C.\"un\" as Integer)  = h.\"UN\" ";
                    SQLStr += " AND CAST(C.\"mouza\" as Integer) = h.\"Mouza\" ";
                    SQLStr += " AND CAST(C.\"vill\" as Integer) = h.\"Vill\" ";
                    SQLStr += " AND CAST(C.\"hhNo\" as Integer)=h.\"HHNo\"";
                    SQLStr += " AND a.\"FWAUnit\" = h.\"unit\"";
                } else if (ProvType.equalsIgnoreCase("2")) {
                    // "---=========Ward------------\n" +
                    SQLStr += " INNER JOIN \"Household\" h ON CAST(C.\"dist\" as Integer) = h.\"Dist\" ";
                    SQLStr += " AND CAST(C.\"upz\" as Integer)  = h.\"Upz\" ";
                    SQLStr += " AND CAST(C.\"un\" as Integer)  = h.\"UN\" ";
                    SQLStr += " AND CAST(C.\"mouza\" as Integer) = h.\"Mouza\" ";
                    SQLStr += " AND CAST(C.\"vill\" as Integer) = h.\"Vill\" ";
                    SQLStr += " AND CAST(C.\"hhNo\" as Integer)=h.\"HHNo\"";
                    SQLStr += " AND a.\"Ward\" = h.\"wardOld\"";
                    //cast(a."Ward" as integer) = cast(h."wardOld" as integer)\
                }

                SQLStr += " where a.\"provType\" = '" + ProvType + "'";
                SQLStr += " and a.\"provCode\" = '" + ProvCode + "'";
                SQLStr += " and v.\"ZILLAID\" = '" + Dist + "'";
                SQLStr += " and v.\"UPAZILAID\" = '" + Upz + "'";
                SQLStr = " and v.\"UNIONID\" = '" + VL[2] + "'  and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";


                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "ses", ProvCode);
                String TR = C.ReturnSingleValueJSON(SQLStr);

                int totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                //int batchSize = 500;
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {

                        SQLStr = "Select distinct C.dist,C.upz,C.un,C.mouza,C.vill,C.\"provType\",C.\"provCode\",C.\"hhNo\",C.status,C.q1,C.q11,C.q2,C.q21,C.q3a,C.q3b,C.q3c,C.q3d,C.q3e,C.q3f,C.q3g,C.q3h,C.q3i,C.q3j,C.q3k,C.q3l,C.q3m,C.q3n,C.q3o,C.q3p,C.q4,C.q41,C.q5,C.q51,C.q6,C.q61,C.q7,C.q71,C.q8a,C.q8b,C.q8c,C.q8d,C.q8e,C.\"Q9\",C.\"Q10\",C.\"startTime\",C.\"endTime\",C.\"userId\",C.\"enDt\",'1' C.upload,C.\"Q11a\"";
                        SQLStr += " from \"Village\" v ";
                        SQLStr += " INNER JOIN \"ProviderArea\" a on v.\"ZILLAID\" = a.zillaid ";
                        SQLStr += " and v.\"UPAZILAID\" = a.upazilaid ";
                        SQLStr += " and v.\"UNIONID\" = a.unionid ";
                        SQLStr += " and v.\"MOUZAID\" = a.mouzaid ";
                        SQLStr += " and v.\"VILLAGEID\" = a.villageid ";
                        SQLStr += " INNER JOIN \"ses\" C on a.zillaid = CAST(C.\"dist\" as Integer) ";
                        SQLStr += " and a.upazilaid = CAST(C.\"upz\" as Integer) ";
                        SQLStr += " and a.unionid = CAST(C.\"un\" as Integer) ";
                        SQLStr += " and a.mouzaid = CAST(C.\"mouza\" as Integer) ";
                        SQLStr += " and a.villageid = CAST(C.\"vill\" as Integer) ";
                        if (ProvType.equalsIgnoreCase("3")) {
                            //"---=========FWAUnit------------\n" +
                            SQLStr += " INNER JOIN \"Household\" h ON CAST(C.\"dist\" as Integer) = h.\"Dist\" ";
                            SQLStr += " AND CAST(C.\"upz\" as Integer)  = h.\"Upz\" ";
                            SQLStr += " AND CAST(C.\"un\" as Integer)  = h.\"UN\" ";
                            SQLStr += " AND CAST(C.\"mouza\" as Integer) = h.\"Mouza\" ";
                            SQLStr += " AND CAST(C.\"vill\" as Integer) = h.\"Vill\" ";
                            SQLStr += " AND CAST(C.\"hhNo\" as Integer)=h.\"HHNo\"";
                            SQLStr += " AND a.\"FWAUnit\" = h.\"unit\"";
                        } else if (ProvType.equalsIgnoreCase("2")) {
                            // "---=========Ward------------\n" +
                            SQLStr += " INNER JOIN \"Household\" h ON CAST(C.\"dist\" as Integer) = h.\"Dist\" ";
                            SQLStr += " AND CAST(C.\"upz\" as Integer)  = h.\"Upz\" ";
                            SQLStr += " AND CAST(C.\"un\" as Integer)  = h.\"UN\" ";
                            SQLStr += " AND CAST(C.\"mouza\" as Integer) = h.\"Mouza\" ";
                            SQLStr += " AND CAST(C.\"vill\" as Integer) = h.\"Vill\" ";
                            SQLStr += " AND CAST(C.\"hhNo\" as Integer)=h.\"HHNo\"";
                            SQLStr += " AND a.\"Ward\" = h.\"wardOld\"";
                            //cast(a."Ward" as integer) = cast(h."wardOld" as integer)\
                        }

                        SQLStr += " where a.\"provType\" = '" + ProvType + "'";
                        SQLStr += " and a.\"provCode\" = '" + ProvCode + "'";
                        SQLStr += " and v.\"ZILLAID\" = '" + Dist + "'";
                        SQLStr += " and v.\"UPAZILAID\" = '" + Upz + "'";
                        SQLStr = " and v.\"UNIONID\" = '" + VL[2] + "'  and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "ses", ProvCode);
                        SQLStr += " limit " + BatchSize;

                        Res = C.DownloadJSON_InsertOnly(SQLStr, "ses", "dist, upz, un, mouza, vill, provType,provCode,hhNo,status,q1,q11,q2 ,q21, q3a,q3b,q3c,q3d, q3e, q3f,q3g,q3h, q3i, q3j, q3k, q3l, q3m, q3n, q3o, q3p, q4, q41, q5, q51, q6, q61, q7, q71, q8a, q8b, q8c, q8d, q8e, Q9, Q10,startTime, endTime, userId, enDt, upload,Q11a", "dist, upz, un, mouza, vill, hhNo");

                    }
                }
            }

            //Visits Data
            //Download SES With FWAUnit with Batch
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");
                SQLStr = " Select count(*)totalRecord ";
                SQLStr += " from \"Village\" v";
                SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                SQLStr += " inner join \"visits\" h on a.zillaid=h.dist and a.upazilaid=h.upz and a.unionid=h.un and a.mouzaid=h.mouza and a.villageid=h.vill";
                SQLStr += " INNER JOIN \"Household\" hh ON h.\"dist\" = hh.\"Dist\"  AND h.\"upz\"  = hh.\"Upz\"  AND h.\"un\"  = hh.\"UN\"  AND h.\"mouza\" = hh.\"Mouza\"  AND h.\"vill\" = hh.\"Vill\" AND h.\"hhNo\"=hh.\"HHNo\" AND  a.\"FWAUnit\" = hh.\"unit\"";
                SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Visits", ProvCode);
                String TR = C.ReturnSingleValueJSON(SQLStr);
                int totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                //int batchSize = 500;
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {
                        SQLStr = " Select dist, upz, un, mouza, vill, h.\"provType\", h.\"provCode\", \"hhNo\", \"vDate\", \"vStatus\", \"startTime\", \"endTime\", \"lat\", \"lon\", \"userId\", \"enDt\",'1' Upload";
                        SQLStr += " from \"Village\" v";
                        SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                        SQLStr += " inner join \"visits\" h on a.zillaid=h.dist and a.upazilaid=h.upz and a.unionid=h.un and a.mouzaid=h.mouza and a.villageid=h.vill";
                        if (ProvType.equalsIgnoreCase("3")) {
                            SQLStr += " INNER JOIN \"Household\" hh ON h.\"dist\" = hh.\"Dist\"  AND h.\"upz\"  = hh.\"Upz\"  AND h.\"un\"  = hh.\"UN\"  AND h.\"mouza\" = hh.\"Mouza\"  AND h.\"vill\" = hh.\"Vill\" AND h.\"hhNo\"=hh.\"HHNo\" AND  a.\"FWAUnit\" = hh.\"unit\"";
                        } else if (ProvType.equalsIgnoreCase("2")) {
                            SQLStr += " INNER JOIN \"Household\" hh ON h.\"dist\" = hh.\"Dist\"  AND h.\"upz\"  = hh.\"Upz\"  AND h.\"un\"  = hh.\"UN\"  AND h.\"mouza\" = hh.\"Mouza\"  AND h.\"vill\" = hh.\"Vill\" AND h.\"hhNo\"=hh.\"HHNo\" AND cast(a.\"Ward\" as integer) = cast(hh.\"wardOld\" as integer)";
                        }
                        SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "'  and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Visits", ProvCode);
                        SQLStr += " limit " + BatchSize;

                        Res = C.DownloadJSON_InsertOnly(SQLStr, "Visits", "Dist, Upz, UN, Mouza, Vill, ProvType, ProvCode, HHNo, VDate, VStatus, StartTime, EndTime, Lat, Lon, UserId, EnDt, Upload", "Dist, Upz, UN, Mouza, Vill, HHNo, VDate");
                    }
                }
            }

        }

        *//*C.Save("drop table if exists 'totalmem'");
        C.Save("drop table if exists 'headName'");

        String SQL = "Create table totalmem as";
        SQL += " select dist,upz,un,mouza,vill,hhno,count(*)totalmem from Member";
        SQL += " group by dist,upz,un,mouza,vill,hhno";

        C.Save(SQL);

        SQL = "Create table headName as";
        SQL += " select dist,upz,un,mouza,vill,provtype,provcode,hhno,nameeng headname from Member where rth='01' and length(extype)=0";
        C.Save(SQL);

        SQL = "update household set HHHead=(select headname from headname h where h.dist=household.dist and h.upz=household.upz and h.un=household.un and h.mouza=household.mouza and h.vill=household.vill and  h.hhno=household.hhno)";
        C.Save(SQL);

        SQL = "update household set TotalMem=(select totalmem from totalmem h where h.dist=household.dist and h.upz=household.upz and h.un=household.un and h.mouza=household.mouza and h.vill=household.vill  and h.hhno=household.hhno)";
        C.Save(SQL);

        C.Save("drop table if exists 'totalmem'");
        C.Save("drop table if exists 'headName'");
        C.GenerateElco();*//*

/////////////////End Download HouseHold,Member,SES,Visits based on with FWAUnit and Ward//////////////////

/////////////////Start Download ClientMap based on with FWAUnit and Ward//////////////////
        String FWAUnit = "";
        String TR = "";
        int totalRecord = 0;
        int batchSize = 0;
        FWAUnit = "Select distinct cast(\"FWAUnit\" as integer) from \"ProviderArea\" where \"provCode\"=" + ProvCode + "";
        for (int v = 0; v < VList.size(); v++) {
            String[] VL = VList.get(v).toString().split(":");
            SQLStr = "select  count(*) " +
                    " from \"ProviderArea\" p \n" +
                    " INNER JOIN \"Household\" h ON h.\"Dist\" = p.zillaid and h.\"Upz\" = p.upazilaid and h.\"UN\" = p.unionid  and h.\"Mouza\" = p.mouzaid and h.\"Vill\" = p.villageid    and h.unit =p.\"FWAUnit\"\n";
            SQLStr += " Inner Join \"Member\" m ON m.\"Dist\" = h.\"Dist\" AND m.\"Upz\" = h.\"Upz\" AND m.\"UN\" = h.\"UN\" AND m.\"Mouza\" = h.\"Mouza\" AND m.\"Vill\" = h.\"Vill\"   AND m.\"HHNo\" = h.\"HHNo\"\n";
            SQLStr += " INNER JOIN \"clientMap\" C ON m.\"HealthID\" = C.\"healthId\"\n";
            SQLStr += " where h.\"Dist\" ='" + Dist + "' and h.\"Upz\"='" + Upz + "' and h.\"UN\" ='" + VL[2] + "' and    cast (h.\"unit\"  as integer) in (" + FWAUnit + ")";
            SQLStr += "  and m.\"ExType\" = '0' and h.unit is not null";

            SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "clientMap", ProvCode);

            TR = C.ReturnSingleValueJSON(SQLStr);
            totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
            batchSize = 500;
            if (totalRecord > 0) {
                int totalBatch = (totalRecord / batchSize) + 1;
                for (int i = 0; i < totalBatch; i++) {
                    SQLStr = "select  C.\"generatedId\", C.\"name\",  C.\"age\", C.\"divisionId\", C.\"zillaId\", C.\"upazilaId\",  C.\"unionId\", C.\"mouzaId\"," +
                            "C.\"villageId\", C.\"houseGRHoldingNo\", C.\"mobileNo\",  C.\"systemEntryDate\", C.\"modifyDate\", C.\"providerId\",  C.\"healthId\",  C.\"gender\", " +
                            "C.\"fatherName\", C.\"motherName\", C.\"husbandName\", C.\"dob\", C.\"ownMobile\", C.\"epiBlock\",C.\"DOBSource\",C.\"SNo\",'1' Upload \n" +
                            " from \"ProviderArea\" p \n" +
                            " INNER JOIN \"Household\" h ON h.\"Dist\" = p.zillaid and h.\"Upz\" = p.upazilaid and h.\"UN\" = p.unionid  and h.\"Mouza\" = p.mouzaid and h.\"Vill\" = p.villageid    and h.unit =p.\"FWAUnit\"\n";
                    SQLStr += " Inner Join \"Member\" m ON m.\"Dist\" = h.\"Dist\" AND m.\"Upz\" = h.\"Upz\" AND m.\"UN\" = h.\"UN\" AND m.\"Mouza\" = h.\"Mouza\" AND m.\"Vill\" = h.\"Vill\"   AND m.\"HHNo\" = h.\"HHNo\"\n";
                    SQLStr += " INNER JOIN \"clientMap\" C ON m.\"HealthID\" = C.\"healthId\"\n";
                    SQLStr += " where h.\"Dist\" ='" + Dist + "' and h.\"Upz\"='" + Upz + "' and h.\"UN\" ='" + VL[2] + "' and    cast (h.\"unit\"  as integer) in (" + FWAUnit + ")";
                    SQLStr += "  and m.\"ExType\" = '0' and h.unit is not null";
                    SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "clientMap", ProvCode);
                    SQLStr += " limit " + batchSize;

                    C.DownloadJSON_InsertOnly(SQLStr, "clientMap", "generatedId, name,  age, divisionId, zillaId, upazilaId,  unionId, mouzaId,villageId, houseGRHoldingNo, mobileNo,  systemEntryDate, modifyDate, providerId,  healthId,  gender, fatherName, motherName, husbandName, dob, ownMobile, epiBlock,DOBSource,SNo,upload", "generatedId");


                }
            }
        }


    }*/
/*    public static void Sync_PRS_HouseHold(String Dist,String Upz,String ProvType,String ProvCode,int BatchSize) {
        Connection C = new Connection(ud_context);
        String SQLStr = "";
        String Res = "";

        /////////////////Start Download HouseHold on with FWAUnit and Ward//////////////////
        //update by Nisan: 22 11 2016
        List<String> VList = C.VillageList();
        if (Global.ProjectType.equalsIgnoreCase("FWAUnitWise")) {
            //For FWA and HA
            //******************************************************************************************************************************
            if (ProvType.equalsIgnoreCase("3")) {
                SQLStr = "update Village \n" +
                        "set fwaunit=\n" +
                        "(select p.fwaunit from ProviderArea p\n" +
                        "where Village.ZILLAID=p.zillaid and Village.UPAZILAID=p.upazilaid and \n" +
                        "Village.UNIONID=p.unionid and  Village.MOUZAID=p.mouzaid and  Village.VILLAGEID=p.villageid)";
                C.Save(SQLStr);
            } else if (ProvType.equalsIgnoreCase("2")) {
                //InsertWardToVillage();
                SQLStr = "update Village \n" +
                        "set ward=\n" +
                        "(select p.ward  from ProviderArea p\n" +
                        "where Village.ZILLAID=p.zillaid and Village.UPAZILAID=p.upazilaid and \n" +
                        "Village.UNIONID=p.unionid and  Village.MOUZAID=p.mouzaid and  Village.VILLAGEID=p.villageid)";
                C.Save(SQLStr);
            }


            //Download SES With FWAUnit with Batch
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");
                SQLStr = "Select count(*)totalRecord ";
                SQLStr += " from \"Village\" v";
                SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                if (ProvType.equalsIgnoreCase("3")) {
                    SQLStr += " inner join \"Household\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\" AND a.\"FWAUnit\" = h.\"unit\"";
                } else if (ProvType.equalsIgnoreCase("2")) {
                    SQLStr += " inner join \"Household\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\" AND cast(a.\"Ward\" as integer) = cast(h.\"wardOld\" as integer)";
                }
                SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Household", ProvCode);
                String TR = C.ReturnSingleValueJSON(SQLStr);
                int totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                //int batchSize = 500;
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {

                        //Household data
                        SQLStr = "Select distinct h.\"Dist\", h.\"Upz\", h.\"UN\",h.\"wardNew\",h.\"wardOld\",h.\"Mouza\", h.\"Vill\", h.\"PAddr\", h.\"PermaAddress\", h.\"ProvType\", h.\"ProvCode\", h.\"HHNo\", h.\"Religion\", h.\"VGFCard\",";
                        SQLStr += " h.\"subBlock\",h.\"unit\", h.\"StartTime\", h.\"EndTime\", h.\"Lat\", h.\"Lon\", h.\"UserId\", h.\"EnDt\", '1' Upload,h.\"hidDistributed\",h.\"hidDistributionDate\"";
                        SQLStr += " from \"Village\" v";
                        SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                        if (ProvType.equalsIgnoreCase("3")) {
                            SQLStr += " inner join \"Household\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\" AND a.\"FWAUnit\" = h.\"unit\"";
                        } else if (ProvType.equalsIgnoreCase("2")) {
                            SQLStr += " inner join \"Household\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\" AND cast(a.\"Ward\" as integer) = cast(h.\"wardOld\" as integer)";
                        }
                        SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Household", ProvCode);
                        SQLStr += " limit " + BatchSize;

                        Res = C.DownloadJSON_InsertOnly(SQLStr, "Household", "Dist, Upz, UN,wardNew,wardOld,Mouza, Vill, PAddr, PermaAddress, ProvType, ProvCode, HHNo, Religion, VGFCard,subBlock,unit, StartTime, EndTime, Lat, Lon, UserId, EnDt, Upload,hidDistributed,hidDistributionDate", "Dist, Upz, UN, Mouza, Vill, HHNo");
                    }
                }
            }
        }
    }

    public static void Sync_PRS_Member(String Dist,String Upz,String ProvType,String ProvCode,int BatchSize)
    {
        Connection C = new Connection(ud_context);
        String SQLStr = "";
        String Res="";

        /////////////////Start Download HouseHold,Member,SES,Visits based on with FWAUnit and Ward//////////////////
        //update by Nisan: 22 11 2016
        List<String> VList = C.VillageList();
        if(Global.ProjectType.equalsIgnoreCase("FWAUnitWise")) {
            //For FWA and HA

            //Download SES With FWAUnit with Batch

            //Member Data
            //Download SES With FWAUnit with Batch
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");

                SQLStr = " Select count(*)totalRecord from \"Village\" v";
                SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                SQLStr += " inner join \"Member\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\"";
                if (ProvType.equalsIgnoreCase("3")) {
                    SQLStr += " INNER JOIN \"Household\" hh ON h.\"Dist\" = hh.\"Dist\"  AND h.\"Upz\"  = hh.\"Upz\"  AND h.\"UN\"  = hh.\"UN\"  AND h.\"Mouza\" = hh.\"Mouza\"  AND h.\"Vill\" = hh.\"Vill\" AND h.\"HHNo\"=hh.\"HHNo\" AND cast(a.\"FWAUnit\" as integer) = cast(hh.\"unit\" as integer)";
                } else if (ProvType.equalsIgnoreCase("2")) {
                    SQLStr += " INNER JOIN \"Household\" hh ON h.\"Dist\" = hh.\"Dist\"  AND h.\"Upz\"  = hh.\"Upz\"  AND h.\"UN\"  = hh.\"UN\"  AND h.\"Mouza\" = hh.\"Mouza\"  AND h.\"Vill\" = hh.\"Vill\" AND h.\"HHNo\"=hh.\"HHNo\" AND cast(a.\"Ward\" as integer) = cast(hh.\"wardOld\" as integer)";
                }
                SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Member", ProvCode);
                String TR = C.ReturnSingleValueJSON(SQLStr);
                int totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                //int batchSize = 500;
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {

                        SQLStr = " Select h.\"Dist\", h.\"Upz\", h.\"UN\", h.\"Mouza\", h.\"Vill\", h.\"ProvType\", h.\"ProvCode\", h.\"HHNo\", h.\"SNo\", h.\"HealthID\", h.\"NameEng\", h.\"NameBang\", h.\"Rth\", h.\"HaveNID\", h.\"NID\", h.\"NIDStatus\", h.\"HaveBR\",";
                        SQLStr += " h.\"BRID\", h.\"BRIDStatus\", h.\"MobileNo1\", h.\"MobileNo2\",h.mobileyn, h.\"DOB\", h.\"Age\", h.\"DOBSource\", h.\"BPlace\", h.\"FNo\", h.\"Father\", h.\"FDontKnow\", h.\"MNo\", h.\"Mother\", h.\"MDontKnow\", h.\"Sex\", h.\"MS\", h.\"SPNO1\",";
                        SQLStr += " h.\"SPNO2\", h.\"SPNO3\", h.\"SPNO4\", h.\"ELCONo\", h.\"ELCODontKnow\", h.\"EDU\", h.\"Rel\", h.\"Nationality\", h.\"OCP\", h.\"StartTime\", h.\"EnType\", h.\"EnDate\", coalesce(h.\"ExType\", '')  AS \"ExType\", h.\"ExDate\", h.\"EndTime\", h.\"Lat\", h.\"Lon\", h.\"UserId\", h.\"EnDt\",  h.\"hidDistributed\",  h.\"hidDistributionDate\",h.\"generatedId\", '1' upload";
                        SQLStr += " from \"Village\" v";
                        SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                        SQLStr += " inner join \"Member\" h on a.zillaid=h.\"Dist\" and a.upazilaid=h.\"Upz\" and a.unionid=h.\"UN\" and a.mouzaid=h.\"Mouza\" and a.villageid=h.\"Vill\"";
                        if (ProvType.equalsIgnoreCase("3")) {
                            SQLStr += " INNER JOIN \"Household\" hh ON h.\"Dist\" = hh.\"Dist\"  AND h.\"Upz\"  = hh.\"Upz\"  AND h.\"UN\"  = hh.\"UN\"  AND h.\"Mouza\" = hh.\"Mouza\"  AND h.\"Vill\" = hh.\"Vill\" AND h.\"HHNo\"=hh.\"HHNo\" AND cast(a.\"FWAUnit\" as integer) = cast(hh.\"unit\" as integer)";
                        } else if (ProvType.equalsIgnoreCase("2")) {
                            SQLStr += " INNER JOIN \"Household\" hh ON h.\"Dist\" = hh.\"Dist\"  AND h.\"Upz\"  = hh.\"Upz\"  AND h.\"UN\"  = hh.\"UN\"  AND h.\"Mouza\" = hh.\"Mouza\"  AND h.\"Vill\" = hh.\"Vill\" AND h.\"HHNo\"=hh.\"HHNo\" AND cast(a.\"Ward\" as integer) = cast(hh.\"wardOld\" as integer)";
                        }

                        SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Member", ProvCode);
                        SQLStr += " limit " + BatchSize;

                        Res = C.DownloadJSON_InsertOnly(SQLStr, "Member", "Dist, Upz, UN, Mouza, Vill, ProvType, ProvCode, HHNo, SNo, HealthID, NameEng, NameBang, Rth, HaveNID, NID, NIDStatus, HaveBR, BRID, BRIDStatus, MobileNo1, MobileNo2,MobileYN, DOB, Age, DOBSource, BPlace, FNo, Father, FDontKnow, MNo, Mother, MDontKnow, Sex, MS, SPNO1, SPNO2, SPNO3, SPNO4, ELCONo, ELCODontKnow, EDU, Rel, Nationality, OCP, StartTime, EnType, EnDate, ExType, ExDate, EndTime, Lat, Lon, UserId, EnDt,hidDistributed,hidDistributionDate,generatedId,upload", "Dist, Upz, UN, Mouza, Vill, HHNo, SNo");
                    }
                }
            }

        }
    }

    public static void Sync_PRS_SES(String Dist,String Upz,String ProvType,String ProvCode,int BatchSize) {
        Connection C = new Connection(ud_context);
        String SQLStr = "";
        String Res = "";

        /////////////////Start Download HouseHold,Member,SES,Visits based on with FWAUnit and Ward//////////////////
        //update by Nisan: 22 11 2016
        List<String> VList = C.VillageList();
        if (Global.ProjectType.equalsIgnoreCase("FWAUnitWise")) {
            //For FWA and HA
            //******************************************************************************************************************************

            //Download SES With FWAUnit with Batch
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");
                SQLStr = "Select count(*)totalRecord from \"Village\" v ";
                SQLStr += " INNER JOIN \"ProviderArea\" a on v.\"ZILLAID\" = a.zillaid ";
                SQLStr += " and v.\"UPAZILAID\" = a.upazilaid ";
                SQLStr += " and v.\"UNIONID\" = a.unionid ";
                SQLStr += " and v.\"MOUZAID\" = a.mouzaid ";
                SQLStr += " and v.\"VILLAGEID\" = a.villageid ";
                SQLStr += " INNER JOIN \"ses\" C on a.zillaid = CAST(C.\"dist\" as Integer) ";
                SQLStr += " and a.upazilaid = CAST(C.\"upz\" as Integer) ";
                SQLStr += " and a.unionid = CAST(C.\"un\" as Integer) ";
                SQLStr += " and a.mouzaid = CAST(C.\"mouza\" as Integer) ";
                SQLStr += " and a.villageid = CAST(C.\"vill\" as Integer) ";
                if (ProvType.equalsIgnoreCase("3")) {
                    //"---=========FWAUnit------------\n" +
                    SQLStr += " INNER JOIN \"Household\" h ON CAST(C.\"dist\" as Integer) = h.\"Dist\" ";
                    SQLStr += " AND CAST(C.\"upz\" as Integer)  = h.\"Upz\" ";
                    SQLStr += " AND CAST(C.\"un\" as Integer)  = h.\"UN\" ";
                    SQLStr += " AND CAST(C.\"mouza\" as Integer) = h.\"Mouza\" ";
                    SQLStr += " AND CAST(C.\"vill\" as Integer) = h.\"Vill\" ";
                    SQLStr += " AND CAST(C.\"hhNo\" as Integer)=h.\"HHNo\"";
                    SQLStr += " AND a.\"FWAUnit\" = h.\"unit\"";
                } else if (ProvType.equalsIgnoreCase("2")) {
                    // "---=========Ward------------\n" +
                    SQLStr += " INNER JOIN \"Household\" h ON CAST(C.\"dist\" as Integer) = h.\"Dist\" ";
                    SQLStr += " AND CAST(C.\"upz\" as Integer)  = h.\"Upz\" ";
                    SQLStr += " AND CAST(C.\"un\" as Integer)  = h.\"UN\" ";
                    SQLStr += " AND CAST(C.\"mouza\" as Integer) = h.\"Mouza\" ";
                    SQLStr += " AND CAST(C.\"vill\" as Integer) = h.\"Vill\" ";
                    SQLStr += " AND CAST(C.\"hhNo\" as Integer)=h.\"HHNo\"";
                    SQLStr += " AND a.\"Ward\" = h.\"wardOld\"";
                    //cast(a."Ward" as integer) = cast(h."wardOld" as integer)\
                }

                SQLStr += " where a.\"provType\" = '" + ProvType + "'";
                SQLStr += " and a.\"provCode\" = '" + ProvCode + "'";
                SQLStr += " and v.\"ZILLAID\" = '" + Dist + "'";
                SQLStr += " and v.\"UPAZILAID\" = '" + Upz + "'";
                SQLStr += " and v.\"UNIONID\" = '" + VL[2] + "'  and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";


                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "ses", ProvCode);
                String TR = C.ReturnSingleValueJSON(SQLStr);

                int totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {

                        SQLStr = "Select distinct C.dist,C.upz,C.un,C.mouza,C.vill,C.\"provType\",C.\"provCode\",C.\"hhNo\",C.status,C.q1,C.q11,C.q2,C.q21,C.q3a,C.q3b,C.q3c,C.q3d,C.q3e,C.q3f,C.q3g,C.q3h,C.q3i,C.q3j,C.q3k,C.q3l,C.q3m,C.q3n,C.q3o,C.q3p,C.q4,C.q41,C.q5,C.q51,C.q6,C.q61,C.q7,C.q71,C.q8a,C.q8b,C.q8c,C.q8d,C.q8e,C.\"Q9\",C.\"Q10\",C.\"startTime\",C.\"endTime\",C.\"userId\",C.\"enDt\",'1' upload,C.\"Q11a\"";
                        SQLStr += " from \"Village\" v ";
                        SQLStr += " INNER JOIN \"ProviderArea\" a on v.\"ZILLAID\" = a.zillaid ";
                        SQLStr += " and v.\"UPAZILAID\" = a.upazilaid ";
                        SQLStr += " and v.\"UNIONID\" = a.unionid ";
                        SQLStr += " and v.\"MOUZAID\" = a.mouzaid ";
                        SQLStr += " and v.\"VILLAGEID\" = a.villageid ";
                        SQLStr += " INNER JOIN \"ses\" C on a.zillaid = CAST(C.\"dist\" as Integer) ";
                        SQLStr += " and a.upazilaid = CAST(C.\"upz\" as Integer) ";
                        SQLStr += " and a.unionid = CAST(C.\"un\" as Integer) ";
                        SQLStr += " and a.mouzaid = CAST(C.\"mouza\" as Integer) ";
                        SQLStr += " and a.villageid = CAST(C.\"vill\" as Integer) ";
                        if (ProvType.equalsIgnoreCase("3")) {
                            //"---=========FWAUnit------------\n" +
                            SQLStr += " INNER JOIN \"Household\" h ON CAST(C.\"dist\" as Integer) = h.\"Dist\" ";
                            SQLStr += " AND CAST(C.\"upz\" as Integer)  = h.\"Upz\" ";
                            SQLStr += " AND CAST(C.\"un\" as Integer)  = h.\"UN\" ";
                            SQLStr += " AND CAST(C.\"mouza\" as Integer) = h.\"Mouza\" ";
                            SQLStr += " AND CAST(C.\"vill\" as Integer) = h.\"Vill\" ";
                            SQLStr += " AND CAST(C.\"hhNo\" as Integer)=h.\"HHNo\"";
                            SQLStr += " AND a.\"FWAUnit\" = h.\"unit\"";
                        } else if (ProvType.equalsIgnoreCase("2")) {
                            // "---=========Ward------------\n" +
                            SQLStr += " INNER JOIN \"Household\" h ON CAST(C.\"dist\" as Integer) = h.\"Dist\" ";
                            SQLStr += " AND CAST(C.\"upz\" as Integer)  = h.\"Upz\" ";
                            SQLStr += " AND CAST(C.\"un\" as Integer)  = h.\"UN\" ";
                            SQLStr += " AND CAST(C.\"mouza\" as Integer) = h.\"Mouza\" ";
                            SQLStr += " AND CAST(C.\"vill\" as Integer) = h.\"Vill\" ";
                            SQLStr += " AND CAST(C.\"hhNo\" as Integer)=h.\"HHNo\"";
                            SQLStr += " AND a.\"Ward\" = h.\"wardOld\"";
                            //cast(a."Ward" as integer) = cast(h."wardOld" as integer)\
                        }

                        SQLStr += " where a.\"provType\" = '" + ProvType + "'";
                        SQLStr += " and a.\"provCode\" = '" + ProvCode + "'";
                        SQLStr += " and v.\"ZILLAID\" = '" + Dist + "'";
                        SQLStr += " and v.\"UPAZILAID\" = '" + Upz + "'";
                        SQLStr += " and v.\"UNIONID\" = '" + VL[2] + "'  and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "ses", ProvCode);
                        SQLStr += " limit " + BatchSize;

                        //Res = C.DownloadJSON_InsertOnly(SQLStr, "ses", "dist, upz, un, mouza, vill, provType,provCode,hhNo,status,q1,q11,q2 ,q21, q3a,q3b,q3c,q3d, q3e, q3f,q3g,q3h, q3i, q3j, q3k, q3l, q3m, q3n, q3o, q3p, q4, q41, q5, q51, q6, q61, q7, q71, q8a, q8b, q8c, q8d, q8e, Q9, Q10,startTime, endTime, userId, enDt, upload,Q11a", "dist, upz, un, mouza, vill, hhNo");

                    }
                }
            }
        }
    }

    public static void Sync_PRS_Visits(String Dist,String Upz,String ProvType,String ProvCode,int BatchSize)
    {
        Connection C = new Connection(ud_context);
        String SQLStr = "";
        String Res="";

        /////////////////Start Download HouseHold,Member,SES,Visits based on with FWAUnit and Ward//////////////////
        //update by Nisan: 22 11 2016
        List<String> VList = C.VillageList();
        if(Global.ProjectType.equalsIgnoreCase("FWAUnitWise")) {
            //For FWA and HA
            //******************************************************************************************************************************

            //Visits Data
            //Download SES With FWAUnit with Batch
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");
                SQLStr = " Select count(*)totalRecord ";
                SQLStr += " from \"Village\" v";
                SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                SQLStr += " inner join \"visits\" h on a.zillaid=h.dist and a.upazilaid=h.upz and a.unionid=h.un and a.mouzaid=h.mouza and a.villageid=h.vill";
                SQLStr += " INNER JOIN \"Household\" hh ON h.\"dist\" = hh.\"Dist\"  AND h.\"upz\"  = hh.\"Upz\"  AND h.\"un\"  = hh.\"UN\"  AND h.\"mouza\" = hh.\"Mouza\"  AND h.\"vill\" = hh.\"Vill\" AND h.\"hhNo\"=hh.\"HHNo\" AND  a.\"FWAUnit\" = hh.\"unit\"";
                SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "' and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Visits", ProvCode);
                String TR = C.ReturnSingleValueJSON(SQLStr);
                int totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {
                        SQLStr = " Select dist, upz, un, mouza, vill, h.\"provType\", h.\"provCode\", \"hhNo\", \"vDate\", \"vStatus\", \"startTime\", \"endTime\", \"lat\", \"lon\", \"userId\", \"enDt\",'1' Upload";
                        SQLStr += " from \"Village\" v";
                        SQLStr += " inner join \"ProviderArea\" a on v.\"ZILLAID\"=a.zillaid and v.\"UPAZILAID\"=a.upazilaid and v.\"UNIONID\"=a.unionid and v.\"MOUZAID\"=a.mouzaid and v.\"VILLAGEID\"=a.villageid";
                        SQLStr += " inner join \"visits\" h on a.zillaid=h.dist and a.upazilaid=h.upz and a.unionid=h.un and a.mouzaid=h.mouza and a.villageid=h.vill";
                        if (ProvType.equalsIgnoreCase("3")) {
                            SQLStr += " INNER JOIN \"Household\" hh ON h.\"dist\" = hh.\"Dist\"  AND h.\"upz\"  = hh.\"Upz\"  AND h.\"un\"  = hh.\"UN\"  AND h.\"mouza\" = hh.\"Mouza\"  AND h.\"vill\" = hh.\"Vill\" AND h.\"hhNo\"=hh.\"HHNo\" AND  a.\"FWAUnit\" = hh.\"unit\"";
                        } else if (ProvType.equalsIgnoreCase("2")) {
                            SQLStr += " INNER JOIN \"Household\" hh ON h.\"dist\" = hh.\"Dist\"  AND h.\"upz\"  = hh.\"Upz\"  AND h.\"un\"  = hh.\"UN\"  AND h.\"mouza\" = hh.\"Mouza\"  AND h.\"vill\" = hh.\"Vill\" AND h.\"hhNo\"=hh.\"HHNo\" AND cast(a.\"Ward\" as integer) = cast(hh.\"wardOld\" as integer)";
                        }
                        SQLStr += " where a.\"provType\"='" + ProvType + "' and a.\"provCode\"='" + ProvCode + "' and v.\"ZILLAID\"='" + Dist + "' and v.\"UPAZILAID\"='" + Upz + "' and v.\"UNIONID\"='" + VL[2] + "'  and v.\"MOUZAID\"='" + VL[3] + "' and v.\"VILLAGEID\"='" + VL[4] + "'";

                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "Visits", ProvCode);
                        SQLStr += " limit " + BatchSize;

                        //Res = C.DownloadJSON_InsertOnly(SQLStr, "Visits", "Dist, Upz, UN, Mouza, Vill, ProvType, ProvCode, HHNo, VDate, VStatus, StartTime, EndTime, Lat, Lon, UserId, EnDt, Upload", "Dist, Upz, UN, Mouza, Vill, HHNo, VDate");
                    }
                }
            }

        }
    }

    public static void Sync_PRS_ClientMap(String Dist,String Upz,String ProvType,String ProvCode,int BatchSize)
    {
        Connection C = new Connection(ud_context);
        String SQLStr = "";
        String Res="";

        /////////////////Start Download HouseHold,Member,SES,Visits based on with FWAUnit and Ward//////////////////
        //update by Nisan: 22 11 2016
        List<String> VList = C.VillageList();
        if(Global.ProjectType.equalsIgnoreCase("FWAUnitWise")) {
            //For FWA and HA
            //******************************************************************************************************************************

/////////////////Start Download ClientMap based on with FWAUnit and Ward//////////////////
            String FWAUnit = "";
            String TR = "";
            int totalRecord = 0;
            int batchSize = 0;
            FWAUnit = "Select distinct cast(\"FWAUnit\" as integer) from \"ProviderArea\" where \"provCode\"=" + ProvCode + "";
            for (int v = 0; v < VList.size(); v++) {
                String[] VL = VList.get(v).toString().split(":");
                SQLStr = "select  count(*) " +
                        " from \"ProviderArea\" p \n" +
                        " INNER JOIN \"Household\" h ON h.\"Dist\" = p.zillaid and h.\"Upz\" = p.upazilaid and h.\"UN\" = p.unionid  and h.\"Mouza\" = p.mouzaid and h.\"Vill\" = p.villageid    and h.unit =p.\"FWAUnit\"\n";
                SQLStr += " Inner Join \"Member\" m ON m.\"Dist\" = h.\"Dist\" AND m.\"Upz\" = h.\"Upz\" AND m.\"UN\" = h.\"UN\" AND m.\"Mouza\" = h.\"Mouza\" AND m.\"Vill\" = h.\"Vill\"   AND m.\"HHNo\" = h.\"HHNo\"\n";
                SQLStr += " INNER JOIN \"clientMap\" C ON m.\"HealthID\" = C.\"healthId\"\n";
                SQLStr += " where h.\"Dist\" ='" + Dist + "' and h.\"Upz\"='" + Upz + "' and h.\"UN\" ='" + VL[2] + "' and    cast (h.\"unit\"  as integer) in (" + FWAUnit + ")";
                SQLStr += "  and m.\"ExType\" = '0' and h.unit is not null";

                SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "clientMap", ProvCode);

                TR = C.ReturnSingleValueJSON(SQLStr);
                totalRecord = Integer.valueOf(TR.length() == 0 ? "0" : TR);
                if (totalRecord > 0) {
                    int totalBatch = (totalRecord / BatchSize) + 1;
                    for (int i = 0; i < totalBatch; i++) {
                        SQLStr = "select  C.\"generatedId\", C.\"name\",  C.\"age\", C.\"divisionId\", C.\"zillaId\", C.\"upazilaId\",  C.\"unionId\", C.\"mouzaId\"," +
                                "C.\"villageId\", C.\"houseGRHoldingNo\", C.\"mobileNo\",  C.\"systemEntryDate\", C.\"modifyDate\", C.\"providerId\",  C.\"healthId\",  C.\"gender\", " +
                                "C.\"fatherName\", C.\"motherName\", C.\"husbandName\", C.\"dob\", C.\"ownMobile\", C.\"epiBlock\",C.\"DOBSource\",C.\"SNo\",'1' Upload \n" +
                                " from \"ProviderArea\" p \n" +
                                " INNER JOIN \"Household\" h ON h.\"Dist\" = p.zillaid and h.\"Upz\" = p.upazilaid and h.\"UN\" = p.unionid  and h.\"Mouza\" = p.mouzaid and h.\"Vill\" = p.villageid    and h.unit =p.\"FWAUnit\"\n";
                        SQLStr += " Inner Join \"Member\" m ON m.\"Dist\" = h.\"Dist\" AND m.\"Upz\" = h.\"Upz\" AND m.\"UN\" = h.\"UN\" AND m.\"Mouza\" = h.\"Mouza\" AND m.\"Vill\" = h.\"Vill\"   AND m.\"HHNo\" = h.\"HHNo\"\n";
                        SQLStr += " INNER JOIN \"clientMap\" C ON m.\"HealthID\" = C.\"healthId\"\n";
                        SQLStr += " where h.\"Dist\" ='" + Dist + "' and h.\"Upz\"='" + Upz + "' and h.\"UN\" ='" + VL[2] + "' and    cast (h.\"unit\"  as integer) in (" + FWAUnit + ")";
                        SQLStr += "  and m.\"ExType\" = '0' and h.unit is not null";
                        SQLStr = C.JoinQueryForAppropriateRecords(SQLStr, "clientMap", ProvCode);
                        SQLStr += " limit " + batchSize;

                        //C.DownloadJSON_InsertOnly(SQLStr, "clientMap", "generatedId, name,  age, divisionId, zillaId, upazilaId,  unionId, mouzaId,villageId, houseGRHoldingNo, mobileNo,  systemEntryDate, modifyDate, providerId,  healthId,  gender, fatherName, motherName, husbandName, dob, ownMobile, epiBlock,DOBSource,SNo,upload", "generatedId");


                    }
                }
            }

        }
    }*/

    //**********************************************************************************************
    //MasterTables Sync: 15 June 2016
    //**********************************************************************************************
    public static void Sync_MasterTables(String ProvCode)
    {
        Connection C = new Connection(ud_context);
        String SQLStr = "";
        String Res = "";


        //Download OCP Code List
        SQLStr = "select \"ocpCode\", \"ocpName\", \"dCode\", \"upz\", \"ocpSequence\" from \"ocpList\" order by \"ocpSequence\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "ocplist", "ocpCode, ocpName, DCode, Upz, ocpSequence", "ocpCode");


        SQLStr = "SELECT * FROM \"BrandMethod\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "BrandMethod", "brandCode, brandName", "brandCode");


        SQLStr = "SELECT * FROM \"AttendantDesignation\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "AttendantDesignation", "attendantCode, attendantDesig", "attendantCode");


        //CodeList
            /*SQLStr = "SELECT * FROM \"CodeList\"";
            Res = DownloadJSON(SQLStr, "CodeList", "TypeName, Code, CName", "TypeName, Code");*/


        //ProviderType
        SQLStr = "SELECT * FROM \"ProviderType\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "ProviderType", "ProvType, TypeName", "TypeName");

        //FWA Unit

        SQLStr = "select \"UCode\",\"UName\",\"UNameBan\" from \"FWAUnit\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "FWAUnit", "UCode,UName,UNameBan", "UCode");



        //Block
        SQLStr = "select \"BCode\",\"BName\",\"BNameBan\" from \"HABlock\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "HABlock", "BCode,BName,BNameBan", "BCode");


        //item
        SQLStr = "select \"itemCode\",\"itemName\", \"brand\", \"unit\", \"status\" from item";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "item", "itemCode, itemName, brand, unit, status", "itemCode");


        //currentStock
        SQLStr = "select \"providerId\",\"itemCode\",\"stockQty\", \"systemEntryDate\", \"modifyDate\", \"upload\" from \"currentStock\" where \"providerId\"='" + ProvCode + "'";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "currentStock", "providerId, itemCode, stockQty, systemEntryDate, modifyDate, upload", "providerId,itemCode");


        //classfication
        SQLStr = "select \"classficationCode\", \"classficationName\" from classfication";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "classfication", "classficationCode, classficationName", "classficationCode");


        //symtom
        SQLStr = "select \"symtomCode\", \"symtomDes\" from symtom";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "symtom", "symtomCode, symtomDes", "symtomCode");


        //treatment
        SQLStr = "select \"treatmentCode\", \"tretmentDes\" from treatment";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "treatment", "treatmentCode, tretmentDes", "treatmentCode");


        //adoSymtom
        SQLStr = "select \"problemCode\", \"problemDes\" from \"adoSymtom\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "adoSymtom", "problemCode, problemDes", "problemCode");


        SQLStr = "select * from \"immunization\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "immunization", "imuCode, imuName, numOfDose,forChild,forWoman", "imuCode");


        //ElcoEvent
        SQLStr = "select \"EVCode\", \"EVName\" from \"ElcoEvent\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "ElcoEvent", "EVCode, EVName", "EVCode");


        //deathReason
        SQLStr = "select \"deathReasonId\", \"code\", \"detail\" from \"deathReason\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "deathReason", "deathReasonId, code, detail", "deathReasonId");


        SQLStr = "SELECT * FROM \"fpaItem\"";
        Res = C.DownloadJSON_InsertOnly(SQLStr, "fpaItem", "itemCode, itemDes,type", "itemCode,type");


        SQLStr = "SELECT * FROM \"month\"";
        Res = C.DownloadJSON(SQLStr, "month", "id,mname", "id");
        //ccInfo

        //SQLStr = "SELECT * FROM \"ccInfo\"";
        //  Res = C.DownloadJSON_InsertOnly(SQLStr, "ccInfo", "zilaid, upazilaid, unionid, unionname, wardid, ward, ccid, ccname, hprovdername, mobailno", "ccid");

    }


    //**********************************************************************************************
    //HealthID Sync: 18 Dec 2016
    //**********************************************************************************************
    public static void Sync_HealthID(String PType, String PCode)
    {
        Connection C = new Connection(ud_context);
        String SQL = "";

        //Trigger for updating client map
        /*SQL = "create trigger if not exists tri_hid_update after";
        SQL += " update on Member";
        SQL += " for each row";
        SQL += " when (New.HealthId<>Old.HealthId)";
        SQL += " begin";
        SQL += "    Update ClientMap set HealthId=New.HealthId where HealthId=Old.HealthId;";
        SQL += " end";*/

        C.Save("drop trigger if exists tri_hid_update");
        SQL = "Create trigger if not exists tri_hid_update";
        SQL += " after update on Member";
        SQL += " for each row";
        SQL += " when (New.HealthId<>Old.HealthId)";
        SQL += " begin";
        SQL += " Update ClientMap set HealthId=New.HealthId where";
        SQL += "     HealthId=Old.HealthId and";
        SQL += "     zillaid = Old.Dist and";
        SQL += "     upazilaid = Old.Upz and";
        SQL += "     unionid = Old.Un and";
        SQL += "     mouzaid = Old.Mouza and";
        SQL += "     villageid = Old.Vill and";
        SQL += "     houseGRHoldingNo = Old.HHNo and";
        SQL += "     SNo = Old.SNo;";
        SQL += " end";
        C.Save(SQL);

        //New Trigger will execute  when name and DOB are same in both member and clientMap table
       /* C.Save("drop trigger if exists tri_hid_update_basedOn_name_and_dob");
        SQL = "Create trigger if not exists tri_hid_update_basedOn_name_and_dob";
        SQL += " after update on Member";
        SQL += " for each row";
        SQL += " when (name = Old . NameEng AND date(dob)=date(Old.DOB))";
        SQL += " begin";
        SQL += " Update ClientMap set HealthId=New.HealthId,upload=2 where";
        SQL += " name = Old . NameEng AND";
        SQL += " zillaid = Old.Dist and";
        SQL += " upazilaid = Old.Upz and";
        SQL += " unionid = Old.Un and";
        SQL += " mouzaid = Old.Mouza and";
        SQL += " villageid = Old.Vill and";
        SQL += " houseGRHoldingNo = Old.HHNo";
        SQL += " date(dob)=date(Old.DOB);";
        SQL += " end";
        C.Save(SQL);*/

        //Total number of records need to download
        SQL = "Select count(*)totalRecord from \"Member\" as t";
        SQL += " inner join \"ProviderArea\" a on t.\"Dist\"=a.\"zillaid\" and t.\"Upz\"=a.\"upazilaid\" and t.\"UN\"=a.\"unionid\" and t.\"Mouza\"=a.\"mouzaid\" and t.\"Vill\"=a.\"villageid\" and a.\"provType\"='" + PType + "' and a.\"provCode\"='" + PCode + "'";
        SQL += " where not exists(select * from data_sync_hid where";
        SQL += " \"tableName\"  = 'Member' and";
        SQL += " to_date(\"modifyDate\",'yyyy-mm-dd') = to_date(t.\"EnDt\",'yyyy-mm-dd') and";
        SQL += " \"recordId\" = cast(\"Dist\" as text)||cast(\"Upz\" as text)||cast(\"UN\" as text)||cast(\"Mouza\" as text)||cast(\"Vill\" as text)||cast(\"HHNo\" as text)||cast(\"SNo\" as text)";
        SQL += " and \"provType\"='" + PType + "' and \"provCode\"='" + PCode + "')";

        String TR =C.ReturnSingleValueJSON(SQL);
        int totalRecord = Integer.valueOf(TR.length()==0?"0":TR);

        int batchSize = 500;
        int totalBatch = (totalRecord/batchSize)+1;
        for(int i = 0; i < totalBatch; i++) {
            SQL = "Select \"Dist\", \"Upz\", \"UN\", \"Mouza\", \"Vill\", \"HHNo\", \"SNo\",\"HealthID\",to_date(\"EnDt\",'yyyy-mm-dd')modifyDate from \"Member\" as t";
            SQL += " inner join \"ProviderArea\" a on t.\"Dist\"=a.\"zillaid\" and t.\"Upz\"=a.\"upazilaid\" and t.\"UN\"=a.\"unionid\" and t.\"Mouza\"=a.\"mouzaid\" and t.\"Vill\"=a.\"villageid\" and a.\"provType\"='" + PType + "' and a.\"provCode\"='" + PCode + "'";
            SQL += " where not exists(select * from data_sync_hid where";
            SQL += " \"tableName\"  = 'Member' and";
            SQL += " to_date(\"modifyDate\",'yyyy-mm-dd') = to_date(t.\"EnDt\",'yyyy-mm-dd') and";
            SQL += " \"recordId\" = cast(\"Dist\" as text)||cast(\"Upz\" as text)||cast(\"UN\" as text)||cast(\"Mouza\" as text)||cast(\"Vill\" as text)||cast(\"HHNo\" as text)||cast(\"SNo\" as text)";
            SQL += " and \"provType\"='" + PType + "' and \"provCode\"='" + PCode + "') limit " + batchSize;

            C.DownloadJSON_HealthID(SQL, "Member", "Dist, Upz, UN, Mouza, Vill, HHNo, SNo, HealthID, modifyDate", "Dist, Upz, UN, Mouza, Vill, HHNo, SNo");
        }
        //finally drop trigger from database
        //C.Save("drop trigger if exists tri_hid_update");
    }


    //Download Data from Server
    public String DownloadJSON_HealthID(String SQL, String TableName, String ColumnList, String UniqueField) {
        DownloadRequestClass dr = new DownloadRequestClass();
        dr.setmethodname("DownloadData");
        dr.setSQL(SQL);
        Gson gson = new Gson();
        String json = gson.toJson(dr);

        String WhereClause = "";
        int varPos = 0;

        String response = "";
        String resp = "";
        String IDNO = "";
        String OldHID="";//select HealthId from  Member Where " + WhereClause;
        String OLD_HID="";//ReturnSingleValue(OldHID);
        String ClientSQL = "";
        //SQLiteDatabase database = this.getWritableDatabase();
        try {
            //For Web Api
            //--------------------------------------------------------------------------------------
            response = new DownloadJSONData().execute(json).get();
            downloadClass responseData = (downloadClass) gson.fromJson(response, downloadClass.class);
            //--------------------------------------------------------------------------------------
            String UField[] = UniqueField.toString().replace(" ", "").split(",");
            String VarList[] = ColumnList.toString().replace(" ", "").split(",");
            String InsertSQL = "";

            List<String> dataStatus = new ArrayList<String>();
            List<DataClassProperty> data = new ArrayList<DataClassProperty>();
            DataClassProperty d;
            String DataList = "";

            String modify_Date = "";
            if (responseData != null) {
                //database.beginTransaction();
                for (int i = 0; i < responseData.getdata().size(); i++) {
                    String VarData[] = split(responseData.getdata().get(i).toString(), '^');

                    //Generate where clause
                    SQL = "";
                    WhereClause = "";
                    varPos = 0;
                    for (int j = 0; j < UField.length; j++) {
                        varPos = VarPosition(UField[j].toString(), VarList);
                        if (j == 0) {
                            WhereClause = UField[j].toString() + "=" + "'" + VarData[varPos].toString() + "'";
                            IDNO += VarData[varPos].toString();
                        } else {
                            WhereClause += " and " + UField[j].toString() + "=" + "'" + VarData[varPos].toString() + "'";
                            IDNO += VarData[varPos].toString();
                        }
                    }

                    //Update command
                    int hid_var_pos = VarPosition("HealthId", VarList);
                    int modifydt_var_pos = VarPosition("modifyDate", VarList);
                    modify_Date = VarData[modifydt_var_pos].toString().replace("null", "");


                    try{
                        String SQL1="Update Member set HealthId='"+ VarData[hid_var_pos].toString() +"' Where " + WhereClause;
                        Save(SQL1);
                    }catch (Exception ex){

                    }

                    DataList = TableName + "^" + IDNO + "^" + modify_Date + "^" + Global.getInstance().getProvType() + "^" + Global.getInstance().getProvCode();
                    d = new DataClassProperty();
                    d.setdatalist(DataList);
                    d.setuniquefieldwithdata("\"tableName\"='" + TableName + "' and \"recordId\"='" + IDNO + "' and \"modifyDate\"='" + modify_Date + "' and \"provCode\"='" + Global.getInstance().getProvCode() + "'");
                    data.add(d);

                    IDNO = "";
                }
                //database.setTransactionSuccessful();

                //Upload status to data_sync_management
                DataClass dt = new DataClass();
                //Insert only
                //dt.setmethodname("UploadData_Sync_Management");

                //Insert or Update
                //dt.setmethodname("UploadData_For_Sync");
                dt.setmethodname("UploadData_Sync");
                dt.settablename("data_sync_hid");
                dt.setcolumnlist("tableName,recordId,modifyDate,provType,provCode");
                dt.setdata(data);

                Gson gson1   = new Gson();
                String json1 = gson1.toJson(dt);
                String response1 = "";

                //UploadJSONData u = new UploadJSONData();

                try{
                    //response1=u.execute(json1).get();
                    response1 = new UploadJSONData().execute(json1).get();
                } catch (Exception e) {
                    //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            resp = e.getMessage();
            e.printStackTrace();
        }finally {
            //database.endTransaction();
            //database.close();
        }

        return resp;
    }

  /*  private void UploadDataForSyncManagemtnt(List<DataClassProperty> data)
    {
        //Upload status to data_sync_management
        DataClass dt = new DataClass();
        dt.setmethodname("UploadData_For_Sync");
        dt.settablename("data_sync_management");
        dt.setcolumnlist("tableName,recordId,modifyDate,provType,provCode");
        dt.setdata(data);

        Gson gson1   = new Gson();
        String json1 = gson1.toJson(dt);
        String response1 = "";

        UploadJSONData u = new UploadJSONData();

        try{
            response1=u.execute(json1).get();
        } catch (Exception e) {
            //  Toast.makeText(dbContext, "All data did not upload. Try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
*/

    public void BatchProcess(downloadClass responseData, String TABLE_NAME, String ColumnList)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        //SQLiteStatement statement = db.compileStatement(sql);
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(database, TABLE_NAME);
        try {
            database.execSQL("PRAGMA synchronous=OFF");
            database.setLockingEnabled(false);
            database.beginTransaction();
            for (int i = 0; i < responseData.getdata().size(); i++) {
                ih.prepareForInsert();

                //ih.bind(nameColumn, Members.get(i));

                ih.execute();
            }

                /*for (int i = 0; i < Members.size(); i++) {
                    ih.prepareForInsert();

                    ih.bind(nameColumn, Members.get(i));

                    ih.execute();
                }*/
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
            database.setLockingEnabled(true);
            database.execSQL("PRAGMA synchronous=NORMAL");
            ih.close();
                /*if (Globals.ENABLE_LOGGING) {
                    final long endtime = System.currentTimeMillis();
                    Log.i("Time to insert Members: ", String.valueOf(endtime - startTime));
                }*/
        }
    }

}
