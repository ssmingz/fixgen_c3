class PlaceHold {
  private Manifest getManifestFromJar(File jarFile) throws IOException {
    ZipFile zf = null;
    try {
      zf = new ZipFile(jarFile);
      Enumeration e = zf.entries();
      while (e.hasMoreElements()) {
        ZipEntry ze = ((ZipEntry) (e.nextElement()));
        if (ze.getName().equalsIgnoreCase(MANIFEST_NAME)) {
          InputStreamReader isr = new InputStreamReader(zf.getInputStream(ze), "UTF-8");
          return getManifest(isr);
        }
      }
      return null;
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
