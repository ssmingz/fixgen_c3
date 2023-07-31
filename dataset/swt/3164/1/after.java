class PlaceHold {
  static Program[] getPrograms(Display display) {
    int desktop = getDesktop(display);
    Hashtable mimeInfo = null;
    if (desktop == DESKTOP_GNOME) {
      mimeInfo = gnome_getMimeInfo(display);
    }
    if (mimeInfo == null) {
      return new Program[0];
    }
    Vector programs = new Vector();
    boolean[] gnomeExpectUri = null;
    if (desktop == DESKTOP_GNOME) {
      gnomeExpectUri = new boolean[1];
    }
    Enumeration keys = mimeInfo.keys();
    while (keys.hasMoreElements()) {
      String mimeType = ((String) (keys.nextElement()));
      Vector mimeExts = ((Vector) (mimeInfo.get(mimeType)));
      String extension = "";
      if (mimeExts.size() > 0) {
        extension = ((String) (mimeExts.elementAt(0)));
      }
      Program program = null;
      if (desktop == DESKTOP_GNOME) {
        program = gnome_getProgram(display, mimeType);
      }
      if (program != null) {
        programs.addElement(program);
      }
    }
    Program[] programList = new Program[programs.size()];
    for (int index = 0; index < programList.length; index++) {
      programList[index] = ((Program) (programs.elementAt(index)));
    }
    return programList;
  }
}
