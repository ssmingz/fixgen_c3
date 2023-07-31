class PlaceHold {
  public void execute() throws TaskException {
    validate();
    m_onWindows = Os.isFamily(OS_FAMILY_WINDOWS) || Os.isFamily(OS_FAMILY_NETWARE);
    char fromDirSep = (m_onWindows) ? '\\' : '/';
    char toDirSep = m_dirSep.charAt(0);
    StringBuffer rslt = new StringBuffer(100);
    String[] elems = m_path.list();
    for (int i = 0; i < elems.length; i++) {
      String elem = elems[i];
      elem = mapElement(elem);
      elem = elem.replace(fromDirSep, toDirSep);
      if (i != 0) {
        rslt.append(m_pathSep);
      }
      rslt.append(elem);
    }
    final String value = rslt.toString();
    getContext().debug((("Set property " + m_property) + " = ") + value);
    final String name = m_property;
    getContext().setProperty(name, value);
  }
}
