package com.tbagrel1.gmd.tp_3;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Tp3 {

    public static final String LUCENE_ROOT_PATH = "output/lucene";
    public static final String INPUT_FILE_PATH = "data_sources/drugbank.txt";

    public static void index(List<Document> documents) throws IOException {
        long begin = System.currentTimeMillis();
        Directory luceneDirectory = FSDirectory.open(Paths.get(LUCENE_ROOT_PATH));
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(luceneDirectory, indexWriterConfig);
        documents.stream().forEach(document -> {
            try {
                writer.addDocument(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.commit();
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println(String.format(
            "Indexing done in %f seconds.",
            ((double) (end - begin)) / 1000.0
        ));
    }

    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Paths.get(INPUT_FILE_PATH));
        RawDrugCardParser parser = new RawDrugCardParser(data);
        List<RawDrugCard> rawDrugCards = parser.parse();
        System.out.println(String.format("%d drug cards parsed", rawDrugCards.size()));
        List<Document> documents = rawDrugCards.stream()
            .map(rawDrugCard -> new DrugCard(rawDrugCard))
            .map(drugCard -> drugCard.toDocument())
            .collect(Collectors.toList());
        index(documents);
    }
}
