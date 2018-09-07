/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.cppcoverage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.editor.BaseDocument;
import org.openide.text.Annotation;
import org.openide.text.Line;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;

/**
 *
 * @author eugine
 */
public class coverage {

    StyledDocument doc = null;

    void show(TopComponent t, String covFile, String fileName) {
        JTextComponent ed = EditorRegistry.lastFocusedComponent();
        doc = (StyledDocument) ed.getDocument();
        findCoverage(fileName, covFile);
//        System.out.println("Line:" + NbDocument.findLineNumber(doc, ed.getCaretPosition()));

    }

    /**
     * Mark line by color annotation
     *
     * @param doc
     * @param lineNumber
     * @param occurance
     */
    private void setLineIsCovered(StyledDocument doc, int lineNumber, int occurance) {
        int startPos = NbDocument.findLineOffset(doc, lineNumber);
        int nextPos = NbDocument.findLineOffset(doc, lineNumber + 1);
        // NbDocument.addAnnotation(doc, startPos, nextPos, annotation);
        Line myLine = NbEditorUtilities.getLine(EditorRegistry.lastFocusedComponent().getDocument(), startPos, true);
        // InsertAnnotation.DEFAULT.attach(myLine);
        if (occurance > 0) {
            new InsertAnnotation(occurance).attach(myLine);
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    String findCoverageReportFile(String fileName) {
        int position = fileName.indexOf("/build/");
        String result = "";
//        if (position > 0) {
        result = fileName + "/report.info";
        System.out.println("INFO:" + result);
//        }
        return result;
    }

    /**
     * Search in coverage report about the file
     *
     * @param fileName
     * @param reportFnile
     * @return
     */
    int findCoverage(String fileName, String reportFile) {
        String prefix = "SF:" + fileName;
        int result = 0;
        File rf = new File(reportFile);
        if (rf.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(rf));
                String line;
                int flag = 0;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(prefix)) {
                        flag = 1;
                        System.out.println("FOUND FILE INFO");
                    } else {
                        if (flag == 1) {
                            System.out.println(line);
                            if (line.startsWith("SF:")) {
                                flag = 0;
                            } else {
                                if (line.startsWith("DA:")) {
                                    System.out.println("FOUND DA");
                                    String[] lineInfo = line.substring(3).split(",");
                                    setLineIsCovered(doc, Integer.parseInt(lineInfo[0]) - 1, Integer.parseInt(lineInfo[1]));
                                }
                            }
                        }
                    }
                    result = result++;
                }
                br.close();
            } catch (Exception ex) {
                System.out.println("Exception:" + ex);
            }
        }
        System.out.println("RES:" + result + " FILE:" + reportFile);
        return result;
    }

    int[] parseCov(String fileGcno) {
        int[] lines = new int[10000];
        return lines;
    }

}
