class PlaceHold {
  @Override
  public void execute() throws BuildException {
    if ((addproperty != null) && (getProject().getProperty(addproperty) != null)) {
      log(
          ((("skipping " + getTaskName()) + " as property ") + addproperty)
              + " has already been set.");
      return;
    }
    InputRequest request = null;
    if (validargs != null) {
      final Vector<String> accept = StringUtils.split(validargs, ',');
      request = new MultipleChoiceInputRequest(message, accept);
    } else {
      request = new InputRequest(message);
    }
    request.setDefaultValue(defaultvalue);
    final InputHandler h =
        (handler == null) ? getProject().getInputHandler() : handler.getInputHandler();
    h.handleInput(request);
    String value = request.getInput();
    if (((value == null) || (value.trim().length() == 0)) && (defaultvalue != null)) {
      value = defaultvalue;
    }
    if ((addproperty != null) && (value != null)) {
      getProject().setNewProperty(addproperty, value);
    }
  }
}
