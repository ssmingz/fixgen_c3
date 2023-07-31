class PlaceHold {
  static String cde_getMimeType(String extension) {
    String mimeType = null;
    Map<String, List<String>> mimeInfo = cde_getDataTypeInfo();
    if (mimeInfo == null) {
      return null;
    }
    Iterator<String> keys = mimeInfo.keySet().iterator();
    while ((mimeType == null) && keys.hasNext()) {
      String type = keys.next();
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
