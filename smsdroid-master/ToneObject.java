/**
 * Created by jmcadams on 4/16/16.
 *
 * Each call to BlueMix's Tone Analyzer service returns a JSON object
 * with three categories: emotional, writing, and social. Each category has
 * a numeric score associated with certain fields, so the ToneObject is an OOP
 * approach to store the data
 */
public class ToneObject {

    public ETone EmotionTone;
    public WTone WritingTone;
    public STone SocialTone;

    public ToneObject(ETone e, WTone w, STone s) {
        this.EmotionTone = e;
        this.WritingTone = w;
        this.SocialTone = s;
    }

    public static class ETone {

        public double Anger;
        public double Disgust;
        public double Fear;
        public double Joy;
        public double Sadness;

        public ETone(double a, double d, double f, double j, double s) {
            this.Anger = a;
            this.Disgust = d;
            this.Fear = f;
            this.Joy = j;
            this.Sadness = s;
        }
    }

    public static class WTone {

        public double Analytical;
        public double Confident;
        public double Tentative;

        public WTone(double a, double c, double t) {
            this.Analytical = a;
            this.Confident = c;
            this.Tentative = t;
        }
    }

    public static class STone {

        public double Openness;
        public double Conscientious;
        public double Extraversion;
        public double Agreeableness;
        public double EmotionalRange;

        public STone(double o, double c, double extraversion, double a, double e) {
            this.Openness = o;
            this.Conscientious = c;
            this.Extraversion = extraversion;
            this.Agreeableness = a;
            this.EmotionalRange = e;
        }
    }

}
