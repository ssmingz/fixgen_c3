class PlaceHold {
  public void execute() throws BuildException {
    if ((property == null) || (property.length() == 0)) {
      throw new BuildException("no property specified");
    }
    if (destDir == null) {
      destDir = getProject().resolveFile(".");
    }
    File tfile = FILE_UTILS.createTempFile(prefix, suffix, destDir, deleteOnExit);
    getProject().setNewProperty(property, tfile.toString());
  }
}
