class PlaceHold {
  private void executeAsForked() throws TaskException {
    final ExecuteJava exe = new ExecuteJava();
    exe.setWorkingDirectory(m_dir);
    if ("text".equals(m_format)) {
      exe.setClassName("jdepend.textui.JDepend");
    } else {
      exe.setClassName("jdepend.xmlui.JDepend");
    }
    if (m_jvm != null) {
      exe.setJvm(m_jvm);
    }
    exe.getClassPath().addPath(m_compileClasspath);
    if (m_outputFile != null) {
      exe.getArguments().addArgument("-file");
      exe.getArguments().addArgument(m_outputFile);
      getContext().info("Output to be stored in " + m_outputFile.getPath());
    }
    final String[] elements = m_sourcesPath.listFiles(getContext());
    for (int i = 0; i < elements.length; i++) {
      File f = new File(elements[i]);
      if ((!f.exists()) || (!f.isDirectory())) {
        throw new TaskException(
            ("\"" + f.getPath()) + "\" does not represent a valid directory. JDepend would fail.");
      }
      exe.getArguments().addArgument(f);
    }
    exe.executeForked(getContext());
  }
}
