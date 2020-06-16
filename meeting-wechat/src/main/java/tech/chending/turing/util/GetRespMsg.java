package tech.chending.turing.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import tech.chending.turing.pojo.*;
import tech.chending.wechat.util.HttpReqUtil;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ChenDing
 */
@Slf4j
public class GetRespMsg {

    private static final String TURING_URL = "http://openapi.tuling123.com/openapi/api/v2";
    private static final String[] API_KEYS = {
            "374dd0f6f2064b73b11833db5ef4ea6b",
            "e7d978405a0b488f8f840c6260cb949a"
    };

    //保证线程安全,采用原子类
    public static AtomicInteger apiKeyIndex = new AtomicInteger(0);

    //apiKey超过次数返回的状态码
    private static final String CODE = "4003";
    private GetRespMsg() {
    }

    public static String getTextMsg(int reqType, String context,String imgUrl) {
        return invoke(reqType, context,imgUrl);
    }


    private static String invoke(int reqType, String context ,String imgUrl) {
        //封装请求数据
        TuringReq turingReq = new TuringReq();
        turingReq.setReqType(reqType);
        Perception perception = new Perception();
        InputText inputText = new InputText();
        InputImage inputImage = new InputImage();
        //设置输入内容
        inputText.setText(context);
        inputImage.setUrl(imgUrl);
        perception.setInputText(inputText);
        perception.setInputImage(inputImage);
        turingReq.setPerception(perception);
        UserInfo userInfo = new UserInfo();
        //获取apiKey
        userInfo.setApiKey(API_KEYS[apiKeyIndex.intValue()]);
        //生成用户的唯一标识
        String userId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        userInfo.setUserId(userId);
        turingReq.setUserInfo(userInfo);
        String respMsg = HttpReqUtil.getRespMsg(TURING_URL, "post", JSONObject.toJSONString(turingReq));
        //转换为json对象
        System.out.println(respMsg);
        JSONObject jsonObject = JSONObject.parseObject(respMsg);
        JSONObject intent = jsonObject.getJSONObject("intent");
        //判断当前apikey是否可用
        String code = intent.getString("code");
        if (CODE.equals(code)) {
            //不可用
            //日志记录
            log.info(API_KEYS[apiKeyIndex.intValue()]+"----->已超过请求次数");
            apiKeyIndex.incrementAndGet();
            if (apiKeyIndex.intValue() == API_KEYS.length) {
                return "机器人要休息了明天再来吧！";
            }
            //递归调用
            return invoke(reqType, context,imgUrl);

        }

        JSONArray results = jsonObject.getJSONArray("results");
        JSONObject o = results.getJSONObject(0);

        return o.getJSONObject("values").getString("text");
    }

    public static void main(String[] args) {
        String textMsg = getTextMsg(1, "", "http://mmbiz.qpic.cn/mmbiz_jpg/eJ26rUtia39ggICgibKpoRW1ibiawfn9iaZyibYxl4LQtJBgJNcgZOFHvKWEofI0cDP6PWCjCibWMT4e17kTdkjdwkBpg/0");

        System.out.println(textMsg);
    }
}
