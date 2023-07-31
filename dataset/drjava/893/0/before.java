class JavaCCParser {
  public JavaCCParser(Reader r, File f) {
    parser = new Parser(r);
    parser.setFile(f);
  }
}
