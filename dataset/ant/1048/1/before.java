class PlaceHold {
  public void write(int b) throws IOException {
    byte[] buff = new byte[1];
    buff[0] = ((byte) (b & 0xff));
    write(buff, 0, 1);
  }
}
