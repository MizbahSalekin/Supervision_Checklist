package Common;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import DataSync.DataClass;
import DataSync.DataClassProperty;
import DataSync.DownloadJSONData;
import DataSync.DownloadRequestClass;
import DataSync.UploadJSONData;
import DataSync.downloadClass;

/**
 * Created by thossain on 16/06/2017.
 */

public class DataSyncManagement {
    Connection C;
    Context ud_context;

    String Dist;
    String Upz;
    String ProvType;
    String ProvCode;
    int BatchSize;
    List<String> VList;
    String FWAUnit = "";
    String HAWard="";
    int totalBatch=0;
    int i = 0;

    private String[] TL;


    public DataSyncManagement(Context context, String dist, String upz, String provtype, String provcode, int batchsize)
    {
        ud_context = context;
        C = new Connection(ud_context);

        Dist = dist;
        Upz  = upz;
        ProvType = provtype;
        ProvCode = provcode;
        BatchSize = batchsize;
        //VList = C.VillageList();
    }

   // String Res="";

    public void Sync_MasterTables_All(String Dist,String Upz,String Providerid)
    {
        String SQLStr = "";
        String Res = "";
//ProviderType


        SQLStr="Select \"tableName\", \"tableScript\",\"columnList\",\"uniqueId\",\"batchSizeDown\",\"batchSizeUp\",\"syncType\",\"modifiedDate\" from \"databaseSetting\"";
        Res = DownloadJSON(SQLStr, "databaseSetting", "tableName,tableScript,columnList,uniqueId,batchSizeDown,batchSizeUp,syncType,modifiedDate", "tableName");


        SQLStr = "Select id,division,divisioneng,systementrydate,modifydate,uploaddate,1 upload from division where id='50'";
        Res = DownloadJSON(SQLStr, "division", "id,division,divisioneng,systementrydate,modifydate,uploaddate,upload", "id");

        SQLStr = "Select divid,zillaid,zillanameeng,zillaname,systementrydate,modifydate,uploaddate,1 upload from zilla where zillaid='" + Dist + "'";
        Res = DownloadJSON(SQLStr, "zilla", "divid,zillaid,zillanameeng,zillaname,systementrydate,modifydate,uploaddate,upload", "divid,zillaid");

        SQLStr = "Select zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,1 upload from upazila where zillaid='" + Dist + "' and upazilaid='" + Upz + "'";
        Res = DownloadJSON(SQLStr, "upazila", "zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid");


        SQLStr = "Select zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,1 upload from upazila where zillaid='" + Dist + "' and upazilaid='" + 88 + "'";
        Res = DownloadJSON(SQLStr, "upazila", "zillaid,upazilaid,upazilanameeng,upazilaname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid");


        SQLStr = "Select distinct u.zillaid,u.upazilaid,u.municipalityid,u.unionid,u.unionnameeng,u.unionname,u.systementrydate,u.modifydate,u.uploaddate,1 upload from unions u";
        SQLStr += " where u.zillaid='" + Dist + "' and u.upazilaid='" + Upz + "'";
        Res = DownloadJSON(SQLStr, "unions", "zillaid,upazilaid,municipalityid,unionid,unionnameeng,unionname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid,unionid");

        SQLStr = "Select distinct u.zillaid,u.upazilaid,u.municipalityid,u.unionid,u.unionnameeng,u.unionname,u.systementrydate,u.modifydate,u.uploaddate,1 upload from unions u";
        SQLStr += " where u.zillaid='" + Dist + "' and u.upazilaid='" + 88 + "'";
        Res = DownloadJSON(SQLStr, "unions", "zillaid,upazilaid,municipalityid,unionid,unionnameeng,unionname,systementrydate,modifydate,uploaddate,upload", "zillaid,upazilaid,unionid");



        SQLStr = "Select distinct divid,zillaid,upazilaid,unionid,provtype,providerid,provname,mobileno,endate,exdate,active,devicesetting,systemupdatedt,healthidrequest,tablestructurerequest,areaupdate,supervisorcode,provpass,facilityname,csba,systementrydate,modifydate,uploaddate,1 upload from providerdb where ";//
        SQLStr += " providerid='" + ProvCode + "' and";
        SQLStr += " active='1'";
        Res = DownloadJSON(SQLStr, "providerdb", "divid,zillaid,upazilaid,unionid,provtype,providerid,provname,mobileno,endate,exdate,active,devicesetting,systemupdatedt,healthidrequest,tablestructurerequest,areaupdate,supervisorcode,provpass,facilityname,csba,systementrydate,modifydate,uploaddate,upload", "providerid");

        SQLStr = "SELECT typename,code, cname,systementrydate,modifydate,uploaddate,upload FROM codelist where typename in('15','41')";
        Res = DownloadJSON(SQLStr, "codelist", "typename,code, cname,systementrydate,modifydate,uploaddate,upload", "typename, code");


        /*
        SQLStr = "SELECT zillaid,upazilaid,unionid,vill_name,slno,clinicid,idno,q101a,q101b,q102,q103,q103a,q104,q105,q106,q107,q108,interviewer_id,interviewer_date,interviewer_time,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,upload from section_1_identifications where";//
        SQLStr += " entryuser='" + ProvCode + "'";
        Res = DownloadJSON(SQLStr, "section_1_identifications", "zillaid,upazilaid,unionid,vill_name,slno,clinicid,idno,q101a,q101b,q102,q103,q103a,q104,q105,q106,q107,q108,interviewer_id,interviewer_date,interviewer_time,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,upload", "idno");

        SQLStr = "SELECT idno,q201,q202,q202a,q203,q204,q204a,q205,q206,q207,q208,q209,q209a,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload from section_2_during_childbirth where";//
        SQLStr += " entryuser='" + ProvCode + "'";
        Res = DownloadJSON(SQLStr, "section_2_during_childbirth", "idno,q201,q202,q202a,q203,q204,q204a,q205,q206,q207,q208,q209,q209a,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload", "idno");

        SQLStr = "SELECT idno,q301,q302,q302a,q303,q303a,q304,q305,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload from section_3_during_childbirth where";//
        SQLStr += " entryuser='" + ProvCode + "'";
        Res = DownloadJSON(SQLStr, "section_3_during_childbirth", "idno,q301,q302,q302a,q303,q303a,q304,q305,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload", "idno");

        SQLStr = "SELECT idno,slno,visit_no,vdate,q401a,q401b,q401c,q401d,q401e,q401f,q401x,q401x1,q402,q403,q403a,q404a,q404b,q404c,q404d,q404e,q404x,q404x1,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload from section_4_during_childbirth where";//
        SQLStr += " entryuser='" + ProvCode + "'";
        Res = DownloadJSON(SQLStr, "section_4_during_childbirth", "idno,slno,visit_no,vdate,q401a,q401b,q401c,q401d,q401e,q401f,q401x,q401x1,q402,q403,q403a,q404a,q404b,q404c,q404d,q404e,q404x,q404x1,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload", "idno,slno");

        SQLStr = "SELECT idno,slno,q501,q501a,q502,q503,q503a,q504,q505a,q505b,q505c,q505d,q505e,q505f,q505g,q505h,q505i,q505j,q505k,q505x,q505x1,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload from section_5_during_childbirth where";//
        SQLStr += " entryuser='" + ProvCode + "'";
        Res = DownloadJSON(SQLStr, "section_5_during_childbirth", "idno,slno,q501,q501a,q502,q503,q503a,q504,q505a,q505b,q505c,q505d,q505e,q505f,q505g,q505h,q505i,q505j,q505k,q505x,q505x1,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload", "idno,slno");


        SQLStr = "SELECT idno,slno,q601,q602,q603,q604,q605,q606,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload from section_6_during_childbirth where";//
        SQLStr += " entryuser='" + ProvCode + "'";
        Res = DownloadJSON(SQLStr, "section_6_during_childbirth", "idno,slno,q601,q602,q603,q604,q605,q606,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload", "idno,slno");


        SQLStr = "SELECT idno,slno,q601,q602,q603,q604,q605,q606,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload from section_7_postpartum where";//
        SQLStr += " entryuser='" + ProvCode + "'";
        Res = DownloadJSON(SQLStr, "section_7_postpartum", "idno,slno,visit_no,vdate,q700,q701,q702a,q702b,q702c,q702d,q702x,q702x1,q703,q703a,q704a,q704b,q704c,q704d,q704e,q704x,q704x1,q705,q706,q706a,q707,q708,q709a,q709b,q709c,q709d,q710,q711a,q711b,q711c,q711d,q711e,q711x,q711x1,q712,q713,q714,q715,q716a,q716a1,q716b,q716b1,q716c,q716c1,q716d,q716d1,q716e,q716e1,q716f,q716f1,q716x,q716x1,q716x2,q717,q718,q719,q720,q721a,q721b,q721c,starttime,endtime,deviceid,entryuser,lat,lon,endt,uploaddt,modifydate,upload", "idno,slno");
*/

    }



//**************************************************************************************************



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
            if (responseData != null & responseData.getdata().size()>0) {
                SQL = "Insert or replace into "+ TableName +"("+ ColumnList +")Values";
                //SQL = "Insert into "+ TableName +"("+ ColumnList +")Values";
                for (int i = 0; i < responseData.getdata().size(); i++) {
                    String VarData[] = Connection.split(responseData.getdata().get(i).toString(), '^');

                    //Generate Unique ID
                    //------------------------------------------------------------------------------
                    for (int j = 0; j < UField.length; j++) {
                        varPos = C.VarPosition(UField[j].toString(), VarList);
                        if (j == 0) {
                            IDNO += VarData[varPos].toString();
                        } else {
                            IDNO += VarData[varPos].toString();
                        }
                    }


                     if(TableName.equalsIgnoreCase("elcovisit")) {
                        varPos_modifyDate = C.VarPosition("modifydate", VarList);
                        if(VarData[varPos_modifyDate].toString().equalsIgnoreCase("")||VarData[varPos_modifyDate].toString().equalsIgnoreCase("null"))
                        {
                            varPos_modifyDate = C.VarPosition("vdate", VarList);
                            modify_Date = VarData[varPos_modifyDate].toString().replace("null", "");
                        }
                        else {
                            modify_Date = VarData[varPos_modifyDate].toString().replace("null", "");
                        }
                    }
                    else{
                        varPos_modifyDate = C.VarPosition("modifydate", VarList);
                        modify_Date = VarData[varPos_modifyDate].toString().replace("null", "");
                    }

                    //------------------------------------------------------------------------------
                    if (i == 0) {
                        SQL += "('" + responseData.getdata().get(i).toString().replace("^","','").replace("null","") +"')";
                    } else {
                        SQL += ",('" + responseData.getdata().get(i).toString().replace("^","','").replace("null","") +"')";
                    }

                    //Populate class with data for sync_management
                    //------------------------------------------------------------------------------
                    DataList = TableName + "^" + IDNO + "^" + modify_Date + "^" + ProvType + "^" + ProvCode;
                    d = new DataClassProperty();
                    d.setdatalist(DataList);
                    d.setuniquefieldwithdata("" +
                            "\"tableName\"  ='" + TableName + "' and " +
                            "\"recordId\"   ='" + IDNO + "' and " +
                            "\"modifyDate\" ='" + modify_Date + "' and " +
                            "\"provType\"   ='" + ProvType + "' and " +
                            "\"provCode\"   ='" + ProvCode + "'");
                    dataTemp.add(d);

                    IDNO = "";
                }
                SaveResp = C.SaveData(SQL);
                if(SaveResp.length()==0){
                    data = dataTemp;
                }else{
                    C.Save("Insert into templog values('"+ TableName +": "+ SaveResp +"')");
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
            C.Save("Insert into templog values('"+ TableName +": "+ SaveResp +"')");
            e.printStackTrace();
        }
        return resp;
    }




}
