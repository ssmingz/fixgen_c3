class PlaceHold {
  private void writeManifest(ZipOutputStream zOut, Manifest manifest) throws IOException {
    for (Enumeration e = manifest.getWarnings(); e.hasMoreElements(); ) {
      log("Manifest warning: " + ((String) (e.nextElement())), MSG_WARN);
    }
    zipDir(null, zOut, "META-INF/", DEFAULT_DIR_MODE, JAR_MARKER);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(baos, "UTF-8");
    PrintWriter writer = new PrintWriter(osw);
    manifest.write(writer);
    writer.flush();
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    super.zipFile(bais, zOut, MANIFEST_NAME, System.currentTimeMillis(), null, DEFAULT_FILE_MODE);
    super.initZipOutputStream(zOut);
  }
}
