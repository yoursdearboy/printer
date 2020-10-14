package com.poffice.printer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PrinterController {
    @RequestMapping(value = "/*", method = RequestMethod.POST)
    public void print(HttpServletRequest request, HttpServletResponse response) throws IOException {
	byte[] result = "Hello, World!".getBytes();
        response.getOutputStream().write(result);
	response.setHeader("Content-Type", "plain/text");
    }
}
