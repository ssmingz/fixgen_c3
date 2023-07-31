class PlaceHold {
  protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
    if ((deploymentDescriptor == null) && (!isInUpdateMode())) {
      throw new BuildException("webxml attribute is required", location);
    }
    super.initZipOutputStream(zOut);
  }
}
