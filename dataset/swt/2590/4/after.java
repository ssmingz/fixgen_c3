class PlaceHold {
  int GetFile(int aFile) {
    String filename = "";
    if (directory != null) {
      filename += directory + SEPARATOR;
    }
    if ((files != null) && (files.length > 0)) {
      filename += files[0];
    }
    nsEmbedString path = new nsEmbedString(filename);
    int[] file = new int[1];
    int rc = XPCOM.NS_NewLocalFile(path.getAddress(), 1, file);
    path.dispose();
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (file[0] == 0) {
      Mozilla.error(NS_ERROR_NULL_POINTER);
    }
    XPCOM.memmove(aFile, file, PTR_SIZEOF);
    return XPCOM.NS_OK;
  }
}
