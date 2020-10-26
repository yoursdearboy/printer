package com.poffice.printer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrinterService {
    @Autowired
    WriterFactory writerFactory;

    public File print(InputStream doc) throws IOException, WriterException {
        File file = File.createTempFile("", ".docx");
        OutputStream out = new FileOutputStream(file);
        Writer writer = writerFactory.getWriter();
        writer.write(doc, out);
        return file;
    }

    public void print(InputStream doc, OutputStream out) throws WriterException {
        Writer writer = writerFactory.getWriter();
        writer.write(doc, out);
    }
}
