package com.example.mansup;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;

public class Section_1_Manager_Service_DataModel {

       private String _idno = "";
       public String getidno(){
             return _idno;
        }
       public void setidno(String newValue){
             _idno = newValue;
        }

    private String _q111 = "";
    public String getq111(){
        return _q111;
    }
    public void setq111(String newValue){
        _q111 = newValue;
    }


    private String _q112a = "";
    public String getq112a(){
        return _q112a;
    }
    public void setq112a(String newValue){
        _q112a = newValue;
    }

    private String _q112a1 = "";
    public String getq112a1(){
        return _q112a1;
    }
    public void setq112a1(String newValue){
        _q112a1 = newValue;
    }
    private String _q112a2 = "";
    public String getq112a2(){
        return _q112a2;
    }
    public void setq112a2(String newValue){
        _q112a2 = newValue;
    }

    private String _q111a = "";
    public String getq111a(){
        return _q111a;
    }
    public void setq111a(String newValue){
        _q111a = newValue;
    }

    private String _q111b = "";
    public String getq111b(){
        return _q111b;
    }
    public void setq111b(String newValue){
        _q111b = newValue;
    }

    private String _q111c = "";
    public String getq111c(){
        return _q111c;
    }
    public void setq111c(String newValue){
        _q111c = newValue;
    }

    private String _q111d = "";
    public String getq111d(){
        return _q111d;
    }
    public void setq111d(String newValue){
        _q111d = newValue;
    }

    private String _q111e = "";
    public String getq111e(){
        return _q111e;
    }
    public void setq111e(String newValue){
        _q111e = newValue;
    }

    private String _q111x = "";
    public String getq111x(){
        return _q111x;
    }
    public void setq111x(String newValue){
        _q111x = newValue;
    }

    private String _q111x1 = "";
    public String getq111x1(){
        return _q111x1;
    }
    public void setq111x1(String newValue){
        _q111x1 = newValue;
    }

  /*  private String _q112a3 = "";
    public String getq112a3(){
        return _q112a3;
    }
    public void setq112a3(String newValue){
        _q112a3 = newValue;
    }*/

    private String _q112b = "";
    public String getq112b(){
        return _q112b;
    }
    public void setq112b(String newValue){
        _q112b = newValue;
    }
    private String _q112b1 = "";
    public String getq112b1(){
        return _q112b1;
    }
    public void setq112b1(String newValue){
        _q112b1 = newValue;
    }
    private String _q112b2 = "";
    public String getq112b2(){
        return _q112b2;
    }
    public void setq112b2(String newValue){
        _q112b2 = newValue;
    }

    private String _q112p1a = "";
    public String getq112p1a(){
        return _q112p1a;
    }
    public void setq112p1a(String newValue){
        _q112p1a = newValue;
    }

    private String _q112p1b = "";
    public String getq112p1b(){
        return _q112p1b;
    }
    public void setq112p1b(String newValue){
        _q112p1b = newValue;
    }

    private String _q112p1c = "";
    public String getq112p1c(){
        return _q112p1c;
    }
    public void setq112p1c(String newValue){
        _q112p1c = newValue;
    }

    private String _q112p1d = "";
    public String getq112p1d(){
        return _q112p1d;
    }
    public void setq112p1d(String newValue){
        _q112p1d = newValue;
    }

    private String _q112p1e = "";
    public String getq112p1e(){
        return _q112p1e;
    }
    public void setq112p1e(String newValue){
        _q112p1e = newValue;
    }

    private String _q112p1x = "";
    public String getq112p1x(){
        return _q112p1x;
    }
    public void setq112p1x(String newValue){
        _q112p1x = newValue;
    }

    private String _q112p1x1 = "";
    public String getq112p1x1(){
        return _q112p1x1;
    }
    public void setq112p1x1(String newValue){
        _q112p1x1 = newValue;
    }
/*    private String _q112b3 = "";
    public String getq112b3(){
        return _q112b3;
    }
    public void setq112b3(String newValue){
        _q112b3 = newValue;
    }*/

