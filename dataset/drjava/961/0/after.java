class PlaceHold {
  private void compileAnonymousInnerConstructor(AnonymousInnerAllocation ast) {
    MethodVisitor mv =
        _classWriter.visitMethod(
            ACC_PUBLIC, "<init>", ("(" + RUNTIME_BINDINGS_DESCRIPTOR) + ")V", null, null);
    StackSizeTracker stack = new StackSizeTracker(2);
    mv.visitCode();
    emitBindingsFactoryAssignment(mv, 1, stack);
    Type superT = NodeProperties.getSuperType(ast);
    DJClass superC = extractClass(superT);
    DJClass superOuterC = SymbolUtil.dynamicOuterClass(superC);
    stack.mark();
    mv.visitVarInsn(ALOAD, 0);
    stack.adjust(1);
    String outerExpKey = "anon super arg outer";
    _expressions.put(outerExpKey, ast.getExpression());
    mv.visitFieldInsn(GETSTATIC, _name, ADAPTER_FIELD, EVALUATION_ADAPTER_DESCRIPTOR);
    mv.visitLdcInsn(outerExpKey);
    mv.visitVarInsn(ALOAD, 1);
    stack.adjust(3);
    mv.visitMethodInsn(
        INVOKEVIRTUAL,
        EVALUATION_ADAPTER_NAME,
        "evaluateExpression",
        EVALUATE_EXPRESSION_DESCRIPTOR);
    stack.adjust(-2);
    DJConstructor superTarget = NodeProperties.getConstructor(ast).declaredSignature();
    List<Expression> superArgs = ast.getArguments();
    if (superArgs != null) {
      emitAnonSuperArgs(mv, superTarget, superArgs, stack);
    }
    String callDescriptor =
        paramListDescriptor(
                typeDescriptor(SymbolUtil.thisType(superOuterC)), superTarget.parameters())
            + "V";
    mv.visitMethodInsn(INVOKESPECIAL, className(superC), "<init>", callDescriptor);
    stack.reset();
    for (Runnable2<MethodVisitor, StackSizeTracker> initCode : _instanceInits) {
      initCode.run(mv, stack);
    }
    mv.visitInsn(RETURN);
    mv.visitMaxs(stack.maxStack(), stack.maxLocals());
    mv.visitEnd();
  }
}
