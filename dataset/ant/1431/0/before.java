class PlaceHold {
  protected void fetchEntry() {
    ZipFile z = null;
    try {
      z = new ZipFile(getZipfile(), getEncoding());
      setEntry(z.getEntry(getName()));
    } catch (IOException e) {
      log(e.getMessage(), MSG_DEBUG);
      throw new BuildException(e);
    } finally {
      if (z != null) {
        try {
          z.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
