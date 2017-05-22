package com.enel.tinamica.api.models.casasegura.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 10/05/17.
 */
public class HubServiceUtils {

    public static String LOCALHOST_LABEL = "192.168.2.20";


    /** Labels Config POC Env **/
    public static final String ZOOKEEPER_QUORUN_POC_ENV = "10.152.148.21"; //FIXME:Actualizar por Masternode
    public static final int ZOOKEEPER_PORT = 2181;

    public static final int SCAN_CATCHING = 1000;


     /* Labels Columns Model*/

    public static final String modelTableNameBrasilGlobal = "Modelos_BA:BRASIL_GLOBAL";

    public static final String LABEL_COLUMN_DISTRIBUIDORA = "distribuidora";
    public static final String LABEL_COLUMN_CPF = "cpf";
    public static final String LABEL_COLUMN_SCORE = "score";
    public static final String LABEL_COLUMN_FECHAEJECUCION = "fechaejecucion";



    public static final String LABEL_SERVCE = "[APUHubBrasilGlobalCasaSegura]";

    public static long getTimeCurrentMilis() throws Exception {
        long time;
        try {
            time = Calendar.getInstance().getTimeInMillis();
        } catch (Exception e) {
            throw e;
        }
        return time;
    }

    public static String getFecha() throws Exception {

        String fecha = "";
        try{

            Date date = new Date();

            fecha = new SimpleDateFormat("yyyyMMdd").format(date).toString();

        }catch (Exception e) {
            throw e;
        }
        return fecha;
    }

    public static boolean getFilterByFecCreaEventAdHoc(String fecCompare, String fecFilter) throws Exception {
        boolean addFilter = false;
        try {
            long date1 = new Long(fecCompare).longValue();
            long date2 = new Long(fecFilter).longValue();

            if(date1>= date2) {
                addFilter = true;
            }
            System.out.println("return filter .: " + addFilter);
        }
        catch (Exception e){
            throw e;
        }
        return addFilter;
    }
}
