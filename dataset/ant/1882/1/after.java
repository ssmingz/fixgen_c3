class PlaceHold {
  private void pack() throws TaskException {
    OutputStream output = null;
    try {
      final FileOutputStream fileOutput = new FileOutputStream(getZipFile());
      output = getPackingStream(fileOutput);
      copy(getSrc(), output);
    } catch (final IOException ioe) {
      final String message =
          (("Problem creating " + getContext().getName()) + ":") + ioe.getMessage();
      throw new TaskException(message, ioe);
    } finally {
      IOUtil.shutdownStream(output);
    }
  }
}
