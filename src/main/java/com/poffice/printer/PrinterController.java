package com.poffice.printer;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class PrinterController {
    @Autowired
    private PrinterService printer;

    @RequestMapping(value = "/*", method = RequestMethod.POST)
    public void print(HttpServletRequest request, HttpServletResponse response) throws IOException, Docx4JException, Exception {
        ServletInputStream reader = request.getInputStream();
        File file = printer.print(reader);

        response.setContentLength((int) file.length());
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        String contentDisposition = String.format("attachment; filename=%s", file.getName());
        int fileSize = Long.valueOf(file.length()).intValue();

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", contentDisposition);
        response.setContentLength(fileSize);
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingm");

        try (OutputStream out = response.getOutputStream()) {
            Path path = file.toPath();
            Files.copy(path, out);
            out.flush();
        } catch (IOException e) {
            // handle exception
        }

    }
}
