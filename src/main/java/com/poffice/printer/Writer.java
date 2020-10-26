package com.poffice.printer;

import java.io.InputStream;
import java.io.OutputStream;

public interface Writer {
    public void write(InputStream doc, OutputStream out) throws WriterException;
}
