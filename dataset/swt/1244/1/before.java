class AppFileLocProvider {
  AppFileLocProvider(
      String mozillaPath, String profilePath, String cacheParentPath, boolean isXULRunner) {
    this.mozillaPath = mozillaPath + SEPARATOR_OS;
    this.profilePath = profilePath + SEPARATOR_OS;
    this.cacheParentPath = cacheParentPath;
    this.isXULRunner = isXULRunner;
    if (!Compatibility.fileExists(profilePath, "")) {
      int[] result = new int[1];
      nsEmbedString pathString = new nsEmbedString(profilePath);
      int rc = XPCOM.NS_NewLocalFile(pathString.getAddress(), 1, result);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      if (result[0] == 0) {
        Mozilla.error(NS_ERROR_NULL_POINTER);
      }
      pathString.dispose();
      nsILocalFile file = new nsILocalFile(result[0]);
      rc = file.Create(DIRECTORY_TYPE, 0700);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      file.Release();
    }
    createCOMInterfaces();
  }
}
