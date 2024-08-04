package edu.fizz.remix.editor;

import javax.swing.*;
import java.io.OutputStream;

public class TextAreaOutputStream extends OutputStream {

        private final JTextArea destination;

        public TextAreaOutputStream (JTextArea destination) {
            this.destination = destination;
        }

        @Override
        public void write(byte[] buffer, int offset, int length) {
            final String text = new String (buffer, offset, length);
            SwingUtilities.invokeLater(new Runnable ()
            {
                @Override
                public void run()
                {
                    if (destination == RemixEditor.systemOutput)
                        RemixEditor.expandSystemOutputPanel();
                    destination.append (text);
                }
            });
        }

        @Override
        public void write(int b) {
            write (new byte [] {(byte)b}, 0, 1);
        }

    }
