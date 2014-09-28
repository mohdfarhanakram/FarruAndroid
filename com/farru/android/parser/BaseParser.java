package com.farru.android.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.farru.android.model.BaseModel;
import com.farru.android.network.ServiceResponse;

/**
 * Created  on 31/1/14.
 */
public class BaseParser implements IParser {
	
	

    public ServiceResponse parseData(int eventType, String data) {
        ServiceResponse response = null;
        try {
            response = handleJsonResponse(eventType, new JSONObject(data));
        } catch (Exception e) {
            response = new ServiceResponse();
            response.setErrorCode(ServiceResponse.EXCEPTION);
        }
        return response;
    }

    protected BaseModel parseBaseData(JSONObject jsonObject)
            throws JSONException {

        BaseModel baseModel = new BaseModel();
        //TODO modify according to response
        return baseModel;
    }

    protected ServiceResponse handleJsonResponse(int eventType, JSONObject jsonObject) {
        ServiceResponse response = new ServiceResponse();
        try {
            BaseModel baseModel = parseBaseData(jsonObject);
            response.setJsonResponse(jsonObject);
            response.setEventType(eventType);
            response.setJsonResponse(jsonObject);
            response.setBaseModel(baseModel);
            
            // Handle response here (Parse base data first to identify error and success)
            
            /*if Success 
             call parseJsonData*/
            
            /*if(error)
            	call handleError(response);*/
            
            // Example code below used in jabong
            
            //       Log.d("response is ", jsonObject.toString());
            /*if (response.isSuccess()) {
                response.setErrorCode(ServiceResponse.SUCCESS);
                parseJsonData(response);
            } else if (eventType == ApiType.API_USER_AUTO_LOGIN && checkWhetherAutoLoginFailed(response)) {
                response.setErrorCode(ServiceResponse.AUTO_LOGIN_FAILED);
            } else {
                handleError(response);
            }
            if (response.getEventType() == ApiType.API_PAYMENT_SUCCES) {
                JSONObject obj = jsonObject.optJSONObject(JsonKey.META_DATA);
                if (obj != null && obj.optJSONObject(JsonKey.DATA) != null && obj.optJSONObject(JsonKey.DATA).has("over_trial_limit")) {
                    response.setRetryLimitExceeded(obj.optJSONObject(JsonKey.DATA).getBoolean("over_trial_limit"));
                }
            }*/


        } catch (Exception e) {
            response.setErrorCode(ServiceResponse.EXCEPTION);
            e.printStackTrace();
        }
        return response;
    }

   

    protected void handleError(ServiceResponse response) throws JSONException {
         //TODO Error hanlding
       
    }

    protected void parseJsonData(ServiceResponse response) throws JSONException {
    	//TODO Parse Json response here
        switch (response.getEventType()) {
             // Parse Json here based on event type.
            default:
                break;
        }

    }


}
