package main;

import com.mycompany.tiralabra_maven.HuffmanCompressor;
import com.mycompany.tiralabra_maven.HuffmanDecompressor;

final class App {

    /**
     * Käyttö:
     *
     * 1. Luo testi tekstitiedosto.
     *
     * 2. Kommentoi compress metodi käyttöön.
     *
     * 3. Aja ohjelma.
     *
     * 4. Uusi tiedosto, jonka nimi on sama kuin alkuperäinen + ".pkx", ilmestyy
     * kansioon.
     *
     * 5. Uudelleen nimeä alkuperäinen tiedosto joksikin muuksi.
     *
     * 6. Kommentoi compress metodi pois, poista kommentit deCompress metodista
     *
     * 7. Aja ohjelma
     *
     * 8. Teksti tiedosto on palautunut kansioon alkuperäisessä muodossa.
     *
     * Huom: kannattaa testata tekstitiedostoa, joka on riittävän suuri.
     * Kehityksessä on käytetty noin 90 kt kokoista, jolloin pakattu version on
     * noin 50 kt
     */
    public static void main(String[] args) {
        //compress("C:\\test.txt");
        //deCompress("C:\\test.txt.pkx");
    }

    private static void compress(final String path) {
        final HuffmanCompressor c = new HuffmanCompressor(path);
        if (!c.canBeRead()) {
            System.err.println("Can't access file...");
        } else if (c.fileExists()) {
            System.err.println("Name that would be for the compressed file already exists in the path");
        } else if (!c.create()) {
            System.err.println("Could not create file");
        } else if (!c.canWrite()) {
            System.err.println("Cannot write to the newly created file");
        } else {
            c.compressFile();
        }
    }

    private static void deCompress(final String path) {
        final HuffmanDecompressor decompressor = new HuffmanDecompressor(path);
        if (!decompressor.fileCanBeRead()) {
            System.err.println("Can't access file...");
        } else if (!decompressor.fileIsValid()) {
            System.err.println("Decompressor can only read .pkx files");
        } else if (decompressor.fileExists()) {
            System.err.println("Name that would be for the decompressed file already exists in the path");
        } else if (!decompressor.create()) {
            System.err.println("Could not create file");
        } else {
            decompressor.decompress();
        }
    }
}