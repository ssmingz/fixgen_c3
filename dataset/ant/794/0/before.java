class PlaceHold {
  public void execute() throws BuildException {
    if (extensionPoint == null) {
      throw new BuildException("extensionPoint required", getLocation());
    }
    if ((getOwningTarget() == null) || (!"".equals(getOwningTarget().getName()))) {
      throw new BuildException("bindtargets only allowed as a top-level task");
    }
    if (onMissingExtensionPoint == null) {
      onMissingExtensionPoint = OnMissingExtensionPoint.FAIL;
    }
    ProjectHelper helper = ((ProjectHelper) (getProject().getReference(PROJECTHELPER_REFERENCE)));
    for (Iterator<String> itTarget = targets.iterator(); itTarget.hasNext(); ) {
      helper
          .getExtensionStack()
          .add(new String[] {extensionPoint, itTarget.next(), onMissingExtensionPoint.name()});
    }
  }
}
