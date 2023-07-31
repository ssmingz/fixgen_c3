class PlaceHold {
  static Program[] getPrograms(Display display) {
    int desktop = getDesktop(display);
    Map<String, List<String>> mimeInfo = null;
    switch (desktop) {
      case DESKTOP_GIO:
        return gio_getPrograms(display);
      case DESKTOP_GNOME:
        break;
      case DESKTOP_CDE:
        mimeInfo = cde_getDataTypeInfo();
        break;
    }
    if (mimeInfo == null) {
      return new Program[0];
    }
    List<Program> programs = new ArrayList<Program>();
    Iterator<String> keys = mimeInfo.keySet().iterator();
    while (keys.hasNext()) {
      String mimeType = keys.next();
      Program program = null;
      switch (desktop) {
        case DESKTOP_CDE:
          program = cde_getProgram(display, mimeType);
          break;
      }
      if (program != null) {
        programs.add(program);
      }
    }
    return programs.toArray(new Program[programs.size()]);
  }
}
