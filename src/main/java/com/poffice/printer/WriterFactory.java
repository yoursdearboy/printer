package com.poffice.printer;

public interface WriterFactory {
    Writer getWriter(String name);
}
