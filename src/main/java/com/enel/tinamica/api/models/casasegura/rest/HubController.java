package com.enel.tinamica.api.models.casasegura.rest;

import com.enel.tinamica.api.models.casasegura.endpoint.HubService;
import com.enel.tinamica.api.models.casasegura.exceptions.CasaSeguraError;
import com.enel.tinamica.api.models.casasegura.exceptions.CasaSeguraException;
import com.enel.tinamica.api.models.casasegura.model.Ack;
import com.enel.tinamica.api.models.casasegura.model.BeanProjectModel;
import com.enel.tinamica.api.models.casasegura.model.BeanProjectModelFilter;
import com.enel.tinamica.api.models.casasegura.model.EventFilter;
import net.bull.javamelody.MonitoredWithSpring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 10/05/17.
 */

@MonitoredWithSpring
@RequestMapping("/apienel/")

@Controller

public class HubController {

    private static final String JSON = "application/json";
    private StringBuffer sb;

    private static final String _component = "[HubController]";

    /** Logger Object **/
    private static Logger _logger = LoggerFactory.getLogger(HubController.class);

    /** HubService Object **/
    private @Autowired HubService hubService;

     @RequestMapping(value = "/getInfo/", method = RequestMethod.GET,  produces={JSON})
    public @ResponseBody


    String  getInfo() throws CasaSeguraException {
        String  _info="[API HUB Enel - Models - Spring Cloud API Microservices]";

        return _info;
    }

    @RequestMapping(value = "/consolidateProjectModel/", method = RequestMethod.POST,  produces={JSON})

    public @ResponseBody Ack consolidateProjectModel(@RequestBody BeanProjectModel model) throws CasaSeguraException {
        Ack ack = null;
        try {
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubController:consolidateProjectModel .......Init Sequence]");
            ack = hubService.consolidateProjectModel(model);
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubController:consolidateProjectModel .......Ends Sequence]");
        }
        catch (CasaSeguraException pae) {
            _logger.error("ERROR .: " + pae.getError().getMessage());
            throw pae;
        }
        catch (Exception e) {
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component+"[01", "[consolidateProjectModel]", CasaSeguraException.ERROR, e);
        }
        return ack;
    }


   @RequestMapping(value = "/getDispoScorePrediction/", method = RequestMethod.GET,  produces={JSON})
    public @ResponseBody
    List<BeanProjectModel> getDispoScorePrediction() throws CasaSeguraException {
        List<BeanProjectModel> beanProjectModelList = null;
        try {
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubController:getDispoScorePrediction .......Init Sequence]");
            beanProjectModelList= hubService.getDispoScorePrediction();
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubController:getDispoScorePrediction .......Ends Sequence]");
        }
        catch (CasaSeguraException pae) {
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

   @RequestMapping(value = "/getDispoScorePredictionFilter/", method = RequestMethod.POST,  produces={JSON})
    public @ResponseBody     List<BeanProjectModel> getDispoScorePredictionFilter(@RequestBody EventFilter filter) throws CasaSeguraException {
        List<BeanProjectModel> beanProjectModelList = null;
        try {
            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubController:getDispoScorePredictionFilter .......Init Sequence]");
            _logger.info("Filter: "+filter.toString());
            beanProjectModelList= hubService.getDispoScorePredictionFilter(filter);

            _logger.info("[........APIHubBrasilGlobalCasaSegura:HubController:getDispoScorePredictionFilter .......Ends Sequence]");
        }
        catch (CasaSeguraException pae) {
            _logger.error("ERROR .: " + pae.getError().getMessage());
            throw pae;
        }
        catch (Exception e) {
            _logger.error("ERROR .: " + e.getStackTrace());
            sb = new StringBuffer();
            CasaSeguraError error = new CasaSeguraError(this.getClass().getName(), String.valueOf(CasaSeguraException.ERROR), sb.append("Error .: ").append(e.fillInStackTrace()).toString(), e.getCause());
            throw new CasaSeguraException(error, _component+"[02", "[getDispoScorePredictionFilter]", CasaSeguraException.ERROR, e);
        }
        return beanProjectModelList;
    }



}
