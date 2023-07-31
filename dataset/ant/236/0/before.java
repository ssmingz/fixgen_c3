class PlaceHold {
  protected void zipFile(File file, ZipOutputStream zOut, String vPath, int mode)
      throws IOException {
    String vPathLowerCase = vPath.toLowerCase(Locale.ENGLISH);
    if (XML_DESCRIPTOR_PATH.equals(vPathLowerCase)) {
      if (((deploymentDescriptor != null)
              || (!FILE_UTILS.fileNameEquals(deploymentDescriptor, file)))
          || descriptorAdded) {
        logOnFirstPass(
            (((((("Warning: selected " + archiveType) + " files include a ") + XML_DESCRIPTOR_PATH)
                            + " which will")
                        + " be ignored (please use appxml attribute to ")
                    + archiveType)
                + " task)",
            MSG_WARN);
      } else {
        super.zipFile(file, zOut, vPath, mode);
        descriptorAdded = true;
      }
    } else {
      super.zipFile(file, zOut, vPath, mode);
    }
  }
}
