package cn.bugstack.chatbot.api.test;

import com.google.gson.JsonObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.List;

public class ApiTest {
    
    @Test
    public void query_unanswered_questions()throws Exception{
        CloseableHttpClient httpClient =  HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        get.setHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212244452542481%22%2C%22first_id%22%3A%22192db06891de07-0dcd26b2d04fc9-4c657b58-678564-192db06891ee05%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkyZGIwNjg5MWRlMDctMGRjZDI2YjJkMDRmYzktNGM2NTdiNTgtNjc4NTY0LTE5MmRiMDY4OTFlZTA1IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMjEyMjQ0NDUyNTQyNDgxIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212244452542481%22%7D%2C%22%24device_id%22%3A%22192db06891de07-0dcd26b2d04fc9-4c657b58-678564-192db06891ee05%22%7D; tfstk=fSEqtqMbNiIVAmj6fxoZLeZTiVnxXmfB3lGsIR2ihjcDchaPSR2mhVfxclPzw7FbhRZayhyLBo6xftnYHcnGO6sIb-exX764qVrqrUDTEhDDoM8uV0nGO6s7xfKm7cVbstN9z8c-QnAii5DkqbH9iccmSQYoKbmis50iEUDxQCAmsfxlUAhoscmiG9hjNMHZo9s7TIFVeO3xt-c0h-Zk8qYYIbloakkUUX2Drhxg4xu0XPfDgQ0bSJ3Lc-sw2mwUr0D4sMfmqJ4zdqrVTnGt7uP47S66WYzaIknxYdj0Umlq-ouvK9F3UR4Izu6hvmomiPiYCp5bUoPbHuy6IUmqczuU0Djpifa7LlkaX1IzT-43sPSyBCHkIBETuCYZoYHrOTWz-RRTwhYZke89WqaEUX62BFLtoYHrOTWyWF3bQYlC3dC..; zsxqsessionid=924ee6513e6ad8f2243f5887d7808231; zsxq_access_token=A9391810-F900-98E2-16D3-8B32E4184B71_79ABFF04AEC04D53");
        get.setHeader("content-type","application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            //System.out.println(res);
            //使用json-lib解析json
            JSONObject jsonObject = JSONObject.fromObject(res);
            boolean succeeded = jsonObject.getBoolean("succeeded");
            JSONObject respData = jsonObject.getJSONObject("resp_data");
            JSONArray topics = (JSONArray) respData.get("topics");
            List list = JSONArray.toList(topics);
            for (Object o : list) {
                System.out.println(o);
            }
            System.out.println(succeeded);
            System.out.println(topics.toString());
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
    
    @Test
    public void answer()throws Exception{
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/4848522254285218/comments");
        post.setHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212244452542481%22%2C%22first_id%22%3A%22192db06891de07-0dcd26b2d04fc9-4c657b58-678564-192db06891ee05%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkyZGIwNjg5MWRlMDctMGRjZDI2YjJkMDRmYzktNGM2NTdiNTgtNjc4NTY0LTE5MmRiMDY4OTFlZTA1IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMjEyMjQ0NDUyNTQyNDgxIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212244452542481%22%7D%2C%22%24device_id%22%3A%22192db06891de07-0dcd26b2d04fc9-4c657b58-678564-192db06891ee05%22%7D; tfstk=fSEqtqMbNiIVAmj6fxoZLeZTiVnxXmfB3lGsIR2ihjcDchaPSR2mhVfxclPzw7FbhRZayhyLBo6xftnYHcnGO6sIb-exX764qVrqrUDTEhDDoM8uV0nGO6s7xfKm7cVbstN9z8c-QnAii5DkqbH9iccmSQYoKbmis50iEUDxQCAmsfxlUAhoscmiG9hjNMHZo9s7TIFVeO3xt-c0h-Zk8qYYIbloakkUUX2Drhxg4xu0XPfDgQ0bSJ3Lc-sw2mwUr0D4sMfmqJ4zdqrVTnGt7uP47S66WYzaIknxYdj0Umlq-ouvK9F3UR4Izu6hvmomiPiYCp5bUoPbHuy6IUmqczuU0Djpifa7LlkaX1IzT-43sPSyBCHkIBETuCYZoYHrOTWz-RRTwhYZke89WqaEUX62BFLtoYHrOTWyWF3bQYlC3dC..; zsxqsessionid=924ee6513e6ad8f2243f5887d7808231; zsxq_access_token=A9391810-F900-98E2-16D3-8B32E4184B71_79ABFF04AEC04D53");
        post.setHeader("Content-Type","application/json;charset=utf8");
       // String paramJson="{req_data: {text: \"我不会啊姐妹 什么Java↵\", image_ids: [], mentioned_user_ids: []}}";
        JSONObject req_data = new JSONObject();
        req_data.put("text","去百度啊");
        req_data.put("image_ids",new JSONArray());
        req_data.put("mentioned_user_ids",new JSONArray());
        JSONObject root = new JSONObject();
        root.put("req_data",req_data);
        String paramJson = root.toString();
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("test/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
        
    }
    
    
}
