class PlaceHold {
  protected void cleanUp() {
    if (optionsFile != null) {
      optionsFile.delete();
      optionsFile = null;
    }
    if (cleanup) {
      String name = target.getName();
      int pos = name.length() - ".jj".length();
      name = ("__jj" + name.substring(0, pos)) + ".sunjj";
      final File sunjj = new File(target.getParent(), name);
      if (sunjj.exists()) {
        project.log("Removing stale file: " + sunjj.getName());
        sunjj.delete();
      }
    }
  }
}
