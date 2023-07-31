class PlaceHold {
  protected void zipFile(File file, ZipOutputStream zOut, String vPath)
      throws IOException, TaskException {
    if (vPath.equalsIgnoreCase("META-INF/aplication.xml")) {
      if (((m_appxml == null) || (!m_appxml.equals(file))) || m_descriptorAdded) {
        final String message =
            (((("Warning: selected " + m_archiveType)
                            + " files include a META-INF/application.xml which will be ignored ")
                        + "(please use appxml attribute to ")
                    + m_archiveType)
                + " task)";
        getLogger().warn(message);
      } else {
        super.zipFile(file, zOut, vPath);
        m_descriptorAdded = true;
      }
    } else {
      super.zipFile(file, zOut, vPath);
    }
  }
}
