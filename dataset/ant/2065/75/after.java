class PlaceHold {
  protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
    if ((deploymentDescriptor == null) && (!isInUpdateMode())) {
      throw new BuildException("appxml attribute is required");
    }
    super.initZipOutputStream(zOut);
  }
}
