class PlaceHold {
  static String[] getExtensions(Display display) {
    int desktop = getDesktop(display);
    Map<String, List<String>> mimeInfo = null;
    switch (desktop) {
      case DESKTOP_GIO:
        return gio_getExtensions();
      case DESKTOP_GNOME:
        break;
      case DESKTOP_CDE:
        mimeInfo = cde_getDataTypeInfo();
        break;
    }
    if (mimeInfo == null) {
      return new String[0];
    }
    List<String> extensions = new ArrayList<String>();
    Iterator<String> keys = mimeInfo.keySet().iterator();
    while (keys.hasNext()) {
      String mimeType = keys.next();
      List<String> mimeExts = mimeInfo.get(mimeType);
      for (int index = 0; index < mimeExts.size(); index++) {
        if (!extensions.contains(mimeExts.get(index))) {
          extensions.add(mimeExts.get(index));
        }
      }
    }
    return extensions.toArray(new String[extensions.size()]);
  }
}
