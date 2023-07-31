class PlaceHold {
  private boolean jarHasIndex(File jarFile) throws IOException {
    ZipFile zf = null;
    try {
      zf = new ZipFile(jarFile);
      Enumeration e = zf.entries();
      while (e.hasMoreElements()) {
        ZipEntry ze = ((ZipEntry) (e.nextElement()));
        if (ze.getName().toUpperCase(Locale.ENGLISH).equals(INDEX_NAME)) {
          return true;
        }
      }
      return false;
    } finally {
      if (zf != null) {
        try {
          zf.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
