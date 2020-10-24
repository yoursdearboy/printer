package com.poffice.printer;

import org.docx4j.convert.in.xhtml.XHTMLImporter;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PrinterService {
    public File print(ServletInputStream body) throws InvalidFormatException, IOException, Exception {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        XHTMLImporter importer = new XHTMLImporterImpl(wordMLPackage);
        List<Object> content = importer.convert(body,"/Users/yuurrraaaa/IdeaProjects/printer/src/main/resources");
        wordMLPackage.getMainDocumentPart().getContent().addAll(content);
        File exportFile = new File("demo.docx");
        wordMLPackage.save(exportFile);

        return exportFile;
    }
}
