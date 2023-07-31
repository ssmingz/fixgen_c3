class PlaceHold {
  public void setHeader(String header) {
    if (StringUtils.isBlank(header)) {
      return;
    }
    checkHeaderNotInitialized();
    final String headerExpandedNewLines = header.replaceAll("\\\\n", "\n");
    final Reader headerReader = new StringReader(headerExpandedNewLines);
    try {
      loadHeader(headerReader);
    } catch (final IOException ex) {
      throw new ConversionException("unable to load header", ex);
    } finally {
      Closeables.closeQuietly(headerReader);
    }
  }
}
