package com.tbagrel1.gmd.tp_3;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Tp3 {

    public static final String LUCENE_ROOT_PATH = "output/lucene";
    public static final String INPUT_FILE_PATH = "data_sources/drugbank.txt";

    public static void index() throws IOException {
        Directory memoryIndex = FSDirectory.open(Paths.get(LUCENE_ROOT_PATH));
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(memoryIndex, indexWriterConfig);
        // Document document = new Document();
    }

    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Paths.get(INPUT_FILE_PATH));
        DrugCardParser parser = new DrugCardParser(data);
        List<DrugCard> cards = parser.parse();
        System.out.println(cards);
    }
}
