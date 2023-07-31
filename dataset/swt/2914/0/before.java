class PlaceHold {
  static String cde_getMimeType(String extension) {
    String mimeType = null;
    Hashtable<String, List<String>> mimeInfo = cde_getDataTypeInfo();
    if (mimeInfo == null) {
      return null;
    }
    Enumeration<String> keys = mimeInfo.keys();
    while ((mimeType == null) && keys.hasMoreElements()) {
      String type = keys.nextElement();
      List<String> mimeExts = mimeInfo.get(type);
      for (int index = 0; index < mimeExts.size(); index++) {
        if (extension.equals(mimeExts.get(index))) {
          mimeType = type;
          break;
        }
      }
    }
    return mimeType;
  }
}
