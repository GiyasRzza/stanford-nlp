package com.opencvjava.nlpms.core;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class NlpTraining {

    private  final String prop = "src/main/resources/config/mypropfile.prop";
    private  final String trainingFilepath = "src/main/resources/data/training-data.txt";
    private final String modelOutPath = "src/main/resources/model/custom-ner-model.ser.gz";

    public void trainLocal() {
        Properties props = StringUtils.propFileToProperties(prop);
        props.setProperty("serializeTo", modelOutPath);
        props.setProperty("trainFile", trainingFilepath);
        SeqClassifierFlags flags = new SeqClassifierFlags(props);
        CRFClassifier<CoreLabel> crf = new CRFClassifier<>(flags);
        crf.train();
        crf.serializeClassifier(modelOutPath);
    }

    public void trainOnline(MultipartFile trainingFile)  {
        File tempTrainingFile = new File("trainingFilepath/temp_training_data.txt");

        try (FileOutputStream fos = new FileOutputStream(tempTrainingFile)) {
            fos.write(trainingFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Properties props = StringUtils.propFileToProperties(prop);
        props.setProperty("serializeTo", modelOutPath);
        props.setProperty("trainFile", tempTrainingFile.getAbsolutePath());
        SeqClassifierFlags flags = new SeqClassifierFlags(props);
        CRFClassifier<CoreLabel> crf = new CRFClassifier<>(flags);
        crf.train();
        crf.serializeClassifier(modelOutPath);
        tempTrainingFile.delete();
    }
}
