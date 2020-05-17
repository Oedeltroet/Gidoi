# Gidoi - Changelog

In this file, all notable changes to the project are documented and commented by the developer.

## Version 0.2 (Beta)

2020-05-17

### Features

* #### Updater
  Gidoi's first major update is all about the Updater. This new feature automatically checks if an update is available and offers the user to download and install it. To make that possible, the program must be constantly located in a cloned git repository. However, git is _not_ required to run the program, since everything is handled by the program itself, using a library called [JGit](https://www.eclipse.org/jgit/). To repair or update the repository, the latest version will be cloned into a temporary folder, whose contents will then be moved up to replace the current files. Since the _.jar_ file cannot be replaced at runtime, those operations will be carried out by temporary, platform specific script files.
* #### Splash Screen
  Loading times can vary and, depending on numerous factors, take from just a few moments up to several minutes. To show and assure the user that the program is not inactive, a splash screen is now visualising the loading process.
* #### Mac OS & Linux compatibility
  When it comes to script-executed file operations, we leave Java's compfortable platform independent environment and need some platform specific code. And as for that, the three major operating systems vary in quite a few aspects.

### Files

* Added [License file](LICENSE)
* Updated [Readme file](README.md)
* Removed unnecessary files:

 * _bin/_, _.target/_ and _Thumbs.db_, since they are automatically generated
 * _.project_, _.settings/_ and _.classpath_, since they are Eclipse specific
 * _Gidoi.exe_, since it is a large, redundant, Windows specific file
 
### Bugfixes and minor changes
 
* Corrected typo _Giodi.jar_ -> _Gidoi.jar_