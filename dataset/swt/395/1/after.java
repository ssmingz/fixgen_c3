class PlaceHold {
  int getFiles(long prop, long _retval) {
    int size = XPCOM.strlen(prop);
    byte[] bytes = new byte[size];
    XPCOM.memmove(bytes, prop, size);
    String propertyName = new String(MozillaDelegate.mbcsToWcs(null, bytes));
    String[] propertyValues = null;
    if (propertyName.equals(NS_APP_PLUGINS_DIR_LIST)) {
      if (pluginDirs == null) {
        int index = 0;
        long ptr = C.getenv(MozillaDelegate.wcsToMbcs(null, MOZILLA_PLUGIN_PATH, true));
        if (ptr != 0) {
          int length = C.strlen(ptr);
          byte[] buffer = new byte[length];
          C.memmove(buffer, ptr, length);
          String value = new String(MozillaDelegate.mbcsToWcs(null, buffer));
          if (value.length() > 0) {
            String separator = System.getProperty("path.separator");
            Vector segments = new Vector();
            int start;
            int end = -1;
            do {
              start = end + 1;
              end = value.indexOf(separator, start);
              String segment;
              if (end == (-1)) {
                segment = value.substring(start);
              } else {
                segment = value.substring(start, end);
              }
              if (segment.length() > 0) {
                segments.addElement(segment);
              }
            } while (end != (-1));
            int segmentsSize = segments.size();
            pluginDirs = new String[segmentsSize + (IsSparc ? 1 : 2)];
            for (index = 0; index < segmentsSize; index++) {
              pluginDirs[index] = ((String) (segments.elementAt(index)));
            }
          }
        }
        if (pluginDirs == null) {
          pluginDirs = new String[IsSparc ? 1 : 2];
        }
        if (!IsSparc) {
          pluginDirs[index++] = mozillaPath + PLUGINS_DIR;
        }
        pluginDirs[index++] = (System.getProperty("user.home") + SEPARATOR_OS) + USER_PLUGINS_DIR;
      }
      propertyValues = pluginDirs;
    }
    XPCOM.memmove(_retval, new long[] {0}, PTR_SIZEOF);
    if (propertyValues != null) {
      long[] result = new long[1];
      nsISupports[] files = new nsISupports[propertyValues.length];
      int index = 0;
      for (int i = 0; i < propertyValues.length; i++) {
        nsEmbedString pathString = new nsEmbedString(propertyValues[i]);
        int rc = XPCOM.NS_NewLocalFile(pathString.getAddress(), 1, result);
        if (rc != XPCOM.NS_ERROR_FILE_UNRECOGNIZED_PATH) {
          if (rc != XPCOM.NS_OK) {
            Mozilla.error(rc);
          }
          if (result[0] == 0) {
            Mozilla.error(NS_ERROR_NULL_POINTER);
          }
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
          localFile.Release();
          nsIFile file = new nsIFile(result[0]);
          files[index++] = file;
        }
        pathString.dispose();
        result[0] = 0;
      }
      if (index < propertyValues.length) {
        nsISupports[] temp = new nsISupports[index];
        System.arraycopy(files, 0, temp, 0, index);
        files = temp;
      }
      SimpleEnumerator enumerator = new SimpleEnumerator(files);
      enumerator.AddRef();
      XPCOM.memmove(_retval, new long[] {enumerator.getAddress()}, PTR_SIZEOF);
      return XPCOM.NS_OK;
    }
    return XPCOM.NS_ERROR_FAILURE;
  }
}
