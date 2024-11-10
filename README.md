# Model Training

In this project, we use the Stanford NLP library to perform tasks like Named Entity Recognition (NER). Follow the steps below to train the model.

## 1. Required Libraries

You will need to set up Stanford CoreNLP and some additional libraries. These dependencies might need to be installed differently on each machine. The best way to handle this is through Maven or downloading the required files manually.

## 2. Running the Training Command

To train the model, use the following command:

```bash
java -cp "C:\Users\rzaye\.m2\repository\edu\stanford\nlp\stanford-corenlp\4.5.3\stanford-corenlp-4.5.3.jar;C:\Users\rzaye\.m2\repository\edu\stanford\nlp\stanford-corenlp\4.5.3\stanford-corenlp-4.5.3-models.jar;C:\Users\rzaye\.m2\repository\com\google\code\gson\gson\2.8.8\gson-2.8.8.jar" edu.stanford.nlp.ie.crf.CRFClassifier -prop E:\djl-model\src\main\resources\config\mypropfile.prop
