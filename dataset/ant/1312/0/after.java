class PlaceHold {
  private void writeManifest(ZipOutputStream zOut, Manifest manifest) throws IOException {
    for (Enumeration e = manifest.getWarnings(); e.hasMoreElements(); ) {
      log("Manifest warning: " + e.nextElement(), MSG_WARN);
    }
    zipDir(((Resource) (null)), zOut, "META-INF/", DEFAULT_DIR_MODE, JAR_MARKER);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(baos, Manifest.JAR_ENCODING);
    PrintWriter writer = new PrintWriter(osw);
    manifest.write(writer);
    if (writer.checkError()) {
      throw new IOException("Encountered an error writing the manifest");
    }
    writer.close();
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    try {
      super.zipFile(
          bais, zOut, MANIFEST_NAME, System.currentTimeMillis(), null, DEFAULT_FILE_MODE, null);
    } finally {
      FileUtils.close(bais);
    }
    super.initZipOutputStream(zOut);
  }
}
