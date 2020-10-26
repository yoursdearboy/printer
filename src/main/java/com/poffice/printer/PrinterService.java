package com.poffice.printer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

@Service
public class PrinterService {
    public File print(InputStream doc) throws IOException, WriterException {
        Docx4jXHTMLWriter writer = new Docx4jXHTMLWriter();
        File file = File.createTempFile("", ".docx");
        OutputStream out = new FileOutputStream(file);
        writer.write(doc, out);
        return file;
    }

    public void print(InputStream doc, OutputStream out) throws WriterException {
        Docx4jXHTMLWriter writer = new Docx4jXHTMLWriter();
        writer.write(doc, out);
    }
}
