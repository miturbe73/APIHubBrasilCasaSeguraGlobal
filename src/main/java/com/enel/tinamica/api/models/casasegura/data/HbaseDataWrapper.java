package com.enel.tinamica.api.models.casasegura.data;

import com.enel.tinamica.api.models.casasegura.exceptions.CasaSeguraError;
import com.enel.tinamica.api.models.casasegura.exceptions.CasaSeguraException;
import com.enel.tinamica.api.models.casasegura.model.*;
import com.enel.tinamica.api.models.casasegura.utils.HubServiceUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by user on 10/05/17.
 */
@Repository
public class HbaseDataWrapper {

    private StringBuffer sb;

    private static final String _component = "[HbaseDataWrapper]";

    /** Logger Object **/
    private static Logger _logger = LoggerFactory.getLogger(HbaseDataWrapper.class);

    public static final String HBASE_CONFIGURATION_ZOOKEEPER_QUORUM      = "hbase.zookeeper.quorum";
    public static final String HBASE_CONFIGURATION_ZOOKEEPER_CLIENTPORT  = "hbase.zookeeper.property.clientPort";

    private static String driverName = "org.apache.hadoop.hbase.HBaseConfiguration";

    private Configuration config;

    private Table table;

    private Connection conn = null;
    private Put p = null;

