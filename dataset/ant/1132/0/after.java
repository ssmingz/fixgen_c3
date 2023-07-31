class PlaceHold {
  private static void registerNonCrash() throws IOException {
    if (crashFile != null) {
      FileWriter out = null;
      try {
        out = new FileWriter(crashFile);
        out.write(Constants.TERMINATED_SUCCESSFULLY + "\n");
        out.flush();
      } finally {
        FileUtils.close(out);
      }
    }
  }
}
