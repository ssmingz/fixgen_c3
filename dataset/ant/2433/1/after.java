class PlaceHold {
  public InputSource resolveEntity(String publicId, String systemId) {
    helperImpl.project.log("resolving systemId: " + systemId, MSG_VERBOSE);
    if (systemId.startsWith("file:")) {
      String path = FILE_UTILS.fromURI(systemId);
      File file = new File(path);
      if (!file.isAbsolute()) {
        file = FILE_UTILS.resolveFile(helperImpl.buildFileParent, path);
        helperImpl.project.log(
            ((((("Warning: '" + systemId) + "' in ") + helperImpl.buildFile)
                        + " should be expressed simply as '")
                    + path.replace('\\', '/'))
                + "' for compliance with other XML tools",
            MSG_WARN);
      }
      try {
        InputSource inputSource = new InputSource(new FileInputStream(file));
        inputSource.setSystemId(FILE_UTILS.toURI(file.getAbsolutePath()));
        return inputSource;
      } catch (FileNotFoundException fne) {
        helperImpl.project.log(file.getAbsolutePath() + " could not be found", MSG_WARN);
      }
    }
    return null;
  }
}
