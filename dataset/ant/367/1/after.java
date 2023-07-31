class PlaceHold {
  public void close() throws IOException {
    OutputStream outShadow = this.out;
    if (outShadow != null) {
      try {
        if (this.runLength > 0) {
          writeRun();
        }
        this.currentChar = -1;
        endBlock();
        endCompression();
        outShadow.close();
      } finally {
        this.out = null;
        this.data = null;
      }
    }
  }
}
