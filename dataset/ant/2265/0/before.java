class PlaceHold {
  public static String readFully(Reader rdr, int bufferSize) throws IOException {
    if (bufferSize <= 0) {
      throw new IllegalArgumentException("Buffer size must be greater " + "than 0");
    }
    final char[] buffer = new char[bufferSize];
    int bufferLength = 0;
    StringBuffer textBuffer = null;
    while (bufferLength != (-1)) {
      bufferLength = rdr.read(buffer);
      if (bufferLength > 0) {
        textBuffer = (textBuffer == null) ? new StringBuffer() : textBuffer;
        textBuffer.append(new String(buffer, 0, bufferLength));
      }
    }
    return textBuffer == null ? "" : textBuffer.toString();
  }
}
