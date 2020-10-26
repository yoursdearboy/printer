package com.poffice.printer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PrinterController {
    @Autowired
    private PrinterService printerService;

    @RequestMapping(value = "/*", method = RequestMethod.POST)
    public void print(HttpServletRequest request, HttpServletResponse response) throws Docx4JException, IOException {
        String contentDisposition = String.format("attachment; filename=%s", "response.docx");
        response.setHeader("Content-Disposition", contentDisposition);
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingm");
        printerService.print(request.getInputStream(),
                             response.getOutputStream());
        response.setHeader("Content-Length", String.valueOf(response.getBufferSize()));
    }
}
