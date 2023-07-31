class PlaceHold {
  protected void doExecute() throws TaskException {
    String pathname = m_target.getAbsolutePath();
    int pos = pathname.length() - ".jj".length();
    pathname = pathname.substring(0, pos) + ".java";
    File javaFile = new File(pathname);
    if (javaFile.exists() && (m_target.lastModified() < javaFile.lastModified())) {
      getContext().info(("Target is already build - skipping (" + m_target) + ")");
      return;
    }
    m_exe.setClassName("com.metamata.jj.MParse");
    m_exe.executeForked(getContext());
  }
}
