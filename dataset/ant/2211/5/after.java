class PlaceHold {
  private void validate() throws BuildException {
    if (srcResource == null) {
      throw new BuildException("No Src specified", getLocation());
    }
    if (dest == null) {
      if (source == null) {
        throw new BuildException(
            "dest is required when using a non-filesystem source", getLocation());
      }
      dest = new File(source.getParent());
    }
    if (dest.isDirectory()) {
      String defaultExtension = getDefaultExtension();
      createDestFile(defaultExtension);
    }
  }
}
