class PlaceHold {
  private void compileClass(Node ast) {
    Type extendsT = TypeSystem.OBJECT;
    List<? extends ReferenceTypeName> implementsTs = CollectUtil.emptyList();
    List<Node> members = CollectUtil.emptyList();
    int accessFlags = 0;
    final boolean isInterface;
    if (ast instanceof ClassDeclaration) {
      ClassDeclaration cd = ((ClassDeclaration) (ast));
      extendsT = NodeProperties.getType(cd.getSuperclass());
      if (cd.getInterfaces() != null) {
        implementsTs = cd.getInterfaces();
      }
      members = cd.getMembers();
      accessFlags = cd.getModifiers().getBitVector();
      isInterface = false;
    } else if (ast instanceof InterfaceDeclaration) {
      InterfaceDeclaration id = ((InterfaceDeclaration) (ast));
      if (id.getInterfaces() != null) {
        implementsTs = id.getInterfaces();
      }
      members = id.getMembers();
      accessFlags = id.getModifiers().getBitVector(INTERFACE);
      isInterface = true;
    } else if (ast instanceof AnonymousAllocation) {
      AnonymousAllocation aa = ((AnonymousAllocation) (ast));
      ReferenceTypeName parent = aa.getCreationType();
      Type parentT = NodeProperties.getType(parent);
      if (extractClass(parentT).isInterface()) {
        implementsTs = Collections.singletonList(parent);
      } else {
        extendsT = parentT;
      }
      members = aa.getMembers();
      isInterface = false;
    } else if (ast instanceof AnonymousInnerAllocation) {
      extendsT = NodeProperties.getSuperType(ast);
      members = ((AnonymousInnerAllocation) (ast)).getMembers();
      isInterface = false;
    } else {
      throw new RuntimeException("Unexpected class AST node type: " + ast);
    }
    accessFlags = defaultToPublicAccess(accessFlags);
    String classSig = null;
    if (_java5) {
      TypeParameter[] paramAsts;
      if (ast instanceof GenericClassDeclaration) {
        paramAsts = ((GenericClassDeclaration) (ast)).getTypeParameters();
      } else if (ast instanceof GenericInterfaceDeclaration) {
        paramAsts = ((GenericClassDeclaration) (ast)).getTypeParameters();
      } else {
        paramAsts = new TypeParameter[0];
      }
      StringBuilder sigBuilder = new StringBuilder();
      if (paramAsts.length > 0) {
        sigBuilder.append(typeParamListSignature(paramAsts));
      }
      sigBuilder.append(typeSignature(extendsT));
      for (ReferenceTypeName implementsT : implementsTs) {
        sigBuilder.append(typeSignature(NodeProperties.getType(implementsT)));
      }
      classSig = sigBuilder.toString();
    }
    _classWriter.visit(
        _java5 ? V1_5 : V1_4,
        accessFlags,
        _name,
        classSig,
        className(extractClass(extendsT)),
        extractClassNames(implementsTs));
    DJClass declaring = _treeClass.declaringClass();
    if (declaring != null) {
      _classWriter.visitOuterClass(className(declaring), null, null);
      _classWriter.visitInnerClass(
          _name, className(declaring), _treeClass.declaredName(), accessFlags);
    }
    if (isInterface) {
      _classWriter
          .visitField(
              ((ACC_PUBLIC | ACC_STATIC) | ACC_FINAL) | ACC_SYNTHETIC,
              ADAPTER_FIELD,
              EVALUATION_ADAPTER_DESCRIPTOR,
              null,
              null)
          .visitEnd();
    } else {
      _classWriter
          .visitField(
              ((ACC_PRIVATE | ACC_STATIC) | ACC_FINAL) | ACC_SYNTHETIC,
              ADAPTER_FIELD,
              EVALUATION_ADAPTER_DESCRIPTOR,
              null,
              null)
          .visitEnd();
      _classWriter
          .visitField(
              ACC_FINAL | ACC_SYNTHETIC,
              BINDINGS_FACTORY_FIELD,
              BINDINGS_FACTORY_DESCRIPTOR,
              null,
              null)
          .visitEnd();
    }
    final List<ConstructorDeclaration> constructors = new LinkedList<ConstructorDeclaration>();
    for (Node member : members) {
      member.acceptVisitor(
          new AbstractVisitor<Void>() {
            @Override
            public Void defaultCase(Node member) {
              return null;
            }

            @Override
            public Void visit(ClassDeclaration member) {
              recordInnerClass(member, isInterface);
              return null;
            }

            @Override
            public Void visit(InterfaceDeclaration member) {
              recordInnerClass(member, isInterface);
              return null;
            }

            @Override
            public Void visit(ConstructorDeclaration member) {
              constructors.add(member);
              return null;
            }

            @Override
            public Void visit(MethodDeclaration member) {
              compileMethod(member, isInterface);
              return null;
            }

            @Override
            public Void visit(FieldDeclaration member) {
              compileField(member, isInterface);
              return null;
            }

            @Override
            public Void visit(ClassInitializer member) {
              compileInitializerBlock(member, true);
              return null;
            }

            @Override
            public Void visit(InstanceInitializer member) {
              compileInitializerBlock(member, false);
              return null;
            }
          });
    }
    compileClassInitializer();
    if (ast instanceof AnonymousAllocation) {
      compileAnonymousConstructor(((AnonymousAllocation) (ast)));
    } else if (ast instanceof AnonymousInnerAllocation) {
      compileAnonymousInnerConstructor(((AnonymousInnerAllocation) (ast)));
    } else if (ast instanceof ClassDeclaration) {
      if (constructors.isEmpty()) {
        compileDefaultConstructor(extendsT);
      } else {
        for (ConstructorDeclaration k : constructors) {
          compileConstructor(k, extendsT);
        }
      }
    }
    _classWriter.visitEnd();
  }
}
