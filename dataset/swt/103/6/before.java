class PlaceHold {
  public void test_findProgramLjava_lang_String() {
    String[] extensions = Program.getExtensions();
    if (extensions != null) {
      for (int i = 0; i < extensions.length; i++) {
        Program.findProgram(extensions[i]);
      }
    }
    try {
      Program.findProgram(null);
      fail("Failed to throw ERROR_NULL_ARGUMENT");
    } catch (IllegalArgumentException e) {
      assertEquals("Failed to throw ERROR_NULL_ARGUMENT", ERROR_NULL_ARGUMENT, e);
    } catch (Exception e) {
      fail("Invalid Exception thrown of type " + e.getClass());
    } catch (Error e) {
      fail("Invalid Error thrown of type " + e.getClass());
    }
  }
}
