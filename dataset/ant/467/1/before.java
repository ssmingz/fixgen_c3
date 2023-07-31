class PlaceHold {
  protected void expandFile(FileUtils fileUtils, File srcF, File dir) {
    log((("Expanding: " + srcF) + " into ") + dir, MSG_INFO);
    ZipFile zf = null;
    FileNameMapper mapper = getMapper();
    if (!srcF.exists()) {
      throw new BuildException(
          ("Unable to expand " + srcF) + " as the file does not exist", getLocation());
    }
    try {
      zf = new ZipFile(srcF, encoding);
      Enumeration e = zf.getEntries();
      while (e.hasMoreElements()) {
        ZipEntry ze = ((ZipEntry) (e.nextElement()));
        extractFile(
            fileUtils,
            srcF,
            dir,
            zf.getInputStream(ze),
            ze.getName(),
            new Date(ze.getTime()),
            ze.isDirectory(),
            mapper);
      }
      log("expand complete", MSG_VERBOSE);
    } catch (IOException ioe) {
      throw new BuildException(
          (("Error while expanding " + srcF.getPath()) + "\n") + ioe.toString(), ioe);
    } finally {
      ZipFile.closeQuietly(zf);
    }
  }
}
