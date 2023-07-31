class PlaceHold {
  protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
    if (libraryDescriptor == null) {
      throw new BuildException("antxml attribute is required", getLocation());
    }
    super.initZipOutputStream(zOut);
  }
}
