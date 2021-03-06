package main;

import PackerX.Packer;
import java.io.IOException;

final class App {

    public static void main(final String[] args) throws IOException {
        try {
            final Packer packer = new Packer(args);
            packer.run();
        } catch (final IllegalArgumentException ex) {
        }
    }
}
