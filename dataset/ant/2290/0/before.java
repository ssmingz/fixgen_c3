class PlaceHold {
  private void executeInVM() throws TaskException {
    JDepend jdepend;
    if ("xml".equals(m_format)) {
      jdepend = new JDepend();
    } else {
      jdepend = new JDepend();
    }
    if (m_outputFile != null) {
      FileWriter fw;
      try {
        fw = new FileWriter(m_outputFile.getPath());
      } catch (IOException e) {
        String msg = "JDepend Failed when creating the output file: " + e.getMessage();
        getContext().info(msg);
        throw new TaskException(msg);
      }
      jdepend.setWriter(new PrintWriter(fw));
      getContext().info("Output to be stored in " + m_outputFile.getPath());
    }
    final String[] elements = m_sourcesPath.list();
    for (int i = 0; i < elements.length; i++) {
      File f = new File(elements[i]);
      if ((!f.exists()) || (!f.isDirectory())) {
        String msg =
            ("\"" + f.getPath()) + "\" does not represent a valid directory. JDepend would fail.";
        getContext().info(msg);
        throw new TaskException(msg);
      }
      try {
        jdepend.addDirectory(f.getPath());
      } catch (IOException e) {
        String msg = "JDepend Failed when adding a source directory: " + e.getMessage();
        getContext().info(msg);
        throw new TaskException(msg);
      }
    }
    jdepend.analyze();
  }
}
