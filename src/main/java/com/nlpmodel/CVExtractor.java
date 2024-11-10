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
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                System.out.println("Word: " + word + " POS: " + pos+" NE: " + ne);
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

    private static Annotation getAnnotation() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        props.setProperty("ner.model", "models/custom-ner-model.ser.gz");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//        String text= "I studied at Baku State University";
//        String text= "I live in New York";
        String text="Barack Obama, who was the 44th President of the United States, " +
                "delivered a powerful speech at the United Nations " +
                "Headquarters in New York City, " +
                "where he discussed global issues including climate change, " +
                "international security, " +
                "and the importance of diplomacy in " +
                "fostering peaceful relations between countries.";
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        return annotation;
    }
}
