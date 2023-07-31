class PlaceHold {
  public static final String readFully(Reader rdr, int bufferSize) throws IOException {
    if (bufferSize <= 0) {
      throw new IllegalArgumentException("Buffer size must be greater " + "than 0");
    }
    final char[] buffer = new char[bufferSize];
    int bufferLength = 0;
    String text = null;
    StringBuffer textBuffer = null;
    while (bufferLength != (-1)) {
      bufferLength = rdr.read(buffer);
      if (bufferLength != (-1)) {
        if (textBuffer == null) {
          textBuffer = new StringBuffer(new String(buffer, 0, bufferLength));
        } else {
          textBuffer.append(new String(buffer, 0, bufferLength));
        }
      }
    }
    if (textBuffer != null) {
      text = textBuffer.toString();
    }
    return text;
  }
}
