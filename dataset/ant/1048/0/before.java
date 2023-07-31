class PlaceHold {
  public int read() throws IOException {
    int num = this.read(this.oneBuf, 0, 1);
    return num == (-1) ? -1 : ((int) (this.oneBuf[0])) & 0xff;
  }
}
