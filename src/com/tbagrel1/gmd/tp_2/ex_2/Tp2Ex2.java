package com.tbagrel1.gmd.tp_2.ex_2;

import com.tbagrel1.gmd.tp_2.PersonalInformation;
import com.tbagrel1.gmd.utils.CsvWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Tp2Ex2 {
    public static void main(String[] args) {
        convertXmlCsv(Paths.get("data_sources/italie.xml"), false);
        convertXmlCsv(Paths.get("data_sources/espagne.xml"), false);
        convertXmlCsv(Paths.get("data_sources/angleterre.xml"), false);
    }

    public static void convertXmlCsv(Path filePath, boolean show) {
        try {
            PeopleXmlConnector xmlConnector = new PeopleXmlConnector(filePath);
            List<PersonalInformation> allInfo = xmlConnector.getAllInfo();

            if (show) {
                System.out.println("### Personal Information ###");
                for (PersonalInformation info : allInfo) {
                    System.out.println(info);
                }
            }

            Path outputPath = Paths.get("output/" + filePath.getFileName().toString().replace(".xml", "") + ".csv");
            CsvWriter csvWriter = new CsvWriter(outputPath);

            allInfo.stream()
                .map(info -> info.toTuple())
                .forEach(tuple -> {
                    try {
                        csvWriter.write(tuple);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            csvWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
