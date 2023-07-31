class PlaceHold {
  protected void cleanUp() {
    if (m_optionsFile != null) {
      m_optionsFile.delete();
      m_optionsFile = null;
    }
    if (m_cleanup) {
      String name = m_target.getName();
      int pos = name.length() - ".jj".length();
      name = ("__jj" + name.substring(0, pos)) + ".sunjj";
      final File sunjj = new File(m_target.getParent(), name);
      if (sunjj.exists()) {
        getLogger().info("Removing stale file: " + sunjj.getName());
        sunjj.delete();
      }
    }
  }
}
