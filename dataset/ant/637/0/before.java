class PlaceHold {
  protected void writeCentralDirectoryEnd() throws IOException {
    out.write(EOCD_SIG.getBytes());
    out.write(ZERO);
    out.write(ZERO);
    byte[] num = new ZipShort(entries.size()).getBytes();
    out.write(num);
    out.write(num);
    out.write(cdLength.getBytes());
    out.write(cdOffset.getBytes());
    byte[] data = comment.getBytes();
    out.write(new ZipShort(data.length).getBytes());
    out.write(data);
  }
}
