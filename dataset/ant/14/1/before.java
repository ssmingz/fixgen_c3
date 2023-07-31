class PlaceHold{
public void testNolanguage() {
    expectBuildExceptionContaining("testNolanguage", "Absence of language attribute not detected", "script language must be specified");
}
}