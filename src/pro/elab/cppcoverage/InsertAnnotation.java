/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.cppcoverage;

import org.openide.text.Annotation;

/**
 *
 * @author eugine
 */
final class InsertAnnotation extends Annotation {

//    static final InsertAnnotation DEFAULT = new InsertAnnotation();
    int occurance;

    InsertAnnotation(int oc) {
        super();
        occurance = oc;

    }

    public String getAnnotationType() {
        return "org-netbeans-modules-cppcoverage";
    }

    public String getShortDescription() {
        return "Is covered " + String.valueOf(occurance) + " times";
    }
}
