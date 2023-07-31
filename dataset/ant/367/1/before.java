class PlaceHold {
  public void close() throws IOException {
    OutputStream out = this.out;
    if (out != null) {
      try {
        if (this.runLength > 0) {
          writeRun();
        }
        this.currentChar = -1;
        endBlock();
        endCompression();
        out.close();
      } finally {
        this.out = null;
        this.data = null;
      }
    }
  }
}
