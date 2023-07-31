class PlaceHold {
  public void testHandParsedGenericClass() {
    int accessFlags = Modifier.PUBLIC;
    List<IdentifierToken> tIds = new LinkedList<IdentifierToken>();
    tIds.add(new Identifier("T"));
    List<IdentifierToken> numberIds = new LinkedList<IdentifierToken>();
    numberIds.add(new Identifier("Number"));
    ReferenceType numberType = new ReferenceType(numberIds);
    TypeParameter t = new TypeParameter(new SourceInfo(), tIds, numberType);
    TypeParameter[] typeParams = new TypeParameter[1];
    typeParams[0] = t;
    List<Node> body = new LinkedList<Node>();
    FieldDeclaration n = new FieldDeclaration(accessFlags, t, "n", null);
    body.add(n);
    FormalParameter param = new FormalParameter(false, t, "_n");
    List<FormalParameter> cparams = new LinkedList<FormalParameter>();
    cparams.add(param);
    List<Node> cstmts = new LinkedList<Node>();
    List<IdentifierToken> nIds = new LinkedList<IdentifierToken>();
    nIds.add(new Identifier("n"));
    QualifiedName nName = new QualifiedName(nIds);
    List<IdentifierToken> _nIds = new LinkedList<IdentifierToken>();
    _nIds.add(new Identifier("_n"));
    AssignExpression stmt = new SimpleAssignExpression(nName, new QualifiedName(_nIds));
    cstmts.add(stmt);
    ConstructorDeclaration c =
        new ConstructorDeclaration(
            accessFlags, "C", cparams, new LinkedList<ReferenceType>(), null, cstmts);
    body.add(c);
    List<Node> mstmts = new LinkedList<Node>();
    ReturnStatement mstmt = new ReturnStatement(nName);
    mstmts.add(mstmt);
    List<FormalParameter> mparams = new LinkedList<FormalParameter>();
    List<ReferenceType> mexcepts = new LinkedList<ReferenceType>();
    MethodDeclaration m =
        new MethodDeclaration(accessFlags, t, "m", mparams, mexcepts, new BlockStatement(mstmts));
    body.add(m);
    GenericClassDeclaration cls =
        new GenericClassDeclaration(accessFlags, "C", null, null, body, typeParams);
    astInterpreter.interpret(cls);
    IdentifierToken cId = new Identifier("C");
    List<IdentifierToken> ids = new LinkedList<IdentifierToken>();
    ids.add(cId);
    IdentifierToken iId = new Identifier("Integer");
    List<IdentifierToken> iIds = new LinkedList<IdentifierToken>();
    iIds.add(iId);
    List<ReferenceType> targs = new LinkedList<ReferenceType>();
    targs.add(new ReferenceType(iIds));
    List<List<? extends Type>> alltargs = new LinkedList<List<? extends Type>>();
    alltargs.add(new LinkedList<ReferenceType>());
    alltargs.add(new LinkedList<ReferenceType>());
    alltargs.add(targs);
    Type genericCType = new GenericReferenceType(ids, alltargs);
    List<Expression> mccargs = new LinkedList<Expression>();
    List<Expression> fiveExps = new LinkedList<Expression>();
    fiveExps.add(new IntegerLiteral("5"));
    mccargs.add(new SimpleAllocation(new ReferenceType(iIds), fiveExps));
    SimpleAllocation sa = new SimpleAllocation(genericCType, mccargs);
    MethodCall mc = new ObjectMethodCall(sa, "m", new LinkedList<Expression>(), "", 0, 0, 0, 0);
    Object result = astInterpreter.interpret(mc);
    assertEquals("java.lang.Integer", result.getClass().getName());
    assertEquals(new Integer(5), ((Integer) (result)));
  }
}
