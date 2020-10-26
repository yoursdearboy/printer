package com.poffice.printer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PrinterController {
    @Autowired
    private PrinterService printerService;

    @RequestMapping(value = "/*", method = RequestMethod.POST)
    public void print(HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException {
        printerService.print(request.getInputStream(),
                             response.getOutputStream(),
                             "Docx4jXHTMLWriter");
    }
}
