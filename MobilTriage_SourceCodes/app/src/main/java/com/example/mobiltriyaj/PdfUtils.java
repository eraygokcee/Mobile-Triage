package com.example.mobiltriyaj;

import com.example.mobiltriyaj.Class.Profile;
import com.example.mobiltriyaj.Class.QuestionAnswerItem;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.util.List;

public class PdfUtils {

    public static void createPdf(String dest, List<Object> itemList, String selected_lang) throws Exception {
        File file = new File(dest);
        file.getParentFile().mkdirs();

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        // Türkçe karakterleri destekleyen yazı tipini yükleyin
        PdfFont font = PdfFontFactory.createFont("/assets/Geliat-ExtraLightRegular.otf", "Identity-H", true);

        // Başlık
        String titleStr = selected_lang.equals("tr") ? "Hasta Bilgileri" : "Patient İnformation";
        Text titleText = new Text(titleStr)
                .setFont(font)
                .setFontSize(30)
                .setBold()
                .setFontColor(new DeviceRgb(0, 0, 0));
        Paragraph title = new Paragraph().add(titleText).setTextAlignment(TextAlignment.CENTER);
        doc.add(title);

        // Boşluk
        doc.add(new Paragraph("\n"));



        // Profil bilgilerini metin olarak ekle
        for (Object item : itemList) {
            if (item instanceof Profile) {
                Profile profile = (Profile) item;

                if (selected_lang.equals("tr")) {
                    addProfileText(doc, "Adı:", profile.getAdı(), font);
                    addProfileText(doc, "Soyadı:", profile.getSoyadı(), font);
                    addProfileText(doc, "Boyu:", profile.getBoyu(), font);
                    addProfileText(doc, "Kilosu:", profile.getKilosu(), font);
                    addProfileText(doc, "Doğum Tarihi:", profile.getDogumTarihi(), font);
                    addProfileText(doc, "Cinsiyet:", profile.getCinsiyet(), font);
                    addProfileText(doc, "Kan Grubu:", profile.getKanGrubu(), font);
                } else {
                    addProfileText(doc, "Name:", profile.getAdı(), font);
                    addProfileText(doc, "Surname:", profile.getSoyadı(), font);
                    addProfileText(doc, "Height:", profile.getBoyu(), font);
                    addProfileText(doc, "Weight:", profile.getKilosu(), font);
                    addProfileText(doc, "Birth Date:", profile.getDogumTarihi(), font);
                    addProfileText(doc, "Gender:", profile.getCinsiyet(), font);
                    addProfileText(doc, "Blood Type:", profile.getKanGrubu(), font);
                }

                // Boşluk
                doc.add(new Paragraph("\n"));
            }
        }
        SolidLine Line = new SolidLine(1f);
        Line.setColor(new DeviceRgb(0, 0, 0));
        LineSeparator ls = new LineSeparator(Line);
        doc.add(ls);
        doc.add(new Paragraph("\n"));

        String titleStr2 = selected_lang.equals("tr") ? "Anemnez" : "Anamnesis";
        Text titleText2 = new Text(titleStr2)
                .setFont(font)
                .setFontSize(30)
                .setBold()
                .setFontColor(new DeviceRgb(0, 0, 0));
        Paragraph title2 = new Paragraph().add(titleText2).setTextAlignment(TextAlignment.CENTER);
        doc.add(title2);


        // Soru ve cevapları ekleme
        for (Object item : itemList) {
            if (item instanceof QuestionAnswerItem) {
                QuestionAnswerItem questionAndAnswer = (QuestionAnswerItem) item;

                String questionStr = selected_lang.equals("tr") ? "Soru: " : "Question: ";
                String answerStr = selected_lang.equals("tr") ? "Cevap: " : "Answer: ";

                Text questionText = new Text(questionStr + questionAndAnswer.getQuestion() + "\n")
                        .setFont(font)
                        .setFontSize(18)
                        .setBold()
                        .setFontColor(new DeviceRgb(0, 0, 0));
                Text answerText = new Text(answerStr + questionAndAnswer.getAnswer() + "\n")
                        .setFont(font)
                        .setFontSize(16)
                        .setFontColor(new DeviceRgb(0, 0, 0));

                Paragraph question = new Paragraph().add(questionText);
                Paragraph answer = new Paragraph().add(answerText);

                doc.add(question);
                doc.add(answer);
                doc.add(new Paragraph("\n")); // Boşluk ekleme
            }
        }

        doc.close();
    }

    private static void addProfileText(Document doc, String label, String value, PdfFont font) {
        Text labelText = new Text(label)
                .setFont(font)
                .setFontSize(18)
                .setBold()
                .setFontColor(new DeviceRgb(0, 0, 0));
        Text valueText = new Text(value)
                .setFont(font)
                .setFontSize(16)
                .setFontColor(new DeviceRgb(0, 0, 0));

        Paragraph paragraph = new Paragraph().add(labelText).add(" ").add(valueText);
        doc.add(paragraph);
    }
}
