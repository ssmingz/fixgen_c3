class LaunchConfigurationWorkingCopy {
  protected LaunchConfigurationWorkingCopy(
      IContainer container, String name, ILaunchConfigurationType type) {
    super(name, container);
    setInfo(new LaunchConfigurationInfo());
    getInfo().setType(type);
    fSuppressChange = false;
  }
}
