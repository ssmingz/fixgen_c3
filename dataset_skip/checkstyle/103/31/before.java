class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle");
    suite.addTest(new TestSuite(AbstractClassNameCheckTest.class));
    suite.addTest(new TestSuite(AbstractViolationReporterTest.class));
    suite.addTest(new TestSuite(AnonInnerLengthCheckTest.class));
    suite.addTest(new TestSuite(ArrayTrailingCommaCheckTest.class));
    suite.addTest(new TestSuite(ArrayTypeStyleCheckTest.class));
    suite.addTest(new TestSuite(AvoidInlineConditionalsCheckTest.class));
    suite.addTest(new TestSuite(AvoidNestedBlocksCheckTest.class));
    suite.addTest(new TestSuite(AvoidStarImportTest.class));
    suite.addTest(new TestSuite(CheckerTest.class));
    suite.addTest(new TestSuite(ConfigurationLoaderTest.class));
    suite.addTest(new TestSuite(ConstantNameCheckTest.class));
    suite.addTest(new TestSuite(CovariantEqualsCheckTest.class));
    suite.addTest(new TestSuite(CyclomaticComplexityCheckTest.class));
    suite.addTest(new TestSuite(DescendantTokenCheckTest.class));
    suite.addTest(new TestSuite(DesignForExtensionCheckTest.class));
    suite.addTest(new TestSuite(DetailASTTest.class));
    suite.addTest(new TestSuite(DeclarationOrderCheckTest.class));
    suite.addTest(new TestSuite(DoubleCheckedLockingCheckTest.class));
    suite.addTest(new TestSuite(EmptyBlockCheckTest.class));
    suite.addTest(new TestSuite(EmptyForIteratorPadCheckTest.class));
    suite.addTest(new TestSuite(EmptyStatementCheckTest.class));
    suite.addTest(new TestSuite(EqualsHashCodeCheckTest.class));
    suite.addTest(new TestSuite(FileLengthCheckTest.class));
    suite.addTest(new TestSuite(FileSetCheckLifecycleTest.class));
    suite.addTest(new TestSuite(FinalClassCheckTest.class));
    suite.addTest(new TestSuite(FinalLocalVariableCheckTest.class));
    suite.addTest(new TestSuite(FinalParametersCheckTest.class));
    suite.addTest(new TestSuite(GenericIllegalRegexpCheckTest.class));
    suite.addTest(new TestSuite(HeaderCheckTest.class));
    suite.addTest(new TestSuite(HiddenFieldCheckTest.class));
    suite.addTest(new TestSuite(HideUtilityClassConstructorCheckTest.class));
    suite.addTest(new TestSuite(IllegalCatchCheckTest.class));
    suite.addTest(new TestSuite(IllegalImportCheckTest.class));
    suite.addTest(new TestSuite(IllegalInstantiationCheckTest.class));
    suite.addTest(new TestSuite(IllegalTokenCheckTest.class));
    suite.addTest(new TestSuite(IllegalTokenTextCheckTest.class));
    suite.addTest(new TestSuite(IllegalTypeCheckTest.class));
    suite.addTest(new TestSuite(IndentationCheckTest.class));
    suite.addTest(new TestSuite(InnerAssignmentCheckTest.class));
    suite.addTest(new TestSuite(InterfaceIsTypeCheckTest.class));
    suite.addTest(new TestSuite(JUnitTestCaseCheckTest.class));
    suite.addTest(new TestSuite(JavadocMethodCheckTest.class));
    suite.addTest(new TestSuite(JavadocStyleCheckTest.class));
    suite.addTest(new TestSuite(JavadocTypeCheckTest.class));
    suite.addTest(new TestSuite(JavadocVariableCheckTest.class));
    suite.addTest(new TestSuite(LeftCurlyCheckTest.class));
    suite.addTest(new TestSuite(LineLengthCheckTest.class));
    suite.addTest(new TestSuite(LocalFinalVariableNameCheckTest.class));
    suite.addTest(new TestSuite(LocalVariableNameCheckTest.class));
    suite.addTest(new TestSuite(MagicNumberCheckTest.class));
    suite.addTest(new TestSuite(MemberNameCheckTest.class));
    suite.addTest(new TestSuite(MethodLengthCheckTest.class));
    suite.addTest(new TestSuite(MethodNameCheckTest.class));
    suite.addTest(new TestSuite(MissingSwitchDefaultCheckTest.class));
    suite.addTest(new TestSuite(ModifierOrderCheckTest.class));
    suite.addTest(new TestSuite(MutableExceptionCheckTest.class));
    suite.addTest(new TestSuite(NeedBracesCheckTest.class));
    suite.addTest(new TestSuite(NestedIfDepthCheckTest.class));
    suite.addTest(new TestSuite(NestedTryDepthCheckTest.class));
    suite.addTest(new TestSuite(NewlineAtEndOfFileCheckTest.class));
    suite.addTest(new TestSuite(NoWhitespaceAfterCheckTest.class));
    suite.addTest(new TestSuite(NoWhitespaceBeforeCheckTest.class));
    suite.addTest(new TestSuite(OperatorWrapCheckTest.class));
    suite.addTest(new TestSuite(OptionTest.class));
    suite.addTest(new TestSuite(PackageDeclarationCheckTest.class));
    suite.addTest(new TestSuite(PackageHtmlCheckTest.class));
    suite.addTest(new TestSuite(PackageNameCheckTest.class));
    suite.addTest(new TestSuite(PackageNamesLoaderTest.class));
    suite.addTest(new TestSuite(PackageObjectFactoryTest.class));
    suite.addTest(new TestSuite(ParameterAssignmentCheckTest.class));
    suite.addTest(new TestSuite(ParameterNameCheckTest.class));
    suite.addTest(new TestSuite(ParameterNumberCheckTest.class));
    suite.addTest(new TestSuite(ParenPadCheckTest.class));
    suite.addTest(new TestSuite(RedundantImportCheckTest.class));
    suite.addTest(new TestSuite(RedundantModifierTest.class));
    suite.addTest(new TestSuite(RedundantThrowsCheckTest.class));
    suite.addTest(new TestSuite(ReturnCountCheckTest.class));
    suite.addTest(new TestSuite(RightCurlyCheckTest.class));
    suite.addTest(new TestSuite(SimplifyBooleanExpressionCheckTest.class));
    suite.addTest(new TestSuite(SimplifyBooleanReturnCheckTest.class));
    suite.addTest(new TestSuite(ExecutableStatementCountCheckTest.class));
    suite.addTest(new TestSuite(StaticVariableNameCheckTest.class));
    suite.addTest(new TestSuite(StringArrayReaderTest.class));
    suite.addTest(new TestSuite(StringLiteralEqualityCheckTest.class));
    suite.addTest(new TestSuite(SuperCloneCheckTest.class));
    suite.addTest(new TestSuite(SuperFinalizeCheckTest.class));
    suite.addTest(new TestSuite(TabCharacterCheckTest.class));
    suite.addTest(new TestSuite(ThrowsCountCheckTest.class));
    suite.addTest(new TestSuite(TodoCommentCheckTest.class));
    suite.addTest(new TestSuite(TranslationCheckTest.class));
    suite.addTest(new TestSuite(TypeNameCheckTest.class));
    suite.addTest(new TestSuite(TypecastParenPadCheckTest.class));
    suite.addTest(new TestSuite(UncommentedMainCheckTest.class));
    suite.addTest(new TestSuite(UnusedImportsCheckTest.class));
    suite.addTest(new TestSuite(UpperEllCheckTest.class));
    suite.addTest(new TestSuite(UtilsTest.class));
    suite.addTest(new TestSuite(VisibilityModifierCheckTest.class));
    suite.addTest(new TestSuite(WhitespaceAfterCheckTest.class));
    suite.addTest(new TestSuite(WhitespaceAroundTest.class));
    suite.addTest(new TestSuite(XMLLoggerTest.class));
    suite.addTest(new TestSuite(EntityBeanCheckTest.class));
    suite.addTest(new TestSuite(FinalStaticCheckTest.class));
    suite.addTest(new TestSuite(LocalHomeInterfaceCheckTest.class));
    suite.addTest(new TestSuite(LocalInterfaceCheckTest.class));
    suite.addTest(new TestSuite(MessageBeanCheckTest.class));
    suite.addTest(new TestSuite(RemoteHomeInterfaceCheckTest.class));
    suite.addTest(new TestSuite(RemoteInterfaceCheckTest.class));
    suite.addTest(new TestSuite(SessionBeanCheckTest.class));
    suite.addTest(new TestSuite(ThisParameterCheckTest.class));
    suite.addTest(new TestSuite(ThisReturnCheckTest.class));
    suite.addTest(new TestSuite(OneMethodPrivateFieldCheckTest.class));
    suite.addTest(new TestSuite(UnusedLocalVariableCheckTest.class));
    suite.addTest(new TestSuite(UnusedParameterCheckTest.class));
    suite.addTest(new TestSuite(UnusedPrivateFieldCheckTest.class));
    suite.addTest(new TestSuite(UnusedPrivateMethodCheckTest.class));
    return suite;
  }
}
