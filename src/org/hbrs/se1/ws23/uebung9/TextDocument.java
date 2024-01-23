package org.hbrs.se1.ws23.uebung9;

public class TextDocument extends CoreDocument {
    enum Encoding {
        UTF8,
        UTF16,
        UTF32;
    }

    private String text;
    private Encoding encoding;
    public TextDocument(String text, Encoding encoding) {
        this.text = text;
        this.encoding = encoding;
    }
}
