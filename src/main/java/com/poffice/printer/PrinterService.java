package com.poffice.printer;

import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class PrinterService {
    public File print() throws InvalidFormatException, IOException, Exception {
        File file = new ClassPathResource("demo.docx").getFile();
        if (file == null) {
            throw new Exception("no demo file");
        }
        return file;
    }
}
