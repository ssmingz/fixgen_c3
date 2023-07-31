class PlaceHold {
  private void convert(String srcName, String destName) throws TaskException {
    final File srcFile = new File(m_srcDir, srcName);
    final File destFile = new File(m_destDir, destName);
    if (srcFile.equals(destFile)) {
      throw new TaskException(("file " + srcFile) + " would overwrite its self");
    }
    final ArgumentList cmd = buildCommand(srcFile, destFile);
    final File parent = destFile.getParentFile();
    if (parent != null) {
      if ((!parent.exists()) && (!parent.mkdirs())) {
        throw new TaskException("cannot create parent directory " + parent);
      }
    }
    getContext().debug("converting " + srcName);
    Main n2a = new Main();
    if (!n2a.convert(cmd.getArguments())) {
      throw new TaskException("conversion failed");
    }
  }
}
