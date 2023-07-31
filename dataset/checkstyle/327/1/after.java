class HandlerFactory {
  public HandlerFactory() {
    register(CASE_GROUP, CaseHandler.class);
    register(LITERAL_SWITCH, SwitchHandler.class);
    register(SLIST, SlistHandler.class);
    register(PACKAGE_DEF, PackageDefHandler.class);
    register(LITERAL_ELSE, ElseHandler.class);
    register(LITERAL_IF, IfHandler.class);
    register(LITERAL_TRY, TryHandler.class);
    register(LITERAL_CATCH, CatchHandler.class);
    register(LITERAL_FINALLY, FinallyHandler.class);
    register(LITERAL_DO, DoWhileHandler.class);
    register(LITERAL_WHILE, WhileHandler.class);
    register(LITERAL_FOR, ForHandler.class);
    register(METHOD_DEF, MethodDefHandler.class);
    register(CTOR_DEF, MethodDefHandler.class);
    register(CLASS_DEF, ClassDefHandler.class);
    register(ENUM_DEF, ClassDefHandler.class);
    register(OBJBLOCK, ObjectBlockHandler.class);
    register(INTERFACE_DEF, ClassDefHandler.class);
    register(IMPORT, ImportHandler.class);
    register(ARRAY_INIT, ArrayInitHandler.class);
    register(METHOD_CALL, MethodCallHandler.class);
    register(CTOR_CALL, MethodCallHandler.class);
    register(LABELED_STAT, LabelHandler.class);
    register(STATIC_INIT, StaticInitHandler.class);
    register(INSTANCE_INIT, SlistHandler.class);
    register(ASSIGN, AssignHandler.class);
    register(PLUS_ASSIGN, AssignHandler.class);
    register(MINUS_ASSIGN, AssignHandler.class);
    register(STAR_ASSIGN, AssignHandler.class);
    register(DIV_ASSIGN, AssignHandler.class);
    register(MOD_ASSIGN, AssignHandler.class);
    register(SR_ASSIGN, AssignHandler.class);
    register(BSR_ASSIGN, AssignHandler.class);
    register(SL_ASSIGN, AssignHandler.class);
    register(BAND_ASSIGN, AssignHandler.class);
    register(BXOR_ASSIGN, AssignHandler.class);
    register(BOR_ASSIGN, AssignHandler.class);
    register(VARIABLE_DEF, MemberDefHandler.class);
    register(LITERAL_NEW, NewHandler.class);
    register(INDEX_OP, IndexHandler.class);
  }
}
