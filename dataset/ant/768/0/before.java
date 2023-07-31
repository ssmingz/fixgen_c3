class PlaceHold {
  public void execute() {
    if (file == null) {
      throw new BuildException("import requires file attribute");
    }
    if ((getOwningTarget() == null) || (!"".equals(getOwningTarget().getName()))) {
      throw new BuildException("import only allowed as a top-level task");
    }
    ProjectHelper helper = ((ProjectHelper) (getProject().getReference("ant.projectHelper")));
    Vector importStack = helper.getImportStack();
    if (importStack.size() == 0) {
      throw new BuildException("import requires support in ProjectHelper");
    }
    if ((getLocation() == null) || (getLocation().getFileName() == null)) {
      throw new BuildException("Unable to get location of import task");
    }
    File buildFile = new File(getLocation().getFileName());
    buildFile = new File(buildFile.getAbsolutePath());
    getProject()
        .log((("Importing file " + file) + " from ") + buildFile.getAbsolutePath(), MSG_VERBOSE);
    File buildFileParent = new File(buildFile.getParent());
    File importedFile = FILE_UTILS.resolveFile(buildFileParent, file);
    if (!importedFile.exists()) {
      String message = (("Cannot find " + file) + " imported from ") + buildFile.getAbsolutePath();
      if (optional) {
        getProject().log(message, MSG_VERBOSE);
        return;
      } else {
        throw new BuildException(message);
      }
    }
    importedFile = new File(getPath(importedFile));
    if (importStack.contains(importedFile)) {
      getProject().log(("Skipped already imported file:\n   " + importedFile) + "\n", MSG_WARN);
      return;
    }
    try {
      helper.parse(getProject(), importedFile);
    } catch (BuildException ex) {
      throw ProjectHelper.addLocationToBuildException(ex, getLocation());
    }
  }
}
