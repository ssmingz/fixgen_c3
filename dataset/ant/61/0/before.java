class PlaceHold{
public void testNoAppend() throws IOException {
    executeTarget("noappend");
    assertTrue(FILE_UTILS.contentEquals(project.resolveFile(REC_DIR + "rectest1.result"), project.resolveFile(REC_DIR + "rectest1.log")));
}
}