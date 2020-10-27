package com.poffice.printer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("application/pdf")
public class LibreOfficePDFWriter implements Writer {
    @Autowired
    WriterFactory writerFactory;

    @Autowired
    LibreOfficePDFConverter converter;

    @Override
    public void write(InputStream doc, OutputStream out) throws WriterException {
        try {
            File tempFile = File.createTempFile("topdf", ".docx");
            OutputStream tempFileOutputStream = new FileOutputStream(tempFile);
            writerFactory.getWriter("application/vnd.openxmlformats-officedocument.wordprocessingml.document").write(doc, tempFileOutputStream);
            File pdfFile = converter.convert(tempFile);
            Files.copy(pdfFile.toPath(), out);
        } catch (IOException | LibreofficeException e) {
            throw new WriterException(e.getMessage());
        }
    }
}
