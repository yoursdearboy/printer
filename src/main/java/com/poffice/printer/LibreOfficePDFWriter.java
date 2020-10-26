package com.poffice.printer;

import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("LibreOfficePDFWriter")
public class LibreOfficePDFWriter implements Writer {
    @Autowired
    WriterFactory writerFactory;

    @Override
    public void write(InputStream doc, OutputStream out) throws WriterException {
        writerFactory.getWriter("Docx4jXHTMLWriter").write(doc, out);
    }
}
