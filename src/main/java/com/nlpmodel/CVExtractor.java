package com.nlpmodel;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.util.*;
import java.util.*;

public class CVExtractor {
    public static void main(String[] args) {

        Annotation annotation = getAnnotation();

        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                if (ne.equals("LOCATION")) {
                    System.out.println("City: " + word);
                } else if (ne.equals("ORGANIZATION")) {
                    System.out.println("School ya da Organisation: " + word);
                } else if (ne.equals("DEPARTMENT")) {
                    System.out.println("Specialty: " + word);
                }
            }
        }
    }

    private static Annotation getAnnotation() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        props.setProperty("ner.model", "models/custom-ner-model.ser.gz");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);


        String text = "John Doe completed his studies at Harvard University and currently works as a software engineer in New York. He previously lived in San Francisco.";

        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        return annotation;
    }
}
