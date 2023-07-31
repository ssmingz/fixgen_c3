class PlaceHold {
  public void testPropertyExpansion() {
    assertExpandsTo("", "");
    assertExpandsTo("$", "$");
    assertExpandsTo("$$-", "$-");
    assertExpandsTo("$$", "$");
    project.setProperty("expanded", "EXPANDED");
    assertExpandsTo("a${expanded}b", "aEXPANDEDb");
    assertExpandsTo("${expanded}${expanded}", "EXPANDEDEXPANDED");
    assertExpandsTo("$$$", "$$");
    assertExpandsTo("$$$$-", "$$-");
    assertExpandsTo("", "");
    assertExpandsTo("Class$$subclass", "Class$subclass");
  }
}
