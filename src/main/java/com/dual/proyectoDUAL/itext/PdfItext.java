package com.dual.proyectoDUAL.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PdfItext {

    public void createPDF(String fileName, String text) throws IOException, DocumentException, URISyntaxException {

        Path path = Paths.get(ClassLoader.getSystemResource("img/Check.png").toURI());

        Document document = new Document();
        //PdfWriter pdfWriter =
        PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
        //Forma de encriptacion de fichero previo a su creacion
        // pdfWriter.setEncryption("user".getBytes(), "1234".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);

        document.open();

        Paragraph paragraph = createParagraph(text);
        Image image = createImage(path, 50);

        PdfPTable pdfPTable = new PdfPTable(4);
        pdfPTable.setSpacingBefore(10f);
        addTableHeaders(pdfPTable);
        addTableSimpleRows(pdfPTable);
        addTableCustomRows(pdfPTable);

        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        document.add(image);
        document.add(pdfPTable);
        document.close();

        //Forma de encriptacion de fichero posterior a su creacion
        PdfReader pdfReader = new PdfReader(fileName + ".pdf");
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(fileName + "_encrypted.pdf"));
        pdfStamper.setEncryption("user".getBytes(), "1234".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);
        pdfStamper.close();

    }

    private Image createImage(Path path, float scale) throws BadElementException, IOException {
        Image image = Image.getInstance(path.toAbsolutePath().toString());
        image.scalePercent(scale);
        return image;
    }

    private Paragraph createParagraph(String text) {
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.RED);
        //Chunk chunk = new Chunk(text, font);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(10f);
        return paragraph;
    }

    private void addTableHeaders(PdfPTable pdfPTable) {
        Stream.of("Foto","Nombre", "Apellido", "Edad").forEach(nombreColumna -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.GREEN);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(nombreColumna));
            pdfPTable.addCell(header);
        });
    }


    private void addTableSimpleRows(PdfPTable pdfPTable) throws URISyntaxException, BadElementException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("img/default-avatar.png").toURI());
        pdfPTable.addCell(createImage(path, 20));
        pdfPTable.addCell("Ismael");
        pdfPTable.addCell("Orellana");
        pdfPTable.addCell("19");
    }

    private void addTableCustomRows(PdfPTable pdfPTable) throws URISyntaxException, BadElementException, IOException {

        Path path = Paths.get(ClassLoader.getSystemResource("img/default-avatar.png").toURI());
        PdfPCell columnFoto = new PdfPCell(createImage(path, 20));
        columnFoto.setBackgroundColor(BaseColor.MAGENTA);
        columnFoto.setBorderWidth(1);
        columnFoto.setHorizontalAlignment(Element.ALIGN_CENTER);
        columnFoto.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPTable.addCell(columnFoto);

        PdfPCell columnNombre = new PdfPCell(new Phrase("Natalia"));
        columnNombre.setBackgroundColor(BaseColor.MAGENTA);
        columnNombre.setBorderWidth(1);
        columnNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
        columnNombre.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPTable.addCell(columnNombre);

        PdfPCell columnApellido = new PdfPCell(new Phrase("Castillo"));
        columnApellido.setBackgroundColor(BaseColor.MAGENTA);
        columnApellido.setBorderWidth(1);
        columnApellido.setHorizontalAlignment(Element.ALIGN_CENTER);
        columnApellido.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPTable.addCell(columnApellido);

        PdfPCell columnEdad = new PdfPCell(new Phrase("45"));
        columnEdad.setBackgroundColor(BaseColor.MAGENTA);
        columnEdad.setBorderWidth(1);
        columnEdad.setHorizontalAlignment(Element.ALIGN_CENTER);
        columnEdad.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPTable.addCell(columnEdad);
    }

    public static void main(String[] args) throws DocumentException, IOException, URISyntaxException {
        new PdfItext().createPDF("Nuevo usuario", "Gracias por registrarte en nuestra aplicación");
    }

}
