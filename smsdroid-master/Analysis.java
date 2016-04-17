/**
 * Created by jmcadams on 4/16/16.
 *
 * The Analysis class handles the request to Watson's ToneAnalysis feature,
 * parsing the resulting JSON object using the structure of ToneObject.java
 *
 * This is very, very hacky code
 */


import com.ibm.watson.developer_cloud.service.BadRequestException;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Analysis {

    private static final String API_KEY = "8e7461e1ca2f2b75d44c41aaf2731c8f68137f0a";
    private static final String TONE_USERNAME = "7b4b01e9-5d91-4175-ae3b-6a451a1c4b89";
    private static final String TONE_PASSWORD = "AVBnymfeytnJ";


    public Analysis() { }

    public static ToneObject AnalyzeToneForString(String text) {
        ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_02_11);
        service.setUsernameAndPassword(TONE_USERNAME, TONE_PASSWORD);
        ToneAnalysis JSONObj = null;
        try {
            JSONObj = service.getTone(text);
        } catch (BadRequestException apiFail) {
            // Sometime Watson doesn't handle this request well
            // So we're just going to keep shoving it down his throat
            AnalyzeToneForString(text);
        }
        ToneObject result = null;
        try {
            result = parseAndReturnToneFromJSON(JSONObj);
        } catch (JSONException e) {
            System.out.println("I hate exception handling");
        }
        return result;
    }

    private static ToneObject parseAndReturnToneFromJSON(ToneAnalysis analysis) throws JSONException {
        JSONObject toneStructure = new JSONObject(analysis);
        JSONArray categories = toneStructure.getJSONObject("documentTone").getJSONArray("tones");
        ToneObject.ETone emotions = null;
        ToneObject.WTone writing = null;
        ToneObject.STone social = null;
        for (int i = 0; i < categories.length(); i++) {
            JSONObject object = categories.getJSONObject(i);
            JSONArray toneArray = object.getJSONArray("tones");
            if (i == 0) { // Emotional tone
                double anger = toneArray.getJSONObject(0).getDouble("score");
                double disgust = toneArray.getJSONObject(1).getDouble("score");
                double fear = toneArray.getJSONObject(2).getDouble("score");
                double joy = toneArray.getJSONObject(3).getDouble("score");
                double sadness = toneArray.getJSONObject(4).getDouble("score");
                emotions = new ToneObject.ETone(anger, disgust, fear, joy, sadness);
            }
            else if (i == 1) { // Writing tone
                double analytical = toneArray.getJSONObject(0).getDouble("score");
                double confident = toneArray.getJSONObject(1).getDouble("score");
                double tentative = toneArray.getJSONObject(2).getDouble("score");
                writing = new ToneObject.WTone(analytical, confident, tentative);
            }
            else { // Social tone
                double openness = toneArray.getJSONObject(0).getDouble("score");
                double cons = toneArray.getJSONObject(1).getDouble("score");
                double extra = toneArray.getJSONObject(2).getDouble("score");
                double agree = toneArray.getJSONObject(3).getDouble("score");
                double eRange = toneArray.getJSONObject(4).getDouble("score");
                social = new ToneObject.STone(openness, cons, extra, agree, eRange);
            }
        }
        ToneObject result = new ToneObject(emotions, writing, social);
        return result;

    }

    public static void main(String[] args) {

        ToneObject result = AnalyzeToneForString("Hello mom, I love you");
        System.out.println(result.EmotionTone.Joy);

    }
}
