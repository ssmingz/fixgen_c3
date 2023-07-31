class PlaceHold {
  protected void writeLocalFileHeader(ZipEntry ze) throws IOException {
    offsets.put(ze, new ZipLong(written));
    out.write(LFH_SIG.getBytes());
    written += 4;
    if (ze.getMethod() == DEFLATED) {
      out.write(new ZipShort(20).getBytes());
      out.write(new ZipShort(8).getBytes());
    } else {
      out.write(new ZipShort(10).getBytes());
      out.write(ZERO);
    }
    written += 4;
    out.write(new ZipShort(ze.getMethod()).getBytes());
    written += 2;
    out.write(toDosTime(new Date(ze.getTime())).getBytes());
    written += 4;
    if (ze.getMethod() == DEFLATED) {
      out.write(LZERO);
      out.write(LZERO);
      out.write(LZERO);
    } else {
      out.write(new ZipLong(ze.getCrc()).getBytes());
      out.write(new ZipLong(ze.getSize()).getBytes());
      out.write(new ZipLong(ze.getSize()).getBytes());
    }
    written += 12;
    byte[] name = getBytes(ze.getName());
    out.write(new ZipShort(name.length).getBytes());
    written += 2;
    byte[] extra = ze.getLocalFileDataExtra();
    out.write(new ZipShort(extra.length).getBytes());
    written += 2;
    out.write(name);
    written += name.length;
    out.write(extra);
    written += extra.length;
    dataStart = written;
  }
}
