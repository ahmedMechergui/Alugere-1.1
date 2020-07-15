package sample;

import javafx.scene.control.TableView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlMaker {

    public static void GenererDevis(String adresse, String rue, String email, String telephone1, String telephone2, int numDevis, TableView<Commande> table, String totalHT, double tvaPercent, String tvaValue, String totalTTC) {
        String code = "";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        String date = dateFormat.format(d);

        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date y = new Date();
        String year = yearFormat.format(y);

        int boxHeight = 352;

        String tvaPercentString = tvaPercent + "";
        tvaPercentString = tvaPercentString.substring(0, tvaPercentString.indexOf("."));


        code = code.concat("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "    <title>Document</title>\n" +
                "\n" +
                "<style>\n" +
                "    img {\n" +
                "        position: absolute;\n" +
                "        top:0;\n" +
                "        left: 0;\n" +
                "        width: 100%;\n" +
                "        z-index: -1;\n" +
                "    }\n" +
                "\n" +
                "    .numDevis{\n" +
                "                font-size: 27px;\n" +
                "                letter-spacing: 5px;\n" +
                "    }\n" +
                "\n" +
                "    .cadre {\n" +
                "                position: relative;\n" +
                "                text-align: center;\n" +
                "             /*   border:1.5px solid #CE811E; */\n" +
                "                width: 270px;\n" +
                "                padding: 15px;\n" +
                "                padding-bottom: 8px;\n" +
                "                position: relative;\n" +
                "                margin-left: 450px;\n" +
                "                top: 75px;\n" +
                "                font-weight: bold;\n" +
                "                font-family:Arial;\n" +
                "                line-height: 20px;\n" +
                "    }\n" +
                "\n" +
                "    .entrepriseInfo {\n" +
                "            position: absolute;\n" +
                "            top: 150px;\n" +
                "            left: 64px;\n" +
                "            font-weight:500;\n" +
                "            font-family:Arial, Helvetica, sans-serif;\n" +
                "            font-size: 19px;\n" +
                "            \n" +
                "    }\n" +
                "\n" +
                "    .devisNumDate {\n" +
                "        display: inline;\n" +
                "        font-size: 17px;\n" +
                "    }\n" +
                "\n" +
                "    .imageTableCover {\n" +
                "        /* \n" +
                "        t8atti biha el table li fel taswira\n" +
                "        */\n" +
                "\n" +
                "        position: absolute;\n" +
                "        background-color: white;\n" +
                "        width: 100%;\n" +
                "        height: 250px;\n" +
                "        left: 0;\n" +
                "        top: 350px;\n" +
                "        opacity: 1;\n" +
                "    } \n" +
                "\n" +
                "    .tableUp table,th,td {\n" +
                "        border-collapse: collapse;\n" +
                "        border:1.5px solid #D8D8D8;\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    .tableUp{\n" +
                "        width: 100%;\n" +
                "        position: absolute;\n" +
                "        left: 0px;\n" +
                "        right: 0px;\n" +
                "        top: 397px;\n" +
                "        opacity: 1;\n" +
                "font-family: Arial;" +
                "    }\n" +
                "\n" +
                "    table {\n" +
                "        border-collapse: collapse;\n" +
                "    }\n" +
                "\n" +
                "    .tableUp tr {\n" +
                "        height: 43px;\n" +
                "    }\n" +
                "\n" +
                "    .tableDown  {\n" +
                "        margin-left: 500px;\n" +
                "        margin-top: 133px;\n" +
                "        font-family: Arial;\n" +
                "       \n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    .tableDown td{\n" +
                "        width: 168px;\n" +
                "        text-align: left;\n" +
                "    }\n" +
                "\n" +
                "    .tableDown tr{\n" +
                "        height: 43px;\n" +
                "    }\n" +
                "\n" +
                "    .tableDown td {\n" +
                "        border:none;\n" +
                "    }\n" +
                "\n" +
                "    .box {\n" +
                "        width: 100%;\n" +
                "        border: 1px solid transparent;\n" +
                "        height: $boxHeightpx;\n" +
                "    }\n" +
                "\n" +
                "    footer {\n" +
                "        color: lightgray;\n" +
                "        text-align: center;\n" +
                "        position: fixed;\n" +
                "        bottom: 0;\n" +
                "    }\n" +
                "\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    \n" +
                "<img src=\"bg.png\" alt=\"image\"></img>\n" +
                "\n" +
                "<div class=\"cadre\">\n" +
                " \n" +
                "<span class=\"numDevis\">DEVIS N°<span id=\"year\">" + year + "</span>-" + String.format("%04d", numDevis) + "</span>\n" +
                "<div class=\"devisNumDate\">\n" +
                "<br />\n" +
                "Realisé le: <span id=\"date\">" + date + "</span>\n" +
                "    <br />\n" +
                "    Devis valable 30 jours\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<div class=\"entrepriseInfo\">\n" +
                "    " + adresse + "<br />\n" +
                "    " + rue + "<br />\n" +
                "    " + email + "<br />\n" +
                "     " + telephone1 + "<br />\n" +
                "     " + telephone2 + " \n" +
                " </div>\n" +
                "\n" +
                "<div class=\"imageTableCover\"></div>");

        code = code.concat("<table class=\"tableUp\">\n" +
                "    <tr>\n" +
                "        <th style=\"width:117px;\">Référence</th>\n" +
                "        <th style=\"width:335px;\">Désignation</th>\n" +
                "        <th style=\"width:92px\">Qté</th>\n" +
                "        <th style=\"width:140px\">Prix unitaire HT</th>\n" +
                "        <th>Montant HT</th>\n" +
                "    </tr>");


        table.getItems().size();

        for (int i = 0; i < table.getItems().size(); i++) {
            code = code.concat("<tr>\n" +
                    "        <td>" + table.getItems().get(i).getReference() + "</td>\n" +
                    "        <td>" + table.getItems().get(i).getDesignation() + "(" + CalculMan.fixDimention(table.getItems().get(i).getDimension().getLargeur()) + " cm" + " / " + CalculMan.fixDimention(table.getItems().get(i).getDimension().getHauteur()) + " cm)" + "</td>\n" +
                    "        <td>" + table.getItems().get(i).getQuantite() + "</td>\n" +
                    "        <td>" + CalculMan.doubleStringToPrix(table.getItems().get(i).getPrixUnitaire() + "") + " dt</td>\n" +
                    "        <td>" + CalculMan.doubleStringToPrix(table.getItems().get(i).getMontantHT() + "") + " dt</td>\n" +
                    "    </tr>");
            boxHeight += 43;
        }

        code = code.replace("$boxHeight", boxHeight + "");

        code = code.concat("</table>\n" +
                " <div class=\"box\"></div>\n" +
                "<table class=\"tableDown\">\n" +
                "    <tr>\n" +
                "        <td>Total HT</td>\n" +
                "        <td>" + CalculMan.doubleStringToPrix(totalHT) + " dt</td>\n" +
                "    </tr>\n");

        if (!(tvaPercent == 0)) {
            code = code.concat("    <tr>\n" +
                    "        <td>TVA à " + tvaPercentString + "%</td>\n" +
                    "        <td>" + CalculMan.doubleStringToPrix(tvaValue) + " dt</td>\n" +
                    "    </tr>\n" +
                    "    <tr style=\"font-weight: bold;\">\n" +
                    "        <td>Total TTC</td>\n" +
                    "        <td>" + CalculMan.doubleStringToPrix(totalTTC) + " dt</td>\n" +
                    "    </tr>\n");
        }

        code = code.concat("</table>\n" +
                "</body>\n" +
                "</html>");

// ta3mel el fichier html

        FileWriter fWriter = null;
        BufferedWriter writer = null;
        try {
            fWriter = new FileWriter("devistemplate.html");
            writer = new BufferedWriter(fWriter);
            writer.write(code);
            writer.newLine(); //this is not actually needed for html files - can make your code more readable though
            writer.close(); //make sure you close the writer object
        } catch (Exception e) {
            AlertClass.displayError("Un erreur a été produit en generation de devis!");
        }

// ta3mel el pdf

        Process wkhtml; // Create uninitialized process
        try {
            // wkhmltopdf.exe full path then html file full path then output file path
            String command = "\"C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf.exe\" --zoom 1.1 devistemplate.html  \"C:\\Users\\Ahmed Mechergui\\Desktop\\devis\\" + year + "-" + String.format("%04d", numDevis) + ".pdf\""; // Desired command

            wkhtml = Runtime.getRuntime().exec(command); // Start process

            wkhtml.waitFor();
            AlertClass.displayInformation("Une fichier pdf de devis nommé \"" + year + "-" + String.format("%04d", numDevis) + "\" a été enregistré Dans le dossier devis sur votre bureau");
        } catch (Exception e) {
            AlertClass.displayError("Un erreur a été produit en generation de devis!\n Vous devez verifier que il existe un dossier nommé devis sur votre bureau");
        }   // Allow process to run
    }

    public static void printDevisPdf() {
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date y = new Date();
        String year = yearFormat.format(y);

        String numDevis = String.format("%04d", FileMan.getNumDevis());

        String pdfName = year + "-" + numDevis + ".pdf";

        String command = "\"C:\\Users\\Ahmed Mechergui\\IdeaProjects\\alugere 1.1\\PDFtoPrinter.exe\" \"C:\\Users\\Ahmed Mechergui\\Desktop\\devis\\" + pdfName + "\"";
        Process printToPrinter;
        try {
            printToPrinter = Runtime.getRuntime().exec(command);
            printToPrinter.waitFor();
        } catch (Exception e) {
            AlertClass.displayError("Erreur d'impression!");
        }

    }


}
