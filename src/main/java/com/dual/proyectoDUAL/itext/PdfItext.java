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
    public void createPDF(String fileName, String nombre, String email, String text) throws IOException, DocumentException, URISyntaxException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        Paragraph paragraph = createParagraph(text);

        PdfPTable pdfPTable = new PdfPTable(2);
        pdfPTable.setSpacingBefore(10f);

        document.add(Chunk.NEWLINE);

        addTableHeaders(pdfPTable);
        addTableSimpleRows(pdfPTable, nombre, email);

        document.add(paragraph);
        document.add(pdfPTable);
        document.add(Chunk.NEWLINE);
        document.close();
    }

    private Paragraph createParagraph(String text) {
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(10f);
        return paragraph;
    }

    private void addTableHeaders(PdfPTable pdfPTable) {
        Stream.of("Nombre", "Correo").forEach(nombreColumna -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(nombreColumna));
            pdfPTable.addCell(header);
        });
    }

    private void addTableSimpleRows(PdfPTable pdfPTable, String nombre, String email) throws URISyntaxException, BadElementException, IOException {
        pdfPTable.addCell(nombre);
        pdfPTable.addCell(email);
    }
}