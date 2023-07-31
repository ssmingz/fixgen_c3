class PlaceHold {
  private void writeFile() throws BuildException {
    BufferedOutputStream bos = null;
    try {
      bos = new BufferedOutputStream(new FileOutputStream(propertyfile));
      properties.store(bos, comment);
    } catch (IOException ioe) {
      throw new BuildException(ioe, getLocation());
    } finally {
      FileUtils.close(bos);
    }
  }
}