    private String _q112c = "";
    public String getq112c(){
        return _q112c;
    }
    public void setq112c(String newValue){
        _q112c = newValue;
    }
    private String _q112c1 = "";
    public String getq112c1(){
        return _q112c1;
    }
    public void setq112c1(String newValue){
        _q112c1 = newValue;
    }
    private String _q112c2 = "";
    public String getq112c2(){
        return _q112c2;
    }
    public void setq112c2(String newValue){
        _q112c2 = newValue;
    }

    private String _q112p2a = "";
    public String getq112p2a(){
        return _q112p2a;
    }
    public void setq112p2a(String newValue){
        _q112p2a = newValue;
    }

    private String _q112p2b = "";
    public String getq112p2b(){
        return _q112p2b;
    }
    public void setq112p2b(String newValue){
        _q112p2b = newValue;
    }

    private String _q112p2c = "";
    public String getq112p2c(){
        return _q112p2c;
    }
    public void setq112p2c(String newValue){
        _q112p2c = newValue;
    }

    private String _q112p2d = "";
    public String getq112p2d(){
        return _q112p2d;
    }
    public void setq112p2d(String newValue){
        _q112p2d = newValue;
    }

    private String _q112p2e = "";
    public String getq112p2e(){
        return _q112p2e;
    }
    public void setq112p2e(String newValue){
        _q112p2e = newValue;
    }

    private String _q112p2x = "";
    public String getq112p2x(){
        return _q112p2x;
    }
    public void setq112p2x(String newValue){
        _q112p2x = newValue;
    }

    private String _q112p2x1 = "";
    public String getq112p2x1(){
        return _q112p2x1;
    }
    public void setq112p2x1(String newValue){
        _q112p2x1 = newValue;
    }
   /* private String _q112c3 = "";
    public String getq112c3(){
        return _q112c3;
    }
    public void setq112c3(String newValue){
        _q112c3 = newValue;
    }*/

    private String _q112d = "";
    public String getq112d(){
        return _q112d;
    }
    public void setq112d(String newValue){
        _q112d = newValue;
    }
    private String _q112d1 = "";
    public String getq112d1(){
        return _q112d1;
    }
    public void setq112d1(String newValue){
        _q112d1 = newValue;
    }
    private String _q112d2 = "";
    public String getq112d2(){
        return _q112d2;
    }
    public void setq112d2(String newValue){
        _q112d2 = newValue;
    }

    private String _q112p3a = "";
    public String getq112p3a(){
        return _q112p3a;
    }
    public void setq112p3a(String newValue){
        _q112p3a = newValue;
    }

    private String _q112p3b = "";
    public String getq112p3b(){
        return _q112p3b;
    }
    public void setq112p3b(String newValue){
        _q112p3b = newValue;
    }

    private String _q112p3c = "";
    public String getq112p3c(){
        return _q112p3c;
    }
    public void setq112p3c(String newValue){
        _q112p3c = newValue;
    }

    private String _q112p3d = "";
    public String getq112p3d(){
        return _q112p3d;
    }
    public void setq112p3d(String newValue){
        _q112p3d = newValue;
    }

    private String _q112p3e = "";
    public String getq112p3e(){
        return _q112p3e;
    }
    public void setq112p3e(String newValue){
        _q112p3e = newValue;
    }

    private String _q112p3x = "";
    public String getq112p3x(){
        return _q112p3x;
    }
    public void setq112p3x(String newValue){
        _q112p3x = newValue;
    }

    private String _q112p3x1 = "";
    public String getq112p3x1(){
        return _q112p3x1;
    }
    public void setq112p3x1(String newValue){
        _q112p3x1 = newValue;
    }
   /* private String _q112d3 = "";
    public String getq112d3(){
        return _q112d3;
    }
    public void setq112d3(String newValue){
        _q112d3 = newValue;
    }*/

    private String _q112e = "";
    public String getq112e(){
        return _q112e;
    }
    public void setq112e(String newValue){
        _q112e = newValue;
    }
    private String _q112e1 = "";
    public String getq112e1(){
        return _q112e1;
    }
    public void setq112e1(String newValue){
        _q112e1 = newValue;
    }
    private String _q112e2 = "";
    public String getq112e2(){
        return _q112e2;
    }
    public void setq112e2(String newValue){
        _q112e2 = newValue;
    }

