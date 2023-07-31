class PlaceHold {
  private void compileAnonymousConstructor(AnonymousAllocation ast) {
    MethodVisitor mv =
        _classWriter.visitMethod(
            ACC_PUBLIC, "<init>", ("(" + RUNTIME_BINDINGS_DESCRIPTOR) + ")V", null, null);
    StackSizeTracker stack = new StackSizeTracker(2);
    mv.visitCode();
    emitBindingsFactoryAssignment(mv, 1, stack);
    Type superT = NodeProperties.getType(ast.getCreationType());
    DJClass superC = extractClass(superT);
    if (superC.isInterface()) {
      emitDefaultSuperCall(mv, OBJECT, 1, stack);
    } else {
      stack.mark();
      mv.visitVarInsn(ALOAD, 0);
      stack.adjust(1);
      boolean includeBindings = superC.hasRuntimeBindingsParams();
      String extraArg = "";
      if (includeBindings) {
        extraArg = RUNTIME_BINDINGS_DESCRIPTOR;
        mv.visitVarInsn(ALOAD, 1);
        stack.adjust(1);
      }
      DJConstructor superTarget = NodeProperties.getConstructor(ast).declaredSignature();
      List<Expression> superArgs = ast.getArguments();
      if (superArgs != null) {
        emitAnonSuperArgs(mv, superTarget, superArgs, stack);
      }
      mv.visitMethodInsn(
          INVOKESPECIAL,
          className(superC),
          "<init>",
          paramListDescriptor(extraArg, superTarget.parameters()) + "V");
      stack.reset();
    }
    for (Runnable2<MethodVisitor, StackSizeTracker> initCode : _instanceInits) {
      initCode.run(mv, stack);
    }
    mv.visitInsn(RETURN);
    mv.visitMaxs(stack.maxStack(), stack.maxLocals());
    mv.visitEnd();
  }
}
