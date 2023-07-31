class PlaceHold {
  protected void zipFile(
      InputStream is,
      ZipOutputStream zOut,
      String vPath,
      long lastModified,
      File fromArchive,
      int mode,
      ZipExtraField[] extra)
      throws IOException {
    if (MANIFEST_NAME.equalsIgnoreCase(vPath)) {
      if (isFirstPass()) {
        filesetManifest(fromArchive, is);
      }
    } else if (INDEX_NAME.equalsIgnoreCase(vPath) && index) {
      logWhenWriting(
          (((("Warning: selected " + archiveType) + " files include a ") + INDEX_NAME)
                  + " which will")
              + " be replaced by a newly generated one.",
          MSG_WARN);
    } else {
      if (index && (vPath.indexOf("/") == (-1))) {
        rootEntries.addElement(vPath);
      }
      super.zipFile(is, zOut, vPath, lastModified, fromArchive, mode, extra);
    }
  }
}