   /* private String _q112e3 = "";
    public String getq112e3(){
        return _q112e3;
    }
    public void setq112e3(String newValue){
        _q112e3 = newValue;
    }*/

    private String _q112f = "";
    public String getq112f(){
        return _q112f;
    }
    public void setq112f(String newValue){
        _q112f = newValue;
    }
    private String _q112f1 = "";
    public String getq112f1(){
        return _q112f1;
    }
    public void setq112f1(String newValue){
        _q112f1 = newValue;
    }
    private String _q112f2 = "";
    public String getq112f2(){
        return _q112f2;
    }
    public void setq112f2(String newValue){
        _q112f2 = newValue;
    }

 /*   private String _q112f3 = "";
    public String getq112f3(){
        return _q112f3;
    }
    public void setq112f3(String newValue){
        _q112f3 = newValue;
    }*/

    private String _q112g = "";
    public String getq112g(){
        return _q112g;
    }
    public void setq112g(String newValue){
        _q112g = newValue;
    }
    private String _q112g1 = "";
    public String getq112g1(){
        return _q112g1;
    }
    public void setq112g1(String newValue){
        _q112g1 = newValue;
    }
    private String _q112g2 = "";
    public String getq112g2(){
        return _q112g2;
    }
    public void setq112g2(String newValue){
        _q112g2 = newValue;
    }

/*    private String _q112g3 = "";
    public String getq112g3(){
        return _q112g3;
    }
    public void setq112g3(String newValue){
        _q112g3 = newValue;
    }*/

    private String _q112h = "";
    public String getq112h(){
        return _q112h;
    }
    public void setq112h(String newValue){
        _q112h = newValue;
    }
    private String _q112h1 = "";
    public String getq112h1(){
        return _q112h1;
    }
    public void setq112h1(String newValue){
        _q112h1 = newValue;
    }
    private String _q112h2 = "";
    public String getq112h2(){
        return _q112h2;
    }
    public void setq112h2(String newValue){
        _q112h2 = newValue;
    }

  /*  private String _q112h3 = "";
    public String getq112h3(){
        return _q112h3;
    }
    public void setq112h3(String newValue){
        _q112h3 = newValue;
    }*/

    private String _q112i = "";
    public String getq112i(){
        return _q112i;
    }
    public void setq112i(String newValue){
        _q112i = newValue;
    }
    private String _q112i1 = "";
    public String getq112i1(){
        return _q112i1;
    }
    public void setq112i1(String newValue){
        _q112i1 = newValue;
    }
    private String _q112i2 = "";
    public String getq112i2(){
        return _q112i2;
    }
    public void setq112i2(String newValue){
        _q112i2 = newValue;
    }

  /*  private String _q112i3 = "";
    public String getq112i3(){
        return _q112i3;
    }
    public void setq112i3(String newValue){
        _q112i3 = newValue;
    }*/

    private String _q112j = "";
    public String getq112j(){
        return _q112j;
    }
    public void setq112j(String newValue){
        _q112j = newValue;
    }
    private String _q112j1 = "";
    public String getq112j1(){
        return _q112j1;
    }
    public void setq112j1(String newValue){
        _q112j1 = newValue;
    }
    private String _q112j2 = "";
    public String getq112j2(){
        return _q112j2;
    }
    public void setq112j2(String newValue){
        _q112j2 = newValue;
    }

   /* private String _q112j3 = "";
    public String getq112j3(){
        return _q112j3;
    }
    public void setq112j3(String newValue){
        _q112j3 = newValue;
    }*/

    private String _q112k = "";
    public String getq112k(){
        return _q112k;
    }
    public void setq112k(String newValue){
        _q112k = newValue;
    }
    private String _q112k1 = "";
    public String getq112k1(){
        return _q112k1;
    }
    public void setq112k1(String newValue){
        _q112k1 = newValue;
    }
    private String _q112k2 = "";
    public String getq112k2(){
        return _q112k2;
    }
    public void setq112k2(String newValue){
        _q112k2 = newValue;
    }

