class PlaceHold {
  protected void logAndAddFilesToCompile(Commandline cmd) {
    getLogger().debug("Compilation args: " + cmd.toString());
    StringBuffer niceSourceList = new StringBuffer("File");
    if (m_compileList.length != 1) {
      niceSourceList.append("s");
    }
    niceSourceList.append(" to be compiled:");
    niceSourceList.append(LINE_SEPARATOR);
    for (int i = 0; i < m_compileList.length; i++) {
      String arg = m_compileList[i].getAbsolutePath();
      cmd.addArgument(arg);
      niceSourceList.append(("    " + arg) + StringUtil.LINE_SEPARATOR);
    }
    getLogger().debug(niceSourceList.toString());
  }
}
