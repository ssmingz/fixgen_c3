class PlaceHold {
  protected boolean createEmptyZip(File zipFile) throws BuildException {
    if (!createEmpty) {
      return true;
    }
    if (emptyBehavior.equals("skip")) {
      if (!skipWriting) {
        log(
            ((("Warning: skipping " + archiveType) + " archive ") + zipFile)
                + " because no files were included.",
            MSG_WARN);
      }
      return true;
    } else if (emptyBehavior.equals("fail")) {
      throw new BuildException(
          ((("Cannot create " + archiveType) + " archive ") + zipFile)
              + ": no files were included.",
          getLocation());
    }
    ZipOutputStream zOut = null;
    try {
      if (!skipWriting) {
        log("Building MANIFEST-only jar: " + getDestFile().getAbsolutePath());
      }
      zOut = new ZipOutputStream(getDestFile());
      zOut.setEncoding(getEncoding());
      zOut.setUseZip64(getZip64Mode().getMode());
      if (isCompress()) {
        zOut.setMethod(DEFLATED);
      } else {
        zOut.setMethod(STORED);
      }
      initZipOutputStream(zOut);
      finalizeZipOutputStream(zOut);
    } catch (IOException ioe) {
      throw new BuildException(
          (("Could not create almost empty JAR archive" + " (") + ioe.getMessage()) + ")",
          ioe,
          getLocation());
    } finally {
      FileUtils.close(zOut);
      createEmpty = false;
    }
    return true;
  }
}
