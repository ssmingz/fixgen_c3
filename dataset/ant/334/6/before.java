class PlaceHold {
  public void start() throws BuildException {
    try {
      if (((p4input != null) && (p4input.length() > 0)) && (os != null)) {
        os.write(p4input.getBytes());
        os.flush();
        os.close();
      }
      BufferedReader input =
          new BufferedReader(new InputStreamReader(new SequenceInputStream(is, es)));
      String line;
      while ((line = input.readLine()) != null) {
        process(line);
      }
      input.close();
    } catch (Exception e) {
      throw new BuildException(e);
    }
  }
}
