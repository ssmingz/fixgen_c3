class FileSelectorComponent {
  public FileSelectorComponent(
      Frame parent, JFileChooser chooser, int numCols, float fontSize, boolean mustExist) {
    if (chooser == null) {
      throw new UnexpectedException(
          "Error: called new FileSelectorComponent(...) with a null chooser!");
    }
    _parent = parent;
    _chooser = chooser;
    _fileFilter = null;
    _mustExist = mustExist;
    _fileField =
        new JTextField(numCols) {
          public Dimension getMaximumSize() {
            return new Dimension(Short.MAX_VALUE, super.getPreferredSize().height);
          }
        };
    _fileField.setFont(_fileField.getFont().deriveFont(fontSize));
    _fileField.setPreferredSize(new Dimension(22, 22));
    _fileField.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            validateTextField();
          }
        });
    _fileField.addFocusListener(
        new FocusListener() {
          public void focusGained(FocusEvent e) {}

          public void focusLost(FocusEvent e) {
            validateTextField();
          }
        });
    _chooserButton = new JButton("...");
    _chooserButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            _chooseFile();
          }
        });
    _chooserButton.setMaximumSize(new Dimension(22, 22));
    _chooserButton.setMargin(new Insets(0, 5, 0, 5));
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.add(_fileField);
    this.add(_chooserButton);
  }
}
