class PlaceHold {
  public ImageData[] load(String filename) {
    if (filename == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    InputStream stream = null;
    try {
      stream = Compatibility.newFileInputStream(filename);
      return load(stream);
    } catch (IOException e) {
      SWT.error(ERROR_IO, e);
    } finally {
      try {
        if (stream != null) {
          stream.close();
        }
      } catch (IOException e) {
      }
    }
    return null;
  }
}
