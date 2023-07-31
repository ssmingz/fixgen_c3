class PlaceHold {
  protected void zipFile(File file, ZipOutputStream zOut, String vPath)
      throws IOException, TaskException {
    if (vPath.equalsIgnoreCase("WEB-INF/web.xml")) {
      if (((deploymentDescriptor == null) || (!deploymentDescriptor.equals(file)))
          || descriptorAdded) {
        final String message =
            (((("Warning: selected " + archiveType)
                            + " files include a WEB-INF/web.xml which will be ignored ")
                        + "(please use webxml attribute to ")
                    + archiveType)
                + " task)";
        getLogger().warn(message);
      } else {
        super.zipFile(file, zOut, vPath);
        descriptorAdded = true;
      }
    } else {
      super.zipFile(file, zOut, vPath);
    }
  }
}
