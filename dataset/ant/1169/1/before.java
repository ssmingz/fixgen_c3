class PlaceHold {
  protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
    zOut.setMethod(ZipOutputStream.DEFLATED);
    if (manifest != null) {
      ZipEntry ze = new ZipEntry("META-INF/");
      zOut.putNextEntry(ze);
      zipFile(manifest, zOut, "META-INF/MANIFEST.MF");
    } else {
      ZipEntry ze = new ZipEntry("META-INF/");
      zOut.putNextEntry(ze);
      String s = "/org/apache/tools/ant/defaultManifest.mf";
      InputStream in = this.getClass().getResourceAsStream(s);
      if (in == null) {
        throw new BuildException("Could not find: " + s);
      }
      zipFile(in, zOut, "META-INF/MANIFEST.MF");
    }
  }
}
