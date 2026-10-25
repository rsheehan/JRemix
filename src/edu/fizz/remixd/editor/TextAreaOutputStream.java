package edu.fizz.remixd.editor;

import java.io.OutputStream;

public class TextAreaOutputStream extends OutputStream {

        private final RemixdSwingWorker destination;

        public TextAreaOutputStream (RemixdSwingWorker destination) {
            this.destination = destination;
        }

        @Override
        public void write(byte[] buffer, int offset, int length) {
            final String text = new String (buffer, offset, length);
            destination.publish(text);
        }

        @Override
        public void write(int b) {
            write (new byte [] {(byte)b}, 0, 1);
        }

    }
