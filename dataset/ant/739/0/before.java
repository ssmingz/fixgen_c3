class PlaceHold {
  public void execute() throws TaskException {
    validate();
    String osname = System.getProperty("os.name").toLowerCase();
    m_onWindows = (osname.indexOf("windows") >= 0) || (osname.indexOf("netware") >= 0);
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
    String value = rslt.toString();
    getLogger().debug((("Set property " + m_property) + " = ") + value);
    setProperty(m_property, value);
  }
}
