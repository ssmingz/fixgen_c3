class PlaceHold {
  private boolean shouldCompile(File classFile) {
    long now = new Date().getTime();
    File stubFile =
        new File(
            classFile.getAbsolutePath().substring(0, classFile.getAbsolutePath().indexOf(".class"))
                + "_Stub.class");
    File skelFile =
        new File(
            classFile.getAbsolutePath().substring(0, classFile.getAbsolutePath().indexOf(".class"))
                + "_Skel.class");
    if (classFile.exists()) {
      if (classFile.lastModified() > now) {
        log("Warning: file modified in the future: " + classFile, MSG_WARN);
      }
      if (classFile.lastModified() > stubFile.lastModified()) {
        return true;
      } else if (classFile.lastModified() > skelFile.lastModified()) {
        return true;
      } else {
        return false;
      }
    }
    return true;
  }
}
