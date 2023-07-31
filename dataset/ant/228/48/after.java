class PlaceHold {
  public void putNextEntry(ZipEntry ze) throws IOException {
    closeEntry();
    entry = ze;
    entries.add(entry);
    if (entry.getMethod() == (-1)) {
      entry.setMethod(method);
    }
    if (entry.getTime() == (-1)) {
      entry.setTime(System.currentTimeMillis());
    }
    if (entry.getMethod() == STORED) {
      if (entry.getSize() == (-1)) {
        throw new ZipException("uncompressed size is required for STORED method");
      }
      if (entry.getCrc() == (-1)) {
        throw new ZipException("crc checksum is required for STORED method");
      }
      entry.setComprSize(entry.getSize());
    } else {
      def.setLevel(level);
    }
    writeLocalFileHeader(entry);
  }
}
