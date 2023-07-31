class PlaceHold {
  static Program[] getPrograms(Display display) {
    int desktop = getDesktop(display);
    Hashtable mimeInfo = null;
    if (desktop == DESKTOP_KDE) {
      mimeInfo = kde_getMimeInfo();
    }
    if (desktop == DESKTOP_GNOME) {
      mimeInfo = gnome_getMimeInfo();
    }
    if (desktop == DESKTOP_CDE) {
      mimeInfo = cde_getDataTypeInfo();
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
      } else {
        String command = null;
        if (desktop == DESKTOP_KDE) {
          command = kde_getMimeTypeCommand(mimeType);
        }
        if (desktop == DESKTOP_CDE) {
          command = cde_getAction(mimeType);
        }
        if (command != null) {
          program = new Program();
          program.name = mimeType;
          program.command = command;
          program.extension = extension;
          program.display = display;
        }
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
