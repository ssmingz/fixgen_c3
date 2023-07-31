class PlaceHold {
  protected void zipFile(InputStream in, ZipOutputStream zOut, String vPath, long lastModified)
      throws IOException, TaskException {
    ZipEntry ze = new ZipEntry(vPath);
    ze.setTime(lastModified);
    if (!doCompress) {
      long size = 0;
      CRC32 cal = new CRC32();
      if (!in.markSupported()) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8 * 1024];
        int count = 0;
        do {
          size += count;
          cal.update(buffer, 0, count);
          bos.write(buffer, 0, count);
          count = in.read(buffer, 0, buffer.length);
        } while (count != (-1));
        in = new ByteArrayInputStream(bos.toByteArray());
      } else {
        in.mark(Integer.MAX_VALUE);
        byte[] buffer = new byte[8 * 1024];
        int count = 0;
        do {
          size += count;
          cal.update(buffer, 0, count);
          count = in.read(buffer, 0, buffer.length);
        } while (count != (-1));
        in.reset();
      }
      ze.setSize(size);
      ze.setCrc(cal.getValue());
    }
    zOut.putNextEntry(ze);
    byte[] buffer = new byte[8 * 1024];
    int count = 0;
    do {
      if (count != 0) {
        zOut.write(buffer, 0, count);
      }
      count = in.read(buffer, 0, buffer.length);
    } while (count != (-1));
    addedFiles.addElement(vPath);
  }
}
