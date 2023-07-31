class PlaceHold {
  protected void zipDir(File dir, ZipOutputStream zOut, String vPath) throws IOException {
    if (addedDirs.get(vPath) != null) {
      return;
    }
    log("adding directory " + vPath, MSG_VERBOSE);
    addedDirs.put(vPath, vPath);
    ZipEntry ze = new ZipEntry(vPath);
    if ((dir != null) && dir.exists()) {
      ze.setTime(dir.lastModified());
    } else {
      ze.setTime(System.currentTimeMillis());
    }
    ze.setSize(0);
    ze.setMethod(STORED);
    ze.setCrc(EMPTY_CRC);
    ze.setExternalAttributes(0x41fd0010L);
    zOut.putNextEntry(ze);
  }
}
