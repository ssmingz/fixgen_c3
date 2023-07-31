class PlaceHold {
  private static void printTargets(Project project) {
    int maxLength = 0;
    Enumeration ptargets = project.getTargets().elements();
    String targetName;
    String targetDescription;
    Target currentTarget;
    Vector topNames = new Vector();
    Vector topDescriptions = new Vector();
    Vector subNames = new Vector();
    while (ptargets.hasMoreElements()) {
      currentTarget = ((Target) (ptargets.nextElement()));
      targetName = currentTarget.getName();
      targetDescription = currentTarget.getDescription();
      if (targetDescription == null) {
        int pos = findTargetPosition(subNames, targetName);
        subNames.insertElementAt(targetName, pos);
      } else {
        int pos = findTargetPosition(topNames, targetName);
        topNames.insertElementAt(targetName, pos);
        topDescriptions.insertElementAt(targetDescription, pos);
        if (targetName.length() > maxLength) {
          maxLength = targetName.length();
        }
      }
    }
    String defaultTarget = project.getDefaultTarget();
    if ((defaultTarget != null) && (!"".equals(defaultTarget))) {
      Vector defaultName = new Vector();
      Vector defaultDesc = null;
      defaultName.addElement(defaultTarget);
      int indexOfDefDesc = topNames.indexOf(defaultTarget);
      if (indexOfDefDesc >= 0) {
        defaultDesc.addElement(topDescriptions.elementAt(indexOfDefDesc));
      }
      printTargets(defaultName, defaultDesc, "Default target:", maxLength);
    }
    printTargets(topNames, topDescriptions, "Main targets:", maxLength);
    printTargets(subNames, null, "Subtargets:", 0);
  }
}
