class JavaCCParser {
  public JavaCCParser(Reader r, File f) {
    _parser = new Parser(r);
    _parser.setFile(f);
    _f = f;
  }
}
