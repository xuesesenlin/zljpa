package com.zlj.zl.util.weather;

/**
 * @author ld
 * @name 天气
 * @table
 * @remarks
 */
public class WeatherUtils {

//    public ResponseResult<String> init_data(String cityName) throws Exception{
//        //参数url化
//        String city = java.net.URLEncoder.encode(cityName, "utf-8");
//
//        String apiUrl = String.format("http://www.sojson.com/open/api/weather/json.shtml?city=%s", city);
//        //开始请求
//        URL url = new URL(apiUrl);
//        URLConnection open = url.openConnection();
//        InputStream input = open.getInputStream();
//        //这里转换为String，带上包名，怕你们引错包
//        String result = org.apache.commons.io.IOUtils.toString(input, "utf-8");
////        ByteArrayOutputStream baos = new ByteArrayOutputStream();
////        int i = -1;
////        while ((i = input.read()) != -1) {
////            baos.write(i);
////        }
//
//        JSONObject json = new JSONObject(result);
//        QxxxModel q1 = new QxxxModel(),
//                q2 = new QxxxModel(),
//                q3 = new QxxxModel(),
//                q4 = new QxxxModel(),
//                q5 = new QxxxModel(),
//                q6 = new QxxxModel();
//        Timestamp timestamp1 = new Timestamp(Dates2.getPreviousDate(new Date()).getTime()),
//                timestamp2 = new Timestamp(new Date().getTime()),
//                timestamp3 = new Timestamp(Dates2.getPreviousDate(new Date(), 1).getTime()),
//                timestamp4 = new Timestamp(Dates2.getPreviousDate(new Date(), 2).getTime()),
//                timestamp5 = new Timestamp(Dates2.getPreviousDate(new Date(), 3).getTime()),
//                timestamp6 = new Timestamp(Dates2.getPreviousDate(new Date(), 4).getTime());
//        String bs = GetUuid.getUUID();
////        yesterday":{"date":"26日星期二","sunrise":"09:44","high":"高温 -7.0℃","low":"低温 -13.0℃","sunset":"18:38","
//// aqi":135.0,"fx":"无持续风向","fl":"<3级","type":"多云","notice":"悠悠的云里有淡淡的诗"}
//        JSONObject datas = json.getJSONObject("data");
//        JSONObject yesterday = datas.getJSONObject("yesterday");
//        q1.setUuid(GetUuid.getUUID());
//        q1.setCs(json.getString("city"));
//        q1.setDates(timestamp1);
//        q1.setZgwd(Double.valueOf(StringToNumber.stn(yesterday.getString("high"))));
//        q1.setZdwd(Double.valueOf(StringToNumber.stn(yesterday.getString("low"))));
//        q1.setFx(yesterday.getString("fx"));
//        q1.setFj(yesterday.getString("fl"));
//        q1.setTq(yesterday.getString("type"));
//        q1.setBs(bs);
////"forecast":[{"date":"27日星期三","sunrise":"09:45","high":"高温 -4.0℃","low":"低温 -13.0℃","sunset":"18:39",
//// "aqi":149.0,"fx":"西北风","fl":"3-4级","type":"小雪","notice":"雪花飘飘的风景来了，注意防寒哦"}
//        JSONArray forecast = datas.getJSONArray("forecast");
////当天
//        q2.setUuid(GetUuid.getUUID());
//        q2.setCs(json.getString("city"));
//        q2.setDates(timestamp2);
//        q2.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(0).getString("high"))));
//        q2.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(0).getString("low"))));
//        q2.setFx(forecast.getJSONObject(0).getString("fx"));
//        q2.setFj(forecast.getJSONObject(0).getString("fl"));
//        q2.setTq(forecast.getJSONObject(0).getString("type"));
//        q2.setBs(bs);
//
////        未来
//        q3.setUuid(GetUuid.getUUID());
//        q3.setCs(json.getString("city"));
//        q3.setDates(timestamp3);
//        q3.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(1).getString("high"))));
//        q3.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(1).getString("low"))));
//        q3.setFx(forecast.getJSONObject(1).getString("fx"));
//        q3.setFj(forecast.getJSONObject(1).getString("fl"));
//        q3.setTq(forecast.getJSONObject(1).getString("type"));
//        q3.setBs(bs);
//
//        q4.setUuid(GetUuid.getUUID());
//        q4.setCs(json.getString("city"));
//        q4.setDates(timestamp4);
//        q4.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(2).getString("high"))));
//        q4.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(2).getString("low"))));
//        q4.setFx(forecast.getJSONObject(2).getString("fx"));
//        q4.setFj(forecast.getJSONObject(2).getString("fl"));
//        q4.setTq(forecast.getJSONObject(2).getString("type"));
//        q4.setBs(bs);
//
//        q5.setUuid(GetUuid.getUUID());
//        q5.setCs(json.getString("city"));
//        q5.setDates(timestamp5);
//        q5.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(3).getString("high"))));
//        q5.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(3).getString("low"))));
//        q5.setFx(forecast.getJSONObject(3).getString("fx"));
//        q5.setFj(forecast.getJSONObject(3).getString("fl"));
//        q5.setTq(forecast.getJSONObject(3).getString("type"));
//        q5.setBs(bs);
//
//        q6.setUuid(GetUuid.getUUID());
//        q6.setCs(json.getString("city"));
//        q6.setDates(timestamp6);
//        q6.setZgwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(4).getString("high"))));
//        q6.setZdwd(Double.valueOf(StringToNumber.stn(forecast.getJSONObject(4).getString("low"))));
//        q6.setFx(forecast.getJSONObject(4).getString("fx"));
//        q6.setFj(forecast.getJSONObject(4).getString("fl"));
//        q6.setTq(forecast.getJSONObject(4).getString("type"));
//        q6.setBs(bs);
//
////        放入map，mapper只认map
//        Map<Integer, QxxxModel> map = new HashMap<>();
//        map.put(1, q1);
//        map.put(2, q2);
//        map.put(3, q3);
//        map.put(4, q4);
//        map.put(5, q5);
//        map.put(6, q6);
//
//        int n = qxxxService.add(map);
//
//        return n + "";

//    }
}
