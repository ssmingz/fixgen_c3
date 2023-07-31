class PlaceHold {
  protected void touch(File file) throws TaskException {
    if (!file.canWrite()) {
      throw new TaskException("Can not change modification date of read-only file " + file);
    }
    if (project.getJavaVersion() == Project.JAVA_1_1) {
      return;
    }
    final long time = (millis < 0) ? System.currentTimeMillis() : millis;
    file.setLastModified(time);
  }
}
