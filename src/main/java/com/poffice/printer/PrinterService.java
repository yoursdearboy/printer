package com.poffice.printer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.docx4j.convert.in.xhtml.XHTMLImporter;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;

@Service
public class PrinterService {
    public File print(InputStream doc) throws Docx4JException, IOException {
        WordprocessingMLPackage wMlPackage = createWMLPackage(doc);
        File file = File.createTempFile("", ".docx");
        wMlPackage.save(file);
        return file;
    }

    public void print(InputStream doc, OutputStream out) throws Docx4JException {
        WordprocessingMLPackage wMlPackage = createWMLPackage(doc);
        wMlPackage.save(out);
    }

    protected WordprocessingMLPackage createWMLPackage(InputStream doc) throws Docx4JException {
        WordprocessingMLPackage wMlPackage = WordprocessingMLPackage.createPackage();
        XHTMLImporter importer = new XHTMLImporterImpl(wMlPackage);
        List<Object> content = importer.convert(doc, null);
        wMlPackage.getMainDocumentPart().getContent().addAll(content);
        return wMlPackage;
    }
}
