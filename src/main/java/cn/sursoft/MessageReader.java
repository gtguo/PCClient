package cn.sursoft;

import java.io.IOException;
import java.io.InputStream;

class MessageReader {
    private InputStream in;

    public MessageReader(InputStream in) {
        this.in = in;
    }

    public Wire.Envelope read() throws IOException {
        return Wire.Envelope.parseDelimitedFrom(in);
    }
}
