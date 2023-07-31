class PlaceHold {
  public Class compile() {
    ClassInfo dc = classInfo.getDeclaringClass();
    String outer = (dc != null) ? dc.getName() : null;
    int af = typeDeclaration.getAccessFlags();
    String name = classInfo.getName();
    if (isInterface) {
      af |= Modifier.INTERFACE;
    }
    classFactory =
        new ClassFactory(
            af,
            name,
            classInfo.getSuperclass().getName(),
            interpreter.getClass(),
            interpreter.getExceptionClass(),
            interpreter.getClassLoader().toString());
    if (dc != null) {
      addInnerClassesAttribute(classInfo);
    }
    ClassInfo[] inners = classInfo.getDeclaredClasses();
    for (int i = 0; i < inners.length; i++) {
      String ciname = inners[i].getName();
      InnerClassesEntry ice = classFactory.addInnerClassesEntry();
      ice.setInnerClassInfo(ciname);
      ice.setOuterClassInfo(name);
      ice.setInnerName(ciname.substring(name.length() + 1, ciname.length()));
      ice.setInnerClassAccessFlags(((short) (inners[i].getModifiers())));
    }
    ClassInfo[] ci = classInfo.getInterfaces();
    for (int i = 0; i < ci.length; i++) {
      classFactory.addInterface(ci[i].getName());
    }
    Iterator it = typeDeclaration.getMembers().iterator();
    while (it.hasNext()) {
      ((Node) (it.next())).acceptVisitor(membersVisitor);
    }
    if (((!isInterface) && hasAbstractMethod) && (!Modifier.isAbstract(af))) {
      typeDeclaration.setProperty(ERROR_STRINGS, new String[] {name});
      throw new ExecutionError("misplaced.abstract", typeDeclaration);
    }
    if (!isInterface) {
      ConstructorInfo[] cons = classInfo.getConstructors();
      for (int i = 0; i < cons.length; i++) {
        addConstructor(((TreeConstructorInfo) (cons[i])));
      }
    }
    if (classInitializer.size() > 0) {
      interpreter.registerMethod(
          classFactory.createClassInitializer(),
          new MethodDeclaration(
              Modifier.PUBLIC,
              new VoidType(),
              "<clinit>",
              new LinkedList<FormalParameter>(),
              new LinkedList<ReferenceType>(),
              new BlockStatement(classInitializer)),
          importationManager);
    }
    TreeClassLoader classLoader = ((TreeClassLoader) (interpreter.getClassLoader()));
    return classLoader.defineClass(name, classFactory.getByteCode());
  }
}
