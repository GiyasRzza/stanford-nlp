package com.nlpmodel;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.util.CoreMap;
import java.util.Properties;

public class TrainNERModel {
    public static void main(String[] args) {
        String trainFile = "src/main/resources/data/training-data.txt";
        String modelFile = "models/custom-ner-model.ser.gz";
        Properties props = new Properties();
        props.setProperty("trainFile", trainFile);
        props.setProperty("serializeTo", modelFile);
        props.setProperty("map", "word=0,answer=1");
        props.setProperty("numIters", "100");
        props.setProperty("useClassFeature", "true");
        CRFClassifier<CoreMap> classifier = new CRFClassifier<>(props);
        classifier.train();
        System.out.println("The model was successfully trained and " + modelFile + " saved to file.");
    }
}
