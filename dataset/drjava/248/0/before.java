class PlaceHold {
  public void interpreterExited(final int status) {
    if (DrJava.getConfig().getSetting(INTERACTIONS_EXIT_PROMPT).booleanValue()) {
      Runnable command =
          new Runnable() {
            public void run() {
              String msg =
                  ((("The interactions window was terminated by a call " + "to System.exit(")
                              + status)
                          + ").\n")
                      + "The interactions window will now be restarted.";
              String title = ("Interactions terminated by System.exit(" + status) + ")";
              ConfirmCheckBoxDialog dialog =
                  new ConfirmCheckBoxDialog(
                      MainFrame.this,
                      title,
                      msg,
                      "Do not show this message again",
                      JOptionPane.INFORMATION_MESSAGE,
                      JOptionPane.DEFAULT_OPTION);
              if ((dialog.show() == JOptionPane.OK_OPTION) && dialog.getCheckBoxValue()) {
                DrJava.getConfig().setSetting(INTERACTIONS_EXIT_PROMPT, Boolean.FALSE);
              }
            }
          };
      Utilities.invokeLater(command);
    }
  }
}
