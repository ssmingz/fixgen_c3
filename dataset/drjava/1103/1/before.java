class PlaceHold {
  private void compileConstructor(ConstructorDeclaration ast, Type extendsT) {
    DJClass outerC = SymbolUtil.dynamicOuterClass(_treeClass);
    Type outerT = (outerC == null) ? null : SymbolUtil.thisType(outerC);
    List<FormalParameter> params = ast.getParameters();
    List<? extends ReferenceTypeName> exceptions = ast.getExceptions();
    String firstArgDescriptor =
        (outerT == null) ? RUNTIME_BINDINGS_DESCRIPTOR : typeDescriptor(outerT);
    String methodDescriptor = paramListDescriptor(firstArgDescriptor, extractVars(params)) + "V";
    String methodSig = null;
    if (_java5) {
      TypeParameter[] typeParamAsts;
      if (ast instanceof PolymorphicConstructorDeclaration) {
        typeParamAsts = ((PolymorphicConstructorDeclaration) (ast)).getTypeParameters();
      } else {
        typeParamAsts = new TypeParameter[0];
      }
      StringBuilder sigBuilder = new StringBuilder();
      if (typeParamAsts.length > 0) {
        sigBuilder.append(typeParamListSignature(typeParamAsts));
      }
      String firstArgSig = (outerT == null) ? RUNTIME_BINDINGS_DESCRIPTOR : typeSignature(outerT);
      sigBuilder.append(paramListSignature(firstArgSig, extractVars(params)));
      sigBuilder.append("V");
      for (ReferenceTypeName tn : exceptions) {
        sigBuilder.append('^').append(typeSignature(NodeProperties.getType(tn)));
      }
      methodSig = sigBuilder.toString();
    }
    int access = defaultToProtectedAccess(ast.getModifiers().getBitVector());
    MethodVisitor mv =
        _classWriter.visitMethod(
            access, "<init>", methodDescriptor, methodSig, extractClassNames(exceptions));
    String key = methodDescriptor;
    _constructors.put(key, ast);
    int[] paramLocations = computeParamLocations(params, 2);
    StackSizeTracker stack = new StackSizeTracker(paramLocations[params.size()]);
    mv.visitCode();
    int bindingsVar = emitPartialBindingsVar(mv, outerC, stack);
    int boxedParamsVar = emitBoxParams(mv, params, paramLocations, stack);
    boolean callsSuper;
    ConstructorCall call = ast.getConstructorCall();
    if (call == null) {
      callsSuper = true;
      emitBindingsFactoryAssignment(mv, bindingsVar, stack);
      emitDefaultSuperCall(mv, extendsT, bindingsVar, stack);
    } else {
      callsSuper = call.isSuper();
      if (callsSuper) {
        emitBindingsFactoryAssignment(mv, bindingsVar, stack);
      }
      DJConstructor callTarget = NodeProperties.getConstructor(call).declaredSignature();
      DJClass extendsC = extractClass(extendsT);
      stack.mark();
      mv.visitVarInsn(ALOAD, 0);
      stack.adjust(1);
      String extraArg = "";
      if (call.getExpression() == null) {
        if (callsSuper) {
          if (extendsC.hasRuntimeBindingsParams()) {
            extraArg = RUNTIME_BINDINGS_DESCRIPTOR;
            mv.visitVarInsn(ALOAD, bindingsVar);
            stack.adjust(1);
          }
        } else if (outerC == null) {
          extraArg = RUNTIME_BINDINGS_DESCRIPTOR;
          mv.visitVarInsn(ALOAD, bindingsVar);
          stack.adjust(1);
        } else {
          extraArg = typeDescriptor(outerT);
          mv.visitVarInsn(ALOAD, 1);
          stack.adjust(1);
        }
      } else {
        if (callsSuper) {
          extraArg = typeDescriptor(SymbolUtil.thisType(SymbolUtil.dynamicOuterClass(extendsC)));
        } else {
          extraArg = typeDescriptor(outerT);
        }
        mv.visitFieldInsn(GETSTATIC, _name, ADAPTER_FIELD, EVALUATION_ADAPTER_DESCRIPTOR);
        mv.visitLdcInsn(key);
        stack.adjust(2);
        emitIntConstant(mv, -1, stack);
        mv.visitVarInsn(ALOAD, bindingsVar);
        mv.visitVarInsn(ALOAD, boxedParamsVar);
        stack.adjust(2);
        mv.visitMethodInsn(
            INVOKEVIRTUAL,
            EVALUATION_ADAPTER_NAME,
            "evaluateConstructorCallArg",
            EVALUATE_CONSTRUCTOR_CALL_ARG_DESCRIPTOR);
        stack.adjust(-4);
      }
      int i = 0;
      for (Pair<LocalVariable, Expression> arg :
          IterUtil.zip(callTarget.parameters(), call.getArguments())) {
        Type paramT = arg.first().type();
        Object val = expressionConstantVal(arg.second());
        if (val == null) {
          mv.visitFieldInsn(GETSTATIC, _name, ADAPTER_FIELD, EVALUATION_ADAPTER_DESCRIPTOR);
          mv.visitLdcInsn(key);
          stack.adjust(2);
          emitIntConstant(mv, i, stack);
          mv.visitVarInsn(ALOAD, bindingsVar);
          mv.visitVarInsn(ALOAD, boxedParamsVar);
          stack.adjust(2);
          mv.visitMethodInsn(
              INVOKEVIRTUAL,
              EVALUATION_ADAPTER_NAME,
              "evaluateConstructorCallArg",
              EVALUATE_CONSTRUCTOR_CALL_ARG_DESCRIPTOR);
          stack.adjust(-4);
          emitConvert(mv, paramT, stack);
        } else {
          emitConstant(mv, val, stack);
        }
        i++;
      }
      mv.visitMethodInsn(
          INVOKESPECIAL,
          callsSuper ? className(extendsC) : _name,
          "<init>",
          paramListDescriptor(extraArg, callTarget.parameters()) + "V");
      stack.reset();
    }
    if (callsSuper) {
      for (Runnable2<MethodVisitor, StackSizeTracker> initCode : _instanceInits) {
        initCode.run(mv, stack);
      }
    }
    mv.visitFieldInsn(GETSTATIC, _name, ADAPTER_FIELD, EVALUATION_ADAPTER_DESCRIPTOR);
    mv.visitLdcInsn(key);
    stack.adjust(2);
    emitLoadBindings(mv, 0, _name, stack);
    mv.visitVarInsn(ALOAD, boxedParamsVar);
    stack.adjust(1);
    mv.visitMethodInsn(
        INVOKEVIRTUAL,
        EVALUATION_ADAPTER_NAME,
        "evaluateConstructorBody",
        EVALUATE_CONSTRUCTOR_BODY_DESCRIPTOR);
    stack.adjust(-4);
    mv.visitInsn(RETURN);
    mv.visitMaxs(stack.maxStack(), stack.maxLocals());
    mv.visitEnd();
  }
}
