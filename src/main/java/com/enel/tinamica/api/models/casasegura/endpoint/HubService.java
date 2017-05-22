package com.enel.tinamica.api.models.casasegura.endpoint;

import com.enel.tinamica.api.models.casasegura.data.HbaseDataWrapper;
import com.enel.tinamica.api.models.casasegura.exceptions.CasaSeguraError;
import com.enel.tinamica.api.models.casasegura.exceptions.CasaSeguraException;
import com.enel.tinamica.api.models.casasegura.model.Ack;
import com.enel.tinamica.api.models.casasegura.model.BeanProjectModel;
import com.enel.tinamica.api.models.casasegura.model.BeanProjectModelFilter;
import com.enel.tinamica.api.models.casasegura.model.EventFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/05/17.
 */

@Service
public class HubService {


    private StringBuffer sb;

    private static final String _component = "[HubService]";

    /** Logger Object **/
    private static Logger _logger = LoggerFactory.getLogger(HubService.class);

    /** HBaseDataWrapper Object **/
    private @Autowired HbaseDataWrapper hbaseDataWrapper;



    public Ack consolidateProjectModel(BeanProjectModel model) throws CasaSeguraException {
        Ack ack;
        try {
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubService:consolidateProjectModel .......Init Sequence]");

            hbaseDataWrapper.connectHBase();
            ack = hbaseDataWrapper.consolidateModelProject(model);
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubService:consolidateProjectModel .......Init Sequence]");
        }
        catch (CasaSeguraException pae){
            _logger.error("ERROR .: " + pae.getError().getMessage());
            throw pae;
        }
        catch (Exception e){
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component+"[01", "[consolidateProjectModel]", CasaSeguraException.ERROR, e);
        }
        return ack;
    }


    public List<BeanProjectModel> getDispoScorePrediction() throws CasaSeguraException {
        List<BeanProjectModel> beanProjectModelList = new ArrayList<BeanProjectModel>();
        try {
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubService:getDispoScorePrediction .......Init Sequence]");
            hbaseDataWrapper.connectHBase();
            beanProjectModelList = hbaseDataWrapper.getScoreTablePrediction();
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubService:getDispoScorePrediction .......Ends Sequence]");
        }
        catch (CasaSeguraException pae){
            _logger.error("ERROR .: " + pae.getError().getMessage());
            throw pae;
        }
        catch (Exception e) {
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component+"[02", "[consolidateProjectModel]", CasaSeguraException.ERROR, e);
        }
        return beanProjectModelList;
    }

    public List<BeanProjectModel> getDispoScorePredictionFilter(EventFilter filter) throws CasaSeguraException {
        List<BeanProjectModel> beanProjectModelList = new ArrayList<BeanProjectModel>();
        try {
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubService:getDispoScorePredictionFilter .......Init Sequence]");
            hbaseDataWrapper.connectHBase();
            beanProjectModelList = hbaseDataWrapper.getScoreTablePredictionFilter(filter);
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubService:getDispoScorePredictionFilter .......Ends Sequence]");
        }
        catch (CasaSeguraException pae){
            _logger.error("ERROR .: " + pae.getError().getMessage());
            throw pae;
        }
        catch (Exception e) {
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component+"[02", "[getDispoScorePrediction]", CasaSeguraException.ERROR, e);
        }
        return beanProjectModelList;
    }


}
