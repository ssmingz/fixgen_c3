class JavaCCParser {
  public JavaCCParser(InputStream is, File f) {
    parser = new Parser(is);
    parser.setFile(f);
  }
}
