class PlaceHold {
  static List<Node> AddValues(String enumTypeName, List<Node> body, List<EnumConstant> consts) {
    List<Node> newbody = body;
    ReferenceTypeName enumType = new ReferenceTypeName(enumTypeName);
    List<Expression> cells = new LinkedList<Expression>();
    for (EnumConstant c : consts) {
      cells.add(new StaticFieldAccess(enumType, c.getName()));
    }
    Expression alloc =
        new ArrayAllocation(
            enumType,
            new ArrayAllocation.TypeDescriptor(
                Collections.<Expression>emptyList(),
                1,
                new ArrayInitializer(cells),
                SourceInfo.NONE));
    Statement valuesBody = new ReturnStatement(alloc);
    newbody.add(
        new MethodDeclaration(
            Modifier.PUBLIC | Modifier.STATIC,
            new ArrayTypeName(enumType, 1, false),
            "values",
            Collections.<FormalParameter>emptyList(),
            Collections.<ReferenceTypeName>emptyList(),
            new BlockStatement(Collections.<Node>singletonList(valuesBody))));
    FormalParameter nameParam =
        new FormalParameter(false, new ReferenceTypeName("java", "lang", "String"), "name");
    List<Node> valueOfBody = new LinkedList<Node>();
    for (EnumConstant c : consts) {
      String cn = c.getName();
      Expression cond =
          new ObjectMethodCall(
              new StringLiteral(("\"" + cn) + "\""),
              "equals",
              Collections.singletonList(new VariableAccess("name")));
      Statement ret = new ReturnStatement(new StaticFieldAccess(enumType, cn));
      valueOfBody.add(new IfThenStatement(cond, ret));
    }
    valueOfBody.add(
        new ThrowStatement(
            new SimpleAllocation(
                new ReferenceTypeName("IllegalArgumentException"),
                Collections.<Expression>emptyList())));
    newbody.add(
        new MethodDeclaration(
            Modifier.PUBLIC | Modifier.STATIC,
            enumType,
            "valueOf",
            Collections.singletonList(nameParam),
            Collections.<ReferenceTypeName>emptyList(),
            new BlockStatement(valueOfBody)));
    return newbody;
  }
}
