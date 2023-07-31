class PlaceHold {
  private String getFilteredFile(String testNumber, String filteredFile) {
    String line = null;
    File f = new File(filteredFile);
    if (!f.exists()) {
      fail(("filter test" + testNumber) + " failed");
    } else {
      BufferedReader in = null;
      try {
        in = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException fnfe) {
        fail(
            ((("filter test" + testNumber) + " failed, filtered file: ") + f.toString())
                + " not found");
      }
      try {
        line = in.readLine();
        in.close();
      } catch (IOException ioe) {
        fail(
            (("filter test" + testNumber) + " failed.  IOException while reading filtered file: ")
                + ioe);
      }
    }
    f.delete();
    return line;
  }
}