    private String _q112m1a = "";
    public String getq112m1a(){
        return _q112m1a;
    }
    public void setq112m1a(String newValue){
        _q112m1a = newValue;
    }

    private String _q112m1b = "";
    public String getq112m1b(){
        return _q112m1b;
    }
    public void setq112m1b(String newValue){
        _q112m1b = newValue;
    }

    private String _q112m1c = "";
    public String getq112m1c(){
        return _q112m1c;
    }
    public void setq112m1c(String newValue){
        _q112m1c = newValue;
    }

    private String _q112m1d = "";
    public String getq112m1d(){
        return _q112m1d;
    }
    public void setq112m1d(String newValue){
        _q112m1d = newValue;
    }

    private String _q112m1e = "";
    public String getq112m1e(){
        return _q112m1e;
    }
    public void setq112m1e(String newValue){
        _q112m1e = newValue;
    }

    private String _q112m1x = "";
    public String getq112m1x(){
        return _q112m1x;
    }
    public void setq112m1x(String newValue){
        _q112m1x = newValue;
    }

    private String _q112m1x1 = "";
    public String getq112m1x1(){
        return _q112m1x1;
    }
    public void setq112m1x1(String newValue){
        _q112m1x1 = newValue;
    }
   /* private String _q112k3 = "";
    public String getq112k3(){
        return _q112k3;
    }
    public void setq112k3(String newValue){
        _q112k3 = newValue;
    }*/

    private String _q112l = "";
    public String getq112l(){
        return _q112l;
    }
    public void setq112l(String newValue){
        _q112l = newValue;
    }
    private String _q112l1 = "";
    public String getq112l1(){
        return _q112l1;
    }
    public void setq112l1(String newValue){
        _q112l1 = newValue;
    }

    private String _q112l2 = "";
    public String getq112l2(){
        return _q112l2;
    }
    public void setq112l2(String newValue){
        _q112l2 = newValue;
    }

 private String _q113 = "";
    public String getq113(){
        return _q113;
    }
    public void setq113(String newValue){
        _q113 = newValue;
    }

    /*private String _q113a1 = "";
    public String getq113a1(){
        return _q113a1;
    }
    public void setq113a1(String newValue){
        _q113a1 = newValue;
    }
    private String _q113a2 = "";
    public String getq113a2(){
        return _q113a2;
    }
    public void setq113a2(String newValue){
        _q113a2 = newValue;
    }
    private String _q113b1 = "";
    public String getq113b1(){
        return _q113b1;
    }
    public void setq113b1(String newValue){
        _q113b1 = newValue;
    }
    private String _q113b2 = "";
    public String getq113b2(){
        return _q113b2;
    }
    public void setq113b2(String newValue){
        _q113b2 = newValue;
    }
    private String _q113c1 = "";
    public String getq113c1(){
        return _q113c1;
    }
    public void setq113c1(String newValue){
        _q113c1 = newValue;
    }
    private String _q113c2 = "";
    public String getq113c2(){
        return _q113c2;
    }
    public void setq113c2(String newValue){
        _q113c2 = newValue;
    }
    private String _q113d1 = "";
    public String getq113d1(){
        return _q113d1;
    }
    public void setq113d1(String newValue){
        _q113d1 = newValue;
    }
    private String _q113d2 = "";
    public String getq113d2(){
        return _q113d2;
    }
    public void setq113d2(String newValue){
        _q113d2 = newValue;
    }

    private String _q113ap1 = "";
    public String getq113ap1(){
        return _q113ap1;
    }
    public void setq113ap1(String newValue){
        _q113ap1 = newValue;
    }

    private String _q113ap2 = "";
    public String getq113ap2(){
        return _q113ap2;
    }
    public void setq113ap2(String newValue){
        _q113ap2 = newValue;
    }

    private String _q113ap3 = "";
    public String getq113ap3(){
        return _q113ap3;
    }
    public void setq113ap3(String newValue){
        _q113ap3 = newValue;
    }

    private String _q113ap4 = "";
    public String getq113ap4(){
        return _q113ap4;
    }
    public void setq113ap4(String newValue){
        _q113ap4 = newValue;
    }*/


