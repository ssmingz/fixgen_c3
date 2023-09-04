class LaunchConfigurationWorkingCopy {
  protected LaunchConfigurationWorkingCopy(
      IContainer container, String name, ILaunchConfigurationType type) {
    super((IPath) null);
    setName(name);
    setInfo(new LaunchConfigurationInfo());
    getInfo().setType(type);
    setContainer(container);
    fSuppressChange = false;
  }
}
