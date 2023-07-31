class PlaceHold {
  static String[] getExtensions(Display display) {
    int desktop = getDesktop(display);
    Hashtable mimeInfo = null;
    if (desktop == DESKTOP_GNOME) {
      mimeInfo = gnome_getMimeInfo(display);
    }
    if (mimeInfo == null) {
      return new String[0];
    }
    Vector extensions = new Vector();
    Enumeration keys = mimeInfo.keys();
    while (keys.hasMoreElements()) {
      String mimeType = ((String) (keys.nextElement()));
      Vector mimeExts = ((Vector) (mimeInfo.get(mimeType)));
      for (int index = 0; index < mimeExts.size(); index++) {
        if (!extensions.contains(mimeExts.elementAt(index))) {
          extensions.addElement(mimeExts.elementAt(index));
        }
      }
    }
    String[] extStrings = new String[extensions.size()];
    for (int index = 0; index < extensions.size(); index++) {
      extStrings[index] = ((String) (extensions.elementAt(index)));
    }
    return extStrings;
  }
}
