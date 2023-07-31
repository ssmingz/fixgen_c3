class PlaceHold {
  public InputSource resolveEntity(String publicId, String systemId) {
    project.log("resolving systemId: " + systemId, MSG_VERBOSE);
    if (systemId.startsWith("file:")) {
      String path = systemId.substring(5);
      int index = path.indexOf("file:");
      while (index != (-1)) {
        path = path.substring(0, index) + path.substring(index + 5);
        index = path.indexOf("file:");
      }
      String entitySystemId = path;
      index = path.indexOf("%23");
      while (index != (-1)) {
        path = (path.substring(0, index) + "#") + path.substring(index + 3);
        index = path.indexOf("%23");
      }
      File file = new File(path);
      if (!file.isAbsolute()) {
        file = new File(buildFileParent, path);
      }
      try {
        InputSource inputSource = new InputSource(new FileInputStream(file));
        inputSource.setSystemId("file:" + entitySystemId);
        return inputSource;
      } catch (FileNotFoundException fne) {
        project.log(file.getAbsolutePath() + " could not be found", MSG_WARN);
      }
    }
    return null;
  }
}
