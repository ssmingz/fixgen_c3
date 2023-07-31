class PlaceHold {
  public File createTempFile(String prefix, String suffix, File parentDir) {
    File result = null;
    String parent =
        (parentDir == null) ? System.getProperty("java.io.tmpdir") : parentDir.getPath();
    DecimalFormat fmt = new DecimalFormat("#####");
    synchronized (rand) {
      do {
        result = new File(parent, (prefix + fmt.format(Math.abs(rand.nextInt()))) + suffix);
      } while (result.exists());
    }
    return result;
  }
}
