/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.cppcoverage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Bugtracking",
        id = "pro.elab.cppcoverage.CppCoverageListiner"
)
@ActionRegistration(
        iconBase = "pro/elab/cppcoverage/coverage.png",
        displayName = "#CTL_CppCoverageListiner"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 0)
    ,
  @ActionReference(path = "Toolbars/File", position = 0)
})
@Messages("CTL_CppCoverageListiner=C++Coverage")
public final class CppCoverageListiner implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        currentProjectInfo cpi = new currentProjectInfo();
//        System.out.println("Name " + cpi.fileName);
        coverage c = new coverage();
        String covFile = c.findCoverageReportFile(cpi.projectDir);
        if (covFile.length() > 5) {
            c.show(cpi.getComponent(), covFile, cpi.fileName);
        }
    }
}
