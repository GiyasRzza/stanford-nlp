package com.careerhive.file_service.service;

import com.careerhive.file_service.core.NlpTraining;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class NlpService {
    private final NlpTraining nlpTraining;
    private StanfordCoreNLP pipeline;

    @PostConstruct
    public void init() {
        loadPipeline();
    }


    private void loadPipeline() {
        try {
            Properties props = new Properties();
            props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
            props.setProperty("ner.model", "src/main/resources/model/custom-ner-model.ser.gz");

            pipeline = new StanfordCoreNLP(props);
            System.out.println("Model yüklendi!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extractEntities(String text) {
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                System.out.println("Word: " + word + " POS: " + pos + " NE: " + ne);
                switch (ne) {
                    case "LOCATION", "STATE_OR_PROVINCE" -> System.out.println("\033[31mLocation: \033[0m" + word);
                    case "ORGANIZATION" -> System.out.println("\033[32mOrganization: \033[0m" + word);
                    case "PERSON" -> System.out.println("\033[34mPerson: \033[0m" + word);
                    case "DEPARTMENT" -> System.out.println("\033[33mDepartment: \033[0m" + word);
                }
                System.out.println("---------------------------------------");
            }
        }
    }

    public void modelTraining(){
        nlpTraining.trainLocal();
        loadPipeline();

    }
    public void modelTraining(MultipartFile file) {
        nlpTraining.trainOnline(file);
        loadPipeline();
    }
}
