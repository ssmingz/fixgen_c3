class PlaceHold {
  private static void printTargets(Project project, boolean printSubTargets) {
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
    printTargets(project, topNames, topDescriptions, "Main targets:", maxLength);
    if (topNames.size() == 0) {
      printSubTargets = true;
    }
    if (printSubTargets) {
      printTargets(project, subNames, null, "Subtargets:", 0);
    }
    String defaultTarget = project.getDefaultTarget();
    if ((defaultTarget != null) && (!"".equals(defaultTarget))) {
      project.log("Default target: " + defaultTarget);
    }
  }
}
