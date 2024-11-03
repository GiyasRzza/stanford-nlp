package com.nlpmodel;

public class MultiNerTest {
}
//class MultiNER {
//    private List<StanfordCoreNLP> pipelines;
//
//    public MultiNER(List<String> modelPaths) {
//        pipelines = new ArrayList<>();
//        for (String modelPath : modelPaths) {
//            Properties props = new Properties();
//            props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
//            props.setProperty("ner.model", modelPath);
//            pipelines.add(new StanfordCoreNLP(props));
//        }
//    }
//
//    public List<Annotation> annotate(String text) {
//        List<Annotation> annotations = new ArrayList<>();
//        for (StanfordCoreNLP pipeline : pipelines) {
//            Annotation annotation = new Annotation(text);
//            pipeline.annotate(annotation);
//            annotations.add(annotation);
//        }
//        return annotations;
//    }
//}


//List<String> modelPaths = Arrays.asList("models/custom-ner-model1.ser.gz", "models/custom-ner-model2.ser.gz");
//MultiNER multiNER = new MultiNER(modelPaths);
//List<Annotation> results = multiNER.annotate("sadasdasdasdad");
