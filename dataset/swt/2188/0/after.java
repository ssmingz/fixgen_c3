class PlaceHold {
  void checkMembers() {
    if (fields != null) {
      return;
    }
    String source = null;
    CompilationUnit unit = null;
    if (JNIItem.GEN64) {
      source = JNIGenerator.loadFile(sourcePath);
      ASTParser parser = ASTParser.newParser(AST.JLS8);
      parser.setSource(source.toCharArray());
      unit = ((CompilationUnit) (parser.createAST(null)));
    }
    Field[] fields = clazz.getDeclaredFields();
    this.fields = new ReflectField[fields.length];
    for (int i = 0; i < fields.length; i++) {
      this.fields[i] = new ReflectField(this, fields[i], source, unit);
    }
    Method[] methods = clazz.getDeclaredMethods();
    this.methods = new ReflectMethod[methods.length];
    for (int i = 0; i < methods.length; i++) {
      this.methods[i] = new ReflectMethod(this, methods[i], source, unit);
    }
  }
}
