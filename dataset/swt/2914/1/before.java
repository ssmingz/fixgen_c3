class PlaceHold {
  static String[] getExtensions(Display display) {
    int desktop = getDesktop(display);
    Hashtable<String, List<String>> mimeInfo = null;
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
    Enumeration<String> keys = mimeInfo.keys();
    while (keys.hasMoreElements()) {
      String mimeType = keys.nextElement();
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