    public void connectHBase() throws CasaSeguraException {

        try{

            _logger.info("[........APIHubBrasilGlobalCasaSegura:HbaseDataWrapper:connectHbase .......Init Sequence]");

            _logger.info("........");

            Class.forName(driverName);
            config = HBaseConfiguration.create();
            String hbaseZookeeperQuorum = HubServiceUtils.ZOOKEEPER_QUORUN_POC_ENV;
            int hbaseZookeeperClientPort = HubServiceUtils.ZOOKEEPER_PORT;

            config.set(HBASE_CONFIGURATION_ZOOKEEPER_QUORUM, hbaseZookeeperQuorum);
            config.setInt(HBASE_CONFIGURATION_ZOOKEEPER_CLIENTPORT, hbaseZookeeperClientPort);

            _logger.info("[........APIHubBrasilGlobalCasaSegura:HbaseDataWrapper:connectHbase .......Ends Sequence]");


        }catch (Exception e) {
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component+"[01", "[connectHbase]", CasaSeguraException.ERROR, e);
        }
    }


    public Ack consolidateModelProject (BeanProjectModel model) throws CasaSeguraException {

        Ack ack = new Ack();
        String row_root = new String();
        StringBuffer sbData = null;

        try{

            _logger.info("[........APIHubBrasilGlobalCasaSegura:HbaseDataWrapper:consolidateModelProject.......Init Sequence]");

            HBaseAdmin.checkHBaseAvailable(config);
            conn = ConnectionFactory.createConnection(config);
            Admin admin = conn.getAdmin();

            table = conn.getTable(TableName.valueOf(HubServiceUtils.modelTableNameBrasilGlobal));

            _logger.info(model.toString());

            for(int i=0;i<model.getBody().getPredictionList().size();i++) {

                sbData = new StringBuffer();

                // Generamos el ID de la tabla
                sbData.append(HubServiceUtils.getFecha()+"-").append(String.valueOf(HubServiceUtils.getTimeCurrentMilis() + "-")).append(model.getHeader().getProject()+ "-").append(model.getHeader().getCountry()+"-").append(model.getHeader().getModel()).append("-" +model.getBody().getPredictionList().get(i).getFechaejecucion()+"_").append(model.getBody().getPredictionList().get(i).getDistribuidora()+"_").append(model.getBody().getPredictionList().get(i).getCpf());

                row_root = sbData.toString();
                p = new Put(Bytes.toBytes(row_root));

                p.add(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_DISTRIBUIDORA), Bytes.toBytes(model.getBody().getPredictionList().get(i).getDistribuidora().toString()));
                p.add(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_CPF), Bytes.toBytes(model.getBody().getPredictionList().get(i).getCpf().toString()));
                p.add(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_SCORE), Bytes.toBytes(model.getBody().getPredictionList().get(i).getScore().toString()));
                p.add(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_FECHAEJECUCION), Bytes.toBytes(model.getBody().getPredictionList().get(i).getFechaejecucion().toString()));

                table.put(p);

            }

            ack.setComponent(_component);
            ack.setOk(true);
            ack.setMsgAck("[ModelClase consolidate successfully.]");
            ack.setService(HubServiceUtils.LABEL_SERVCE);
            ack.setEventDate(HubServiceUtils.getTimeCurrentMilis());
            ack.setStremingEvent(model.toString());

            _logger.info("[........APIHubBrasilGlobalCasaSegura:HbaseDataWrapper:consolidateModelProject .......Ends Sequence]");



        }catch (Exception e) {
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component + "[02", "[consolidateModelProject]", CasaSeguraException.ERROR, e);
        }

        finally {
            try {
                table.close();
                conn.close();
            } catch (Exception e) {
                sb = new StringBuffer();
                CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
                throw new CasaSeguraException(error, _component + "[02.5", "[consolidateModelProject]", CasaSeguraException.ERROR, e);
            }
        }

        return ack;
    }

    public List<BeanProjectModel> getScoreTablePredictionFilter(EventFilter filtro) throws CasaSeguraException {
        SingleColumnValueFilter filter = null;
        List<BeanProjectModel> beanProjectModelList = new ArrayList<BeanProjectModel>();
        BeanProjectModel model;
        List<Prediction> tablePredictionList = new ArrayList<Prediction>();
        EventHeader header;
        Eventbody body;
        Prediction prediction;
        ResultScanner scanner = null;
        String row = new String();
        StringTokenizer stRow;
        StringTokenizer stColumn;
        String feCreaEvent = "";
        String id = "";
        try{
            _logger.info("Filter Wrapper: "+filtro.toString());
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HbaseDataWrapper:getScoreTablePredictionFilter .......Init Sequence]");
            //FIXME: //filter = new SingleColumnValueFilter(Bytes.toBytes("fecCreaEvent"), CompareFilter.CompareOp.GREATER_OR_EQUAL, );

            HBaseAdmin.checkHBaseAvailable(config);

            conn = ConnectionFactory.createConnection(config);
            Admin admin = conn.getAdmin();

            table = conn.getTable(TableName.valueOf(HubServiceUtils.modelTableNameBrasilGlobal));

            Scan scan = new Scan();

            scan.addFamily(Bytes.toBytes("ga_distribuidora"));
            scan.setCaching(HubServiceUtils.SCAN_CATCHING);
            scanner = table.getScanner(scan);
            //FIXME: Actualizar con el analisis de impacto de la actualizacion de las tablas de HBASE
            for (Result result = scanner.next(); result != null; result = scanner.next()) {
                model = new BeanProjectModel();
                header = new EventHeader();
                body = new Eventbody();

                String _row = new String(result.getRow(), "UTF-8");

                /** Se parsea la row **/
                row = new String(_row);
                _logger.info("_ROW .: " + _row);
                stRow = new StringTokenizer(row, "-");
                id = stRow.nextToken();
                _logger.info("id .: " + id);

                feCreaEvent = stRow.nextToken();
                _logger.info("feCreaEvent .: " + feCreaEvent);

                /** Se evalua el filtro de la fecha : Filter AdHoc **/
                _logger.info("Llega al if del wrapper");
                _logger.info("param " + filtro.getFecCreaEvent());
                if (HubServiceUtils.getFilterByFecCreaEventAdHoc(feCreaEvent, filtro.getFecCreaEvent())){
                    _logger.info("feCreaEvent: "+feCreaEvent + "y filtro.getFecCreaEvent()): "+filtro.getFecCreaEvent());
                    String project = stRow.nextToken();
                    _logger.info("project .: " + project);
                    String counttry = stRow.nextToken();
                    _logger.info("coountry .: " + counttry);
                    String _model = stRow.nextToken();
                    _logger.info("model .: " + _model);
                    header.setProject(project);
                    header.setCountry(counttry);
                    header.setModel(_model);
                    header.setFecreaevent(feCreaEvent);

                    byte[] distribuidora = result.getValue(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_DISTRIBUIDORA));
                    byte[] cpf = result.getValue(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_CPF));
                    byte[] score = result.getValue(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_SCORE));
                    byte[] fechaejecucion = result.getValue(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_FECHAEJECUCION));

                    prediction = new Prediction();
                    prediction.setDistribuidora(new String(distribuidora, "UTF-8"));
                    prediction.setCpf(new String(cpf, "UTF-8"));
                    prediction.setScore(new String(score, "UTF-8"));
                    prediction.setFechaejecucion(new String(fechaejecucion, "UTF-8"));

                    tablePredictionList.add(prediction);
                    body.setPredictionList(tablePredictionList);
                    model.setHeader(header);
                    model.setBody(body);
                    beanProjectModelList.add(model);

                }

            }
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HbaseDataWrapper:getScoreTablePredictionFilter .......Ends Sequence]");
        }catch (Exception e) {
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component + "[04", "[getScoreTablePredictionFilter]", CasaSeguraException.ERROR, e);
        }
        return beanProjectModelList;
    }


    public List<BeanProjectModel> getScoreTablePrediction() throws CasaSeguraException {

        List<BeanProjectModel> beanProjectModelList = new ArrayList<BeanProjectModel>();
        BeanProjectModel model;
        List<Prediction> tablePredictionList = new ArrayList<Prediction>();
        EventHeader header;
        Eventbody body;
        Prediction prediction;
        ResultScanner scanner = null;
        String row = new String();
        StringTokenizer stRow;
        StringTokenizer stColumn;
        String feCreaEvent = "";
        String id = "";

        try{

            _logger.info("[........APIHubBrasilGlobalCasaSegura:HbaseDataWrapper:getScoreTablePrediction .......Init Sequence]");
            HBaseAdmin.checkHBaseAvailable(config);

            conn = ConnectionFactory.createConnection(config);
            Admin admin = conn.getAdmin();

            table = conn.getTable(TableName.valueOf(HubServiceUtils.modelTableNameBrasilGlobal));

            Scan scan = new Scan();

            scan.addFamily(Bytes.toBytes("ga_distribuidora"));
            scan.setCaching(HubServiceUtils.SCAN_CATCHING);
            scanner = table.getScanner(scan);

            for (Result result = scanner.next(); result != null; result = scanner.next()) {
                model = new BeanProjectModel();
                header = new EventHeader();
                body = new Eventbody();

                String _row = new String(result.getRow(), "UTF-8");

                /** Se parsea la row **/
                row = new String(_row);
                _logger.info("_ROW .: " + _row);
                stRow = new StringTokenizer(row, "-");
                id = stRow.nextToken();
                _logger.info("id .: " + id);

                feCreaEvent = stRow.nextToken();
                _logger.info("feCreaEvent .: " + feCreaEvent);
                String project = stRow.nextToken();
                _logger.info("project .: " + project);
                String counttry = stRow.nextToken();
                _logger.info("coountry .: " + counttry);
                String _model = stRow.nextToken();
                _logger.info("model .: " + _model);
                header.setProject(project);
                header.setCountry(counttry);
                header.setModel(_model);
                header.setFecreaevent(feCreaEvent);

                byte[] distribuidora = result.getValue(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_DISTRIBUIDORA));
                byte[] cpf = result.getValue(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_CPF));
                byte[] score = result.getValue(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_SCORE));
                byte[] fechaejecucion = result.getValue(Bytes.toBytes("ga_distribuidora"), Bytes.toBytes(HubServiceUtils.LABEL_COLUMN_FECHAEJECUCION));

                prediction = new Prediction();
                prediction.setDistribuidora(new String(distribuidora, "UTF-8"));
                prediction.setCpf(new String(cpf, "UTF-8"));
                prediction.setScore(new String(score, "UTF-8"));
                prediction.setFechaejecucion(new String(fechaejecucion, "UTF-8"));

                tablePredictionList.add(prediction);
                body.setPredictionList(tablePredictionList);
                model.setHeader(header);
                model.setBody(body);
                beanProjectModelList.add(model);
            }

            _logger.info("[........APIHubBrasilGlobalCasaSegura:HbaseDataWrapper:getScoreTablePrediction .......Ends Sequence]");

        }catch (Exception e) {
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component + "[03", "[getScoreTablePrediction]", CasaSeguraException.ERROR, e);
        } finally {
            try {
                table.close();
                conn.close();
            } catch (Exception e) {
                sb = new StringBuffer();
                CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
                throw new CasaSeguraException(error, _component + "[03", "[getScoreTablePrediction]", CasaSeguraException.ERROR, e);
            }
        }
        return beanProjectModelList;
    }

}
