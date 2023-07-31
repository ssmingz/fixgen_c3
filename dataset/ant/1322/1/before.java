class PlaceHold {
  private static int textCompare(Resource r1, Resource r2) throws IOException {
    BufferedReader in1 = null;
    BufferedReader in2 = null;
    try {
      in1 = new BufferedReader(new InputStreamReader(r1.getInputStream()));
      in2 = new BufferedReader(new InputStreamReader(r2.getInputStream()));
      String expected = in1.readLine();
      while (expected != null) {
        String actual = in2.readLine();
        if (!expected.equals(actual)) {
          return expected.compareTo(actual);
        }
        expected = in1.readLine();
      }
      return in2.readLine() == null ? 0 : -1;
    } finally {
      FileUtils.close(in1);
      FileUtils.close(in2);
    }
  }
}
