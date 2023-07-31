class PlaceHold {
  protected void expandFile(FileUtils fileUtils, File srcF, File dir) {
    TarInputStream tis = null;
    try {
      log((("Expanding: " + srcF) + " into ") + dir, MSG_INFO);
      tis =
          new TarInputStream(
              compression.decompress(srcF, new BufferedInputStream(new FileInputStream(srcF))));
      TarEntry te = null;
      while ((te = tis.getNextEntry()) != null) {
        extractFile(fileUtils, srcF, dir, tis, te.getName(), te.getModTime(), te.isDirectory());
      }
      log("expand complete", MSG_VERBOSE);
    } catch (IOException ioe) {
      throw new BuildException("Error while expanding " + srcF.getPath(), ioe, getLocation());
    } finally {
      if (tis != null) {
        try {
          tis.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
