class PlaceHold {
  public InputSource resolveEntity(String publicId, String systemId) {
    context.getProject().log("resolving systemId: " + systemId, MSG_VERBOSE);
    if (systemId.startsWith("file:")) {
      String path = FILE_UTILS.fromURI(systemId);
      File file = new File(path);
      if (!file.isAbsolute()) {
        file = FILE_UTILS.resolveFile(context.getBuildFileParent(), path);
        context
            .getProject()
            .log(
                ((((("Warning: '" + systemId) + "' in ") + context.getBuildFile())
                            + " should be expressed simply as '")
                        + path.replace('\\', '/'))
                    + "' for compliance with other XML tools",
                MSG_WARN);
      }
      context.getProject().log("file=" + file, MSG_DEBUG);
      try {
        InputSource inputSource = new InputSource(new FileInputStream(file));
        inputSource.setSystemId(FILE_UTILS.toURI(file.getAbsolutePath()));
        return inputSource;
      } catch (FileNotFoundException fne) {
        context.getProject().log(file.getAbsolutePath() + " could not be found", MSG_WARN);
      }
    }
    context.getProject().log("could not resolve systemId", MSG_DEBUG);
    return null;
  }
}
