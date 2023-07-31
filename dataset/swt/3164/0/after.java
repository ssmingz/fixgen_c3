class PlaceHold {
  static Program findProgram(Display display, String extension) {
    if (extension == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (extension.length() == 0) {
      return null;
    }
    if (extension.charAt(0) != '.') {
      extension = "." + extension;
    }
    String name = null;
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
      return null;
    }
    Enumeration keys = mimeInfo.keys();
    while ((name == null) && keys.hasMoreElements()) {
      String mimeType = ((String) (keys.nextElement()));
      Vector mimeExts = ((Vector) (mimeInfo.get(mimeType)));
      for (int index = 0; index < mimeExts.size(); index++) {
        if (extension.equals(mimeExts.elementAt(index))) {
          name = mimeType;
          break;
        }
      }
    }
    if (name == null) {
      return null;
    }
    Program program = null;
    if (desktop == DESKTOP_GNOME) {
      program = gnome_getProgram(display, name);
    } else {
      String command = null;
      if (desktop == DESKTOP_KDE) {
        command = kde_getMimeTypeCommand(name);
      }
      if (desktop == DESKTOP_CDE) {
        command = cde_getAction(name);
      }
      if (command != null) {
        program = new Program();
        program.name = name;
        program.command = command;
        program.extension = extension;
        program.display = display;
      }
    }
    return program;
  }
}
