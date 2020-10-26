package com.poffice.printer;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class LibreOfficePDFConverter {
    @Value("${libreoffice.path}")
    private String libreofficePath;

    public File convert(File document) throws LibreofficeException {
        int exitCode;
        try {
            Process process = buildProcess(document);
            exitCode = process.waitFor();    
        } catch (IOException | InterruptedException e) {
            throw new LibreofficeException(e.getMessage());
        }
        if (exitCode != 0) throw new LibreofficeException("Can't convert file");
        return new File(getPDFName(document));
    }

    private Process buildProcess(File file) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(libreofficePath, "--convert-to", "pdf", file.getAbsolutePath());
        builder.directory(file.getParentFile());
        return builder.start();
    }

    private String getPDFName(File file) {
        String path = file.getAbsolutePath();
        return String.format("%s.pdf", path.substring(0, path.length() - 5));
    }
}
