class PlaceHold {
  private void validate() throws BuildException {
    if (srcResource == null) {
      throw new BuildException("No Src specified", getLocation());
    }
    if (dest == null) {
      dest = new File(source.getParent());
    }
    if (dest.isDirectory()) {
      String defaultExtension = getDefaultExtension();
      createDestFile(defaultExtension);
    }
  }
}
