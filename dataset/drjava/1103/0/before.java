class PlaceHold {
  private void compileMethod(MethodDeclaration ast, boolean isInterface) {
    int access =
        (isInterface)
            ? ast.getModifiers().getBitVector(ABSTRACT)
            : ast.getModifiers().getBitVector();
    if (isInterface) {
      access = defaultToPublicAccess(access);
    }
    List<FormalParameter> params = ast.getParameters();
    Type returnT = NodeProperties.getType(ast.getReturnType());
    List<? extends ReferenceTypeName> exceptions = ast.getExceptions();
    boolean isStatic = Modifier.isStatic(access);
    String extraArg = (isStatic) ? RUNTIME_BINDINGS_DESCRIPTOR : "";
    String methodDescriptor =
        paramListDescriptor(extraArg, extractVars(params)) + typeDescriptor(returnT);
    String methodSig = null;
    if (_java5) {
      TypeParameter[] typeParamAsts;
      if (ast instanceof PolymorphicMethodDeclaration) {
        typeParamAsts = ((PolymorphicMethodDeclaration) (ast)).getTypeParameters();
      } else {
        typeParamAsts = new TypeParameter[0];
      }
      StringBuilder sigBuilder = new StringBuilder();
      if (typeParamAsts.length > 0) {
        sigBuilder.append(typeParamListSignature(typeParamAsts));
      }
      sigBuilder.append(paramListSignature(extraArg, extractVars(params)));
      sigBuilder.append(typeSignature(returnT));
      for (ReferenceTypeName tn : exceptions) {
        sigBuilder.append('^').append(typeSignature(NodeProperties.getType(tn)));
      }
      methodSig = sigBuilder.toString();
    }
    final MethodVisitor mv =
        _classWriter.visitMethod(
            access, ast.getName(), methodDescriptor, methodSig, extractClassNames(exceptions));
    if (!Modifier.isAbstract(access)) {
      String key = ast.getName() + methodDescriptor;
      _methods.put(key, ast);
      int[] paramLocations = computeParamLocations(params, 1);
      StackSizeTracker stack = new StackSizeTracker(paramLocations[params.size()]);
      mv.visitCode();
      int boxedParamsVar = emitBoxParams(mv, params, paramLocations, stack);
      mv.visitFieldInsn(GETSTATIC, _name, ADAPTER_FIELD, EVALUATION_ADAPTER_DESCRIPTOR);
      mv.visitLdcInsn(key);
      stack.adjust(2);
      if (isStatic) {
        mv.visitVarInsn(ALOAD, 0);
        stack.adjust(1);
      } else {
        emitLoadBindings(mv, 0, _name, stack);
      }
      mv.visitVarInsn(ALOAD, boxedParamsVar);
      stack.adjust(1);
      mv.visitMethodInsn(
          INVOKEVIRTUAL, EVALUATION_ADAPTER_NAME, "evaluateMethod", EVALUATE_METHOD_DESCRIPTOR);
      stack.adjust(-3);
      stack.mark();
      emitConvert(mv, returnT, stack);
      _opt.typeSystem()
          .erase(returnT)
          .apply(
              new TypeAbstractVisitor_void() {
                @Override
                public void forReferenceType(ReferenceType t) {
                  mv.visitInsn(ARETURN);
                }

                @Override
                public void forPrimitiveType(PrimitiveType t) {
                  mv.visitInsn(IRETURN);
                }

                @Override
                public void forLongType(LongType t) {
                  mv.visitInsn(LRETURN);
                }

                @Override
                public void forFloatType(FloatType t) {
                  mv.visitInsn(FRETURN);
                }

                @Override
                public void forDoubleType(DoubleType t) {
                  mv.visitInsn(DRETURN);
                }

                @Override
                public void forVoidType(VoidType t) {
                  mv.visitInsn(RETURN);
                }
              });
      stack.reset();
      mv.visitMaxs(stack.maxStack(), stack.maxLocals());
    }
    mv.visitEnd();
  }
}
