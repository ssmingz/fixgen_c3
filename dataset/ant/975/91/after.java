class PlaceHold {
  protected void zipFile(final File file, final ZipOutputStream zOut, final String vPath)
      throws IOException, TaskException {
    if (vPath.equalsIgnoreCase("WEB-INF/web.xml")) {
      if (((m_webxml == null) || (!m_webxml.equals(file))) || m_descriptorAdded) {
        final String message =
            (((("Warning: selected " + m_archiveType)
                            + " files include a WEB-INF/web.xml which will be ignored ")
                        + "(please use webxml attribute to ")
                    + m_archiveType)
                + " task)";
        getContext().warn(message);
      } else {
        super.zipFile(file, zOut, vPath);
        m_descriptorAdded = true;
      }
    } else {
      super.zipFile(file, zOut, vPath);
    }
  }
}
