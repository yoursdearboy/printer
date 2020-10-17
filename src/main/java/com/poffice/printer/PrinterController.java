package com.poffice.printer;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class PrinterController {
    @RequestMapping(value = "/*", method = RequestMethod.POST)
    public void print(HttpServletRequest request, HttpServletResponse response) throws IOException, Docx4JException {
        File file = new ClassPathResource("demo.docx").getFile();
        System.out.println(file);
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
 
	byte[] result = "Hello, World!".getBytes();
        response.getOutputStream().write(result);
	response.setHeader("Content-Type", "plain/text");
    }
}
