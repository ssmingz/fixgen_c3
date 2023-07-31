class PlaceHold {
  public void save(String filename, int format) {
    if (filename == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    OutputStream stream = null;
    try {
      stream = Compatibility.newFileOutputStream(filename);
    } catch (IOException e) {
      SWT.error(ERROR_IO, e);
    }
    save(stream, format);
  }
}
