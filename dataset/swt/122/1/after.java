class ASTClass {
  public ASTClass(String sourcePath, MetaData metaData) {
    this.sourcePath = sourcePath;
    this.metaData = metaData;
    String source = JNIGenerator.loadFile(sourcePath);
    ASTParser parser = ASTParser.newParser(AST.JLS3);
    parser.setSource(source.toCharArray());
    CompilationUnit unit = ((CompilationUnit) (parser.createAST(null)));
    TypeDeclaration type = ((TypeDeclaration) (unit.types().get(0)));
    simpleName = type.getName().getIdentifier();
    packageName = unit.getPackage().getName().getFullyQualifiedName();
    name = (packageName + ".") + simpleName;
    superclassName =
        (type.getSuperclassType() != null) ? type.getSuperclassType().toString() : null;
    List imports = unit.imports();
    this.imports = new String[imports.size()];
    int count = 0;
    for (Iterator iterator = imports.iterator(); iterator.hasNext(); ) {
      ImportDeclaration imp = ((ImportDeclaration) (iterator.next()));
      this.imports[count++] = imp.getName().getFullyQualifiedName();
    }
    start = type.getStartPosition();
    Javadoc doc = type.getJavadoc();
    List tags = null;
    if (doc != null) {
      tags = doc.tags();
      for (Iterator iterator = tags.iterator(); iterator.hasNext(); ) {
        TagElement tag = ((TagElement) (iterator.next()));
        if ("@jniclass".equals(tag.getTagName())) {
          String data = tag.fragments().get(0).toString();
          setMetaData(data);
          break;
        }
      }
    }
    FieldDeclaration[] fields = type.getFields();
    ArrayList fid = new ArrayList();
    for (int i = 0; i < fields.length; i++) {
      FieldDeclaration field = fields[i];
      List fragments = field.fragments();
      for (Iterator iterator = fragments.iterator(); iterator.hasNext(); ) {
        VariableDeclarationFragment fragment = ((VariableDeclarationFragment) (iterator.next()));
        fid.add(new ASTField(this, source, field, fragment));
      }
    }
    this.fields = ((ASTField[]) (fid.toArray(new ASTField[fid.size()])));
    MethodDeclaration[] methods = type.getMethods();
    ArrayList mid = new ArrayList();
    for (int i = 0; i < methods.length; i++) {
      if (methods[i].getReturnType2() == null) {
        continue;
      }
      mid.add(new ASTMethod(this, source, methods[i]));
    }
    this.methods = ((ASTMethod[]) (mid.toArray(new ASTMethod[mid.size()])));
  }
}
