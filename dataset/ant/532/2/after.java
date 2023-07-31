class PlaceHold {
  public synchronized int read() throws IOException {
    int result = -1;
    try {
      result = super.read();
    } catch (IOException eyeOhEx) {
      if ("write end dead".equalsIgnoreCase(eyeOhEx.getMessage())) {
        if (((super.in > 0) && (super.out < super.buffer.length)) && (super.out > super.in)) {
          result = super.buffer[super.out++] & BYTE_MASK;
        }
      } else {
        log("error at LeadPipeInputStream.read():  " + eyeOhEx.getMessage(), MSG_INFO);
      }
    }
    return result;
  }
}
