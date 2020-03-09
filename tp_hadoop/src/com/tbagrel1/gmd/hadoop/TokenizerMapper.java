package com.tbagrel1.gmd.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import javax.naming.Context;
import java.io.IOException;
import java.util.StringTokenizer;

public class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer tokenReader = new StringTokenizer(value.toString());
        while (tokenReader.hasMoreTokens()) {
            word.set(tokenReader.nextToken());
            context.write(word, one);
        }
    }
}
