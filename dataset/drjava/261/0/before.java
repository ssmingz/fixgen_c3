class PlaceHold {
  protected void addConstructor(TreeConstructorInfo ci) {
    ClassInfo[] cinf = ci.getParameterTypes();
    String[] params = new String[cinf.length];
    for (int i = 0; i < cinf.length; i++) {
      params[i] = cinf[i].getName();
    }
    cinf = ci.getExceptionTypes();
    String[] ex = new String[cinf.length];
    for (int i = 0; i < cinf.length; i++) {
      ex[i] = cinf[i].getName();
    }
    String sig =
        ClassFactory.getMethodIdentifier(
            classInfo.getName(), "<init>", params, interpreter.getClassLoader().toString());
    ConstructorDeclaration cd = ci.getConstructorDeclaration();
    if (!cd.getName().equals(typeDeclaration.getName())) {
      cd.setProperty(ERROR_STRINGS, new String[] {cd.getName()});
      throw new ExecutionError("constructor.name", cd);
    }
    ConstructorInvocation civ = cd.getConstructorInvocation();
    ConstructorVisitor cv = new ConstructorVisitor();
    if (civ != null) {
      Iterator it = cd.getParameters().iterator();
      while (it.hasNext()) {
        ((Node) (it.next())).acceptVisitor(cv);
      }
      civ.acceptVisitor(cv);
      interpreter.registerConstructorArguments(
          sig, cd.getParameters(), civ.getArguments(), importationManager);
    } else {
      interpreter.registerConstructorArguments(
          sig, new LinkedList<FormalParameter>(), new LinkedList<Expression>(), importationManager);
    }
    MethodDeclaration md =
        new MethodDeclaration(
            cd.getAccessFlags(),
            new VoidType(),
            "<init>",
            cd.getParameters(),
            new LinkedList<ReferenceType>(),
            new BlockStatement(cd.getStatements()));
    interpreter.registerMethod(sig, md, importationManager);
    if (!cv.superConstructor.equals(classInfo.getName())) {
      ListIterator<Node> lit = cd.getStatements().listIterator();
      Iterator<Node> it = instanceInitializer.iterator();
      while (it.hasNext()) {
        lit.add(it.next());
      }
    }
    classFactory.addConstructor(
        cd.getAccessFlags(), params, ex, cv.superConstructor, cv.constructorParameters);
  }
}
