package com.poffice.printer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.docx4j.convert.in.xhtml.XHTMLImporter;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Component;

@Component("Docx4jXHTMLWriter")
public class Docx4jXHTMLWriter implements Writer {
    @Override
    public void write(InputStream doc, OutputStream out) throws WriterException {
        try {
            WordprocessingMLPackage wMlPackage = WordprocessingMLPackage.createPackage();
            XHTMLImporter importer = new XHTMLImporterImpl(wMlPackage);
            List<Object> content = importer.convert(doc, null);
            wMlPackage.getMainDocumentPart().getContent().addAll(content);
            wMlPackage.save(out);    
        } catch (Docx4JException e) {
            throw new WriterException();
        }
    }
}