    private String _StartTime = "";
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

       String TableName = "section_1_manager_staff_service";

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
                SQL = "Insert into "+ TableName +" (idno,q111,q111a,q111b,q111c,q111d,q111e,q111x,q111x1,q112a,q112a1,q112a2,q112b,q112b1,q112b2,q112c,q112c1,q112c2,q112d,q112d1,q112d2,q112e,q112e1,q112e2,q112f,q112f1,q112f2,q112g,q112g1,q112g2,q112h,q112h1,q112h2,q112i,q112i1,q112i2,q112j,q112j1,q112j2,q112k,q112k1,q112k2,q112l,q112l1,q112l2,q113,q112p1a,q112p1b,q112p1c,q112p1d,q112p1e,q112p1x,q112p1x1,q112p2a,q112p2b,q112p2c,q112p2d,q112p2e,q112p2x,q112p2x1,q112p3a,q112p3b,q112p3c,q112p3d,q112p3e,q112p3x,q112p3x1,q112m1a,q112m1b,q112m1c,q112m1d,q112m1e,q112m1x,q112m1x1,StartTime,EndTime,DeviceID,EntryUser,Lat,Lon,EnDt,Upload,modifyDate)Values('"+ _idno +"', '"+ _q111+"', '"+ _q111a+"', '"+ _q111b+"', '"+ _q111c+"', '"+ _q111d+"', '"+ _q111e+"', '"+ _q111x +"', '"+ _q111x1+"', '"+ _q112a +"', '"+ _q112a1 +"', '"+ _q112a2  +"', '"+ _q112b +"', '"+ _q112b1 +"', '"+ _q112b2  +"', '"+ _q112c +"', '"+ _q112c1 +"', '"+ _q112c2 +"', '"+ _q112d +"', '"+ _q112d1 +"', '"+ _q112d2 +"', '"+ _q112e +"', '"+ _q112e1 +"', '"+ _q112e2  +"', '"+ _q112f +"', '"+ _q112f1 +"', '"+ _q112f2  +"', '"+ _q112g +"', '"+ _q112g1 +"', '"+ _q112g2 +"', '"+ _q112h +"', '"+ _q112h1 +"', '"+ _q112h2  +"', '"+ _q112i +"', '"+ _q112i1 +"', '"+ _q112i2  +"', '"+ _q112j +"', '"+ _q112j1 +"', '"+ _q112j2 +"', '"+ _q112k +"', '"+ _q112k1 +"', '"+ _q112k2 +"', '"+ _q112l +"', '"+ _q112l1 +"', '"+ _q112l2 +"', '"+ _q113+"', '"+ _q112p1a+"', '"+ _q112p1b+"', '"+ _q112p1c+"', '"+ _q112p1d+"', '"+ _q112p1e+"', '"+ _q112p1x+"', '"+ _q112p1x1+"', '"+ _q112p2a+"', '"+ _q112p2b+"', '"+ _q112p2c+"', '"+ _q112p2d+"', '"+ _q112p2e+"', '"+ _q112p2x+"', '"+ _q112p2x1+"', '"+ _q112p3a+"', '"+ _q112p3b+"', '"+ _q112p3c+"', '"+ _q112p3d+"', '"+ _q112p3e+"', '"+ _q112p3x+"', '"+ _q112p3x1+"', '"+ _q112m1a+"', '"+ _q112m1b+"', '"+ _q112m1c+"', '"+ _q112m1d+"', '"+ _q112m1e+"', '"+ _q112m1x+"', '"+ _q112m1x1+"', '"+ _StartTime +"', '"+ _EndTime +"', '"+ _DeviceID +"', '"+ _EntryUser +"', '"+ _Lat +"', '"+ _Lon +"', '"+ _EnDt +"', '"+ _Upload +"', '"+ _modifyDate +"')";
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
                SQL = "Update "+ TableName +" Set Upload='2',modifyDate='" + _modifyDate + "' ,idno = '"+ _idno  +"',q111= '"+ _q111+"',q111a = '"+ _q111a +"',q111b = '"+ _q111b+"',q111c = '"+ _q111c+"',q111d = '"+ _q111d+"',q111e = '"+ _q111e+"',q111x = '"+ _q111x+"',q111x1 = '"+ _q111x1+"',q112a = '"+ _q112a +"',q112a1 = '"+ _q112a1 +"',q112a2 = '"+ _q112a2 +"',q112b = '"+ _q112b +"',q112b1 = '"+ _q112b1 +"',q112b2 = '"+ _q112b2  +"',q112c = '"+ _q112c +"',q112c1 = '"+ _q112c1 +"',q112c2 = '"+ _q112c2 +"',q112d = '"+ _q112d +"',q112d1 = '"+ _q112d1 +"',q112d2 = '"+ _q112d2  +"',q112e = '"+ _q112e +"',q112e1 = '"+ _q112e1 +"',q112e2 = '"+ _q112e2 +"',q112f = '"+ _q112f +"',q112f1 = '"+ _q112f1 +"',q112f2 = '"+ _q112f2  +"',q112g = '"+ _q112g +"',q112g1 = '"+ _q112g1 +"',q112g2 = '"+ _q112g2  +"',q112h = '"+ _q112h +"',q112h1 = '"+ _q112h1 +"',q112h2 = '"+ _q112h2  +"',q112i = '"+ _q112i +"',q112i1 = '"+ _q112i1 +"',q112i2 = '"+ _q112i2  +"',q112j = '"+ _q112j +"',q112j1 = '"+ _q112j1 +"',q112j2 = '"+ _q112j2  +"',q112k = '"+ _q112k +"',q112k1 = '"+ _q112k1 +"',q112k2 = '"+ _q112k2  +"',q112l = '"+ _q112l +"',q112l1 = '"+ _q112l1 +"',q112l2 = '"+ _q112l2+"',q113 = '"+ _q113+"',q112p1a = '"+ _q112p1a+"',q112p1b = '"+ _q112p1b+"',q112p1c = '"+ _q112p1c+"',q112p1d = '"+ _q112p1d+"',q112p1e = '"+ _q112p1e+"',q112p1x = '"+ _q112p1x+"',q112p1x1 = '"+ _q112p1x1+"',q112p2a = '"+ _q112p2a+"',q112p2b = '"+ _q112p2b+"',q112p2c = '"+ _q112p2c+"',q112p2d = '"+ _q112p2d+"',q112p2e = '"+ _q112p2e+"',q112p2x = '"+ _q112p2x+"',q112p2x1 = '"+ _q112p2x1+"',q112p3a = '"+ _q112p3a+"',q112p3b = '"+ _q112p3b+"',q112p3c = '"+ _q112p3c+"',q112p3d = '"+ _q112p3d+"',q112p3e = '"+ _q112p3e+"',q112p3x = '"+ _q112p3x+"',q112p3x1 = '"+ _q112p3x1+"',q112m1a = '"+ _q112m1a+"',q112m1b = '"+ _q112m1b+"',q112m1c = '"+ _q112m1c+"',q112m1d = '"+ _q112m1d+"',q112m1e = '"+ _q112m1e+"',q112m1x = '"+ _q112m1x+"',q112m1x1 = '"+ _q112m1x1+"'  Where idno='"+ _idno +"'";
                C.Save(SQL);
                C.close();
             }
             catch(Exception  e)
             {
                response = e.getMessage();
             }
          return response;
       }


       public List<Section_1_Manager_Service_DataModel> SelectAll(Context context, String SQL)
       {
           Connection C = new Connection(context);
           List<Section_1_Manager_Service_DataModel> data = new ArrayList<Section_1_Manager_Service_DataModel>();
           Section_1_Manager_Service_DataModel d = new Section_1_Manager_Service_DataModel();
           Cursor cur = C.ReadData(SQL);

           cur.moveToFirst();
           while(!cur.isAfterLast())
           {
               d = new Section_1_Manager_Service_DataModel();
               d._idno = cur.getString(cur.getColumnIndex("idno"));
               d._q111 = cur.getString(cur.getColumnIndex("q111"));
               d._q111a = cur.getString(cur.getColumnIndex("q111a"));
               d._q111b = cur.getString(cur.getColumnIndex("q111b"));
               d._q111c = cur.getString(cur.getColumnIndex("q111c"));
               d._q111d = cur.getString(cur.getColumnIndex("q111d"));
               d._q111e = cur.getString(cur.getColumnIndex("q111e"));
               d._q111x = cur.getString(cur.getColumnIndex("q111x"));
               d._q111x1 = cur.getString(cur.getColumnIndex("q111x1"));
               d._q112a = cur.getString(cur.getColumnIndex("q112a"));
               d._q112a1 = cur.getString(cur.getColumnIndex("q112a1"));
               d._q112a2 = cur.getString(cur.getColumnIndex("q112a2"));
             //  d._q112a3 = cur.getString(cur.getColumnIndex("q112a3"));
               d._q112b = cur.getString(cur.getColumnIndex("q112b"));
               d._q112b1 = cur.getString(cur.getColumnIndex("q112b1"));
               d._q112b2 = cur.getString(cur.getColumnIndex("q112b2"));
              // d._q112b3 = cur.getString(cur.getColumnIndex("q112b3"));
               d._q112c = cur.getString(cur.getColumnIndex("q112c"));
               d._q112c1 = cur.getString(cur.getColumnIndex("q112c1"));
               d._q112c2 = cur.getString(cur.getColumnIndex("q112c2"));
             //  d._q112c3 = cur.getString(cur.getColumnIndex("q112c3"));
               d._q112d = cur.getString(cur.getColumnIndex("q112d"));
               d._q112d1 = cur.getString(cur.getColumnIndex("q112d1"));
               d._q112d2 = cur.getString(cur.getColumnIndex("q112d2"));
             //  d._q112d3 = cur.getString(cur.getColumnIndex("q112d3"));
               d._q112e = cur.getString(cur.getColumnIndex("q112e"));
               d._q112e1 = cur.getString(cur.getColumnIndex("q112e1"));
               d._q112e2 = cur.getString(cur.getColumnIndex("q112e2"));
            //   d._q112e3 = cur.getString(cur.getColumnIndex("q112e3"));
               d._q112f = cur.getString(cur.getColumnIndex("q112f"));
               d._q112f1 = cur.getString(cur.getColumnIndex("q112f1"));
               d._q112f2 = cur.getString(cur.getColumnIndex("q112f2"));
            //   d._q112f3 = cur.getString(cur.getColumnIndex("q112f3"));
               d._q112g = cur.getString(cur.getColumnIndex("q112g"));
               d._q112g1 = cur.getString(cur.getColumnIndex("q112g1"));
               d._q112g2 = cur.getString(cur.getColumnIndex("q112g2"));
            //   d._q112g3 = cur.getString(cur.getColumnIndex("q112g3"));
               d._q112h = cur.getString(cur.getColumnIndex("q112h"));
               d._q112h1 = cur.getString(cur.getColumnIndex("q112h1"));
               d._q112h2 = cur.getString(cur.getColumnIndex("q112h2"));
            //   d._q112h3 = cur.getString(cur.getColumnIndex("q112h3"));
               d._q112i = cur.getString(cur.getColumnIndex("q112i"));
               d._q112i1 = cur.getString(cur.getColumnIndex("q112i1"));
               d._q112i2 = cur.getString(cur.getColumnIndex("q112i2"));
             //  d._q112i3 = cur.getString(cur.getColumnIndex("q112i3"));
               d._q112j = cur.getString(cur.getColumnIndex("q112j"));
               d._q112j1 = cur.getString(cur.getColumnIndex("q112j1"));
               d._q112j2 = cur.getString(cur.getColumnIndex("q112j2"));
             //  d._q112j3 = cur.getString(cur.getColumnIndex("q112j3"));
               d._q112k = cur.getString(cur.getColumnIndex("q112k"));
               d._q112k1 = cur.getString(cur.getColumnIndex("q112k1"));
               d._q112k2 = cur.getString(cur.getColumnIndex("q112k2"));
              // d._q112k3 = cur.getString(cur.getColumnIndex("q112k3"));
               d._q112l = cur.getString(cur.getColumnIndex("q112l"));
               d._q112l1 = cur.getString(cur.getColumnIndex("q112l1"));
               d._q112l2 = cur.getString(cur.getColumnIndex("q112l2"));
               d._q113 = cur.getString(cur.getColumnIndex("q113"));

               d._q112p1a = cur.getString(cur.getColumnIndex("q112p1a"));
               d._q112p1b = cur.getString(cur.getColumnIndex("q112p1b"));
               d._q112p1c = cur.getString(cur.getColumnIndex("q112p1c"));
               d._q112p1d = cur.getString(cur.getColumnIndex("q112p1d"));
               d._q112p1e = cur.getString(cur.getColumnIndex("q112p1e"));
               d._q112p1x = cur.getString(cur.getColumnIndex("q112p1x"));
               d._q112p1x1 = cur.getString(cur.getColumnIndex("q112p1x1"));
               d._q112p2a = cur.getString(cur.getColumnIndex("q112p2a"));
               d._q112p2b = cur.getString(cur.getColumnIndex("q112p2b"));
               d._q112p2c = cur.getString(cur.getColumnIndex("q112p2c"));
               d._q112p2d = cur.getString(cur.getColumnIndex("q112p2d"));
               d._q112p2e = cur.getString(cur.getColumnIndex("q112p2e"));
               d._q112p2x = cur.getString(cur.getColumnIndex("q112p2x"));
               d._q112p2x1 = cur.getString(cur.getColumnIndex("q112p2x1"));
               d._q112p3a = cur.getString(cur.getColumnIndex("q112p3a"));
               d._q112p3b = cur.getString(cur.getColumnIndex("q112p3b"));
               d._q112p3c = cur.getString(cur.getColumnIndex("q112p3c"));
               d._q112p3d = cur.getString(cur.getColumnIndex("q112p3d"));
               d._q112p3e = cur.getString(cur.getColumnIndex("q112p3e"));
               d._q112p3x = cur.getString(cur.getColumnIndex("q112p3x"));
               d._q112p3x1 = cur.getString(cur.getColumnIndex("q112p3x1"));
               d._q112m1a = cur.getString(cur.getColumnIndex("q112m1a"));
               d._q112m1b = cur.getString(cur.getColumnIndex("q112m1b"));
               d._q112m1c = cur.getString(cur.getColumnIndex("q112m1c"));
               d._q112m1d = cur.getString(cur.getColumnIndex("q112m1d"));
               d._q112m1e = cur.getString(cur.getColumnIndex("q112m1e"));
               d._q112m1x = cur.getString(cur.getColumnIndex("q112m1x"));
               d._q112m1x1 = cur.getString(cur.getColumnIndex("q112m1x1"));

              /* d._q112l3 = cur.getString(cur.getColumnIndex("q112l3"));
               d._q113a1 = cur.getString(cur.getColumnIndex("q113a1"));
               d._q113a2 = cur.getString(cur.getColumnIndex("q113a2"));
               d._q113b1 = cur.getString(cur.getColumnIndex("q113b1"));
               d._q113b2 = cur.getString(cur.getColumnIndex("q113b2"));
               d._q113c1 = cur.getString(cur.getColumnIndex("q113c1"));
               d._q113c2 = cur.getString(cur.getColumnIndex("q113c2"));
               d._q113d1 = cur.getString(cur.getColumnIndex("q113d1"));
               d._q113d2 = cur.getString(cur.getColumnIndex("q113d2"));
               d._q113ap1 = cur.getString(cur.getColumnIndex("q113ap1"));
               d._q113ap2 = cur.getString(cur.getColumnIndex("q113ap2"));
               d._q113ap3 = cur.getString(cur.getColumnIndex("q113ap3"));
               d._q113ap4 = cur.getString(cur.getColumnIndex("q113ap4"));*/
               data.add(d);

               cur.moveToNext();
           }
           cur.close();
         return data;
       }
}