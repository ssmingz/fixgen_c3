class PlaceHold {
  int GetDisplayDirectory(int aDisplayDirectory) {
    String directoryName = (directory != null) ? directory : "";
    nsEmbedString path = new nsEmbedString(directoryName);
    int[] file = new int[1];
    int rc = XPCOM.NS_NewLocalFile(path.getAddress(), true, file);
    path.dispose();
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (file[0] == 0) {
      Mozilla.error(NS_ERROR_NULL_POINTER);
    }
    XPCOM.memmove(aDisplayDirectory, file, PTR_SIZEOF);
    return XPCOM.NS_OK;
  }
}
