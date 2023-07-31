class PlaceHold {
  int getFile(long prop, long persistent, long _retval) {
    int size = XPCOM.strlen(prop);
    byte[] bytes = new byte[size];
    XPCOM.memmove(bytes, prop, size);
    String propertyName = new String(MozillaDelegate.mbcsToWcs(null, bytes));
    String propertyValue = null;
    if (propertyName.equals(NS_APP_HISTORY_50_FILE)) {
      propertyValue = profilePath + HISTORY_FILE;
    } else if (propertyName.equals(NS_APP_USER_MIMETYPES_50_FILE)) {
      propertyValue = profilePath + MIMETYPES_FILE;
    } else if (propertyName.equals(NS_APP_PREFS_50_FILE)) {
      propertyValue = profilePath + PREFERENCES_FILE;
    } else if (propertyName.equals(NS_APP_PREFS_50_DIR)) {
      propertyValue = profilePath;
    } else if (propertyName.equals(NS_APP_USER_CHROME_DIR)) {
      propertyValue = profilePath + CHROME_DIR;
    } else if (propertyName.equals(NS_APP_USER_PROFILE_50_DIR)) {
      propertyValue = profilePath;
    } else if (propertyName.equals(NS_APP_LOCALSTORE_50_FILE)) {
      propertyValue = profilePath + LOCALSTORE_FILE;
    } else if (propertyName.equals(NS_APP_CACHE_PARENT_DIR)) {
      propertyValue = cacheParentPath;
    } else if (propertyName.equals(NS_OS_HOME_DIR)) {
      propertyValue = System.getProperty("user.home");
    } else if (propertyName.equals(NS_OS_TEMP_DIR)) {
      propertyValue = System.getProperty("java.io.tmpdir");
    } else if (propertyName.equals(NS_GRE_DIR)) {
      propertyValue = mozillaPath;
    } else if (propertyName.equals(NS_GRE_COMPONENT_DIR)) {
      propertyValue = profilePath + COMPONENTS_DIR;
    } else if (propertyName.equals(NS_XPCOM_INIT_CURRENT_PROCESS_DIR)) {
      propertyValue = mozillaPath;
    } else if (propertyName.equals(NS_OS_CURRENT_PROCESS_DIR)) {
      propertyValue = mozillaPath;
    } else if (propertyName.equals(NS_XPCOM_COMPONENT_DIR)) {
      propertyValue = mozillaPath + COMPONENTS_DIR;
    } else if (propertyName.equals(NS_XPCOM_CURRENT_PROCESS_DIR)) {
      propertyValue = mozillaPath;
    } else if (propertyName.equals(NS_APP_PREF_DEFAULTS_50_DIR)) {
      if (isXULRunner) {
        propertyValue = profilePath;
      }
    }
    XPCOM.memmove(persistent, new boolean[] {true});
    XPCOM.memmove(_retval, new long[] {0}, PTR_SIZEOF);
    if ((propertyValue != null) && (propertyValue.length() > 0)) {
      long[] result = new long[1];
      nsEmbedString pathString = new nsEmbedString(propertyValue);
      int rc = XPCOM.NS_NewLocalFile(pathString.getAddress(), 1, result);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      if (result[0] == 0) {
        Mozilla.error(NS_ERROR_NULL_POINTER);
      }
      pathString.dispose();
      nsILocalFile localFile = new nsILocalFile(result[0]);
      result[0] = 0;
      rc =
          localFile.QueryInterface(
              Mozilla.IsPre_24 ? nsIFile.NS_IFILE_IID : nsIFile.NS_IFILE_24_IID, result);
      if (rc != XPCOM.NS_OK) {
        Mozilla.error(rc);
      }
      if (result[0] == 0) {
        Mozilla.error(NS_ERROR_NO_INTERFACE);
      }
      XPCOM.memmove(_retval, new long[] {result[0]}, PTR_SIZEOF);
      localFile.Release();
      return XPCOM.NS_OK;
    }
    return XPCOM.NS_ERROR_FAILURE;
  }
}
