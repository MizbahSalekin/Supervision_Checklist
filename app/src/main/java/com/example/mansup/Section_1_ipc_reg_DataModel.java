package com.example.mansup;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;

public class Section_1_ipc_reg_DataModel {

       private String _zillaid = "";
       public String getzillaid(){
             return _zillaid;
        }
       public void setzillaid(String newValue){
             _zillaid = newValue;
        }
       private String _upazilaid = "";
       public String getupazilaid(){
             return _upazilaid;
        }
       public void setupazilaid(String newValue){
             _upazilaid = newValue;
        }
       private String _unionid = "";
       public String getunionid(){
             return _unionid;
        }
       public void setunionid(String newValue){
             _unionid = newValue;
        }

    private String _union_name = "";
    public String getunion_name(){
        return _union_name;
    }
    public void setunion_name(String newValue){
        _union_name = newValue;
    }

   private String _vill_name = "";
   public String getvill_name(){
             return _vill_name;
        }
   public void setvill_name(String newValue){
             _vill_name = newValue;
        }


    private String _slno = "";
    public String getslno(){
        return _slno;
    }
    public void setslno(String newValue){
        _slno = newValue;
    }

       private String _idno = "";
       public String getidno(){
             return _idno;
        }
       public void setidno(String newValue){
             _idno = newValue;
        }


    private String _q105 = "";
    public String getq105(){
        return _q105;
    }
    public void setq105(String newValue){
        _q105 = newValue;
    }
    private String _q106 = "";
    public String getq106(){
        return _q106;
    }
    public void setq106(String newValue){
        _q106 = newValue;
    }
    private String _q107 = "";
    public String getq107(){
        return _q107;
    }
    public void setq107(String newValue){
        _q107 = newValue;
    }

    private String _interviewer_date = "";
    public String getinterviewer_date(){
        return _interviewer_date;
    }

    public void setinterviewer_date(String newValue){
        _interviewer_date = newValue;
    }

    private String _interviewer_id = "";
    public String getinterviewer_id(){
        return _interviewer_id;
    }
    public void setinterviewer_id(String newValue){
        _interviewer_id = newValue;
    }

       private String _StartTime = "";
       public String getStartTime(){
        return _StartTime;
    }

       public void setStartTime(String newValue){
             _StartTime = newValue;
        }
       private String _EndTime = "";
       public void setEndTime(String newValue){
             _EndTime = newValue;
        }
       private String _DeviceID = "";
       public void setDeviceID(String newValue){
             _DeviceID = newValue;
        }
       private String _EntryUser = "";
       public void setEntryUser(String newValue){
             _EntryUser = newValue;
        }
       private String _Lat = "";
       public void setLat(String newValue){
             _Lat = newValue;
        }
       private String _Lon = "";
       public void setLon(String newValue){
             _Lon = newValue;
        }

     private String _EnDt = "";
     public void setEnDt(String newValue){
        _EnDt = newValue;
    }
     private String _Upload = "2";

     private String _modifyDate = "";
     public void setmodifyDate(String newValue){
        _modifyDate = newValue;
    }

       String TableName = "section_1_identifications_ipc_reg";

       public String SaveUpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
           {
                if(C.Existence("Select * from "+ TableName +"  Where idno='"+ _idno +"' "))
                   response = UpdateData(context);
                else
                   response = SaveData(context);
           }
           catch(Exception  e)
           {
                response = e.getMessage();
           }
          return response;
       }
       Connection C;

       private String SaveData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
             {

                SQL = "Insert into "+ TableName +" (zillaid,upazilaid,unionid,union_name,vill_name,slno,idno,q105,q106,q107,interviewer_id,interviewer_date,starttime,endtime,deviceid,entryuser,lat,lon,endt,upload,modifydate)Values('"+ _zillaid +"', '"+ _upazilaid +"', '"+ _unionid +"', '"+ _union_name+"', '"+ _vill_name +"', '"+ _slno+"', '"+ _idno+"', '"+_q105 +"', '"+_q106 +"', '"+_q107  +"', '"+ _interviewer_id +"', '"+ _interviewer_date  +"', '"+ _StartTime +"', '"+ _EndTime +"', '"+ _DeviceID +"', '"+ _EntryUser +"', '"+ _Lat +"', '"+ _Lon +"', '"+ _EnDt +"', '"+ _Upload +"', '"+ _modifyDate +"')";
                C.Save(SQL);
                C.close();
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }

       private String UpdateData(Context context)
       {
           String response = "";
           C = new Connection(context);
           String SQL = "";
           try
             {
                SQL = "Update "+ TableName +" Set Upload='2',modifyDate='" + _modifyDate + "' ,zillaid = '"+ _zillaid +"',upazilaid = '"+ _upazilaid +"',unionid = '"+ _unionid +"',union_name = '"+ _union_name +"',vill_name = '"+ _vill_name +"',slno = '"+ _slno+"',idno = '"+ _idno +"',q105 = '"+ _q105 +"',q106 = '"+ _q106 +"',q107 = '"+ _q107 +"',interviewer_id = '"+ _interviewer_id +"',interviewer_date = '"+ _interviewer_date +"'  Where idno='"+ _idno +"'";
                C.Save(SQL);
                C.close();
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }


       public List<Section_1_ipc_reg_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<Section_1_ipc_reg_DataModel> data = new ArrayList<Section_1_ipc_reg_DataModel>();
           Section_1_ipc_reg_DataModel d = new Section_1_ipc_reg_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               d = new Section_1_ipc_reg_DataModel();
               d._idno = cur.getString(cur.getColumnIndex("idno"));
                d._zillaid = cur.getString(cur.getColumnIndex("zillaid"));
                d._upazilaid = cur.getString(cur.getColumnIndex("upazilaid"));
                d._unionid = cur.getString(cur.getColumnIndex("unionid"));
               d._union_name = cur.getString(cur.getColumnIndex("union_name"));
               d._vill_name = cur.getString(cur.getColumnIndex("vill_name"));
               d._slno = cur.getString(cur.getColumnIndex("slno"));
               d._q105 = cur.getString(cur.getColumnIndex("q105"));
               d._q106 = cur.getString(cur.getColumnIndex("q106"));
               d._q107 = cur.getString(cur.getColumnIndex("q107"));
               d._interviewer_date = cur.getString(cur.getColumnIndex("interviewer_date"));
               d._interviewer_id = cur.getString(cur.getColumnIndex("interviewer_id"));
               data.add(d);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }
}